package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional

@JsonDeserialize(using = BetaResponseToolUnion.Deserializer::class)
@JsonSerialize(using = BetaResponseToolUnion.Serializer::class)
class BetaResponseToolUnion
private constructor(
    private val betaResponseTool: BetaResponseTool? = null,
    private val toolBash20241022: BetaToolBash20241022? = null,
    private val toolBash20250124: BetaToolBash20250124? = null,
    private val codeExecutionTool20250522: BetaCodeExecutionTool20250522? = null,
    private val codeExecutionTool20250825: BetaCodeExecutionTool20250825? = null,
    private val codeExecutionTool20260120: BetaCodeExecutionTool20260120? = null,
    private val codeExecutionTool20260521: BetaCodeExecutionTool20260521? = null,
    private val browserToolset20260801: BetaBrowserToolset20260801? = null,
    private val toolComputerUse20241022: BetaToolComputerUse20241022? = null,
    private val memoryTool20250818: BetaMemoryTool20250818? = null,
    private val toolComputerUse20250124: BetaToolComputerUse20250124? = null,
    private val toolTextEditor20241022: BetaToolTextEditor20241022? = null,
    private val toolComputerUse20251124: BetaToolComputerUse20251124? = null,
    private val computerToolset20260801: BetaComputerToolset20260801? = null,
    private val toolTextEditor20250124: BetaToolTextEditor20250124? = null,
    private val toolTextEditor20250429: BetaToolTextEditor20250429? = null,
    private val toolTextEditor20250728: BetaToolTextEditor20250728? = null,
    private val webSearchTool20250305: BetaWebSearchTool20250305? = null,
    private val webFetchTool20250910: BetaWebFetchTool20250910? = null,
    private val webSearchTool20260209: BetaWebSearchTool20260209? = null,
    private val webFetchTool20260209: BetaWebFetchTool20260209? = null,
    private val webFetchTool20260309: BetaWebFetchTool20260309? = null,
    private val webSearchTool20260318: BetaWebSearchTool20260318? = null,
    private val webFetchTool20260318: BetaWebFetchTool20260318? = null,
    private val advisorTool20260301: BetaAdvisorTool20260301? = null,
    private val toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119? = null,
    private val toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119? = null,
    private val mcpToolset: BetaMcpToolset? = null,
    private val _json: JsonValue? = null,
) {

    fun toParam(): BetaToolUnion =
        accept(
            object : Visitor<BetaToolUnion> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): BetaToolUnion = BetaToolUnion.ofBetaTool(betaResponseTool.toParam())

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): BetaToolUnion = BetaToolUnion.ofBash20241022(toolBash20241022)

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): BetaToolUnion = BetaToolUnion.ofBash20250124(toolBash20250124)

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): BetaToolUnion =
                    BetaToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): BetaToolUnion =
                    BetaToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): BetaToolUnion =
                    BetaToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): BetaToolUnion =
                    BetaToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): BetaToolUnion = BetaToolUnion.ofBrowserToolset20260801(browserToolset20260801)

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): BetaToolUnion = BetaToolUnion.ofComputerUse20241022(toolComputerUse20241022)

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): BetaToolUnion = BetaToolUnion.ofMemoryTool20250818(memoryTool20250818)

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): BetaToolUnion = BetaToolUnion.ofComputerUse20250124(toolComputerUse20250124)

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): BetaToolUnion = BetaToolUnion.ofTextEditor20241022(toolTextEditor20241022)

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): BetaToolUnion = BetaToolUnion.ofComputerUse20251124(toolComputerUse20251124)

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): BetaToolUnion = BetaToolUnion.ofComputerToolset20260801(computerToolset20260801)

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): BetaToolUnion = BetaToolUnion.ofTextEditor20250124(toolTextEditor20250124)

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): BetaToolUnion = BetaToolUnion.ofTextEditor20250429(toolTextEditor20250429)

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): BetaToolUnion = BetaToolUnion.ofTextEditor20250728(toolTextEditor20250728)

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): BetaToolUnion = BetaToolUnion.ofWebSearchTool20250305(webSearchTool20250305)

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): BetaToolUnion = BetaToolUnion.ofWebFetchTool20250910(webFetchTool20250910)

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): BetaToolUnion = BetaToolUnion.ofWebSearchTool20260209(webSearchTool20260209)

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): BetaToolUnion = BetaToolUnion.ofWebFetchTool20260209(webFetchTool20260209)

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): BetaToolUnion = BetaToolUnion.ofWebFetchTool20260309(webFetchTool20260309)

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): BetaToolUnion = BetaToolUnion.ofWebSearchTool20260318(webSearchTool20260318)

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): BetaToolUnion = BetaToolUnion.ofWebFetchTool20260318(webFetchTool20260318)

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): BetaToolUnion = BetaToolUnion.ofAdvisorTool20260301(advisorTool20260301)

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): BetaToolUnion =
                    BetaToolUnion.ofSearchToolBm25_20251119(toolSearchToolBm25_20251119)

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): BetaToolUnion =
                    BetaToolUnion.ofSearchToolRegex20251119(toolSearchToolRegex20251119)

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): BetaToolUnion =
                    BetaToolUnion.ofMcpToolset(mcpToolset)
            }
        )

    fun deferLoading(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Boolean> = betaResponseTool.deferLoading()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Boolean> = toolBash20241022.deferLoading()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Boolean> = toolBash20250124.deferLoading()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Boolean> = codeExecutionTool20250522.deferLoading()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Boolean> = codeExecutionTool20250825.deferLoading()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Boolean> = codeExecutionTool20260120.deferLoading()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Boolean> = codeExecutionTool20260521.deferLoading()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Boolean> = toolComputerUse20241022.deferLoading()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Boolean> = memoryTool20250818.deferLoading()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Boolean> = toolComputerUse20250124.deferLoading()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Boolean> = toolTextEditor20241022.deferLoading()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Boolean> = toolComputerUse20251124.deferLoading()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Boolean> = toolTextEditor20250124.deferLoading()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Boolean> = toolTextEditor20250429.deferLoading()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Boolean> = toolTextEditor20250728.deferLoading()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Boolean> = webSearchTool20250305.deferLoading()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Boolean> = webFetchTool20250910.deferLoading()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Boolean> = webSearchTool20260209.deferLoading()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Boolean> = webFetchTool20260209.deferLoading()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Boolean> = webFetchTool20260309.deferLoading()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Boolean> = webSearchTool20260318.deferLoading()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Boolean> = webFetchTool20260318.deferLoading()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Boolean> = advisorTool20260301.deferLoading()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Boolean> = toolSearchToolBm25_20251119.deferLoading()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Boolean> = toolSearchToolRegex20251119.deferLoading()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Boolean> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("defer_loading").asKnown()
            }
        )

    fun strict(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Boolean> = betaResponseTool.strict()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Boolean> = toolBash20241022.strict()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Boolean> = toolBash20250124.strict()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Boolean> = codeExecutionTool20250522.strict()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Boolean> = codeExecutionTool20250825.strict()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Boolean> = codeExecutionTool20260120.strict()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Boolean> = codeExecutionTool20260521.strict()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Boolean> = toolComputerUse20241022.strict()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Boolean> = memoryTool20250818.strict()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Boolean> = toolComputerUse20250124.strict()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Boolean> = toolTextEditor20241022.strict()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Boolean> = toolComputerUse20251124.strict()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Boolean> = toolTextEditor20250124.strict()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Boolean> = toolTextEditor20250429.strict()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Boolean> = toolTextEditor20250728.strict()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Boolean> = webSearchTool20250305.strict()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Boolean> = webFetchTool20250910.strict()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Boolean> = webSearchTool20260209.strict()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Boolean> = webFetchTool20260209.strict()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Boolean> = webFetchTool20260309.strict()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Boolean> = webSearchTool20260318.strict()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Boolean> = webFetchTool20260318.strict()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Boolean> = advisorTool20260301.strict()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Boolean> = toolSearchToolBm25_20251119.strict()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Boolean> = toolSearchToolRegex20251119.strict()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Boolean> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("strict").asKnown()
            }
        )

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        accept(
            object : Visitor<Optional<BetaCacheControlEphemeral>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<BetaCacheControlEphemeral> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<BetaCacheControlEphemeral> = toolBash20241022.cacheControl()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<BetaCacheControlEphemeral> = toolBash20250124.cacheControl()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<BetaCacheControlEphemeral> = codeExecutionTool20250522.cacheControl()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<BetaCacheControlEphemeral> = codeExecutionTool20250825.cacheControl()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<BetaCacheControlEphemeral> = codeExecutionTool20260120.cacheControl()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<BetaCacheControlEphemeral> = codeExecutionTool20260521.cacheControl()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<BetaCacheControlEphemeral> = browserToolset20260801.cacheControl()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<BetaCacheControlEphemeral> = toolComputerUse20241022.cacheControl()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<BetaCacheControlEphemeral> = memoryTool20250818.cacheControl()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<BetaCacheControlEphemeral> = toolComputerUse20250124.cacheControl()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<BetaCacheControlEphemeral> = toolTextEditor20241022.cacheControl()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<BetaCacheControlEphemeral> = toolComputerUse20251124.cacheControl()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<BetaCacheControlEphemeral> = computerToolset20260801.cacheControl()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<BetaCacheControlEphemeral> = toolTextEditor20250124.cacheControl()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<BetaCacheControlEphemeral> = toolTextEditor20250429.cacheControl()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<BetaCacheControlEphemeral> = toolTextEditor20250728.cacheControl()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<BetaCacheControlEphemeral> = webSearchTool20250305.cacheControl()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<BetaCacheControlEphemeral> = webFetchTool20250910.cacheControl()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<BetaCacheControlEphemeral> = webSearchTool20260209.cacheControl()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<BetaCacheControlEphemeral> = webFetchTool20260209.cacheControl()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<BetaCacheControlEphemeral> = webFetchTool20260309.cacheControl()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<BetaCacheControlEphemeral> = webSearchTool20260318.cacheControl()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<BetaCacheControlEphemeral> = webFetchTool20260318.cacheControl()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<BetaCacheControlEphemeral> = advisorTool20260301.cacheControl()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<BetaCacheControlEphemeral> = toolSearchToolBm25_20251119.cacheControl()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<BetaCacheControlEphemeral> = toolSearchToolRegex20251119.cacheControl()

                override fun visitMcpToolset(
                    mcpToolset: BetaMcpToolset
                ): Optional<BetaCacheControlEphemeral> = mcpToolset.cacheControl()

                override fun unknown(json: JsonValue?): Optional<BetaCacheControlEphemeral> =
                    json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
            }
        )

    fun displayHeightPx(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Long> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Long> = Optional.of(toolComputerUse20241022.displayHeightPx())

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Long> = Optional.of(toolComputerUse20250124.displayHeightPx())

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Long> = Optional.of(toolComputerUse20251124.displayHeightPx())

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Long> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Long> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("display_height_px").asKnown()
            }
        )

    fun displayWidthPx(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Long> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Long> = Optional.of(toolComputerUse20241022.displayWidthPx())

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Long> = Optional.of(toolComputerUse20250124.displayWidthPx())

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Long> = Optional.of(toolComputerUse20251124.displayWidthPx())

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Long> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Long> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("display_width_px").asKnown()
            }
        )

    fun displayNumber(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Long> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Long> = toolComputerUse20241022.displayNumber()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Long> = toolComputerUse20250124.displayNumber()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Long> = toolComputerUse20251124.displayNumber()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Long> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Long> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("display_number").asKnown()
            }
        )

    fun allowedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<List<String>> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<List<String>> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<List<String>> = webSearchTool20250305.allowedDomains()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<List<String>> = webFetchTool20250910.allowedDomains()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<List<String>> = webSearchTool20260209.allowedDomains()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<List<String>> = webFetchTool20260209.allowedDomains()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<List<String>> = webFetchTool20260309.allowedDomains()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<List<String>> = webSearchTool20260318.allowedDomains()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<List<String>> = webFetchTool20260318.allowedDomains()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<List<String>> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<List<String>> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<List<String>> =
                    json.getProperty<List<String>>("allowed_domains").asKnown()
            }
        )

    fun blockedDomains(): Optional<List<String>> =
        accept(
            object : Visitor<Optional<List<String>>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<List<String>> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<List<String>> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<List<String>> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<List<String>> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<List<String>> = webSearchTool20250305.blockedDomains()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<List<String>> = webFetchTool20250910.blockedDomains()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<List<String>> = webSearchTool20260209.blockedDomains()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<List<String>> = webFetchTool20260209.blockedDomains()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<List<String>> = webFetchTool20260309.blockedDomains()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<List<String>> = webSearchTool20260318.blockedDomains()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<List<String>> = webFetchTool20260318.blockedDomains()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<List<String>> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<List<String>> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<List<String>> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<List<String>> =
                    json.getProperty<List<String>>("blocked_domains").asKnown()
            }
        )

    fun maxUses(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Long> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Long> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Long> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Long> = webSearchTool20250305.maxUses()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Long> = webFetchTool20250910.maxUses()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Long> = webSearchTool20260209.maxUses()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Long> = webFetchTool20260209.maxUses()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Long> = webFetchTool20260309.maxUses()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Long> = webSearchTool20260318.maxUses()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Long> = webFetchTool20260318.maxUses()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Long> = advisorTool20260301.maxUses()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Long> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Long> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("max_uses").asKnown()
            }
        )

    fun userLocation(): Optional<BetaUserLocation> =
        accept(
            object : Visitor<Optional<BetaUserLocation>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<BetaUserLocation> = webSearchTool20250305.userLocation()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<BetaUserLocation> = webSearchTool20260209.userLocation()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<BetaUserLocation> = webSearchTool20260318.userLocation()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun visitMcpToolset(
                    mcpToolset: BetaMcpToolset
                ): Optional<BetaUserLocation> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<BetaUserLocation> =
                    json.getProperty<BetaUserLocation>("user_location").asKnown()
            }
        )

    fun citations(): Optional<BetaCitationsConfigParam> =
        accept(
            object : Visitor<Optional<BetaCitationsConfigParam>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<BetaCitationsConfigParam> = webFetchTool20250910.citations()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<BetaCitationsConfigParam> = webFetchTool20260209.citations()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<BetaCitationsConfigParam> = webFetchTool20260309.citations()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<BetaCitationsConfigParam> = webFetchTool20260318.citations()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun visitMcpToolset(
                    mcpToolset: BetaMcpToolset
                ): Optional<BetaCitationsConfigParam> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<BetaCitationsConfigParam> =
                    json.getProperty<BetaCitationsConfigParam>("citations").asKnown()
            }
        )

    fun maxContentTokens(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Long> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Long> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Long> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Long> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Long> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Long> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Long> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Long> = webFetchTool20250910.maxContentTokens()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Long> = webFetchTool20260209.maxContentTokens()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Long> = webFetchTool20260309.maxContentTokens()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Long> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Long> = webFetchTool20260318.maxContentTokens()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Long> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Long> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Long> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("max_content_tokens").asKnown()
            }
        )

    fun urlSources(): Optional<BetaWebFetchUrlSources> =
        accept(
            object : Visitor<Optional<BetaWebFetchUrlSources>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<BetaWebFetchUrlSources> = webFetchTool20250910.urlSources()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<BetaWebFetchUrlSources> = webFetchTool20260209.urlSources()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<BetaWebFetchUrlSources> = webFetchTool20260309.urlSources()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<BetaWebFetchUrlSources> = webFetchTool20260318.urlSources()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun visitMcpToolset(
                    mcpToolset: BetaMcpToolset
                ): Optional<BetaWebFetchUrlSources> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<BetaWebFetchUrlSources> =
                    json.getProperty<BetaWebFetchUrlSources>("url_sources").asKnown()
            }
        )

    fun useCache(): Optional<Boolean> =
        accept(
            object : Visitor<Optional<Boolean>> {
                override fun visitBetaResponseTool(
                    betaResponseTool: BetaResponseTool
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolBash20241022(
                    toolBash20241022: BetaToolBash20241022
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolBash20250124(
                    toolBash20250124: BetaToolBash20250124
                ): Optional<Boolean> = Optional.empty()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ): Optional<Boolean> = Optional.empty()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ): Optional<Boolean> = Optional.empty()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ): Optional<Boolean> = Optional.empty()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ): Optional<Boolean> = Optional.empty()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ): Optional<Boolean> = Optional.empty()

                override fun visitMemoryTool20250818(
                    memoryTool20250818: BetaMemoryTool20250818
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ): Optional<Boolean> = Optional.empty()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ): Optional<Boolean> = webFetchTool20260309.useCache()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ): Optional<Boolean> = Optional.empty()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ): Optional<Boolean> = webFetchTool20260318.useCache()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ): Optional<Boolean> = Optional.empty()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ): Optional<Boolean> = Optional.empty()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset): Optional<Boolean> =
                    Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Boolean> =
                    json.getProperty<Boolean>("use_cache").asKnown()
            }
        )

    /** A custom tool definition, as sent. */
    fun betaResponseTool(): Optional<BetaResponseTool> = Optional.ofNullable(betaResponseTool)

    fun toolBash20241022(): Optional<BetaToolBash20241022> = Optional.ofNullable(toolBash20241022)

    fun toolBash20250124(): Optional<BetaToolBash20250124> = Optional.ofNullable(toolBash20250124)

    fun codeExecutionTool20250522(): Optional<BetaCodeExecutionTool20250522> =
        Optional.ofNullable(codeExecutionTool20250522)

    fun codeExecutionTool20250825(): Optional<BetaCodeExecutionTool20250825> =
        Optional.ofNullable(codeExecutionTool20250825)

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun codeExecutionTool20260120(): Optional<BetaCodeExecutionTool20260120> =
        Optional.ofNullable(codeExecutionTool20260120)

    /** Code execution tool with REPL state persistence. */
    fun codeExecutionTool20260521(): Optional<BetaCodeExecutionTool20260521> =
        Optional.ofNullable(codeExecutionTool20260521)

    /**
     * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
     * browser tool family. The model is served the family's tool with any members disabled via
     * ``configs`` removed from its schema.
     */
    fun browserToolset20260801(): Optional<BetaBrowserToolset20260801> =
        Optional.ofNullable(browserToolset20260801)

    fun toolComputerUse20241022(): Optional<BetaToolComputerUse20241022> =
        Optional.ofNullable(toolComputerUse20241022)

    fun memoryTool20250818(): Optional<BetaMemoryTool20250818> =
        Optional.ofNullable(memoryTool20250818)

    fun toolComputerUse20250124(): Optional<BetaToolComputerUse20250124> =
        Optional.ofNullable(toolComputerUse20250124)

    fun toolTextEditor20241022(): Optional<BetaToolTextEditor20241022> =
        Optional.ofNullable(toolTextEditor20241022)

    fun toolComputerUse20251124(): Optional<BetaToolComputerUse20251124> =
        Optional.ofNullable(toolComputerUse20251124)

    /**
     * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
     * computer tool family. The model is served the family's tool with any members disabled via
     * ``configs`` removed from its schema. Every member is enabled by default, zoom included. The
     * single-tool options ``display_number`` and ``enable_zoom`` are not fields of a toolset entry
     * — it carries only ``type``, ``configs``, and ``cache_control``; zoom is controlled via
     * ``configs.zoom.enabled``.
     */
    fun computerToolset20260801(): Optional<BetaComputerToolset20260801> =
        Optional.ofNullable(computerToolset20260801)

    fun toolTextEditor20250124(): Optional<BetaToolTextEditor20250124> =
        Optional.ofNullable(toolTextEditor20250124)

    fun toolTextEditor20250429(): Optional<BetaToolTextEditor20250429> =
        Optional.ofNullable(toolTextEditor20250429)

    fun toolTextEditor20250728(): Optional<BetaToolTextEditor20250728> =
        Optional.ofNullable(toolTextEditor20250728)

    fun webSearchTool20250305(): Optional<BetaWebSearchTool20250305> =
        Optional.ofNullable(webSearchTool20250305)

    fun webFetchTool20250910(): Optional<BetaWebFetchTool20250910> =
        Optional.ofNullable(webFetchTool20250910)

    fun webSearchTool20260209(): Optional<BetaWebSearchTool20260209> =
        Optional.ofNullable(webSearchTool20260209)

    fun webFetchTool20260209(): Optional<BetaWebFetchTool20260209> =
        Optional.ofNullable(webFetchTool20260209)

    /** Web fetch tool with use_cache parameter for bypassing cached content. */
    fun webFetchTool20260309(): Optional<BetaWebFetchTool20260309> =
        Optional.ofNullable(webFetchTool20260309)

    fun webSearchTool20260318(): Optional<BetaWebSearchTool20260318> =
        Optional.ofNullable(webSearchTool20260318)

    fun webFetchTool20260318(): Optional<BetaWebFetchTool20260318> =
        Optional.ofNullable(webFetchTool20260318)

    fun advisorTool20260301(): Optional<BetaAdvisorTool20260301> =
        Optional.ofNullable(advisorTool20260301)

    fun toolSearchToolBm25_20251119(): Optional<BetaToolSearchToolBm25_20251119> =
        Optional.ofNullable(toolSearchToolBm25_20251119)

    fun toolSearchToolRegex20251119(): Optional<BetaToolSearchToolRegex20251119> =
        Optional.ofNullable(toolSearchToolRegex20251119)

    /**
     * Configuration for a group of tools from an MCP server.
     *
     * Allows configuring enabled status and defer_loading for all tools from an MCP server, with
     * optional per-tool overrides.
     */
    fun mcpToolset(): Optional<BetaMcpToolset> = Optional.ofNullable(mcpToolset)

    fun isBetaResponseTool(): Boolean = betaResponseTool != null

    fun isToolBash20241022(): Boolean = toolBash20241022 != null

    fun isToolBash20250124(): Boolean = toolBash20250124 != null

    fun isCodeExecutionTool20250522(): Boolean = codeExecutionTool20250522 != null

    fun isCodeExecutionTool20250825(): Boolean = codeExecutionTool20250825 != null

    fun isCodeExecutionTool20260120(): Boolean = codeExecutionTool20260120 != null

    fun isCodeExecutionTool20260521(): Boolean = codeExecutionTool20260521 != null

    fun isBrowserToolset20260801(): Boolean = browserToolset20260801 != null

    fun isToolComputerUse20241022(): Boolean = toolComputerUse20241022 != null

    fun isMemoryTool20250818(): Boolean = memoryTool20250818 != null

    fun isToolComputerUse20250124(): Boolean = toolComputerUse20250124 != null

    fun isToolTextEditor20241022(): Boolean = toolTextEditor20241022 != null

    fun isToolComputerUse20251124(): Boolean = toolComputerUse20251124 != null

    fun isComputerToolset20260801(): Boolean = computerToolset20260801 != null

    fun isToolTextEditor20250124(): Boolean = toolTextEditor20250124 != null

    fun isToolTextEditor20250429(): Boolean = toolTextEditor20250429 != null

    fun isToolTextEditor20250728(): Boolean = toolTextEditor20250728 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun isWebFetchTool20250910(): Boolean = webFetchTool20250910 != null

    fun isWebSearchTool20260209(): Boolean = webSearchTool20260209 != null

    fun isWebFetchTool20260209(): Boolean = webFetchTool20260209 != null

    fun isWebFetchTool20260309(): Boolean = webFetchTool20260309 != null

    fun isWebSearchTool20260318(): Boolean = webSearchTool20260318 != null

    fun isWebFetchTool20260318(): Boolean = webFetchTool20260318 != null

    fun isAdvisorTool20260301(): Boolean = advisorTool20260301 != null

    fun isToolSearchToolBm25_20251119(): Boolean = toolSearchToolBm25_20251119 != null

    fun isToolSearchToolRegex20251119(): Boolean = toolSearchToolRegex20251119 != null

    fun isMcpToolset(): Boolean = mcpToolset != null

    /** A custom tool definition, as sent. */
    fun asBetaResponseTool(): BetaResponseTool = betaResponseTool.getOrThrow("betaResponseTool")

    fun asToolBash20241022(): BetaToolBash20241022 = toolBash20241022.getOrThrow("toolBash20241022")

    fun asToolBash20250124(): BetaToolBash20250124 = toolBash20250124.getOrThrow("toolBash20250124")

    fun asCodeExecutionTool20250522(): BetaCodeExecutionTool20250522 =
        codeExecutionTool20250522.getOrThrow("codeExecutionTool20250522")

    fun asCodeExecutionTool20250825(): BetaCodeExecutionTool20250825 =
        codeExecutionTool20250825.getOrThrow("codeExecutionTool20250825")

    /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
    fun asCodeExecutionTool20260120(): BetaCodeExecutionTool20260120 =
        codeExecutionTool20260120.getOrThrow("codeExecutionTool20260120")

    /** Code execution tool with REPL state persistence. */
    fun asCodeExecutionTool20260521(): BetaCodeExecutionTool20260521 =
        codeExecutionTool20260521.getOrThrow("codeExecutionTool20260521")

    /**
     * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
     * browser tool family. The model is served the family's tool with any members disabled via
     * ``configs`` removed from its schema.
     */
    fun asBrowserToolset20260801(): BetaBrowserToolset20260801 =
        browserToolset20260801.getOrThrow("browserToolset20260801")

    fun asToolComputerUse20241022(): BetaToolComputerUse20241022 =
        toolComputerUse20241022.getOrThrow("toolComputerUse20241022")

    fun asMemoryTool20250818(): BetaMemoryTool20250818 =
        memoryTool20250818.getOrThrow("memoryTool20250818")

    fun asToolComputerUse20250124(): BetaToolComputerUse20250124 =
        toolComputerUse20250124.getOrThrow("toolComputerUse20250124")

    fun asToolTextEditor20241022(): BetaToolTextEditor20241022 =
        toolTextEditor20241022.getOrThrow("toolTextEditor20241022")

    fun asToolComputerUse20251124(): BetaToolComputerUse20251124 =
        toolComputerUse20251124.getOrThrow("toolComputerUse20251124")

    /**
     * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
     * computer tool family. The model is served the family's tool with any members disabled via
     * ``configs`` removed from its schema. Every member is enabled by default, zoom included. The
     * single-tool options ``display_number`` and ``enable_zoom`` are not fields of a toolset entry
     * — it carries only ``type``, ``configs``, and ``cache_control``; zoom is controlled via
     * ``configs.zoom.enabled``.
     */
    fun asComputerToolset20260801(): BetaComputerToolset20260801 =
        computerToolset20260801.getOrThrow("computerToolset20260801")

    fun asToolTextEditor20250124(): BetaToolTextEditor20250124 =
        toolTextEditor20250124.getOrThrow("toolTextEditor20250124")

    fun asToolTextEditor20250429(): BetaToolTextEditor20250429 =
        toolTextEditor20250429.getOrThrow("toolTextEditor20250429")

    fun asToolTextEditor20250728(): BetaToolTextEditor20250728 =
        toolTextEditor20250728.getOrThrow("toolTextEditor20250728")

    fun asWebSearchTool20250305(): BetaWebSearchTool20250305 =
        webSearchTool20250305.getOrThrow("webSearchTool20250305")

    fun asWebFetchTool20250910(): BetaWebFetchTool20250910 =
        webFetchTool20250910.getOrThrow("webFetchTool20250910")

    fun asWebSearchTool20260209(): BetaWebSearchTool20260209 =
        webSearchTool20260209.getOrThrow("webSearchTool20260209")

    fun asWebFetchTool20260209(): BetaWebFetchTool20260209 =
        webFetchTool20260209.getOrThrow("webFetchTool20260209")

    /** Web fetch tool with use_cache parameter for bypassing cached content. */
    fun asWebFetchTool20260309(): BetaWebFetchTool20260309 =
        webFetchTool20260309.getOrThrow("webFetchTool20260309")

    fun asWebSearchTool20260318(): BetaWebSearchTool20260318 =
        webSearchTool20260318.getOrThrow("webSearchTool20260318")

    fun asWebFetchTool20260318(): BetaWebFetchTool20260318 =
        webFetchTool20260318.getOrThrow("webFetchTool20260318")

    fun asAdvisorTool20260301(): BetaAdvisorTool20260301 =
        advisorTool20260301.getOrThrow("advisorTool20260301")

    fun asToolSearchToolBm25_20251119(): BetaToolSearchToolBm25_20251119 =
        toolSearchToolBm25_20251119.getOrThrow("toolSearchToolBm25_20251119")

    fun asToolSearchToolRegex20251119(): BetaToolSearchToolRegex20251119 =
        toolSearchToolRegex20251119.getOrThrow("toolSearchToolRegex20251119")

    /**
     * Configuration for a group of tools from an MCP server.
     *
     * Allows configuring enabled status and defer_loading for all tools from an MCP server, with
     * optional per-tool overrides.
     */
    fun asMcpToolset(): BetaMcpToolset = mcpToolset.getOrThrow("mcpToolset")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaResponseToolUnion.accept(new BetaResponseToolUnion.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBetaResponseTool(BetaResponseTool betaResponseTool) {
     *         return Optional.of(betaResponseTool.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            betaResponseTool != null -> visitor.visitBetaResponseTool(betaResponseTool)
            toolBash20241022 != null -> visitor.visitToolBash20241022(toolBash20241022)
            toolBash20250124 != null -> visitor.visitToolBash20250124(toolBash20250124)
            codeExecutionTool20250522 != null ->
                visitor.visitCodeExecutionTool20250522(codeExecutionTool20250522)
            codeExecutionTool20250825 != null ->
                visitor.visitCodeExecutionTool20250825(codeExecutionTool20250825)
            codeExecutionTool20260120 != null ->
                visitor.visitCodeExecutionTool20260120(codeExecutionTool20260120)
            codeExecutionTool20260521 != null ->
                visitor.visitCodeExecutionTool20260521(codeExecutionTool20260521)
            browserToolset20260801 != null ->
                visitor.visitBrowserToolset20260801(browserToolset20260801)
            toolComputerUse20241022 != null ->
                visitor.visitToolComputerUse20241022(toolComputerUse20241022)
            memoryTool20250818 != null -> visitor.visitMemoryTool20250818(memoryTool20250818)
            toolComputerUse20250124 != null ->
                visitor.visitToolComputerUse20250124(toolComputerUse20250124)
            toolTextEditor20241022 != null ->
                visitor.visitToolTextEditor20241022(toolTextEditor20241022)
            toolComputerUse20251124 != null ->
                visitor.visitToolComputerUse20251124(toolComputerUse20251124)
            computerToolset20260801 != null ->
                visitor.visitComputerToolset20260801(computerToolset20260801)
            toolTextEditor20250124 != null ->
                visitor.visitToolTextEditor20250124(toolTextEditor20250124)
            toolTextEditor20250429 != null ->
                visitor.visitToolTextEditor20250429(toolTextEditor20250429)
            toolTextEditor20250728 != null ->
                visitor.visitToolTextEditor20250728(toolTextEditor20250728)
            webSearchTool20250305 != null ->
                visitor.visitWebSearchTool20250305(webSearchTool20250305)
            webFetchTool20250910 != null -> visitor.visitWebFetchTool20250910(webFetchTool20250910)
            webSearchTool20260209 != null ->
                visitor.visitWebSearchTool20260209(webSearchTool20260209)
            webFetchTool20260209 != null -> visitor.visitWebFetchTool20260209(webFetchTool20260209)
            webFetchTool20260309 != null -> visitor.visitWebFetchTool20260309(webFetchTool20260309)
            webSearchTool20260318 != null ->
                visitor.visitWebSearchTool20260318(webSearchTool20260318)
            webFetchTool20260318 != null -> visitor.visitWebFetchTool20260318(webFetchTool20260318)
            advisorTool20260301 != null -> visitor.visitAdvisorTool20260301(advisorTool20260301)
            toolSearchToolBm25_20251119 != null ->
                visitor.visitToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)
            toolSearchToolRegex20251119 != null ->
                visitor.visitToolSearchToolRegex20251119(toolSearchToolRegex20251119)
            mcpToolset != null -> visitor.visitMcpToolset(mcpToolset)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaResponseToolUnion = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitBetaResponseTool(betaResponseTool: BetaResponseTool) {
                    betaResponseTool.validate()
                }

                override fun visitToolBash20241022(toolBash20241022: BetaToolBash20241022) {
                    toolBash20241022.validate()
                }

                override fun visitToolBash20250124(toolBash20250124: BetaToolBash20250124) {
                    toolBash20250124.validate()
                }

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ) {
                    codeExecutionTool20250522.validate()
                }

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ) {
                    codeExecutionTool20250825.validate()
                }

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ) {
                    codeExecutionTool20260120.validate()
                }

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ) {
                    codeExecutionTool20260521.validate()
                }

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ) {
                    browserToolset20260801.validate()
                }

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ) {
                    toolComputerUse20241022.validate()
                }

                override fun visitMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818) {
                    memoryTool20250818.validate()
                }

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ) {
                    toolComputerUse20250124.validate()
                }

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ) {
                    toolTextEditor20241022.validate()
                }

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ) {
                    toolComputerUse20251124.validate()
                }

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ) {
                    computerToolset20260801.validate()
                }

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ) {
                    toolTextEditor20250124.validate()
                }

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ) {
                    toolTextEditor20250429.validate()
                }

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ) {
                    toolTextEditor20250728.validate()
                }

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ) {
                    webSearchTool20250305.validate()
                }

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ) {
                    webFetchTool20250910.validate()
                }

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ) {
                    webSearchTool20260209.validate()
                }

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ) {
                    webFetchTool20260209.validate()
                }

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ) {
                    webFetchTool20260309.validate()
                }

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ) {
                    webSearchTool20260318.validate()
                }

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ) {
                    webFetchTool20260318.validate()
                }

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ) {
                    advisorTool20260301.validate()
                }

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ) {
                    toolSearchToolBm25_20251119.validate()
                }

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ) {
                    toolSearchToolRegex20251119.validate()
                }

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset) {
                    mcpToolset.validate()
                }
            }
        )
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitBetaResponseTool(betaResponseTool: BetaResponseTool) =
                    betaResponseTool.validity()

                override fun visitToolBash20241022(toolBash20241022: BetaToolBash20241022) =
                    toolBash20241022.validity()

                override fun visitToolBash20250124(toolBash20250124: BetaToolBash20250124) =
                    toolBash20250124.validity()

                override fun visitCodeExecutionTool20250522(
                    codeExecutionTool20250522: BetaCodeExecutionTool20250522
                ) = codeExecutionTool20250522.validity()

                override fun visitCodeExecutionTool20250825(
                    codeExecutionTool20250825: BetaCodeExecutionTool20250825
                ) = codeExecutionTool20250825.validity()

                override fun visitCodeExecutionTool20260120(
                    codeExecutionTool20260120: BetaCodeExecutionTool20260120
                ) = codeExecutionTool20260120.validity()

                override fun visitCodeExecutionTool20260521(
                    codeExecutionTool20260521: BetaCodeExecutionTool20260521
                ) = codeExecutionTool20260521.validity()

                override fun visitBrowserToolset20260801(
                    browserToolset20260801: BetaBrowserToolset20260801
                ) = browserToolset20260801.validity()

                override fun visitToolComputerUse20241022(
                    toolComputerUse20241022: BetaToolComputerUse20241022
                ) = toolComputerUse20241022.validity()

                override fun visitMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818) =
                    memoryTool20250818.validity()

                override fun visitToolComputerUse20250124(
                    toolComputerUse20250124: BetaToolComputerUse20250124
                ) = toolComputerUse20250124.validity()

                override fun visitToolTextEditor20241022(
                    toolTextEditor20241022: BetaToolTextEditor20241022
                ) = toolTextEditor20241022.validity()

                override fun visitToolComputerUse20251124(
                    toolComputerUse20251124: BetaToolComputerUse20251124
                ) = toolComputerUse20251124.validity()

                override fun visitComputerToolset20260801(
                    computerToolset20260801: BetaComputerToolset20260801
                ) = computerToolset20260801.validity()

                override fun visitToolTextEditor20250124(
                    toolTextEditor20250124: BetaToolTextEditor20250124
                ) = toolTextEditor20250124.validity()

                override fun visitToolTextEditor20250429(
                    toolTextEditor20250429: BetaToolTextEditor20250429
                ) = toolTextEditor20250429.validity()

                override fun visitToolTextEditor20250728(
                    toolTextEditor20250728: BetaToolTextEditor20250728
                ) = toolTextEditor20250728.validity()

                override fun visitWebSearchTool20250305(
                    webSearchTool20250305: BetaWebSearchTool20250305
                ) = webSearchTool20250305.validity()

                override fun visitWebFetchTool20250910(
                    webFetchTool20250910: BetaWebFetchTool20250910
                ) = webFetchTool20250910.validity()

                override fun visitWebSearchTool20260209(
                    webSearchTool20260209: BetaWebSearchTool20260209
                ) = webSearchTool20260209.validity()

                override fun visitWebFetchTool20260209(
                    webFetchTool20260209: BetaWebFetchTool20260209
                ) = webFetchTool20260209.validity()

                override fun visitWebFetchTool20260309(
                    webFetchTool20260309: BetaWebFetchTool20260309
                ) = webFetchTool20260309.validity()

                override fun visitWebSearchTool20260318(
                    webSearchTool20260318: BetaWebSearchTool20260318
                ) = webSearchTool20260318.validity()

                override fun visitWebFetchTool20260318(
                    webFetchTool20260318: BetaWebFetchTool20260318
                ) = webFetchTool20260318.validity()

                override fun visitAdvisorTool20260301(
                    advisorTool20260301: BetaAdvisorTool20260301
                ) = advisorTool20260301.validity()

                override fun visitToolSearchToolBm25_20251119(
                    toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
                ) = toolSearchToolBm25_20251119.validity()

                override fun visitToolSearchToolRegex20251119(
                    toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
                ) = toolSearchToolRegex20251119.validity()

                override fun visitMcpToolset(mcpToolset: BetaMcpToolset) = mcpToolset.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaResponseToolUnion &&
            betaResponseTool == other.betaResponseTool &&
            toolBash20241022 == other.toolBash20241022 &&
            toolBash20250124 == other.toolBash20250124 &&
            codeExecutionTool20250522 == other.codeExecutionTool20250522 &&
            codeExecutionTool20250825 == other.codeExecutionTool20250825 &&
            codeExecutionTool20260120 == other.codeExecutionTool20260120 &&
            codeExecutionTool20260521 == other.codeExecutionTool20260521 &&
            browserToolset20260801 == other.browserToolset20260801 &&
            toolComputerUse20241022 == other.toolComputerUse20241022 &&
            memoryTool20250818 == other.memoryTool20250818 &&
            toolComputerUse20250124 == other.toolComputerUse20250124 &&
            toolTextEditor20241022 == other.toolTextEditor20241022 &&
            toolComputerUse20251124 == other.toolComputerUse20251124 &&
            computerToolset20260801 == other.computerToolset20260801 &&
            toolTextEditor20250124 == other.toolTextEditor20250124 &&
            toolTextEditor20250429 == other.toolTextEditor20250429 &&
            toolTextEditor20250728 == other.toolTextEditor20250728 &&
            webSearchTool20250305 == other.webSearchTool20250305 &&
            webFetchTool20250910 == other.webFetchTool20250910 &&
            webSearchTool20260209 == other.webSearchTool20260209 &&
            webFetchTool20260209 == other.webFetchTool20260209 &&
            webFetchTool20260309 == other.webFetchTool20260309 &&
            webSearchTool20260318 == other.webSearchTool20260318 &&
            webFetchTool20260318 == other.webFetchTool20260318 &&
            advisorTool20260301 == other.advisorTool20260301 &&
            toolSearchToolBm25_20251119 == other.toolSearchToolBm25_20251119 &&
            toolSearchToolRegex20251119 == other.toolSearchToolRegex20251119 &&
            mcpToolset == other.mcpToolset
    }

    override fun hashCode(): Int =
        Objects.hash(
            betaResponseTool,
            toolBash20241022,
            toolBash20250124,
            codeExecutionTool20250522,
            codeExecutionTool20250825,
            codeExecutionTool20260120,
            codeExecutionTool20260521,
            browserToolset20260801,
            toolComputerUse20241022,
            memoryTool20250818,
            toolComputerUse20250124,
            toolTextEditor20241022,
            toolComputerUse20251124,
            computerToolset20260801,
            toolTextEditor20250124,
            toolTextEditor20250429,
            toolTextEditor20250728,
            webSearchTool20250305,
            webFetchTool20250910,
            webSearchTool20260209,
            webFetchTool20260209,
            webFetchTool20260309,
            webSearchTool20260318,
            webFetchTool20260318,
            advisorTool20260301,
            toolSearchToolBm25_20251119,
            toolSearchToolRegex20251119,
            mcpToolset,
        )

    override fun toString(): String =
        when {
            betaResponseTool != null -> "BetaResponseToolUnion{betaResponseTool=$betaResponseTool}"
            toolBash20241022 != null -> "BetaResponseToolUnion{toolBash20241022=$toolBash20241022}"
            toolBash20250124 != null -> "BetaResponseToolUnion{toolBash20250124=$toolBash20250124}"
            codeExecutionTool20250522 != null ->
                "BetaResponseToolUnion{codeExecutionTool20250522=$codeExecutionTool20250522}"
            codeExecutionTool20250825 != null ->
                "BetaResponseToolUnion{codeExecutionTool20250825=$codeExecutionTool20250825}"
            codeExecutionTool20260120 != null ->
                "BetaResponseToolUnion{codeExecutionTool20260120=$codeExecutionTool20260120}"
            codeExecutionTool20260521 != null ->
                "BetaResponseToolUnion{codeExecutionTool20260521=$codeExecutionTool20260521}"
            browserToolset20260801 != null ->
                "BetaResponseToolUnion{browserToolset20260801=$browserToolset20260801}"
            toolComputerUse20241022 != null ->
                "BetaResponseToolUnion{toolComputerUse20241022=$toolComputerUse20241022}"
            memoryTool20250818 != null ->
                "BetaResponseToolUnion{memoryTool20250818=$memoryTool20250818}"
            toolComputerUse20250124 != null ->
                "BetaResponseToolUnion{toolComputerUse20250124=$toolComputerUse20250124}"
            toolTextEditor20241022 != null ->
                "BetaResponseToolUnion{toolTextEditor20241022=$toolTextEditor20241022}"
            toolComputerUse20251124 != null ->
                "BetaResponseToolUnion{toolComputerUse20251124=$toolComputerUse20251124}"
            computerToolset20260801 != null ->
                "BetaResponseToolUnion{computerToolset20260801=$computerToolset20260801}"
            toolTextEditor20250124 != null ->
                "BetaResponseToolUnion{toolTextEditor20250124=$toolTextEditor20250124}"
            toolTextEditor20250429 != null ->
                "BetaResponseToolUnion{toolTextEditor20250429=$toolTextEditor20250429}"
            toolTextEditor20250728 != null ->
                "BetaResponseToolUnion{toolTextEditor20250728=$toolTextEditor20250728}"
            webSearchTool20250305 != null ->
                "BetaResponseToolUnion{webSearchTool20250305=$webSearchTool20250305}"
            webFetchTool20250910 != null ->
                "BetaResponseToolUnion{webFetchTool20250910=$webFetchTool20250910}"
            webSearchTool20260209 != null ->
                "BetaResponseToolUnion{webSearchTool20260209=$webSearchTool20260209}"
            webFetchTool20260209 != null ->
                "BetaResponseToolUnion{webFetchTool20260209=$webFetchTool20260209}"
            webFetchTool20260309 != null ->
                "BetaResponseToolUnion{webFetchTool20260309=$webFetchTool20260309}"
            webSearchTool20260318 != null ->
                "BetaResponseToolUnion{webSearchTool20260318=$webSearchTool20260318}"
            webFetchTool20260318 != null ->
                "BetaResponseToolUnion{webFetchTool20260318=$webFetchTool20260318}"
            advisorTool20260301 != null ->
                "BetaResponseToolUnion{advisorTool20260301=$advisorTool20260301}"
            toolSearchToolBm25_20251119 != null ->
                "BetaResponseToolUnion{toolSearchToolBm25_20251119=$toolSearchToolBm25_20251119}"
            toolSearchToolRegex20251119 != null ->
                "BetaResponseToolUnion{toolSearchToolRegex20251119=$toolSearchToolRegex20251119}"
            mcpToolset != null -> "BetaResponseToolUnion{mcpToolset=$mcpToolset}"
            _json != null -> "BetaResponseToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaResponseToolUnion")
        }

    companion object {

        /** A custom tool definition, as sent. */
        @JvmStatic
        fun ofBetaResponseTool(betaResponseTool: BetaResponseTool) =
            BetaResponseToolUnion(betaResponseTool = betaResponseTool)

        @JvmStatic
        fun ofToolBash20241022(toolBash20241022: BetaToolBash20241022) =
            BetaResponseToolUnion(toolBash20241022 = toolBash20241022)

        @JvmStatic
        fun ofToolBash20250124(toolBash20250124: BetaToolBash20250124) =
            BetaResponseToolUnion(toolBash20250124 = toolBash20250124)

        @JvmStatic
        fun ofCodeExecutionTool20250522(codeExecutionTool20250522: BetaCodeExecutionTool20250522) =
            BetaResponseToolUnion(codeExecutionTool20250522 = codeExecutionTool20250522)

        @JvmStatic
        fun ofCodeExecutionTool20250825(codeExecutionTool20250825: BetaCodeExecutionTool20250825) =
            BetaResponseToolUnion(codeExecutionTool20250825 = codeExecutionTool20250825)

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        @JvmStatic
        fun ofCodeExecutionTool20260120(codeExecutionTool20260120: BetaCodeExecutionTool20260120) =
            BetaResponseToolUnion(codeExecutionTool20260120 = codeExecutionTool20260120)

        /** Code execution tool with REPL state persistence. */
        @JvmStatic
        fun ofCodeExecutionTool20260521(codeExecutionTool20260521: BetaCodeExecutionTool20260521) =
            BetaResponseToolUnion(codeExecutionTool20260521 = codeExecutionTool20260521)

        /**
         * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * browser tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema.
         */
        @JvmStatic
        fun ofBrowserToolset20260801(browserToolset20260801: BetaBrowserToolset20260801) =
            BetaResponseToolUnion(browserToolset20260801 = browserToolset20260801)

        @JvmStatic
        fun ofToolComputerUse20241022(toolComputerUse20241022: BetaToolComputerUse20241022) =
            BetaResponseToolUnion(toolComputerUse20241022 = toolComputerUse20241022)

        @JvmStatic
        fun ofMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818) =
            BetaResponseToolUnion(memoryTool20250818 = memoryTool20250818)

        @JvmStatic
        fun ofToolComputerUse20250124(toolComputerUse20250124: BetaToolComputerUse20250124) =
            BetaResponseToolUnion(toolComputerUse20250124 = toolComputerUse20250124)

        @JvmStatic
        fun ofToolTextEditor20241022(toolTextEditor20241022: BetaToolTextEditor20241022) =
            BetaResponseToolUnion(toolTextEditor20241022 = toolTextEditor20241022)

        @JvmStatic
        fun ofToolComputerUse20251124(toolComputerUse20251124: BetaToolComputerUse20251124) =
            BetaResponseToolUnion(toolComputerUse20251124 = toolComputerUse20251124)

        /**
         * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * computer tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema. Every member is enabled by default, zoom included.
         * The single-tool options ``display_number`` and ``enable_zoom`` are not fields of a
         * toolset entry — it carries only ``type``, ``configs``, and ``cache_control``; zoom is
         * controlled via ``configs.zoom.enabled``.
         */
        @JvmStatic
        fun ofComputerToolset20260801(computerToolset20260801: BetaComputerToolset20260801) =
            BetaResponseToolUnion(computerToolset20260801 = computerToolset20260801)

        @JvmStatic
        fun ofToolTextEditor20250124(toolTextEditor20250124: BetaToolTextEditor20250124) =
            BetaResponseToolUnion(toolTextEditor20250124 = toolTextEditor20250124)

        @JvmStatic
        fun ofToolTextEditor20250429(toolTextEditor20250429: BetaToolTextEditor20250429) =
            BetaResponseToolUnion(toolTextEditor20250429 = toolTextEditor20250429)

        @JvmStatic
        fun ofToolTextEditor20250728(toolTextEditor20250728: BetaToolTextEditor20250728) =
            BetaResponseToolUnion(toolTextEditor20250728 = toolTextEditor20250728)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: BetaWebSearchTool20250305) =
            BetaResponseToolUnion(webSearchTool20250305 = webSearchTool20250305)

        @JvmStatic
        fun ofWebFetchTool20250910(webFetchTool20250910: BetaWebFetchTool20250910) =
            BetaResponseToolUnion(webFetchTool20250910 = webFetchTool20250910)

        @JvmStatic
        fun ofWebSearchTool20260209(webSearchTool20260209: BetaWebSearchTool20260209) =
            BetaResponseToolUnion(webSearchTool20260209 = webSearchTool20260209)

        @JvmStatic
        fun ofWebFetchTool20260209(webFetchTool20260209: BetaWebFetchTool20260209) =
            BetaResponseToolUnion(webFetchTool20260209 = webFetchTool20260209)

        /** Web fetch tool with use_cache parameter for bypassing cached content. */
        @JvmStatic
        fun ofWebFetchTool20260309(webFetchTool20260309: BetaWebFetchTool20260309) =
            BetaResponseToolUnion(webFetchTool20260309 = webFetchTool20260309)

        @JvmStatic
        fun ofWebSearchTool20260318(webSearchTool20260318: BetaWebSearchTool20260318) =
            BetaResponseToolUnion(webSearchTool20260318 = webSearchTool20260318)

        @JvmStatic
        fun ofWebFetchTool20260318(webFetchTool20260318: BetaWebFetchTool20260318) =
            BetaResponseToolUnion(webFetchTool20260318 = webFetchTool20260318)

        @JvmStatic
        fun ofAdvisorTool20260301(advisorTool20260301: BetaAdvisorTool20260301) =
            BetaResponseToolUnion(advisorTool20260301 = advisorTool20260301)

        /**
         * Returns an immutable instance of [BetaResponseToolUnion] whose [ofAdvisorTool20260301]
         * variant is built from the given required [model].
         */
        @JvmStatic
        fun ofAdvisorTool20260301(model: Model) =
            ofAdvisorTool20260301(BetaAdvisorTool20260301.of(model))

        @JvmStatic
        fun ofToolSearchToolBm25_20251119(
            toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
        ) = BetaResponseToolUnion(toolSearchToolBm25_20251119 = toolSearchToolBm25_20251119)

        /**
         * Returns an immutable instance of [BetaResponseToolUnion] whose
         * [ofToolSearchToolBm25_20251119] variant is built from the given required [type].
         */
        @JvmStatic
        fun ofToolSearchToolBm25_20251119(type: BetaToolSearchToolBm25_20251119.Type) =
            ofToolSearchToolBm25_20251119(BetaToolSearchToolBm25_20251119.of(type))

        @JvmStatic
        fun ofToolSearchToolRegex20251119(
            toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
        ) = BetaResponseToolUnion(toolSearchToolRegex20251119 = toolSearchToolRegex20251119)

        /**
         * Returns an immutable instance of [BetaResponseToolUnion] whose
         * [ofToolSearchToolRegex20251119] variant is built from the given required [type].
         */
        @JvmStatic
        fun ofToolSearchToolRegex20251119(type: BetaToolSearchToolRegex20251119.Type) =
            ofToolSearchToolRegex20251119(BetaToolSearchToolRegex20251119.of(type))

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        @JvmStatic
        fun ofMcpToolset(mcpToolset: BetaMcpToolset) =
            BetaResponseToolUnion(mcpToolset = mcpToolset)

        /**
         * Returns an immutable instance of [BetaResponseToolUnion] whose [ofMcpToolset] variant is
         * built from the given required [mcpServerName].
         */
        @JvmStatic
        fun ofMcpToolset(mcpServerName: String) = ofMcpToolset(BetaMcpToolset.of(mcpServerName))
    }

    /**
     * An interface that defines how to map each variant of [BetaResponseToolUnion] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /** A custom tool definition, as sent. */
        fun visitBetaResponseTool(betaResponseTool: BetaResponseTool): T

        fun visitToolBash20241022(toolBash20241022: BetaToolBash20241022): T

        fun visitToolBash20250124(toolBash20250124: BetaToolBash20250124): T

        fun visitCodeExecutionTool20250522(
            codeExecutionTool20250522: BetaCodeExecutionTool20250522
        ): T

        fun visitCodeExecutionTool20250825(
            codeExecutionTool20250825: BetaCodeExecutionTool20250825
        ): T

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        fun visitCodeExecutionTool20260120(
            codeExecutionTool20260120: BetaCodeExecutionTool20260120
        ): T

        /** Code execution tool with REPL state persistence. */
        fun visitCodeExecutionTool20260521(
            codeExecutionTool20260521: BetaCodeExecutionTool20260521
        ): T

        /**
         * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * browser tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema.
         */
        fun visitBrowserToolset20260801(browserToolset20260801: BetaBrowserToolset20260801): T

        fun visitToolComputerUse20241022(toolComputerUse20241022: BetaToolComputerUse20241022): T

        fun visitMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818): T

        fun visitToolComputerUse20250124(toolComputerUse20250124: BetaToolComputerUse20250124): T

        fun visitToolTextEditor20241022(toolTextEditor20241022: BetaToolTextEditor20241022): T

        fun visitToolComputerUse20251124(toolComputerUse20251124: BetaToolComputerUse20251124): T

        /**
         * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * computer tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema. Every member is enabled by default, zoom included.
         * The single-tool options ``display_number`` and ``enable_zoom`` are not fields of a
         * toolset entry — it carries only ``type``, ``configs``, and ``cache_control``; zoom is
         * controlled via ``configs.zoom.enabled``.
         */
        fun visitComputerToolset20260801(computerToolset20260801: BetaComputerToolset20260801): T

        fun visitToolTextEditor20250124(toolTextEditor20250124: BetaToolTextEditor20250124): T

        fun visitToolTextEditor20250429(toolTextEditor20250429: BetaToolTextEditor20250429): T

        fun visitToolTextEditor20250728(toolTextEditor20250728: BetaToolTextEditor20250728): T

        fun visitWebSearchTool20250305(webSearchTool20250305: BetaWebSearchTool20250305): T

        fun visitWebFetchTool20250910(webFetchTool20250910: BetaWebFetchTool20250910): T

        fun visitWebSearchTool20260209(webSearchTool20260209: BetaWebSearchTool20260209): T

        fun visitWebFetchTool20260209(webFetchTool20260209: BetaWebFetchTool20260209): T

        /** Web fetch tool with use_cache parameter for bypassing cached content. */
        fun visitWebFetchTool20260309(webFetchTool20260309: BetaWebFetchTool20260309): T

        fun visitWebSearchTool20260318(webSearchTool20260318: BetaWebSearchTool20260318): T

        fun visitWebFetchTool20260318(webFetchTool20260318: BetaWebFetchTool20260318): T

        fun visitAdvisorTool20260301(advisorTool20260301: BetaAdvisorTool20260301): T

        fun visitToolSearchToolBm25_20251119(
            toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
        ): T

        fun visitToolSearchToolRegex20251119(
            toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
        ): T

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        fun visitMcpToolset(mcpToolset: BetaMcpToolset): T

        /**
         * Maps an unknown variant of [BetaResponseToolUnion] to a value of type [T].
         *
         * An instance of [BetaResponseToolUnion] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaResponseToolUnion: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaResponseToolUnion>(BetaResponseToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaResponseToolUnion {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaResponseTool>())?.let {
                            BetaResponseToolUnion(betaResponseTool = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>())?.let {
                            BetaResponseToolUnion(toolBash20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolBash20250124>())?.let {
                            BetaResponseToolUnion(toolBash20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250522>())?.let {
                            BetaResponseToolUnion(codeExecutionTool20250522 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250825>())?.let {
                            BetaResponseToolUnion(codeExecutionTool20250825 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20260120>())?.let {
                            BetaResponseToolUnion(codeExecutionTool20260120 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20260521>())?.let {
                            BetaResponseToolUnion(codeExecutionTool20260521 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaBrowserToolset20260801>())?.let {
                            BetaResponseToolUnion(browserToolset20260801 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>())?.let {
                            BetaResponseToolUnion(toolComputerUse20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaMemoryTool20250818>())?.let {
                            BetaResponseToolUnion(memoryTool20250818 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20250124>())?.let {
                            BetaResponseToolUnion(toolComputerUse20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>())?.let {
                            BetaResponseToolUnion(toolTextEditor20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20251124>())?.let {
                            BetaResponseToolUnion(toolComputerUse20251124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaComputerToolset20260801>())?.let {
                            BetaResponseToolUnion(computerToolset20260801 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250124>())?.let {
                            BetaResponseToolUnion(toolTextEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250429>())?.let {
                            BetaResponseToolUnion(toolTextEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250728>())?.let {
                            BetaResponseToolUnion(toolTextEditor20250728 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20250305>())?.let {
                            BetaResponseToolUnion(webSearchTool20250305 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20250910>())?.let {
                            BetaResponseToolUnion(webFetchTool20250910 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20260209>())?.let {
                            BetaResponseToolUnion(webSearchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260209>())?.let {
                            BetaResponseToolUnion(webFetchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260309>())?.let {
                            BetaResponseToolUnion(webFetchTool20260309 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20260318>())?.let {
                            BetaResponseToolUnion(webSearchTool20260318 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260318>())?.let {
                            BetaResponseToolUnion(webFetchTool20260318 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaAdvisorTool20260301>())?.let {
                            BetaResponseToolUnion(advisorTool20260301 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolBm25_20251119>())
                            ?.let {
                                BetaResponseToolUnion(
                                    toolSearchToolBm25_20251119 = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolRegex20251119>())
                            ?.let {
                                BetaResponseToolUnion(
                                    toolSearchToolRegex20251119 = it,
                                    _json = json,
                                )
                            },
                        tryDeserialize(node, jacksonTypeRef<BetaMcpToolset>())?.let {
                            BetaResponseToolUnion(mcpToolset = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaResponseToolUnion(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaResponseToolUnion>(BetaResponseToolUnion::class) {

        override fun serialize(
            value: BetaResponseToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.betaResponseTool != null -> generator.writeObject(value.betaResponseTool)
                value.toolBash20241022 != null -> generator.writeObject(value.toolBash20241022)
                value.toolBash20250124 != null -> generator.writeObject(value.toolBash20250124)
                value.codeExecutionTool20250522 != null ->
                    generator.writeObject(value.codeExecutionTool20250522)
                value.codeExecutionTool20250825 != null ->
                    generator.writeObject(value.codeExecutionTool20250825)
                value.codeExecutionTool20260120 != null ->
                    generator.writeObject(value.codeExecutionTool20260120)
                value.codeExecutionTool20260521 != null ->
                    generator.writeObject(value.codeExecutionTool20260521)
                value.browserToolset20260801 != null ->
                    generator.writeObject(value.browserToolset20260801)
                value.toolComputerUse20241022 != null ->
                    generator.writeObject(value.toolComputerUse20241022)
                value.memoryTool20250818 != null -> generator.writeObject(value.memoryTool20250818)
                value.toolComputerUse20250124 != null ->
                    generator.writeObject(value.toolComputerUse20250124)
                value.toolTextEditor20241022 != null ->
                    generator.writeObject(value.toolTextEditor20241022)
                value.toolComputerUse20251124 != null ->
                    generator.writeObject(value.toolComputerUse20251124)
                value.computerToolset20260801 != null ->
                    generator.writeObject(value.computerToolset20260801)
                value.toolTextEditor20250124 != null ->
                    generator.writeObject(value.toolTextEditor20250124)
                value.toolTextEditor20250429 != null ->
                    generator.writeObject(value.toolTextEditor20250429)
                value.toolTextEditor20250728 != null ->
                    generator.writeObject(value.toolTextEditor20250728)
                value.webSearchTool20250305 != null ->
                    generator.writeObject(value.webSearchTool20250305)
                value.webFetchTool20250910 != null ->
                    generator.writeObject(value.webFetchTool20250910)
                value.webSearchTool20260209 != null ->
                    generator.writeObject(value.webSearchTool20260209)
                value.webFetchTool20260209 != null ->
                    generator.writeObject(value.webFetchTool20260209)
                value.webFetchTool20260309 != null ->
                    generator.writeObject(value.webFetchTool20260309)
                value.webSearchTool20260318 != null ->
                    generator.writeObject(value.webSearchTool20260318)
                value.webFetchTool20260318 != null ->
                    generator.writeObject(value.webFetchTool20260318)
                value.advisorTool20260301 != null ->
                    generator.writeObject(value.advisorTool20260301)
                value.toolSearchToolBm25_20251119 != null ->
                    generator.writeObject(value.toolSearchToolBm25_20251119)
                value.toolSearchToolRegex20251119 != null ->
                    generator.writeObject(value.toolSearchToolRegex20251119)
                value.mcpToolset != null -> generator.writeObject(value.mcpToolset)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaResponseToolUnion")
            }
        }
    }
}
