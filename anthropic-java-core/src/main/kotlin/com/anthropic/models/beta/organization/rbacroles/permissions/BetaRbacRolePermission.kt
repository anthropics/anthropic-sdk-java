package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

class BetaRbacRolePermission
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val action: JsonField<String>,
    private val resource: JsonField<Resource>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("action") @ExcludeMissing action: JsonField<String> = JsonMissing.of(),
        @JsonProperty("resource") @ExcludeMissing resource: JsonField<Resource> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(action, resource, type, mutableMapOf())

    /**
     * Action the permission grants on the resource.
     *
     * The vocabulary follows the resource: an `organization` grant carries a product-feature
     * entitlement (for example `chat`), an admin-panel permission entitlement (`permission_*`), or
     * a blanket capability-access mode — `capability_access_all` grants every product-feature
     * entitlement, and `capability_access_all_ga` grants the generally-available subset as it
     * stands at permission-check time; neither mode grants model-access entitlements. A consumer
     * enumerating a role's per-feature grants should treat a blanket row as granting every
     * product-feature entitlement it covers, or it will under-report the role's effective access. A
     * `connector_tool` grant carries a tool-access action (`use` or `always_allow`); a
     * `connector_scope` grant carries the scope action `grant` (the role may receive the named
     * OAuth scope when tokens are minted for the connector); `connector` and `all_connectors`
     * grants carry a tool-access action, the scope action, or an authentication-method action
     * (`interactive` or `managed`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun action(): String = action.getRequired("action")

    /**
     * What the permission applies to.
     *
     * A tagged union: `type` names the kind of resource and determines which identifier fields are
     * present.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun resource(): Resource = resource.getRequired("resource")

    /**
     * Object type.
     *
     * For RBAC Role Permissions, this is always `"rbac_role_permission"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("rbac_role_permission")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [action].
     *
     * Unlike [action], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("action") @ExcludeMissing fun _action(): JsonField<String> = action

    /**
     * Returns the raw JSON value of [resource].
     *
     * Unlike [resource], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resource") @ExcludeMissing fun _resource(): JsonField<Resource> = resource

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
         * Returns a mutable builder for constructing an instance of [BetaRbacRolePermission].
         *
         * The following fields are required:
         * ```java
         * .action()
         * .resource()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRbacRolePermission]. */
    class Builder internal constructor() {

        private var action: JsonField<String>? = null
        private var resource: JsonField<Resource>? = null
        private var type: JsonValue = JsonValue.from("rbac_role_permission")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRbacRolePermission: BetaRbacRolePermission) = apply {
            action = betaRbacRolePermission.action
            resource = betaRbacRolePermission.resource
            type = betaRbacRolePermission.type
            additionalProperties = betaRbacRolePermission.additionalProperties.toMutableMap()
        }

        /**
         * Action the permission grants on the resource.
         *
         * The vocabulary follows the resource: an `organization` grant carries a product-feature
         * entitlement (for example `chat`), an admin-panel permission entitlement (`permission_*`),
         * or a blanket capability-access mode — `capability_access_all` grants every
         * product-feature entitlement, and `capability_access_all_ga` grants the
         * generally-available subset as it stands at permission-check time; neither mode grants
         * model-access entitlements. A consumer enumerating a role's per-feature grants should
         * treat a blanket row as granting every product-feature entitlement it covers, or it will
         * under-report the role's effective access. A `connector_tool` grant carries a tool-access
         * action (`use` or `always_allow`); a `connector_scope` grant carries the scope action
         * `grant` (the role may receive the named OAuth scope when tokens are minted for the
         * connector); `connector` and `all_connectors` grants carry a tool-access action, the scope
         * action, or an authentication-method action (`interactive` or `managed`).
         */
        fun action(action: String) = action(JsonField.of(action))

        /**
         * Sets [Builder.action] to an arbitrary JSON value.
         *
         * You should usually call [Builder.action] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun action(action: JsonField<String>) = apply { this.action = action }

        /**
         * What the permission applies to.
         *
         * A tagged union: `type` names the kind of resource and determines which identifier fields
         * are present.
         */
        fun resource(resource: Resource) = resource(JsonField.of(resource))

        /**
         * Sets [Builder.resource] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resource] with a well-typed [Resource] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun resource(resource: JsonField<Resource>) = apply { this.resource = resource }

        /** Alias for calling [resource] with `Resource.ofOrganization(organization)`. */
        fun resource(organization: BetaRbacOrganizationPermissionResource) =
            resource(Resource.ofOrganization(organization))

        /**
         * Alias for calling [resource] with the following:
         * ```java
         * BetaRbacOrganizationPermissionResource.builder()
         *     .organizationId(organizationId)
         *     .build()
         * ```
         */
        fun organizationResource(organizationId: String) =
            resource(
                BetaRbacOrganizationPermissionResource.builder()
                    .organizationId(organizationId)
                    .build()
            )

        /** Alias for calling [resource] with `Resource.ofConnectorTool(connectorTool)`. */
        fun resource(connectorTool: BetaRbacConnectorToolPermissionResource) =
            resource(Resource.ofConnectorTool(connectorTool))

        /** Alias for calling [resource] with `Resource.ofConnectorScope(connectorScope)`. */
        fun resource(connectorScope: BetaRbacConnectorScopePermissionResource) =
            resource(Resource.ofConnectorScope(connectorScope))

        /** Alias for calling [resource] with `Resource.ofConnector(connector)`. */
        fun resource(connector: BetaRbacConnectorPermissionResource) =
            resource(Resource.ofConnector(connector))

        /**
         * Alias for calling [resource] with the following:
         * ```java
         * BetaRbacConnectorPermissionResource.builder()
         *     .connectorId(connectorId)
         *     .build()
         * ```
         */
        fun connectorResource(connectorId: String) =
            resource(BetaRbacConnectorPermissionResource.builder().connectorId(connectorId).build())

        /** Alias for calling [resource] with `Resource.ofAllConnectors(allConnectors)`. */
        fun resource(allConnectors: BetaRbacAllConnectorsPermissionResource) =
            resource(Resource.ofAllConnectors(allConnectors))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("rbac_role_permission")
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
         * Returns an immutable instance of [BetaRbacRolePermission].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .action()
         * .resource()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRbacRolePermission =
            BetaRbacRolePermission(
                checkRequired("action", action),
                checkRequired("resource", resource),
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
    fun validate(): BetaRbacRolePermission = apply {
        if (validated) {
            return@apply
        }

        action()
        resource().validate()
        _type().let {
            if (it != JsonValue.from("rbac_role_permission")) {
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
        (if (action.asKnown().isPresent) 1 else 0) +
            (resource.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("rbac_role_permission")) 1 else 0 }

    /**
     * What the permission applies to.
     *
     * A tagged union: `type` names the kind of resource and determines which identifier fields are
     * present.
     */
    @JsonDeserialize(using = Resource.Deserializer::class)
    @JsonSerialize(using = Resource.Serializer::class)
    class Resource
    private constructor(
        private val organization: BetaRbacOrganizationPermissionResource? = null,
        private val connectorTool: BetaRbacConnectorToolPermissionResource? = null,
        private val connectorScope: BetaRbacConnectorScopePermissionResource? = null,
        private val connector: BetaRbacConnectorPermissionResource? = null,
        private val allConnectors: BetaRbacAllConnectorsPermissionResource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            when {
                organization != null -> Type.ORGANIZATION
                connectorTool != null -> Type.CONNECTOR_TOOL
                connectorScope != null -> Type.CONNECTOR_SCOPE
                connector != null -> Type.CONNECTOR
                allConnectors != null -> Type.ALL_CONNECTORS
                else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }

        fun connectorId(): Optional<String> =
            when {
                organization != null -> Optional.empty()
                connectorTool != null -> Optional.of(connectorTool.connectorId())
                connectorScope != null -> Optional.of(connectorScope.connectorId())
                connector != null -> Optional.of(connector.connectorId())
                allConnectors != null -> Optional.empty()
                else -> _json.getProperty<String>("connector_id").asKnown()
            }

        fun organization(): Optional<BetaRbacOrganizationPermissionResource> =
            Optional.ofNullable(organization)

        fun connectorTool(): Optional<BetaRbacConnectorToolPermissionResource> =
            Optional.ofNullable(connectorTool)

        fun connectorScope(): Optional<BetaRbacConnectorScopePermissionResource> =
            Optional.ofNullable(connectorScope)

        fun connector(): Optional<BetaRbacConnectorPermissionResource> =
            Optional.ofNullable(connector)

        fun allConnectors(): Optional<BetaRbacAllConnectorsPermissionResource> =
            Optional.ofNullable(allConnectors)

        fun isOrganization(): Boolean = organization != null

        fun isConnectorTool(): Boolean = connectorTool != null

        fun isConnectorScope(): Boolean = connectorScope != null

        fun isConnector(): Boolean = connector != null

        fun isAllConnectors(): Boolean = allConnectors != null

        fun asOrganization(): BetaRbacOrganizationPermissionResource =
            organization.getOrThrow("organization")

        fun asConnectorTool(): BetaRbacConnectorToolPermissionResource =
            connectorTool.getOrThrow("connectorTool")

        fun asConnectorScope(): BetaRbacConnectorScopePermissionResource =
            connectorScope.getOrThrow("connectorScope")

        fun asConnector(): BetaRbacConnectorPermissionResource = connector.getOrThrow("connector")

        fun asAllConnectors(): BetaRbacAllConnectorsPermissionResource =
            allConnectors.getOrThrow("allConnectors")

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
         * Optional<String> result = resource.accept(new Resource.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitOrganization(BetaRbacOrganizationPermissionResource organization) {
         *         return Optional.of(organization.toString());
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
                organization != null -> visitor.visitOrganization(organization)
                connectorTool != null -> visitor.visitConnectorTool(connectorTool)
                connectorScope != null -> visitor.visitConnectorScope(connectorScope)
                connector != null -> visitor.visitConnector(connector)
                allConnectors != null -> visitor.visitAllConnectors(allConnectors)
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
        fun validate(): Resource = apply {
            if (validated) {
                return@apply
            }

            when {
                organization != null -> organization.validate()
                connectorTool != null -> connectorTool.validate()
                connectorScope != null -> connectorScope.validate()
                connector != null -> connector.validate()
                allConnectors != null -> allConnectors.validate()
                else -> throw AnthropicInvalidDataException("Unknown Resource: $_json")
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            when {
                organization != null -> organization.validity()
                connectorTool != null -> connectorTool.validity()
                connectorScope != null -> connectorScope.validity()
                connector != null -> connector.validity()
                allConnectors != null -> allConnectors.validity()
                else -> 0
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Resource &&
                organization == other.organization &&
                connectorTool == other.connectorTool &&
                connectorScope == other.connectorScope &&
                connector == other.connector &&
                allConnectors == other.allConnectors
        }

        override fun hashCode(): Int =
            Objects.hash(organization, connectorTool, connectorScope, connector, allConnectors)

        override fun toString(): String =
            when {
                organization != null -> "Resource{organization=$organization}"
                connectorTool != null -> "Resource{connectorTool=$connectorTool}"
                connectorScope != null -> "Resource{connectorScope=$connectorScope}"
                connector != null -> "Resource{connector=$connector}"
                allConnectors != null -> "Resource{allConnectors=$allConnectors}"
                _json != null -> "Resource{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Resource")
            }

        companion object {

            @JvmStatic
            fun ofOrganization(organization: BetaRbacOrganizationPermissionResource) =
                Resource(organization = organization)

            /**
             * Returns an immutable instance of [Resource] whose [ofOrganization] variant is built
             * from the given required [organizationId].
             */
            @JvmStatic
            fun ofOrganization(organizationId: String) =
                ofOrganization(BetaRbacOrganizationPermissionResource.of(organizationId))

            @JvmStatic
            fun ofConnectorTool(connectorTool: BetaRbacConnectorToolPermissionResource) =
                Resource(connectorTool = connectorTool)

            @JvmStatic
            fun ofConnectorScope(connectorScope: BetaRbacConnectorScopePermissionResource) =
                Resource(connectorScope = connectorScope)

            @JvmStatic
            fun ofConnector(connector: BetaRbacConnectorPermissionResource) =
                Resource(connector = connector)

            /**
             * Returns an immutable instance of [Resource] whose [ofConnector] variant is built from
             * the given required [connectorId].
             */
            @JvmStatic
            fun ofConnector(connectorId: String) =
                ofConnector(BetaRbacConnectorPermissionResource.of(connectorId))

            @JvmStatic
            fun ofAllConnectors(allConnectors: BetaRbacAllConnectorsPermissionResource) =
                Resource(allConnectors = allConnectors)
        }

        /**
         * An interface that defines how to map each variant of [Resource] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitOrganization(organization: BetaRbacOrganizationPermissionResource): T

            fun visitConnectorTool(connectorTool: BetaRbacConnectorToolPermissionResource): T

            fun visitConnectorScope(connectorScope: BetaRbacConnectorScopePermissionResource): T

            fun visitConnector(connector: BetaRbacConnectorPermissionResource): T

            fun visitAllConnectors(allConnectors: BetaRbacAllConnectorsPermissionResource): T

            /**
             * Maps an unknown variant of [Resource] to a value of type [T].
             *
             * An instance of [Resource] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Resource: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Resource>(Resource::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Resource {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "organization" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaRbacOrganizationPermissionResource>(),
                            )
                            ?.let { Resource(organization = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                    "connector_tool" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaRbacConnectorToolPermissionResource>(),
                            )
                            ?.let { Resource(connectorTool = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                    "connector_scope" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaRbacConnectorScopePermissionResource>(),
                            )
                            ?.let { Resource(connectorScope = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                    "connector" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaRbacConnectorPermissionResource>(),
                            )
                            ?.let { Resource(connector = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                    "all_connectors" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaRbacAllConnectorsPermissionResource>(),
                            )
                            ?.let { Resource(allConnectors = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                }

                return Resource(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Resource>(Resource::class) {

            override fun serialize(
                value: Resource,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.organization != null -> generator.writeObject(value.organization)
                    value.connectorTool != null -> generator.writeObject(value.connectorTool)
                    value.connectorScope != null -> generator.writeObject(value.connectorScope)
                    value.connector != null -> generator.writeObject(value.connector)
                    value.allConnectors != null -> generator.writeObject(value.allConnectors)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Resource")
                }
            }
        }

        class Type private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val ORGANIZATION = Type(JsonField.of("organization"))

                @JvmField val CONNECTOR_TOOL = Type(JsonField.of("connector_tool"))

                @JvmField val CONNECTOR_SCOPE = Type(JsonField.of("connector_scope"))

                @JvmField val CONNECTOR = Type(JsonField.of("connector"))

                @JvmField val ALL_CONNECTORS = Type(JsonField.of("all_connectors"))

                @JvmStatic
                fun of(value: String): Type =
                    // Intern known values so `==` works
                    when (value) {
                        "organization" -> ORGANIZATION
                        "connector_tool" -> CONNECTOR_TOOL
                        "connector_scope" -> CONNECTOR_SCOPE
                        "connector" -> CONNECTOR
                        "all_connectors" -> ALL_CONNECTORS
                        else -> Type(JsonField.of(value))
                    }

                @JsonCreator
                @JvmStatic
                fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                ORGANIZATION,
                CONNECTOR_TOOL,
                CONNECTOR_SCOPE,
                CONNECTOR,
                ALL_CONNECTORS,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ORGANIZATION,
                CONNECTOR_TOOL,
                CONNECTOR_SCOPE,
                CONNECTOR,
                ALL_CONNECTORS,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    ORGANIZATION -> Value.ORGANIZATION
                    CONNECTOR_TOOL -> Value.CONNECTOR_TOOL
                    CONNECTOR_SCOPE -> Value.CONNECTOR_SCOPE
                    CONNECTOR -> Value.CONNECTOR
                    ALL_CONNECTORS -> Value.ALL_CONNECTORS
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    ORGANIZATION -> Known.ORGANIZATION
                    CONNECTOR_TOOL -> Known.CONNECTOR_TOOL
                    CONNECTOR_SCOPE -> Known.CONNECTOR_SCOPE
                    CONNECTOR -> Known.CONNECTOR
                    ALL_CONNECTORS -> Known.ALL_CONNECTORS
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRbacRolePermission &&
            action == other.action &&
            resource == other.resource &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(action, resource, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRbacRolePermission{action=$action, resource=$resource, type=$type, additionalProperties=$additionalProperties}"
}
