package com.anthropic.helpers

import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaFallbackBlockParam
import com.anthropic.models.beta.messages.BetaFallbackInfoParam
import com.anthropic.models.beta.messages.BetaFallbackParam
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaThinkingBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.IOException
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Tests for [BetaRefusalFallbackInterceptor]'s streaming splice.
 *
 * `stream-a-refusal.sse` and `stream-b-fallback.sse` are wire-shaped synthetic captures — the
 * primary refuses after a thinking + partial-text block and mints a credit token; the fallback then
 * completes the message. `stream-a-toolrefusal.sse` is wire-shaped synthetic `web_search` wire
 * whose refusal terminal (message_delta + token) lands mid-tool-loop, after a partial text block.
 */
internal class BetaRefusalFallbackInterceptorStreamingTest {

    companion object {
        private const val FALLBACK_MODEL = "fallback-model"
        private const val SECOND_MODEL = "second-model"
        private const val FABLE_MODEL = "claude-fable-5"

        private val STREAM_A = fixture("stream-a-refusal.sse")
        private val STREAM_B = fixture("stream-b-fallback.sse")
        private val STREAM_A_TOOL = fixture("stream-a-toolrefusal.sse")

        private fun fixture(name: String): String =
            BetaRefusalFallbackInterceptorStreamingTest::class
                .java
                .classLoader
                .getResourceAsStream("helpers/fable-fallback/$name")!!
                .readBytes()
                .decodeToString()
    }

    private val jsonMapper = jsonMapper()

    // --- happy path ---

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun splicesTheFallbackOntoTheRefusedStream(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL), async)

        // A's thinking + text are forwarded, a fallback boundary block is emitted at the next
        // monotonic index, then B's blocks continue after it.
        val starts = events.filter { it.path("type").asText() == "content_block_start" }
        assertThat(starts.map { it.path("index").asInt() to blockType(it) })
            .containsExactly(0 to "thinking", 1 to "text", 2 to "fallback", 3 to "text")

        // The fallback block carries the from/to model transition.
        val fallback = starts.first { blockType(it) == "fallback" }.path("content_block")
        assertThat(fallback.path("from").path("model").asText()).isEqualTo(FABLE_MODEL)
        assertThat(fallback.path("to").path("model").asText()).isEqualTo(FALLBACK_MODEL)

        // Exactly one message_start (A's) and one message_stop reach the client — B's
        // message_start is suppressed.
        assertThat(events.count { it.path("type").asText() == "message_start" }).isEqualTo(1)
        assertThat(events.count { it.path("type").asText() == "message_stop" }).isEqualTo(1)
        assertThat(events.count { it.path("type").asText() == "message_delta" }).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun rewritesUsageIterationsToTheChainShape(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL), async)

        val messageDelta = events.first { it.path("type").asText() == "message_delta" }
        assertThat(messageDelta.path("delta").path("stop_reason").asText()).isEqualTo("end_turn")
        assertThat(iterations(messageDelta))
            .containsExactly("message" to FABLE_MODEL, "fallback_message" to FALLBACK_MODEL)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun buildsTheHopRequestAsAContinuation(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))

        consume(httpClient.intercepted(FALLBACK_MODEL), async)

        assertThat(httpClient.requests).hasSize(2)
        val bodyA = httpClient.jsonBodies[0]
        val bodyB = httpClient.jsonBodies[1]

        // Model swapped to the fallback, credit token from A's stop_details set.
        assertThat(bodyB.path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(bodyB.path("fallback_credit_token").asText()).isNotEmpty()

        // max_tokens untouched (any render-shaping change would 400).
        assertThat(bodyB.path("max_tokens").asInt()).isEqualTo(1024)

        // Original turn preserved; one assistant turn appended carrying the [thinking, text]
        // partial output as-is — the prefill claim authorizes it verbatim, so no client-side
        // filtering or trimming.
        assertThat(bodyB.path("messages").size()).isEqualTo(2)
        assertThat(bodyB.path("messages").path(0)).isEqualTo(bodyA.path("messages").path(0))
        val appended = bodyB.path("messages").path(1)
        assertThat(appended.path("role").asText()).isEqualTo("assistant")
        assertThat(appended.path("content").map { it.path("type").asText() })
            .containsExactly("thinking", "text")
        assertThat(appended.path("content").path(0).path("signature").asText()).isNotEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun appendsTheFallbackCreditBetaToTheOriginalAndHopRequests(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))

        consume(httpClient.intercepted(FALLBACK_MODEL), async)

        assertThat(httpClient.requests).hasSize(2)
        httpClient.requests.forEach {
            assertThat(it.headers.values("anthropic-beta"))
                .containsExactly("fallback-credit-2026-06-01")
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun trimsReplayedFallbackTurnsOnTheStreamingPath(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))
        // A turn served by a fallback on an earlier request, echoed back into this stream's
        // history.
        val replayedTurn =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.ASSISTANT)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofThinking(
                            BetaThinkingBlockParam.builder()
                                .thinking("refused attempt")
                                .signature("sig")
                                .build()
                        ),
                        BetaContentBlockParam.ofFallback(
                            BetaFallbackBlockParam.builder()
                                .from(
                                    BetaFallbackInfoParam.builder().model("primary-model").build()
                                )
                                .to(BetaFallbackInfoParam.builder().model(FALLBACK_MODEL).build())
                                .build()
                        ),
                        BetaContentBlockParam.ofText(
                            BetaTextBlockParam.builder().text("answer").build()
                        ),
                    )
                )
                .build()
        val body =
            jsonMapper.valueToTree<ObjectNode>(
                MessageCreateParams.Body.builder()
                    .model("primary-model")
                    .maxTokens(1024)
                    .addUserMessage("hi")
                    .addMessage(replayedTurn)
                    .addUserMessage("continue")
                    .putAdditionalProperty("stream", JsonValue.from(true))
                    .build()
            )
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://api.example.com")
                .addPathSegments("v1", "messages")
                .putQueryParam("beta", "true")
                .body(HttpRequestBody.ofJson(body))
                .build()

        val response = httpClient.intercepted(FALLBACK_MODEL).execute(request, withState(), async)
        response.body().readBytes() // drive the splice so the hop request goes out

        // Both the initial request and the hop continuation built from it carry the trimmed
        // turn: the seam and the refused attempt's thinking are gone, the served answer stays.
        assertThat(httpClient.requests).hasSize(2)
        httpClient.jsonBodies.forEach { sent ->
            val turn = sent.path("messages").path(1)
            assertThat(turn.path("role").asText()).isEqualTo("assistant")
            assertThat(turn.path("content").map { it.path("type").asText() })
                .containsExactly("text")
        }
    }

    // --- edge cases ---

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun anImmediateRefusalWithACreditTokenStillFallsBack(async: Boolean) {
        // A refusal before any content block is just a chain with an empty partial: the hop
        // resends the original body with the token attached and the boundary block leads the
        // message.
        val immediateRefusal =
            messageStart() + refusalDelta("tok_abc") + event("""{"type":"message_stop"}""")
        val httpClient = FakeHttpClient(sse(immediateRefusal), sse(STREAM_B))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL), async)

        assertThat(httpClient.requests).hasSize(2)
        val bodyB = httpClient.jsonBodies[1]
        assertThat(bodyB.path("fallback_credit_token").asText()).isEqualTo("tok_abc")
        assertThat(bodyB.path("messages")).isEqualTo(httpClient.jsonBodies[0].path("messages"))

        // The boundary block leads, then the fallback's content follows at shifted indices.
        val starts = events.filter { it.path("type").asText() == "content_block_start" }
        assertThat(starts.map { it.path("index").asInt() to blockType(it) })
            .containsExactly(0 to "fallback", 1 to "text")
        assertThat(
                events
                    .first { it.path("type").asText() == "message_delta" }
                    .path("delta")
                    .path("stop_reason")
                    .asText()
            )
            .isEqualTo("end_turn")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aRefusalWithoutAPrefillClaimSendsTheSameBody(async: Boolean) {
        // fallback_has_prefill_claim: false — the partial output may not be resent, so the
        // interceptor omits the prefill and resends the original body with just the token
        // attached.
        val noClaim =
            messageStart() +
                event(
                    """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}"""
                ) +
                event(
                    """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Partial. "}}"""
                ) +
                event("""{"type":"content_block_stop","index":0}""") +
                refusalDelta("tok_abc", hasPrefillClaim = false) +
                event("""{"type":"message_stop"}""")
        val httpClient = FakeHttpClient(sse(noClaim), sse(STREAM_B))

        consume(httpClient.intercepted(FALLBACK_MODEL), async)

        val bodyB = httpClient.jsonBodies[1]
        assertThat(bodyB.path("fallback_credit_token").asText()).isEqualTo("tok_abc")
        // No appended assistant turn — identical messages (same-body form).
        assertThat(bodyB.path("messages")).isEqualTo(httpClient.jsonBodies[0].path("messages"))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun aRefusalWithoutACreditTokenPassesThroughAndWarnsOnce(async: Boolean) {
        val noToken =
            STREAM_A.replace(
                Regex("\"fallback_credit_token\":\"[^\"]*\""),
                "\"fallback_credit_token\":null",
            )
        val httpClient = FakeHttpClient(sse(noToken), sse(noToken))
        val interceptedClient = httpClient.intercepted(FALLBACK_MODEL)

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(interceptedClient, async)
            consume(interceptedClient, async)
        }

        // Only the original requests were made — no fallback — and the missing-token warning can
        // be steady-state (e.g. the beta isn't enabled), so it's rate-limited to once per
        // interceptor.
        assertThat(httpClient.requests).hasSize(2)
        assertThat(stderr.lines().filter { it.contains("no fallback_credit_token") }).hasSize(1)

        // A passes through unchanged, ending in its own refusal (no fallback block).
        assertThat(events!!.none { blockType(it) == "fallback" }).isTrue()
        assertThat(
                events!!
                    .first { it.path("type").asText() == "message_delta" }
                    .path("delta")
                    .path("stop_reason")
                    .asText()
            )
            .isEqualTo("refusal")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun a400OnThePrefillFormRetriesTheSameHopWithoutThePartial(async: Boolean) {
        val httpClient =
            FakeHttpClient(sse(STREAM_A), json(400, INVALID_REQUEST_ERROR), sse(STREAM_B))

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(httpClient.intercepted(FALLBACK_MODEL), async)
        }

        // Attempt 1 appends A's partial; the 400 drops it and attempt 2 redeems the same token
        // against the unchanged body.
        assertThat(httpClient.requests).hasSize(3)
        assertThat(stderr).contains("retrying without it")
        val body1 = httpClient.jsonBodies[1]
        val body2 = httpClient.jsonBodies[2]
        assertThat(body1.path("messages").size()).isEqualTo(2)
        assertThat(body2.path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(body2.path("fallback_credit_token"))
            .isEqualTo(body1.path("fallback_credit_token"))
        assertThat(body2.path("messages")).isEqualTo(httpClient.jsonBodies[0].path("messages"))

        // The recovered hop is not a failure: one boundary, a normal completion.
        assertThat(stderr).doesNotContain("fallback request to")
        assertThat(events!!.count { blockType(it) == "fallback" }).isEqualTo(1)
        assertThat(
                events!!
                    .first { it.path("type").asText() == "message_delta" }
                    .path("delta")
                    .path("stop_reason")
                    .asText()
            )
            .isEqualTo("end_turn")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun aFailedFallbackRequestClosesWithTheHeldRefusal(async: Boolean) {
        // Give A's refusal a real category/explanation, plus a field the splicer doesn't model
        // (context_management), to prove they thread through.
        val refusedA =
            STREAM_A.replace(
                    "\"category\":null,\"explanation\":null",
                    "\"category\":\"safety\",\"explanation\":\"declined to help\"",
                )
                .replace(
                    "{\"type\":\"message_delta\",",
                    "{\"type\":\"message_delta\",\"context_management\":{\"applied_edits\":[]},",
                )
        val httpClient =
            FakeHttpClient(
                sse(refusedA),
                json(400, INVALID_REQUEST_ERROR),
                json(400, INVALID_REQUEST_ERROR),
            )

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(httpClient.intercepted(FALLBACK_MODEL), async)
        }

        // The prefill form 400s, the same-body retry 400s too — only then does the hop count as
        // failed.
        assertThat(httpClient.requests).hasSize(3)
        assertThat(stderr).contains("fallback request to $FALLBACK_MODEL failed: HTTP 400")

        // The fallback boundary block was already emitted, then the held refusal message_delta +
        // message_stop close the stream.
        assertThat(events!!.any { blockType(it) == "fallback" }).isTrue()
        val messageDelta = events!!.first { it.path("type").asText() == "message_delta" }
        assertThat(messageDelta.path("delta").path("stop_reason").asText()).isEqualTo("refusal")
        assertThat(events!!.last().path("type").asText()).isEqualTo("message_stop")

        // The held refusal is surfaced verbatim — category/explanation and the still-unredeemed
        // credit token — with recommended_model pointing at the last hop we tried.
        val stopDetails = messageDelta.path("delta").path("stop_details")
        assertThat(stopDetails.path("category").asText()).isEqualTo("safety")
        assertThat(stopDetails.path("explanation").asText()).isEqualTo("declined to help")
        assertThat(stopDetails.path("recommended_model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(stopDetails.path("fallback_credit_token").asText()).isNotEmpty()

        // Fields the splicer doesn't model survive the degraded close verbatim.
        assertThat(messageDelta.path("context_management").path("applied_edits").isArray).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun aFallbackRequestThatThrowsClosesWithTheHeldRefusal(async: Boolean) {
        val connectionReset = IOException("connection reset")
        val httpClient = FakeHttpClient(sse(STREAM_A), Failure(connectionReset))

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(httpClient.intercepted(FALLBACK_MODEL), async)
        }

        // The original error is reported, in both the sync path (thrown directly) and the async
        // path (unwrapped from the join's CompletionException).
        assertThat(stderr)
            .contains("fallback request to $FALLBACK_MODEL failed: " + connectionReset)

        // The stream still closes cleanly: boundary block, then the held refusal message_delta +
        // message_stop — not a hard stream error.
        val messageDelta = events!!.first { it.path("type").asText() == "message_delta" }
        assertThat(messageDelta.path("delta").path("stop_reason").asText()).isEqualTo("refusal")
        assertThat(events!!.last().path("type").asText()).isEqualTo("message_stop")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun closingBeforeReadingReleasesTheInitialResponse(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A))

        val response =
            httpClient.intercepted(FALLBACK_MODEL).execute(streamingRequest(), withState(), async)
        response.close()

        assertThat(httpClient.issued.single().closed.get()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun closingDuringAnInFlightHopReleasesItsResponseWhenItResolves(async: Boolean) {
        val hopStarted = CountDownLatch(1)
        val closeDone = CountDownLatch(1)
        val httpClient =
            FakeHttpClient(
                sse(STREAM_A),
                {
                    hopStarted.countDown()
                    closeDone.await(5, TimeUnit.SECONDS)
                    sse(STREAM_B)
                },
            )
        val response =
            httpClient.intercepted(FALLBACK_MODEL).execute(streamingRequest(), withState(), async)

        // Close while the reader thread is blocked inside the hop request; the hop's response
        // must still be released once the request resolves.
        val reader = Thread { response.body().readBytes() }
        reader.start()
        assertThat(hopStarted.await(5, TimeUnit.SECONDS)).isTrue()
        response.close()
        closeDone.countDown()
        reader.join(5_000)

        assertThat(reader.isAlive).isFalse()
        assertThat(httpClient.issued).hasSize(2)
        assertThat(httpClient.issued[1].closed.get()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun warnsOnceOnAContentDeltaTypeItCannotAccumulate(async: Boolean) {
        // An unknown delta type can't be folded into the accumulated block, so the prefill may
        // not match the wire output — flagged once per type, while the delta itself still flows
        // to the client.
        val unknownDelta =
            messageStart() +
                event(
                    """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}"""
                ) +
                event(
                    """{"type":"content_block_delta","index":0,"delta":{"type":"mystery_delta","mystery":"?"}}"""
                ) +
                event(
                    """{"type":"content_block_delta","index":0,"delta":{"type":"mystery_delta","mystery":"!"}}"""
                ) +
                event("""{"type":"content_block_stop","index":0}""") +
                refusalDelta("tok_abc") +
                event("""{"type":"message_stop"}""")
        val httpClient = FakeHttpClient(sse(unknownDelta), sse(STREAM_B))

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(httpClient.intercepted(FALLBACK_MODEL), async)
        }

        assertThat(stderr.lines().filter { it.contains("mystery_delta") }).hasSize(1)
        assertThat(events!!.count { it.path("delta").path("type").asText() == "mystery_delta" })
            .isEqualTo(2)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun passthroughPreservesSseFieldsTheDecoderDoesNotModel(async: Boolean) {
        val wire =
            "retry: 1500\n" +
                "event: message_start\n" +
                "data: " +
                """{"type":"message_start","message":{"id":"msg_a","type":"message","role":"assistant","model":"$FABLE_MODEL","content":[],"stop_reason":null,"stop_sequence":null,"usage":{"input_tokens":10,"output_tokens":1}}}""" +
                "\n\n" +
                ": keep-alive\nid: 42\nevent: message_delta\n" +
                "data: " +
                """{"type":"message_delta","delta":{"stop_reason":"end_turn","stop_sequence":null},"usage":{"output_tokens":3}}""" +
                "\n\n" +
                "event: message_stop\ndata: " +
                """{"type":"message_stop"}""" +
                "\n\n"
        val httpClient = FakeHttpClient(sse(wire))

        val response =
            httpClient.intercepted(FALLBACK_MODEL).execute(streamingRequest(), withState(), async)

        assertThat(response.body().readBytes().decodeToString()).isEqualTo(wire)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aNonRefusalStreamIsPassedThroughUntouched(async: Boolean) {
        val normal =
            messageStart() +
                event(
                    """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}"""
                ) +
                event(
                    """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Sure!"}}"""
                ) +
                event("""{"type":"content_block_stop","index":0}""") +
                event(
                    """{"type":"message_delta","delta":{"stop_reason":"end_turn","stop_sequence":null},"usage":{"output_tokens":3}}"""
                ) +
                event("""{"type":"message_stop"}""")
        val httpClient = FakeHttpClient(sse(normal))

        val response =
            httpClient.intercepted(FALLBACK_MODEL).execute(streamingRequest(), withState(), async)

        assertThat(response.body().readBytes().decodeToString()).isEqualTo(normal)
        assertThat(httpClient.requests).hasSize(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun hopEventsAreForwardedAsTheyArrive(async: Boolean) {
        // The hop's body arrives through a pipe so the test controls pacing: a delta written
        // while the hop stream is still open must come out of the spliced stream before any
        // further bytes (or the terminal events) are written. A splice that buffers the hop
        // would time out here.
        val hopBody = PipedOutputStream()
        val immediateRefusal =
            messageStart() + refusalDelta("tok_abc") + event("""{"type":"message_stop"}""")
        val httpClient =
            FakeHttpClient(sse(immediateRefusal), Streaming(PipedInputStream(hopBody, 64 * 1024)))

        val response =
            httpClient.intercepted(FALLBACK_MODEL).execute(streamingRequest(), withState(), async)
        val received = StringBuilder()
        val consumer = Thread {
            val body = response.body()
            val buffer = ByteArray(4096)
            while (true) {
                val count = body.read(buffer)
                if (count == -1) {
                    break
                }
                synchronized(received) { received.append(String(buffer, 0, count)) }
            }
        }
        consumer.start()

        fun awaitReceived(text: String) {
            val deadline = System.currentTimeMillis() + 5_000
            while (synchronized(received) { !received.contains(text) }) {
                if (System.currentTimeMillis() > deadline) {
                    throw AssertionError("Timed out waiting for $text in: $received")
                }
                Thread.sleep(10)
            }
        }

        hopBody.write(
            (messageStart() +
                    event(
                        """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}"""
                    ) +
                    event(
                        """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"First chunk"}}"""
                    ))
                .toByteArray()
        )
        hopBody.flush()
        awaitReceived("First chunk")

        hopBody.write(
            (event("""{"type":"content_block_stop","index":0}""") +
                    event(
                        """{"type":"message_delta","delta":{"stop_reason":"end_turn","stop_sequence":null},"usage":{"output_tokens":3}}"""
                    ) +
                    event("""{"type":"message_stop"}"""))
                .toByteArray()
        )
        hopBody.close()
        consumer.join(5_000)
        awaitReceived("message_stop")
    }

    // --- fallbackState pinning ---

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun pinsTheStateToTheHopThatServed(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))
        val fallbackState = BetaFallbackState.create()

        consume(
            httpClient.intercepted(FALLBACK_MODEL),
            async,
            RequestOptions.builder().fallbackState(fallbackState).build(),
        )

        assertThat(fallbackState.index()).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aPinnedStateStartsOnThePinnedEntryAndChainsPastIt(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B))
        val fallbackState = BetaFallbackState.create()
        fallbackState.index(0)

        consume(
            httpClient.intercepted(FALLBACK_MODEL, SECOND_MODEL),
            async,
            RequestOptions.builder().fallbackState(fallbackState).build(),
        )

        assertThat(httpClient.requests).hasSize(2)
        // The initial request already carries the pinned entry's params; the mid-stream refusal
        // then chains to the entry after the pin.
        assertThat(httpClient.jsonBodies[0].path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(httpClient.jsonBodies[1].path("model").asText()).isEqualTo(SECOND_MODEL)
        assertThat(fallbackState.index()).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aFullyPinnedChainPassesTheStreamThroughUnwrapped(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A))
        val fallbackState = BetaFallbackState.create()
        fallbackState.index(0)

        val response =
            httpClient
                .intercepted(FALLBACK_MODEL)
                .execute(
                    streamingRequest(),
                    RequestOptions.builder().fallbackState(fallbackState).build(),
                    async,
                )

        // With nothing to hop to, the response isn't even wrapped — no per-event decode/re-encode
        // overhead, and no error: this is the steady state of a fully-pinned chain.
        assertThat(httpClient.requests).hasSize(1)
        assertThat(response.body().readBytes().decodeToString()).isEqualTo(STREAM_A)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun warnsOnceWhenFallingBackWithoutState(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(STREAM_B), sse(STREAM_A), sse(STREAM_B))
        val interceptedClient = httpClient.intercepted(FALLBACK_MODEL)

        val stderr = captureStderr {
            consume(interceptedClient, async, RequestOptions.none())
            consume(interceptedClient, async, RequestOptions.none())
        }

        assertThat(httpClient.requests).hasSize(4)
        assertThat(stderr.lines().filter { it.contains("fallbackState") }).hasSize(1)
    }

    // --- fallback chain ---

    // A fallback hop that contributes one text block, then refuses with a fresh token.
    private val hopRefusal =
        messageStart() +
            event(
                """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}"""
            ) +
            event(
                """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Partial from B. "}}"""
            ) +
            event("""{"type":"content_block_stop","index":0}""") +
            refusalDelta("tok_b") +
            event("""{"type":"message_stop"}""")

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aRefusedHopSplicesItsPartialAndChainsToTheNextEntry(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(hopRefusal), sse(STREAM_B))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL, SECOND_MODEL), async)

        assertThat(httpClient.requests).hasSize(3)

        // Hop 1 redeems A's token; hop 2 redeems the fresh token minted by hop 1's refusal, with
        // hop 1's partial extending the same turn as-is.
        val body1 = httpClient.jsonBodies[1]
        val body2 = httpClient.jsonBodies[2]
        assertThat(body1.path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(body2.path("model").asText()).isEqualTo(SECOND_MODEL)
        assertThat(body2.path("fallback_credit_token").asText()).isEqualTo("tok_b")
        assertThat(body2.path("fallback_credit_token"))
            .isNotEqualTo(body1.path("fallback_credit_token"))
        val content1 = body1.path("messages").path(1).path("content")
        val content2 = body2.path("messages").path(1).path("content")
        assertThat(content2.size()).isEqualTo(content1.size() + 1)
        assertThat(content2.path(content2.size() - 1).path("text").asText())
            .isEqualTo("Partial from B. ")

        // One continuous message: A's blocks, boundary, hop 1's partial, boundary, hop 2's blocks
        // — indices stay monotonic across all three streams.
        val starts = events.filter { it.path("type").asText() == "content_block_start" }
        assertThat(starts.map { it.path("index").asInt() to blockType(it) })
            .containsExactly(
                0 to "thinking",
                1 to "text",
                2 to "fallback",
                3 to "text",
                4 to "fallback",
                5 to "text",
            )
        val boundaries =
            starts.filter { blockType(it) == "fallback" }.map { it.path("content_block") }
        assertThat(boundaries[0].path("from").path("model").asText()).isEqualTo(FABLE_MODEL)
        assertThat(boundaries[0].path("to").path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(boundaries[1].path("from").path("model").asText()).isEqualTo(FALLBACK_MODEL)
        assertThat(boundaries[1].path("to").path("model").asText()).isEqualTo(SECOND_MODEL)

        // Hop 1's refusal delta is suppressed; the terminal delta carries every hop.
        val deltas = events.filter { it.path("type").asText() == "message_delta" }
        assertThat(deltas).hasSize(1)
        assertThat(deltas[0].path("delta").path("stop_reason").asText()).isEqualTo("end_turn")
        assertThat(iterations(deltas[0]))
            .containsExactly(
                "message" to FABLE_MODEL,
                "message" to FALLBACK_MODEL,
                "fallback_message" to SECOND_MODEL,
            )
        assertThat(events.count { it.path("type").asText() == "message_stop" }).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun anHttpFailedHopIsSkippedAndTheUnredeemedTokenCarriesToTheNextEntry(async: Boolean) {
        val httpClient =
            FakeHttpClient(
                sse(STREAM_A),
                json(
                    529,
                    """{"type":"error","error":{"type":"overloaded_error","message":"later"}}""",
                ),
                sse(STREAM_B),
            )

        var events: List<ObjectNode>? = null
        val stderr = captureStderr {
            events = consume(httpClient.intercepted(FALLBACK_MODEL, SECOND_MODEL), async)
        }

        assertThat(stderr).contains("fallback request to $FALLBACK_MODEL failed: HTTP 529")
        assertThat(httpClient.requests).hasSize(3)

        // Same token and continuation — the failed hop never redeemed them.
        val body1 = httpClient.jsonBodies[1]
        val body2 = httpClient.jsonBodies[2]
        assertThat(body2.path("model").asText()).isEqualTo(SECOND_MODEL)
        assertThat(body2.path("fallback_credit_token"))
            .isEqualTo(body1.path("fallback_credit_token"))
        assertThat(body2.path("messages")).isEqualTo(body1.path("messages"))

        // Both boundaries are from A — the failed hop contributed no output.
        val boundaries =
            events!!
                .filter { blockType(it) == "fallback" }
                .map {
                    it.path("content_block").path("from").path("model").asText() to
                        it.path("content_block").path("to").path("model").asText()
                }
        assertThat(boundaries)
            .containsExactly(FABLE_MODEL to FALLBACK_MODEL, FABLE_MODEL to SECOND_MODEL)

        // The failed hop is absent from iterations (no usage came back).
        val delta = events!!.first { it.path("type").asText() == "message_delta" }
        assertThat(delta.path("delta").path("stop_reason").asText()).isEqualTo("end_turn")
        assertThat(iterations(delta))
            .containsExactly("message" to FABLE_MODEL, "fallback_message" to SECOND_MODEL)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aTerminalRefusalWithNoEntriesLeftIsEmittedWithTheFullIterationChain(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A), sse(hopRefusal))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL), async)

        assertThat(httpClient.requests).hasSize(2)
        val delta = events.first { it.path("type").asText() == "message_delta" }
        assertThat(delta.path("delta").path("stop_reason").asText()).isEqualTo("refusal")
        // The fresh token reaches the client for a manual retry.
        assertThat(delta.path("delta").path("stop_details").path("fallback_credit_token").asText())
            .isEqualTo("tok_b")
        assertThat(iterations(delta))
            .containsExactly("message" to FABLE_MODEL, "fallback_message" to FALLBACK_MODEL)
    }

    // --- tool-use refusals ---

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun aToolUseRefusalContinuationReassemblesToolInputsFromTheirDeltas(async: Boolean) {
        val httpClient = FakeHttpClient(sse(STREAM_A_TOOL), sse(STREAM_B))

        val events = consume(httpClient.intercepted(FALLBACK_MODEL), async)

        val starts = events.filter { it.path("type").asText() == "content_block_start" }
        assertThat(starts.map { it.path("index").asInt() to blockType(it) })
            .containsExactly(
                0 to "server_tool_use",
                1 to "web_search_tool_result",
                2 to "text",
                3 to "fallback",
                4 to "text",
            )

        val appended = httpClient.jsonBodies[1].path("messages").path(1)
        assertThat(appended.path("content").map { it.path("type").asText() })
            .containsExactly("server_tool_use", "web_search_tool_result", "text")
        // The tool input is the parsed input_json_delta payload, not the empty `{}` from
        // content_block_start.
        val toolUse = appended.path("content").path(0)
        assertThat(toolUse.path("id").asText()).isEqualTo("srvtoolu_fixture_a_0001")
        assertThat(toolUse.path("name").asText()).isEqualTo("web_search")
        assertThat(toolUse.path("input").path("query").asText())
            .isEqualTo("solar eclipse viewing safety news 2026")
        // The result block keeps its pairing id.
        assertThat(appended.path("content").path(1).path("tool_use_id").asText())
            .isEqualTo("srvtoolu_fixture_a_0001")
    }

    // --- helpers ---

    private val INVALID_REQUEST_ERROR =
        """{"type":"error","error":{"type":"invalid_request_error","message":"bad request"}}"""

    private fun messageStart(): String =
        event(
            """{"type":"message_start","message":{"id":"msg_a","type":"message","role":"assistant","model":"$FABLE_MODEL","content":[],"stop_reason":null,"stop_sequence":null,"usage":{"input_tokens":12,"output_tokens":1}}}"""
        )

    private fun refusalDelta(token: String?, hasPrefillClaim: Boolean = true): String {
        val tokenJson = token?.let { "\"$it\"" } ?: "null"
        return event(
            """{"type":"message_delta","delta":{"stop_reason":"refusal","stop_sequence":null,"stop_details":{"type":"refusal","category":null,"explanation":null,"fallback_credit_token":$tokenJson,"fallback_has_prefill_claim":$hasPrefillClaim}},"usage":{"output_tokens":20}}"""
        )
    }

    /** Serializes one event payload as an SSE frame (its `type` is the event name). */
    private fun event(data: String): String =
        "event: ${jsonMapper.readTree(data).path("type").asText()}\ndata: $data\n\n"

    private fun blockType(contentBlockStart: ObjectNode): String =
        contentBlockStart.path("content_block").path("type").asText()

    private fun iterations(messageDelta: ObjectNode): List<Pair<String, String>> =
        messageDelta.path("usage").path("iterations").map {
            it.path("type").asText() to it.path("model").asText()
        }

    private fun FakeHttpClient.intercepted(vararg fallbackModels: String): HttpClient =
        BetaRefusalFallbackInterceptor.builder()
            .fallbacks(fallbackModels.map { BetaFallbackParam.builder().model(it).build() })
            .build()
            .intercept(this)

    /**
     * A fresh-state request option — the default for tests, so they don't pollute the stderr of
     * concurrently running tests asserting on the missing-state warning.
     */
    private fun withState(): RequestOptions =
        RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()

    /** Executes a streaming request and decodes the spliced SSE events from the response. */
    private fun consume(
        httpClient: HttpClient,
        async: Boolean,
        requestOptions: RequestOptions = withState(),
    ): List<ObjectNode> {
        val response = httpClient.execute(streamingRequest(), requestOptions, async)
        return response
            .body()
            .readBytes()
            .decodeToString()
            .split("\n\n")
            .filter { it.isNotBlank() }
            .mapNotNull { chunk ->
                val data =
                    chunk
                        .lines()
                        .filter { it.startsWith("data:") }
                        .joinToString("\n") { it.removePrefix("data:").trim() }
                if (data.isEmpty()) null else jsonMapper.readTree(data) as? ObjectNode
            }
    }

    private fun streamingRequest(): HttpRequest {
        val body =
            MessageCreateParams.Body.builder()
                .model("primary-model")
                .maxTokens(1024)
                .addUserMessage("hi")
                .putAdditionalProperty("stream", JsonValue.from(true))
                .build()
        return HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://api.example.com")
            .addPathSegments("v1", "messages")
            .putQueryParam("beta", "true")
            .body(HttpRequestBody.ofJson(body))
            .build()
    }

    private fun sse(body: String): Pair<Int, String> = 200 to body

    private fun json(status: Int, body: String): Pair<Int, String> = status to body
}
