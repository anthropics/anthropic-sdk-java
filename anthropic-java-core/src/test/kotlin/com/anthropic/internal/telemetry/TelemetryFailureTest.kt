package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.LogLevel
import com.anthropic.core.Params
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.QueryParams
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.opentelemetry.endApiCallSpanWhenStreamEnds
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.models.messages.MessageCountTokensParams
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.opentelemetry.api.trace.StatusCode
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/** A failure to record a call on its span neither fails the call nor keeps the span open. */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class TelemetryFailureTest : TracingTestSupport() {

    /** A response with [body] whose headers cannot be read. With logging off, only a span does. */
    private class UnreadableHeadersResponse(private val body: String) : HttpResponse {
        override fun statusCode(): Int = 200

        override fun headers(): Headers = error("BOOM")

        override fun body(): InputStream = ByteArrayInputStream(body.toByteArray())

        override fun close() {}
    }

    private class UnreadableHeadersClient(private val body: String) : HttpClient {
        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            UnreadableHeadersResponse(body)

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            CompletableFuture.completedFuture(execute(request, requestOptions))

        override fun close() {}
    }

    private class SpanDataParams(private val spanData: SpanData) : Params {
        override fun _headers(): Headers = Headers.builder().build()

        override fun _queryParams(): QueryParams = QueryParams.builder().build()

        override fun _spanData(): SpanData = spanData
    }

    private fun clientOptions(httpClient: HttpClient, logLevel: LogLevel = LogLevel.OFF) =
        ClientOptions.builder()
            .httpClient(httpClient)
            .baseUrl(baseUrl)
            .maxRetries(0)
            .logLevel(logLevel)
            .openTelemetry(options())
            .build()

    private fun request(method: HttpMethod = HttpMethod.POST): HttpRequest =
        HttpRequest.builder()
            .method(method)
            .baseUrl(baseUrl)
            .addPathSegments("v1", "things")
            .build()

    /** The failures to record that [block] logs. */
    private fun telemetryFailuresOf(block: () -> Unit): List<String> {
        val original = System.err
        val captured = ByteArrayOutputStream()
        System.setErr(PrintStream(captured, true, "UTF-8"))
        try {
            block()
        } finally {
            System.setErr(original)
        }
        return captured.toString("UTF-8").lines().filter { it.startsWith("Could not ") }
    }

    @ParameterizedTest
    @ValueSource(
        strings =
            [
                "create",
                "countTokens",
                "closedUnparsed",
                "createStreaming",
                "createAsync",
                "createStreamingAsync",
            ]
    )
    fun responseWhoseHeadersCannotBeRead_neitherFailsTheCallNorKeepsTheSpanOpen(method: String) {
        fun client(body: String): AnthropicClient =
            AnthropicClientImpl(clientOptions(UnreadableHeadersClient(body)))

        when (method) {
            "create" ->
                assertThat(client(MESSAGE_JSON).messages().create(messageParams()).id())
                    .isEqualTo("msg_1")
            "countTokens" ->
                assertThat(
                        client("""{"input_tokens": 3}""")
                            .messages()
                            .countTokens(
                                MessageCountTokensParams.builder()
                                    .model("claude-requested")
                                    .addUserMessage("hello")
                                    .build()
                            )
                            .inputTokens()
                    )
                    .isEqualTo(3)
            "closedUnparsed" ->
                client(MESSAGE_JSON).messages().withRawResponse().create(messageParams()).close()
            "createStreaming" ->
                client(STREAM_BODY).messages().createStreaming(messageParams()).use {
                    assertThat(it.stream().count()).isEqualTo(STREAM_EVENTS.size.toLong())
                }
            "createAsync" ->
                assertThat(
                        client(MESSAGE_JSON).async().messages().create(messageParams()).get().id()
                    )
                    .isEqualTo("msg_1")
            "createStreamingAsync" ->
                client(STREAM_BODY)
                    .async()
                    .messages()
                    .createStreaming(messageParams())
                    .subscribe {}
                    .onCompleteFuture()
                    .get()
        }

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(attributesOf(span)).doesNotContainKey("anthropic.request.id")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun responseWhoseHeadersCannotBeRead_isLoggedOnce() {
        val clientOptions = clientOptions(FakeHttpClient("{}"), LogLevel.DEBUG)
        var parsed: String? = null

        val failures = telemetryFailuresOf {
            parsed =
                traceApiCall(clientOptions, SpanDataParams(SpanData("call", "/v1/things"))) { client
                        ->
                        client.execute(request(), RequestOptions.none()).close()
                        UnreadableHeadersResponse("{}").parseable { "parsed" }
                    }
                    .parse()
        }

        assertThat(parsed).isEqualTo("parsed")
        assertThat(failures)
            .containsExactly(
                "Could not record the response's headers on its span: " +
                    "java.lang.IllegalStateException"
            )
        assertThat(recorder.only().status.statusCode).isEqualTo(StatusCode.OK)
    }

    @ParameterizedTest
    @CsvSource("POST, headers", "GET, headers", "POST, statusCode")
    @ResourceLock(Resources.SYSTEM_ERR)
    fun streamOfAResponseThatCannotBeRead_isReadToItsEnd_whichEndsTheSpan(
        method: HttpMethod,
        failing: String,
    ) {
        val call =
            ApiCall(
                recorder.tracerProvider.get("test").spanBuilder("call").startSpan(),
                clientOptions(FakeHttpClient(), LogLevel.DEBUG),
            )
        call.recordFirstRequest(request(method))
        val source =
            object : StreamResponse<String> {
                override fun stream(): Stream<String> = Stream.of("first", "second")

                override fun close() {}
            }
        val response =
            object : HttpResponse {
                override fun statusCode(): Int {
                    check(failing != "statusCode") { "BOOM" }
                    return 200
                }

                override fun headers(): Headers {
                    check(failing != "headers") { "BOOM" }
                    return Headers.builder().build()
                }

                override fun body(): InputStream = ByteArrayInputStream(ByteArray(0))

                override fun close() {}
            }
        var events: List<String>? = null

        val failures = telemetryFailuresOf {
            events =
                endApiCallSpanWhenStreamEnds(call, source, response, null).use {
                    it.stream().collect(Collectors.toList())
                }
        }

        assertThat(events).containsExactly("first", "second")
        assertThat(failures)
            .containsExactly(
                "Could not record the stream's response on its span: " +
                    "java.lang.IllegalStateException"
            )
        assertThat(recorder.only().status.statusCode).isEqualTo(StatusCode.OK)
    }

    @ParameterizedTest
    @ValueSource(strings = ["request", "response"])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun spanDataThatThrows_neitherFailsTheCallNorKeepsTheSpanOpen(failing: String) {
        val clientOptions = clientOptions(FakeHttpClient("{}"), LogLevel.DEBUG)
        val spanData =
            if (failing == "request")
                SpanData("call", "/v1/things", requestAttributes = { error("BOOM") })
            else SpanData("call", "/v1/things", responseAttributes = { error("BOOM") })
        var parsed: String? = null

        val failures = telemetryFailuresOf {
            parsed =
                traceApiCall(clientOptions, SpanDataParams(spanData)) { client ->
                        client.execute(request(), RequestOptions.none()).parseable { "parsed" }
                    }
                    .parse()
        }

        assertThat(parsed).isEqualTo("parsed")
        assertThat(failures)
            .containsExactly(
                "Could not record the $failing on its span: java.lang.IllegalStateException"
            )
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("http.route")).isEqualTo("/v1/things")
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
    }

    @Test
    fun spanDataThatThrowsAnError_failsTheCall_whichEndsTheSpan() {
        val spanData =
            SpanData("call", "/v1/things", responseAttributes = { throw LinkageError("BOOM") })

        assertThrows<LinkageError> {
            traceApiCall(clientOptions(FakeHttpClient("{}")), SpanDataParams(spanData)) { client ->
                    client.execute(request(), RequestOptions.none()).parseable { "parsed" }
                }
                .parse()
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("client_error")
    }
}
