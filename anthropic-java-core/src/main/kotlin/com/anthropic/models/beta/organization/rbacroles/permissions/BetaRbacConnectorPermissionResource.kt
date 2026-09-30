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

class BetaRbacConnectorPermissionResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val connectorId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("connector_id")
        @ExcludeMissing
        connectorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(connectorId, type, mutableMapOf())

    /**
     * ID of the connector the permission applies to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorId(): String = connectorId.getRequired("connector_id")

    /**
     * Kind of resource the permission applies to.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("connector")
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
         * [BetaRbacConnectorPermissionResource].
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaRbacConnectorPermissionResource] with the required
         * [connectorId] set to the given value.
         */
        @JvmStatic fun of(connectorId: String) = builder().connectorId(connectorId).build()
    }

    /** A builder for [BetaRbacConnectorPermissionResource]. */
    class Builder internal constructor() {

        private var connectorId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("connector")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaRbacConnectorPermissionResource: BetaRbacConnectorPermissionResource
        ) = apply {
            connectorId = betaRbacConnectorPermissionResource.connectorId
            type = betaRbacConnectorPermissionResource.type
            additionalProperties =
                betaRbacConnectorPermissionResource.additionalProperties.toMutableMap()
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
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("connector")
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
         * Returns an immutable instance of [BetaRbacConnectorPermissionResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRbacConnectorPermissionResource =
            BetaRbacConnectorPermissionResource(
                checkRequired("connectorId", connectorId),
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
    fun validate(): BetaRbacConnectorPermissionResource = apply {
        if (validated) {
            return@apply
        }

        connectorId()
        _type().let {
            if (it != JsonValue.from("connector")) {
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
            type.let { if (it == JsonValue.from("connector")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRbacConnectorPermissionResource &&
            connectorId == other.connectorId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(connectorId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRbacConnectorPermissionResource{connectorId=$connectorId, type=$type, additionalProperties=$additionalProperties}"
}
