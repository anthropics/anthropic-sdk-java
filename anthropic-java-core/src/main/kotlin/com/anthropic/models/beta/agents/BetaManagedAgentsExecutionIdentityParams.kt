package com.anthropic.models.beta.agents

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
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

/** Execution principal for runs of this agent: `service_account` (the default) or `aws_role`. */
@JsonDeserialize(using = BetaManagedAgentsExecutionIdentityParams.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsExecutionIdentityParams.Serializer::class)
class BetaManagedAgentsExecutionIdentityParams
private constructor(
    private val awsRole: BetaManagedAgentsAwsRoleExecutionIdentityParams? = null,
    private val serviceAccount: BetaManagedAgentsServiceAccountExecutionIdentityParams? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            awsRole != null -> Type.AWS_ROLE
            serviceAccount != null -> Type.SERVICE_ACCOUNT
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** An AWS IAM role principal. */
    fun awsRole(): Optional<BetaManagedAgentsAwsRoleExecutionIdentityParams> =
        Optional.ofNullable(awsRole)

    /**
     * The Anthropic-managed service account — the default execution principal. Carries no
     * identifier.
     */
    fun serviceAccount(): Optional<BetaManagedAgentsServiceAccountExecutionIdentityParams> =
        Optional.ofNullable(serviceAccount)

    fun isAwsRole(): Boolean = awsRole != null

    fun isServiceAccount(): Boolean = serviceAccount != null

    /** An AWS IAM role principal. */
    fun asAwsRole(): BetaManagedAgentsAwsRoleExecutionIdentityParams = awsRole.getOrThrow("awsRole")

    /**
     * The Anthropic-managed service account — the default execution principal. Carries no
     * identifier.
     */
    fun asServiceAccount(): BetaManagedAgentsServiceAccountExecutionIdentityParams =
        serviceAccount.getOrThrow("serviceAccount")

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
     * Optional<String> result = betaManagedAgentsExecutionIdentityParams.accept(new BetaManagedAgentsExecutionIdentityParams.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAwsRole(BetaManagedAgentsAwsRoleExecutionIdentityParams awsRole) {
     *         return Optional.of(awsRole.toString());
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
            awsRole != null -> visitor.visitAwsRole(awsRole)
            serviceAccount != null -> visitor.visitServiceAccount(serviceAccount)
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
    fun validate(): BetaManagedAgentsExecutionIdentityParams = apply {
        if (validated) {
            return@apply
        }

        when {
            awsRole != null -> awsRole.validate()
            serviceAccount != null -> serviceAccount.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaManagedAgentsExecutionIdentityParams: $_json"
                )
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
            awsRole != null -> awsRole.validity()
            serviceAccount != null -> serviceAccount.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsExecutionIdentityParams &&
            awsRole == other.awsRole &&
            serviceAccount == other.serviceAccount
    }

    override fun hashCode(): Int = Objects.hash(awsRole, serviceAccount)

    override fun toString(): String =
        when {
            awsRole != null -> "BetaManagedAgentsExecutionIdentityParams{awsRole=$awsRole}"
            serviceAccount != null ->
                "BetaManagedAgentsExecutionIdentityParams{serviceAccount=$serviceAccount}"
            _json != null -> "BetaManagedAgentsExecutionIdentityParams{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsExecutionIdentityParams")
        }

    companion object {

        /** An AWS IAM role principal. */
        @JvmStatic
        fun ofAwsRole(awsRole: BetaManagedAgentsAwsRoleExecutionIdentityParams) =
            BetaManagedAgentsExecutionIdentityParams(awsRole = awsRole)

        /**
         * Returns an immutable instance of [BetaManagedAgentsExecutionIdentityParams] whose
         * [ofAwsRole] variant is built from the given required [roleArn].
         */
        @JvmStatic
        fun ofAwsRole(roleArn: String) =
            ofAwsRole(BetaManagedAgentsAwsRoleExecutionIdentityParams.of(roleArn))

        /**
         * The Anthropic-managed service account — the default execution principal. Carries no
         * identifier.
         */
        @JvmStatic
        fun ofServiceAccount(
            serviceAccount: BetaManagedAgentsServiceAccountExecutionIdentityParams
        ) = BetaManagedAgentsExecutionIdentityParams(serviceAccount = serviceAccount)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsExecutionIdentityParams] to a value of type [T].
     */
    interface Visitor<out T> {

        /** An AWS IAM role principal. */
        fun visitAwsRole(awsRole: BetaManagedAgentsAwsRoleExecutionIdentityParams): T

        /**
         * The Anthropic-managed service account — the default execution principal. Carries no
         * identifier.
         */
        fun visitServiceAccount(
            serviceAccount: BetaManagedAgentsServiceAccountExecutionIdentityParams
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsExecutionIdentityParams] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsExecutionIdentityParams] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsExecutionIdentityParams: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsExecutionIdentityParams>(
            BetaManagedAgentsExecutionIdentityParams::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsExecutionIdentityParams {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "aws_role" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsAwsRoleExecutionIdentityParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsExecutionIdentityParams(awsRole = it, _json = json)
                        } ?: BetaManagedAgentsExecutionIdentityParams(_json = json)
                }
                "service_account" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsServiceAccountExecutionIdentityParams>(),
                        )
                        ?.let {
                            BetaManagedAgentsExecutionIdentityParams(
                                serviceAccount = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsExecutionIdentityParams(_json = json)
                }
            }

            return BetaManagedAgentsExecutionIdentityParams(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsExecutionIdentityParams>(
            BetaManagedAgentsExecutionIdentityParams::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsExecutionIdentityParams,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.awsRole != null -> generator.writeObject(value.awsRole)
                value.serviceAccount != null -> generator.writeObject(value.serviceAccount)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsExecutionIdentityParams")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val AWS_ROLE = Type(JsonField.of("aws_role"))

            @JvmField val SERVICE_ACCOUNT = Type(JsonField.of("service_account"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "aws_role" -> AWS_ROLE
                    "service_account" -> SERVICE_ACCOUNT
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AWS_ROLE,
            SERVICE_ACCOUNT,
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AWS_ROLE,
            SERVICE_ACCOUNT,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                AWS_ROLE -> Value.AWS_ROLE
                SERVICE_ACCOUNT -> Value.SERVICE_ACCOUNT
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
                AWS_ROLE -> Known.AWS_ROLE
                SERVICE_ACCOUNT -> Known.SERVICE_ACCOUNT
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
