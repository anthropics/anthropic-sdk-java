package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.*
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Wrap an [HttpClient] with retry logic.
 *
 * Provider-agnostic replacement for the former `RetryingHttpClient` class. Its
 * retry policy mirrors ktor's `HttpRequestRetry` plugin:
 *   - retries on 408/409/429/5xx and any `Throwable` whose type implements [Retryable]
 *   - honors `Retry-After` + `Retry-After-Ms` headers (numeric or RFC 1123)
 *   - honors `X-Should-Retry: true|false` server override
 *   - exponential backoff with jitter, capped at 8s
 */
fun HttpClient.withRetry(
    maxRetries: Int,
    sleeper: Sleeper,
    idempotencyHeader: String? = null,
    nowMillisProvider: () -> Long = ::currentTimeMillis,
): HttpClient = RetryClient(this, sleeper, maxRetries, idempotencyHeader, nowMillisProvider)

/** Marker interface for exceptions that should always trigger a retry. */
interface Retryable

@OptIn(ExperimentalUuidApi::class)
private class RetryClient(
    private val delegate: HttpClient,
    private val sleeper: Sleeper,
    private val maxRetries: Int,
    private val idempotencyHeader: String?,
    private val nowMillisProvider: () -> Long,
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        var modifiedRequest = maybeAddIdempotencyHeader(request)
        val shouldSendRetryCount = !modifiedRequest.headers.names().contains("X-Stainless-Retry-Count")
        var retries = 0

        while (true) {
            if (shouldSendRetryCount) {
                modifiedRequest = setRetryCountHeader(modifiedRequest, retries)
            }
            if (!isRetryable(modifiedRequest)) {
                return delegate.execute(modifiedRequest, requestOptions)
            }
            val response = try {
                val r = delegate.execute(modifiedRequest, requestOptions)
                if (++retries > maxRetries || !shouldRetry(r)) return r
                r
            } catch (t: Throwable) {
                if (++retries > maxRetries || !shouldRetry(t)) throw t
                null
            }
            val backoff = getRetryBackoffDuration(retries, response)
            response?.close()
            sleeper.sleep(backoff)
        }
    }

    override suspend fun executeSuspend(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): HttpResponse {
        var modifiedRequest = maybeAddIdempotencyHeader(request)
        val shouldSendRetryCount = !modifiedRequest.headers.names().contains("X-Stainless-Retry-Count")
        var retries = 0

        while (true) {
            if (shouldSendRetryCount) {
                modifiedRequest = setRetryCountHeader(modifiedRequest, retries)
            }
            if (!isRetryable(modifiedRequest)) {
                return delegate.executeSuspend(modifiedRequest, requestOptions)
            }
            val response = try {
                val r = delegate.executeSuspend(modifiedRequest, requestOptions)
                if (++retries > maxRetries || !shouldRetry(r)) return r
                r
            } catch (t: Throwable) {
                if (++retries > maxRetries || !shouldRetry(t)) throw t
                null
            }
            val backoff = getRetryBackoffDuration(retries, response)
            response?.close()
            sleeper.sleepSuspend(backoff)
        }
    }

    override fun close() {
        delegate.close()
        sleeper.close()
    }

    private fun isRetryable(request: HttpRequest): Boolean = request.body?.repeatable() ?: true

    private fun setRetryCountHeader(request: HttpRequest, retries: Int): HttpRequest =
        request.toBuilder().replaceHeaders("X-Stainless-Retry-Count", retries.toString()).build()

    private fun idempotencyKey(): String = "stainless-retry-${Uuid.random()}"

    private fun maybeAddIdempotencyHeader(request: HttpRequest): HttpRequest {
        if (idempotencyHeader == null || request.headers.names().contains(idempotencyHeader)) return request
        return request.toBuilder().putHeader(idempotencyHeader, idempotencyKey()).build()
    }

    private fun shouldRetry(response: HttpResponse): Boolean {
        val header = response.headers().values("X-Should-Retry").getOrNull(0)
        val status = response.statusCode()
        return when {
            header == "true" -> true
            header == "false" -> false
            status == 408 || status == 409 || status == 429 -> true
            status >= 500 -> true
            else -> false
        }
    }

    private fun shouldRetry(throwable: Throwable): Boolean =
        throwable is Retryable

    private fun getRetryBackoffDuration(retries: Int, response: HttpResponse?): Duration {
        response?.headers()?.let { headers ->
            headers.values("Retry-After-Ms").getOrNull(0)?.toFloatOrNull()?.times(1_000_000L)
                ?: headers.values("Retry-After").getOrNull(0)?.let { retryAfter ->
                    retryAfter.toFloatOrNull()?.times(1_000_000_000L)
                        ?: parseRetryAfterToDelayNanos(retryAfter, nowMillisProvider())
                }
        }?.let { return it.toLong().nanoseconds }

        val backoffSeconds = min(0.5 * 2.0.pow(retries - 1), 8.0)
        val jitter = 1.0 - 0.25 * Random.nextDouble()
        return (1_000_000_000L * backoffSeconds * jitter).toLong().nanoseconds
    }
}
