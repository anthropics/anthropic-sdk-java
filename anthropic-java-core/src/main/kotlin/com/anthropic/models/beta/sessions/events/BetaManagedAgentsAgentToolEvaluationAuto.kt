package com.anthropic.models.beta.sessions.events

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** The resolved permission_policy was auto: the server judged this invocation individually. */
class BetaManagedAgentsAgentToolEvaluationAuto
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val evaluatedPermission: JsonField<BetaManagedAgentsAgentAutoEvaluatedPermission>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("evaluated_permission")
        @ExcludeMissing
        evaluatedPermission: JsonField<BetaManagedAgentsAgentAutoEvaluatedPermission> =
            JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(evaluatedPermission, type, mutableMapOf())

    /**
     * The server's per-invocation judgement under the auto permission policy. Its type always
     * equals the event's top-level evaluated_permission. Open union: clients must tolerate unknown
     * variants.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun evaluatedPermission(): BetaManagedAgentsAgentAutoEvaluatedPermission =
        evaluatedPermission.getRequired("evaluated_permission")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("auto")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [evaluatedPermission].
     *
     * Unlike [evaluatedPermission], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("evaluated_permission")
    @ExcludeMissing
    fun _evaluatedPermission(): JsonField<BetaManagedAgentsAgentAutoEvaluatedPermission> =
        evaluatedPermission

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsAgentToolEvaluationAuto].
         *
         * The following fields are required:
         * ```java
         * .evaluatedPermission()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsAgentToolEvaluationAuto] with the
         * required [evaluatedPermission] set to the given value.
         */
        @JvmStatic
        fun of(evaluatedPermission: BetaManagedAgentsAgentAutoEvaluatedPermission) =
            builder().evaluatedPermission(evaluatedPermission).build()
    }

    /** A builder for [BetaManagedAgentsAgentToolEvaluationAuto]. */
    class Builder internal constructor() {

        private var evaluatedPermission: JsonField<BetaManagedAgentsAgentAutoEvaluatedPermission>? =
            null
        private var type: JsonValue = JsonValue.from("auto")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolEvaluationAuto: BetaManagedAgentsAgentToolEvaluationAuto
        ) = apply {
            evaluatedPermission = betaManagedAgentsAgentToolEvaluationAuto.evaluatedPermission
            type = betaManagedAgentsAgentToolEvaluationAuto.type
            additionalProperties =
                betaManagedAgentsAgentToolEvaluationAuto.additionalProperties.toMutableMap()
        }

        /**
         * The server's per-invocation judgement under the auto permission policy. Its type always
         * equals the event's top-level evaluated_permission. Open union: clients must tolerate
         * unknown variants.
         */
        fun evaluatedPermission(
            evaluatedPermission: BetaManagedAgentsAgentAutoEvaluatedPermission
        ) = evaluatedPermission(JsonField.of(evaluatedPermission))

        /**
         * Sets [Builder.evaluatedPermission] to an arbitrary JSON value.
         *
         * You should usually call [Builder.evaluatedPermission] with a well-typed
         * [BetaManagedAgentsAgentAutoEvaluatedPermission] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun evaluatedPermission(
            evaluatedPermission: JsonField<BetaManagedAgentsAgentAutoEvaluatedPermission>
        ) = apply { this.evaluatedPermission = evaluatedPermission }

        /**
         * Alias for calling [evaluatedPermission] with
         * `BetaManagedAgentsAgentAutoEvaluatedPermission.ofAllow(allow)`.
         */
        fun evaluatedPermission(allow: BetaManagedAgentsAgentAutoEvaluatedPermissionAllow) =
            evaluatedPermission(BetaManagedAgentsAgentAutoEvaluatedPermission.ofAllow(allow))

        /**
         * Alias for calling [evaluatedPermission] with
         * `BetaManagedAgentsAgentAutoEvaluatedPermission.ofAsk(ask)`.
         */
        fun evaluatedPermission(ask: BetaManagedAgentsAgentAutoEvaluatedPermissionAsk) =
            evaluatedPermission(BetaManagedAgentsAgentAutoEvaluatedPermission.ofAsk(ask))

        /**
         * Alias for calling [evaluatedPermission] with the following:
         * ```java
         * BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.builder()
         *     .reasonCode(reasonCode)
         *     .build()
         * ```
         */
        fun askEvaluatedPermission(reasonCode: String) =
            evaluatedPermission(
                BetaManagedAgentsAgentAutoEvaluatedPermissionAsk.builder()
                    .reasonCode(reasonCode)
                    .build()
            )

        /**
         * Alias for calling [evaluatedPermission] with
         * `BetaManagedAgentsAgentAutoEvaluatedPermission.ofDeny(deny)`.
         */
        fun evaluatedPermission(deny: BetaManagedAgentsAgentAutoEvaluatedPermissionDeny) =
            evaluatedPermission(BetaManagedAgentsAgentAutoEvaluatedPermission.ofDeny(deny))

        /**
         * Alias for calling [evaluatedPermission] with the following:
         * ```java
         * BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.builder()
         *     .reasonCode(reasonCode)
         *     .build()
         * ```
         */
        fun denyEvaluatedPermission(reasonCode: String) =
            evaluatedPermission(
                BetaManagedAgentsAgentAutoEvaluatedPermissionDeny.builder()
                    .reasonCode(reasonCode)
                    .build()
            )

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("auto")
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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolEvaluationAuto].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .evaluatedPermission()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolEvaluationAuto =
            BetaManagedAgentsAgentToolEvaluationAuto(
                checkRequired("evaluatedPermission", evaluatedPermission),
                type,
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
    fun validate(): BetaManagedAgentsAgentToolEvaluationAuto = apply {
        if (validated) {
            return@apply
        }

        evaluatedPermission().validate()
        _type().let {
            if (it != JsonValue.from("auto")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (evaluatedPermission.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("auto")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolEvaluationAuto &&
            evaluatedPermission == other.evaluatedPermission &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(evaluatedPermission, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolEvaluationAuto{evaluatedPermission=$evaluatedPermission, type=$type, additionalProperties=$additionalProperties}"
}
