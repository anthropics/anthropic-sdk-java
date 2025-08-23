// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaTextBlockParam
private constructor(
    private val text: JsonField<String>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val citations: JsonField<List<BetaTextCitationParam>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<List<BetaTextCitationParam>> = JsonMissing.of(),
    ) : this(text, type, cacheControl, citations, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun text(): String = text.getRequired("text")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<List<BetaTextCitationParam>> = citations.getOptional("citations")

    /**
     * Returns the raw JSON value of [text].
     *
     * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<List<BetaTextCitationParam>> = citations

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
         * Returns a mutable builder for constructing an instance of [BetaTextBlockParam].
         *
         * The following fields are required:
         * ```java
         * .text()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTextBlockParam]. */
    class Builder internal constructor() {

        private var text: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("text")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<MutableList<BetaTextCitationParam>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTextBlockParam: BetaTextBlockParam) = apply {
            text = betaTextBlockParam.text
            type = betaTextBlockParam.type
            cacheControl = betaTextBlockParam.cacheControl
            citations = betaTextBlockParam.citations.map { it.toMutableList() }
            additionalProperties = betaTextBlockParam.additionalProperties.toMutableMap()
        }

        fun text(text: String) = text(JsonField.of(text))

        /**
         * Sets [Builder.text] to an arbitrary JSON value.
         *
         * You should usually call [Builder.text] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun text(text: JsonField<String>) = apply { this.text = text }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun citations(citations: List<BetaTextCitationParam>?) =
            citations(JsonField.ofNullable(citations))

        /** Alias for calling [Builder.citations] with `citations.orElse(null)`. */
        fun citations(citations: Optional<List<BetaTextCitationParam>>) =
            citations(citations.getOrNull())

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed
         * `List<BetaTextCitationParam>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun citations(citations: JsonField<List<BetaTextCitationParam>>) = apply {
            this.citations = citations.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaTextCitationParam] to [citations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCitation(citation: BetaTextCitationParam) = apply {
            citations =
                (citations ?: JsonField.of(mutableListOf())).also {
                    checkKnown("citations", it).add(citation)
                }
        }

        /** Alias for calling [addCitation] with `citation.toParam()`. */
        fun addCitation(citation: BetaTextCitation) = addCitation(citation.toParam())

        /** Alias for calling [addCitation] with `BetaTextCitation.ofCharLocation(charLocation)`. */
        fun addCitation(charLocation: BetaCitationCharLocation) =
            addCitation(BetaTextCitation.ofCharLocation(charLocation))

        /** Alias for calling [addCitation] with `BetaTextCitation.ofPageLocation(pageLocation)`. */
        fun addCitation(pageLocation: BetaCitationPageLocation) =
            addCitation(BetaTextCitation.ofPageLocation(pageLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun addCitation(contentBlockLocation: BetaCitationContentBlockLocation) =
            addCitation(BetaTextCitation.ofContentBlockLocation(contentBlockLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun addCitation(webSearchResultLocation: BetaCitationsWebSearchResultLocation) =
            addCitation(BetaTextCitation.ofWebSearchResultLocation(webSearchResultLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofSearchResultLocation(searchResultLocation)`.
         */
        fun addCitation(searchResultLocation: BetaCitationSearchResultLocation) =
            addCitation(BetaTextCitation.ofSearchResultLocation(searchResultLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitationParam.ofCharLocation(charLocation)`.
         */
        fun addCitation(charLocation: BetaCitationCharLocationParam) =
            addCitation(BetaTextCitationParam.ofCharLocation(charLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitationParam.ofPageLocation(pageLocation)`.
         */
        fun addCitation(pageLocation: BetaCitationPageLocationParam) =
            addCitation(BetaTextCitationParam.ofPageLocation(pageLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitationParam.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun addCitation(contentBlockLocation: BetaCitationContentBlockLocationParam) =
            addCitation(BetaTextCitationParam.ofContentBlockLocation(contentBlockLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitationParam.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun addCitation(webSearchResultLocation: BetaCitationWebSearchResultLocationParam) =
            addCitation(BetaTextCitationParam.ofWebSearchResultLocation(webSearchResultLocation))

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitationParam.ofSearchResultLocation(searchResultLocation)`.
         */
        fun addCitation(searchResultLocation: BetaCitationSearchResultLocationParam) =
            addCitation(BetaTextCitationParam.ofSearchResultLocation(searchResultLocation))

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
         * Returns an immutable instance of [BetaTextBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .text()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTextBlockParam =
            BetaTextBlockParam(
                checkRequired("text", text),
                type,
                cacheControl,
                (citations ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaTextBlockParam = apply {
        if (validated) {
            return@apply
        }

        text()
        _type().let {
            if (it != JsonValue.from("text")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.forEach { it.validate() } }
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
        (if (text.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("text")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (citations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTextBlockParam &&
            text == other.text &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            citations == other.citations &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(text, type, cacheControl, citations, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTextBlockParam{text=$text, type=$type, cacheControl=$cacheControl, citations=$citations, additionalProperties=$additionalProperties}"
}
