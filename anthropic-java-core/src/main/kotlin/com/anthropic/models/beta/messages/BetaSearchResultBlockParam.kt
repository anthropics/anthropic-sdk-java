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

class BetaSearchResultBlockParam
private constructor(
    private val content: JsonField<List<BetaTextBlockParam>>,
    private val source: JsonField<String>,
    private val title: JsonField<String>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val citations: JsonField<BetaCitationsConfigParam>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<List<BetaTextBlockParam>> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of(),
    ) : this(content, source, title, type, cacheControl, citations, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): List<BetaTextBlockParam> = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): String = source.getRequired("source")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun title(): String = title.getRequired("title")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("search_result")
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
    fun citations(): Optional<BetaCitationsConfigParam> = citations.getOptional("citations")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<List<BetaTextBlockParam>> = content

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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
    fun _citations(): JsonField<BetaCitationsConfigParam> = citations

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
         * Returns a mutable builder for constructing an instance of [BetaSearchResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .source()
         * .title()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSearchResultBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<MutableList<BetaTextBlockParam>>? = null
        private var source: JsonField<String>? = null
        private var title: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("search_result")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSearchResultBlockParam: BetaSearchResultBlockParam) = apply {
            content = betaSearchResultBlockParam.content.map { it.toMutableList() }
            source = betaSearchResultBlockParam.source
            title = betaSearchResultBlockParam.title
            type = betaSearchResultBlockParam.type
            cacheControl = betaSearchResultBlockParam.cacheControl
            citations = betaSearchResultBlockParam.citations
            additionalProperties = betaSearchResultBlockParam.additionalProperties.toMutableMap()
        }

        fun content(content: List<BetaTextBlockParam>) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed `List<BetaTextBlockParam>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun content(content: JsonField<List<BetaTextBlockParam>>) = apply {
            this.content = content.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaTextBlockParam] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: BetaTextBlockParam) = apply {
            this.content =
                (this.content ?: JsonField.of(mutableListOf())).also {
                    checkKnown("content", it).add(content)
                }
        }

        /** Alias for calling [addContent] with `content.toParam()`. */
        fun addContent(content: BetaTextBlock) = addContent(content.toParam())

        fun source(source: String) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<String>) = apply { this.source = source }

        fun title(title: String) = title(JsonField.of(title))

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { this.title = title }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("search_result")
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

        fun citations(citations: BetaCitationsConfigParam) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [BetaCitationsConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<BetaCitationsConfigParam>) = apply {
            this.citations = citations
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
         * Returns an immutable instance of [BetaSearchResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .source()
         * .title()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSearchResultBlockParam =
            BetaSearchResultBlockParam(
                checkRequired("content", content).map { it.toImmutable() },
                checkRequired("source", source),
                checkRequired("title", title),
                type,
                cacheControl,
                citations,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaSearchResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().forEach { it.validate() }
        source()
        title()
        _type().let {
            if (it != JsonValue.from("search_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.validate() }
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
        (content.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (source.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("search_result")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (citations.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaSearchResultBlockParam && content == other.content && source == other.source && title == other.title && type == other.type && cacheControl == other.cacheControl && citations == other.citations && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(content, source, title, type, cacheControl, citations, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSearchResultBlockParam{content=$content, source=$source, title=$title, type=$type, cacheControl=$cacheControl, citations=$citations, additionalProperties=$additionalProperties}"
}
