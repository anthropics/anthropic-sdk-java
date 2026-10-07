package com.anthropic.models.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** Web search and code execution tool support, with one entry per tool. */
class ServerToolsCapability
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val codeExecution: JsonField<CapabilitySupport>,
    private val supported: JsonField<Boolean>,
    private val webSearch: JsonField<CapabilitySupport>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("code_execution")
        @ExcludeMissing
        codeExecution: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("web_search")
        @ExcludeMissing
        webSearch: JsonField<CapabilitySupport> = JsonMissing.of(),
    ) : this(codeExecution, supported, webSearch, mutableMapOf())

    /**
     * Whether the model supports the code execution tool: true when the model supports at least one
     * version of the tool, not necessarily every version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun codeExecution(): CapabilitySupport = codeExecution.getRequired("code_execution")

    /**
     * Whether this capability is supported by the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Whether the model supports the web search tool: true when the model supports at least one
     * version of the tool, not necessarily every version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun webSearch(): CapabilitySupport = webSearch.getRequired("web_search")

    /**
     * Returns the raw JSON value of [codeExecution].
     *
     * Unlike [codeExecution], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("code_execution")
    @ExcludeMissing
    fun _codeExecution(): JsonField<CapabilitySupport> = codeExecution

    /**
     * Returns the raw JSON value of [supported].
     *
     * Unlike [supported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("supported") @ExcludeMissing fun _supported(): JsonField<Boolean> = supported

    /**
     * Returns the raw JSON value of [webSearch].
     *
     * Unlike [webSearch], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("web_search")
    @ExcludeMissing
    fun _webSearch(): JsonField<CapabilitySupport> = webSearch

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ServerToolsCapability].
         *
         * The following fields are required:
         * ```java
         * .codeExecution()
         * .supported()
         * .webSearch()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ServerToolsCapability]. */
    class Builder internal constructor() {

        private var codeExecution: JsonField<CapabilitySupport>? = null
        private var supported: JsonField<Boolean>? = null
        private var webSearch: JsonField<CapabilitySupport>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(serverToolsCapability: ServerToolsCapability) = apply {
            codeExecution = serverToolsCapability.codeExecution
            supported = serverToolsCapability.supported
            webSearch = serverToolsCapability.webSearch
            additionalProperties = serverToolsCapability.additionalProperties.toMutableMap()
        }

        /**
         * Whether the model supports the code execution tool: true when the model supports at least
         * one version of the tool, not necessarily every version.
         */
        fun codeExecution(codeExecution: CapabilitySupport) =
            codeExecution(JsonField.of(codeExecution))

        /**
         * Sets [Builder.codeExecution] to an arbitrary JSON value.
         *
         * You should usually call [Builder.codeExecution] with a well-typed [CapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun codeExecution(codeExecution: JsonField<CapabilitySupport>) = apply {
            this.codeExecution = codeExecution
        }

        /** Whether this capability is supported by the model. */
        fun supported(supported: Boolean) = supported(JsonField.of(supported))

        /**
         * Sets [Builder.supported] to an arbitrary JSON value.
         *
         * You should usually call [Builder.supported] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun supported(supported: JsonField<Boolean>) = apply { this.supported = supported }

        /**
         * Whether the model supports the web search tool: true when the model supports at least one
         * version of the tool, not necessarily every version.
         */
        fun webSearch(webSearch: CapabilitySupport) = webSearch(JsonField.of(webSearch))

        /**
         * Sets [Builder.webSearch] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webSearch] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun webSearch(webSearch: JsonField<CapabilitySupport>) = apply {
            this.webSearch = webSearch
        }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [ServerToolsCapability].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .codeExecution()
         * .supported()
         * .webSearch()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ServerToolsCapability =
            ServerToolsCapability(
                checkRequired("codeExecution", codeExecution),
                checkRequired("supported", supported),
                checkRequired("webSearch", webSearch),
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): ServerToolsCapability = apply {
        if (validated) {
            return@apply
        }

        codeExecution().validate()
        supported()
        webSearch().validate()
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
        (codeExecution.asKnown().getOrNull()?.validity() ?: 0) +
            (if (supported.asKnown().isPresent) 1 else 0) +
            (webSearch.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ServerToolsCapability &&
            codeExecution == other.codeExecution &&
            supported == other.supported &&
            webSearch == other.webSearch &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(codeExecution, supported, webSearch, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ServerToolsCapability{codeExecution=$codeExecution, supported=$supported, webSearch=$webSearch, additionalProperties=$additionalProperties}"
}
