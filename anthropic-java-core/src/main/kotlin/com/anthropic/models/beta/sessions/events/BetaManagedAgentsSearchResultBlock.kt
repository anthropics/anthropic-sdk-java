package com.anthropic.models.beta.sessions.events

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
import kotlin.jvm.optionals.getOrNull

/** A block containing a web search result. */
class BetaManagedAgentsSearchResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val citations: JsonField<BetaManagedAgentsSearchResultCitations>,
    private val content: JsonField<List<BetaManagedAgentsSearchResultContent>>,
    private val source: JsonField<String>,
    private val title: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<BetaManagedAgentsSearchResultCitations> = JsonMissing.of(),
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<List<BetaManagedAgentsSearchResultContent>> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(citations, content, source, title, type, mutableMapOf())

    /**
     * Citation settings for this search result.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citations(): BetaManagedAgentsSearchResultCitations = citations.getRequired("citations")

    /**
     * Array of text content blocks from the search result.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): List<BetaManagedAgentsSearchResultContent> = content.getRequired("content")

    /**
     * The URL source of the search result.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): String = source.getRequired("source")

    /**
     * The title of the search result.
     *
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
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<BetaManagedAgentsSearchResultCitations> = citations

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<List<BetaManagedAgentsSearchResultContent>> = content

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsSearchResultBlock].
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .content()
         * .source()
         * .title()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSearchResultBlock]. */
    class Builder internal constructor() {

        private var citations: JsonField<BetaManagedAgentsSearchResultCitations>? = null
        private var content: JsonField<MutableList<BetaManagedAgentsSearchResultContent>>? = null
        private var source: JsonField<String>? = null
        private var title: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("search_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSearchResultBlock: BetaManagedAgentsSearchResultBlock) =
            apply {
                citations = betaManagedAgentsSearchResultBlock.citations
                content =
                    betaManagedAgentsSearchResultBlock.content
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                source = betaManagedAgentsSearchResultBlock.source
                title = betaManagedAgentsSearchResultBlock.title
                type = betaManagedAgentsSearchResultBlock.type
                additionalProperties =
                    betaManagedAgentsSearchResultBlock.additionalProperties.toMutableMap()
            }

        /** Citation settings for this search result. */
        fun citations(citations: BetaManagedAgentsSearchResultCitations) =
            citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed
         * [BetaManagedAgentsSearchResultCitations] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun citations(citations: JsonField<BetaManagedAgentsSearchResultCitations>) = apply {
            this.citations = citations
        }

        /** Array of text content blocks from the search result. */
        fun content(content: List<BetaManagedAgentsSearchResultContent>) =
            content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed
         * `List<BetaManagedAgentsSearchResultContent>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<List<BetaManagedAgentsSearchResultContent>>) = apply {
            this.content = content.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsSearchResultContent] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: BetaManagedAgentsSearchResultContent) = apply {
            this.content =
                (this.content ?: JsonField.of(mutableListOf())).also {
                    checkKnown("content", it).add(content)
                }
        }

        /** The URL source of the search result. */
        fun source(source: String) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<String>) = apply { this.source = source }

        /** The title of the search result. */
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
         * Returns an immutable instance of [BetaManagedAgentsSearchResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .content()
         * .source()
         * .title()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSearchResultBlock =
            BetaManagedAgentsSearchResultBlock(
                checkRequired("citations", citations),
                checkRequired("content", content).map { it.toImmutable() },
                checkRequired("source", source),
                checkRequired("title", title),
                type,
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
    fun validate(): BetaManagedAgentsSearchResultBlock = apply {
        if (validated) {
            return@apply
        }

        citations().validate()
        content().forEach { it.validate() }
        source()
        title()
        _type().let {
            if (it != JsonValue.from("search_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (citations.asKnown().getOrNull()?.validity() ?: 0) +
            (content.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (source.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("search_result")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSearchResultBlock &&
            citations == other.citations &&
            content == other.content &&
            source == other.source &&
            title == other.title &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(citations, content, source, title, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSearchResultBlock{citations=$citations, content=$content, source=$source, title=$title, type=$type, additionalProperties=$additionalProperties}"
}
