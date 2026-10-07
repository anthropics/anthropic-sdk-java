package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.core.outputTypeFromJson
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A wrapper for [BetaTextBlock] that provides type-safe access to the [text] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredTextBlock<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    private val delegate: BetaTextBlock,
) {

    @get:JvmName("rawTextBlock")
    val rawTextBlock: BetaTextBlock
        get() = delegate

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     * @see BetaTextBlock.citations
     */
    fun citations(): Optional<List<BetaTextCitation>> = delegate.citations()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     * @see BetaTextBlock.text
     */
    fun text(): T = text.getRequired("text")

    private val text: JsonField<T> by lazy {
        delegate._text().map { outputTypeFromJson<T>(it, outputType) }
    }

    /** @see BetaTextBlock._type */
    fun _type(): JsonValue = delegate._type()

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _citations(): JsonField<List<BetaTextCitation>> = delegate._citations()

    /**
     * Returns the raw JSON value of [text].
     *
     * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _text(): JsonField<T> = text

    /** @see BetaTextBlock._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = delegate._additionalProperties()

    /** @see BetaTextBlock.validate */
    fun validate(): StructuredTextBlock<T> = apply { delegate.validate() }

    /** @see BetaTextBlock.isValid */
    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    fun toBuilder() = Builder(outputType).from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [StructuredTextBlock].
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .text()
         * ```
         */
        @JvmStatic fun <T : Any> builder(outputType: Class<T>) = Builder(outputType)
    }

    /** A builder for [StructuredTextBlock]. */
    class Builder<T : Any> internal constructor(private val outputType: Class<T>) {

        private var delegate: BetaTextBlock.Builder = BetaTextBlock.builder()

        @JvmSynthetic
        internal fun from(structuredTextBlock: StructuredTextBlock<T>) = apply {
            delegate = structuredTextBlock.delegate.toBuilder()
        }

        /** @see BetaTextBlock.Builder.citations */
        fun citations(citations: List<BetaTextCitation>?) = apply { delegate.citations(citations) }

        /** Alias for calling [Builder.citations] with `citations.orElse(null)`. */
        fun citations(citations: Optional<List<BetaTextCitation>>) =
            citations(citations.getOrNull())

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed `List<BetaTextCitation>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<List<BetaTextCitation>>) = apply {
            delegate.citations(citations)
        }

        /**
         * Adds a single [BetaTextCitation] to [citations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCitation(citation: BetaTextCitation) = apply { delegate.addCitation(citation) }

        /** Alias for calling [addCitation] with `BetaTextCitation.ofCharLocation(charLocation)`. */
        fun addCitation(charLocation: BetaCitationCharLocation) = apply {
            delegate.addCitation(charLocation)
        }

        /** Alias for calling [addCitation] with `BetaTextCitation.ofPageLocation(pageLocation)`. */
        fun addCitation(pageLocation: BetaCitationPageLocation) = apply {
            delegate.addCitation(pageLocation)
        }

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofContentBlockLocation(contentBlockLocation)`.
         */
        fun addCitation(contentBlockLocation: BetaCitationContentBlockLocation) = apply {
            delegate.addCitation(contentBlockLocation)
        }

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofWebSearchResultLocation(webSearchResultLocation)`.
         */
        fun addCitation(webSearchResultLocation: BetaCitationsWebSearchResultLocation) = apply {
            delegate.addCitation(webSearchResultLocation)
        }

        /**
         * Alias for calling [addCitation] with
         * `BetaTextCitation.ofSearchResultLocation(searchResultLocation)`.
         */
        fun addCitation(searchResultLocation: BetaCitationSearchResultLocation) = apply {
            delegate.addCitation(searchResultLocation)
        }

        /** @see BetaTextBlock.Builder.text */
        fun text(text: String) = apply { delegate.text(text) }

        /**
         * Sets [Builder.text] to an arbitrary JSON value.
         *
         * You should usually call [Builder.text] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun text(text: JsonField<String>) = apply { delegate.text(text) }

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
        fun type(type: JsonValue) = apply { delegate.type(type) }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.additionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            delegate.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            delegate.putAllAdditionalProperties(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { delegate.removeAdditionalProperty(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            delegate.removeAllAdditionalProperties(keys)
        }

        /**
         * Returns an immutable instance of [StructuredTextBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .text()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): StructuredTextBlock<T> = StructuredTextBlock(outputType, delegate.build())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredTextBlock<*> &&
            outputType == other.outputType &&
            delegate == other.delegate
    }

    override fun hashCode(): Int = Objects.hash(outputType, delegate)

    override fun toString() = "StructuredTextBlock{outputType=$outputType, rawTextBlock=$delegate}"
}
