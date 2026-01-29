package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.core.outputTypeFromJson
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional

/**
 * A wrapper for [TextBlock] that provides type-safe access to the [text] when using the _Structured
 * Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class. See the SDK
 * documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredTextBlock<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    @get:JvmName("rawTextBlock") val rawTextBlock: TextBlock,
) {
    /** @see TextBlock.citations */
    fun citations(): Optional<List<TextCitation>> = rawTextBlock.citations()

    private val text: JsonField<T> by lazy {
        rawTextBlock._text().map { outputTypeFromJson<T>(it, outputType) }
    }

    /** @see TextBlock.text */
    fun text(): T = text.getRequired("text")

    /** @see TextBlock._type */
    fun _type(): JsonValue = rawTextBlock._type()

    /** @see TextBlock._citations */
    fun _citations(): JsonField<List<TextCitation>> = rawTextBlock._citations()

    /** @see TextBlock._text */
    fun _text(): JsonField<T> = text

    /** @see TextBlock._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = rawTextBlock._additionalProperties()

    fun validate(): StructuredTextBlock<T> = apply { rawTextBlock.validate() }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (_: AnthropicInvalidDataException) {
            false
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is StructuredTextBlock<*> &&
            outputType == other.outputType &&
            rawTextBlock == other.rawTextBlock
    }

    private val hashCode: Int by lazy { Objects.hash(outputType, rawTextBlock) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "${javaClass.simpleName}{outputType=$outputType, rawTextBlock=$rawTextBlock}"
}
