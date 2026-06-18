package com.anthropic.helpers

import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaFallbackBlockParam
import com.anthropic.models.beta.messages.BetaFallbackInfoParam
import com.anthropic.models.beta.messages.BetaFallbackParam
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaThinkingBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BetaRefusalFallbackInterceptorTest {

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun retriesRefusalWithFallbackParamsAndCreditToken(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model", "credit-token"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        // A fresh state, so falling back doesn't write the missing-state warning to the stderr of
        // concurrently running tests asserting on it.
        val requestOptions =
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()
        val response = interceptedClient.execute(messagesRequest(), requestOptions, async)

        val message = response.json(BetaMessage::class.java)
        assertThat(message.model().asString()).isEqualTo("fallback-model")
        assertThat(message.stopReason().getOrNull()?.asString()).isEqualTo("end_turn")
        assertThat(httpClient.models()).containsExactly("primary-model", "fallback-model")
        assertThat(httpClient.bodies[1].fallbackCreditToken().getOrNull()).isEqualTo("credit-token")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun prependsAFallbackSeamBlockToAServedRetry(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model", "credit-token"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val requestOptions =
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()
        val response = interceptedClient.execute(messagesRequest(), requestOptions, async)

        // The served message mirrors the server-side stitched envelope: a `fallback` seam block
        // prepended to (not replacing) the serving hop's content.
        val message = response.json(BetaMessage::class.java)
        assertThat(message.content()).hasSize(2)
        val seam = message.content()[0].asFallback()
        assertThat(seam.from().model().asString()).isEqualTo("primary-model")
        assertThat(seam.to().model().asString()).isEqualTo("fallback-model")
        assertThat(message.content()[1].asText().text()).isEqualTo("ok")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun prependsASeamBlockOnATokenlessRetryToo(async: Boolean) {
        // The seam is unconditional on every served fallback retry, tokenless ones included.
        val httpClient = FakeHttpClient(refusal("primary-model"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val requestOptions =
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()
        val response = interceptedClient.execute(messagesRequest(), requestOptions, async)

        val message = response.json(BetaMessage::class.java)
        assertThat(message.content()).hasSize(2)
        val seam = message.content()[0].asFallback()
        assertThat(seam.from().model().asString()).isEqualTo("primary-model")
        assertThat(seam.to().model().asString()).isEqualTo("fallback-model")
        assertThat(message.content()[1].asText().text()).isEqualTo("ok")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun prependsOneSeamBlockPerModelBoundaryOnAMultiHopChain(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model"), refusal("mid-model"), message("last-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("mid-model"))
                .addFallback(fallback("last-model"))
                .build()
                .intercept(httpClient)

        val requestOptions =
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()
        val response = interceptedClient.execute(messagesRequest(), requestOptions, async)

        // One seam per boundary, in order, then the serving hop's own content.
        val message = response.json(BetaMessage::class.java)
        assertThat(message.content()).hasSize(3)
        val first = message.content()[0].asFallback()
        assertThat(first.from().model().asString()).isEqualTo("primary-model")
        assertThat(first.to().model().asString()).isEqualTo("mid-model")
        val second = message.content()[1].asFallback()
        assertThat(second.from().model().asString()).isEqualTo("mid-model")
        assertThat(second.to().model().asString()).isEqualTo("last-model")
        assertThat(message.content()[2].asText().text()).isEqualTo("ok")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun pinsToAcceptedFallbackViaFallbackState(async: Boolean) {
        val httpClient =
            FakeHttpClient(
                refusal("primary-model"),
                message("fallback-model"),
                message("fallback-model"),
            )
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val fallbackState = BetaFallbackState.create()
        val requestOptions = RequestOptions.builder().fallbackState(fallbackState).build()
        interceptedClient.execute(messagesRequest(), requestOptions, async)
        assertThat(fallbackState.index()).isEqualTo(0)

        // The follow-up goes straight to the pinned fallback in a single request.
        interceptedClient.execute(messagesRequest(), requestOptions, async)
        assertThat(httpClient.models())
            .containsExactly("primary-model", "fallback-model", "fallback-model")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun throwsWhenStateIndexIsOutOfBounds(async: Boolean) {
        val httpClient = FakeHttpClient(message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val fallbackState = BetaFallbackState.create()
        fallbackState.index(1)
        val requestOptions = RequestOptions.builder().fallbackState(fallbackState).build()

        assertThatThrownBy { interceptedClient.execute(messagesRequest(), requestOptions, async) }
            .isInstanceOf(AnthropicException::class.java)
            .hasMessageContaining(
                "fallbackState.index 1 is out of bounds for a chain of 1 fallback(s)"
            )
        assertThat(httpClient.requests).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun warnsOnceWhenFallingBackWithoutState(async: Boolean) {
        val httpClient =
            FakeHttpClient(
                refusal("primary-model"),
                message("fallback-model"),
                refusal("primary-model"),
                message("fallback-model"),
            )
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val stderr = captureStderr {
            interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)
            interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)
        }

        assertThat(httpClient.requests).hasSize(4)
        assertThat(stderr.lines().filter { it.contains("fallbackState") }).hasSize(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aSeparateStateIsUnaffectedByAnotherState(async: Boolean) {
        val httpClient =
            FakeHttpClient(
                refusal("primary-model"),
                message("fallback-model"),
                message("primary-model"),
            )
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        interceptedClient.execute(
            messagesRequest(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )
        interceptedClient.execute(
            messagesRequest(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(httpClient.models())
            .containsExactly("primary-model", "fallback-model", "primary-model")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun leavesAcceptedRequestsAndTheResponseUntouched(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val fallbackState = BetaFallbackState.create()
        val response =
            interceptedClient.execute(
                messagesRequest(),
                RequestOptions.builder().fallbackState(fallbackState).build(),
                async,
            )

        assertThat(response.json(BetaMessage::class.java).model().asString())
            .isEqualTo("primary-model")
        assertThat(httpClient.requests).hasSize(1)
        assertThat(httpClient.bodies[0].fallbackCreditToken()).isEmpty()
        assertThat(fallbackState.index()).isEqualTo(-1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun tagsTheOriginalAndFallbackRequests(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model", "credit-token"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        interceptedClient.execute(
            messagesRequest(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(httpClient.requests.map { it.headers.values(STAINLESS_HELPER_HEADER) })
            .containsExactly(
                listOf("fallback-refusal-middleware"),
                listOf("fallback-refusal-middleware"),
            )
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun appendsToAHelperTagAlreadyOnTheRequest(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        interceptedClient.execute(
            messagesRequest().toBuilder().putHeader("X-Stainless-Helper", "BetaToolRunner").build(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(httpClient.requests.single().headers.values(STAINLESS_HELPER_HEADER))
            .containsExactly("BetaToolRunner, fallback-refusal-middleware")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun doesNotTagRequestsItPassesThrough(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        // the GA surface (no ?beta=true) is not applicable to this interceptor
        val gaRequest =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "messages")
                .body(HttpRequestBody.ofJson(messagesBody()))
                .build()
        interceptedClient.execute(gaRequest, RequestOptions.none(), async)

        assertThat(httpClient.requests.single().headers.values(STAINLESS_HELPER_HEADER)).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun walksEachHopThroughTheChainUntilAModelAccepts(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model"), refusal("mid-model"), message("last-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("mid-model"))
                .addFallback(fallback("last-model"))
                .build()
                .intercept(httpClient)

        val fallbackState = BetaFallbackState.create()
        val response =
            interceptedClient.execute(
                messagesRequest(),
                RequestOptions.builder().fallbackState(fallbackState).build(),
                async,
            )

        assertThat(response.json(BetaMessage::class.java).model().asString())
            .isEqualTo("last-model")
        assertThat(fallbackState.index()).isEqualTo(1)
        assertThat(httpClient.models()).containsExactly("primary-model", "mid-model", "last-model")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun returnsTheFinalRefusalOnceTheChainIsExhausted(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"), refusal("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        var response: HttpResponse? = null
        // Capture the missing-state warning to avoid polluting test output.
        captureStderr {
            response = interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)
        }

        val message = response!!.json(BetaMessage::class.java)
        assertThat(message.model().asString()).isEqualTo("fallback-model")
        assertThat(message.stopReason().getOrNull()?.asString()).isEqualTo("refusal")
        // A terminal refusal is surfaced verbatim — no seam block prepended.
        assertThat(message.content()).isEmpty()
        assertThat(httpClient.requests).hasSize(2)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun returnsErrorResponsesAsIs(async: Boolean) {
        // Even a refusal-shaped body must not trigger a fallback when the status is an error.
        val httpClient = FakeHttpClient(429 to refusal("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val response = interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)

        assertThat(response.statusCode()).isEqualTo(429)
        assertThat(httpClient.requests).hasSize(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun appendsTheFallbackCreditBetaToEveryHandledRequest(async: Boolean) {
        val httpClient =
            FakeHttpClient(refusal("primary-model", "credit-token"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        interceptedClient.execute(
            messagesRequest(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(httpClient.requests).hasSize(2)
        httpClient.requests.forEach {
            assertThat(it.headers.values("anthropic-beta"))
                .containsExactly("fallback-credit-2026-06-01")
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun doesNotDuplicateAnAlreadyPresentBeta(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val request =
            messagesRequest()
                .toBuilder()
                .putHeader("anthropic-beta", "some-other-beta,fallback-credit-2026-06-01")
                .build()
        interceptedClient.execute(request, RequestOptions.none(), async)

        assertThat(httpClient.requests[0].headers.values("anthropic-beta"))
            .containsExactly("some-other-beta,fallback-credit-2026-06-01")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun sendsCustomBetasInsteadOfTheDefault(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .betas(listOf(AnthropicBeta.of("custom-beta")))
                .build()
                .intercept(httpClient)

        interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)

        assertThat(httpClient.requests[0].headers.values("anthropic-beta"))
            .containsExactly("custom-beta")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun sendsNoBetasWhenConfiguredWithAnEmptyList(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .betas(emptyList())
                .build()
                .intercept(httpClient)

        interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)

        assertThat(httpClient.requests[0].headers.values("anthropic-beta")).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun anEmptyChainDisablesTheInterceptor(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder().build().intercept(httpClient)

        val response = interceptedClient.execute(messagesRequest(), RequestOptions.none(), async)

        assertThat(response.json(BetaMessage::class.java).stopReason().getOrNull()?.asString())
            .isEqualTo("refusal")
        assertThat(httpClient.requests).hasSize(1)
        assertThat(httpClient.requests[0].headers.values("anthropic-beta")).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun appliesFallbackAdditionalPropertiesToRetriedRequests(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"), message("fallback-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(
                    fallback("fallback-model")
                        .toBuilder()
                        .putAdditionalProperty("custom_field", JsonValue.from("custom-value"))
                        .build()
                )
                .build()
                .intercept(httpClient)

        interceptedClient.execute(
            messagesRequest(),
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(httpClient.bodies[1]._additionalProperties()["custom_field"])
            .isEqualTo(JsonValue.from("custom-value"))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun trimsReplayedFallbackTurnsFromOutgoingHistory(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        // A turn served by a fallback, replayed as the server streamed it: the refused
        // attempt's thinking + unresolved tool call, the fallback seam, then the serving
        // model's answer.
        val request =
            requestWithHistory(
                listOf(
                    userMessage("hi"),
                    assistantMessage(
                        BetaContentBlockParam.ofThinking(
                            BetaThinkingBlockParam.builder()
                                .thinking("refused attempt")
                                .signature("sig")
                                .build()
                        ),
                        BetaContentBlockParam.ofToolUse(
                            BetaToolUseBlockParam.builder()
                                .id("toolu_1")
                                .name("get_weather")
                                .input(BetaToolUseBlockParam.Input.builder().build())
                                .build()
                        ),
                        seamBlock(),
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder().text("answer").build()
                        ),
                    ),
                    userMessage("continue"),
                )
            )
        interceptedClient.execute(
            request,
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        val sent = httpClient.jsonBodies[0].path("messages")
        assertThat(sent.size()).isEqualTo(3)
        val turn = sent.path(1)
        assertThat(turn.path("role").asText()).isEqualTo("assistant")
        assertThat(turn.path("content").map { it.path("type").asText() }).containsExactly("text")
        assertThat(turn.path("content").path(0).path("text").asText()).isEqualTo("answer")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun dropsAssistantTurnsLeftEmptyByTheTrim(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val request =
            requestWithHistory(
                listOf(userMessage("hi"), assistantMessage(seamBlock()), userMessage("continue"))
            )
        interceptedClient.execute(
            request,
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        val sent = httpClient.jsonBodies[0].path("messages")
        assertThat(sent.map { it.path("role").asText() }).containsExactly("user", "user")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun passesSeamFreeHistoryThroughByteForByte(async: Boolean) {
        val httpClient = FakeHttpClient(message("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        // Deliberately a hand-formatted raw string, not typed builders: non-canonical key
        // order and whitespace a JSON round-trip would rewrite are the point of this test.
        val rawBody =
            """{"max_tokens": 1024,  "messages": [{"content": "hi", "role": "user"}], "model": "primary-model"}"""
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "messages")
                .putQueryParam("beta", "true")
                .body(rawJsonBody(rawBody))
                .build()
        interceptedClient.execute(
            request,
            RequestOptions.builder().fallbackState(BetaFallbackState.create()).build(),
            async,
        )

        assertThat(bodyBytes(httpClient.requests[0])).isEqualTo(rawBody.toByteArray())
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun throwsOnRequestsWithServerSideFallbacks(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val body =
            messagesBody().toBuilder().fallbacks(listOf(fallback("server-side-model"))).build()

        assertThatThrownBy {
                interceptedClient.execute(messagesRequest(body), RequestOptions.none(), async)
            }
            .isInstanceOf(AnthropicException::class.java)
            .hasMessage(
                "Sending the `fallbacks:` request param is not supported when using the `BetaRefusalFallbackInterceptor`. You should either remove the middleware and send `fallbacks:` with the `server-side-fallback-2026-06-01` beta header to let the API handle refusal fallbacks, or omit the `fallbacks:` param if you'd like `BetaRefusalFallbackInterceptor` to handle fallbacks on the client side."
            )
        assertThat(httpClient.requests).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun skipsNonMessagesRequests(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "complete")
                .body(HttpRequestBody.ofJson(messagesBody()))
                .build()
        interceptedClient.execute(request, RequestOptions.none(), async)

        assertThat(httpClient.requests).hasSize(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun skipsNonBetaMessagesRequests(async: Boolean) {
        val httpClient = FakeHttpClient(refusal("primary-model"))
        val interceptedClient =
            BetaRefusalFallbackInterceptor.builder()
                .addFallback(fallback("fallback-model"))
                .build()
                .intercept(httpClient)

        // The same request the beta service would make, minus its `beta=true` tag — what the
        // non-beta `client.messages()` service sends.
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "messages")
                .body(HttpRequestBody.ofJson(messagesBody()))
                .build()
        interceptedClient.execute(request, RequestOptions.none(), async)

        assertThat(httpClient.requests).hasSize(1)
        assertThat(httpClient.requests.single().headers.values("anthropic-beta")).isEmpty()
    }

    private fun fallback(model: String): BetaFallbackParam =
        BetaFallbackParam.builder().model(model).build()

    private fun requestWithHistory(messages: List<BetaMessageParam>): HttpRequest =
        messagesRequest(
            MessageCreateParams.Body.builder()
                .model("primary-model")
                .maxTokens(1024)
                .messages(messages)
                .build()
        )

    private fun userMessage(text: String): BetaMessageParam =
        BetaMessageParam.builder().role(BetaMessageParam.Role.USER).content(text).build()

    private fun assistantMessage(vararg blocks: BetaContentBlockParam): BetaMessageParam =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.ASSISTANT)
            .contentOfBetaContentBlockParams(blocks.toList())
            .build()

    /** A replayed `fallback` seam, as a prior fallback-served response echoes back. */
    private fun seamBlock(): BetaContentBlockParam =
        BetaContentBlockParam.ofFallback(
            BetaFallbackBlockParam.builder()
                .from(BetaFallbackInfoParam.builder().model("primary-model").build())
                .to(BetaFallbackInfoParam.builder().model("fallback-model").build())
                .build()
        )

    /** A repeatable raw-bytes JSON body, so byte-identity through the interceptor is testable. */
    private fun rawJsonBody(json: String): HttpRequestBody =
        object : HttpRequestBody {
            private val bytes = json.toByteArray()

            override fun writeTo(outputStream: OutputStream) = outputStream.write(bytes)

            override fun contentType(): String = "application/json"

            override fun contentLength(): Long = bytes.size.toLong()

            override fun repeatable(): Boolean = true

            override fun close() {}
        }

    private fun bodyBytes(request: HttpRequest): ByteArray =
        ByteArrayOutputStream().also { request.body!!.writeTo(it) }.toByteArray()

    private fun messagesBody(): MessageCreateParams.Body =
        MessageCreateParams.Body.builder()
            .model("primary-model")
            .maxTokens(1024)
            .addUserMessage("hi")
            .build()

    private fun messagesRequest(body: MessageCreateParams.Body = messagesBody()): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://api.example.com")
            .addPathSegments("v1", "messages")
            .putQueryParam("beta", "true")
            .body(HttpRequestBody.ofJson(body))
            .build()

    private fun message(model: String): String =
        """
        {
          "id": "msg_1",
          "type": "message",
          "role": "assistant",
          "model": "$model",
          "content": [{"type": "text", "text": "ok"}],
          "stop_reason": "end_turn",
          "stop_sequence": null,
          "usage": {"input_tokens": 1, "output_tokens": 1}
        }
        """
            .trimIndent()

    private fun refusal(model: String, fallbackCreditToken: String? = null): String =
        """
        {
          "id": "msg_1",
          "type": "message",
          "role": "assistant",
          "model": "$model",
          "content": [],
          "stop_reason": "refusal",
          "stop_details": {
            "type": "refusal",
            "reason": "other",
            "explanation": null,
            "fallback_credit_token": ${fallbackCreditToken?.let { "\"$it\"" }}
          },
          "stop_sequence": null,
          "usage": {"input_tokens": 1, "output_tokens": 1}
        }
        """
            .trimIndent()
}
