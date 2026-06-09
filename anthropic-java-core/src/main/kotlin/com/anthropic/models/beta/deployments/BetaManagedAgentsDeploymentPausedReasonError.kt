// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The error that triggered an auto-pause. Matches the failed run's `error.type`. */
@JsonDeserialize(using = BetaManagedAgentsDeploymentPausedReasonError.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsDeploymentPausedReasonError.Serializer::class)
class BetaManagedAgentsDeploymentPausedReasonError
private constructor(
    private val environmentArchived:
        BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError? =
        null,
    private val agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError? = null,
    private val environmentNotFound:
        BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError? =
        null,
    private val vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError? = null,
    private val fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError? = null,
    private val sessionResourceNotFound:
        BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError? =
        null,
    private val workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError? =
        null,
    private val organizationDisabled:
        BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError? =
        null,
    private val memoryStoreArchived:
        BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError? =
        null,
    private val skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError? = null,
    private val vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError? = null,
    private val unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError? = null,
    private val selfHostedResourcesUnsupported:
        BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError? =
        null,
    private val mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError? =
        null,
    private val _json: JsonValue? = null,
) {

    /** The deployment's environment was archived. */
    fun environmentArchived():
        Optional<BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError> =
        Optional.ofNullable(environmentArchived)

    /** The deployment's agent was archived. */
    fun agentArchived(): Optional<BetaManagedAgentsAgentArchivedDeploymentPausedReasonError> =
        Optional.ofNullable(agentArchived)

    /** The deployment's environment no longer exists. */
    fun environmentNotFound():
        Optional<BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError> =
        Optional.ofNullable(environmentNotFound)

    /** A vault referenced by the deployment no longer exists. */
    fun vaultNotFound(): Optional<BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError> =
        Optional.ofNullable(vaultNotFound)

    /** A file resource referenced by the deployment no longer exists. */
    fun fileNotFound(): Optional<BetaManagedAgentsFileNotFoundDeploymentPausedReasonError> =
        Optional.ofNullable(fileNotFound)

    /** A referenced resource no longer exists and its kind was not reported. */
    fun sessionResourceNotFound():
        Optional<BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError> =
        Optional.ofNullable(sessionResourceNotFound)

    /** The deployment's workspace was archived. */
    fun workspaceArchived():
        Optional<BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError> =
        Optional.ofNullable(workspaceArchived)

    /** The deployment's organization is disabled. */
    fun organizationDisabled():
        Optional<BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError> =
        Optional.ofNullable(organizationDisabled)

    /** A memory store referenced by the deployment is archived. */
    fun memoryStoreArchived():
        Optional<BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError> =
        Optional.ofNullable(memoryStoreArchived)

    /** A skill referenced by the deployment's agent no longer exists. */
    fun skillNotFound(): Optional<BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError> =
        Optional.ofNullable(skillNotFound)

    /** A vault referenced by the deployment is archived. */
    fun vaultArchived(): Optional<BetaManagedAgentsVaultArchivedDeploymentPausedReasonError> =
        Optional.ofNullable(vaultArchived)

    /**
     * An unrecognized error auto-paused the deployment. A fallback variant; matches a run whose
     * `error.type` is `unknown_error`.
     */
    fun unknown(): Optional<BetaManagedAgentsUnknownDeploymentPausedReasonError> =
        Optional.ofNullable(unknown)

    /**
     * The deployment configures resources, but its environment is self-hosted and cannot mount
     * them.
     */
    fun selfHostedResourcesUnsupported():
        Optional<BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError> =
        Optional.ofNullable(selfHostedResourcesUnsupported)

    /**
     * An MCP server host used by the deployment's agent is blocked by the environment's network
     * policy.
     */
    fun mcpEgressBlocked(): Optional<BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError> =
        Optional.ofNullable(mcpEgressBlocked)

    fun isEnvironmentArchived(): Boolean = environmentArchived != null

    fun isAgentArchived(): Boolean = agentArchived != null

    fun isEnvironmentNotFound(): Boolean = environmentNotFound != null

    fun isVaultNotFound(): Boolean = vaultNotFound != null

    fun isFileNotFound(): Boolean = fileNotFound != null

    fun isSessionResourceNotFound(): Boolean = sessionResourceNotFound != null

    fun isWorkspaceArchived(): Boolean = workspaceArchived != null

    fun isOrganizationDisabled(): Boolean = organizationDisabled != null

    fun isMemoryStoreArchived(): Boolean = memoryStoreArchived != null

    fun isSkillNotFound(): Boolean = skillNotFound != null

    fun isVaultArchived(): Boolean = vaultArchived != null

    fun isUnknown(): Boolean = unknown != null

    fun isSelfHostedResourcesUnsupported(): Boolean = selfHostedResourcesUnsupported != null

    fun isMcpEgressBlocked(): Boolean = mcpEgressBlocked != null

    /** The deployment's environment was archived. */
    fun asEnvironmentArchived(): BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError =
        environmentArchived.getOrThrow("environmentArchived")

    /** The deployment's agent was archived. */
    fun asAgentArchived(): BetaManagedAgentsAgentArchivedDeploymentPausedReasonError =
        agentArchived.getOrThrow("agentArchived")

    /** The deployment's environment no longer exists. */
    fun asEnvironmentNotFound(): BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError =
        environmentNotFound.getOrThrow("environmentNotFound")

    /** A vault referenced by the deployment no longer exists. */
    fun asVaultNotFound(): BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError =
        vaultNotFound.getOrThrow("vaultNotFound")

    /** A file resource referenced by the deployment no longer exists. */
    fun asFileNotFound(): BetaManagedAgentsFileNotFoundDeploymentPausedReasonError =
        fileNotFound.getOrThrow("fileNotFound")

    /** A referenced resource no longer exists and its kind was not reported. */
    fun asSessionResourceNotFound():
        BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError =
        sessionResourceNotFound.getOrThrow("sessionResourceNotFound")

    /** The deployment's workspace was archived. */
    fun asWorkspaceArchived(): BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError =
        workspaceArchived.getOrThrow("workspaceArchived")

    /** The deployment's organization is disabled. */
    fun asOrganizationDisabled(): BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError =
        organizationDisabled.getOrThrow("organizationDisabled")

    /** A memory store referenced by the deployment is archived. */
    fun asMemoryStoreArchived(): BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
        memoryStoreArchived.getOrThrow("memoryStoreArchived")

    /** A skill referenced by the deployment's agent no longer exists. */
    fun asSkillNotFound(): BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError =
        skillNotFound.getOrThrow("skillNotFound")

    /** A vault referenced by the deployment is archived. */
    fun asVaultArchived(): BetaManagedAgentsVaultArchivedDeploymentPausedReasonError =
        vaultArchived.getOrThrow("vaultArchived")

    /**
     * An unrecognized error auto-paused the deployment. A fallback variant; matches a run whose
     * `error.type` is `unknown_error`.
     */
    fun asUnknown(): BetaManagedAgentsUnknownDeploymentPausedReasonError =
        unknown.getOrThrow("unknown")

    /**
     * The deployment configures resources, but its environment is self-hosted and cannot mount
     * them.
     */
    fun asSelfHostedResourcesUnsupported():
        BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError =
        selfHostedResourcesUnsupported.getOrThrow("selfHostedResourcesUnsupported")

    /**
     * An MCP server host used by the deployment's agent is blocked by the environment's network
     * policy.
     */
    fun asMcpEgressBlocked(): BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError =
        mcpEgressBlocked.getOrThrow("mcpEgressBlocked")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaManagedAgentsDeploymentPausedReasonError.accept(new BetaManagedAgentsDeploymentPausedReasonError.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitEnvironmentArchived(BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError environmentArchived) {
     *         return Optional.of(environmentArchived.toString());
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            environmentArchived != null -> visitor.visitEnvironmentArchived(environmentArchived)
            agentArchived != null -> visitor.visitAgentArchived(agentArchived)
            environmentNotFound != null -> visitor.visitEnvironmentNotFound(environmentNotFound)
            vaultNotFound != null -> visitor.visitVaultNotFound(vaultNotFound)
            fileNotFound != null -> visitor.visitFileNotFound(fileNotFound)
            sessionResourceNotFound != null ->
                visitor.visitSessionResourceNotFound(sessionResourceNotFound)
            workspaceArchived != null -> visitor.visitWorkspaceArchived(workspaceArchived)
            organizationDisabled != null -> visitor.visitOrganizationDisabled(organizationDisabled)
            memoryStoreArchived != null -> visitor.visitMemoryStoreArchived(memoryStoreArchived)
            skillNotFound != null -> visitor.visitSkillNotFound(skillNotFound)
            vaultArchived != null -> visitor.visitVaultArchived(vaultArchived)
            unknown != null -> visitor.visitUnknown(unknown)
            selfHostedResourcesUnsupported != null ->
                visitor.visitSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)
            mcpEgressBlocked != null -> visitor.visitMcpEgressBlocked(mcpEgressBlocked)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaManagedAgentsDeploymentPausedReasonError = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitEnvironmentArchived(
                    environmentArchived:
                        BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
                ) {
                    environmentArchived.validate()
                }

                override fun visitAgentArchived(
                    agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
                ) {
                    agentArchived.validate()
                }

                override fun visitEnvironmentNotFound(
                    environmentNotFound:
                        BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
                ) {
                    environmentNotFound.validate()
                }

                override fun visitVaultNotFound(
                    vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
                ) {
                    vaultNotFound.validate()
                }

                override fun visitFileNotFound(
                    fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError
                ) {
                    fileNotFound.validate()
                }

                override fun visitSessionResourceNotFound(
                    sessionResourceNotFound:
                        BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
                ) {
                    sessionResourceNotFound.validate()
                }

                override fun visitWorkspaceArchived(
                    workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
                ) {
                    workspaceArchived.validate()
                }

                override fun visitOrganizationDisabled(
                    organizationDisabled:
                        BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
                ) {
                    organizationDisabled.validate()
                }

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived:
                        BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
                ) {
                    memoryStoreArchived.validate()
                }

                override fun visitSkillNotFound(
                    skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
                ) {
                    skillNotFound.validate()
                }

                override fun visitVaultArchived(
                    vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
                ) {
                    vaultArchived.validate()
                }

                override fun visitUnknown(
                    unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError
                ) {
                    unknown.validate()
                }

                override fun visitSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported:
                        BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                ) {
                    selfHostedResourcesUnsupported.validate()
                }

                override fun visitMcpEgressBlocked(
                    mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
                ) {
                    mcpEgressBlocked.validate()
                }
            }
        )
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
        accept(
            object : Visitor<Int> {
                override fun visitEnvironmentArchived(
                    environmentArchived:
                        BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
                ) = environmentArchived.validity()

                override fun visitAgentArchived(
                    agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
                ) = agentArchived.validity()

                override fun visitEnvironmentNotFound(
                    environmentNotFound:
                        BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
                ) = environmentNotFound.validity()

                override fun visitVaultNotFound(
                    vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
                ) = vaultNotFound.validity()

                override fun visitFileNotFound(
                    fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError
                ) = fileNotFound.validity()

                override fun visitSessionResourceNotFound(
                    sessionResourceNotFound:
                        BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
                ) = sessionResourceNotFound.validity()

                override fun visitWorkspaceArchived(
                    workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
                ) = workspaceArchived.validity()

                override fun visitOrganizationDisabled(
                    organizationDisabled:
                        BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
                ) = organizationDisabled.validity()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived:
                        BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
                ) = memoryStoreArchived.validity()

                override fun visitSkillNotFound(
                    skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
                ) = skillNotFound.validity()

                override fun visitVaultArchived(
                    vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
                ) = vaultArchived.validity()

                override fun visitUnknown(
                    unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError
                ) = unknown.validity()

                override fun visitSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported:
                        BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                ) = selfHostedResourcesUnsupported.validity()

                override fun visitMcpEgressBlocked(
                    mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
                ) = mcpEgressBlocked.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeploymentPausedReasonError &&
            environmentArchived == other.environmentArchived &&
            agentArchived == other.agentArchived &&
            environmentNotFound == other.environmentNotFound &&
            vaultNotFound == other.vaultNotFound &&
            fileNotFound == other.fileNotFound &&
            sessionResourceNotFound == other.sessionResourceNotFound &&
            workspaceArchived == other.workspaceArchived &&
            organizationDisabled == other.organizationDisabled &&
            memoryStoreArchived == other.memoryStoreArchived &&
            skillNotFound == other.skillNotFound &&
            vaultArchived == other.vaultArchived &&
            unknown == other.unknown &&
            selfHostedResourcesUnsupported == other.selfHostedResourcesUnsupported &&
            mcpEgressBlocked == other.mcpEgressBlocked
    }

    override fun hashCode(): Int =
        Objects.hash(
            environmentArchived,
            agentArchived,
            environmentNotFound,
            vaultNotFound,
            fileNotFound,
            sessionResourceNotFound,
            workspaceArchived,
            organizationDisabled,
            memoryStoreArchived,
            skillNotFound,
            vaultArchived,
            unknown,
            selfHostedResourcesUnsupported,
            mcpEgressBlocked,
        )

    override fun toString(): String =
        when {
            environmentArchived != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{environmentArchived=$environmentArchived}"
            agentArchived != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{agentArchived=$agentArchived}"
            environmentNotFound != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{environmentNotFound=$environmentNotFound}"
            vaultNotFound != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{vaultNotFound=$vaultNotFound}"
            fileNotFound != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{fileNotFound=$fileNotFound}"
            sessionResourceNotFound != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{sessionResourceNotFound=$sessionResourceNotFound}"
            workspaceArchived != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{workspaceArchived=$workspaceArchived}"
            organizationDisabled != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{organizationDisabled=$organizationDisabled}"
            memoryStoreArchived != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{memoryStoreArchived=$memoryStoreArchived}"
            skillNotFound != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{skillNotFound=$skillNotFound}"
            vaultArchived != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{vaultArchived=$vaultArchived}"
            unknown != null -> "BetaManagedAgentsDeploymentPausedReasonError{unknown=$unknown}"
            selfHostedResourcesUnsupported != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{selfHostedResourcesUnsupported=$selfHostedResourcesUnsupported}"
            mcpEgressBlocked != null ->
                "BetaManagedAgentsDeploymentPausedReasonError{mcpEgressBlocked=$mcpEgressBlocked}"
            _json != null -> "BetaManagedAgentsDeploymentPausedReasonError{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaManagedAgentsDeploymentPausedReasonError")
        }

    companion object {

        /** The deployment's environment was archived. */
        @JvmStatic
        fun ofEnvironmentArchived(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(environmentArchived = environmentArchived)

        /** The deployment's agent was archived. */
        @JvmStatic
        fun ofAgentArchived(
            agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(agentArchived = agentArchived)

        /** The deployment's environment no longer exists. */
        @JvmStatic
        fun ofEnvironmentNotFound(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(environmentNotFound = environmentNotFound)

        /** A vault referenced by the deployment no longer exists. */
        @JvmStatic
        fun ofVaultNotFound(
            vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(vaultNotFound = vaultNotFound)

        /** A file resource referenced by the deployment no longer exists. */
        @JvmStatic
        fun ofFileNotFound(fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError) =
            BetaManagedAgentsDeploymentPausedReasonError(fileNotFound = fileNotFound)

        /** A referenced resource no longer exists and its kind was not reported. */
        @JvmStatic
        fun ofSessionResourceNotFound(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ) =
            BetaManagedAgentsDeploymentPausedReasonError(
                sessionResourceNotFound = sessionResourceNotFound
            )

        /** The deployment's workspace was archived. */
        @JvmStatic
        fun ofWorkspaceArchived(
            workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(workspaceArchived = workspaceArchived)

        /** The deployment's organization is disabled. */
        @JvmStatic
        fun ofOrganizationDisabled(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ) =
            BetaManagedAgentsDeploymentPausedReasonError(
                organizationDisabled = organizationDisabled
            )

        /** A memory store referenced by the deployment is archived. */
        @JvmStatic
        fun ofMemoryStoreArchived(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(memoryStoreArchived = memoryStoreArchived)

        /** A skill referenced by the deployment's agent no longer exists. */
        @JvmStatic
        fun ofSkillNotFound(
            skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(skillNotFound = skillNotFound)

        /** A vault referenced by the deployment is archived. */
        @JvmStatic
        fun ofVaultArchived(
            vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(vaultArchived = vaultArchived)

        /**
         * An unrecognized error auto-paused the deployment. A fallback variant; matches a run whose
         * `error.type` is `unknown_error`.
         */
        @JvmStatic
        fun ofUnknown(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError) =
            BetaManagedAgentsDeploymentPausedReasonError(unknown = unknown)

        /**
         * The deployment configures resources, but its environment is self-hosted and cannot mount
         * them.
         */
        @JvmStatic
        fun ofSelfHostedResourcesUnsupported(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ) =
            BetaManagedAgentsDeploymentPausedReasonError(
                selfHostedResourcesUnsupported = selfHostedResourcesUnsupported
            )

        /**
         * An MCP server host used by the deployment's agent is blocked by the environment's network
         * policy.
         */
        @JvmStatic
        fun ofMcpEgressBlocked(
            mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
        ) = BetaManagedAgentsDeploymentPausedReasonError(mcpEgressBlocked = mcpEgressBlocked)
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsDeploymentPausedReasonError] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The deployment's environment was archived. */
        fun visitEnvironmentArchived(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ): T

        /** The deployment's agent was archived. */
        fun visitAgentArchived(
            agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
        ): T

        /** The deployment's environment no longer exists. */
        fun visitEnvironmentNotFound(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ): T

        /** A vault referenced by the deployment no longer exists. */
        fun visitVaultNotFound(
            vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
        ): T

        /** A file resource referenced by the deployment no longer exists. */
        fun visitFileNotFound(
            fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError
        ): T

        /** A referenced resource no longer exists and its kind was not reported. */
        fun visitSessionResourceNotFound(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ): T

        /** The deployment's workspace was archived. */
        fun visitWorkspaceArchived(
            workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
        ): T

        /** The deployment's organization is disabled. */
        fun visitOrganizationDisabled(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ): T

        /** A memory store referenced by the deployment is archived. */
        fun visitMemoryStoreArchived(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ): T

        /** A skill referenced by the deployment's agent no longer exists. */
        fun visitSkillNotFound(
            skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
        ): T

        /** A vault referenced by the deployment is archived. */
        fun visitVaultArchived(
            vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
        ): T

        /**
         * An unrecognized error auto-paused the deployment. A fallback variant; matches a run whose
         * `error.type` is `unknown_error`.
         */
        fun visitUnknown(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError): T

        /**
         * The deployment configures resources, but its environment is self-hosted and cannot mount
         * them.
         */
        fun visitSelfHostedResourcesUnsupported(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ): T

        /**
         * An MCP server host used by the deployment's agent is blocked by the environment's network
         * policy.
         */
        fun visitMcpEgressBlocked(
            mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
        ): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsDeploymentPausedReasonError] to a value of
         * type [T].
         *
         * An instance of [BetaManagedAgentsDeploymentPausedReasonError] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsDeploymentPausedReasonError: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsDeploymentPausedReasonError>(
            BetaManagedAgentsDeploymentPausedReasonError::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsDeploymentPausedReasonError {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "environment_archived_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                environmentArchived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "agent_archived_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                agentArchived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "environment_not_found_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                environmentNotFound = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "vault_not_found_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                vaultNotFound = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "file_not_found_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsFileNotFoundDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                fileNotFound = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "session_resource_not_found_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                sessionResourceNotFound = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "workspace_archived_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                workspaceArchived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "organization_disabled_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                organizationDisabled = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "memory_store_archived_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                memoryStoreArchived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "skill_not_found_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                skillNotFound = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "vault_archived_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                vaultArchived = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "unknown_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsUnknownDeploymentPausedReasonError>(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(unknown = it, _json = json)
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "self_hosted_resources_unsupported_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                selfHostedResourcesUnsupported = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
                "mcp_egress_blocked_error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<
                                BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
                            >(),
                        )
                        ?.let {
                            BetaManagedAgentsDeploymentPausedReasonError(
                                mcpEgressBlocked = it,
                                _json = json,
                            )
                        } ?: BetaManagedAgentsDeploymentPausedReasonError(_json = json)
                }
            }

            return BetaManagedAgentsDeploymentPausedReasonError(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsDeploymentPausedReasonError>(
            BetaManagedAgentsDeploymentPausedReasonError::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsDeploymentPausedReasonError,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.environmentArchived != null ->
                    generator.writeObject(value.environmentArchived)
                value.agentArchived != null -> generator.writeObject(value.agentArchived)
                value.environmentNotFound != null ->
                    generator.writeObject(value.environmentNotFound)
                value.vaultNotFound != null -> generator.writeObject(value.vaultNotFound)
                value.fileNotFound != null -> generator.writeObject(value.fileNotFound)
                value.sessionResourceNotFound != null ->
                    generator.writeObject(value.sessionResourceNotFound)
                value.workspaceArchived != null -> generator.writeObject(value.workspaceArchived)
                value.organizationDisabled != null ->
                    generator.writeObject(value.organizationDisabled)
                value.memoryStoreArchived != null ->
                    generator.writeObject(value.memoryStoreArchived)
                value.skillNotFound != null -> generator.writeObject(value.skillNotFound)
                value.vaultArchived != null -> generator.writeObject(value.vaultArchived)
                value.unknown != null -> generator.writeObject(value.unknown)
                value.selfHostedResourcesUnsupported != null ->
                    generator.writeObject(value.selfHostedResourcesUnsupported)
                value.mcpEgressBlocked != null -> generator.writeObject(value.mcpEgressBlocked)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaManagedAgentsDeploymentPausedReasonError"
                    )
            }
        }
    }
}
