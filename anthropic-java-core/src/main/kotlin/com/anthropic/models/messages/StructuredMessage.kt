package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import java.util.Objects
import java.util.Optional

/**
 * A wrapper for [Message] that provides type-safe access to the [content] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredMessage<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    @get:JvmName("rawMessage") val rawMessage: Message,
) {

    /** @see Message.id */
    fun id(): String = rawMessage.id()

    private val content by lazy {
        rawMessage._content().map { contentBlock ->
            contentBlock.map { StructuredContentBlock<T>(outputType, it) }
        }
    }

    /** @see Message.content */
    fun content(): List<StructuredContentBlock<T>> = content.getRequired("content")

    /** @see Message.model */
    fun model(): Model = rawMessage.model()

    /** @see Message._role */
    fun _role(): JsonValue = rawMessage._role()

    /** @see Message.stopReason */
    fun stopReason(): Optional<StopReason> = rawMessage.stopReason()

    /** @see Message.stopSequence */
    fun stopSequence(): Optional<String> = rawMessage.stopSequence()

    /** @see Message._type */
    fun _type(): JsonValue = rawMessage._type()

    /** @see Message.container */
    fun container(): Optional<Container> = rawMessage.container()

    /** @see Message.usage */
    fun usage(): Usage = rawMessage.usage()

    /** @see Message._id */
    fun _id(): JsonField<String> = rawMessage._id()

    /** @see Message._content */
    fun _content(): JsonField<List<StructuredContentBlock<T>>> = content

    /** @see Message._container */
    fun _container(): JsonField<Container> = rawMessage._container()

    /** @see Message._model */
    fun _model(): JsonField<Model> = rawMessage._model()

    /** @see Message._stopReason */
    fun _stopReason(): JsonField<StopReason> = rawMessage._stopReason()

    /** @see Message._stopSequence */
    fun _stopSequence(): JsonField<String> = rawMessage._stopSequence()

    /** @see Message._usage */
    fun _usage(): JsonField<Usage> = rawMessage._usage()

    /** @see Message._additionalProperties */
    fun _additionalProperties(): Map<String, JsonValue> = rawMessage._additionalProperties()

    fun validate() = apply {
        content().forEach { it.validate() }
        rawMessage.validate()
    }

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

        return other is StructuredMessage<*> &&
            outputType == other.outputType &&
            rawMessage == other.rawMessage
    }

    private val hashCode: Int by lazy { Objects.hash(outputType, rawMessage) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "${javaClass.simpleName}{outputType=$outputType, rawMessage=$rawMessage}"
}
