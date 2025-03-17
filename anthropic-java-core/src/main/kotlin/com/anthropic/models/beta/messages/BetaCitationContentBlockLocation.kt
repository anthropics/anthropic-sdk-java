// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class BetaCitationContentBlockLocation
@JsonCreator
private constructor(
    @JsonProperty("cited_text")
    @ExcludeMissing
    private val citedText: JsonField<String> = JsonMissing.of(),
    @JsonProperty("document_index")
    @ExcludeMissing
    private val documentIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("document_title")
    @ExcludeMissing
    private val documentTitle: JsonField<String> = JsonMissing.of(),
    @JsonProperty("end_block_index")
    @ExcludeMissing
    private val endBlockIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("start_block_index")
    @ExcludeMissing
    private val startBlockIndex: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun citedText(): String = citedText.getRequired("cited_text")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun documentIndex(): Long = documentIndex.getRequired("document_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun documentTitle(): Optional<String> =
        Optional.ofNullable(documentTitle.getNullable("document_title"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endBlockIndex(): Long = endBlockIndex.getRequired("end_block_index")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startBlockIndex(): Long = startBlockIndex.getRequired("start_block_index")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("content_block_location")
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
     * Returns the raw JSON value of [documentIndex].
     *
     * Unlike [documentIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_index")
    @ExcludeMissing
    fun _documentIndex(): JsonField<Long> = documentIndex

    /**
     * Returns the raw JSON value of [documentTitle].
     *
     * Unlike [documentTitle], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_title")
    @ExcludeMissing
    fun _documentTitle(): JsonField<String> = documentTitle

    /**
     * Returns the raw JSON value of [endBlockIndex].
     *
     * Unlike [endBlockIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("end_block_index")
    @ExcludeMissing
    fun _endBlockIndex(): JsonField<Long> = endBlockIndex

    /**
     * Returns the raw JSON value of [startBlockIndex].
     *
     * Unlike [startBlockIndex], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_block_index")
    @ExcludeMissing
    fun _startBlockIndex(): JsonField<Long> = startBlockIndex

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaCitationContentBlockLocation = apply {
        if (validated) {
            return@apply
        }

        citedText()
        documentIndex()
        documentTitle()
        endBlockIndex()
        startBlockIndex()
        _type().let {
            if (it != JsonValue.from("content_block_location")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaCitationContentBlockLocation].
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endBlockIndex()
         * .startBlockIndex()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCitationContentBlockLocation]. */
    class Builder internal constructor() {

        private var citedText: JsonField<String>? = null
        private var documentIndex: JsonField<Long>? = null
        private var documentTitle: JsonField<String>? = null
        private var endBlockIndex: JsonField<Long>? = null
        private var startBlockIndex: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("content_block_location")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCitationContentBlockLocation: BetaCitationContentBlockLocation) =
            apply {
                citedText = betaCitationContentBlockLocation.citedText
                documentIndex = betaCitationContentBlockLocation.documentIndex
                documentTitle = betaCitationContentBlockLocation.documentTitle
                endBlockIndex = betaCitationContentBlockLocation.endBlockIndex
                startBlockIndex = betaCitationContentBlockLocation.startBlockIndex
                type = betaCitationContentBlockLocation.type
                additionalProperties =
                    betaCitationContentBlockLocation.additionalProperties.toMutableMap()
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

        fun documentIndex(documentIndex: Long) = documentIndex(JsonField.of(documentIndex))

        /**
         * Sets [Builder.documentIndex] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentIndex] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun documentIndex(documentIndex: JsonField<Long>) = apply {
            this.documentIndex = documentIndex
        }

        fun documentTitle(documentTitle: String?) =
            documentTitle(JsonField.ofNullable(documentTitle))

        /** Alias for calling [Builder.documentTitle] with `documentTitle.orElse(null)`. */
        fun documentTitle(documentTitle: Optional<String>) =
            documentTitle(documentTitle.getOrNull())

        /**
         * Sets [Builder.documentTitle] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentTitle] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun documentTitle(documentTitle: JsonField<String>) = apply {
            this.documentTitle = documentTitle
        }

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("content_block_location")
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
         * Returns an immutable instance of [BetaCitationContentBlockLocation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citedText()
         * .documentIndex()
         * .documentTitle()
         * .endBlockIndex()
         * .startBlockIndex()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCitationContentBlockLocation =
            BetaCitationContentBlockLocation(
                checkRequired("citedText", citedText),
                checkRequired("documentIndex", documentIndex),
                checkRequired("documentTitle", documentTitle),
                checkRequired("endBlockIndex", endBlockIndex),
                checkRequired("startBlockIndex", startBlockIndex),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCitationContentBlockLocation && citedText == other.citedText && documentIndex == other.documentIndex && documentTitle == other.documentTitle && endBlockIndex == other.endBlockIndex && startBlockIndex == other.startBlockIndex && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(citedText, documentIndex, documentTitle, endBlockIndex, startBlockIndex, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCitationContentBlockLocation{citedText=$citedText, documentIndex=$documentIndex, documentTitle=$documentTitle, endBlockIndex=$endBlockIndex, startBlockIndex=$startBlockIndex, type=$type, additionalProperties=$additionalProperties}"
}
