package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import java.util.Objects
import java.util.Optional

/**
 * A wrapper for [BetaMessage] that provides type-safe access to the [content] when using the
 * _Structured Outputs_ feature to deserialize a JSON response to an instance of an arbitrary class.
 * See the SDK documentation for more details on _Structured Outputs_.
 *
 * @param T The type of the class to which the JSON data in the response will be deserialized.
 */
class StructuredMessage<T : Any>
internal constructor(
    @get:JvmName("outputType") val outputType: Class<T>,
    @get:JvmName("rawMessage") val rawMessage: BetaMessage,
) {

    /** @see BetaMessage.id */
    fun id(): String = rawMessage.id()

    /** @see BetaMessage.container */
    fun container(): Optional<BetaContainer> = rawMessage.container()

    private val content by lazy {
        rawMessage._content().map { contentBlock ->
            contentBlock.map { StructuredContentBlock<T>(outputType, it) }
        }
    }

    /** @see BetaMessage.content */
    fun content(): List<StructuredContentBlock<T>> = content.getRequired("content")

    /** @see BetaMessage.contextManagement */
    fun contextManagement(): Optional<BetaContextManagementResponse> =
        rawMessage.contextManagement()

    /** @see BetaMessage.model */
    fun model(): Model = rawMessage.model()

    /** @see BetaMessage._role */
    fun _role(): JsonValue = rawMessage._role()

    /** @see BetaMessage.stopReason */
    fun stopReason(): Optional<BetaStopReason> = rawMessage.stopReason()

    /** @see BetaMessage.stopSequence */
    fun stopSequence(): Optional<String> = rawMessage.stopSequence()

    /** @see BetaMessage._type */
    fun _type(): JsonValue = rawMessage._type()

    /** @see BetaMessage.usage */
    fun usage(): BetaUsage = rawMessage.usage()

    /** @see BetaMessage._id */
    fun _id(): JsonField<String> = rawMessage._id()

    /** @see BetaMessage._container */
    fun _container(): JsonField<BetaContainer> = rawMessage._container()

    /** @see BetaMessage._content */
    fun _content(): JsonField<List<StructuredContentBlock<T>>> = content

    /** @see BetaMessage._contextManagement */
    fun _contextManagement(): JsonField<BetaContextManagementResponse> =
        rawMessage._contextManagement()

    /** @see BetaMessage._model */
    fun _model(): JsonField<Model> = rawMessage._model()

    /** @see BetaMessage._stopReason */
    fun _stopReason(): JsonField<BetaStopReason> = rawMessage._stopReason()

    /** @see BetaMessage._stopSequence */
    fun _stopSequence(): JsonField<String> = rawMessage._stopSequence()

    /** @see BetaMessage._usage */
    fun _usage(): JsonField<BetaUsage> = rawMessage._usage()

    /** @see BetaMessage._additionalProperties */
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
