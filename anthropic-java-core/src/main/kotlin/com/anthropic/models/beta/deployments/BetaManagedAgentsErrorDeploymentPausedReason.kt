package com.anthropic.models.beta.deployments

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

/** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
class BetaManagedAgentsErrorDeploymentPausedReason
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error")
        @ExcludeMissing
        error: JsonField<BetaManagedAgentsDeploymentPausedReasonError> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(error, type, mutableMapOf())

    /**
     * The failed run's error.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): BetaManagedAgentsDeploymentPausedReasonError = error.getRequired("error")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error")
    @ExcludeMissing
    fun _error(): JsonField<BetaManagedAgentsDeploymentPausedReasonError> = error

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
         * [BetaManagedAgentsErrorDeploymentPausedReason].
         *
         * The following fields are required:
         * ```java
         * .error()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsErrorDeploymentPausedReason] with the
         * required [error] set to the given value.
         */
        @JvmStatic
        fun of(error: BetaManagedAgentsDeploymentPausedReasonError) = builder().error(error).build()

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)`.
         */
        @JvmStatic
        fun of(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                    environmentArchived
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)`.
         */
        @JvmStatic
        fun of(agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)`.
         */
        @JvmStatic
        fun of(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                    environmentNotFound
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)`.
         */
        @JvmStatic
        fun of(vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)`.
         */
        @JvmStatic
        fun of(fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(sessionResourceNotFound)`.
         */
        @JvmStatic
        fun of(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                    sessionResourceNotFound
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)`.
         */
        @JvmStatic
        fun of(workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(organizationDisabled)`.
         */
        @JvmStatic
        fun of(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                    organizationDisabled
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)`.
         */
        @JvmStatic
        fun of(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                    memoryStoreArchived
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)`.
         */
        @JvmStatic
        fun of(skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)`.
         */
        @JvmStatic
        fun of(vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)`.
         */
        @JvmStatic
        fun of(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)`.
         */
        @JvmStatic
        fun of(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ) =
            of(
                BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported
                )
            )

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)`.
         */
        @JvmStatic
        fun of(mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError) =
            of(BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked))
    }

    /** A builder for [BetaManagedAgentsErrorDeploymentPausedReason]. */
    class Builder internal constructor() {

        private var error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>? = null
        private var type: JsonValue = JsonValue.from("error")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsErrorDeploymentPausedReason:
                BetaManagedAgentsErrorDeploymentPausedReason
        ) = apply {
            error = betaManagedAgentsErrorDeploymentPausedReason.error
            type = betaManagedAgentsErrorDeploymentPausedReason.type
            additionalProperties =
                betaManagedAgentsErrorDeploymentPausedReason.additionalProperties.toMutableMap()
        }

        /** The failed run's error. */
        fun error(error: BetaManagedAgentsDeploymentPausedReasonError) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed
         * [BetaManagedAgentsDeploymentPausedReasonError] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>) = apply {
            this.error = error
        }

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)`.
         */
        fun error(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                    environmentArchived
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)`.
         */
        fun error(agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)`.
         */
        fun error(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                    environmentNotFound
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)`.
         */
        fun error(vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)`.
         */
        fun error(fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(sessionResourceNotFound)`.
         */
        fun error(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                    sessionResourceNotFound
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)`.
         */
        fun error(
            workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(organizationDisabled)`.
         */
        fun error(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                    organizationDisabled
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)`.
         */
        fun error(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                    memoryStoreArchived
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)`.
         */
        fun error(skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)`.
         */
        fun error(vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)`.
         */
        fun error(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)`.
         */
        fun error(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)`.
         */
        fun error(mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("error")
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
         * Returns an immutable instance of [BetaManagedAgentsErrorDeploymentPausedReason].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .error()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsErrorDeploymentPausedReason =
            BetaManagedAgentsErrorDeploymentPausedReason(
                checkRequired("error", error),
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
    fun validate(): BetaManagedAgentsErrorDeploymentPausedReason = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        _type().let {
            if (it != JsonValue.from("error")) {
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
        (error.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("error")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsErrorDeploymentPausedReason &&
            error == other.error &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(error, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsErrorDeploymentPausedReason{error=$error, type=$type, additionalProperties=$additionalProperties}"
}
