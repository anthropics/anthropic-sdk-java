// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core.http

import com.anthropic.core.DefaultSleeper
import com.anthropic.core.RequestOptions
import com.anthropic.core.Sleeper
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicIoException
import com.anthropic.errors.AnthropicRetryableException
import java.io.IOException
import java.time.Clock
import java.time.Duration
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import java.util.function.Function
import kotlin.math.min
import kotlin.math.pow

class RetryingHttpClient
private constructor(
    private val httpClient: HttpClient,
    private val sleeper: Sleeper,
    private val clock: Clock,
    private val maxRetries: Int,
    private val idempotencyHeader: String?,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        var modifiedRequest = prepareRequest(request)

        // Don't send the current retry count in the headers if the caller set their own value.
        val shouldSendRetryCount =
            !modifiedRequest.headers.names().contains("X-Stainless-Retry-Count")

        var retries = 0

        while (true) {
            if (shouldSendRetryCount) {
                modifiedRequest = setRetryCountHeader(modifiedRequest, retries)
            }

            if (!isRetryable(modifiedRequest)) {
                return httpClient.execute(modifiedRequest, requestOptions)
            }

            val response =
                try {
                    val response = httpClient.execute(modifiedRequest, requestOptions)
                    if (++retries > maxRetries || !shouldRetry(response)) {
                        return response
                    }

                    response
                } catch (throwable: Throwable) {
                    if (++retries > maxRetries || !shouldRetry(throwable, modifiedRequest)) {
                        throw throwable
                    }

                    null
                }

            val backoffDuration = getRetryBackoffDuration(retries, response)
            // All responses must be closed, so close the failed one before retrying.
            response?.close()
            sleeper.sleep(backoffDuration)
        }
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> {
        val modifiedRequest = prepareRequest(request)

        // Don't send the current retry count in the headers if the caller set their own value.
        val shouldSendRetryCount =
            !modifiedRequest.headers.names().contains("X-Stainless-Retry-Count")

        var retries = 0

        fun executeWithRetries(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            val requestWithRetryCount =
                if (shouldSendRetryCount) setRetryCountHeader(request, retries) else request

            val responseFuture = httpClient.executeAsync(requestWithRetryCount, requestOptions)
            if (!isRetryable(requestWithRetryCount)) {
                return responseFuture
            }

            return responseFuture
                .handleAsync(
                    fun(
                        response: HttpResponse?,
                        throwable: Throwable?,
                    ): CompletableFuture<HttpResponse> {
                        if (response != null) {
                            if (++retries > maxRetries || !shouldRetry(response)) {
                                return CompletableFuture.completedFuture(response)
                            }
                        } else {
                            if (++retries > maxRetries ||
                                !shouldRetry(throwable!!, requestWithRetryCount)
                            ) {
                                val failedFuture = CompletableFuture<HttpResponse>()
                                failedFuture.completeExceptionally(throwable)
                                return failedFuture
                            }
                        }

                        val backoffDuration = getRetryBackoffDuration(retries, response)
                        // All responses must be closed, so close the failed one before retrying.
                        response?.close()
                        return sleeper.sleepAsync(backoffDuration).thenCompose {
                            executeWithRetries(requestWithRetryCount, requestOptions)
                        }
                    }
                ) {
                    // Run in the same thread.
                    it.run()
                }
                .thenCompose(Function.identity())
        }

        return executeWithRetries(modifiedRequest, requestOptions)
    }

    override fun close() {
        httpClient.close()
        sleeper.close()
    }

    private fun isRetryable(request: HttpRequest): Boolean =
        // Some requests, such as when a request body is being streamed, cannot be retried because
        // the body data aren't available on subsequent attempts.
        request.body?.repeatable() ?: true

    private fun setRetryCountHeader(request: HttpRequest, retries: Int): HttpRequest =
        request.toBuilder().replaceHeaders("X-Stainless-Retry-Count", retries.toString()).build()

    private fun idempotencyKey(): String = "stainless-java-retry-${UUID.randomUUID()}"

    /**
     * Attach a stable idempotency header before the first attempt so retries reuse the same key.
     *
     * For [POST /v1/sessions/{id}/events][isSessionEventsMutation], always attach
     * [IDEMPOTENCY_KEY_HEADER] (unless the caller already set it). Transport failures for that path
     * are still not retried until the API documents server-side dedup for the header; the key
     * remains useful for status-code retries and for future server support.
     */
    private fun prepareRequest(request: HttpRequest): HttpRequest {
        var modified = maybeAddIdempotencyHeader(request)
        if (isSessionEventsMutation(modified) &&
            !modified.headers.names().contains(IDEMPOTENCY_KEY_HEADER)
        ) {
            modified =
                modified.toBuilder().putHeader(IDEMPOTENCY_KEY_HEADER, idempotencyKey()).build()
        }
        return modified
    }

    private fun maybeAddIdempotencyHeader(request: HttpRequest): HttpRequest {
        if (idempotencyHeader == null || request.headers.names().contains(idempotencyHeader)) {
            return request
        }

        return request
            .toBuilder()
            // Set a header to uniquely identify the request when retried.
            .putHeader(idempotencyHeader, idempotencyKey())
            .build()
    }

    /**
     * `POST /v1/sessions/{session_id}/events` appends user/system turns. A timed-out client request
     * that still landed server-side must not be retried blindly or the session sees a duplicated
     * turn (see github.com/anthropics/anthropic-sdk-java/issues/370).
     */
    private fun isSessionEventsMutation(request: HttpRequest): Boolean {
        if (request.method != HttpMethod.POST) {
            return false
        }
        val segments = request.pathSegments
        // Path is ["v1", "sessions", "{session_id}", "events"] (optional trailing segments ignored).
        return segments.size >= 4 &&
            segments[0] == "v1" &&
            segments[1] == "sessions" &&
            segments[3] == "events"
    }

    private fun shouldRetry(response: HttpResponse): Boolean {
        // Note: this is not a standard header
        val shouldRetryHeader = response.headers().values("X-Should-Retry").getOrNull(0)
        val statusCode = response.statusCode()

        return when {
            // If the server explicitly says whether to retry, obey
            shouldRetryHeader == "true" -> true
            shouldRetryHeader == "false" -> false

            // Retry on request timeouts
            statusCode == 408 -> true
            // Retry on lock timeouts
            statusCode == 409 -> true
            // Retry on rate limits
            statusCode == 429 -> true
            // Retry internal errors
            statusCode >= 500 -> true
            else -> false
        }
    }

    private fun shouldRetry(throwable: Throwable, request: HttpRequest): Boolean {
        // Explicitly marked retryable failures always retry.
        if (throwable is AnthropicRetryableException) {
            return true
        }
        if (throwable !is IOException && throwable !is AnthropicIoException) {
            return false
        }
        // Timed-out-but-landed session event sends would duplicate user turns if retried.
        // Refuse transport retries for that path; status-code retries still apply above.
        if (isSessionEventsMutation(request)) {
            return false
        }
        return true
    }

    private fun getRetryBackoffDuration(retries: Int, response: HttpResponse?): Duration {
        // About the Retry-After header:
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Retry-After
        response
            ?.headers()
            ?.let { headers ->
                headers
                    .values("Retry-After-Ms")
                    .getOrNull(0)
                    ?.toFloatOrNull()
                    ?.times(TimeUnit.MILLISECONDS.toNanos(1))
                    ?: headers.values("Retry-After").getOrNull(0)?.let { retryAfter ->
                        retryAfter.toFloatOrNull()?.times(TimeUnit.SECONDS.toNanos(1))
                            ?: try {
                                ChronoUnit.NANOS.between(
                                    OffsetDateTime.now(clock),
                                    OffsetDateTime.parse(
                                        retryAfter,
                                        DateTimeFormatter.RFC_1123_DATE_TIME,
                                    ),
                                )
                            } catch (e: DateTimeParseException) {
                                null
                            }
                    }
            }
            ?.let { retryAfterNanos ->
                // If the API asks us to wait a certain amount of time, do what it says.
                return Duration.ofNanos(retryAfterNanos.toLong())
            }

        // Apply exponential backoff, but not more than the max.
        val backoffSeconds = min(0.5 * 2.0.pow(retries - 1), 8.0)

        // Apply some jitter
        val jitter = 1.0 - 0.25 * ThreadLocalRandom.current().nextDouble()

        return Duration.ofNanos((TimeUnit.SECONDS.toNanos(1) * backoffSeconds * jitter).toLong())
    }

    companion object {

        /** Standard request-idempotency header attached to session event mutations. */
        internal const val IDEMPOTENCY_KEY_HEADER = "Idempotency-Key"

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var httpClient: HttpClient? = null
        private var sleeper: Sleeper? = null
        private var clock: Clock = Clock.systemUTC()
        private var maxRetries: Int = 2
        private var idempotencyHeader: String? = null

        fun httpClient(httpClient: HttpClient) = apply { this.httpClient = httpClient }

        fun sleeper(sleeper: Sleeper) = apply { this.sleeper = sleeper }

        fun clock(clock: Clock) = apply { this.clock = clock }

        fun maxRetries(maxRetries: Int) = apply { this.maxRetries = maxRetries }

        fun idempotencyHeader(header: String) = apply { this.idempotencyHeader = header }

        fun build(): HttpClient =
            RetryingHttpClient(
                checkRequired("httpClient", httpClient),
                sleeper ?: DefaultSleeper(),
                clock,
                maxRetries,
                idempotencyHeader,
            )
    }
}
