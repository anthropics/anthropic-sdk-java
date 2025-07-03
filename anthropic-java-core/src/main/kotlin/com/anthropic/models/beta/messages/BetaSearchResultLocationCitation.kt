// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaSearchResultLocationCitation
private constructor(
    private val citedText: JsonField<String>,
    private val endBlockIndex: JsonField<Long>,
    private val searchResultIndex: JsonField<Long>,
    private val source: JsonField<String>,
    private val startBlockIndex: JsonField<Long>,
    private val title: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cited_text") @ExcludeMissing citedText: JsonField<String> = JsonMissing.of(),
        @JsonProperty("end_block_index")
        @ExcludeMissing
        endBlockIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("search_result_index")
        @ExcludeMissing
        searchResultIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("start_block_index")
        @ExcludeMissing
        startBlockIndex: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        citedText,
        endBlockIndex,
        searchResultIndex,
        source,
        startBlockIndex,
        title,
        type,
        mutableMapOf(),
    )

    fun toParam(): BetaSearchResultLocationCitationParam =
        BetaSearchResultLocationCitationParam.builder()
            .citedText(_citedText())
            .endBlockIndex(_endBlockIndex())
            .searchResultIndex(_searchResultIndex())
            .source(_source())
            .startBlockIndex(_startBlockIndex())
            .title(_title())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citedText(): String = citedText.getRequired("cited_text")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endBlockIndex(): Long = endBlockIndex.getRequired("end_block_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun searchResultIndex(): Long = searchResultIndex.getRequired("search_result_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): String = source.getRequired("source")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startBlockIndex(): Long = startBlockIndex.getRequired("start_block_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = title.getOptional("title")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("search_result_location")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [citedText].
     *
     * Unlike [citedText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cited_text") @ExcludeMissing fun _citedText(): JsonField<String> = citedText

    /**
     * Returns the raw JSON value of [endBlockIndex].
     *
     * Unlike [endBlockIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_block_index")
    @ExcludeMissing
    fun _endBlockIndex(): JsonField<Long> = endBlockIndex

    /**
     * Returns the raw JSON value of [searchResultIndex].
     *
     * Unlike [searchResultIndex], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("search_result_index")
    @ExcludeMissing
    fun _searchResultIndex(): JsonField<Long> = searchResultIndex

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [startBlockIndex].
     *
     * Unlike [startBlockIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_block_index")
    @ExcludeMissing
    fun _startBlockIndex(): JsonField<Long> = startBlockIndex

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
         * [BetaSearchResultLocationCitation].
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .endBlockIndex()
         * .searchResultIndex()
         * .source()
         * .startBlockIndex()
         * .title()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSearchResultLocationCitation]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var endBlockIndex: JsonField<Long>? = null
        private var searchResultIndex: JsonField<Long>? = null
        private var source: JsonField<String>? = null
        private var startBlockIndex: JsonField<Long>? = null
        private var title: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("search_result_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSearchResultLocationCitation: BetaSearchResultLocationCitation) =
            apply {
                citedText = betaSearchResultLocationCitation.citedText
                endBlockIndex = betaSearchResultLocationCitation.endBlockIndex
                searchResultIndex = betaSearchResultLocationCitation.searchResultIndex
                source = betaSearchResultLocationCitation.source
                startBlockIndex = betaSearchResultLocationCitation.startBlockIndex
                title = betaSearchResultLocationCitation.title
                type = betaSearchResultLocationCitation.type
                additionalProperties =
                    betaSearchResultLocationCitation.additionalProperties.toMutableMap()
            }

        fun citedText(citedText: String) = citedText(JsonField.of(citedText))

        /**
         * Sets [Builder.citedText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citedText] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun citedText(citedText: JsonField<String>) = apply { this.citedText = citedText }

        fun endBlockIndex(endBlockIndex: Long) = endBlockIndex(JsonField.of(endBlockIndex))

        /**
         * Sets [Builder.endBlockIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endBlockIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endBlockIndex(endBlockIndex: JsonField<Long>) = apply {
            this.endBlockIndex = endBlockIndex
        }

        fun searchResultIndex(searchResultIndex: Long) =
            searchResultIndex(JsonField.of(searchResultIndex))

        /**
         * Sets [Builder.searchResultIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.searchResultIndex] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun searchResultIndex(searchResultIndex: JsonField<Long>) = apply {
            this.searchResultIndex = searchResultIndex
        }

        fun source(source: String) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<String>) = apply { this.source = source }

        fun startBlockIndex(startBlockIndex: Long) = startBlockIndex(JsonField.of(startBlockIndex))

        /**
         * Sets [Builder.startBlockIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startBlockIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun startBlockIndex(startBlockIndex: JsonField<Long>) = apply {
            this.startBlockIndex = startBlockIndex
        }

        fun title(title: String?) = title(JsonField.ofNullable(title))

        /** Alias for calling [Builder.title] with `title.orElse(null)`. */
        fun title(title: Optional<String>) = title(title.getOrNull())

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
         * JsonValue.from("search_result_location")
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
         * Returns an immutable instance of [BetaSearchResultLocationCitation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .endBlockIndex()
         * .searchResultIndex()
         * .source()
         * .startBlockIndex()
         * .title()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSearchResultLocationCitation =
            BetaSearchResultLocationCitation(
                checkRequired("citedText", citedText),
                checkRequired("endBlockIndex", endBlockIndex),
                checkRequired("searchResultIndex", searchResultIndex),
                checkRequired("source", source),
                checkRequired("startBlockIndex", startBlockIndex),
                checkRequired("title", title),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaSearchResultLocationCitation = apply {
        if (validated) {
            return@apply
        }

        citedText()
        endBlockIndex()
        searchResultIndex()
        source()
        startBlockIndex()
        title()
        _type().let {
            if (it != JsonValue.from("search_result_location")) {
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
        (if (citedText.asKnown().isPresent) 1 else 0) +
            (if (endBlockIndex.asKnown().isPresent) 1 else 0) +
            (if (searchResultIndex.asKnown().isPresent) 1 else 0) +
            (if (source.asKnown().isPresent) 1 else 0) +
            (if (startBlockIndex.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("search_result_location")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaSearchResultLocationCitation && citedText == other.citedText && endBlockIndex == other.endBlockIndex && searchResultIndex == other.searchResultIndex && source == other.source && startBlockIndex == other.startBlockIndex && title == other.title && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, endBlockIndex, searchResultIndex, source, startBlockIndex, title, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSearchResultLocationCitation{citedText=$citedText, endBlockIndex=$endBlockIndex, searchResultIndex=$searchResultIndex, source=$source, startBlockIndex=$startBlockIndex, title=$title, type=$type, additionalProperties=$additionalProperties}"
}
