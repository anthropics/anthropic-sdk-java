package com.anthropic.models.beta.organization.rbacroles.permissions

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

class BetaRbacConnectorToolPermissionResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val connectorId: JsonField<String>,
    private val toolName: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("connector_id")
        @ExcludeMissing
        connectorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tool_name") @ExcludeMissing toolName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(connectorId, toolName, type, mutableMapOf())

    /**
     * ID of the connector the permission applies to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorId(): String = connectorId.getRequired("connector_id")

    /**
     * Published name of the connector tool the permission applies to.
     *
     * When the published name contains characters outside `[a-zA-Z0-9_-]` (or collides with a
     * reserved form), it is server-encoded into a stable `{prefix}_{32-hex}` form — a shortened
     * readable prefix of the name plus a hash — from which the published name is not recoverable.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolName(): String = toolName.getRequired("tool_name")

    /**
     * Kind of resource the permission applies to.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("connector_tool")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [connectorId].
     *
     * Unlike [connectorId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("connector_id")
    @ExcludeMissing
    fun _connectorId(): JsonField<String> = connectorId

    /**
     * Returns the raw JSON value of [toolName].
     *
     * Unlike [toolName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_name") @ExcludeMissing fun _toolName(): JsonField<String> = toolName

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
         * [BetaRbacConnectorToolPermissionResource].
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * .toolName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRbacConnectorToolPermissionResource]. */
    class Builder internal constructor() {

        private var connectorId: JsonField<String>? = null
        private var toolName: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("connector_tool")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaRbacConnectorToolPermissionResource: BetaRbacConnectorToolPermissionResource
        ) = apply {
            connectorId = betaRbacConnectorToolPermissionResource.connectorId
            toolName = betaRbacConnectorToolPermissionResource.toolName
            type = betaRbacConnectorToolPermissionResource.type
            additionalProperties =
                betaRbacConnectorToolPermissionResource.additionalProperties.toMutableMap()
        }

        /** ID of the connector the permission applies to. */
        fun connectorId(connectorId: String) = connectorId(JsonField.of(connectorId))

        /**
         * Sets [Builder.connectorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.connectorId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun connectorId(connectorId: JsonField<String>) = apply { this.connectorId = connectorId }

        /**
         * Published name of the connector tool the permission applies to.
         *
         * When the published name contains characters outside `[a-zA-Z0-9_-]` (or collides with a
         * reserved form), it is server-encoded into a stable `{prefix}_{32-hex}` form — a shortened
         * readable prefix of the name plus a hash — from which the published name is not
         * recoverable.
         */
        fun toolName(toolName: String) = toolName(JsonField.of(toolName))

        /**
         * Sets [Builder.toolName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolName] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun toolName(toolName: JsonField<String>) = apply { this.toolName = toolName }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("connector_tool")
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
         * Returns an immutable instance of [BetaRbacConnectorToolPermissionResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * .toolName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRbacConnectorToolPermissionResource =
            BetaRbacConnectorToolPermissionResource(
                checkRequired("connectorId", connectorId),
                checkRequired("toolName", toolName),
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
    fun validate(): BetaRbacConnectorToolPermissionResource = apply {
        if (validated) {
            return@apply
        }

        connectorId()
        toolName()
        _type().let {
            if (it != JsonValue.from("connector_tool")) {
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
        (if (connectorId.asKnown().isPresent) 1 else 0) +
            (if (toolName.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("connector_tool")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRbacConnectorToolPermissionResource &&
            connectorId == other.connectorId &&
            toolName == other.toolName &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(connectorId, toolName, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRbacConnectorToolPermissionResource{connectorId=$connectorId, toolName=$toolName, type=$type, additionalProperties=$additionalProperties}"
}
