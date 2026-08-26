// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaWorkspaceRateLimit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val groupType: JsonField<GroupType>,
    private val limits: JsonField<List<BetaWorkspaceRateLimitValue>>,
    private val models: JsonField<List<String>>,
    private val rateLimitId: JsonField<String>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("group_type")
        @ExcludeMissing
        groupType: JsonField<GroupType> = JsonMissing.of(),
        @JsonProperty("limits")
        @ExcludeMissing
        limits: JsonField<List<BetaWorkspaceRateLimitValue>> = JsonMissing.of(),
        @JsonProperty("models") @ExcludeMissing models: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("rate_limit_id")
        @ExcludeMissing
        rateLimitId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(groupType, limits, models, rateLimitId, type, workspaceId, mutableMapOf())

    /**
     * The kind of rate-limit group this entry represents. `model_group` entries apply to a family
     * of models (listed in `models`); other values apply to an API-surface category and have
     * `models` set to `null`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun groupType(): GroupType = groupType.getRequired("group_type")

    /**
     * The limiter values overridden for this group in this workspace. Limiter types without a
     * workspace override are omitted and inherit the organization value.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun limits(): List<BetaWorkspaceRateLimitValue> = limits.getRequired("limits")

    /**
     * Model names this entry's limits apply to, including aliases. `null` when `group_type` is not
     * `"model_group"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun models(): Optional<List<String>> = models.getOptional("models")

    /**
     * The `id` of the RateLimit group this override applies to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rateLimitId(): String = rateLimitId.getRequired("rate_limit_id")

    /**
     * Object type. Always `workspace_rate_limit` for workspace rate-limit entries.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workspace_rate_limit")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * ID of the Workspace this override applies to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Returns the raw JSON value of [groupType].
     *
     * Unlike [groupType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("group_type") @ExcludeMissing fun _groupType(): JsonField<GroupType> = groupType

    /**
     * Returns the raw JSON value of [limits].
     *
     * Unlike [limits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("limits")
    @ExcludeMissing
    fun _limits(): JsonField<List<BetaWorkspaceRateLimitValue>> = limits

    /**
     * Returns the raw JSON value of [models].
     *
     * Unlike [models], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("models") @ExcludeMissing fun _models(): JsonField<List<String>> = models

    /**
     * Returns the raw JSON value of [rateLimitId].
     *
     * Unlike [rateLimitId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rate_limit_id")
    @ExcludeMissing
    fun _rateLimitId(): JsonField<String> = rateLimitId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * Returns a mutable builder for constructing an instance of [BetaWorkspaceRateLimit].
         *
         * The following fields are required:
         * ```java
         * .groupType()
         * .limits()
         * .models()
         * .rateLimitId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspaceRateLimit]. */
    class Builder internal constructor() {

        private var groupType: JsonField<GroupType>? = null
        private var limits: JsonField<MutableList<BetaWorkspaceRateLimitValue>>? = null
        private var models: JsonField<MutableList<String>>? = null
        private var rateLimitId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("workspace_rate_limit")
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspaceRateLimit: BetaWorkspaceRateLimit) = apply {
            groupType = betaWorkspaceRateLimit.groupType
            limits =
                betaWorkspaceRateLimit.limits
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            models =
                betaWorkspaceRateLimit.models
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            rateLimitId = betaWorkspaceRateLimit.rateLimitId
            type = betaWorkspaceRateLimit.type
            workspaceId = betaWorkspaceRateLimit.workspaceId
            additionalProperties = betaWorkspaceRateLimit.additionalProperties.toMutableMap()
        }

        /**
         * The kind of rate-limit group this entry represents. `model_group` entries apply to a
         * family of models (listed in `models`); other values apply to an API-surface category and
         * have `models` set to `null`.
         */
        fun groupType(groupType: GroupType) = groupType(JsonField.of(groupType))

        /**
         * Sets [Builder.groupType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.groupType] with a well-typed [GroupType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun groupType(groupType: JsonField<GroupType>) = apply { this.groupType = groupType }

        /**
         * The limiter values overridden for this group in this workspace. Limiter types without a
         * workspace override are omitted and inherit the organization value.
         */
        fun limits(limits: List<BetaWorkspaceRateLimitValue>) = limits(JsonField.of(limits))

        /**
         * Sets [Builder.limits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.limits] with a well-typed
         * `List<BetaWorkspaceRateLimitValue>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun limits(limits: JsonField<List<BetaWorkspaceRateLimitValue>>) = apply {
            this.limits = limits.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaWorkspaceRateLimitValue] to [limits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addLimit(limit: BetaWorkspaceRateLimitValue) = apply {
            limits =
                (limits ?: JsonField.of(mutableListOf())).also {
                    checkKnown("limits", it).add(limit)
                }
        }

        /**
         * Model names this entry's limits apply to, including aliases. `null` when `group_type` is
         * not `"model_group"`.
         */
        fun models(models: List<String>?) = models(JsonField.ofNullable(models))

        /** Alias for calling [Builder.models] with `models.orElse(null)`. */
        fun models(models: Optional<List<String>>) = models(models.getOrNull())

        /**
         * Sets [Builder.models] to an arbitrary JSON value.
         *
         * You should usually call [Builder.models] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun models(models: JsonField<List<String>>) = apply {
            this.models = models.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [models].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addModel(model: String) = apply {
            models =
                (models ?: JsonField.of(mutableListOf())).also {
                    checkKnown("models", it).add(model)
                }
        }

        /** The `id` of the RateLimit group this override applies to. */
        fun rateLimitId(rateLimitId: String) = rateLimitId(JsonField.of(rateLimitId))

        /**
         * Sets [Builder.rateLimitId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rateLimitId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rateLimitId(rateLimitId: JsonField<String>) = apply { this.rateLimitId = rateLimitId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workspace_rate_limit")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** ID of the Workspace this override applies to. */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaWorkspaceRateLimit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .groupType()
         * .limits()
         * .models()
         * .rateLimitId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceRateLimit =
            BetaWorkspaceRateLimit(
                checkRequired("groupType", groupType),
                checkRequired("limits", limits).map { it.toImmutable() },
                checkRequired("models", models).map { it.toImmutable() },
                checkRequired("rateLimitId", rateLimitId),
                type,
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaWorkspaceRateLimit = apply {
        if (validated) {
            return@apply
        }

        groupType().validate()
        limits().forEach { it.validate() }
        models()
        rateLimitId()
        _type().let {
            if (it != JsonValue.from("workspace_rate_limit")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceId()
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
        (groupType.asKnown().getOrNull()?.validity() ?: 0) +
            (limits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (models.asKnown().getOrNull()?.size ?: 0) +
            (if (rateLimitId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("workspace_rate_limit")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    /**
     * The kind of rate-limit group this entry represents. `model_group` entries apply to a family
     * of models (listed in `models`); other values apply to an API-surface category and have
     * `models` set to `null`.
     */
    class GroupType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val BATCH = of("batch")

            @JvmField val FILES = of("files")

            @JvmField val MODEL_GROUP = of("model_group")

            @JvmField val SKILLS = of("skills")

            @JvmField val TOKEN_COUNT = of("token_count")

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmStatic fun of(value: String) = GroupType(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): GroupType =
                value.asString().getOrNull()?.let { of(it) } ?: GroupType(value)
        }

        /** An enum containing [GroupType]'s known values. */
        enum class Known {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
        }

        /**
         * An enum containing [GroupType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [GroupType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
            /**
             * An enum member indicating that [GroupType] was instantiated with an unknown value.
             */
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
                BATCH -> Value.BATCH
                FILES -> Value.FILES
                MODEL_GROUP -> Value.MODEL_GROUP
                SKILLS -> Value.SKILLS
                TOKEN_COUNT -> Value.TOKEN_COUNT
                WEB_SEARCH -> Value.WEB_SEARCH
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
                BATCH -> Known.BATCH
                FILES -> Known.FILES
                MODEL_GROUP -> Known.MODEL_GROUP
                SKILLS -> Known.SKILLS
                TOKEN_COUNT -> Known.TOKEN_COUNT
                WEB_SEARCH -> Known.WEB_SEARCH
                else -> throw AnthropicInvalidDataException("Unknown GroupType: $value")
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
        fun validate(): GroupType = apply {
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

            return other is GroupType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspaceRateLimit &&
            groupType == other.groupType &&
            limits == other.limits &&
            models == other.models &&
            rateLimitId == other.rateLimitId &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            groupType,
            limits,
            models,
            rateLimitId,
            type,
            workspaceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceRateLimit{groupType=$groupType, limits=$limits, models=$models, rateLimitId=$rateLimitId, type=$type, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
