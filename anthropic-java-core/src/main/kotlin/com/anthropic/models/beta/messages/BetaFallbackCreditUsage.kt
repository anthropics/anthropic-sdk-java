// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Outcome of the ``fallback_credit_token`` presented on this request. */
class BetaFallbackCreditUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val status: JsonField<Status>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of()
    ) : this(status, mutableMapOf())

    /**
     * Whether the fallback-credit reprice was applied to this response's billing.
     *
     * A union discriminated on `type`. `redeemed`: the retry is billed as if the conversation had
     * been on the retry model all along — including when the resulting shift is zero because there
     * was nothing to move. `not_applied`: no reprice was applied; the arm's `reason` says why.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackCreditUsage].
         *
         * The following fields are required:
         * ```java
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaFallbackCreditUsage] with the required [status] set
         * to the given value.
         */
        @JvmStatic fun of(status: Status) = builder().status(status).build()
    }

    /** A builder for [BetaFallbackCreditUsage]. */
    class Builder internal constructor() {

        private var status: JsonField<Status>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackCreditUsage: BetaFallbackCreditUsage) = apply {
            status = betaFallbackCreditUsage.status
            additionalProperties = betaFallbackCreditUsage.additionalProperties.toMutableMap()
        }

        /**
         * Whether the fallback-credit reprice was applied to this response's billing.
         *
         * A union discriminated on `type`. `redeemed`: the retry is billed as if the conversation
         * had been on the retry model all along — including when the resulting shift is zero
         * because there was nothing to move. `not_applied`: no reprice was applied; the arm's
         * `reason` says why.
         */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        /** Alias for calling [status] with `Status.ofRedeemed(redeemed)`. */
        fun status(redeemed: BetaFallbackCreditRedeemed) = status(Status.ofRedeemed(redeemed))

        /** Alias for calling [status] with `Status.ofNotApplied(notApplied)`. */
        fun status(notApplied: BetaFallbackCreditNotApplied) =
            status(Status.ofNotApplied(notApplied))

        /**
         * Alias for calling [status] with the following:
         * ```java
         * BetaFallbackCreditNotApplied.builder()
         *     .reason(reason)
         *     .build()
         * ```
         */
        fun notAppliedStatus(reason: BetaFallbackCreditNotApplied.Reason) =
            status(BetaFallbackCreditNotApplied.builder().reason(reason).build())

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
         * Returns an immutable instance of [BetaFallbackCreditUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackCreditUsage =
            BetaFallbackCreditUsage(
                checkRequired("status", status),
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaFallbackCreditUsage = apply {
        if (validated) {
            return@apply
        }

        status().validate()
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
    @JvmSynthetic internal fun validity(): Int = (status.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Whether the fallback-credit reprice was applied to this response's billing.
     *
     * A union discriminated on `type`. `redeemed`: the retry is billed as if the conversation had
     * been on the retry model all along — including when the resulting shift is zero because there
     * was nothing to move. `not_applied`: no reprice was applied; the arm's `reason` says why.
     */
    @JsonDeserialize(using = Status.Deserializer::class)
    @JsonSerialize(using = Status.Serializer::class)
    class Status
    private constructor(
        private val redeemed: BetaFallbackCreditRedeemed? = null,
        private val notApplied: BetaFallbackCreditNotApplied? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * The reprice was applied: the retry is billed as if the conversation had been on the retry
         * model all along.
         */
        fun redeemed(): Optional<BetaFallbackCreditRedeemed> = Optional.ofNullable(redeemed)

        /** No reprice was applied; ``reason`` says why. */
        fun notApplied(): Optional<BetaFallbackCreditNotApplied> = Optional.ofNullable(notApplied)

        fun isRedeemed(): Boolean = redeemed != null

        fun isNotApplied(): Boolean = notApplied != null

        /**
         * The reprice was applied: the retry is billed as if the conversation had been on the retry
         * model all along.
         */
        fun asRedeemed(): BetaFallbackCreditRedeemed = redeemed.getOrThrow("redeemed")

        /** No reprice was applied; ``reason`` says why. */
        fun asNotApplied(): BetaFallbackCreditNotApplied = notApplied.getOrThrow("notApplied")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = status.accept(new Status.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitRedeemed(BetaFallbackCreditRedeemed redeemed) {
         *         return Optional.of(redeemed.toString());
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
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                redeemed != null -> visitor.visitRedeemed(redeemed)
                notApplied != null -> visitor.visitNotApplied(notApplied)
                else -> visitor.unknown(_json)
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
        fun validate(): Status = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitRedeemed(redeemed: BetaFallbackCreditRedeemed) {
                        redeemed.validate()
                    }

                    override fun visitNotApplied(notApplied: BetaFallbackCreditNotApplied) {
                        notApplied.validate()
                    }
                }
            )
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
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitRedeemed(redeemed: BetaFallbackCreditRedeemed) =
                        redeemed.validity()

                    override fun visitNotApplied(notApplied: BetaFallbackCreditNotApplied) =
                        notApplied.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Status && redeemed == other.redeemed && notApplied == other.notApplied
        }

        override fun hashCode(): Int = Objects.hash(redeemed, notApplied)

        override fun toString(): String =
            when {
                redeemed != null -> "Status{redeemed=$redeemed}"
                notApplied != null -> "Status{notApplied=$notApplied}"
                _json != null -> "Status{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Status")
            }

        companion object {

            /**
             * The reprice was applied: the retry is billed as if the conversation had been on the
             * retry model all along.
             */
            @JvmStatic
            fun ofRedeemed(redeemed: BetaFallbackCreditRedeemed) = Status(redeemed = redeemed)

            /** No reprice was applied; ``reason`` says why. */
            @JvmStatic
            fun ofNotApplied(notApplied: BetaFallbackCreditNotApplied) =
                Status(notApplied = notApplied)

            /**
             * Returns an immutable instance of [Status] whose [ofNotApplied] variant is built from
             * the given required [reason].
             */
            @JvmStatic
            fun ofNotApplied(reason: BetaFallbackCreditNotApplied.Reason) =
                ofNotApplied(BetaFallbackCreditNotApplied.of(reason))
        }

        /** An interface that defines how to map each variant of [Status] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * The reprice was applied: the retry is billed as if the conversation had been on the
             * retry model all along.
             */
            fun visitRedeemed(redeemed: BetaFallbackCreditRedeemed): T

            /** No reprice was applied; ``reason`` says why. */
            fun visitNotApplied(notApplied: BetaFallbackCreditNotApplied): T

            /**
             * Maps an unknown variant of [Status] to a value of type [T].
             *
             * An instance of [Status] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Status: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Status>(Status::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Status {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "redeemed" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaFallbackCreditRedeemed>())
                            ?.let { Status(redeemed = it, _json = json) } ?: Status(_json = json)
                    }
                    "not_applied" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaFallbackCreditNotApplied>())
                            ?.let { Status(notApplied = it, _json = json) } ?: Status(_json = json)
                    }
                }

                return Status(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Status>(Status::class) {

            override fun serialize(
                value: Status,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.redeemed != null -> generator.writeObject(value.redeemed)
                    value.notApplied != null -> generator.writeObject(value.notApplied)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Status")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackCreditUsage &&
            status == other.status &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(status, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackCreditUsage{status=$status, additionalProperties=$additionalProperties}"
}
