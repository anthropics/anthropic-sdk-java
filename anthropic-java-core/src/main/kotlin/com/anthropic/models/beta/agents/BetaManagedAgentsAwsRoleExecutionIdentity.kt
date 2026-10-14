package com.anthropic.models.beta.agents

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

/** An AWS IAM role principal as returned in API responses. */
class BetaManagedAgentsAwsRoleExecutionIdentity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val roleArn: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("role_arn") @ExcludeMissing roleArn: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(roleArn, type, mutableMapOf())

    /**
     * The IAM role's ARN.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun roleArn(): String = roleArn.getRequired("role_arn")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("aws_role")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [roleArn].
     *
     * Unlike [roleArn], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("role_arn") @ExcludeMissing fun _roleArn(): JsonField<String> = roleArn

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
         * [BetaManagedAgentsAwsRoleExecutionIdentity].
         *
         * The following fields are required:
         * ```java
         * .roleArn()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsAwsRoleExecutionIdentity] with the
         * required [roleArn] set to the given value.
         */
        @JvmStatic fun of(roleArn: String) = builder().roleArn(roleArn).build()
    }

    /** A builder for [BetaManagedAgentsAwsRoleExecutionIdentity]. */
    class Builder internal constructor() {

        private var roleArn: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("aws_role")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAwsRoleExecutionIdentity: BetaManagedAgentsAwsRoleExecutionIdentity
        ) = apply {
            roleArn = betaManagedAgentsAwsRoleExecutionIdentity.roleArn
            type = betaManagedAgentsAwsRoleExecutionIdentity.type
            additionalProperties =
                betaManagedAgentsAwsRoleExecutionIdentity.additionalProperties.toMutableMap()
        }

        /** The IAM role's ARN. */
        fun roleArn(roleArn: String) = roleArn(JsonField.of(roleArn))

        /**
         * Sets [Builder.roleArn] to an arbitrary JSON value.
         *
         * You should usually call [Builder.roleArn] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun roleArn(roleArn: JsonField<String>) = apply { this.roleArn = roleArn }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("aws_role")
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
         * Returns an immutable instance of [BetaManagedAgentsAwsRoleExecutionIdentity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .roleArn()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAwsRoleExecutionIdentity =
            BetaManagedAgentsAwsRoleExecutionIdentity(
                checkRequired("roleArn", roleArn),
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
    fun validate(): BetaManagedAgentsAwsRoleExecutionIdentity = apply {
        if (validated) {
            return@apply
        }

        roleArn()
        _type().let {
            if (it != JsonValue.from("aws_role")) {
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
        (if (roleArn.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("aws_role")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAwsRoleExecutionIdentity &&
            roleArn == other.roleArn &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(roleArn, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAwsRoleExecutionIdentity{roleArn=$roleArn, type=$type, additionalProperties=$additionalProperties}"
}
