package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

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

@JsonDeserialize(using = BetaGoogleManagedProjectStorageRegionStatus.Deserializer::class)
@JsonSerialize(using = BetaGoogleManagedProjectStorageRegionStatus.Serializer::class)
class BetaGoogleManagedProjectStorageRegionStatus
private constructor(
    private val notConfigured: BetaGoogleManagedProjectStorageRegionNotConfigured? = null,
    private val pending: BetaGoogleManagedProjectStorageRegionPending? = null,
    private val active: BetaGoogleManagedProjectStorageRegionActive? = null,
    private val failed: BetaGoogleManagedProjectStorageRegionFailed? = null,
    private val suspended: BetaGoogleManagedProjectStorageRegionSuspended? = null,
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

    fun effectiveBucket(): Optional<BetaGoogleManagedProjectEffectiveBucket> =
        when {
            notConfigured != null -> notConfigured.effectiveBucket()
            pending != null -> pending.effectiveBucket()
            active != null -> active.effectiveBucket()
            failed != null -> failed.effectiveBucket()
            suspended != null -> suspended.effectiveBucket()
            else ->
                _json
                    .getProperty<BetaGoogleManagedProjectEffectiveBucket>("effective_bucket")
                    .asKnown()
        }

    fun failedChecks(): Optional<List<BetaGoogleManagedProjectStorageFailedCheck>> =
        when {
            notConfigured != null -> Optional.empty()
            pending != null -> Optional.of(pending.failedChecks())
            active != null -> Optional.of(active.failedChecks())
            failed != null -> Optional.of(failed.failedChecks())
            suspended != null -> Optional.empty()
            else ->
                _json
                    .getProperty<List<BetaGoogleManagedProjectStorageFailedCheck>>("failed_checks")
                    .asKnown()
        }

    /** No bucket is set for this region. */
    fun notConfigured(): Optional<BetaGoogleManagedProjectStorageRegionNotConfigured> =
        Optional.ofNullable(notConfigured)

    /** The region is verifying the configured bucket. */
    fun pending(): Optional<BetaGoogleManagedProjectStorageRegionPending> =
        Optional.ofNullable(pending)

    /** The configured bucket passed verification, and the region applies it. */
    fun active(): Optional<BetaGoogleManagedProjectStorageRegionActive> =
        Optional.ofNullable(active)

    /**
     * The region stopped retrying verification before the configured bucket passed. To restart
     * verification, send the region a bucket again. It can be the same bucket.
     */
    fun failed(): Optional<BetaGoogleManagedProjectStorageRegionFailed> =
        Optional.ofNullable(failed)

    /**
     * Anthropic has withdrawn or paused its approval of customer-owned storage for this project, so
     * the region does not apply the control.
     */
    fun suspended(): Optional<BetaGoogleManagedProjectStorageRegionSuspended> =
        Optional.ofNullable(suspended)

    fun isNotConfigured(): Boolean = notConfigured != null

    fun isPending(): Boolean = pending != null

    fun isActive(): Boolean = active != null

    fun isFailed(): Boolean = failed != null

    fun isSuspended(): Boolean = suspended != null

    /** No bucket is set for this region. */
    fun asNotConfigured(): BetaGoogleManagedProjectStorageRegionNotConfigured =
        notConfigured.getOrThrow("notConfigured")

    /** The region is verifying the configured bucket. */
    fun asPending(): BetaGoogleManagedProjectStorageRegionPending = pending.getOrThrow("pending")

    /** The configured bucket passed verification, and the region applies it. */
    fun asActive(): BetaGoogleManagedProjectStorageRegionActive = active.getOrThrow("active")

    /**
     * The region stopped retrying verification before the configured bucket passed. To restart
     * verification, send the region a bucket again. It can be the same bucket.
     */
    fun asFailed(): BetaGoogleManagedProjectStorageRegionFailed = failed.getOrThrow("failed")

    /**
     * Anthropic has withdrawn or paused its approval of customer-owned storage for this project, so
     * the region does not apply the control.
     */
    fun asSuspended(): BetaGoogleManagedProjectStorageRegionSuspended =
        suspended.getOrThrow("suspended")

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
     * Optional<String> result = betaGoogleManagedProjectStorageRegionStatus.accept(new BetaGoogleManagedProjectStorageRegionStatus.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitNotConfigured(BetaGoogleManagedProjectStorageRegionNotConfigured notConfigured) {
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
    fun validate(): BetaGoogleManagedProjectStorageRegionStatus = apply {
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
                    "Unknown BetaGoogleManagedProjectStorageRegionStatus: $_json"
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

        return other is BetaGoogleManagedProjectStorageRegionStatus &&
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
                "BetaGoogleManagedProjectStorageRegionStatus{notConfigured=$notConfigured}"
            pending != null -> "BetaGoogleManagedProjectStorageRegionStatus{pending=$pending}"
            active != null -> "BetaGoogleManagedProjectStorageRegionStatus{active=$active}"
            failed != null -> "BetaGoogleManagedProjectStorageRegionStatus{failed=$failed}"
            suspended != null -> "BetaGoogleManagedProjectStorageRegionStatus{suspended=$suspended}"
            _json != null -> "BetaGoogleManagedProjectStorageRegionStatus{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaGoogleManagedProjectStorageRegionStatus")
        }

    companion object {

        /** No bucket is set for this region. */
        @JvmStatic
        fun ofNotConfigured(notConfigured: BetaGoogleManagedProjectStorageRegionNotConfigured) =
            BetaGoogleManagedProjectStorageRegionStatus(notConfigured = notConfigured)

        /**
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageRegionStatus] whose
         * [ofNotConfigured] variant is built from the given required [effectiveBucket].
         */
        @JvmStatic
        fun ofNotConfigured(effectiveBucket: BetaGoogleManagedProjectEffectiveBucket?) =
            ofNotConfigured(BetaGoogleManagedProjectStorageRegionNotConfigured.of(effectiveBucket))

        /** Alias for calling [ofNotConfigured] with `effectiveBucket.orElse(null)`. */
        @JvmStatic
        fun ofNotConfigured(effectiveBucket: Optional<BetaGoogleManagedProjectEffectiveBucket>) =
            ofNotConfigured(effectiveBucket.getOrNull())

        /** The region is verifying the configured bucket. */
        @JvmStatic
        fun ofPending(pending: BetaGoogleManagedProjectStorageRegionPending) =
            BetaGoogleManagedProjectStorageRegionStatus(pending = pending)

        /** The configured bucket passed verification, and the region applies it. */
        @JvmStatic
        fun ofActive(active: BetaGoogleManagedProjectStorageRegionActive) =
            BetaGoogleManagedProjectStorageRegionStatus(active = active)

        /**
         * The region stopped retrying verification before the configured bucket passed. To restart
         * verification, send the region a bucket again. It can be the same bucket.
         */
        @JvmStatic
        fun ofFailed(failed: BetaGoogleManagedProjectStorageRegionFailed) =
            BetaGoogleManagedProjectStorageRegionStatus(failed = failed)

        /**
         * Anthropic has withdrawn or paused its approval of customer-owned storage for this
         * project, so the region does not apply the control.
         */
        @JvmStatic
        fun ofSuspended(suspended: BetaGoogleManagedProjectStorageRegionSuspended) =
            BetaGoogleManagedProjectStorageRegionStatus(suspended = suspended)

        /**
         * Returns an immutable instance of [BetaGoogleManagedProjectStorageRegionStatus] whose
         * [ofSuspended] variant is built from the given required [effectiveBucket].
         */
        @JvmStatic
        fun ofSuspended(effectiveBucket: BetaGoogleManagedProjectEffectiveBucket?) =
            ofSuspended(BetaGoogleManagedProjectStorageRegionSuspended.of(effectiveBucket))

        /** Alias for calling [ofSuspended] with `effectiveBucket.orElse(null)`. */
        @JvmStatic
        fun ofSuspended(effectiveBucket: Optional<BetaGoogleManagedProjectEffectiveBucket>) =
            ofSuspended(effectiveBucket.getOrNull())
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaGoogleManagedProjectStorageRegionStatus] to a value of type [T].
     */
    interface Visitor<out T> {

        /** No bucket is set for this region. */
        fun visitNotConfigured(notConfigured: BetaGoogleManagedProjectStorageRegionNotConfigured): T

        /** The region is verifying the configured bucket. */
        fun visitPending(pending: BetaGoogleManagedProjectStorageRegionPending): T

        /** The configured bucket passed verification, and the region applies it. */
        fun visitActive(active: BetaGoogleManagedProjectStorageRegionActive): T

        /**
         * The region stopped retrying verification before the configured bucket passed. To restart
         * verification, send the region a bucket again. It can be the same bucket.
         */
        fun visitFailed(failed: BetaGoogleManagedProjectStorageRegionFailed): T

        /**
         * Anthropic has withdrawn or paused its approval of customer-owned storage for this
         * project, so the region does not apply the control.
         */
        fun visitSuspended(suspended: BetaGoogleManagedProjectStorageRegionSuspended): T

        /**
         * Maps an unknown variant of [BetaGoogleManagedProjectStorageRegionStatus] to a value of
         * type [T].
         *
         * An instance of [BetaGoogleManagedProjectStorageRegionStatus] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaGoogleManagedProjectStorageRegionStatus: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaGoogleManagedProjectStorageRegionStatus>(
            BetaGoogleManagedProjectStorageRegionStatus::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaGoogleManagedProjectStorageRegionStatus {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "not_configured" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedProjectStorageRegionNotConfigured>(),
                        )
                        ?.let {
                            BetaGoogleManagedProjectStorageRegionStatus(
                                notConfigured = it,
                                _json = json,
                            )
                        } ?: BetaGoogleManagedProjectStorageRegionStatus(_json = json)
                }
                "pending" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedProjectStorageRegionPending>(),
                        )
                        ?.let {
                            BetaGoogleManagedProjectStorageRegionStatus(pending = it, _json = json)
                        } ?: BetaGoogleManagedProjectStorageRegionStatus(_json = json)
                }
                "active" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedProjectStorageRegionActive>(),
                        )
                        ?.let {
                            BetaGoogleManagedProjectStorageRegionStatus(active = it, _json = json)
                        } ?: BetaGoogleManagedProjectStorageRegionStatus(_json = json)
                }
                "failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedProjectStorageRegionFailed>(),
                        )
                        ?.let {
                            BetaGoogleManagedProjectStorageRegionStatus(failed = it, _json = json)
                        } ?: BetaGoogleManagedProjectStorageRegionStatus(_json = json)
                }
                "suspended" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaGoogleManagedProjectStorageRegionSuspended>(),
                        )
                        ?.let {
                            BetaGoogleManagedProjectStorageRegionStatus(
                                suspended = it,
                                _json = json,
                            )
                        } ?: BetaGoogleManagedProjectStorageRegionStatus(_json = json)
                }
            }

            return BetaGoogleManagedProjectStorageRegionStatus(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaGoogleManagedProjectStorageRegionStatus>(
            BetaGoogleManagedProjectStorageRegionStatus::class
        ) {

        override fun serialize(
            value: BetaGoogleManagedProjectStorageRegionStatus,
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
                else ->
                    throw IllegalStateException(
                        "Invalid BetaGoogleManagedProjectStorageRegionStatus"
                    )
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
