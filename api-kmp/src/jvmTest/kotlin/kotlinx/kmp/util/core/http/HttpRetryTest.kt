// File generated from our OpenAPI spec by Stainless.

package kotlinx.kmp.util.core.http

import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.errors.ApiRetryableException
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.client.WireMock.matching
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.resetAllScenarios
import com.github.tomakehurst.wiremock.client.WireMock.serviceUnavailable
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.stubbing.Scenario
import java.io.InputStream
import java.time.Clock
import java.time.OffsetDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class HttpRetryTest {

    private var openResponseCount = 0
    private lateinit var baseUrl: String
    private lateinit var httpClient: HttpClient

    private class RecordingDelay {
        val durations = mutableListOf<Duration>()
        val fn: suspend (Duration) -> Unit = { durations.add(it) }
    }

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        val backing = KtorHttpClient()
        httpClient =
            object : HttpClient {

                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = trackClose(backing.execute(withBaseUrl(request), requestOptions))

                fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    backing.executeAsync(withBaseUrl(request), requestOptions).thenApply { trackClose(it) }

                override suspend fun executeSuspend(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse = trackClose(backing.executeSuspend(withBaseUrl(request), requestOptions))

                override fun close() = backing.close()

                private fun withBaseUrl(request: HttpRequest): HttpRequest =
                    if (request.baseUrl != null) request
                    else request.toBuilder().baseUrl(baseUrl).build()

                private fun trackClose(response: HttpResponse): HttpResponse {
                    openResponseCount++
                    return object : HttpResponse {

                        private var isClosed = false

                        override fun statusCode(): Int = response.statusCode()

                        override fun headers(): Headers = response.headers()

                        override fun body(): okio.BufferedSource = response.body()

                        override fun close() {
                            response.close()
                            if (isClosed) {
                                return
                            }
                            openResponseCount--
                            isClosed = true
                        }
                    }
                }
            }
        resetAllScenarios()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute(async: Boolean) {
        stubFor(post(urlPathEqualTo("/something")).willReturn(ok()))
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 2, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder().method(HttpMethod.POST).addPathSegment("something").build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(1, postRequestedFor(urlPathEqualTo("/something")))
        assertThat(sleeper.durations).isEmpty()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withIdempotencyHeader(async: Boolean) {
        stubFor(
            post(urlPathEqualTo("/something"))
                .withHeader("X-Some-Header", matching("stainless-retry-.+"))
                .willReturn(ok())
        )
        val sleeper = RecordingDelay()
        val retryingClient =
            retryingClientFrom(httpClient, maxRetries = 2, sleeper = sleeper, clock = Clock.systemUTC(), idempotencyHeader = "X-Some-Header")

        val response =
            retryingClient.execute(
                HttpRequest.builder().method(HttpMethod.POST).addPathSegment("something").build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(1, postRequestedFor(urlPathEqualTo("/something")))
        assertThat(sleeper.durations).isEmpty()
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withRetryAfterHeader(async: Boolean) {
        val retryAfterDate = "Wed, 21 Oct 2015 07:28:00 GMT"
        stubFor(
            post(urlPathEqualTo("/something"))
                // First we fail with a retry after header given as a date
                .inScenario("foo")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serviceUnavailable().withHeader("Retry-After", retryAfterDate))
                .willSetStateTo("RETRY_AFTER_DATE")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                // Then we fail with a retry after header given as a delay
                .inScenario("foo")
                .whenScenarioStateIs("RETRY_AFTER_DATE")
                .willReturn(serviceUnavailable().withHeader("Retry-After", "1.234"))
                .willSetStateTo("RETRY_AFTER_DELAY")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                // Then we return a success
                .inScenario("foo")
                .whenScenarioStateIs("RETRY_AFTER_DELAY")
                .willReturn(ok())
                .willSetStateTo("COMPLETED")
        )
        // Fix the clock to 5 seconds before the Retry-After date so the date-based backoff is
        // deterministic.
        val retryAfterDateTime =
            OffsetDateTime.parse(retryAfterDate, DateTimeFormatter.RFC_1123_DATE_TIME)
        val clock = Clock.fixed(retryAfterDateTime.minusSeconds(5).toInstant(), ZoneOffset.UTC)
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 2, sleeper = sleeper, clock = clock)

        val response =
            retryingClient.execute(
                HttpRequest.builder().method(HttpMethod.POST).addPathSegment("something").build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(
            1,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("0")),
        )
        verify(
            1,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("1")),
        )
        verify(
            1,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("2")),
        )
        assertThat(sleeper.durations)
            .containsExactly(5.seconds, 1234.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withOverwrittenRetryCountHeader(async: Boolean) {
        val retryAfterDate = "Wed, 21 Oct 2015 07:28:00 GMT"
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo") // first we fail with a retry after header given as a date
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serviceUnavailable().withHeader("Retry-After", retryAfterDate))
                .willSetStateTo("RETRY_AFTER_DATE")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo") // then we return a success
                .whenScenarioStateIs("RETRY_AFTER_DATE")
                .willReturn(ok())
                .willSetStateTo("COMPLETED")
        )
        val retryAfterDateTime =
            OffsetDateTime.parse(retryAfterDate, DateTimeFormatter.RFC_1123_DATE_TIME)
        val clock = Clock.fixed(retryAfterDateTime.minusSeconds(5).toInstant(), ZoneOffset.UTC)
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 2, sleeper = sleeper, clock = clock)

        val response =
            retryingClient.execute(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .addPathSegment("something")
                    .putHeader("x-stainless-retry-count", "42")
                    .build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(
            2,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("42")),
        )
        assertThat(sleeper.durations).containsExactly(5.seconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withRetryAfterMsHeader(async: Boolean) {
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serviceUnavailable().withHeader("Retry-After-Ms", "10"))
                .willSetStateTo("RETRY_AFTER_DELAY")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo") // then we return a success
                .whenScenarioStateIs("RETRY_AFTER_DELAY")
                .willReturn(ok())
                .willSetStateTo("COMPLETED")
        )
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 1, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder().method(HttpMethod.POST).addPathSegment("something").build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(2, postRequestedFor(urlPathEqualTo("/something")))
        assertThat(sleeper.durations).containsExactly(10.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withRetryableException(async: Boolean) {
        stubFor(post(urlPathEqualTo("/something")).willReturn(ok()))

        var callCount = 0
        val failingHttpClient =
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse {
                    callCount++
                    if (callCount == 1) {
                        throw ApiRetryableException("Simulated retryable failure")
                    }
                    return httpClient.execute(request, requestOptions)
                }

                fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> {
                    callCount++
                    if (callCount == 1) {
                        val future = CompletableFuture<HttpResponse>()
                        future.completeExceptionally(
                            ApiRetryableException("Simulated retryable failure")
                        )
                        return future
                    }
                    return httpClient.executeAsync(request, requestOptions)
                }

                override suspend fun executeSuspend(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse {
                    callCount++
                    if (callCount == 1) {
                        throw ApiRetryableException("Simulated retryable failure")
                    }
                    return httpClient.executeSuspend(request, requestOptions)
                }

                override fun close() = httpClient.close()
            }

        val sleeper = RecordingDelay()
        val retryingClient =
            retryingClientFrom(failingHttpClient, maxRetries = 2, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder().method(HttpMethod.POST).addPathSegment("something").build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        verify(
            1,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("1")),
        )
        verify(
            0,
            postRequestedFor(urlPathEqualTo("/something"))
                .withHeader("x-stainless-retry-count", equalTo("0")),
        )
        // Exponential backoff with jitter: 0.5s * jitter where jitter is in [0.75, 1.0].
        assertThat(sleeper.durations).hasSize(1)
        assertThat(sleeper.durations[0]).isBetween(375.milliseconds, 500.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withExponentialBackoff(async: Boolean) {
        stubFor(post(urlPathEqualTo("/something")).willReturn(serviceUnavailable()))
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 3, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build(),
                async,
            )

        // All retries exhausted; the last 503 response is returned.
        assertThat(response.statusCode()).isEqualTo(503)
        verify(4, postRequestedFor(urlPathEqualTo("/something")))
        // Exponential backoff with jitter: backoff = min(0.5 * 2^(retries-1), 8) * jitter where
        // jitter is in [0.75, 1.0].
        assertThat(sleeper.durations).hasSize(3)
        // retries=1: 0.5s * [0.75, 1.0]
        assertThat(sleeper.durations[0]).isBetween(375.milliseconds, 500.milliseconds)
        // retries=2: 1s * [0.75, 1.0]
        assertThat(sleeper.durations[1]).isBetween(750.milliseconds, 1000.milliseconds)
        // retries=3: 2s * [0.75, 1.0]
        assertThat(sleeper.durations[2]).isBetween(1500.milliseconds, 2000.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withExponentialBackoffCap(async: Boolean) {
        stubFor(post(urlPathEqualTo("/something")).willReturn(serviceUnavailable()))
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 6, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(503)
        verify(7, postRequestedFor(urlPathEqualTo("/something")))
        assertThat(sleeper.durations).hasSize(6)
        // retries=5: backoff hits the 8s cap * [0.75, 1.0]
        assertThat(sleeper.durations[4]).isBetween(6000.milliseconds, 8000.milliseconds)
        // retries=6: still capped at 8s * [0.75, 1.0]
        assertThat(sleeper.durations[5]).isBetween(6000.milliseconds, 8000.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withRetryAfterMsPriorityOverRetryAfter(async: Boolean) {
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(
                    serviceUnavailable()
                        .withHeader("Retry-After-Ms", "50")
                        .withHeader("Retry-After", "2")
                )
                .willSetStateTo("RETRY")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo")
                .whenScenarioStateIs("RETRY")
                .willReturn(ok())
                .willSetStateTo("COMPLETED")
        )
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 1, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        // Retry-After-Ms (50ms) takes priority over Retry-After (2s).
        assertThat(sleeper.durations).containsExactly(50.milliseconds)
        assertNoResponseLeaks()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun execute_withRetryAfterUnparseable(async: Boolean) {
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serviceUnavailable().withHeader("Retry-After", "not-a-date-or-number"))
                .willSetStateTo("RETRY")
        )
        stubFor(
            post(urlPathEqualTo("/something"))
                .inScenario("foo")
                .whenScenarioStateIs("RETRY")
                .willReturn(ok())
                .willSetStateTo("COMPLETED")
        )
        val sleeper = RecordingDelay()
        val retryingClient = retryingClientFrom(httpClient, maxRetries = 1, sleeper = sleeper, clock = Clock.systemUTC())

        val response =
            retryingClient.execute(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build(),
                async,
            )

        assertThat(response.statusCode()).isEqualTo(200)
        // Unparseable Retry-After falls through to exponential backoff.
        assertThat(sleeper.durations).hasSize(1)
        assertThat(sleeper.durations[0]).isBetween(375.milliseconds, 500.milliseconds)
        assertNoResponseLeaks()
    }

    private fun HttpClient.execute(request: HttpRequest, async: Boolean): HttpResponse =
        if (async) executeAsync(request, RequestOptions.none()).get() else execute(request, RequestOptions.none())

    // When retrying, all failed responses should be closed. Only the final returned response should
    // be open.
    private fun assertNoResponseLeaks() = assertThat(openResponseCount).isEqualTo(1)
    /**
     * Build a retry-wrapped HttpClient via ClientOptions, exercising the same
     * wiring that the production SDK uses (ClientOptions.build() calls
     * httpClient.withRetry(maxRetries, sleeper, clock) under the hood).
     */
    private fun retryingClientFrom(
        rawClient: HttpClient,
        maxRetries: Int,
        sleeper: RecordingDelay,
        clock: Clock,
        idempotencyHeader: String? = null,
    ): HttpClient {
        val builder = ClientOptions.builder()
            .httpClient(rawClient)
            .delayFn(sleeper.fn)
            .nowMillisProvider { clock.millis() }
            .maxRetries(maxRetries)
            .idempotencyHeader(idempotencyHeader)
            .baseUrl(baseUrl)
        return builder.build().httpClient
    }

}