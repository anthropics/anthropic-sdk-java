package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.toField
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

@JsonDeserialize(using = BetaToolUnion.Deserializer::class)
@JsonSerialize(using = BetaToolUnion.Serializer::class)
class BetaToolUnion
private constructor(
    private val betaTool: BetaTool? = null,
    private val bash20241022: BetaToolBash20241022? = null,
    private val bash20250124: BetaToolBash20250124? = null,
    private val codeExecutionTool20250522: BetaCodeExecutionTool20250522? = null,
    private val codeExecutionTool20250825: BetaCodeExecutionTool20250825? = null,
    private val codeExecutionTool20260120: BetaCodeExecutionTool20260120? = null,
    private val codeExecutionTool20260521: BetaCodeExecutionTool20260521? = null,
    private val browserToolset20260801: BetaBrowserToolset20260801? = null,
    private val computerUse20241022: BetaToolComputerUse20241022? = null,
    private val memoryTool20250818: BetaMemoryTool20250818? = null,
    private val computerUse20250124: BetaToolComputerUse20250124? = null,
    private val textEditor20241022: BetaToolTextEditor20241022? = null,
    private val computerUse20251124: BetaToolComputerUse20251124? = null,
    private val computerToolset20260801: BetaComputerToolset20260801? = null,
    private val textEditor20250124: BetaToolTextEditor20250124? = null,
    private val textEditor20250429: BetaToolTextEditor20250429? = null,
    private val textEditor20250728: BetaToolTextEditor20250728? = null,
    private val webSearchTool20250305: BetaWebSearchTool20250305? = null,
    private val webFetchTool20250910: BetaWebFetchTool20250910? = null,
    private val webSearchTool20260209: BetaWebSearchTool20260209? = null,
    private val webFetchTool20260209: BetaWebFetchTool20260209? = null,
    private val webFetchTool20260309: BetaWebFetchTool20260309? = null,
    private val webSearchTool20260318: BetaWebSearchTool20260318? = null,
    private val webFetchTool20260318: BetaWebFetchTool20260318? = null,
    private val advisorTool20260301: BetaAdvisorTool20260301? = null,
    private val searchToolBm25_20251119: BetaToolSearchToolBm25_20251119? = null,
    private val searchToolRegex20251119: BetaToolSearchToolRegex20251119? = null,
    private val mcpToolset: BetaMcpToolset? = null,
    private val _json: JsonValue? = null,
) {

    fun name(): Optional<String> =
        when {
            betaTool != null -> Optional.of(betaTool.name())
            bash20241022 != null -> bash20241022._name().toField<String>().asKnown()
            bash20250124 != null -> bash20250124._name().toField<String>().asKnown()
            codeExecutionTool20250522 != null ->
                codeExecutionTool20250522._name().toField<String>().asKnown()
            codeExecutionTool20250825 != null ->
                codeExecutionTool20250825._name().toField<String>().asKnown()
            codeExecutionTool20260120 != null ->
                codeExecutionTool20260120._name().toField<String>().asKnown()
            codeExecutionTool20260521 != null ->
                codeExecutionTool20260521._name().toField<String>().asKnown()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> computerUse20241022._name().toField<String>().asKnown()
            memoryTool20250818 != null -> memoryTool20250818._name().toField<String>().asKnown()
            computerUse20250124 != null -> computerUse20250124._name().toField<String>().asKnown()
            textEditor20241022 != null -> textEditor20241022._name().toField<String>().asKnown()
            computerUse20251124 != null -> computerUse20251124._name().toField<String>().asKnown()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> textEditor20250124._name().toField<String>().asKnown()
            textEditor20250429 != null -> textEditor20250429._name().toField<String>().asKnown()
            textEditor20250728 != null -> textEditor20250728._name().toField<String>().asKnown()
            webSearchTool20250305 != null ->
                webSearchTool20250305._name().toField<String>().asKnown()
            webFetchTool20250910 != null -> webFetchTool20250910._name().toField<String>().asKnown()
            webSearchTool20260209 != null ->
                webSearchTool20260209._name().toField<String>().asKnown()
            webFetchTool20260209 != null -> webFetchTool20260209._name().toField<String>().asKnown()
            webFetchTool20260309 != null -> webFetchTool20260309._name().toField<String>().asKnown()
            webSearchTool20260318 != null ->
                webSearchTool20260318._name().toField<String>().asKnown()
            webFetchTool20260318 != null -> webFetchTool20260318._name().toField<String>().asKnown()
            advisorTool20260301 != null -> advisorTool20260301._name().toField<String>().asKnown()
            searchToolBm25_20251119 != null ->
                searchToolBm25_20251119._name().toField<String>().asKnown()
            searchToolRegex20251119 != null ->
                searchToolRegex20251119._name().toField<String>().asKnown()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<String>("name").asKnown()
        }

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        when {
            betaTool != null -> betaTool.cacheControl()
            bash20241022 != null -> bash20241022.cacheControl()
            bash20250124 != null -> bash20250124.cacheControl()
            codeExecutionTool20250522 != null -> codeExecutionTool20250522.cacheControl()
            codeExecutionTool20250825 != null -> codeExecutionTool20250825.cacheControl()
            codeExecutionTool20260120 != null -> codeExecutionTool20260120.cacheControl()
            codeExecutionTool20260521 != null -> codeExecutionTool20260521.cacheControl()
            browserToolset20260801 != null -> browserToolset20260801.cacheControl()
            computerUse20241022 != null -> computerUse20241022.cacheControl()
            memoryTool20250818 != null -> memoryTool20250818.cacheControl()
            computerUse20250124 != null -> computerUse20250124.cacheControl()
            textEditor20241022 != null -> textEditor20241022.cacheControl()
            computerUse20251124 != null -> computerUse20251124.cacheControl()
            computerToolset20260801 != null -> computerToolset20260801.cacheControl()
            textEditor20250124 != null -> textEditor20250124.cacheControl()
            textEditor20250429 != null -> textEditor20250429.cacheControl()
            textEditor20250728 != null -> textEditor20250728.cacheControl()
            webSearchTool20250305 != null -> webSearchTool20250305.cacheControl()
            webFetchTool20250910 != null -> webFetchTool20250910.cacheControl()
            webSearchTool20260209 != null -> webSearchTool20260209.cacheControl()
            webFetchTool20260209 != null -> webFetchTool20260209.cacheControl()
            webFetchTool20260309 != null -> webFetchTool20260309.cacheControl()
            webSearchTool20260318 != null -> webSearchTool20260318.cacheControl()
            webFetchTool20260318 != null -> webFetchTool20260318.cacheControl()
            advisorTool20260301 != null -> advisorTool20260301.cacheControl()
            searchToolBm25_20251119 != null -> searchToolBm25_20251119.cacheControl()
            searchToolRegex20251119 != null -> searchToolRegex20251119.cacheControl()
            mcpToolset != null -> mcpToolset.cacheControl()
            else -> _json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
        }

    fun deferLoading(): Optional<Boolean> =
        when {
            betaTool != null -> betaTool.deferLoading()
            bash20241022 != null -> bash20241022.deferLoading()
            bash20250124 != null -> bash20250124.deferLoading()
            codeExecutionTool20250522 != null -> codeExecutionTool20250522.deferLoading()
            codeExecutionTool20250825 != null -> codeExecutionTool20250825.deferLoading()
            codeExecutionTool20260120 != null -> codeExecutionTool20260120.deferLoading()
            codeExecutionTool20260521 != null -> codeExecutionTool20260521.deferLoading()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> computerUse20241022.deferLoading()
            memoryTool20250818 != null -> memoryTool20250818.deferLoading()
            computerUse20250124 != null -> computerUse20250124.deferLoading()
            textEditor20241022 != null -> textEditor20241022.deferLoading()
            computerUse20251124 != null -> computerUse20251124.deferLoading()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> textEditor20250124.deferLoading()
            textEditor20250429 != null -> textEditor20250429.deferLoading()
            textEditor20250728 != null -> textEditor20250728.deferLoading()
            webSearchTool20250305 != null -> webSearchTool20250305.deferLoading()
            webFetchTool20250910 != null -> webFetchTool20250910.deferLoading()
            webSearchTool20260209 != null -> webSearchTool20260209.deferLoading()
            webFetchTool20260209 != null -> webFetchTool20260209.deferLoading()
            webFetchTool20260309 != null -> webFetchTool20260309.deferLoading()
            webSearchTool20260318 != null -> webSearchTool20260318.deferLoading()
            webFetchTool20260318 != null -> webFetchTool20260318.deferLoading()
            advisorTool20260301 != null -> advisorTool20260301.deferLoading()
            searchToolBm25_20251119 != null -> searchToolBm25_20251119.deferLoading()
            searchToolRegex20251119 != null -> searchToolRegex20251119.deferLoading()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Boolean>("defer_loading").asKnown()
        }

    fun strict(): Optional<Boolean> =
        when {
            betaTool != null -> betaTool.strict()
            bash20241022 != null -> bash20241022.strict()
            bash20250124 != null -> bash20250124.strict()
            codeExecutionTool20250522 != null -> codeExecutionTool20250522.strict()
            codeExecutionTool20250825 != null -> codeExecutionTool20250825.strict()
            codeExecutionTool20260120 != null -> codeExecutionTool20260120.strict()
            codeExecutionTool20260521 != null -> codeExecutionTool20260521.strict()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> computerUse20241022.strict()
            memoryTool20250818 != null -> memoryTool20250818.strict()
            computerUse20250124 != null -> computerUse20250124.strict()
            textEditor20241022 != null -> textEditor20241022.strict()
            computerUse20251124 != null -> computerUse20251124.strict()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> textEditor20250124.strict()
            textEditor20250429 != null -> textEditor20250429.strict()
            textEditor20250728 != null -> textEditor20250728.strict()
            webSearchTool20250305 != null -> webSearchTool20250305.strict()
            webFetchTool20250910 != null -> webFetchTool20250910.strict()
            webSearchTool20260209 != null -> webSearchTool20260209.strict()
            webFetchTool20260209 != null -> webFetchTool20260209.strict()
            webFetchTool20260309 != null -> webFetchTool20260309.strict()
            webSearchTool20260318 != null -> webSearchTool20260318.strict()
            webFetchTool20260318 != null -> webFetchTool20260318.strict()
            advisorTool20260301 != null -> advisorTool20260301.strict()
            searchToolBm25_20251119 != null -> searchToolBm25_20251119.strict()
            searchToolRegex20251119 != null -> searchToolRegex20251119.strict()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Boolean>("strict").asKnown()
        }

    fun displayHeightPx(): Optional<Long> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.of(computerUse20241022.displayHeightPx())
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.of(computerUse20250124.displayHeightPx())
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.of(computerUse20251124.displayHeightPx())
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> Optional.empty()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> Optional.empty()
            webFetchTool20260309 != null -> Optional.empty()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> Optional.empty()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Long>("display_height_px").asKnown()
        }

    fun displayWidthPx(): Optional<Long> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.of(computerUse20241022.displayWidthPx())
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.of(computerUse20250124.displayWidthPx())
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.of(computerUse20251124.displayWidthPx())
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> Optional.empty()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> Optional.empty()
            webFetchTool20260309 != null -> Optional.empty()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> Optional.empty()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Long>("display_width_px").asKnown()
        }

    fun displayNumber(): Optional<Long> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> computerUse20241022.displayNumber()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> computerUse20250124.displayNumber()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> computerUse20251124.displayNumber()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> Optional.empty()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> Optional.empty()
            webFetchTool20260309 != null -> Optional.empty()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> Optional.empty()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Long>("display_number").asKnown()
        }

    fun allowedDomains(): Optional<List<String>> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> webSearchTool20250305.allowedDomains()
            webFetchTool20250910 != null -> webFetchTool20250910.allowedDomains()
            webSearchTool20260209 != null -> webSearchTool20260209.allowedDomains()
            webFetchTool20260209 != null -> webFetchTool20260209.allowedDomains()
            webFetchTool20260309 != null -> webFetchTool20260309.allowedDomains()
            webSearchTool20260318 != null -> webSearchTool20260318.allowedDomains()
            webFetchTool20260318 != null -> webFetchTool20260318.allowedDomains()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<List<String>>("allowed_domains").asKnown()
        }

    fun blockedDomains(): Optional<List<String>> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> webSearchTool20250305.blockedDomains()
            webFetchTool20250910 != null -> webFetchTool20250910.blockedDomains()
            webSearchTool20260209 != null -> webSearchTool20260209.blockedDomains()
            webFetchTool20260209 != null -> webFetchTool20260209.blockedDomains()
            webFetchTool20260309 != null -> webFetchTool20260309.blockedDomains()
            webSearchTool20260318 != null -> webSearchTool20260318.blockedDomains()
            webFetchTool20260318 != null -> webFetchTool20260318.blockedDomains()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<List<String>>("blocked_domains").asKnown()
        }

    fun maxUses(): Optional<Long> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> webSearchTool20250305.maxUses()
            webFetchTool20250910 != null -> webFetchTool20250910.maxUses()
            webSearchTool20260209 != null -> webSearchTool20260209.maxUses()
            webFetchTool20260209 != null -> webFetchTool20260209.maxUses()
            webFetchTool20260309 != null -> webFetchTool20260309.maxUses()
            webSearchTool20260318 != null -> webSearchTool20260318.maxUses()
            webFetchTool20260318 != null -> webFetchTool20260318.maxUses()
            advisorTool20260301 != null -> advisorTool20260301.maxUses()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Long>("max_uses").asKnown()
        }

    fun userLocation(): Optional<BetaUserLocation> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> webSearchTool20250305.userLocation()
            webFetchTool20250910 != null -> Optional.empty()
            webSearchTool20260209 != null -> webSearchTool20260209.userLocation()
            webFetchTool20260209 != null -> Optional.empty()
            webFetchTool20260309 != null -> Optional.empty()
            webSearchTool20260318 != null -> webSearchTool20260318.userLocation()
            webFetchTool20260318 != null -> Optional.empty()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<BetaUserLocation>("user_location").asKnown()
        }

    fun citations(): Optional<BetaCitationsConfigParam> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> webFetchTool20250910.citations()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> webFetchTool20260209.citations()
            webFetchTool20260309 != null -> webFetchTool20260309.citations()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> webFetchTool20260318.citations()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<BetaCitationsConfigParam>("citations").asKnown()
        }

    fun maxContentTokens(): Optional<Long> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> webFetchTool20250910.maxContentTokens()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> webFetchTool20260209.maxContentTokens()
            webFetchTool20260309 != null -> webFetchTool20260309.maxContentTokens()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> webFetchTool20260318.maxContentTokens()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Long>("max_content_tokens").asKnown()
        }

    fun urlSources(): Optional<BetaWebFetchUrlSources> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> webFetchTool20250910.urlSources()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> webFetchTool20260209.urlSources()
            webFetchTool20260309 != null -> webFetchTool20260309.urlSources()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> webFetchTool20260318.urlSources()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<BetaWebFetchUrlSources>("url_sources").asKnown()
        }

    fun useCache(): Optional<Boolean> =
        when {
            betaTool != null -> Optional.empty()
            bash20241022 != null -> Optional.empty()
            bash20250124 != null -> Optional.empty()
            codeExecutionTool20250522 != null -> Optional.empty()
            codeExecutionTool20250825 != null -> Optional.empty()
            codeExecutionTool20260120 != null -> Optional.empty()
            codeExecutionTool20260521 != null -> Optional.empty()
            browserToolset20260801 != null -> Optional.empty()
            computerUse20241022 != null -> Optional.empty()
            memoryTool20250818 != null -> Optional.empty()
            computerUse20250124 != null -> Optional.empty()
            textEditor20241022 != null -> Optional.empty()
            computerUse20251124 != null -> Optional.empty()
            computerToolset20260801 != null -> Optional.empty()
            textEditor20250124 != null -> Optional.empty()
            textEditor20250429 != null -> Optional.empty()
            textEditor20250728 != null -> Optional.empty()
            webSearchTool20250305 != null -> Optional.empty()
            webFetchTool20250910 != null -> Optional.empty()
            webSearchTool20260209 != null -> Optional.empty()
            webFetchTool20260209 != null -> Optional.empty()
            webFetchTool20260309 != null -> webFetchTool20260309.useCache()
            webSearchTool20260318 != null -> Optional.empty()
            webFetchTool20260318 != null -> webFetchTool20260318.useCache()
            advisorTool20260301 != null -> Optional.empty()
            searchToolBm25_20251119 != null -> Optional.empty()
            searchToolRegex20251119 != null -> Optional.empty()
            mcpToolset != null -> Optional.empty()
            else -> _json.getProperty<Boolean>("use_cache").asKnown()
        }

    fun betaTool(): Optional<BetaTool> = Optional.ofNullable(betaTool)

    fun bash20241022(): Optional<BetaToolBash20241022> = Optional.ofNullable(bash20241022)

    fun bash20250124(): Optional<BetaToolBash20250124> = Optional.ofNullable(bash20250124)

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

    fun computerUse20241022(): Optional<BetaToolComputerUse20241022> =
        Optional.ofNullable(computerUse20241022)

    fun memoryTool20250818(): Optional<BetaMemoryTool20250818> =
        Optional.ofNullable(memoryTool20250818)

    fun computerUse20250124(): Optional<BetaToolComputerUse20250124> =
        Optional.ofNullable(computerUse20250124)

    fun textEditor20241022(): Optional<BetaToolTextEditor20241022> =
        Optional.ofNullable(textEditor20241022)

    fun computerUse20251124(): Optional<BetaToolComputerUse20251124> =
        Optional.ofNullable(computerUse20251124)

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

    fun textEditor20250124(): Optional<BetaToolTextEditor20250124> =
        Optional.ofNullable(textEditor20250124)

    fun textEditor20250429(): Optional<BetaToolTextEditor20250429> =
        Optional.ofNullable(textEditor20250429)

    fun textEditor20250728(): Optional<BetaToolTextEditor20250728> =
        Optional.ofNullable(textEditor20250728)

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

    fun searchToolBm25_20251119(): Optional<BetaToolSearchToolBm25_20251119> =
        Optional.ofNullable(searchToolBm25_20251119)

    fun searchToolRegex20251119(): Optional<BetaToolSearchToolRegex20251119> =
        Optional.ofNullable(searchToolRegex20251119)

    /**
     * Configuration for a group of tools from an MCP server.
     *
     * Allows configuring enabled status and defer_loading for all tools from an MCP server, with
     * optional per-tool overrides.
     */
    fun mcpToolset(): Optional<BetaMcpToolset> = Optional.ofNullable(mcpToolset)

    fun isBetaTool(): Boolean = betaTool != null

    fun isBash20241022(): Boolean = bash20241022 != null

    fun isBash20250124(): Boolean = bash20250124 != null

    fun isCodeExecutionTool20250522(): Boolean = codeExecutionTool20250522 != null

    fun isCodeExecutionTool20250825(): Boolean = codeExecutionTool20250825 != null

    fun isCodeExecutionTool20260120(): Boolean = codeExecutionTool20260120 != null

    fun isCodeExecutionTool20260521(): Boolean = codeExecutionTool20260521 != null

    fun isBrowserToolset20260801(): Boolean = browserToolset20260801 != null

    fun isComputerUse20241022(): Boolean = computerUse20241022 != null

    fun isMemoryTool20250818(): Boolean = memoryTool20250818 != null

    fun isComputerUse20250124(): Boolean = computerUse20250124 != null

    fun isTextEditor20241022(): Boolean = textEditor20241022 != null

    fun isComputerUse20251124(): Boolean = computerUse20251124 != null

    fun isComputerToolset20260801(): Boolean = computerToolset20260801 != null

    fun isTextEditor20250124(): Boolean = textEditor20250124 != null

    fun isTextEditor20250429(): Boolean = textEditor20250429 != null

    fun isTextEditor20250728(): Boolean = textEditor20250728 != null

    fun isWebSearchTool20250305(): Boolean = webSearchTool20250305 != null

    fun isWebFetchTool20250910(): Boolean = webFetchTool20250910 != null

    fun isWebSearchTool20260209(): Boolean = webSearchTool20260209 != null

    fun isWebFetchTool20260209(): Boolean = webFetchTool20260209 != null

    fun isWebFetchTool20260309(): Boolean = webFetchTool20260309 != null

    fun isWebSearchTool20260318(): Boolean = webSearchTool20260318 != null

    fun isWebFetchTool20260318(): Boolean = webFetchTool20260318 != null

    fun isAdvisorTool20260301(): Boolean = advisorTool20260301 != null

    fun isSearchToolBm25_20251119(): Boolean = searchToolBm25_20251119 != null

    fun isSearchToolRegex20251119(): Boolean = searchToolRegex20251119 != null

    fun isMcpToolset(): Boolean = mcpToolset != null

    fun asBetaTool(): BetaTool = betaTool.getOrThrow("betaTool")

    fun asBash20241022(): BetaToolBash20241022 = bash20241022.getOrThrow("bash20241022")

    fun asBash20250124(): BetaToolBash20250124 = bash20250124.getOrThrow("bash20250124")

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

    fun asComputerUse20241022(): BetaToolComputerUse20241022 =
        computerUse20241022.getOrThrow("computerUse20241022")

    fun asMemoryTool20250818(): BetaMemoryTool20250818 =
        memoryTool20250818.getOrThrow("memoryTool20250818")

    fun asComputerUse20250124(): BetaToolComputerUse20250124 =
        computerUse20250124.getOrThrow("computerUse20250124")

    fun asTextEditor20241022(): BetaToolTextEditor20241022 =
        textEditor20241022.getOrThrow("textEditor20241022")

    fun asComputerUse20251124(): BetaToolComputerUse20251124 =
        computerUse20251124.getOrThrow("computerUse20251124")

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

    fun asTextEditor20250124(): BetaToolTextEditor20250124 =
        textEditor20250124.getOrThrow("textEditor20250124")

    fun asTextEditor20250429(): BetaToolTextEditor20250429 =
        textEditor20250429.getOrThrow("textEditor20250429")

    fun asTextEditor20250728(): BetaToolTextEditor20250728 =
        textEditor20250728.getOrThrow("textEditor20250728")

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

    fun asSearchToolBm25_20251119(): BetaToolSearchToolBm25_20251119 =
        searchToolBm25_20251119.getOrThrow("searchToolBm25_20251119")

    fun asSearchToolRegex20251119(): BetaToolSearchToolRegex20251119 =
        searchToolRegex20251119.getOrThrow("searchToolRegex20251119")

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
     * Optional<String> result = betaToolUnion.accept(new BetaToolUnion.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBetaTool(BetaTool betaTool) {
     *         return Optional.of(betaTool.toString());
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
            betaTool != null -> visitor.visitBetaTool(betaTool)
            bash20241022 != null -> visitor.visitBash20241022(bash20241022)
            bash20250124 != null -> visitor.visitBash20250124(bash20250124)
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
            computerUse20241022 != null -> visitor.visitComputerUse20241022(computerUse20241022)
            memoryTool20250818 != null -> visitor.visitMemoryTool20250818(memoryTool20250818)
            computerUse20250124 != null -> visitor.visitComputerUse20250124(computerUse20250124)
            textEditor20241022 != null -> visitor.visitTextEditor20241022(textEditor20241022)
            computerUse20251124 != null -> visitor.visitComputerUse20251124(computerUse20251124)
            computerToolset20260801 != null ->
                visitor.visitComputerToolset20260801(computerToolset20260801)
            textEditor20250124 != null -> visitor.visitTextEditor20250124(textEditor20250124)
            textEditor20250429 != null -> visitor.visitTextEditor20250429(textEditor20250429)
            textEditor20250728 != null -> visitor.visitTextEditor20250728(textEditor20250728)
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
            searchToolBm25_20251119 != null ->
                visitor.visitSearchToolBm25_20251119(searchToolBm25_20251119)
            searchToolRegex20251119 != null ->
                visitor.visitSearchToolRegex20251119(searchToolRegex20251119)
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
    fun validate(): BetaToolUnion = apply {
        if (validated) {
            return@apply
        }

        when {
            betaTool != null -> betaTool.validate()
            bash20241022 != null -> bash20241022.validate()
            bash20250124 != null -> bash20250124.validate()
            codeExecutionTool20250522 != null -> codeExecutionTool20250522.validate()
            codeExecutionTool20250825 != null -> codeExecutionTool20250825.validate()
            codeExecutionTool20260120 != null -> codeExecutionTool20260120.validate()
            codeExecutionTool20260521 != null -> codeExecutionTool20260521.validate()
            browserToolset20260801 != null -> browserToolset20260801.validate()
            computerUse20241022 != null -> computerUse20241022.validate()
            memoryTool20250818 != null -> memoryTool20250818.validate()
            computerUse20250124 != null -> computerUse20250124.validate()
            textEditor20241022 != null -> textEditor20241022.validate()
            computerUse20251124 != null -> computerUse20251124.validate()
            computerToolset20260801 != null -> computerToolset20260801.validate()
            textEditor20250124 != null -> textEditor20250124.validate()
            textEditor20250429 != null -> textEditor20250429.validate()
            textEditor20250728 != null -> textEditor20250728.validate()
            webSearchTool20250305 != null -> webSearchTool20250305.validate()
            webFetchTool20250910 != null -> webFetchTool20250910.validate()
            webSearchTool20260209 != null -> webSearchTool20260209.validate()
            webFetchTool20260209 != null -> webFetchTool20260209.validate()
            webFetchTool20260309 != null -> webFetchTool20260309.validate()
            webSearchTool20260318 != null -> webSearchTool20260318.validate()
            webFetchTool20260318 != null -> webFetchTool20260318.validate()
            advisorTool20260301 != null -> advisorTool20260301.validate()
            searchToolBm25_20251119 != null -> searchToolBm25_20251119.validate()
            searchToolRegex20251119 != null -> searchToolRegex20251119.validate()
            mcpToolset != null -> mcpToolset.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaToolUnion: $_json")
        }
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
        when {
            betaTool != null -> betaTool.validity()
            bash20241022 != null -> bash20241022.validity()
            bash20250124 != null -> bash20250124.validity()
            codeExecutionTool20250522 != null -> codeExecutionTool20250522.validity()
            codeExecutionTool20250825 != null -> codeExecutionTool20250825.validity()
            codeExecutionTool20260120 != null -> codeExecutionTool20260120.validity()
            codeExecutionTool20260521 != null -> codeExecutionTool20260521.validity()
            browserToolset20260801 != null -> browserToolset20260801.validity()
            computerUse20241022 != null -> computerUse20241022.validity()
            memoryTool20250818 != null -> memoryTool20250818.validity()
            computerUse20250124 != null -> computerUse20250124.validity()
            textEditor20241022 != null -> textEditor20241022.validity()
            computerUse20251124 != null -> computerUse20251124.validity()
            computerToolset20260801 != null -> computerToolset20260801.validity()
            textEditor20250124 != null -> textEditor20250124.validity()
            textEditor20250429 != null -> textEditor20250429.validity()
            textEditor20250728 != null -> textEditor20250728.validity()
            webSearchTool20250305 != null -> webSearchTool20250305.validity()
            webFetchTool20250910 != null -> webFetchTool20250910.validity()
            webSearchTool20260209 != null -> webSearchTool20260209.validity()
            webFetchTool20260209 != null -> webFetchTool20260209.validity()
            webFetchTool20260309 != null -> webFetchTool20260309.validity()
            webSearchTool20260318 != null -> webSearchTool20260318.validity()
            webFetchTool20260318 != null -> webFetchTool20260318.validity()
            advisorTool20260301 != null -> advisorTool20260301.validity()
            searchToolBm25_20251119 != null -> searchToolBm25_20251119.validity()
            searchToolRegex20251119 != null -> searchToolRegex20251119.validity()
            mcpToolset != null -> mcpToolset.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolUnion &&
            betaTool == other.betaTool &&
            bash20241022 == other.bash20241022 &&
            bash20250124 == other.bash20250124 &&
            codeExecutionTool20250522 == other.codeExecutionTool20250522 &&
            codeExecutionTool20250825 == other.codeExecutionTool20250825 &&
            codeExecutionTool20260120 == other.codeExecutionTool20260120 &&
            codeExecutionTool20260521 == other.codeExecutionTool20260521 &&
            browserToolset20260801 == other.browserToolset20260801 &&
            computerUse20241022 == other.computerUse20241022 &&
            memoryTool20250818 == other.memoryTool20250818 &&
            computerUse20250124 == other.computerUse20250124 &&
            textEditor20241022 == other.textEditor20241022 &&
            computerUse20251124 == other.computerUse20251124 &&
            computerToolset20260801 == other.computerToolset20260801 &&
            textEditor20250124 == other.textEditor20250124 &&
            textEditor20250429 == other.textEditor20250429 &&
            textEditor20250728 == other.textEditor20250728 &&
            webSearchTool20250305 == other.webSearchTool20250305 &&
            webFetchTool20250910 == other.webFetchTool20250910 &&
            webSearchTool20260209 == other.webSearchTool20260209 &&
            webFetchTool20260209 == other.webFetchTool20260209 &&
            webFetchTool20260309 == other.webFetchTool20260309 &&
            webSearchTool20260318 == other.webSearchTool20260318 &&
            webFetchTool20260318 == other.webFetchTool20260318 &&
            advisorTool20260301 == other.advisorTool20260301 &&
            searchToolBm25_20251119 == other.searchToolBm25_20251119 &&
            searchToolRegex20251119 == other.searchToolRegex20251119 &&
            mcpToolset == other.mcpToolset
    }

    override fun hashCode(): Int =
        Objects.hash(
            betaTool,
            bash20241022,
            bash20250124,
            codeExecutionTool20250522,
            codeExecutionTool20250825,
            codeExecutionTool20260120,
            codeExecutionTool20260521,
            browserToolset20260801,
            computerUse20241022,
            memoryTool20250818,
            computerUse20250124,
            textEditor20241022,
            computerUse20251124,
            computerToolset20260801,
            textEditor20250124,
            textEditor20250429,
            textEditor20250728,
            webSearchTool20250305,
            webFetchTool20250910,
            webSearchTool20260209,
            webFetchTool20260209,
            webFetchTool20260309,
            webSearchTool20260318,
            webFetchTool20260318,
            advisorTool20260301,
            searchToolBm25_20251119,
            searchToolRegex20251119,
            mcpToolset,
        )

    override fun toString(): String =
        when {
            betaTool != null -> "BetaToolUnion{betaTool=$betaTool}"
            bash20241022 != null -> "BetaToolUnion{bash20241022=$bash20241022}"
            bash20250124 != null -> "BetaToolUnion{bash20250124=$bash20250124}"
            codeExecutionTool20250522 != null ->
                "BetaToolUnion{codeExecutionTool20250522=$codeExecutionTool20250522}"
            codeExecutionTool20250825 != null ->
                "BetaToolUnion{codeExecutionTool20250825=$codeExecutionTool20250825}"
            codeExecutionTool20260120 != null ->
                "BetaToolUnion{codeExecutionTool20260120=$codeExecutionTool20260120}"
            codeExecutionTool20260521 != null ->
                "BetaToolUnion{codeExecutionTool20260521=$codeExecutionTool20260521}"
            browserToolset20260801 != null ->
                "BetaToolUnion{browserToolset20260801=$browserToolset20260801}"
            computerUse20241022 != null -> "BetaToolUnion{computerUse20241022=$computerUse20241022}"
            memoryTool20250818 != null -> "BetaToolUnion{memoryTool20250818=$memoryTool20250818}"
            computerUse20250124 != null -> "BetaToolUnion{computerUse20250124=$computerUse20250124}"
            textEditor20241022 != null -> "BetaToolUnion{textEditor20241022=$textEditor20241022}"
            computerUse20251124 != null -> "BetaToolUnion{computerUse20251124=$computerUse20251124}"
            computerToolset20260801 != null ->
                "BetaToolUnion{computerToolset20260801=$computerToolset20260801}"
            textEditor20250124 != null -> "BetaToolUnion{textEditor20250124=$textEditor20250124}"
            textEditor20250429 != null -> "BetaToolUnion{textEditor20250429=$textEditor20250429}"
            textEditor20250728 != null -> "BetaToolUnion{textEditor20250728=$textEditor20250728}"
            webSearchTool20250305 != null ->
                "BetaToolUnion{webSearchTool20250305=$webSearchTool20250305}"
            webFetchTool20250910 != null ->
                "BetaToolUnion{webFetchTool20250910=$webFetchTool20250910}"
            webSearchTool20260209 != null ->
                "BetaToolUnion{webSearchTool20260209=$webSearchTool20260209}"
            webFetchTool20260209 != null ->
                "BetaToolUnion{webFetchTool20260209=$webFetchTool20260209}"
            webFetchTool20260309 != null ->
                "BetaToolUnion{webFetchTool20260309=$webFetchTool20260309}"
            webSearchTool20260318 != null ->
                "BetaToolUnion{webSearchTool20260318=$webSearchTool20260318}"
            webFetchTool20260318 != null ->
                "BetaToolUnion{webFetchTool20260318=$webFetchTool20260318}"
            advisorTool20260301 != null -> "BetaToolUnion{advisorTool20260301=$advisorTool20260301}"
            searchToolBm25_20251119 != null ->
                "BetaToolUnion{searchToolBm25_20251119=$searchToolBm25_20251119}"
            searchToolRegex20251119 != null ->
                "BetaToolUnion{searchToolRegex20251119=$searchToolRegex20251119}"
            mcpToolset != null -> "BetaToolUnion{mcpToolset=$mcpToolset}"
            _json != null -> "BetaToolUnion{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolUnion")
        }

    companion object {

        @JvmStatic fun ofBetaTool(betaTool: BetaTool) = BetaToolUnion(betaTool = betaTool)

        @JvmStatic
        fun ofBash20241022(bash20241022: BetaToolBash20241022) =
            BetaToolUnion(bash20241022 = bash20241022)

        @JvmStatic
        fun ofBash20250124(bash20250124: BetaToolBash20250124) =
            BetaToolUnion(bash20250124 = bash20250124)

        @JvmStatic
        fun ofCodeExecutionTool20250522(codeExecutionTool20250522: BetaCodeExecutionTool20250522) =
            BetaToolUnion(codeExecutionTool20250522 = codeExecutionTool20250522)

        @JvmStatic
        fun ofCodeExecutionTool20250825(codeExecutionTool20250825: BetaCodeExecutionTool20250825) =
            BetaToolUnion(codeExecutionTool20250825 = codeExecutionTool20250825)

        /** Code execution tool with REPL state persistence (daemon mode + gVisor checkpoint). */
        @JvmStatic
        fun ofCodeExecutionTool20260120(codeExecutionTool20260120: BetaCodeExecutionTool20260120) =
            BetaToolUnion(codeExecutionTool20260120 = codeExecutionTool20260120)

        /** Code execution tool with REPL state persistence. */
        @JvmStatic
        fun ofCodeExecutionTool20260521(codeExecutionTool20260521: BetaCodeExecutionTool20260521) =
            BetaToolUnion(codeExecutionTool20260521 = codeExecutionTool20260521)

        /**
         * The browser toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * browser tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema.
         */
        @JvmStatic
        fun ofBrowserToolset20260801(browserToolset20260801: BetaBrowserToolset20260801) =
            BetaToolUnion(browserToolset20260801 = browserToolset20260801)

        @JvmStatic
        fun ofComputerUse20241022(computerUse20241022: BetaToolComputerUse20241022) =
            BetaToolUnion(computerUse20241022 = computerUse20241022)

        @JvmStatic
        fun ofMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818) =
            BetaToolUnion(memoryTool20250818 = memoryTool20250818)

        @JvmStatic
        fun ofComputerUse20250124(computerUse20250124: BetaToolComputerUse20250124) =
            BetaToolUnion(computerUse20250124 = computerUse20250124)

        @JvmStatic
        fun ofTextEditor20241022(textEditor20241022: BetaToolTextEditor20241022) =
            BetaToolUnion(textEditor20241022 = textEditor20241022)

        @JvmStatic
        fun ofComputerUse20251124(computerUse20251124: BetaToolComputerUse20251124) =
            BetaToolUnion(computerUse20251124 = computerUse20251124)

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
            BetaToolUnion(computerToolset20260801 = computerToolset20260801)

        @JvmStatic
        fun ofTextEditor20250124(textEditor20250124: BetaToolTextEditor20250124) =
            BetaToolUnion(textEditor20250124 = textEditor20250124)

        @JvmStatic
        fun ofTextEditor20250429(textEditor20250429: BetaToolTextEditor20250429) =
            BetaToolUnion(textEditor20250429 = textEditor20250429)

        @JvmStatic
        fun ofTextEditor20250728(textEditor20250728: BetaToolTextEditor20250728) =
            BetaToolUnion(textEditor20250728 = textEditor20250728)

        @JvmStatic
        fun ofWebSearchTool20250305(webSearchTool20250305: BetaWebSearchTool20250305) =
            BetaToolUnion(webSearchTool20250305 = webSearchTool20250305)

        @JvmStatic
        fun ofWebFetchTool20250910(webFetchTool20250910: BetaWebFetchTool20250910) =
            BetaToolUnion(webFetchTool20250910 = webFetchTool20250910)

        @JvmStatic
        fun ofWebSearchTool20260209(webSearchTool20260209: BetaWebSearchTool20260209) =
            BetaToolUnion(webSearchTool20260209 = webSearchTool20260209)

        @JvmStatic
        fun ofWebFetchTool20260209(webFetchTool20260209: BetaWebFetchTool20260209) =
            BetaToolUnion(webFetchTool20260209 = webFetchTool20260209)

        /** Web fetch tool with use_cache parameter for bypassing cached content. */
        @JvmStatic
        fun ofWebFetchTool20260309(webFetchTool20260309: BetaWebFetchTool20260309) =
            BetaToolUnion(webFetchTool20260309 = webFetchTool20260309)

        @JvmStatic
        fun ofWebSearchTool20260318(webSearchTool20260318: BetaWebSearchTool20260318) =
            BetaToolUnion(webSearchTool20260318 = webSearchTool20260318)

        @JvmStatic
        fun ofWebFetchTool20260318(webFetchTool20260318: BetaWebFetchTool20260318) =
            BetaToolUnion(webFetchTool20260318 = webFetchTool20260318)

        @JvmStatic
        fun ofAdvisorTool20260301(advisorTool20260301: BetaAdvisorTool20260301) =
            BetaToolUnion(advisorTool20260301 = advisorTool20260301)

        /**
         * Returns an immutable instance of [BetaToolUnion] whose [ofAdvisorTool20260301] variant is
         * built from the given required [model].
         */
        @JvmStatic
        fun ofAdvisorTool20260301(model: Model) =
            ofAdvisorTool20260301(BetaAdvisorTool20260301.of(model))

        /**
         * Alias for calling [ofAdvisorTool20260301] with `Model.of(value)`.
         *
         * You should usually call [ofAdvisorTool20260301] with a well-typed [Model] constant
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        @JvmStatic fun ofAdvisorTool20260301(value: String) = ofAdvisorTool20260301(Model.of(value))

        @JvmStatic
        fun ofSearchToolBm25_20251119(searchToolBm25_20251119: BetaToolSearchToolBm25_20251119) =
            BetaToolUnion(searchToolBm25_20251119 = searchToolBm25_20251119)

        /**
         * Returns an immutable instance of [BetaToolUnion] whose [ofSearchToolBm25_20251119]
         * variant is built from the given required [type].
         */
        @JvmStatic
        fun ofSearchToolBm25_20251119(type: BetaToolSearchToolBm25_20251119.Type) =
            ofSearchToolBm25_20251119(BetaToolSearchToolBm25_20251119.of(type))

        @JvmStatic
        fun ofSearchToolRegex20251119(searchToolRegex20251119: BetaToolSearchToolRegex20251119) =
            BetaToolUnion(searchToolRegex20251119 = searchToolRegex20251119)

        /**
         * Returns an immutable instance of [BetaToolUnion] whose [ofSearchToolRegex20251119]
         * variant is built from the given required [type].
         */
        @JvmStatic
        fun ofSearchToolRegex20251119(type: BetaToolSearchToolRegex20251119.Type) =
            ofSearchToolRegex20251119(BetaToolSearchToolRegex20251119.of(type))

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        @JvmStatic
        fun ofMcpToolset(mcpToolset: BetaMcpToolset) = BetaToolUnion(mcpToolset = mcpToolset)

        /**
         * Returns an immutable instance of [BetaToolUnion] whose [ofMcpToolset] variant is built
         * from the given required [mcpServerName].
         */
        @JvmStatic
        fun ofMcpToolset(mcpServerName: String) = ofMcpToolset(BetaMcpToolset.of(mcpServerName))
    }

    /**
     * An interface that defines how to map each variant of [BetaToolUnion] to a value of type [T].
     */
    interface Visitor<out T> {

        fun visitBetaTool(betaTool: BetaTool): T

        fun visitBash20241022(bash20241022: BetaToolBash20241022): T

        fun visitBash20250124(bash20250124: BetaToolBash20250124): T

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

        fun visitComputerUse20241022(computerUse20241022: BetaToolComputerUse20241022): T

        fun visitMemoryTool20250818(memoryTool20250818: BetaMemoryTool20250818): T

        fun visitComputerUse20250124(computerUse20250124: BetaToolComputerUse20250124): T

        fun visitTextEditor20241022(textEditor20241022: BetaToolTextEditor20241022): T

        fun visitComputerUse20251124(computerUse20251124: BetaToolComputerUse20251124): T

        /**
         * The computer toolset: a single ``tools[]`` entry (carrying no ``name``) that declares the
         * computer tool family. The model is served the family's tool with any members disabled via
         * ``configs`` removed from its schema. Every member is enabled by default, zoom included.
         * The single-tool options ``display_number`` and ``enable_zoom`` are not fields of a
         * toolset entry — it carries only ``type``, ``configs``, and ``cache_control``; zoom is
         * controlled via ``configs.zoom.enabled``.
         */
        fun visitComputerToolset20260801(computerToolset20260801: BetaComputerToolset20260801): T

        fun visitTextEditor20250124(textEditor20250124: BetaToolTextEditor20250124): T

        fun visitTextEditor20250429(textEditor20250429: BetaToolTextEditor20250429): T

        fun visitTextEditor20250728(textEditor20250728: BetaToolTextEditor20250728): T

        fun visitWebSearchTool20250305(webSearchTool20250305: BetaWebSearchTool20250305): T

        fun visitWebFetchTool20250910(webFetchTool20250910: BetaWebFetchTool20250910): T

        fun visitWebSearchTool20260209(webSearchTool20260209: BetaWebSearchTool20260209): T

        fun visitWebFetchTool20260209(webFetchTool20260209: BetaWebFetchTool20260209): T

        /** Web fetch tool with use_cache parameter for bypassing cached content. */
        fun visitWebFetchTool20260309(webFetchTool20260309: BetaWebFetchTool20260309): T

        fun visitWebSearchTool20260318(webSearchTool20260318: BetaWebSearchTool20260318): T

        fun visitWebFetchTool20260318(webFetchTool20260318: BetaWebFetchTool20260318): T

        fun visitAdvisorTool20260301(advisorTool20260301: BetaAdvisorTool20260301): T

        fun visitSearchToolBm25_20251119(
            searchToolBm25_20251119: BetaToolSearchToolBm25_20251119
        ): T

        fun visitSearchToolRegex20251119(
            searchToolRegex20251119: BetaToolSearchToolRegex20251119
        ): T

        /**
         * Configuration for a group of tools from an MCP server.
         *
         * Allows configuring enabled status and defer_loading for all tools from an MCP server,
         * with optional per-tool overrides.
         */
        fun visitMcpToolset(mcpToolset: BetaMcpToolset): T

        /**
         * Maps an unknown variant of [BetaToolUnion] to a value of type [T].
         *
         * An instance of [BetaToolUnion] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolUnion: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolUnion {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaTool>())?.let {
                            BetaToolUnion(betaTool = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolBash20241022>())?.let {
                            BetaToolUnion(bash20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolBash20250124>())?.let {
                            BetaToolUnion(bash20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250522>())?.let {
                            BetaToolUnion(codeExecutionTool20250522 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20250825>())?.let {
                            BetaToolUnion(codeExecutionTool20250825 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20260120>())?.let {
                            BetaToolUnion(codeExecutionTool20260120 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaCodeExecutionTool20260521>())?.let {
                            BetaToolUnion(codeExecutionTool20260521 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaBrowserToolset20260801>())?.let {
                            BetaToolUnion(browserToolset20260801 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20241022>())?.let {
                            BetaToolUnion(computerUse20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaMemoryTool20250818>())?.let {
                            BetaToolUnion(memoryTool20250818 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20250124>())?.let {
                            BetaToolUnion(computerUse20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20241022>())?.let {
                            BetaToolUnion(textEditor20241022 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolComputerUse20251124>())?.let {
                            BetaToolUnion(computerUse20251124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaComputerToolset20260801>())?.let {
                            BetaToolUnion(computerToolset20260801 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250124>())?.let {
                            BetaToolUnion(textEditor20250124 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250429>())?.let {
                            BetaToolUnion(textEditor20250429 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolTextEditor20250728>())?.let {
                            BetaToolUnion(textEditor20250728 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20250305>())?.let {
                            BetaToolUnion(webSearchTool20250305 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20250910>())?.let {
                            BetaToolUnion(webFetchTool20250910 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20260209>())?.let {
                            BetaToolUnion(webSearchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260209>())?.let {
                            BetaToolUnion(webFetchTool20260209 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260309>())?.let {
                            BetaToolUnion(webFetchTool20260309 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebSearchTool20260318>())?.let {
                            BetaToolUnion(webSearchTool20260318 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaWebFetchTool20260318>())?.let {
                            BetaToolUnion(webFetchTool20260318 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaAdvisorTool20260301>())?.let {
                            BetaToolUnion(advisorTool20260301 = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolBm25_20251119>())
                            ?.let { BetaToolUnion(searchToolBm25_20251119 = it, _json = json) },
                        tryDeserialize(node, jacksonTypeRef<BetaToolSearchToolRegex20251119>())
                            ?.let { BetaToolUnion(searchToolRegex20251119 = it, _json = json) },
                        tryDeserialize(node, jacksonTypeRef<BetaMcpToolset>())?.let {
                            BetaToolUnion(mcpToolset = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaToolUnion(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<BetaToolUnion>(BetaToolUnion::class) {

        override fun serialize(
            value: BetaToolUnion,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.betaTool != null -> generator.writeObject(value.betaTool)
                value.bash20241022 != null -> generator.writeObject(value.bash20241022)
                value.bash20250124 != null -> generator.writeObject(value.bash20250124)
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
                value.computerUse20241022 != null ->
                    generator.writeObject(value.computerUse20241022)
                value.memoryTool20250818 != null -> generator.writeObject(value.memoryTool20250818)
                value.computerUse20250124 != null ->
                    generator.writeObject(value.computerUse20250124)
                value.textEditor20241022 != null -> generator.writeObject(value.textEditor20241022)
                value.computerUse20251124 != null ->
                    generator.writeObject(value.computerUse20251124)
                value.computerToolset20260801 != null ->
                    generator.writeObject(value.computerToolset20260801)
                value.textEditor20250124 != null -> generator.writeObject(value.textEditor20250124)
                value.textEditor20250429 != null -> generator.writeObject(value.textEditor20250429)
                value.textEditor20250728 != null -> generator.writeObject(value.textEditor20250728)
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
                value.searchToolBm25_20251119 != null ->
                    generator.writeObject(value.searchToolBm25_20251119)
                value.searchToolRegex20251119 != null ->
                    generator.writeObject(value.searchToolRegex20251119)
                value.mcpToolset != null -> generator.writeObject(value.mcpToolset)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolUnion")
            }
        }
    }
}
