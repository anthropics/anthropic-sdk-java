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

class BetaRbacConnectorScopePermissionResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val connectorId: JsonField<String>,
    private val scope: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("connector_id")
        @ExcludeMissing
        connectorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(connectorId, scope, type, mutableMapOf())

    /**
     * ID of the connector the permission applies to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorId(): String = connectorId.getRequired("connector_id")

    /**
     * OAuth scope the permission names — the role may receive this scope when tokens are minted for
     * the connector.
     *
     * Subject to the same encoding rule as `tool_name`: a scope containing characters outside
     * `[a-zA-Z0-9_-]` (or colliding with a reserved form) appears server-encoded in a stable
     * `{prefix}_{32-hex}` form. OAuth scopes routinely contain `:` and `/`, so most appear encoded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scope(): String = scope.getRequired("scope")

    /**
     * Kind of resource the permission applies to.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("connector_scope")
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
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<String> = scope

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
         * [BetaRbacConnectorScopePermissionResource].
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * .scope()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRbacConnectorScopePermissionResource]. */
    class Builder internal constructor() {

        private var connectorId: JsonField<String>? = null
        private var scope: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("connector_scope")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaRbacConnectorScopePermissionResource: BetaRbacConnectorScopePermissionResource
        ) = apply {
            connectorId = betaRbacConnectorScopePermissionResource.connectorId
            scope = betaRbacConnectorScopePermissionResource.scope
            type = betaRbacConnectorScopePermissionResource.type
            additionalProperties =
                betaRbacConnectorScopePermissionResource.additionalProperties.toMutableMap()
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
         * OAuth scope the permission names — the role may receive this scope when tokens are minted
         * for the connector.
         *
         * Subject to the same encoding rule as `tool_name`: a scope containing characters outside
         * `[a-zA-Z0-9_-]` (or colliding with a reserved form) appears server-encoded in a stable
         * `{prefix}_{32-hex}` form. OAuth scopes routinely contain `:` and `/`, so most appear
         * encoded.
         */
        fun scope(scope: String) = scope(JsonField.of(scope))

        /**
         * Sets [Builder.scope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scope] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun scope(scope: JsonField<String>) = apply { this.scope = scope }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("connector_scope")
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
         * Returns an immutable instance of [BetaRbacConnectorScopePermissionResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .connectorId()
         * .scope()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRbacConnectorScopePermissionResource =
            BetaRbacConnectorScopePermissionResource(
                checkRequired("connectorId", connectorId),
                checkRequired("scope", scope),
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
    fun validate(): BetaRbacConnectorScopePermissionResource = apply {
        if (validated) {
            return@apply
        }

        connectorId()
        scope()
        _type().let {
            if (it != JsonValue.from("connector_scope")) {
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
            (if (scope.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("connector_scope")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRbacConnectorScopePermissionResource &&
            connectorId == other.connectorId &&
            scope == other.scope &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(connectorId, scope, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRbacConnectorScopePermissionResource{connectorId=$connectorId, scope=$scope, type=$type, additionalProperties=$additionalProperties}"
}
