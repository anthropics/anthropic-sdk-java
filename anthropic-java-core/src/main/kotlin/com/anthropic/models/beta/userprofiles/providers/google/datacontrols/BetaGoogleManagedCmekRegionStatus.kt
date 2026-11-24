package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

/** One region's status for the `cmek` control, discriminated by `type`. */
@JsonDeserialize(using = BetaGoogleManagedCmekRegionStatus.Deserializer::class)
@JsonSerialize(using = BetaGoogleManagedCmekRegionStatus.Serializer::class)
class BetaGoogleManagedCmekRegionStatus
private constructor(
    private val notConfigured: BetaGoogleManagedCmekRegionNotConfigured? = null,
    private val pending: BetaGoogleManagedCmekRegionPending? = null,
    private val active: BetaGoogleManagedCmekRegionActive? = null,
    private val failed: BetaGoogleManagedCmekRegionFailed? = null,
    private val suspended: BetaGoogleManagedCmekRegionSuspended? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            notConfigured != null -> Type.NOT_CONFIGURED
            pending != null -> Type.PENDING
            active != null -> Type.ACTIVE
            failed != null -> Type.FAILED
            suspended != null -> Type.SUSPENDED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun effectiveExternalKeyId(): Optional<String> =
        when {
            notConfigured != null -> notConfigured.effectiveExternalKeyId()
            pending != null -> pending.effectiveExternalKeyId()
            active != null -> active.effectiveExternalKeyId()
            failed != null -> failed.effectiveExternalKeyId()
            suspended != null -> suspended.effectiveExternalKeyId()
            else -> _json.getProperty<String>("effective_external_key_id").asKnown()
        }

    fun error(): Optional<String> =
        when {
            notConfigured != null -> Optional.empty()
            pending != null -> pending.error()
            active != null -> active.error()
            failed != null -> Optional.of(failed.error())
            suspended != null -> Optional.empty()
            else -> _json.getProperty<String>("error").asKnown()
        }

    /** No key is set for this region. */
    fun notConfigured(): Optional<BetaGoogleManagedCmekRegionNotConfigured> =
        Optional.ofNullable(notConfigured)

    /**
     * The region is validating the configured key, or the key has passed validation and the region
     * has not started to apply it.
     */
    fun pending(): Optional<BetaGoogleManagedCmekRegionPending> = Optional.ofNullable(pending)

    /** The configured key passed validation, and the region applies it. */
    fun active(): Optional<BetaGoogleManagedCmekRegionActive> = Optional.ofNullable(active)

    /**
     * The region stopped retrying validation before the configured key passed. To restart
     * validation, send the region a key again. It can be the same key.
     */
    fun failed(): Optional<BetaGoogleManagedCmekRegionFailed> = Optional.ofNullable(failed)

    /**
     * Anthropic has withdrawn or paused its approval of customer-managed keys for this profile, so
     * the region does not apply the control.
     */
    fun suspended(): Optional<BetaGoogleManagedCmekRegionSuspended> = Optional.ofNullable(suspended)

    fun isNotConfigured(): Boolean = notConfigured != null

    fun isPending(): Boolean = pending != null

    fun isActive(): Boolean = active != null

    fun isFailed(): Boolean = failed != null

    fun isSuspended(): Boolean = suspended != null

    /** No key is set for this region. */
    fun asNotConfigured(): BetaGoogleManagedCmekRegionNotConfigured =
        notConfigured.getOrThrow("notConfigured")

    /**
     * The region is validating the configured key, or the key has passed validation and the region
     * has not started to apply it.
     */
    fun asPending(): BetaGoogleManagedCmekRegionPending = pending.getOrThrow("pending")

    /** The configured key passed validation, and the region applies it. */
    fun asActive(): BetaGoogleManagedCmekRegionActive = active.getOrThrow("active")

    /**
     * The region stopped retrying validation before the configured key passed. To restart
     * validation, send the region a key again. It can be the same key.
     */
    fun asFailed(): BetaGoogleManagedCmekRegionFailed = failed.getOrThrow("failed")

    /**
     * Anthropic has withdrawn or paused its approval of customer-managed keys for this profile, so
     * the region does not apply the control.
     */
    fun asSuspended(): BetaGoogleManagedCmekRegionSuspended = suspended.getOrThrow("suspended")

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
     * Optional<String> result = betaGoogleManagedCmekRegionStatus.accept(new BetaGoogleManagedCmekRegionStatus.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitNotConfigured(BetaGoogleManagedCmekRegionNotConfigured notConfigured) {
     *         return Optional.of(notConfigured.toString());
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
            notConfigured != null -> visitor.visitNotConfigured(notConfigured)
            pending != null -> visitor.visitPending(pending)
            active != null -> visitor.visitActive(active)
            failed != null -> visitor.visitFailed(failed)
            suspended != null -> visitor.visitSuspended(suspended)
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
    fun validate(): BetaGoogleManagedCmekRegionStatus = apply {
        if (validated) {
            return@apply
        }

        when {
            notConfigured != null -> notConfigured.validate()
            pending != null -> pending.validate()
            active != null -> active.validate()
            failed != null -> failed.validate()
            suspended != null -> suspended.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaGoogleManagedCmekRegionStatus: $_json"
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
            notConfigured != null -> notConfigured.validity()
            pending != null -> pending.validity()
            active != null -> active.validity()
            failed != null -> failed.validity()
            suspended != null -> suspended.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedCmekRegionStatus &&
            notConfigured == other.notConfigured &&
            pending == other.pending &&
            active == other.active &&
            failed == other.failed &&
            suspended == other.suspended
    }

    override fun hashCode(): Int = Objects.hash(notConfigured, pending, active, failed, suspended)

    override fun toString(): String =
        when {
            notConfigured != null ->
                "BetaGoogleManagedCmekRegionStatus{notConfigured=$notConfigured}"
            pending != null -> "BetaGoogleManagedCmekRegionStatus{pending=$pending}"
            active != null -> "BetaGoogleManagedCmekRegionStatus{active=$active}"
            failed != null -> "BetaGoogleManagedCmekRegionStatus{failed=$failed}"
            suspended != null -> "BetaGoogleManagedCmekRegionStatus{suspended=$suspended}"
            _json != null -> "BetaGoogleManagedCmekRegionStatus{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaGoogleManagedCmekRegionStatus")
        }

    companion object {

        /** No key is set for this region. */
        @JvmStatic
        fun ofNotConfigured(notConfigured: BetaGoogleManagedCmekRegionNotConfigured) =
            BetaGoogleManagedCmekRegionStatus(notConfigured = notConfigured)

        /**
         * Returns an immutable instance of [BetaGoogleManagedCmekRegionStatus] whose
         * [ofNotConfigured] variant is built from the given required [effectiveExternalKeyId].
         */
        @JvmStatic
        fun ofNotConfigured(effectiveExternalKeyId: String?) =
            ofNotConfigured(BetaGoogleManagedCmekRegionNotConfigured.of(effectiveExternalKeyId))

        /** Alias for calling [ofNotConfigured] with `effectiveExternalKeyId.orElse(null)`. */
        @JvmStatic
        fun ofNotConfigured(effectiveExternalKeyId: Optional<String>) =
            ofNotConfigured(effectiveExternalKeyId.getOrNull())

        /**
         * The region is validating the configured key, or the key has passed validation and the
         * region has not started to apply it.
         */
        @JvmStatic
        fun ofPending(pending: BetaGoogleManagedCmekRegionPending) =
            BetaGoogleManagedCmekRegionStatus(pending = pending)

        /** The configured key passed validation, and the region applies it. */
        @JvmStatic
        fun ofActive(active: BetaGoogleManagedCmekRegionActive) =
            BetaGoogleManagedCmekRegionStatus(active = active)

        /**
         * The region stopped retrying validation before the configured key passed. To restart
         * validation, send the region a key again. It can be the same key.
         */
        @JvmStatic
        fun ofFailed(failed: BetaGoogleManagedCmekRegionFailed) =
            BetaGoogleManagedCmekRegionStatus(failed = failed)

        /**
         * Anthropic has withdrawn or paused its approval of customer-managed keys for this profile,
         * so the region does not apply the control.
         */
        @JvmStatic
        fun ofSuspended(suspended: BetaGoogleManagedCmekRegionSuspended) =
            BetaGoogleManagedCmekRegionStatus(suspended = suspended)

        /**
         * Returns an immutable instance of [BetaGoogleManagedCmekRegionStatus] whose [ofSuspended]
         * variant is built from the given required [effectiveExternalKeyId].
         */
        @JvmStatic
        fun ofSuspended(effectiveExternalKeyId: String?) =
            ofSuspended(BetaGoogleManagedCmekRegionSuspended.of(effectiveExternalKeyId))

        /** Alias for calling [ofSuspended] with `effectiveExternalKeyId.orElse(null)`. */
        @JvmStatic
        fun ofSuspended(effectiveExternalKeyId: Optional<String>) =
            ofSuspended(effectiveExternalKeyId.getOrNull())
    }

    /**
     * An interface that defines how to map each variant of [BetaGoogleManagedCmekRegionStatus] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** No key is set for this region. */
        fun visitNotConfigured(notConfigured: BetaGoogleManagedCmekRegionNotConfigured): T

        /**
         * The region is validating the configured key, or the key has passed validation and the
         * region has not started to apply it.
         */
        fun visitPending(pending: BetaGoogleManagedCmekRegionPending): T

        /** The configured key passed validation, and the region applies it. */
        fun visitActive(active: BetaGoogleManagedCmekRegionActive): T

        /**
         * The region stopped retrying validation before the configured key passed. To restart
         * validation, send the region a key again. It can be the same key.
         */
        fun visitFailed(failed: BetaGoogleManagedCmekRegionFailed): T

        /**
         * Anthropic has withdrawn or paused its approval of customer-managed keys for this profile,
         * so the region does not apply the control.
         */
        fun visitSuspended(suspended: BetaGoogleManagedCmekRegionSuspended): T

        /**
         * Maps an unknown variant of [BetaGoogleManagedCmekRegionStatus] to a value of type [T].
         *
         * An instance of [BetaGoogleManagedCmekRegionStatus] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaGoogleManagedCmekRegionStatus: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaGoogleManagedCmekRegionStatus>(
            BetaGoogleManagedCmekRegionStatus::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaGoogleManagedCmekRegionStatus {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "not_configured" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedCmekRegionNotConfigured>(),
                        )
                        ?.let {
                            BetaGoogleManagedCmekRegionStatus(notConfigured = it, _json = json)
                        } ?: BetaGoogleManagedCmekRegionStatus(_json = json)
                }
                "pending" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedCmekRegionPending>(),
                        )
                        ?.let { BetaGoogleManagedCmekRegionStatus(pending = it, _json = json) }
                        ?: BetaGoogleManagedCmekRegionStatus(_json = json)
                }
                "active" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGoogleManagedCmekRegionActive>())
                        ?.let { BetaGoogleManagedCmekRegionStatus(active = it, _json = json) }
                        ?: BetaGoogleManagedCmekRegionStatus(_json = json)
                }
                "failed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGoogleManagedCmekRegionFailed>())
                        ?.let { BetaGoogleManagedCmekRegionStatus(failed = it, _json = json) }
                        ?: BetaGoogleManagedCmekRegionStatus(_json = json)
                }
                "suspended" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedCmekRegionSuspended>(),
                        )
                        ?.let { BetaGoogleManagedCmekRegionStatus(suspended = it, _json = json) }
                        ?: BetaGoogleManagedCmekRegionStatus(_json = json)
                }
            }

            return BetaGoogleManagedCmekRegionStatus(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaGoogleManagedCmekRegionStatus>(
            BetaGoogleManagedCmekRegionStatus::class
        ) {

        override fun serialize(
            value: BetaGoogleManagedCmekRegionStatus,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.notConfigured != null -> generator.writeObject(value.notConfigured)
                value.pending != null -> generator.writeObject(value.pending)
                value.active != null -> generator.writeObject(value.active)
                value.failed != null -> generator.writeObject(value.failed)
                value.suspended != null -> generator.writeObject(value.suspended)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaGoogleManagedCmekRegionStatus")
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

            @JvmField val NOT_CONFIGURED = Type(JsonField.of("not_configured"))

            @JvmField val PENDING = Type(JsonField.of("pending"))

            @JvmField val ACTIVE = Type(JsonField.of("active"))

            @JvmField val FAILED = Type(JsonField.of("failed"))

            @JvmField val SUSPENDED = Type(JsonField.of("suspended"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "not_configured" -> NOT_CONFIGURED
                    "pending" -> PENDING
                    "active" -> ACTIVE
                    "failed" -> FAILED
                    "suspended" -> SUSPENDED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            NOT_CONFIGURED,
            PENDING,
            ACTIVE,
            FAILED,
            SUSPENDED,
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
            NOT_CONFIGURED,
            PENDING,
            ACTIVE,
            FAILED,
            SUSPENDED,
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
                NOT_CONFIGURED -> Value.NOT_CONFIGURED
                PENDING -> Value.PENDING
                ACTIVE -> Value.ACTIVE
                FAILED -> Value.FAILED
                SUSPENDED -> Value.SUSPENDED
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
                NOT_CONFIGURED -> Known.NOT_CONFIGURED
                PENDING -> Known.PENDING
                ACTIVE -> Known.ACTIVE
                FAILED -> Known.FAILED
                SUSPENDED -> Known.SUSPENDED
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
