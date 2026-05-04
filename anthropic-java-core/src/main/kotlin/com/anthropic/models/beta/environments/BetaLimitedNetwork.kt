// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

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
import kotlin.jvm.optionals.getOrNull

/** Limited network access. */
class BetaLimitedNetwork
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowMcpServers: JsonField<Boolean>,
    private val allowPackageManagers: JsonField<Boolean>,
    private val allowedHosts: JsonField<List<String>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allow_mcp_servers")
        @ExcludeMissing
        allowMcpServers: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("allow_package_managers")
        @ExcludeMissing
        allowPackageManagers: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("allowed_hosts")
        @ExcludeMissing
        allowedHosts: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(allowMcpServers, allowPackageManagers, allowedHosts, type, mutableMapOf())

    /**
     * Permits outbound access to MCP server endpoints configured on the agent, beyond those listed
     * in the `allowed_hosts` array.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun allowMcpServers(): Boolean = allowMcpServers.getRequired("allow_mcp_servers")

    /**
     * Permits outbound access to public package registries (PyPI, npm, etc.) beyond those listed in
     * the `allowed_hosts` array.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun allowPackageManagers(): Boolean = allowPackageManagers.getRequired("allow_package_managers")

    /**
     * Specifies domains the container can reach.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun allowedHosts(): List<String> = allowedHosts.getRequired("allowed_hosts")

    /**
     * Network policy type
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("limited")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [allowMcpServers].
     *
     * Unlike [allowMcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allow_mcp_servers")
    @ExcludeMissing
    fun _allowMcpServers(): JsonField<Boolean> = allowMcpServers

    /**
     * Returns the raw JSON value of [allowPackageManagers].
     *
     * Unlike [allowPackageManagers], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("allow_package_managers")
    @ExcludeMissing
    fun _allowPackageManagers(): JsonField<Boolean> = allowPackageManagers

    /**
     * Returns the raw JSON value of [allowedHosts].
     *
     * Unlike [allowedHosts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_hosts")
    @ExcludeMissing
    fun _allowedHosts(): JsonField<List<String>> = allowedHosts

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
         * Returns a mutable builder for constructing an instance of [BetaLimitedNetwork].
         *
         * The following fields are required:
         * ```java
         * .allowMcpServers()
         * .allowPackageManagers()
         * .allowedHosts()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaLimitedNetwork]. */
    class Builder internal constructor() {

        private var allowMcpServers: JsonField<Boolean>? = null
        private var allowPackageManagers: JsonField<Boolean>? = null
        private var allowedHosts: JsonField<MutableList<String>>? = null
        private var type: JsonValue = JsonValue.from("limited")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaLimitedNetwork: BetaLimitedNetwork) = apply {
            allowMcpServers = betaLimitedNetwork.allowMcpServers
            allowPackageManagers = betaLimitedNetwork.allowPackageManagers
            allowedHosts = betaLimitedNetwork.allowedHosts.map { it.toMutableList() }
            type = betaLimitedNetwork.type
            additionalProperties = betaLimitedNetwork.additionalProperties.toMutableMap()
        }

        /**
         * Permits outbound access to MCP server endpoints configured on the agent, beyond those
         * listed in the `allowed_hosts` array.
         */
        fun allowMcpServers(allowMcpServers: Boolean) =
            allowMcpServers(JsonField.of(allowMcpServers))

        /**
         * Sets [Builder.allowMcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowMcpServers] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowMcpServers(allowMcpServers: JsonField<Boolean>) = apply {
            this.allowMcpServers = allowMcpServers
        }

        /**
         * Permits outbound access to public package registries (PyPI, npm, etc.) beyond those
         * listed in the `allowed_hosts` array.
         */
        fun allowPackageManagers(allowPackageManagers: Boolean) =
            allowPackageManagers(JsonField.of(allowPackageManagers))

        /**
         * Sets [Builder.allowPackageManagers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowPackageManagers] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowPackageManagers(allowPackageManagers: JsonField<Boolean>) = apply {
            this.allowPackageManagers = allowPackageManagers
        }

        /** Specifies domains the container can reach. */
        fun allowedHosts(allowedHosts: List<String>) = allowedHosts(JsonField.of(allowedHosts))

        /**
         * Sets [Builder.allowedHosts] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedHosts] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedHosts(allowedHosts: JsonField<List<String>>) = apply {
            this.allowedHosts = allowedHosts.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedHosts].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedHost(allowedHost: String) = apply {
            allowedHosts =
                (allowedHosts ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedHosts", it).add(allowedHost)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("limited")
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
         * Returns an immutable instance of [BetaLimitedNetwork].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .allowMcpServers()
         * .allowPackageManagers()
         * .allowedHosts()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaLimitedNetwork =
            BetaLimitedNetwork(
                checkRequired("allowMcpServers", allowMcpServers),
                checkRequired("allowPackageManagers", allowPackageManagers),
                checkRequired("allowedHosts", allowedHosts).map { it.toImmutable() },
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
    fun validate(): BetaLimitedNetwork = apply {
        if (validated) {
            return@apply
        }

        allowMcpServers()
        allowPackageManagers()
        allowedHosts()
        _type().let {
            if (it != JsonValue.from("limited")) {
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
        (if (allowMcpServers.asKnown().isPresent) 1 else 0) +
            (if (allowPackageManagers.asKnown().isPresent) 1 else 0) +
            (allowedHosts.asKnown().getOrNull()?.size ?: 0) +
            type.let { if (it == JsonValue.from("limited")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaLimitedNetwork &&
            allowMcpServers == other.allowMcpServers &&
            allowPackageManagers == other.allowPackageManagers &&
            allowedHosts == other.allowedHosts &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            allowMcpServers,
            allowPackageManagers,
            allowedHosts,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaLimitedNetwork{allowMcpServers=$allowMcpServers, allowPackageManagers=$allowPackageManagers, allowedHosts=$allowedHosts, type=$type, additionalProperties=$additionalProperties}"
}
