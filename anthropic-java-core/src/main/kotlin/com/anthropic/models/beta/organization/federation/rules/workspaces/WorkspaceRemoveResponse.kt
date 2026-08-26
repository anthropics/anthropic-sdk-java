// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules.workspaces

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

class WorkspaceRemoveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val federationRuleId: JsonField<String>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("federation_rule_id")
        @ExcludeMissing
        federationRuleId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(federationRuleId, type, workspaceId, mutableMapOf())

    /**
     * Tagged ID of the federation rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun federationRuleId(): String = federationRuleId.getRequired("federation_rule_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("federation_rule_workspace_deleted")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Tagged ID of the workspace named in the delete request. Removal is idempotent.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Returns the raw JSON value of [federationRuleId].
     *
     * Unlike [federationRuleId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("federation_rule_id")
    @ExcludeMissing
    fun _federationRuleId(): JsonField<String> = federationRuleId

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
         * Returns a mutable builder for constructing an instance of [WorkspaceRemoveResponse].
         *
         * The following fields are required:
         * ```java
         * .federationRuleId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkspaceRemoveResponse]. */
    class Builder internal constructor() {

        private var federationRuleId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("federation_rule_workspace_deleted")
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(workspaceRemoveResponse: WorkspaceRemoveResponse) = apply {
            federationRuleId = workspaceRemoveResponse.federationRuleId
            type = workspaceRemoveResponse.type
            workspaceId = workspaceRemoveResponse.workspaceId
            additionalProperties = workspaceRemoveResponse.additionalProperties.toMutableMap()
        }

        /** Tagged ID of the federation rule. */
        fun federationRuleId(federationRuleId: String) =
            federationRuleId(JsonField.of(federationRuleId))

        /**
         * Sets [Builder.federationRuleId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.federationRuleId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun federationRuleId(federationRuleId: JsonField<String>) = apply {
            this.federationRuleId = federationRuleId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("federation_rule_workspace_deleted")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Tagged ID of the workspace named in the delete request. Removal is idempotent. */
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
         * Returns an immutable instance of [WorkspaceRemoveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .federationRuleId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WorkspaceRemoveResponse =
            WorkspaceRemoveResponse(
                checkRequired("federationRuleId", federationRuleId),
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
    fun validate(): WorkspaceRemoveResponse = apply {
        if (validated) {
            return@apply
        }

        federationRuleId()
        _type().let {
            if (it != JsonValue.from("federation_rule_workspace_deleted")) {
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
        (if (federationRuleId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("federation_rule_workspace_deleted")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WorkspaceRemoveResponse &&
            federationRuleId == other.federationRuleId &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(federationRuleId, type, workspaceId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WorkspaceRemoveResponse{federationRuleId=$federationRuleId, type=$type, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
