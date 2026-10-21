package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = BetaMessageParam.Deserializer::class)
@JsonSerialize(using = BetaMessageParam.Serializer::class)
class BetaMessageParam
private constructor(
    private val user: BetaUserMessageParam? = null,
    private val assistant: BetaAssistantMessageParam? = null,
    private val system: BetaSystemMessageParam? = null,
    private val _json: JsonValue? = null,
) {

    fun role(): Role =
        when {
            user != null -> Role.USER
            assistant != null -> Role.ASSISTANT
            system != null -> Role.SYSTEM
            else -> Role.of(_json?.asObject()?.getOrNull()?.get("role") ?: JsonMissing.of())
        }

    fun content(): Optional<BetaMessageContentParam> =
        when {
            user != null -> Optional.of(user.content())
            assistant != null -> Optional.of(assistant.content())
            system != null ->
                system.content().map {
                    when {
                        it.isString() -> BetaMessageContentParam.ofString(it.asString())
                        it.isBlocks() ->
                            BetaMessageContentParam.ofContentBlockParams(
                                it.asBlocks().map {
                                    when {
                                        it.isText() -> BetaContentBlockParam.ofText(it.asText())
                                        it.isToolAddition() ->
                                            BetaContentBlockParam.ofToolAddition(
                                                it.asToolAddition()
                                            )
                                        it.isToolRemoval() ->
                                            BetaContentBlockParam.ofToolRemoval(it.asToolRemoval())
                                        else ->
                                            it._json()
                                                .getOrNull()
                                                ?.convert(jacksonTypeRef<BetaContentBlockParam>())
                                                ?: throw AnthropicInvalidDataException(
                                                    "Unknown BetaSystemMessageParam.Content.SystemMessageContentBlock: ${it._json().getOrNull()}"
                                                )
                                    }
                                }
                            )
                        else ->
                            it._json()
                                .getOrNull()
                                ?.convert(jacksonTypeRef<BetaMessageContentParam>())
                                ?: throw AnthropicInvalidDataException(
                                    "Unknown BetaSystemMessageParam.Content: ${it._json().getOrNull()}"
                                )
                    }
                }
            else -> _json.getProperty<BetaMessageContentParam>("content").asKnown()
        }

    fun user(): Optional<BetaUserMessageParam> = Optional.ofNullable(user)

    fun assistant(): Optional<BetaAssistantMessageParam> = Optional.ofNullable(assistant)

    fun system(): Optional<BetaSystemMessageParam> = Optional.ofNullable(system)

    fun isUser(): Boolean = user != null

    fun isAssistant(): Boolean = assistant != null

    fun isSystem(): Boolean = system != null

    fun asUser(): BetaUserMessageParam = user.getOrThrow("user")

    fun asAssistant(): BetaAssistantMessageParam = assistant.getOrThrow("assistant")

    fun asSystem(): BetaSystemMessageParam = system.getOrThrow("system")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaMessageParam.accept(new BetaMessageParam.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitUser(BetaUserMessageParam user) {
     *         return Optional.of(user.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            user != null -> visitor.visitUser(user)
            assistant != null -> visitor.visitAssistant(assistant)
            system != null -> visitor.visitSystem(system)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaMessageParam = apply {
        if (validated) {
            return@apply
        }

        when {
            user != null -> user.validate()
            assistant != null -> assistant.validate()
            system != null -> system.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaMessageParam: $_json")
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
        when {
            user != null -> user.validity()
            assistant != null -> assistant.validity()
            system != null -> system.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageParam &&
            user == other.user &&
            assistant == other.assistant &&
            system == other.system
    }

    override fun hashCode(): Int = Objects.hash(user, assistant, system)

    override fun toString(): String =
        when {
            user != null -> "BetaMessageParam{user=$user}"
            assistant != null -> "BetaMessageParam{assistant=$assistant}"
            system != null -> "BetaMessageParam{system=$system}"
            _json != null -> "BetaMessageParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaMessageParam")
        }

    companion object {

        @JvmStatic fun ofUser(user: BetaUserMessageParam) = BetaMessageParam(user = user)

        /**
         * Returns an immutable instance of [BetaMessageParam] whose [ofUser] variant is built from
         * the given required [content].
         */
        @JvmStatic
        fun ofUser(content: BetaMessageContentParam) = ofUser(BetaUserMessageParam.of(content))

        /** Alias for calling [ofUser] with `BetaMessageContentParam.ofString(string)`. */
        @JvmStatic fun ofUser(string: String) = ofUser(BetaMessageContentParam.ofString(string))

        /**
         * Alias for calling [ofUser] with
         * `BetaMessageContentParam.ofContentBlockParams(contentBlockParams)`.
         */
        @JvmStatic
        fun ofUserOfContentBlockParams(contentBlockParams: List<BetaContentBlockParam>) =
            ofUser(BetaMessageContentParam.ofContentBlockParams(contentBlockParams))

        @JvmStatic
        fun ofAssistant(assistant: BetaAssistantMessageParam) =
            BetaMessageParam(assistant = assistant)

        /**
         * Returns an immutable instance of [BetaMessageParam] whose [ofAssistant] variant is built
         * from the given required [content].
         */
        @JvmStatic
        fun ofAssistant(content: BetaMessageContentParam) =
            ofAssistant(BetaAssistantMessageParam.of(content))

        /** Alias for calling [ofAssistant] with `BetaMessageContentParam.ofString(string)`. */
        @JvmStatic
        fun ofAssistant(string: String) = ofAssistant(BetaMessageContentParam.ofString(string))

        /**
         * Alias for calling [ofAssistant] with
         * `BetaMessageContentParam.ofContentBlockParams(contentBlockParams)`.
         */
        @JvmStatic
        fun ofAssistantOfContentBlockParams(contentBlockParams: List<BetaContentBlockParam>) =
            ofAssistant(BetaMessageContentParam.ofContentBlockParams(contentBlockParams))

        @JvmStatic fun ofSystem(system: BetaSystemMessageParam) = BetaMessageParam(system = system)

        /**
         * Returns an immutable instance of [BetaMessageParam] whose [ofSystem] variant is built
         * from the given required [content].
         */
        @JvmStatic
        fun ofSystem(content: BetaSystemMessageParam.Content) =
            ofSystem(BetaSystemMessageParam.of(content))

        /** Alias for calling [ofSystem] with `BetaSystemMessageParam.Content.ofString(string)`. */
        @JvmStatic
        fun ofSystem(string: String) = ofSystem(BetaSystemMessageParam.Content.ofString(string))

        /** Alias for calling [ofSystem] with `BetaSystemMessageParam.Content.ofBlocks(blocks)`. */
        @JvmStatic
        fun ofSystemOfBlocks(
            blocks: List<BetaSystemMessageParam.Content.SystemMessageContentBlock>
        ) = ofSystem(BetaSystemMessageParam.Content.ofBlocks(blocks))
    }

    /**
     * An interface that defines how to map each variant of [BetaMessageParam] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitUser(user: BetaUserMessageParam): T

        fun visitAssistant(assistant: BetaAssistantMessageParam): T

        fun visitSystem(system: BetaSystemMessageParam): T

        /**
         * Maps an unknown variant of [BetaMessageParam] to a value of type [T].
         *
         * An instance of [BetaMessageParam] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaMessageParam: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaMessageParam>(BetaMessageParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaMessageParam {
            val json = JsonValue.fromJsonNode(node)
            val role = json.asObject().getOrNull()?.get("role")?.asString()?.getOrNull()

            when (role) {
                "user" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaUserMessageParam>())?.let {
                        BetaMessageParam(user = it, _json = json)
                    } ?: BetaMessageParam(_json = json)
                }
                "assistant" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaAssistantMessageParam>())?.let {
                        BetaMessageParam(assistant = it, _json = json)
                    } ?: BetaMessageParam(_json = json)
                }
                "system" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaSystemMessageParam>())?.let {
                        BetaMessageParam(system = it, _json = json)
                    } ?: BetaMessageParam(_json = json)
                }
            }

            return BetaMessageParam(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaMessageParam>(BetaMessageParam::class) {

        override fun serialize(
            value: BetaMessageParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.user != null -> generator.writeObject(value.user)
                value.assistant != null -> generator.writeObject(value.assistant)
                value.system != null -> generator.writeObject(value.system)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaMessageParam")
            }
        }
    }

    class Role private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val USER = Role(JsonField.of("user"))

            @JvmField val ASSISTANT = Role(JsonField.of("assistant"))

            @JvmField val SYSTEM = Role(JsonField.of("system"))

            @JvmStatic
            fun of(value: String): Role =
                // Intern known values so `==` works
                when (value) {
                    "user" -> USER
                    "assistant" -> ASSISTANT
                    "system" -> SYSTEM
                    else -> Role(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Role =
                value.asString().getOrNull()?.let { of(it) } ?: Role(value)
        }

        /** An enum containing [Role]'s known values. */
        enum class Known {
            USER,
            ASSISTANT,
            SYSTEM,
        }

        /**
         * An enum containing [Role]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Role] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            USER,
            ASSISTANT,
            SYSTEM,
            /** An enum member indicating that [Role] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                USER -> Value.USER
                ASSISTANT -> Value.ASSISTANT
                SYSTEM -> Value.SYSTEM
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                USER -> Known.USER
                ASSISTANT -> Known.ASSISTANT
                SYSTEM -> Known.SYSTEM
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Role = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Role && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
