package com.anthropic.helpers

import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.rawSseHandler
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.Interceptor
import com.anthropic.core.http.SseMessage
import com.anthropic.core.jsonMapper
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaAdvisorToolResultBlockParam
import com.anthropic.models.beta.messages.BetaBashCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.messages.BetaCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaCompactionBlockParam
import com.anthropic.models.beta.messages.BetaContainerUploadBlockParam
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaFallbackBlock
import com.anthropic.models.beta.messages.BetaFallbackBlockParam
import com.anthropic.models.beta.messages.BetaFallbackInfo
import com.anthropic.models.beta.messages.BetaFallbackParam
import com.anthropic.models.beta.messages.BetaFallbackRefusalTrigger
import com.anthropic.models.beta.messages.BetaImageBlockParam
import com.anthropic.models.beta.messages.BetaMcpToolUseBlockParam
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageIterationUsage
import com.anthropic.models.beta.messages.BetaMidConversationSystemBlockParam
import com.anthropic.models.beta.messages.BetaRawContentBlockStartEvent
import com.anthropic.models.beta.messages.BetaRawContentBlockStopEvent
import com.anthropic.models.beta.messages.BetaRawMessageDeltaEvent
import com.anthropic.models.beta.messages.BetaRawMessageStartEvent
import com.anthropic.models.beta.messages.BetaRawMessageStopEvent
import com.anthropic.models.beta.messages.BetaRedactedThinkingBlockParam
import com.anthropic.models.beta.messages.BetaRefusalStopDetails
import com.anthropic.models.beta.messages.BetaRequestDocumentBlock
import com.anthropic.models.beta.messages.BetaRequestMcpToolResultBlockParam
import com.anthropic.models.beta.messages.BetaSearchResultBlockParam
import com.anthropic.models.beta.messages.BetaServerToolUseBlockParam
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTextEditorCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaThinkingBlockParam
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolSearchToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.beta.messages.BetaWebFetchToolResultBlockParam
import com.anthropic.models.beta.messages.BetaWebSearchToolResultBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import kotlin.jvm.optionals.getOrNull
import kotlin.math.max
import kotlin.math.min
import kotlin.streams.asSequence

/**
 * An [Interceptor] that retries refused beta `/v1/messages` requests down a fallback chain.
 *
 * Only requests made through the beta API surface (`client.beta().messages()`) are handled;
 * non-beta `client.messages()` requests pass through untouched.
 *
 * When a non-streaming response comes back with `stop_reason: "refusal"`, the request is retried
 * with each entry of the fallback chain applied over the original params — passing along the
 * refusal's `fallback_credit_token`, which refunds the retry's cache-miss cost — until a model
 * accepts or the chain is exhausted. A message served by a fallback retry mirrors the server-side
 * stitched envelope: one `fallback` content block per model boundary (`from`: the model that
 * refused, as the caller spelled it; `to`: the next entry's model) is prepended to the serving
 * hop's content.
 *
 * When a streaming response ends in `stop_reason: "refusal"`, a second request is issued to the
 * fallback model — carrying the refused model's partial output as a trailing assistant prefill when
 * the refusal grants one (`fallback_has_prefill_claim`), plus the refusal's `fallback_credit_token`
 * — and the fallback's events are spliced onto the still-open stream, so the client sees one
 * continuous message in the server-side `fallbacks` wire shape: a `fallback` content block at each
 * model boundary, monotonic block indices, and per-hop `usage.iterations` on the final
 * `message_delta`. Only `model` is honored from each entry on this path: the credit token is
 * redeemable only against the refused request's body, so the other per-entry overrides
 * (`max_tokens`, `thinking`, ...) would be rejected.
 *
 * The fallback-credit beta the credit tokens require is sent by default on every request the
 * interceptor handles — the original request included, since refusals only carry a
 * `fallback_credit_token` when the beta is enabled; the [Builder.betas] option controls this.
 *
 * In both modes a fallback that itself refuses with a fresh credit token continues down the chain.
 * A streaming fallback whose prefill the server rejects (HTTP 400) is retried once without it; a
 * streaming fallback whose request fails outright is skipped — its token was never redeemed, so it
 * carries to the next entry. A streaming refusal with no `fallback_credit_token` to retry is
 * surfaced to the client and reported once per interceptor with a warning on [System.err]; one that
 * exhausts the chain is surfaced silently — its terminal `message_delta` already reports every hop.
 *
 * To keep later requests on the model that accepted, pass a [BetaFallbackState] via the
 * [RequestOptions.Builder.fallbackState] request option; requests sharing that state start directly
 * at the pinned fallback. Reuse one state across whatever scope the pin should apply to — typically
 * a conversation.
 *
 * Example:
 * ```java
 * AnthropicClient client = AnthropicOkHttpClient.builder()
 *     .fromEnv()
 *     .addInterceptor(BetaRefusalFallbackInterceptor.builder()
 *         .addFallback(Model.CLAUDE_OPUS_4_8)
 *         .build())
 *     .build();
 *
 * BetaFallbackState fallbackState = BetaFallbackState.create();
 * BetaMessage message = client.beta().messages().create(
 *     params, RequestOptions.builder().fallbackState(fallbackState).build());
 * ```
 */
class BetaRefusalFallbackInterceptor
private constructor(
    private val fallbacks: List<BetaFallbackParam>,
    private val betas: List<AnthropicBeta>,
) : Interceptor {

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Betas sent by default; override with [Builder.betas]. */
        private val DEFAULT_BETAS = listOf(AnthropicBeta.FALLBACK_CREDIT_2026_06_01)

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaRefusalFallbackInterceptor].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRefusalFallbackInterceptor]. */
    class Builder internal constructor() {

        private var fallbacks: MutableList<BetaFallbackParam> = mutableListOf()
        private var betas: MutableList<AnthropicBeta> = DEFAULT_BETAS.toMutableList()

        @JvmSynthetic
        internal fun from(interceptor: BetaRefusalFallbackInterceptor) = apply {
            fallbacks = interceptor.fallbacks.toMutableList()
            betas = interceptor.betas.toMutableList()
        }

        /**
         * Sets the fallbacks that refused requests are retried through, in order.
         *
         * An empty list disables the interceptor.
         */
        fun fallbacks(fallbacks: List<BetaFallbackParam>) = apply {
            this.fallbacks = fallbacks.toMutableList()
        }

        /** Adds a single [BetaFallbackParam] to [fallbacks]. */
        fun addFallback(fallback: BetaFallbackParam) = apply { fallbacks.add(fallback) }

        /**
         * Adds a single [BetaFallbackParam] with the given model, and no overrides, to [fallbacks].
         */
        fun addFallback(model: Model) =
            addFallback(BetaFallbackParam.builder().model(model).build())

        /**
         * Sets the betas added to the `anthropic-beta` header of every `/v1/messages` request this
         * interceptor handles — the original request included, since refusals only carry a
         * `fallback_credit_token` when the beta is enabled.
         *
         * Defaults to [AnthropicBeta.FALLBACK_CREDIT_2026_06_01]; pass an empty list to send none.
         */
        fun betas(betas: List<AnthropicBeta>) = apply { this.betas = betas.toMutableList() }

        /** Adds a single [AnthropicBeta] to [betas]. */
        fun addBeta(beta: AnthropicBeta) = apply { betas.add(beta) }

        /** Returns an immutable instance of [BetaRefusalFallbackInterceptor]. */
        fun build(): BetaRefusalFallbackInterceptor =
            BetaRefusalFallbackInterceptor(fallbacks.toImmutable(), betas.toImmutable())
    }

    private val jsonMapper = jsonMapper()
    private val warnedMissingState = AtomicBoolean()
    private val warnedMissingToken = AtomicBoolean()
    /** Delta types [FallbackStreamSplicer] can't accumulate into a prefill, each warned once. */
    private val warnedDeltaTypes: MutableSet<String> = ConcurrentHashMap.newKeySet()

    override fun intercept(httpClient: HttpClient): HttpClient =
        object : HttpClient {

            override fun execute(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): HttpResponse {
                val preparedRequest =
                    prepare(request, requestOptions)
                        ?: return httpClient.execute(request, requestOptions)

                var index = preparedRequest.initialIndex
                val initialRequest = preparedRequest.request(index)
                var response = httpClient.execute(initialRequest, requestOptions)
                if (preparedRequest.isStreaming) {
                    return spliceRefusedStream(preparedRequest, initialRequest, response) {
                        hopRequest ->
                        httpClient.execute(hopRequest, requestOptions)
                    }
                }

                val seams = mutableListOf<Seam>()
                while (index < fallbacks.size - 1) {
                    // Only a successful response can be a refusal; errors get normal handling.
                    if (response.statusCode() !in 200..299) {
                        return response
                    }

                    response = response.buffered()
                    val fallbackCreditToken =
                        refusalCreditToken(response)
                            ?: return prependSeamIfStitched(response, seams)
                    val category = refusalCategory(response)

                    val fromModel = preparedRequest.modelAt(index)
                    index++
                    preparedRequest.pin(index)
                    seams += Seam(fromModel, preparedRequest.modelAt(index), category)

                    response.close()
                    response =
                        httpClient.execute(
                            preparedRequest.request(index, fallbackCreditToken),
                            requestOptions,
                        )
                }
                return prependSeamIfStitched(response, seams)
            }

            override fun executeAsync(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): CompletableFuture<HttpResponse> {
                val preparedRequest =
                    prepare(request, requestOptions)
                        ?: return httpClient.executeAsync(request, requestOptions)

                val initialRequest = preparedRequest.request(preparedRequest.initialIndex)
                return httpClient.executeAsync(initialRequest, requestOptions).thenCompose {
                    response ->
                    if (preparedRequest.isStreaming) {
                        // Hop requests are issued lazily while the spliced body is being read,
                        // which already blocks the reading thread on network I/O, so joining the
                        // async hop response there blocks nothing extra.
                        CompletableFuture.completedFuture(
                            spliceRefusedStream(preparedRequest, initialRequest, response) {
                                hopRequest ->
                                httpClient.executeAsync(hopRequest, requestOptions).join()
                            }
                        )
                    } else {
                        retryRefusalAsync(
                            httpClient,
                            requestOptions,
                            preparedRequest,
                            preparedRequest.initialIndex,
                            response,
                            seams = emptyList(),
                        )
                    }
                }
            }

            override fun close() = httpClient.close()
        }

    private fun retryRefusalAsync(
        httpClient: HttpClient,
        requestOptions: RequestOptions,
        preparedRequest: PreparedRequest,
        index: Int,
        response: HttpResponse,
        /** One entry per model boundary crossed to reach [response]. */
        seams: List<Seam>,
    ): CompletableFuture<HttpResponse> {
        // Only a successful response can be a refusal; errors get normal handling.
        if (index >= fallbacks.size - 1 || response.statusCode() !in 200..299) {
            return CompletableFuture.completedFuture(prependSeamIfStitched(response, seams))
        }

        val bufferedResponse = response.buffered()
        val fallbackCreditToken =
            refusalCreditToken(bufferedResponse)
                ?: return CompletableFuture.completedFuture(
                    prependSeamIfStitched(bufferedResponse, seams)
                )
        val category = refusalCategory(bufferedResponse)

        val fromModel = preparedRequest.modelAt(index)
        val nextIndex = index + 1
        preparedRequest.pin(nextIndex)
        val nextSeams = seams + Seam(fromModel, preparedRequest.modelAt(nextIndex), category)

        bufferedResponse.close()
        return httpClient
            .executeAsync(preparedRequest.request(nextIndex, fallbackCreditToken), requestOptions)
            .thenCompose { nextResponse ->
                retryRefusalAsync(
                    httpClient,
                    requestOptions,
                    preparedRequest,
                    nextIndex,
                    nextResponse,
                    seams = nextSeams,
                )
            }
    }

    /**
     * Mirrors the server-side stitched envelope on a message served by a non-streaming fallback
     * retry: one `fallback` seam block per model boundary in [seams] — `from` the model that
     * refused (as the caller spelled it), `to` the next entry's model — is prepended in order to
     * the serving hop's content, exactly as the streaming splicer emits one at each model boundary.
     *
     * Responses that aren't a served message pass through unchanged: errors, refusals (an exhausted
     * chain's terminal refusal is surfaced verbatim), and shapes that don't parse.
     */
    private fun prependSeamIfStitched(response: HttpResponse, seams: List<Seam>): HttpResponse {
        if (seams.isEmpty() || response.statusCode() !in 200..299) {
            return response
        }
        val buffered = response.buffered()
        val message =
            try {
                buffered.json(ObjectNode::class.java)
            } catch (e: AnthropicInvalidDataException) {
                return buffered
            }
        // This raw check is the only thing keeping a seam off an exhausted chain's terminal
        // refusal (which reaches here with non-empty [seams]); it mirrors [refusalCreditToken]'s
        // typed check so the surface-verbatim contract holds.
        if (
            message.path("type").asText() != "message" ||
                message.path("stop_reason").asText() == "refusal"
        ) {
            return buffered
        }
        val content = message.get("content") as? ArrayNode ?: return buffered
        val newContent = jsonMapper.createArrayNode()
        for (seam in seams) {
            newContent.add(
                jsonMapper.valueToTree<ObjectNode>(
                    BetaFallbackBlock.builder()
                        .from(BetaFallbackInfo.builder().model(Model.of(seam.from)).build())
                        .to(BetaFallbackInfo.builder().model(Model.of(seam.to)).build())
                        .trigger(triggerFrom(seam.category))
                        .build()
                )
            )
        }
        content.forEach { newContent.add(it) }
        message.set<JsonNode>("content", newContent)
        val body = jsonMapper.writeValueAsBytes(message)
        // The body grew, so any Content-Length the upstream response carried is now wrong.
        val headers = buffered.headers().toBuilder().remove("Content-Length").build()
        return object : HttpResponse {
            override fun statusCode(): Int = buffered.statusCode()

            override fun headers() = headers

            override fun body(): InputStream = ByteArrayInputStream(body)

            override fun close() = buffered.close()
        }
    }

    /**
     * Returns a response that passes the refused stream's events through and splices the fallback
     * chain's events on (see [FallbackStreamSplicer]), or the response unchanged if it can't end in
     * a retryable refusal.
     */
    private fun spliceRefusedStream(
        preparedRequest: PreparedRequest,
        initialRequest: HttpRequest,
        response: HttpResponse,
        executeHop: (HttpRequest) -> HttpResponse,
    ): HttpResponse {
        val firstHop = preparedRequest.initialIndex + 1
        // Splicing needs at least one entry left to hop to; otherwise the stream passes through
        // untouched. Only a successful response can end in a refusal; errors get normal handling.
        if (response.statusCode() !in 200..299 || firstHop >= fallbacks.size) {
            return response
        }
        return FallbackStreamSplicer(
                fallbacks,
                firstHop,
                initialRequest,
                response,
                preparedRequest::pin,
                executeHop,
                warnedMissingToken,
                warnedDeltaTypes,
            )
            .response()
    }

    /**
     * Returns a [PreparedRequest] for retrying the given request through the fallback chain, or
     * `null` if the request should pass through unintercepted.
     */
    private fun prepare(request: HttpRequest, requestOptions: RequestOptions): PreparedRequest? {
        // This interceptor only applies to the beta messages API — the SDK's beta services tag
        // their requests with `beta=true`. An empty chain also disables this interceptor.
        if (
            fallbacks.isEmpty() ||
                request.method != HttpMethod.POST ||
                request.pathSegments != listOf("v1", "messages") ||
                !request.queryParams.values("beta").contains("true") ||
                request.body == null
        ) {
            return null
        }
        val bufferedRequest = request.buffered()
        val bodyNode = bufferedRequest.body!!.json(ObjectNode::class.java)
        // Combining the two fallback mechanisms would race retries against the server's own
        // chain, so it is rejected outright.
        if (bodyNode.hasNonNull("fallbacks")) {
            throw AnthropicException(
                "Sending the `fallbacks:` request param is not supported when using the `BetaRefusalFallbackInterceptor`. You should either remove the middleware and send `fallbacks:` with the `server-side-fallback-2026-06-01` beta header to let the API handle refusal fallbacks, or omit the `fallbacks:` param if you'd like `BetaRefusalFallbackInterceptor` to handle fallbacks on the client side."
            )
        }

        // History replayed from an earlier fallback carries blocks the server rejects; trim
        // them here so every outgoing variant — the initial request, non-streaming hops (built
        // from the typed body below), and the streaming splicer's hop continuations (re-parsed
        // from the initial request's body) — goes to the wire trimmed. The request is only
        // rebuilt when something was trimmed, so untouched bodies keep their original wire
        // bytes.
        val trimmedRequest =
            if (trimReplayedFallbackTurns(bodyNode)) {
                bufferedRequest.toBuilder().body(HttpRequestBody.ofJson(bodyNode)).build()
            } else {
                bufferedRequest
            }
        val body = jsonMapper.treeToValue(bodyNode, MessageCreateParams.Body::class.java)

        val state = requestOptions.fallbackState
        // Start from the pinned fallback (-1 = the original params).
        val index = state?.index() ?: -1
        if (index < -1 || index >= fallbacks.size) {
            throw AnthropicException(
                "fallbackState.index $index is out of bounds for a chain of ${fallbacks.size} fallback(s); was the state shared with a different interceptor?"
            )
        }

        // Send the configured betas on this and every hop request derived from it,
        // and tag this and every hop with the interceptor's helper telemetry.
        return PreparedRequest(
            withInterceptorHeaders(trimmedRequest),
            body,
            state,
            index,
            isStreaming = JsonValue.from(true) == body._additionalProperties()["stream"],
        )
    }

    /**
     * Removes content the server would reject from assistant turns replayed with a `fallback`
     * block: the fallback block itself (only accepted under a beta this interceptor does not send),
     * and everything before it that belongs to the model that refused — thinking, connector text,
     * and tool calls that never got a result. Blocks after the fallback block are what the serving
     * model produced and stay as written; a turn left with no content is dropped whole. Turns
     * without a fallback block are never touched.
     *
     * Returns whether [body] was modified (in place).
     */
    private fun trimReplayedFallbackTurns(body: ObjectNode): Boolean {
        val messages = body.get("messages") as? ArrayNode ?: return false

        // Tool calls whose result appears anywhere in the history are kept. This scan stays
        // raw: the `*_tool_result` suffix match covers result types the union doesn't model
        // yet, which a typed enumeration would miss until regenerated.
        val resolved = mutableSetOf<String>()
        for (message in messages) {
            val content = message.get("content") as? ArrayNode ?: continue
            for (block in content) {
                val type = block.path("type").asText("")
                if (type == "tool_result" || type.endsWith("_tool_result")) {
                    resolved.add(block.path("tool_use_id").asText(""))
                }
            }
        }

        val classifier = trimClassifier(resolved)
        var changed = false
        val kept = jsonMapper.createArrayNode()
        for (message in messages) {
            val content =
                (message.get("content") as? ArrayNode)?.takeIf {
                    message.path("role").asText("") == "assistant"
                }
            if (content == null) {
                kept.add(message)
                continue
            }
            // Classified through the typed union's visitor so a newly generated block type
            // fails compilation in [trimClassifier] instead of silently defaulting. Kept
            // blocks are still emitted as their original nodes — never re-serialized from
            // the typed values — so they keep their wire bytes.
            val classifications =
                content.map { block ->
                    jsonMapper
                        .treeToValue(block, BetaContentBlockParam::class.java)
                        .accept(classifier)
                }
            val lastSeam = classifications.lastIndexOf(TrimClassification.SEAM)
            if (lastSeam == -1) {
                kept.add(message)
                continue
            }
            changed = true
            val blocks = jsonMapper.createArrayNode()
            content.forEachIndexed { i, block ->
                val drop =
                    when (classifications[i]) {
                        TrimClassification.SEAM -> true
                        TrimClassification.PRE_SEAM_DROPPABLE -> i < lastSeam
                        TrimClassification.KEEP -> false
                    }
                if (!drop) {
                    blocks.add(block)
                }
            }
            // An assistant turn with no content left (a seam-only turn, say) is itself
            // rejected, so the message is dropped whole.
            if (blocks.isEmpty()) {
                continue
            }
            (message as ObjectNode).set<JsonNode>("content", blocks)
            kept.add(message)
        }
        if (!changed) {
            return false
        }
        body.set<JsonNode>("messages", kept)
        return true
    }

    /** How [trimReplayedFallbackTurns] treats a block in a seam-bearing assistant turn. */
    private enum class TrimClassification {
        /** The `fallback` seam block itself; always removed. */
        SEAM,
        /** The refused attempt's residue; removed when it precedes the turn's last seam. */
        PRE_SEAM_DROPPABLE,
        /** Everything else; kept verbatim. */
        KEEP,
    }

    /**
     * Classifies the blocks of a seam-bearing assistant turn. Exhaustive over the typed union so a
     * newly generated block type must be classified here before this compiles; types the union
     * doesn't recognize at all are kept — never drop what we don't understand.
     */
    private fun trimClassifier(
        resolved: Set<String>
    ): BetaContentBlockParam.Visitor<TrimClassification> =
        object : BetaContentBlockParam.Visitor<TrimClassification> {
            override fun visitText(text: BetaTextBlockParam) = TrimClassification.KEEP

            override fun visitImage(image: BetaImageBlockParam) = TrimClassification.KEEP

            override fun visitDocument(document: BetaRequestDocumentBlock) = TrimClassification.KEEP

            override fun visitSearchResult(searchResult: BetaSearchResultBlockParam) =
                TrimClassification.KEEP

            override fun visitThinking(thinking: BetaThinkingBlockParam) =
                TrimClassification.PRE_SEAM_DROPPABLE

            override fun visitRedactedThinking(redactedThinking: BetaRedactedThinkingBlockParam) =
                TrimClassification.PRE_SEAM_DROPPABLE

            override fun visitToolUse(toolUse: BetaToolUseBlockParam) =
                TrimClassification.PRE_SEAM_DROPPABLE

            override fun visitToolResult(toolResult: BetaToolResultBlockParam) =
                TrimClassification.KEEP

            override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
                if (serverToolUse.id() in resolved) TrimClassification.KEEP
                else TrimClassification.PRE_SEAM_DROPPABLE

            override fun visitWebSearchToolResult(
                webSearchToolResult: BetaWebSearchToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitWebFetchToolResult(
                webFetchToolResult: BetaWebFetchToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitAdvisorToolResult(
                advisorToolResult: BetaAdvisorToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitCodeExecutionToolResult(
                codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitBashCodeExecutionToolResult(
                bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitTextEditorCodeExecutionToolResult(
                textEditorCodeExecutionToolResult: BetaTextEditorCodeExecutionToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitToolSearchToolResult(
                toolSearchToolResult: BetaToolSearchToolResultBlockParam
            ) = TrimClassification.KEEP

            override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
                TrimClassification.KEEP

            override fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
                TrimClassification.KEEP

            override fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
                TrimClassification.KEEP

            override fun visitCompaction(compaction: BetaCompactionBlockParam) =
                TrimClassification.KEEP

            override fun visitMidConvSystem(midConvSystem: BetaMidConversationSystemBlockParam) =
                TrimClassification.KEEP

            override fun visitFallback(fallback: BetaFallbackBlockParam) = TrimClassification.SEAM

            override fun unknown(json: JsonValue?): TrimClassification {
                // `connector_text` has no request-union variant yet, so it lands here and is
                // classified by its raw type. Any other unrecognized type passes through
                // verbatim — never drop what we don't understand.
                val type = json?.asObject()?.getOrNull()?.get("type")?.asString()?.getOrNull()
                return if (type == "connector_text") TrimClassification.PRE_SEAM_DROPPABLE
                else TrimClassification.KEEP
            }
        }

    /**
     * Returns a copy of the request with [betas] appended to its `anthropic-beta` header (skipping
     * values already present) and the interceptor's helper-telemetry tag appended to
     * `x-stainless-helper`. Single rebuild for both.
     */
    private fun withInterceptorHeaders(request: HttpRequest): HttpRequest {
        val builder = request.toBuilder()
        val existing =
            request.headers.values("anthropic-beta").flatMapTo(mutableSetOf()) { value ->
                value.split(",").map { it.trim() }
            }
        val missing = betas.map { it.asString() }.filter { existing.add(it) }
        if (missing.isNotEmpty()) {
            builder.putHeaders("anthropic-beta", missing)
        }
        return builder
            .replaceHeaders(
                STAINLESS_HELPER_HEADER,
                mergedStainlessHelperValue(
                    request.headers,
                    StainlessHelperHeaderValue.FALLBACK_REFUSAL_MIDDLEWARE,
                ),
            )
            .build()
    }

    /**
     * Returns the refusal's `fallback_credit_token` if the response is a refused message, or `null`
     * if the response isn't a refusal and should be returned as-is.
     */
    private fun refusalCreditToken(response: HttpResponse): Optional<String>? =
        try {
            val message = response.json(BetaMessage::class.java)
            if (message.stopReason().map(BetaStopReason.REFUSAL::equals).orElse(false)) {
                message.stopDetails().flatMap(BetaRefusalStopDetails::fallbackCreditToken)
            } else {
                null
            }
        } catch (e: AnthropicInvalidDataException) {
            // Pass unexpected response shapes (e.g. errors) through for normal handling.
            null
        }

    /**
     * Returns the refusal's policy `category` if the response is a refused message, or `null` if it
     * isn't (or none was surfaced). [response] must be buffered — re-parses the same body
     * [refusalCreditToken] read.
     */
    private fun refusalCategory(response: HttpResponse): BetaRefusalStopDetails.Category? =
        try {
            response
                .json(BetaMessage::class.java)
                .stopDetails()
                .flatMap(BetaRefusalStopDetails::category)
                .getOrNull()
        } catch (e: AnthropicInvalidDataException) {
            null
        }

    /** One model boundary the non-streaming retry chain crossed. */
    private data class Seam(
        val from: String,
        val to: String,
        val category: BetaRefusalStopDetails.Category?,
    )

    /** A request being retried through the fallback chain. */
    private inner class PreparedRequest(
        private val request: HttpRequest,
        private val body: MessageCreateParams.Body,
        private val state: BetaFallbackState?,
        val initialIndex: Int,
        val isStreaming: Boolean,
    ) {

        /**
         * Returns the request with the fallback at the given index applied (-1 = the original
         * request), plus the previous refusal's `fallback_credit_token`, if any.
         */
        fun request(
            index: Int,
            fallbackCreditToken: Optional<String> = Optional.empty(),
        ): HttpRequest =
            if (index == -1) request
            else
                request
                    .toBuilder()
                    .body(HttpRequestBody.ofJson(applyFallback(index, fallbackCreditToken)))
                    .build()

        /**
         * The model the request at the given index goes to the wire with (-1 = the caller's
         * original model, as they spelled it).
         */
        fun modelAt(index: Int): String =
            if (index == -1) body.model().asString() else fallbacks[index].model().asString()

        /** Pins requests sharing the [state] to the fallback at the given index. */
        fun pin(index: Int) {
            if (state != null) {
                state.index(index)
            } else if (!warnedMissingState.getAndSet(true)) {
                System.err.println(
                    "WARNING: `BetaRefusalFallbackInterceptor` fell back without a `fallbackState` request option; follow-up requests will retry models that already refused. Pass a shared `RequestOptions.builder().fallbackState(BetaFallbackState.create()).build()` to pin them to the accepted model."
                )
            }
        }

        private fun applyFallback(
            index: Int,
            fallbackCreditToken: Optional<String>,
        ): MessageCreateParams.Body {
            val fallback = fallbacks[index]
            val newBody = body.toBuilder().model(fallback.model())
            fallback.maxTokens().ifPresent { newBody.maxTokens(it) }
            fallback.outputConfig().ifPresent { newBody.outputConfig(it) }
            fallback.speed().ifPresent {
                newBody.speed(MessageCreateParams.Speed.of(it.asString()))
            }
            fallback.thinking().ifPresent { thinking ->
                when {
                    thinking.isEnabled() -> newBody.thinking(thinking.asEnabled())
                    thinking.isDisabled() -> newBody.thinking(thinking.asDisabled())
                    else -> newBody.thinking(thinking.asAdaptive())
                }
            }
            newBody.putAllAdditionalProperties(fallback._additionalProperties())
            fallbackCreditToken.ifPresent { newBody.fallbackCreditToken(it) }
            return newBody.build()
        }
    }
}

// --- streaming fallback (credit-token continuation) -----------------------------------------
//
// The retry uses the appended-assistant form documented on `fallback_credit_token`: the refused
// request's body, extended by one trailing assistant turn carrying the refused model's partial
// output. The token authorizes that turn as a prefill continuation and applies the fallback
// credit. The refusal's `fallback_has_prefill_claim` says whether the partial output may be resent
// verbatim: when true the accumulated blocks are appended as-is; when false the refused hop's
// output is dropped and the token is redeemed against the same body.
//
// Known divergences from server-side `fallbacks`:
//
// * `message.model` keeps the refused model's id — message_start has already been sent when the
//   refusal arrives; the `fallback` block's `model` field carries the serving model.
// * Refusal text streamed before the refusal stays in the message and is resent as-is — the
//   appended turn must match the partial output verbatim.

/**
 * Builds the `trigger` for a synthetic seam block from the refusal's stop_details category. `null`
 * when none was surfaced — mirrors the server's gated emission. Shared between the non-streaming
 * interceptor and [FallbackStreamSplicer].
 */
private fun triggerFrom(category: BetaRefusalStopDetails.Category?): BetaFallbackRefusalTrigger =
    BetaFallbackRefusalTrigger.builder()
        .category(category?.let { BetaFallbackRefusalTrigger.Category.of(it.toString()) })
        .build()

/**
 * Splices the fallback chain's events onto a stream that ends in a retryable refusal.
 *
 * The spliced body is produced lazily as it's read: the refused stream's events pass through in
 * their original wire bytes until a chainable refusal, then each fallback hop's request is issued
 * and its events are spliced on. Closing the [response] tears down whichever stream is being read;
 * an in-flight hop request can't be cancelled, but its response is closed once it resolves.
 *
 * Events flowing through the splice — and the partial output resent as the continuation prefill —
 * are handled as raw JSON rather than the typed models: the credit token is only redeemable when
 * the prefill matches the wire output verbatim, and only raw JSON accumulates deltas onto block
 * types the SDK doesn't model yet (exactly what a brand-new model may stream). Detection reads the
 * typed models through their lenient accessors ([com.anthropic.core.JsonField.asKnown] surfaces an
 * unexpected shape as an empty value instead of throwing), and events the splicer synthesizes
 * itself are built from the typed models.
 */
private class FallbackStreamSplicer(
    private val fallbacks: List<BetaFallbackParam>,
    /** Index into [fallbacks] of the first entry to try when the initial stream refuses. */
    private val firstHop: Int,
    /**
     * The request the initial stream was made with — the body its credit token is redeemable
     * against.
     */
    private val initialRequest: HttpRequest,
    /** The initial stream: the OK SSE response that may end in a refusal. */
    private val initialResponse: HttpResponse,
    /** Pins shared state to the entry being tried (or warns that there is none). */
    private val pin: (Int) -> Unit,
    private val executeHop: (HttpRequest) -> HttpResponse,
    /** Rate-limits the missing-credit-token warning to once per interceptor. */
    private val warnedMissingToken: AtomicBoolean,
    /** Delta types already warned about as non-accumulable, shared across the interceptor. */
    private val warnedDeltaTypes: MutableSet<String>,
) {

    private val jsonMapper = jsonMapper()
    private val closed = AtomicBoolean()
    /**
     * The response currently being consumed, closed when fully consumed or on close. Seeded with
     * [initialResponse] so a close before the first read still releases it.
     */
    private val currentResponse = AtomicReference<HttpResponse?>(initialResponse)

    fun response(): HttpResponse =
        object : HttpResponse {
            private val body = SequenceInputStream(splicedEvents().iterator())

            override fun statusCode(): Int = initialResponse.statusCode()

            override fun headers() = initialResponse.headers()

            override fun body(): InputStream = body

            override fun close() = body.close()
        }

    /** An [InputStream] over a lazily produced sequence of byte chunks. */
    private inner class SequenceInputStream(private val chunks: Iterator<ByteArray>) :
        InputStream() {

        private var current = ByteArray(0)
        private var position = 0

        override fun read(): Int {
            val buffer = ByteArray(1)
            return if (read(buffer, 0, 1) == -1) -1 else buffer[0].toInt() and 0xFF
        }

        override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
            if (length == 0) {
                return 0
            }
            while (position >= current.size) {
                if (closed.get() || !chunks.hasNext()) {
                    return -1
                }
                current = chunks.next()
                position = 0
            }
            val count = min(length, current.size - position)
            System.arraycopy(current, position, buffer, offset, count)
            position += count
            return count
        }

        override fun close() {
            closed.set(true)
            currentResponse.getAndSet(null)?.close()
        }
    }

    /**
     * The spliced SSE byte chunks: the initial stream passed through until a chainable refusal,
     * then each fallback hop tried in order.
     */
    private fun splicedEvents(): Sequence<ByteArray> = sequence {
        // --- the initial stream: pass through until a chainable refusal ---
        // the caller guarantees firstHop < fallbacks.size
        val initial = consumeHop(initialResponse, indexBase = 0, hasNext = true, splice = null)
        currentResponse.getAndSet(null)?.close()
        val initialRefusal = initial.refusal ?: return@sequence // non-refusal: pure pass-through

        // --- fallback chain: try each entry in order ---
        // `base` is the assistant-turn content the current token's request already carried — the
        // token is redeemable only with it resent verbatim. `partial` is the newest refused hop's
        // output, included only when its refusal granted a prefill claim (any other change to the
        // body is a 400).
        var nextIndex = initial.nextIndex // monotonic block index across all spliced streams
        // The refusal whose token is currently in flight — its message_delta is surfaced verbatim
        // (with a recommended_model added) if every fallback request fails and we degrade.
        var refusal = initialRefusal
        var base = listOf<ObjectNode>()
        var partial =
            if (initialRefusal.hasPrefillClaim) toPrefillBlocks(initial.blocks) else emptyList()
        var fromModel = initial.model ?: ""

        // One `message` entry per refused hop, in order — the initial stream's first. Failed hops
        // are skipped (no usage came back); the serving hop is appended as `fallback_message` when
        // its message_delta arrives.
        val iterations =
            mutableListOf(toMessageIterationUsage(initial.model ?: "", initialRefusal.usage))

        for (hop in firstHop until fallbacks.size) {
            val model = fallbacks[hop].model().asString()
            val hasNext = hop + 1 < fallbacks.size
            pin(hop)

            // --- boundary: a `fallback` content block at the next monotonic index ---
            // Emitted before the request, so a hop that fails leaves its boundary in place and the
            // next attempt emits its own (still `from: fromModel` — the last model that
            // contributed output).
            val boundaryIndex = nextIndex++
            yield(
                fallbackBlockStart(
                    boundaryIndex,
                    fromModel,
                    model,
                    refusal.details.category().getOrNull(),
                )
            )
            yield(contentBlockStop(boundaryIndex))

            // --- build the request: appended-assistant continuation ---
            // The first attempt carries the newest partial appended (when its refusal granted a
            // prefill claim); a 400 on that form means the server rejected the prefill, so the hop
            // is retried once without it — the same-body form the token always supports.
            var continuation = base + partial
            var hopResponse: HttpResponse? = null
            var failed = false
            for (attempt in 0..1) {
                val hopRequest = buildFallbackRequest(model, refusal.token, continuation)
                val response =
                    try {
                        executeHop(hopRequest)
                    } catch (e: Throwable) {
                        val cause = (e as? CompletionException)?.cause ?: e
                        warn("fallback request to $model failed: $cause")
                        failed = true
                        break
                    }
                // The spliced stream may have been closed while the hop was in flight; the request
                // can't be cancelled, so release its response now that it has resolved.
                if (closed.get()) {
                    response.close()
                    return@sequence
                }
                if (response.statusCode() in 200..299) {
                    hopResponse = response
                    break
                }
                val errorBody = readErrorBody(response)
                if (attempt == 0 && response.statusCode() == 400 && partial.isNotEmpty()) {
                    warn(
                        "fallback request with the partial output appended was rejected (HTTP 400: $errorBody); retrying without it"
                    )
                    continuation = base
                    continue
                }
                warn("fallback request to $model failed: HTTP ${response.statusCode()}: $errorBody")
                failed = true
                break
            }

            if (failed) {
                // The token was never redeemed — retry it against the next entry.
                if (hasNext) {
                    continue
                }
                // Surface the held refusal verbatim — its category/explanation and the still
                // unredeemed credit token — and point recommended_model at the hop we last tried.
                yield(heldRefusalDelta(refusal, model))
                yield(messageStop())
                return@sequence
            }

            // --- splice: monotonic indices, suppressed message_start, usage.iterations ---
            currentResponse.set(hopResponse)
            // A close between the in-flight check above and this registration finds
            // currentResponse empty; re-check so the hop response is still released.
            if (closed.get()) {
                currentResponse.getAndSet(null)?.close()
                return@sequence
            }
            val outcome =
                consumeHop(
                    hopResponse!!,
                    indexBase = nextIndex,
                    hasNext,
                    splice = Splice(iterations, model),
                )
            currentResponse.getAndSet(null)?.close()
            val hopRefusal = outcome.refusal ?: return@sequence

            // This hop refused too, with a fresh token: its emitted partial stays in the client's
            // message, becomes the next partial segment, and the chain continues.
            refusal = hopRefusal
            base = continuation
            partial =
                if (hopRefusal.hasPrefillClaim) toPrefillBlocks(outcome.blocks) else emptyList()
            iterations.add(toMessageIterationUsage(model, hopRefusal.usage))
            fromModel = model
            nextIndex = outcome.nextIndex
        }
    }

    /**
     * Consumes one hop's SSE events, forwarding them to the client while accumulating its content
     * blocks (returned in the outcome).
     *
     * The initial stream (`splice == null`) is forwarded in its original wire bytes; a spliced hop
     * (`splice` set) has its message_start suppressed (the client already saw the initial one), its
     * block indices shifted by [indexBase], and its terminal message_delta's usage rewritten to the
     * `usage.iterations` chain shape.
     *
     * A refusal that can be chained — it carries a `fallback_credit_token` and a fallback entry
     * remains — ends the hop early: open blocks are closed, the terminal message_delta +
     * message_stop are suppressed, and the token + usage are returned so the caller can issue the
     * next hop. Any other refusal is reported with a warning and passes through to the client.
     */
    private suspend fun SequenceScope<ByteArray>.consumeHop(
        response: HttpResponse,
        /** Shifts wire block indices by this much, keeping them monotonic across hops. */
        indexBase: Int,
        /** Whether a fallback entry exists to chain to if this hop refuses. */
        hasNext: Boolean,
        /** Splice context for fallback hops; `null` for the initial stream. */
        splice: Splice?,
    ): HopOutcome {
        val tracker = BlockTracker(indexBase)
        var model: String? = null
        var startUsage: ObjectNode? = null

        rawSseHandler(jsonMapper).handle(response).use { sseStream ->
            events@ for (sse in sseStream.stream().asSequence()) {
                // The decoder's `lastId` persists across messages, so stray blank lines after an
                // id-bearing message can flush line-less messages; nothing to forward.
                if (sse.rawLines.isEmpty()) {
                    continue@events
                }
                val event = parseJsonObject(sse.data)
                when (event?.path("type")?.asText()) {
                    "message_start" -> {
                        val message =
                            jsonMapper
                                .treeToValue(event, BetaRawMessageStartEvent::class.java)
                                ._message()
                                .asKnown()
                                .getOrNull()
                        model =
                            message
                                ?._model()
                                ?.asKnown()
                                ?.getOrNull()
                                ?._value()
                                ?.asString()
                                ?.getOrNull()
                        startUsage =
                            message?._usage()?.asKnown()?.getOrNull()?.let {
                                jsonMapper.valueToTree<ObjectNode>(it)
                            }
                        if (splice != null) {
                            continue@events
                        }
                    }
                    "content_block_start" -> {
                        tracker.start(event)
                        if (splice != null) {
                            yield(emit(event))
                            continue@events
                        }
                    }
                    "content_block_delta" -> {
                        tracker.delta(event)
                        if (splice != null) {
                            yield(emit(event))
                            continue@events
                        }
                    }
                    "content_block_stop" -> {
                        tracker.stop(event)
                        if (splice != null) {
                            yield(emit(event))
                            continue@events
                        }
                    }
                    "message_delta" -> {
                        val deltaEvent =
                            jsonMapper.treeToValue(event, BetaRawMessageDeltaEvent::class.java)
                        val delta = deltaEvent._delta().asKnown().getOrNull()
                        if (
                            delta?._stopReason()?.asKnown()?.getOrNull() == BetaStopReason.REFUSAL
                        ) {
                            // `fallback_credit_token` is null when the refusal isn't eligible for
                            // a fallback credit; without one we don't retry. The `stop_details`
                            // field lenient-parses any shape, so its discriminator is re-checked.
                            val details =
                                delta._stopDetails().asKnown().getOrNull()?.takeIf {
                                    it._type() == JsonValue.from("refusal")
                                }
                            val token = details?._fallbackCreditToken()?.asKnown()?.getOrNull()
                            if (token != null && hasNext) {
                                val usage = backfill(event.path("usage") as? ObjectNode, startUsage)
                                tracker.closeOpenBlocks().forEach { yield(it) }
                                // suppress this hop's message_delta + message_stop
                                return HopOutcome(
                                    refusal =
                                        Refusal(
                                            token,
                                            details
                                                ._fallbackHasPrefillClaim()
                                                .asKnown()
                                                .getOrNull() == true,
                                            usage,
                                            deltaEvent,
                                            delta,
                                            details,
                                        ),
                                    model,
                                    tracker.blocks,
                                    tracker.nextIndex,
                                )
                            }
                            // Chain exhaustion (a token with no entries left) is normal operation
                            // and fully visible in the terminal message_delta, so it isn't
                            // warned; a missing token can be steady-state (the account may not
                            // have the fallback-credit beta), so it's warned only once.
                            if (token == null && !warnedMissingToken.getAndSet(true)) {
                                warn(
                                    "refusal stop_details has no fallback_credit_token, so there is nothing to retry; the refusal may be ineligible for a fallback credit, or the fallback-credit beta may not be enabled for this account"
                                )
                            }
                        }
                        if (splice != null) {
                            // Terminal hop. Replace iterations, don't append: this hop's own
                            // message_delta self-reports a single `{type: "message"}` iteration (a
                            // fresh non-fallback request counts itself as one message hop).
                            // Server-side `fallbacks` relabels the whole chain instead — refused
                            // hops as `message`, the serving hop as `fallback_message` — so
                            // keeping the self-report would add a spurious entry.
                            val usage = backfill(event.path("usage") as? ObjectNode, startUsage)
                            val entry = toFallbackIterationUsage(splice.model, usage)
                            val chain = jsonMapper.createArrayNode()
                            splice.iterations.forEach { chain.add(it.deepCopy()) }
                            chain.add(entry)
                            usage.set<JsonNode>("iterations", chain)
                            event.set<JsonNode>("usage", usage)
                            yield(emit(event))
                            continue@events
                        }
                    }
                }

                // message_stop, ping, error, unrecognised — and for the initial stream every
                // event — pass through in their original wire bytes.
                yield(passthrough(sse))
            }
        }
        return HopOutcome(refusal = null, model, tracker.blocks, tracker.nextIndex)
    }

    // --- fallback request construction (appended-assistant continuation) ---

    private fun buildFallbackRequest(
        model: String,
        creditToken: String,
        continuation: List<ObjectNode>,
    ): HttpRequest {
        // Re-parsed on every attempt so each request starts from the body the token was minted
        // against.
        val body = initialRequest.body!!.json(ObjectNode::class.java)

        body.put("model", model)
        body.put("fallback_credit_token", creditToken)

        // Append the continuation (decided by the chain loop) as a trailing assistant turn;
        // everything else — max_tokens included — must stay identical to the refused request: the
        // token is only redeemable against the same body, so model, fallback_credit_token, and the
        // one appended assistant turn are the only permitted deltas; anything else is a 400. This
        // is also why the per-entry BetaFallbackParam overrides are ignored on the streaming path.
        if (continuation.isNotEmpty()) {
            val messages = body.get("messages") as? ArrayNode ?: body.putArray("messages")
            val turn = jsonMapper.createObjectNode()
            turn.put("role", "assistant")
            val content = turn.putArray("content")
            continuation.forEach { content.add(it.deepCopy()) }
            messages.add(turn)
        }

        return initialRequest.toBuilder().body(HttpRequestBody.ofJson(body)).build()
    }

    /** Reads and parses a failed hop response's error body, closing the response. */
    private fun readErrorBody(response: HttpResponse): JsonValue =
        response.use { errorBodyHandler(jsonMapper).handle(it) }

    // --- event synthesis & serialization ---

    private fun fallbackBlockStart(
        index: Int,
        fromModel: String,
        toModel: String,
        category: BetaRefusalStopDetails.Category?,
    ): ByteArray =
        emit(
            "content_block_start",
            BetaRawContentBlockStartEvent.builder()
                .index(index.toLong())
                .contentBlock(
                    BetaRawContentBlockStartEvent.ContentBlock.ofFallback(
                        BetaFallbackBlock.builder()
                            .from(BetaFallbackInfo.builder().model(Model.of(fromModel)).build())
                            .to(BetaFallbackInfo.builder().model(Model.of(toModel)).build())
                            .trigger(triggerFrom(category))
                            .build()
                    )
                )
                .build(),
        )

    private fun contentBlockStop(index: Int): ByteArray =
        emit(
            "content_block_stop",
            BetaRawContentBlockStopEvent.builder().index(index.toLong()).build(),
        )

    /**
     * Surfaces a held refusal's message_delta verbatim — fields the splicer doesn't use
     * (`context_management`, unknown fields, ...) ride along on the typed event — with
     * `recommended_model` pointed at the hop last tried and its usage backfilled from its
     * message_start (injected raw: the merged usage is the wire shape, not a typed value).
     */
    private fun heldRefusalDelta(refusal: Refusal, recommendedModel: String): ByteArray =
        emit(
            "message_delta",
            refusal.event
                .toBuilder()
                .delta(
                    refusal.delta
                        .toBuilder()
                        .stopDetails(
                            refusal.details.toBuilder().recommendedModel(recommendedModel).build()
                        )
                        .build()
                )
                .usage(JsonValue.fromJsonNode(refusal.usage))
                .build(),
        )

    private fun messageStop(): ByteArray =
        emit("message_stop", BetaRawMessageStopEvent.builder().build())

    /** Serializes an event payload to its SSE wire form. */
    private fun emit(event: String, payload: Any): ByteArray =
        "event: $event\ndata: ${jsonMapper.writeValueAsString(payload)}\n\n".toByteArray()

    /** Serializes a wire event payload to its SSE wire form (its `type` is the event name). */
    private fun emit(event: ObjectNode): ByteArray = emit(event.path("type").asText(), event)

    /**
     * Forwards a decoded event in its original wire bytes, preserving SSE fields the decoder
     * doesn't model (`id:`, `retry:`, comment lines).
     */
    private fun passthrough(sse: SseMessage): ByteArray =
        (sse.rawLines.joinToString("\n") + "\n\n").toByteArray()

    private fun parseJsonObject(data: String): ObjectNode? =
        try {
            jsonMapper.readTree(data) as? ObjectNode
        } catch (e: Exception) {
            null
        }

    private fun warn(message: String) {
        System.err.println("WARNING: `BetaRefusalFallbackInterceptor`: $message")
    }

    // --- usage bookkeeping ---

    /** Builds a `usage.iterations` entry for a refused hop from its (raw, backfilled) usage. */
    private fun toMessageIterationUsage(model: String, usage: ObjectNode?): ObjectNode =
        jsonMapper.valueToTree(
            BetaMessageIterationUsage.builder()
                .model(Model.of(model))
                .inputTokens(usage.longField("input_tokens"))
                .outputTokens(usage.longField("output_tokens"))
                .cacheReadInputTokens(usage.longField("cache_read_input_tokens"))
                .cacheCreationInputTokens(usage.longField("cache_creation_input_tokens"))
                .cacheCreation(usage.cacheCreation())
                .build()
        )

    /** Builds the serving hop's `usage.iterations` entry from its (raw, backfilled) usage. */
    private fun toFallbackIterationUsage(model: String, usage: ObjectNode?): ObjectNode =
        // `BetaFallbackMessageIterationUsage` differs from `BetaMessageIterationUsage` only in its
        // type discriminator; restamping one shape keeps a new usage field from being mapped in
        // one entry kind but not the other.
        toMessageIterationUsage(model, usage).put("type", "fallback_message")

    private fun ObjectNode?.longField(name: String): Long =
        this?.get(name)?.takeIf { it.isNumber }?.asLong() ?: 0L

    private fun ObjectNode?.cacheCreation(): BetaCacheCreation? =
        this?.get("cache_creation")
            ?.takeIf { it.isObject }
            ?.let { jsonMapper.treeToValue(it, BetaCacheCreation::class.java) }

    /**
     * Fills null/missing fields on `primary` (a message_delta usage) from `fallback` (the
     * message_start usage).
     */
    private fun backfill(primary: ObjectNode?, fallback: ObjectNode?): ObjectNode {
        val merged = fallback?.deepCopy() ?: jsonMapper.createObjectNode()
        primary?.fields()?.forEach { (name, value) -> merged.set<JsonNode>(name, value.deepCopy()) }
        for (name in merged.fieldNames().asSequence().toList()) {
            val fallbackValue = fallback?.get(name)
            if (merged.get(name).isNull && fallbackValue != null && !fallbackValue.isNull) {
                merged.set<JsonNode>(name, fallbackValue.deepCopy())
            }
        }
        return merged
    }

    // --- block accumulation & prefill conversion ---

    /** Splice context for fallback hops. */
    private class Splice(val iterations: List<ObjectNode>, val model: String)

    /** The outcome of consuming one hop's stream. */
    private class HopOutcome(
        /** Set when the hop refused with a credit token and an entry remained to chain to. */
        val refusal: Refusal?,
        /** The hop's serving model, from its message_start. */
        val model: String?,
        /** The hop's accumulated content blocks, in start order — the next partial segment. */
        val blocks: List<AccumulatedBlock>,
        /** One past the highest (shifted) block index emitted — where the next boundary goes. */
        val nextIndex: Int,
    )

    private class Refusal(
        val token: String,
        val hasPrefillClaim: Boolean,
        val usage: ObjectNode,
        /** The refusal's message_delta wire event, surfaced if the whole chain degrades. */
        val event: BetaRawMessageDeltaEvent,
        /** [event]'s delta and refusal stop details, rebuilt into it when it's surfaced. */
        val delta: BetaRawMessageDeltaEvent.Delta,
        val details: BetaRefusalStopDetails,
    )

    /** A response content block being accumulated from its streaming deltas. */
    private class AccumulatedBlock(val index: Int, val block: ObjectNode) {
        /** The block's `input_json_delta` JSON accumulated so far, if any arrived. */
        var partialJson: StringBuilder? = null
    }

    /**
     * Block bookkeeping for one stream of the splice: accumulates each content block from its
     * deltas (for the continuation prefill), shifts wire indices by [indexBase] so they stay
     * monotonic across hops, and tracks which blocks are still open so a refusal that cuts
     * mid-block can close them.
     */
    private inner class BlockTracker(private val indexBase: Int) {

        /** The stream's accumulated blocks keyed by their original wire index, in start order. */
        val blocks = mutableListOf<AccumulatedBlock>()

        /** One past the highest shifted block index seen. */
        var nextIndex = indexBase
            private set

        /** Shifted indices of blocks started but not yet stopped. */
        private val open = mutableListOf<Int>()

        /** Tracks a content_block_start, shifting the event's `index`. */
        fun start(event: ObjectNode) {
            val index = event.path("index").asInt()
            val block =
                (event.path("content_block") as? ObjectNode)?.deepCopy()
                    ?: jsonMapper.createObjectNode()
            blocks.add(AccumulatedBlock(index, block))
            val shifted = index + indexBase
            event.put("index", shifted)
            open.add(shifted)
            nextIndex = max(nextIndex, shifted + 1)
        }

        /**
         * Applies a content_block_delta to its accumulating block, shifting the event's `index`.
         */
        fun delta(event: ObjectNode) {
            val index = event.path("index").asInt()
            blocks.find { it.index == index }?.let { applyDelta(it, event.path("delta")) }
            event.put("index", index + indexBase)
        }

        /** Tracks a content_block_stop, shifting the event's `index`. */
        fun stop(event: ObjectNode) {
            val shifted = event.path("index").asInt() + indexBase
            event.put("index", shifted)
            open.remove(shifted)
            nextIndex = max(nextIndex, shifted + 1)
        }

        /** content_block_stop events for any blocks still open. */
        fun closeOpenBlocks(): List<ByteArray> {
            val stops = open.map { contentBlockStop(it) }
            open.clear()
            return stops
        }

        private fun applyDelta(accumulated: AccumulatedBlock, delta: JsonNode) {
            val block = accumulated.block
            when (delta.path("type").asText("")) {
                "text_delta" ->
                    block.put("text", block.path("text").asText("") + delta.path("text").asText(""))
                "input_json_delta" -> {
                    val partialJson =
                        accumulated.partialJson
                            ?: StringBuilder().also { accumulated.partialJson = it }
                    partialJson.append(delta.path("partial_json").asText(""))
                }
                "citations_delta" -> {
                    val citations =
                        block.get("citations") as? ArrayNode ?: block.putArray("citations")
                    citations.add(delta.path("citation").deepCopy<JsonNode>())
                }
                "thinking_delta" ->
                    block.put(
                        "thinking",
                        block.path("thinking").asText("") + delta.path("thinking").asText(""),
                    )
                "signature_delta" -> block.put("signature", delta.path("signature").asText(""))
                else -> {
                    // A delta type this accumulator doesn't know (a brand-new model's, say) can't
                    // be folded into the block, so a prefill including it may not match the wire
                    // output; the server rejects it and the hop retries without the prefill.
                    val type = delta.path("type").asText("")
                    if (warnedDeltaTypes.add(type)) {
                        warn(
                            "content_block_delta type \"$type\" is not accumulated; a continuation prefill including its block may be rejected and retried without the prefill"
                        )
                    }
                }
            }
        }
    }

    /**
     * Converts a hop's accumulated response blocks to the appended assistant turn, as-is: a
     * `fallback_has_prefill_claim` refusal guarantees the partial output is resendable verbatim, so
     * no client-side filtering is applied. The only rewrite is reassembling tool inputs from their
     * accumulated `input_json_delta` JSON (content_block_start carries `input: {}`).
     */
    private fun toPrefillBlocks(blocks: List<AccumulatedBlock>): List<ObjectNode> =
        blocks.map { accumulated ->
            val partialJson = accumulated.partialJson ?: return@map accumulated.block
            val input =
                try {
                    jsonMapper.readTree(partialJson.toString())
                } catch (e: Exception) {
                    null
                }
            input?.let { accumulated.block.set<JsonNode>("input", it) }
            accumulated.block
        }
}
