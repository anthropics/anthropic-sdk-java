package com.anthropic.models.beta.organization.rbacgroups.members

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

class MemberRemoveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val rbacGroupId: JsonField<String>,
    private val type: JsonValue,
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
    ) : this(rbacGroupId, type, userId, mutableMapOf())

    /**
     * ID of the RBAC Group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupId(): String = rbacGroupId.getRequired("rbac_group_id")

    /**
     * Deleted object type. For RBAC Group Members, this is always `"rbac_group_member_deleted"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("rbac_group_member_deleted")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * ID of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun userId(): String = userId.getRequired("user_id")

    /**
     * Returns the raw JSON value of [rbacGroupId].
     *
     * Unlike [rbacGroupId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_id")
    @ExcludeMissing
    fun _rbacGroupId(): JsonField<String> = rbacGroupId

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

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
         * Returns a mutable builder for constructing an instance of [MemberRemoveResponse].
         *
         * The following fields are required:
         * ```java
         * .rbacGroupId()
         * .userId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [MemberRemoveResponse]. */
    class Builder internal constructor() {

        private var rbacGroupId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("rbac_group_member_deleted")
        private var userId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(memberRemoveResponse: MemberRemoveResponse) = apply {
            rbacGroupId = memberRemoveResponse.rbacGroupId
            type = memberRemoveResponse.type
            userId = memberRemoveResponse.userId
            additionalProperties = memberRemoveResponse.additionalProperties.toMutableMap()
        }

        /** ID of the RBAC Group. */
        fun rbacGroupId(rbacGroupId: String) = rbacGroupId(JsonField.of(rbacGroupId))

        /**
         * Sets [Builder.rbacGroupId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rbacGroupId(rbacGroupId: JsonField<String>) = apply { this.rbacGroupId = rbacGroupId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("rbac_group_member_deleted")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** ID of the User. */
        fun userId(userId: String) = userId(JsonField.of(userId))

        /**
         * Sets [Builder.userId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun userId(userId: JsonField<String>) = apply { this.userId = userId }

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
         * Returns an immutable instance of [MemberRemoveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .rbacGroupId()
         * .userId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): MemberRemoveResponse =
            MemberRemoveResponse(
                checkRequired("rbacGroupId", rbacGroupId),
                type,
                checkRequired("userId", userId),
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
    fun validate(): MemberRemoveResponse = apply {
        if (validated) {
            return@apply
        }

        rbacGroupId()
        _type().let {
            if (it != JsonValue.from("rbac_group_member_deleted")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        userId()
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
        (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("rbac_group_member_deleted")) 1 else 0 } +
            (if (userId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is MemberRemoveResponse &&
            rbacGroupId == other.rbacGroupId &&
            type == other.type &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(rbacGroupId, type, userId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "MemberRemoveResponse{rbacGroupId=$rbacGroupId, type=$type, userId=$userId, additionalProperties=$additionalProperties}"
}
