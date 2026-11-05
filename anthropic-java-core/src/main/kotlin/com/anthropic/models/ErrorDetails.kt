package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaUnsupportedCharsetErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentArchivedErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentCreateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentNotFoundErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentUpdateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidConfigTypeChangeErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidMetadataErrorDetails
import com.anthropic.models.beta.environments.work.BetaHeartbeatPreconditionFailedErrorDetails
import com.anthropic.models.beta.environments.work.BetaWorkLeaseState
import com.anthropic.models.beta.organization.BetaBillingFrozenErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsConflictErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsRefusedErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaPluginNameTakenErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaRegistrationPendingErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaScanFailedErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaScanPendingErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaSkillNameTakenErrorDetails
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails
import com.anthropic.models.files.FileExpiredErrorDetails
import com.anthropic.models.files.FileNotDownloadableErrorDetails
import com.anthropic.models.files.StorageQuotaExceededErrorDetails
import com.anthropic.models.messages.AvailableToolsLimitExceededErrorDetails
import com.anthropic.models.messages.CompactionBlockAmbiguousErrorDetails
import com.anthropic.models.messages.CompactionBlockMisplacedErrorDetails
import com.anthropic.models.messages.CompactionContentMismatchErrorDetails
import com.anthropic.models.messages.CompactionIncompleteTurnErrorDetails
import com.anthropic.models.messages.CompactionNothingToSummarizeErrorDetails
import com.anthropic.models.messages.CompactionSignatureInvalidErrorDetails
import com.anthropic.models.messages.CompactionTooManyToolReferencesErrorDetails
import com.anthropic.models.messages.CompactionToolChangesMismatchErrorDetails
import com.anthropic.models.messages.CompactionUnavailableErrorDetails
import com.anthropic.models.messages.ThreadAlreadyContinuedErrorDetails
import com.anthropic.models.messages.ThreadContinueInFlightErrorDetails
import com.anthropic.models.messages.ThreadFingerprintMismatchErrorDetails
import com.anthropic.models.messages.ThreadNotFoundErrorDetails
import com.anthropic.models.messages.ThreadUnsupportedRequestErrorDetails
import com.anthropic.models.messages.ToolNameConflictErrorDetails
import com.anthropic.models.messages.ToolReferenceUnresolvedErrorDetails
import com.anthropic.models.organization.ConcurrentMembershipChangeErrorDetails
import com.anthropic.models.organization.OrgMemberLimitExceededErrorDetails
import com.anthropic.models.organization.apikeys.OrgApiKeyLimitExceededErrorDetails
import com.anthropic.models.organization.apikeys.WorkspaceApiKeyLimitExceededErrorDetails
import com.anthropic.models.organization.externalkeys.ExternalKeyChangedErrorDetails
import com.anthropic.models.organization.externalkeys.ExternalKeyInWorkspacePolicyErrorDetails
import com.anthropic.models.organization.externalkeys.ExternalKeyValidationFailedErrorDetails
import com.anthropic.models.organization.externalkeys.ExternalKeyValidationTimedOutErrorDetails
import com.anthropic.models.organization.invites.AlreadyMemberErrorDetails
import com.anthropic.models.organization.invites.CannotInviteEmailsWithUnverifiedDomainsErrorDetails
import com.anthropic.models.organization.invites.EmailDomainNotAllowedErrorDetails
import com.anthropic.models.organization.invites.InviteAlreadyExistsErrorDetails
import com.anthropic.models.organization.rbacgroups.RbacGroupGrantsOwnerAccessErrorDetails
import com.anthropic.models.organization.rbacgroups.RbacGroupOwnerAccessUnconfirmedErrorDetails
import com.anthropic.models.organization.users.CannotRemoveLastMemberErrorDetails
import com.anthropic.models.organization.workspaces.KeyProfileMismatchErrorDetails
import com.anthropic.models.organization.workspaces.WorkspaceMemberLimitExceededErrorDetails
import com.anthropic.models.organization.workspaces.WorkspacePolicyField
import com.anthropic.models.organization.workspaces.WorkspacePolicyViolationErrorDetails
import com.anthropic.models.skills.SkillReadBlockedUndecryptableErrorDetails
import com.anthropic.models.userprofiles.UserProfileNotFoundErrorDetails
import com.fasterxml.jackson.annotation.JsonCreator
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

@JsonDeserialize(using = ErrorDetails.Deserializer::class)
@JsonSerialize(using = ErrorDetails.Serializer::class)
class ErrorDetails
private constructor(
    private val availableToolsLimitExceeded: AvailableToolsLimitExceededErrorDetails? = null,
    private val claudeCodeKeyCreatorNotMember: ClaudeCodeKeyCreatorNotMemberErrorDetails? = null,
    private val claudeCodeVersionTooOld: ClaudeCodeVersionTooOldErrorDetails? = null,
    private val cmekKeyDisabled: CmekKeyDisabledErrorDetails? = null,
    private val cmekKeyNetworkBlocked: CmekKeyNetworkBlockedErrorDetails? = null,
    private val compactionBlockAmbiguous: CompactionBlockAmbiguousErrorDetails? = null,
    private val compactionBlockMisplaced: CompactionBlockMisplacedErrorDetails? = null,
    private val compactionContentMismatch: CompactionContentMismatchErrorDetails? = null,
    private val compactionIncompleteTurn: CompactionIncompleteTurnErrorDetails? = null,
    private val compactionNothingToSummarize: CompactionNothingToSummarizeErrorDetails? = null,
    private val compactionSignatureInvalid: CompactionSignatureInvalidErrorDetails? = null,
    private val compactionTooManyToolReferences: CompactionTooManyToolReferencesErrorDetails? =
        null,
    private val compactionToolChangesMismatch: CompactionToolChangesMismatchErrorDetails? = null,
    private val organizationOnHold: OrganizationOnHoldErrorDetails? = null,
    private val requestBodyEncodingInvalid: RequestBodyEncodingInvalidErrorDetails? = null,
    private val requestBodyEncodingUnsupported: RequestBodyEncodingUnsupportedErrorDetails? = null,
    private val threadAlreadyContinued: ThreadAlreadyContinuedErrorDetails? = null,
    private val threadContinueInFlight: ThreadContinueInFlightErrorDetails? = null,
    private val threadFingerprintMismatch: ThreadFingerprintMismatchErrorDetails? = null,
    private val threadUnsupportedRequest: ThreadUnsupportedRequestErrorDetails? = null,
    private val toolNameConflict: ToolNameConflictErrorDetails? = null,
    private val toolReferenceUnresolved: ToolReferenceUnresolvedErrorDetails? = null,
    private val threadNotFound: ThreadNotFoundErrorDetails? = null,
    private val skillReadBlockedUndecryptable: SkillReadBlockedUndecryptableErrorDetails? = null,
    private val requestBodyTooLargeAfterDecoding: RequestBodyTooLargeAfterDecodingErrorDetails? =
        null,
    private val enforcedSpendLimitReached: EnforcedSpendLimitReachedErrorDetails? = null,
    private val rampLimitExceeded: RampLimitExceededErrorDetails? = null,
    private val cmekKeyUnreachable: CmekKeyUnreachableErrorDetails? = null,
    private val compactionUnavailable: CompactionUnavailableErrorDetails? = null,
    private val storageQuotaExceeded: StorageQuotaExceededErrorDetails? = null,
    private val fileNotDownloadable: FileNotDownloadableErrorDetails? = null,
    private val fileExpired: FileExpiredErrorDetails? = null,
    private val cmekContextUnavailable: CmekContextUnavailableErrorDetails? = null,
    private val orgApiKeyLimitExceeded: OrgApiKeyLimitExceededErrorDetails? = null,
    private val workspaceApiKeyLimitExceeded: WorkspaceApiKeyLimitExceededErrorDetails? = null,
    private val userProfileNotFound: UserProfileNotFoundErrorDetails? = null,
    private val externalKeyInWorkspacePolicy: ExternalKeyInWorkspacePolicyErrorDetails? = null,
    private val alreadyMember: AlreadyMemberErrorDetails? = null,
    private val cannotInviteEmailsWithUnverifiedDomains:
        CannotInviteEmailsWithUnverifiedDomainsErrorDetails? =
        null,
    private val emailDomainNotAllowed: EmailDomainNotAllowedErrorDetails? = null,
    private val inviteAlreadyExists: InviteAlreadyExistsErrorDetails? = null,
    private val orgMemberLimitExceeded: OrgMemberLimitExceededErrorDetails? = null,
    private val rbacGroupGrantsOwnerAccess: RbacGroupGrantsOwnerAccessErrorDetails? = null,
    private val rbacGroupOwnerAccessUnconfirmed: RbacGroupOwnerAccessUnconfirmedErrorDetails? =
        null,
    private val cannotRemoveLastMember: CannotRemoveLastMemberErrorDetails? = null,
    private val concurrentMembershipChange: ConcurrentMembershipChangeErrorDetails? = null,
    private val externalKeyValidationFailed: ExternalKeyValidationFailedErrorDetails? = null,
    private val externalKeyValidationTimedOut: ExternalKeyValidationTimedOutErrorDetails? = null,
    private val keyProfileMismatch: KeyProfileMismatchErrorDetails? = null,
    private val workspacePolicyViolation: WorkspacePolicyViolationErrorDetails? = null,
    private val externalKeyChanged: ExternalKeyChangedErrorDetails? = null,
    private val workspaceMemberLimitExceeded: WorkspaceMemberLimitExceededErrorDetails? = null,
    private val organizationTypeNotSupported: OrganizationTypeNotSupportedErrorDetails? = null,
    private val environmentCreateDisabled: BetaEnvironmentCreateDisabledErrorDetails? = null,
    private val environmentArchived: BetaEnvironmentArchivedErrorDetails? = null,
    private val invalidConfigTypeChange: BetaInvalidConfigTypeChangeErrorDetails? = null,
    private val environmentUpdateDisabled: BetaEnvironmentUpdateDisabledErrorDetails? = null,
    private val heartbeatPreconditionFailed: BetaHeartbeatPreconditionFailedErrorDetails? = null,
    private val invalidMetadata: BetaInvalidMetadataErrorDetails? = null,
    private val billingFrozen: BetaBillingFrozenErrorDetails? = null,
    private val spendLimitsAdminApiWritesNotEnabled:
        BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails? =
        null,
    private val unsupportedCharset: BetaUnsupportedCharsetErrorDetails? = null,
    private val pluginNameTaken: BetaPluginNameTakenErrorDetails? = null,
    private val skillNameTaken: BetaSkillNameTakenErrorDetails? = null,
    private val registrationPending: BetaRegistrationPendingErrorDetails? = null,
    private val scanFailed: BetaScanFailedErrorDetails? = null,
    private val scanPending: BetaScanPendingErrorDetails? = null,
    private val slackChannelSetUpSettingsRefused:
        BetaSlackChannelSetUpSettingsRefusedErrorDetails? =
        null,
    private val apiKeyScopeMissing: BetaApiKeyScopeMissingErrorDetails? = null,
    private val claudeTagWritesNotEnabled: BetaClaudeTagWritesNotEnabledErrorDetails? = null,
    private val complianceConfigurationRestricted:
        BetaComplianceConfigurationRestrictedErrorDetails? =
        null,
    private val organizationLevelScopedApiKeyRequired:
        BetaOrganizationLevelScopedApiKeyRequiredErrorDetails? =
        null,
    private val environmentNotFound: BetaEnvironmentNotFoundErrorDetails? = null,
    private val slackChannelSetUpEnvironmentNotFound:
        BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails? =
        null,
    private val slackChannelSetUpSettingsConflict:
        BetaSlackChannelSetUpSettingsConflictErrorDetails? =
        null,
    private val _json: JsonValue? = null,
) {

    fun errorCode(): ErrorCode =
        when {
            availableToolsLimitExceeded != null -> ErrorCode.AVAILABLE_TOOLS_LIMIT_EXCEEDED
            claudeCodeKeyCreatorNotMember != null -> ErrorCode.CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER
            claudeCodeVersionTooOld != null -> ErrorCode.CLAUDE_CODE_VERSION_TOO_OLD
            cmekKeyDisabled != null -> ErrorCode.CMEK_KEY_DISABLED
            cmekKeyNetworkBlocked != null -> ErrorCode.CMEK_KEY_NETWORK_BLOCKED
            compactionBlockAmbiguous != null -> ErrorCode.COMPACTION_BLOCK_AMBIGUOUS
            compactionBlockMisplaced != null -> ErrorCode.COMPACTION_BLOCK_MISPLACED
            compactionContentMismatch != null -> ErrorCode.COMPACTION_CONTENT_MISMATCH
            compactionIncompleteTurn != null -> ErrorCode.COMPACTION_INCOMPLETE_TURN
            compactionNothingToSummarize != null -> ErrorCode.COMPACTION_NOTHING_TO_SUMMARIZE
            compactionSignatureInvalid != null -> ErrorCode.COMPACTION_SIGNATURE_INVALID
            compactionTooManyToolReferences != null -> ErrorCode.COMPACTION_TOO_MANY_TOOL_REFERENCES
            compactionToolChangesMismatch != null -> ErrorCode.COMPACTION_TOOL_CHANGES_MISMATCH
            organizationOnHold != null -> ErrorCode.ORGANIZATION_ON_HOLD
            requestBodyEncodingInvalid != null -> ErrorCode.REQUEST_BODY_ENCODING_INVALID
            requestBodyEncodingUnsupported != null -> ErrorCode.REQUEST_BODY_ENCODING_UNSUPPORTED
            threadAlreadyContinued != null -> ErrorCode.THREAD_ALREADY_CONTINUED
            threadContinueInFlight != null -> ErrorCode.THREAD_CONTINUE_IN_FLIGHT
            threadFingerprintMismatch != null -> ErrorCode.THREAD_FINGERPRINT_MISMATCH
            threadUnsupportedRequest != null -> ErrorCode.THREAD_UNSUPPORTED_REQUEST
            toolNameConflict != null -> ErrorCode.TOOL_NAME_CONFLICT
            toolReferenceUnresolved != null -> ErrorCode.TOOL_REFERENCE_UNRESOLVED
            threadNotFound != null -> ErrorCode.THREAD_NOT_FOUND
            skillReadBlockedUndecryptable != null -> ErrorCode.SKILL_READ_BLOCKED_UNDECRYPTABLE
            requestBodyTooLargeAfterDecoding != null ->
                ErrorCode.REQUEST_BODY_TOO_LARGE_AFTER_DECODING
            enforcedSpendLimitReached != null -> ErrorCode.ENFORCED_SPEND_LIMIT_REACHED
            rampLimitExceeded != null -> ErrorCode.RAMP_LIMIT_EXCEEDED
            cmekKeyUnreachable != null -> ErrorCode.CMEK_KEY_UNREACHABLE
            compactionUnavailable != null -> ErrorCode.COMPACTION_UNAVAILABLE
            storageQuotaExceeded != null -> ErrorCode.STORAGE_QUOTA_EXCEEDED
            fileNotDownloadable != null -> ErrorCode.FILE_NOT_DOWNLOADABLE
            fileExpired != null -> ErrorCode.FILE_EXPIRED
            cmekContextUnavailable != null -> ErrorCode.CMEK_CONTEXT_UNAVAILABLE
            orgApiKeyLimitExceeded != null -> ErrorCode.ORG_API_KEY_LIMIT_EXCEEDED
            workspaceApiKeyLimitExceeded != null -> ErrorCode.WORKSPACE_API_KEY_LIMIT_EXCEEDED
            userProfileNotFound != null -> ErrorCode.USER_PROFILE_NOT_FOUND
            externalKeyInWorkspacePolicy != null -> ErrorCode.EXTERNAL_KEY_IN_WORKSPACE_POLICY
            alreadyMember != null -> ErrorCode.ALREADY_MEMBER
            cannotInviteEmailsWithUnverifiedDomains != null ->
                ErrorCode.CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS
            emailDomainNotAllowed != null -> ErrorCode.EMAIL_DOMAIN_NOT_ALLOWED
            inviteAlreadyExists != null -> ErrorCode.INVITE_ALREADY_EXISTS
            orgMemberLimitExceeded != null -> ErrorCode.ORG_MEMBER_LIMIT_EXCEEDED
            rbacGroupGrantsOwnerAccess != null -> ErrorCode.RBAC_GROUP_GRANTS_OWNER_ACCESS
            rbacGroupOwnerAccessUnconfirmed != null -> ErrorCode.RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED
            cannotRemoveLastMember != null -> ErrorCode.CANNOT_REMOVE_LAST_MEMBER
            concurrentMembershipChange != null -> ErrorCode.CONCURRENT_MEMBERSHIP_CHANGE
            externalKeyValidationFailed != null -> ErrorCode.EXTERNAL_KEY_VALIDATION_FAILED
            externalKeyValidationTimedOut != null -> ErrorCode.EXTERNAL_KEY_VALIDATION_TIMED_OUT
            keyProfileMismatch != null -> ErrorCode.KEY_PROFILE_MISMATCH
            workspacePolicyViolation != null -> ErrorCode.WORKSPACE_POLICY_VIOLATION
            externalKeyChanged != null -> ErrorCode.EXTERNAL_KEY_CHANGED
            workspaceMemberLimitExceeded != null -> ErrorCode.WORKSPACE_MEMBER_LIMIT_EXCEEDED
            organizationTypeNotSupported != null -> ErrorCode.ORGANIZATION_TYPE_NOT_SUPPORTED
            environmentCreateDisabled != null -> ErrorCode.ENVIRONMENT_CREATE_DISABLED
            environmentArchived != null -> ErrorCode.ENVIRONMENT_ARCHIVED
            invalidConfigTypeChange != null -> ErrorCode.INVALID_CONFIG_TYPE_CHANGE
            environmentUpdateDisabled != null -> ErrorCode.ENVIRONMENT_UPDATE_DISABLED
            heartbeatPreconditionFailed != null -> ErrorCode.HEARTBEAT_PRECONDITION_FAILED
            invalidMetadata != null -> ErrorCode.INVALID_METADATA
            billingFrozen != null -> ErrorCode.BILLING_FROZEN
            spendLimitsAdminApiWritesNotEnabled != null ->
                ErrorCode.SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED
            unsupportedCharset != null -> ErrorCode.UNSUPPORTED_CHARSET
            pluginNameTaken != null -> ErrorCode.PLUGIN_NAME_TAKEN
            skillNameTaken != null -> ErrorCode.SKILL_NAME_TAKEN
            registrationPending != null -> ErrorCode.REGISTRATION_PENDING
            scanFailed != null -> ErrorCode.SCAN_FAILED
            scanPending != null -> ErrorCode.SCAN_PENDING
            slackChannelSetUpSettingsRefused != null ->
                ErrorCode.SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED
            apiKeyScopeMissing != null -> ErrorCode.API_KEY_SCOPE_MISSING
            claudeTagWritesNotEnabled != null -> ErrorCode.CLAUDE_TAG_WRITES_NOT_ENABLED
            complianceConfigurationRestricted != null ->
                ErrorCode.COMPLIANCE_CONFIGURATION_RESTRICTED
            organizationLevelScopedApiKeyRequired != null ->
                ErrorCode.ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED
            environmentNotFound != null -> ErrorCode.ENVIRONMENT_NOT_FOUND
            slackChannelSetUpEnvironmentNotFound != null ->
                ErrorCode.SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND
            slackChannelSetUpSettingsConflict != null ->
                ErrorCode.SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT
            else ->
                ErrorCode.of(_json?.asObject()?.getOrNull()?.get("error_code") ?: JsonMissing.of())
        }

    fun pluginId(): Optional<String> =
        when {
            availableToolsLimitExceeded != null -> Optional.empty()
            claudeCodeKeyCreatorNotMember != null -> Optional.empty()
            claudeCodeVersionTooOld != null -> Optional.empty()
            cmekKeyDisabled != null -> Optional.empty()
            cmekKeyNetworkBlocked != null -> Optional.empty()
            compactionBlockAmbiguous != null -> Optional.empty()
            compactionBlockMisplaced != null -> Optional.empty()
            compactionContentMismatch != null -> Optional.empty()
            compactionIncompleteTurn != null -> Optional.empty()
            compactionNothingToSummarize != null -> Optional.empty()
            compactionSignatureInvalid != null -> Optional.empty()
            compactionTooManyToolReferences != null -> Optional.empty()
            compactionToolChangesMismatch != null -> Optional.empty()
            organizationOnHold != null -> Optional.empty()
            requestBodyEncodingInvalid != null -> Optional.empty()
            requestBodyEncodingUnsupported != null -> Optional.empty()
            threadAlreadyContinued != null -> Optional.empty()
            threadContinueInFlight != null -> Optional.empty()
            threadFingerprintMismatch != null -> Optional.empty()
            threadUnsupportedRequest != null -> Optional.empty()
            toolNameConflict != null -> Optional.empty()
            toolReferenceUnresolved != null -> Optional.empty()
            threadNotFound != null -> Optional.empty()
            skillReadBlockedUndecryptable != null -> Optional.empty()
            requestBodyTooLargeAfterDecoding != null -> Optional.empty()
            enforcedSpendLimitReached != null -> Optional.empty()
            rampLimitExceeded != null -> Optional.empty()
            cmekKeyUnreachable != null -> Optional.empty()
            compactionUnavailable != null -> Optional.empty()
            storageQuotaExceeded != null -> Optional.empty()
            fileNotDownloadable != null -> Optional.empty()
            fileExpired != null -> Optional.empty()
            cmekContextUnavailable != null -> Optional.empty()
            orgApiKeyLimitExceeded != null -> Optional.empty()
            workspaceApiKeyLimitExceeded != null -> Optional.empty()
            userProfileNotFound != null -> Optional.empty()
            externalKeyInWorkspacePolicy != null -> Optional.empty()
            alreadyMember != null -> Optional.empty()
            cannotInviteEmailsWithUnverifiedDomains != null -> Optional.empty()
            emailDomainNotAllowed != null -> Optional.empty()
            inviteAlreadyExists != null -> Optional.empty()
            orgMemberLimitExceeded != null -> Optional.empty()
            rbacGroupGrantsOwnerAccess != null -> Optional.empty()
            rbacGroupOwnerAccessUnconfirmed != null -> Optional.empty()
            cannotRemoveLastMember != null -> Optional.empty()
            concurrentMembershipChange != null -> Optional.empty()
            externalKeyValidationFailed != null -> Optional.empty()
            externalKeyValidationTimedOut != null -> Optional.empty()
            keyProfileMismatch != null -> Optional.empty()
            workspacePolicyViolation != null -> Optional.empty()
            externalKeyChanged != null -> Optional.empty()
            workspaceMemberLimitExceeded != null -> Optional.empty()
            organizationTypeNotSupported != null -> Optional.empty()
            environmentCreateDisabled != null -> Optional.empty()
            environmentArchived != null -> Optional.empty()
            invalidConfigTypeChange != null -> Optional.empty()
            environmentUpdateDisabled != null -> Optional.empty()
            heartbeatPreconditionFailed != null -> Optional.empty()
            invalidMetadata != null -> Optional.empty()
            billingFrozen != null -> Optional.empty()
            spendLimitsAdminApiWritesNotEnabled != null -> Optional.empty()
            unsupportedCharset != null -> Optional.empty()
            pluginNameTaken != null -> pluginNameTaken.pluginId()
            skillNameTaken != null -> Optional.empty()
            registrationPending != null -> Optional.of(registrationPending.pluginId())
            scanFailed != null -> Optional.empty()
            scanPending != null -> Optional.empty()
            slackChannelSetUpSettingsRefused != null -> Optional.empty()
            apiKeyScopeMissing != null -> Optional.empty()
            claudeTagWritesNotEnabled != null -> Optional.empty()
            complianceConfigurationRestricted != null -> Optional.empty()
            organizationLevelScopedApiKeyRequired != null -> Optional.empty()
            environmentNotFound != null -> Optional.empty()
            slackChannelSetUpEnvironmentNotFound != null -> Optional.empty()
            slackChannelSetUpSettingsConflict != null -> Optional.empty()
            else -> _json.getProperty<String>("plugin_id").asKnown()
        }

    fun pluginVersionId(): Optional<String> =
        when {
            availableToolsLimitExceeded != null -> Optional.empty()
            claudeCodeKeyCreatorNotMember != null -> Optional.empty()
            claudeCodeVersionTooOld != null -> Optional.empty()
            cmekKeyDisabled != null -> Optional.empty()
            cmekKeyNetworkBlocked != null -> Optional.empty()
            compactionBlockAmbiguous != null -> Optional.empty()
            compactionBlockMisplaced != null -> Optional.empty()
            compactionContentMismatch != null -> Optional.empty()
            compactionIncompleteTurn != null -> Optional.empty()
            compactionNothingToSummarize != null -> Optional.empty()
            compactionSignatureInvalid != null -> Optional.empty()
            compactionTooManyToolReferences != null -> Optional.empty()
            compactionToolChangesMismatch != null -> Optional.empty()
            organizationOnHold != null -> Optional.empty()
            requestBodyEncodingInvalid != null -> Optional.empty()
            requestBodyEncodingUnsupported != null -> Optional.empty()
            threadAlreadyContinued != null -> Optional.empty()
            threadContinueInFlight != null -> Optional.empty()
            threadFingerprintMismatch != null -> Optional.empty()
            threadUnsupportedRequest != null -> Optional.empty()
            toolNameConflict != null -> Optional.empty()
            toolReferenceUnresolved != null -> Optional.empty()
            threadNotFound != null -> Optional.empty()
            skillReadBlockedUndecryptable != null -> Optional.empty()
            requestBodyTooLargeAfterDecoding != null -> Optional.empty()
            enforcedSpendLimitReached != null -> Optional.empty()
            rampLimitExceeded != null -> Optional.empty()
            cmekKeyUnreachable != null -> Optional.empty()
            compactionUnavailable != null -> Optional.empty()
            storageQuotaExceeded != null -> Optional.empty()
            fileNotDownloadable != null -> Optional.empty()
            fileExpired != null -> Optional.empty()
            cmekContextUnavailable != null -> Optional.empty()
            orgApiKeyLimitExceeded != null -> Optional.empty()
            workspaceApiKeyLimitExceeded != null -> Optional.empty()
            userProfileNotFound != null -> Optional.empty()
            externalKeyInWorkspacePolicy != null -> Optional.empty()
            alreadyMember != null -> Optional.empty()
            cannotInviteEmailsWithUnverifiedDomains != null -> Optional.empty()
            emailDomainNotAllowed != null -> Optional.empty()
            inviteAlreadyExists != null -> Optional.empty()
            orgMemberLimitExceeded != null -> Optional.empty()
            rbacGroupGrantsOwnerAccess != null -> Optional.empty()
            rbacGroupOwnerAccessUnconfirmed != null -> Optional.empty()
            cannotRemoveLastMember != null -> Optional.empty()
            concurrentMembershipChange != null -> Optional.empty()
            externalKeyValidationFailed != null -> Optional.empty()
            externalKeyValidationTimedOut != null -> Optional.empty()
            keyProfileMismatch != null -> Optional.empty()
            workspacePolicyViolation != null -> Optional.empty()
            externalKeyChanged != null -> Optional.empty()
            workspaceMemberLimitExceeded != null -> Optional.empty()
            organizationTypeNotSupported != null -> Optional.empty()
            environmentCreateDisabled != null -> Optional.empty()
            environmentArchived != null -> Optional.empty()
            invalidConfigTypeChange != null -> Optional.empty()
            environmentUpdateDisabled != null -> Optional.empty()
            heartbeatPreconditionFailed != null -> Optional.empty()
            invalidMetadata != null -> Optional.empty()
            billingFrozen != null -> Optional.empty()
            spendLimitsAdminApiWritesNotEnabled != null -> Optional.empty()
            unsupportedCharset != null -> Optional.empty()
            pluginNameTaken != null -> Optional.empty()
            skillNameTaken != null -> Optional.empty()
            registrationPending != null -> Optional.of(registrationPending.pluginVersionId())
            scanFailed != null -> Optional.of(scanFailed.pluginVersionId())
            scanPending != null -> Optional.of(scanPending.pluginVersionId())
            slackChannelSetUpSettingsRefused != null -> Optional.empty()
            apiKeyScopeMissing != null -> Optional.empty()
            claudeTagWritesNotEnabled != null -> Optional.empty()
            complianceConfigurationRestricted != null -> Optional.empty()
            organizationLevelScopedApiKeyRequired != null -> Optional.empty()
            environmentNotFound != null -> Optional.empty()
            slackChannelSetUpEnvironmentNotFound != null -> Optional.empty()
            slackChannelSetUpSettingsConflict != null -> Optional.empty()
            else -> _json.getProperty<String>("plugin_version_id").asKnown()
        }

    fun slackChannelId(): Optional<String> =
        when {
            availableToolsLimitExceeded != null -> Optional.empty()
            claudeCodeKeyCreatorNotMember != null -> Optional.empty()
            claudeCodeVersionTooOld != null -> Optional.empty()
            cmekKeyDisabled != null -> Optional.empty()
            cmekKeyNetworkBlocked != null -> Optional.empty()
            compactionBlockAmbiguous != null -> Optional.empty()
            compactionBlockMisplaced != null -> Optional.empty()
            compactionContentMismatch != null -> Optional.empty()
            compactionIncompleteTurn != null -> Optional.empty()
            compactionNothingToSummarize != null -> Optional.empty()
            compactionSignatureInvalid != null -> Optional.empty()
            compactionTooManyToolReferences != null -> Optional.empty()
            compactionToolChangesMismatch != null -> Optional.empty()
            organizationOnHold != null -> Optional.empty()
            requestBodyEncodingInvalid != null -> Optional.empty()
            requestBodyEncodingUnsupported != null -> Optional.empty()
            threadAlreadyContinued != null -> Optional.empty()
            threadContinueInFlight != null -> Optional.empty()
            threadFingerprintMismatch != null -> Optional.empty()
            threadUnsupportedRequest != null -> Optional.empty()
            toolNameConflict != null -> Optional.empty()
            toolReferenceUnresolved != null -> Optional.empty()
            threadNotFound != null -> Optional.empty()
            skillReadBlockedUndecryptable != null -> Optional.empty()
            requestBodyTooLargeAfterDecoding != null -> Optional.empty()
            enforcedSpendLimitReached != null -> Optional.empty()
            rampLimitExceeded != null -> Optional.empty()
            cmekKeyUnreachable != null -> Optional.empty()
            compactionUnavailable != null -> Optional.empty()
            storageQuotaExceeded != null -> Optional.empty()
            fileNotDownloadable != null -> Optional.empty()
            fileExpired != null -> Optional.empty()
            cmekContextUnavailable != null -> Optional.empty()
            orgApiKeyLimitExceeded != null -> Optional.empty()
            workspaceApiKeyLimitExceeded != null -> Optional.empty()
            userProfileNotFound != null -> Optional.empty()
            externalKeyInWorkspacePolicy != null -> Optional.empty()
            alreadyMember != null -> Optional.empty()
            cannotInviteEmailsWithUnverifiedDomains != null -> Optional.empty()
            emailDomainNotAllowed != null -> Optional.empty()
            inviteAlreadyExists != null -> Optional.empty()
            orgMemberLimitExceeded != null -> Optional.empty()
            rbacGroupGrantsOwnerAccess != null -> Optional.empty()
            rbacGroupOwnerAccessUnconfirmed != null -> Optional.empty()
            cannotRemoveLastMember != null -> Optional.empty()
            concurrentMembershipChange != null -> Optional.empty()
            externalKeyValidationFailed != null -> Optional.empty()
            externalKeyValidationTimedOut != null -> Optional.empty()
            keyProfileMismatch != null -> Optional.empty()
            workspacePolicyViolation != null -> Optional.empty()
            externalKeyChanged != null -> Optional.empty()
            workspaceMemberLimitExceeded != null -> Optional.empty()
            organizationTypeNotSupported != null -> Optional.empty()
            environmentCreateDisabled != null -> Optional.empty()
            environmentArchived != null -> Optional.empty()
            invalidConfigTypeChange != null -> Optional.empty()
            environmentUpdateDisabled != null -> Optional.empty()
            heartbeatPreconditionFailed != null -> Optional.empty()
            invalidMetadata != null -> Optional.empty()
            billingFrozen != null -> Optional.empty()
            spendLimitsAdminApiWritesNotEnabled != null -> Optional.empty()
            unsupportedCharset != null -> Optional.empty()
            pluginNameTaken != null -> Optional.empty()
            skillNameTaken != null -> Optional.empty()
            registrationPending != null -> Optional.empty()
            scanFailed != null -> Optional.empty()
            scanPending != null -> Optional.empty()
            slackChannelSetUpSettingsRefused != null ->
                Optional.of(slackChannelSetUpSettingsRefused.slackChannelId())
            apiKeyScopeMissing != null -> Optional.empty()
            claudeTagWritesNotEnabled != null -> Optional.empty()
            complianceConfigurationRestricted != null -> Optional.empty()
            organizationLevelScopedApiKeyRequired != null -> Optional.empty()
            environmentNotFound != null -> Optional.empty()
            slackChannelSetUpEnvironmentNotFound != null ->
                Optional.of(slackChannelSetUpEnvironmentNotFound.slackChannelId())
            slackChannelSetUpSettingsConflict != null ->
                Optional.of(slackChannelSetUpSettingsConflict.slackChannelId())
            else -> _json.getProperty<String>("slack_channel_id").asKnown()
        }

    /** The tools available after a message exceed a limit on their number or their size. */
    fun availableToolsLimitExceeded(): Optional<AvailableToolsLimitExceededErrorDetails> =
        Optional.ofNullable(availableToolsLimitExceeded)

    /**
     * The API key belongs to the Claude Code workspace, and the user who created it is no longer a
     * member of the organization or of that workspace.
     */
    fun claudeCodeKeyCreatorNotMember(): Optional<ClaudeCodeKeyCreatorNotMemberErrorDetails> =
        Optional.ofNullable(claudeCodeKeyCreatorNotMember)

    /**
     * The request comes from a Claude Code version older than the minimum that the requested model
     * or the organization requires. Update Claude Code, then retry.
     */
    fun claudeCodeVersionTooOld(): Optional<ClaudeCodeVersionTooOldErrorDetails> =
        Optional.ofNullable(claudeCodeVersionTooOld)

    /**
     * The workspace's customer-managed encryption key is disabled or revoked. Re-enable the key,
     * then retry.
     */
    fun cmekKeyDisabled(): Optional<CmekKeyDisabledErrorDetails> =
        Optional.ofNullable(cmekKeyDisabled)

    /**
     * The key management service's network access controls blocked the request to the workspace's
     * customer-managed encryption key. The key itself may be healthy.
     */
    fun cmekKeyNetworkBlocked(): Optional<CmekKeyNetworkBlockedErrorDetails> =
        Optional.ofNullable(cmekKeyNetworkBlocked)

    /**
     * `messages` holds more than one `compaction` block with content. Send only the block the
     * conversation returned last.
     */
    fun compactionBlockAmbiguous(): Optional<CompactionBlockAmbiguousErrorDetails> =
        Optional.ofNullable(compactionBlockAmbiguous)

    /**
     * The `compaction` block is not the first block of the conversation, or is in a `system`
     * message.
     */
    fun compactionBlockMisplaced(): Optional<CompactionBlockMisplacedErrorDetails> =
        Optional.ofNullable(compactionBlockMisplaced)

    /** The `compaction` block's `content` differs from the text its `signature` recorded. */
    fun compactionContentMismatch(): Optional<CompactionContentMismatchErrorDetails> =
        Optional.ofNullable(compactionContentMismatch)

    /** The compaction request ends on an `assistant` turn that is not finished. */
    fun compactionIncompleteTurn(): Optional<CompactionIncompleteTurnErrorDetails> =
        Optional.ofNullable(compactionIncompleteTurn)

    /** `messages` holds no `user` or `assistant` content to summarize or adopt. */
    fun compactionNothingToSummarize(): Optional<CompactionNothingToSummarizeErrorDetails> =
        Optional.ofNullable(compactionNothingToSummarize)

    /** The `compaction` block's `signature` does not verify. */
    fun compactionSignatureInvalid(): Optional<CompactionSignatureInvalidErrorDetails> =
        Optional.ofNullable(compactionSignatureInvalid)

    /** The conversation references more distinct tools than a `compaction` block can record. */
    fun compactionTooManyToolReferences(): Optional<CompactionTooManyToolReferencesErrorDetails> =
        Optional.ofNullable(compactionTooManyToolReferences)

    /** The `compaction` block's `tool_changes` differs from what was returned with the block. */
    fun compactionToolChangesMismatch(): Optional<CompactionToolChangesMismatchErrorDetails> =
        Optional.ofNullable(compactionToolChangesMismatch)

    /**
     * Anthropic has disabled the organization for trust and safety reasons. An organization
     * disabled for any other reason, such as billing, does not get this code. `appeal_url`, when
     * present, is where the organization can appeal.
     */
    fun organizationOnHold(): Optional<OrganizationOnHoldErrorDetails> =
        Optional.ofNullable(organizationOnHold)

    /** The request body is not valid for its `Content-Encoding`. */
    fun requestBodyEncodingInvalid(): Optional<RequestBodyEncodingInvalidErrorDetails> =
        Optional.ofNullable(requestBodyEncodingInvalid)

    /** The request's `Content-Encoding` is not supported. */
    fun requestBodyEncodingUnsupported(): Optional<RequestBodyEncodingUnsupportedErrorDetails> =
        Optional.ofNullable(requestBodyEncodingUnsupported)

    /**
     * The response that `previous_message_id` names has already been continued, and each response
     * can be continued only once. Continue from the Thread's most recent response `id`.
     */
    fun threadAlreadyContinued(): Optional<ThreadAlreadyContinuedErrorDetails> =
        Optional.ofNullable(threadAlreadyContinued)

    /**
     * Another request is still continuing from the same `previous_message_id`. Wait for it to
     * complete and continue from its response `id`, or replay the full conversation with `thread:
     * {"type": "create"}`.
     */
    fun threadContinueInFlight(): Optional<ThreadContinueInFlightErrorDetails> =
        Optional.ofNullable(threadContinueInFlight)

    /**
     * A field that must stay the same across a Thread's turns differs from the value stored with
     * the Thread; the message names each such field. Send the stored values, or replay the full
     * conversation with `thread: {"type": "create"}` to change them.
     */
    fun threadFingerprintMismatch(): Optional<ThreadFingerprintMismatchErrorDetails> =
        Optional.ofNullable(threadFingerprintMismatch)

    /**
     * The request combines `thread` with a request field or an organization setting that Threads do
     * not support; `unsupported`, when present, names it. Send the full conversation without
     * `thread`, or change that field.
     */
    fun threadUnsupportedRequest(): Optional<ThreadUnsupportedRequestErrorDetails> =
        Optional.ofNullable(threadUnsupportedRequest)

    /**
     * A tool made available in `messages` takes a name that an available tool of another kind, or
     * of another MCP server, already holds.
     */
    fun toolNameConflict(): Optional<ToolNameConflictErrorDetails> =
        Optional.ofNullable(toolNameConflict)

    /**
     * A tool change or MCP tool listing references a tool or MCP server that is not defined at that
     * position in the conversation.
     */
    fun toolReferenceUnresolved(): Optional<ToolReferenceUnresolvedErrorDetails> =
        Optional.ofNullable(toolReferenceUnresolved)

    /**
     * No usable Thread state was found for `previous_message_id`. Replay the full conversation with
     * `thread: {"type": "create"}` to start a new Thread.
     */
    fun threadNotFound(): Optional<ThreadNotFoundErrorDetails> = Optional.ofNullable(threadNotFound)

    /**
     * A custom skill the request names is stored under an encryption key state the workspace cannot
     * currently decrypt.
     */
    fun skillReadBlockedUndecryptable(): Optional<SkillReadBlockedUndecryptableErrorDetails> =
        Optional.ofNullable(skillReadBlockedUndecryptable)

    /** The compressed request body exceeds the request size limit once decoded. */
    fun requestBodyTooLargeAfterDecoding(): Optional<RequestBodyTooLargeAfterDecodingErrorDetails> =
        Optional.ofNullable(requestBodyTooLargeAfterDecoding)

    /** The organization has crossed the monthly API usage threshold of its API tier. */
    fun enforcedSpendLimitReached(): Optional<EnforcedSpendLimitReachedErrorDetails> =
        Optional.ofNullable(enforcedSpendLimitReached)

    /**
     * The request would exceed a ramp limit, which caps how fast usage can grow. Retry after the
     * time in the `retry-after` header.
     */
    fun rampLimitExceeded(): Optional<RampLimitExceededErrorDetails> =
        Optional.ofNullable(rampLimitExceeded)

    /**
     * The workspace's customer-managed encryption key could not be reached. Retry the request; if
     * this continues, check that the key is available in your key management service, or contact
     * Anthropic support.
     */
    fun cmekKeyUnreachable(): Optional<CmekKeyUnreachableErrorDetails> =
        Optional.ofNullable(cmekKeyUnreachable)

    /** The compaction could not be completed for a transient server reason. Retry the request. */
    fun compactionUnavailable(): Optional<CompactionUnavailableErrorDetails> =
        Optional.ofNullable(compactionUnavailable)

    /**
     * The organization's file storage quota is exhausted. Delete files to free up space, then retry
     * the upload.
     */
    fun storageQuotaExceeded(): Optional<StorageQuotaExceededErrorDetails> =
        Optional.ofNullable(storageQuotaExceeded)

    /**
     * The file has `downloadable: false`. Only files generated by a tool, such as the code
     * execution tool, can be downloaded.
     */
    fun fileNotDownloadable(): Optional<FileNotDownloadableErrorDetails> =
        Optional.ofNullable(fileNotDownloadable)

    /** The file's `expires_at` has passed, so its content is no longer available. */
    fun fileExpired(): Optional<FileExpiredErrorDetails> = Optional.ofNullable(fileExpired)

    /**
     * The workspace's encryption settings could not be read, so the request was refused. Retry in a
     * few minutes.
     */
    fun cmekContextUnavailable(): Optional<CmekContextUnavailableErrorDetails> =
        Optional.ofNullable(cmekContextUnavailable)

    /** The organization has reached its limit of active API keys. */
    fun orgApiKeyLimitExceeded(): Optional<OrgApiKeyLimitExceededErrorDetails> =
        Optional.ofNullable(orgApiKeyLimitExceeded)

    /** The workspace has reached its limit of active API keys. */
    fun workspaceApiKeyLimitExceeded(): Optional<WorkspaceApiKeyLimitExceededErrorDetails> =
        Optional.ofNullable(workspaceApiKeyLimitExceeded)

    /**
     * The request depends on a user profile that does not exist in the organization or is no longer
     * active. The request either names this user profile by ID, or names a resource, such as an
     * External Key, that is bound to it.
     */
    fun userProfileNotFound(): Optional<UserProfileNotFoundErrorDetails> =
        Optional.ofNullable(userProfileNotFound)

    /**
     * The External Key can't be deleted or reconfigured while the organization's Workspace Policy
     * lists it as an allowed key.
     */
    fun externalKeyInWorkspacePolicy(): Optional<ExternalKeyInWorkspacePolicyErrorDetails> =
        Optional.ofNullable(externalKeyInWorkspacePolicy)

    /**
     * The email address already belongs to a user in the organization. The message names the user.
     */
    fun alreadyMember(): Optional<AlreadyMemberErrorDetails> = Optional.ofNullable(alreadyMember)

    /**
     * The email address's domain is not one of the verified domains of the organization's parent
     * organization.
     */
    fun cannotInviteEmailsWithUnverifiedDomains():
        Optional<CannotInviteEmailsWithUnverifiedDomainsErrorDetails> =
        Optional.ofNullable(cannotInviteEmailsWithUnverifiedDomains)

    /**
     * The organization restricts invites to an allowed list of email domains, and the list is empty
     * or does not include the email address's domain.
     */
    fun emailDomainNotAllowed(): Optional<EmailDomainNotAllowedErrorDetails> =
        Optional.ofNullable(emailDomainNotAllowed)

    /** A pending invite already exists for the email address. The message names the invite. */
    fun inviteAlreadyExists(): Optional<InviteAlreadyExistsErrorDetails> =
        Optional.ofNullable(inviteAlreadyExists)

    /**
     * The organization has reached the member limit that Anthropic set for it. Pending invites
     * count toward the limit.
     */
    fun orgMemberLimitExceeded(): Optional<OrgMemberLimitExceededErrorDetails> =
        Optional.ofNullable(orgMemberLimitExceeded)

    /**
     * A role attached to an RBAC Group in the request grants owner access, so this API does not add
     * members to that group (directly or through an invite), remove its members, or delete it. Make
     * the change in the organization's admin settings in Claude.
     */
    fun rbacGroupGrantsOwnerAccess(): Optional<RbacGroupGrantsOwnerAccessErrorDetails> =
        Optional.ofNullable(rbacGroupGrantsOwnerAccess)

    /**
     * The API could not finish checking whether an RBAC Group in the request grants owner access,
     * so it made no change. Retrying the same request will not help. Make the change in the
     * organization's admin settings in Claude.
     */
    fun rbacGroupOwnerAccessUnconfirmed(): Optional<RbacGroupOwnerAccessUnconfirmedErrorDetails> =
        Optional.ofNullable(rbacGroupOwnerAccessUnconfirmed)

    /** The user is the organization's only member. Add another member before removing this one. */
    fun cannotRemoveLastMember(): Optional<CannotRemoveLastMemberErrorDetails> =
        Optional.ofNullable(cannotRemoveLastMember)

    /**
     * Another change to the organization's members is in progress. Retry the request once it
     * completes.
     */
    fun concurrentMembershipChange(): Optional<ConcurrentMembershipChangeErrorDetails> =
        Optional.ofNullable(concurrentMembershipChange)

    /**
     * The External Key failed Anthropic's validation, so the request was refused. The message gives
     * the reason, if there is one.
     */
    fun externalKeyValidationFailed(): Optional<ExternalKeyValidationFailedErrorDetails> =
        Optional.ofNullable(externalKeyValidationFailed)

    /**
     * Anthropic's validation of the External Key did not finish in time, so the request was
     * refused. Wait a moment, then send the request again.
     */
    fun externalKeyValidationTimedOut(): Optional<ExternalKeyValidationTimedOutErrorDetails> =
        Optional.ofNullable(externalKeyValidationTimedOut)

    /**
     * The External Key is bound to a user profile, and the Workspace does not have that user
     * profile. A Workspace can use only a key bound to its own user profile or a key that isn't
     * bound to one.
     */
    fun keyProfileMismatch(): Optional<KeyProfileMismatchErrorDetails> =
        Optional.ofNullable(keyProfileMismatch)

    /**
     * The request breaks the organization's Workspace Policy. The message says which rule it broke;
     * an organization admin can see and change the policy in the Console.
     */
    fun workspacePolicyViolation(): Optional<WorkspacePolicyViolationErrorDetails> =
        Optional.ofNullable(workspacePolicyViolation)

    /** The external key changed while the request was validating it. Send the request again. */
    fun externalKeyChanged(): Optional<ExternalKeyChangedErrorDetails> =
        Optional.ofNullable(externalKeyChanged)

    /** The workspace has reached the member limit that Anthropic set for it. */
    fun workspaceMemberLimitExceeded(): Optional<WorkspaceMemberLimitExceededErrorDetails> =
        Optional.ofNullable(workspaceMemberLimitExceeded)

    /**
     * The endpoint does not support the organization's type. Each endpoint's description says which
     * organization types it supports.
     */
    fun organizationTypeNotSupported(): Optional<OrganizationTypeNotSupportedErrorDetails> =
        Optional.ofNullable(organizationTypeNotSupported)

    /**
     * Anthropic has turned off creating environments for this organization. Existing environments
     * still work.
     */
    fun environmentCreateDisabled(): Optional<BetaEnvironmentCreateDisabledErrorDetails> =
        Optional.ofNullable(environmentCreateDisabled)

    /** The environment is archived, so it cannot be updated. */
    fun environmentArchived(): Optional<BetaEnvironmentArchivedErrorDetails> =
        Optional.ofNullable(environmentArchived)

    /**
     * `config.type` differs from the environment's stored type, which cannot change after creation.
     */
    fun invalidConfigTypeChange(): Optional<BetaInvalidConfigTypeChangeErrorDetails> =
        Optional.ofNullable(invalidConfigTypeChange)

    /**
     * Anthropic has turned off updating environments for this organization. Existing environments
     * still work as they are.
     */
    fun environmentUpdateDisabled(): Optional<BetaEnvironmentUpdateDisabledErrorDetails> =
        Optional.ofNullable(environmentUpdateDisabled)

    /**
     * `expected_last_heartbeat` does not match the stored heartbeat, so another worker may hold the
     * lease.
     */
    fun heartbeatPreconditionFailed(): Optional<BetaHeartbeatPreconditionFailedErrorDetails> =
        Optional.ofNullable(heartbeatPreconditionFailed)

    /**
     * `metadata` has too many keys, a key or value that is too long, or a reserved key. The message
     * names each problem.
     */
    fun invalidMetadata(): Optional<BetaInvalidMetadataErrorDetails> =
        Optional.ofNullable(invalidMetadata)

    /**
     * Anthropic has temporarily disabled billing changes for the user account the credential
     * belongs to. Contact support.
     */
    fun billingFrozen(): Optional<BetaBillingFrozenErrorDetails> =
        Optional.ofNullable(billingFrozen)

    /**
     * The organization cannot retrieve, set or delete spend limits with an Admin API credential.
     */
    fun spendLimitsAdminApiWritesNotEnabled():
        Optional<BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails> =
        Optional.ofNullable(spendLimitsAdminApiWritesNotEnabled)

    /**
     * The request's `Content-Type` header names a charset other than UTF-8, ISO-8859-1 or US-ASCII.
     * Send the request with no charset, or with one of these.
     */
    fun unsupportedCharset(): Optional<BetaUnsupportedCharsetErrorDetails> =
        Optional.ofNullable(unsupportedCharset)

    /**
     * The marketplace already holds this name. `plugin_id` is the Plugin that holds it, and can be
     * absent.
     */
    fun pluginNameTaken(): Optional<BetaPluginNameTakenErrorDetails> =
        Optional.ofNullable(pluginNameTaken)

    /**
     * One of the Plugin's skills has the name of an organization skill. `skill_name` is that name.
     */
    fun skillNameTaken(): Optional<BetaSkillNameTakenErrorDetails> =
        Optional.ofNullable(skillNameTaken)

    /**
     * The Plugin version was stored but is not yet usable in claude.ai. `plugin_id` and
     * `plugin_version_id` identify what was stored.
     */
    fun registrationPending(): Optional<BetaRegistrationPendingErrorDetails> =
        Optional.ofNullable(registrationPending)

    /** The version in `plugin_version_id` did not pass its content scan, so it cannot be served. */
    fun scanFailed(): Optional<BetaScanFailedErrorDetails> = Optional.ofNullable(scanFailed)

    /**
     * The content scan of the version in `plugin_version_id` is still running. Retry once the scan
     * finishes.
     */
    fun scanPending(): Optional<BetaScanPendingErrorDetails> = Optional.ofNullable(scanPending)

    /**
     * The request set the channel up, and `settings` was then refused. The channel stays set up
     * with every setting unset.
     */
    fun slackChannelSetUpSettingsRefused():
        Optional<BetaSlackChannelSetUpSettingsRefusedErrorDetails> =
        Optional.ofNullable(slackChannelSetUpSettingsRefused)

    /**
     * The API key lacks the scope the request needs: `read:claude_tag` for a read,
     * `write:claude_tag` for a change.
     */
    fun apiKeyScopeMissing(): Optional<BetaApiKeyScopeMissingErrorDetails> =
        Optional.ofNullable(apiKeyScopeMissing)

    /** Changes through the Claude Tag API are not enabled for the organization. */
    fun claudeTagWritesNotEnabled(): Optional<BetaClaudeTagWritesNotEnabledErrorDetails> =
        Optional.ofNullable(claudeTagWritesNotEnabled)

    /** The organization's compliance configuration does not let Claude Tag store anything new. */
    fun complianceConfigurationRestricted():
        Optional<BetaComplianceConfigurationRestrictedErrorDetails> =
        Optional.ofNullable(complianceConfigurationRestricted)

    /** The credential is not a scoped API key that is bound at the organization level. */
    fun organizationLevelScopedApiKeyRequired():
        Optional<BetaOrganizationLevelScopedApiKeyRequiredErrorDetails> =
        Optional.ofNullable(organizationLevelScopedApiKeyRequired)

    /** `settings.environment_id` names no Environment within the organization's reach. */
    fun environmentNotFound(): Optional<BetaEnvironmentNotFoundErrorDetails> =
        Optional.ofNullable(environmentNotFound)

    /**
     * `settings.environment_id` names no Environment within the organization's reach, and the
     * request had set the channel up by then. The channel stays set up with every setting unset.
     */
    fun slackChannelSetUpEnvironmentNotFound():
        Optional<BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails> =
        Optional.ofNullable(slackChannelSetUpEnvironmentNotFound)

    /**
     * The request set the channel up, and something else then changed the channel or removed it.
     * Nothing of `settings` was stored.
     */
    fun slackChannelSetUpSettingsConflict():
        Optional<BetaSlackChannelSetUpSettingsConflictErrorDetails> =
        Optional.ofNullable(slackChannelSetUpSettingsConflict)

    fun isAvailableToolsLimitExceeded(): Boolean = availableToolsLimitExceeded != null

    fun isClaudeCodeKeyCreatorNotMember(): Boolean = claudeCodeKeyCreatorNotMember != null

    fun isClaudeCodeVersionTooOld(): Boolean = claudeCodeVersionTooOld != null

    fun isCmekKeyDisabled(): Boolean = cmekKeyDisabled != null

    fun isCmekKeyNetworkBlocked(): Boolean = cmekKeyNetworkBlocked != null

    fun isCompactionBlockAmbiguous(): Boolean = compactionBlockAmbiguous != null

    fun isCompactionBlockMisplaced(): Boolean = compactionBlockMisplaced != null

    fun isCompactionContentMismatch(): Boolean = compactionContentMismatch != null

    fun isCompactionIncompleteTurn(): Boolean = compactionIncompleteTurn != null

    fun isCompactionNothingToSummarize(): Boolean = compactionNothingToSummarize != null

    fun isCompactionSignatureInvalid(): Boolean = compactionSignatureInvalid != null

    fun isCompactionTooManyToolReferences(): Boolean = compactionTooManyToolReferences != null

    fun isCompactionToolChangesMismatch(): Boolean = compactionToolChangesMismatch != null

    fun isOrganizationOnHold(): Boolean = organizationOnHold != null

    fun isRequestBodyEncodingInvalid(): Boolean = requestBodyEncodingInvalid != null

    fun isRequestBodyEncodingUnsupported(): Boolean = requestBodyEncodingUnsupported != null

    fun isThreadAlreadyContinued(): Boolean = threadAlreadyContinued != null

    fun isThreadContinueInFlight(): Boolean = threadContinueInFlight != null

    fun isThreadFingerprintMismatch(): Boolean = threadFingerprintMismatch != null

    fun isThreadUnsupportedRequest(): Boolean = threadUnsupportedRequest != null

    fun isToolNameConflict(): Boolean = toolNameConflict != null

    fun isToolReferenceUnresolved(): Boolean = toolReferenceUnresolved != null

    fun isThreadNotFound(): Boolean = threadNotFound != null

    fun isSkillReadBlockedUndecryptable(): Boolean = skillReadBlockedUndecryptable != null

    fun isRequestBodyTooLargeAfterDecoding(): Boolean = requestBodyTooLargeAfterDecoding != null

    fun isEnforcedSpendLimitReached(): Boolean = enforcedSpendLimitReached != null

    fun isRampLimitExceeded(): Boolean = rampLimitExceeded != null

    fun isCmekKeyUnreachable(): Boolean = cmekKeyUnreachable != null

    fun isCompactionUnavailable(): Boolean = compactionUnavailable != null

    fun isStorageQuotaExceeded(): Boolean = storageQuotaExceeded != null

    fun isFileNotDownloadable(): Boolean = fileNotDownloadable != null

    fun isFileExpired(): Boolean = fileExpired != null

    fun isCmekContextUnavailable(): Boolean = cmekContextUnavailable != null

    fun isOrgApiKeyLimitExceeded(): Boolean = orgApiKeyLimitExceeded != null

    fun isWorkspaceApiKeyLimitExceeded(): Boolean = workspaceApiKeyLimitExceeded != null

    fun isUserProfileNotFound(): Boolean = userProfileNotFound != null

    fun isExternalKeyInWorkspacePolicy(): Boolean = externalKeyInWorkspacePolicy != null

    fun isAlreadyMember(): Boolean = alreadyMember != null

    fun isCannotInviteEmailsWithUnverifiedDomains(): Boolean =
        cannotInviteEmailsWithUnverifiedDomains != null

    fun isEmailDomainNotAllowed(): Boolean = emailDomainNotAllowed != null

    fun isInviteAlreadyExists(): Boolean = inviteAlreadyExists != null

    fun isOrgMemberLimitExceeded(): Boolean = orgMemberLimitExceeded != null

    fun isRbacGroupGrantsOwnerAccess(): Boolean = rbacGroupGrantsOwnerAccess != null

    fun isRbacGroupOwnerAccessUnconfirmed(): Boolean = rbacGroupOwnerAccessUnconfirmed != null

    fun isCannotRemoveLastMember(): Boolean = cannotRemoveLastMember != null

    fun isConcurrentMembershipChange(): Boolean = concurrentMembershipChange != null

    fun isExternalKeyValidationFailed(): Boolean = externalKeyValidationFailed != null

    fun isExternalKeyValidationTimedOut(): Boolean = externalKeyValidationTimedOut != null

    fun isKeyProfileMismatch(): Boolean = keyProfileMismatch != null

    fun isWorkspacePolicyViolation(): Boolean = workspacePolicyViolation != null

    fun isExternalKeyChanged(): Boolean = externalKeyChanged != null

    fun isWorkspaceMemberLimitExceeded(): Boolean = workspaceMemberLimitExceeded != null

    fun isOrganizationTypeNotSupported(): Boolean = organizationTypeNotSupported != null

    fun isEnvironmentCreateDisabled(): Boolean = environmentCreateDisabled != null

    fun isEnvironmentArchived(): Boolean = environmentArchived != null

    fun isInvalidConfigTypeChange(): Boolean = invalidConfigTypeChange != null

    fun isEnvironmentUpdateDisabled(): Boolean = environmentUpdateDisabled != null

    fun isHeartbeatPreconditionFailed(): Boolean = heartbeatPreconditionFailed != null

    fun isInvalidMetadata(): Boolean = invalidMetadata != null

    fun isBillingFrozen(): Boolean = billingFrozen != null

    fun isSpendLimitsAdminApiWritesNotEnabled(): Boolean =
        spendLimitsAdminApiWritesNotEnabled != null

    fun isUnsupportedCharset(): Boolean = unsupportedCharset != null

    fun isPluginNameTaken(): Boolean = pluginNameTaken != null

    fun isSkillNameTaken(): Boolean = skillNameTaken != null

    fun isRegistrationPending(): Boolean = registrationPending != null

    fun isScanFailed(): Boolean = scanFailed != null

    fun isScanPending(): Boolean = scanPending != null

    fun isSlackChannelSetUpSettingsRefused(): Boolean = slackChannelSetUpSettingsRefused != null

    fun isApiKeyScopeMissing(): Boolean = apiKeyScopeMissing != null

    fun isClaudeTagWritesNotEnabled(): Boolean = claudeTagWritesNotEnabled != null

    fun isComplianceConfigurationRestricted(): Boolean = complianceConfigurationRestricted != null

    fun isOrganizationLevelScopedApiKeyRequired(): Boolean =
        organizationLevelScopedApiKeyRequired != null

    fun isEnvironmentNotFound(): Boolean = environmentNotFound != null

    fun isSlackChannelSetUpEnvironmentNotFound(): Boolean =
        slackChannelSetUpEnvironmentNotFound != null

    fun isSlackChannelSetUpSettingsConflict(): Boolean = slackChannelSetUpSettingsConflict != null

    /** The tools available after a message exceed a limit on their number or their size. */
    fun asAvailableToolsLimitExceeded(): AvailableToolsLimitExceededErrorDetails =
        availableToolsLimitExceeded.getOrThrow("availableToolsLimitExceeded")

    /**
     * The API key belongs to the Claude Code workspace, and the user who created it is no longer a
     * member of the organization or of that workspace.
     */
    fun asClaudeCodeKeyCreatorNotMember(): ClaudeCodeKeyCreatorNotMemberErrorDetails =
        claudeCodeKeyCreatorNotMember.getOrThrow("claudeCodeKeyCreatorNotMember")

    /**
     * The request comes from a Claude Code version older than the minimum that the requested model
     * or the organization requires. Update Claude Code, then retry.
     */
    fun asClaudeCodeVersionTooOld(): ClaudeCodeVersionTooOldErrorDetails =
        claudeCodeVersionTooOld.getOrThrow("claudeCodeVersionTooOld")

    /**
     * The workspace's customer-managed encryption key is disabled or revoked. Re-enable the key,
     * then retry.
     */
    fun asCmekKeyDisabled(): CmekKeyDisabledErrorDetails =
        cmekKeyDisabled.getOrThrow("cmekKeyDisabled")

    /**
     * The key management service's network access controls blocked the request to the workspace's
     * customer-managed encryption key. The key itself may be healthy.
     */
    fun asCmekKeyNetworkBlocked(): CmekKeyNetworkBlockedErrorDetails =
        cmekKeyNetworkBlocked.getOrThrow("cmekKeyNetworkBlocked")

    /**
     * `messages` holds more than one `compaction` block with content. Send only the block the
     * conversation returned last.
     */
    fun asCompactionBlockAmbiguous(): CompactionBlockAmbiguousErrorDetails =
        compactionBlockAmbiguous.getOrThrow("compactionBlockAmbiguous")

    /**
     * The `compaction` block is not the first block of the conversation, or is in a `system`
     * message.
     */
    fun asCompactionBlockMisplaced(): CompactionBlockMisplacedErrorDetails =
        compactionBlockMisplaced.getOrThrow("compactionBlockMisplaced")

    /** The `compaction` block's `content` differs from the text its `signature` recorded. */
    fun asCompactionContentMismatch(): CompactionContentMismatchErrorDetails =
        compactionContentMismatch.getOrThrow("compactionContentMismatch")

    /** The compaction request ends on an `assistant` turn that is not finished. */
    fun asCompactionIncompleteTurn(): CompactionIncompleteTurnErrorDetails =
        compactionIncompleteTurn.getOrThrow("compactionIncompleteTurn")

    /** `messages` holds no `user` or `assistant` content to summarize or adopt. */
    fun asCompactionNothingToSummarize(): CompactionNothingToSummarizeErrorDetails =
        compactionNothingToSummarize.getOrThrow("compactionNothingToSummarize")

    /** The `compaction` block's `signature` does not verify. */
    fun asCompactionSignatureInvalid(): CompactionSignatureInvalidErrorDetails =
        compactionSignatureInvalid.getOrThrow("compactionSignatureInvalid")

    /** The conversation references more distinct tools than a `compaction` block can record. */
    fun asCompactionTooManyToolReferences(): CompactionTooManyToolReferencesErrorDetails =
        compactionTooManyToolReferences.getOrThrow("compactionTooManyToolReferences")

    /** The `compaction` block's `tool_changes` differs from what was returned with the block. */
    fun asCompactionToolChangesMismatch(): CompactionToolChangesMismatchErrorDetails =
        compactionToolChangesMismatch.getOrThrow("compactionToolChangesMismatch")

    /**
     * Anthropic has disabled the organization for trust and safety reasons. An organization
     * disabled for any other reason, such as billing, does not get this code. `appeal_url`, when
     * present, is where the organization can appeal.
     */
    fun asOrganizationOnHold(): OrganizationOnHoldErrorDetails =
        organizationOnHold.getOrThrow("organizationOnHold")

    /** The request body is not valid for its `Content-Encoding`. */
    fun asRequestBodyEncodingInvalid(): RequestBodyEncodingInvalidErrorDetails =
        requestBodyEncodingInvalid.getOrThrow("requestBodyEncodingInvalid")

    /** The request's `Content-Encoding` is not supported. */
    fun asRequestBodyEncodingUnsupported(): RequestBodyEncodingUnsupportedErrorDetails =
        requestBodyEncodingUnsupported.getOrThrow("requestBodyEncodingUnsupported")

    /**
     * The response that `previous_message_id` names has already been continued, and each response
     * can be continued only once. Continue from the Thread's most recent response `id`.
     */
    fun asThreadAlreadyContinued(): ThreadAlreadyContinuedErrorDetails =
        threadAlreadyContinued.getOrThrow("threadAlreadyContinued")

    /**
     * Another request is still continuing from the same `previous_message_id`. Wait for it to
     * complete and continue from its response `id`, or replay the full conversation with `thread:
     * {"type": "create"}`.
     */
    fun asThreadContinueInFlight(): ThreadContinueInFlightErrorDetails =
        threadContinueInFlight.getOrThrow("threadContinueInFlight")

    /**
     * A field that must stay the same across a Thread's turns differs from the value stored with
     * the Thread; the message names each such field. Send the stored values, or replay the full
     * conversation with `thread: {"type": "create"}` to change them.
     */
    fun asThreadFingerprintMismatch(): ThreadFingerprintMismatchErrorDetails =
        threadFingerprintMismatch.getOrThrow("threadFingerprintMismatch")

    /**
     * The request combines `thread` with a request field or an organization setting that Threads do
     * not support; `unsupported`, when present, names it. Send the full conversation without
     * `thread`, or change that field.
     */
    fun asThreadUnsupportedRequest(): ThreadUnsupportedRequestErrorDetails =
        threadUnsupportedRequest.getOrThrow("threadUnsupportedRequest")

    /**
     * A tool made available in `messages` takes a name that an available tool of another kind, or
     * of another MCP server, already holds.
     */
    fun asToolNameConflict(): ToolNameConflictErrorDetails =
        toolNameConflict.getOrThrow("toolNameConflict")

    /**
     * A tool change or MCP tool listing references a tool or MCP server that is not defined at that
     * position in the conversation.
     */
    fun asToolReferenceUnresolved(): ToolReferenceUnresolvedErrorDetails =
        toolReferenceUnresolved.getOrThrow("toolReferenceUnresolved")

    /**
     * No usable Thread state was found for `previous_message_id`. Replay the full conversation with
     * `thread: {"type": "create"}` to start a new Thread.
     */
    fun asThreadNotFound(): ThreadNotFoundErrorDetails = threadNotFound.getOrThrow("threadNotFound")

    /**
     * A custom skill the request names is stored under an encryption key state the workspace cannot
     * currently decrypt.
     */
    fun asSkillReadBlockedUndecryptable(): SkillReadBlockedUndecryptableErrorDetails =
        skillReadBlockedUndecryptable.getOrThrow("skillReadBlockedUndecryptable")

    /** The compressed request body exceeds the request size limit once decoded. */
    fun asRequestBodyTooLargeAfterDecoding(): RequestBodyTooLargeAfterDecodingErrorDetails =
        requestBodyTooLargeAfterDecoding.getOrThrow("requestBodyTooLargeAfterDecoding")

    /** The organization has crossed the monthly API usage threshold of its API tier. */
    fun asEnforcedSpendLimitReached(): EnforcedSpendLimitReachedErrorDetails =
        enforcedSpendLimitReached.getOrThrow("enforcedSpendLimitReached")

    /**
     * The request would exceed a ramp limit, which caps how fast usage can grow. Retry after the
     * time in the `retry-after` header.
     */
    fun asRampLimitExceeded(): RampLimitExceededErrorDetails =
        rampLimitExceeded.getOrThrow("rampLimitExceeded")

    /**
     * The workspace's customer-managed encryption key could not be reached. Retry the request; if
     * this continues, check that the key is available in your key management service, or contact
     * Anthropic support.
     */
    fun asCmekKeyUnreachable(): CmekKeyUnreachableErrorDetails =
        cmekKeyUnreachable.getOrThrow("cmekKeyUnreachable")

    /** The compaction could not be completed for a transient server reason. Retry the request. */
    fun asCompactionUnavailable(): CompactionUnavailableErrorDetails =
        compactionUnavailable.getOrThrow("compactionUnavailable")

    /**
     * The organization's file storage quota is exhausted. Delete files to free up space, then retry
     * the upload.
     */
    fun asStorageQuotaExceeded(): StorageQuotaExceededErrorDetails =
        storageQuotaExceeded.getOrThrow("storageQuotaExceeded")

    /**
     * The file has `downloadable: false`. Only files generated by a tool, such as the code
     * execution tool, can be downloaded.
     */
    fun asFileNotDownloadable(): FileNotDownloadableErrorDetails =
        fileNotDownloadable.getOrThrow("fileNotDownloadable")

    /** The file's `expires_at` has passed, so its content is no longer available. */
    fun asFileExpired(): FileExpiredErrorDetails = fileExpired.getOrThrow("fileExpired")

    /**
     * The workspace's encryption settings could not be read, so the request was refused. Retry in a
     * few minutes.
     */
    fun asCmekContextUnavailable(): CmekContextUnavailableErrorDetails =
        cmekContextUnavailable.getOrThrow("cmekContextUnavailable")

    /** The organization has reached its limit of active API keys. */
    fun asOrgApiKeyLimitExceeded(): OrgApiKeyLimitExceededErrorDetails =
        orgApiKeyLimitExceeded.getOrThrow("orgApiKeyLimitExceeded")

    /** The workspace has reached its limit of active API keys. */
    fun asWorkspaceApiKeyLimitExceeded(): WorkspaceApiKeyLimitExceededErrorDetails =
        workspaceApiKeyLimitExceeded.getOrThrow("workspaceApiKeyLimitExceeded")

    /**
     * The request depends on a user profile that does not exist in the organization or is no longer
     * active. The request either names this user profile by ID, or names a resource, such as an
     * External Key, that is bound to it.
     */
    fun asUserProfileNotFound(): UserProfileNotFoundErrorDetails =
        userProfileNotFound.getOrThrow("userProfileNotFound")

    /**
     * The External Key can't be deleted or reconfigured while the organization's Workspace Policy
     * lists it as an allowed key.
     */
    fun asExternalKeyInWorkspacePolicy(): ExternalKeyInWorkspacePolicyErrorDetails =
        externalKeyInWorkspacePolicy.getOrThrow("externalKeyInWorkspacePolicy")

    /**
     * The email address already belongs to a user in the organization. The message names the user.
     */
    fun asAlreadyMember(): AlreadyMemberErrorDetails = alreadyMember.getOrThrow("alreadyMember")

    /**
     * The email address's domain is not one of the verified domains of the organization's parent
     * organization.
     */
    fun asCannotInviteEmailsWithUnverifiedDomains():
        CannotInviteEmailsWithUnverifiedDomainsErrorDetails =
        cannotInviteEmailsWithUnverifiedDomains.getOrThrow(
            "cannotInviteEmailsWithUnverifiedDomains"
        )

    /**
     * The organization restricts invites to an allowed list of email domains, and the list is empty
     * or does not include the email address's domain.
     */
    fun asEmailDomainNotAllowed(): EmailDomainNotAllowedErrorDetails =
        emailDomainNotAllowed.getOrThrow("emailDomainNotAllowed")

    /** A pending invite already exists for the email address. The message names the invite. */
    fun asInviteAlreadyExists(): InviteAlreadyExistsErrorDetails =
        inviteAlreadyExists.getOrThrow("inviteAlreadyExists")

    /**
     * The organization has reached the member limit that Anthropic set for it. Pending invites
     * count toward the limit.
     */
    fun asOrgMemberLimitExceeded(): OrgMemberLimitExceededErrorDetails =
        orgMemberLimitExceeded.getOrThrow("orgMemberLimitExceeded")

    /**
     * A role attached to an RBAC Group in the request grants owner access, so this API does not add
     * members to that group (directly or through an invite), remove its members, or delete it. Make
     * the change in the organization's admin settings in Claude.
     */
    fun asRbacGroupGrantsOwnerAccess(): RbacGroupGrantsOwnerAccessErrorDetails =
        rbacGroupGrantsOwnerAccess.getOrThrow("rbacGroupGrantsOwnerAccess")

    /**
     * The API could not finish checking whether an RBAC Group in the request grants owner access,
     * so it made no change. Retrying the same request will not help. Make the change in the
     * organization's admin settings in Claude.
     */
    fun asRbacGroupOwnerAccessUnconfirmed(): RbacGroupOwnerAccessUnconfirmedErrorDetails =
        rbacGroupOwnerAccessUnconfirmed.getOrThrow("rbacGroupOwnerAccessUnconfirmed")

    /** The user is the organization's only member. Add another member before removing this one. */
    fun asCannotRemoveLastMember(): CannotRemoveLastMemberErrorDetails =
        cannotRemoveLastMember.getOrThrow("cannotRemoveLastMember")

    /**
     * Another change to the organization's members is in progress. Retry the request once it
     * completes.
     */
    fun asConcurrentMembershipChange(): ConcurrentMembershipChangeErrorDetails =
        concurrentMembershipChange.getOrThrow("concurrentMembershipChange")

    /**
     * The External Key failed Anthropic's validation, so the request was refused. The message gives
     * the reason, if there is one.
     */
    fun asExternalKeyValidationFailed(): ExternalKeyValidationFailedErrorDetails =
        externalKeyValidationFailed.getOrThrow("externalKeyValidationFailed")

    /**
     * Anthropic's validation of the External Key did not finish in time, so the request was
     * refused. Wait a moment, then send the request again.
     */
    fun asExternalKeyValidationTimedOut(): ExternalKeyValidationTimedOutErrorDetails =
        externalKeyValidationTimedOut.getOrThrow("externalKeyValidationTimedOut")

    /**
     * The External Key is bound to a user profile, and the Workspace does not have that user
     * profile. A Workspace can use only a key bound to its own user profile or a key that isn't
     * bound to one.
     */
    fun asKeyProfileMismatch(): KeyProfileMismatchErrorDetails =
        keyProfileMismatch.getOrThrow("keyProfileMismatch")

    /**
     * The request breaks the organization's Workspace Policy. The message says which rule it broke;
     * an organization admin can see and change the policy in the Console.
     */
    fun asWorkspacePolicyViolation(): WorkspacePolicyViolationErrorDetails =
        workspacePolicyViolation.getOrThrow("workspacePolicyViolation")

    /** The external key changed while the request was validating it. Send the request again. */
    fun asExternalKeyChanged(): ExternalKeyChangedErrorDetails =
        externalKeyChanged.getOrThrow("externalKeyChanged")

    /** The workspace has reached the member limit that Anthropic set for it. */
    fun asWorkspaceMemberLimitExceeded(): WorkspaceMemberLimitExceededErrorDetails =
        workspaceMemberLimitExceeded.getOrThrow("workspaceMemberLimitExceeded")

    /**
     * The endpoint does not support the organization's type. Each endpoint's description says which
     * organization types it supports.
     */
    fun asOrganizationTypeNotSupported(): OrganizationTypeNotSupportedErrorDetails =
        organizationTypeNotSupported.getOrThrow("organizationTypeNotSupported")

    /**
     * Anthropic has turned off creating environments for this organization. Existing environments
     * still work.
     */
    fun asEnvironmentCreateDisabled(): BetaEnvironmentCreateDisabledErrorDetails =
        environmentCreateDisabled.getOrThrow("environmentCreateDisabled")

    /** The environment is archived, so it cannot be updated. */
    fun asEnvironmentArchived(): BetaEnvironmentArchivedErrorDetails =
        environmentArchived.getOrThrow("environmentArchived")

    /**
     * `config.type` differs from the environment's stored type, which cannot change after creation.
     */
    fun asInvalidConfigTypeChange(): BetaInvalidConfigTypeChangeErrorDetails =
        invalidConfigTypeChange.getOrThrow("invalidConfigTypeChange")

    /**
     * Anthropic has turned off updating environments for this organization. Existing environments
     * still work as they are.
     */
    fun asEnvironmentUpdateDisabled(): BetaEnvironmentUpdateDisabledErrorDetails =
        environmentUpdateDisabled.getOrThrow("environmentUpdateDisabled")

    /**
     * `expected_last_heartbeat` does not match the stored heartbeat, so another worker may hold the
     * lease.
     */
    fun asHeartbeatPreconditionFailed(): BetaHeartbeatPreconditionFailedErrorDetails =
        heartbeatPreconditionFailed.getOrThrow("heartbeatPreconditionFailed")

    /**
     * `metadata` has too many keys, a key or value that is too long, or a reserved key. The message
     * names each problem.
     */
    fun asInvalidMetadata(): BetaInvalidMetadataErrorDetails =
        invalidMetadata.getOrThrow("invalidMetadata")

    /**
     * Anthropic has temporarily disabled billing changes for the user account the credential
     * belongs to. Contact support.
     */
    fun asBillingFrozen(): BetaBillingFrozenErrorDetails = billingFrozen.getOrThrow("billingFrozen")

    /**
     * The organization cannot retrieve, set or delete spend limits with an Admin API credential.
     */
    fun asSpendLimitsAdminApiWritesNotEnabled():
        BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails =
        spendLimitsAdminApiWritesNotEnabled.getOrThrow("spendLimitsAdminApiWritesNotEnabled")

    /**
     * The request's `Content-Type` header names a charset other than UTF-8, ISO-8859-1 or US-ASCII.
     * Send the request with no charset, or with one of these.
     */
    fun asUnsupportedCharset(): BetaUnsupportedCharsetErrorDetails =
        unsupportedCharset.getOrThrow("unsupportedCharset")

    /**
     * The marketplace already holds this name. `plugin_id` is the Plugin that holds it, and can be
     * absent.
     */
    fun asPluginNameTaken(): BetaPluginNameTakenErrorDetails =
        pluginNameTaken.getOrThrow("pluginNameTaken")

    /**
     * One of the Plugin's skills has the name of an organization skill. `skill_name` is that name.
     */
    fun asSkillNameTaken(): BetaSkillNameTakenErrorDetails =
        skillNameTaken.getOrThrow("skillNameTaken")

    /**
     * The Plugin version was stored but is not yet usable in claude.ai. `plugin_id` and
     * `plugin_version_id` identify what was stored.
     */
    fun asRegistrationPending(): BetaRegistrationPendingErrorDetails =
        registrationPending.getOrThrow("registrationPending")

    /** The version in `plugin_version_id` did not pass its content scan, so it cannot be served. */
    fun asScanFailed(): BetaScanFailedErrorDetails = scanFailed.getOrThrow("scanFailed")

    /**
     * The content scan of the version in `plugin_version_id` is still running. Retry once the scan
     * finishes.
     */
    fun asScanPending(): BetaScanPendingErrorDetails = scanPending.getOrThrow("scanPending")

    /**
     * The request set the channel up, and `settings` was then refused. The channel stays set up
     * with every setting unset.
     */
    fun asSlackChannelSetUpSettingsRefused(): BetaSlackChannelSetUpSettingsRefusedErrorDetails =
        slackChannelSetUpSettingsRefused.getOrThrow("slackChannelSetUpSettingsRefused")

    /**
     * The API key lacks the scope the request needs: `read:claude_tag` for a read,
     * `write:claude_tag` for a change.
     */
    fun asApiKeyScopeMissing(): BetaApiKeyScopeMissingErrorDetails =
        apiKeyScopeMissing.getOrThrow("apiKeyScopeMissing")

    /** Changes through the Claude Tag API are not enabled for the organization. */
    fun asClaudeTagWritesNotEnabled(): BetaClaudeTagWritesNotEnabledErrorDetails =
        claudeTagWritesNotEnabled.getOrThrow("claudeTagWritesNotEnabled")

    /** The organization's compliance configuration does not let Claude Tag store anything new. */
    fun asComplianceConfigurationRestricted(): BetaComplianceConfigurationRestrictedErrorDetails =
        complianceConfigurationRestricted.getOrThrow("complianceConfigurationRestricted")

    /** The credential is not a scoped API key that is bound at the organization level. */
    fun asOrganizationLevelScopedApiKeyRequired():
        BetaOrganizationLevelScopedApiKeyRequiredErrorDetails =
        organizationLevelScopedApiKeyRequired.getOrThrow("organizationLevelScopedApiKeyRequired")

    /** `settings.environment_id` names no Environment within the organization's reach. */
    fun asEnvironmentNotFound(): BetaEnvironmentNotFoundErrorDetails =
        environmentNotFound.getOrThrow("environmentNotFound")

    /**
     * `settings.environment_id` names no Environment within the organization's reach, and the
     * request had set the channel up by then. The channel stays set up with every setting unset.
     */
    fun asSlackChannelSetUpEnvironmentNotFound():
        BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails =
        slackChannelSetUpEnvironmentNotFound.getOrThrow("slackChannelSetUpEnvironmentNotFound")

    /**
     * The request set the channel up, and something else then changed the channel or removed it.
     * Nothing of `settings` was stored.
     */
    fun asSlackChannelSetUpSettingsConflict(): BetaSlackChannelSetUpSettingsConflictErrorDetails =
        slackChannelSetUpSettingsConflict.getOrThrow("slackChannelSetUpSettingsConflict")

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
     * Optional<String> result = errorDetails.accept(new ErrorDetails.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitAvailableToolsLimitExceeded(AvailableToolsLimitExceededErrorDetails availableToolsLimitExceeded) {
     *         return Optional.of(availableToolsLimitExceeded.toString());
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
            availableToolsLimitExceeded != null ->
                visitor.visitAvailableToolsLimitExceeded(availableToolsLimitExceeded)
            claudeCodeKeyCreatorNotMember != null ->
                visitor.visitClaudeCodeKeyCreatorNotMember(claudeCodeKeyCreatorNotMember)
            claudeCodeVersionTooOld != null ->
                visitor.visitClaudeCodeVersionTooOld(claudeCodeVersionTooOld)
            cmekKeyDisabled != null -> visitor.visitCmekKeyDisabled(cmekKeyDisabled)
            cmekKeyNetworkBlocked != null ->
                visitor.visitCmekKeyNetworkBlocked(cmekKeyNetworkBlocked)
            compactionBlockAmbiguous != null ->
                visitor.visitCompactionBlockAmbiguous(compactionBlockAmbiguous)
            compactionBlockMisplaced != null ->
                visitor.visitCompactionBlockMisplaced(compactionBlockMisplaced)
            compactionContentMismatch != null ->
                visitor.visitCompactionContentMismatch(compactionContentMismatch)
            compactionIncompleteTurn != null ->
                visitor.visitCompactionIncompleteTurn(compactionIncompleteTurn)
            compactionNothingToSummarize != null ->
                visitor.visitCompactionNothingToSummarize(compactionNothingToSummarize)
            compactionSignatureInvalid != null ->
                visitor.visitCompactionSignatureInvalid(compactionSignatureInvalid)
            compactionTooManyToolReferences != null ->
                visitor.visitCompactionTooManyToolReferences(compactionTooManyToolReferences)
            compactionToolChangesMismatch != null ->
                visitor.visitCompactionToolChangesMismatch(compactionToolChangesMismatch)
            organizationOnHold != null -> visitor.visitOrganizationOnHold(organizationOnHold)
            requestBodyEncodingInvalid != null ->
                visitor.visitRequestBodyEncodingInvalid(requestBodyEncodingInvalid)
            requestBodyEncodingUnsupported != null ->
                visitor.visitRequestBodyEncodingUnsupported(requestBodyEncodingUnsupported)
            threadAlreadyContinued != null ->
                visitor.visitThreadAlreadyContinued(threadAlreadyContinued)
            threadContinueInFlight != null ->
                visitor.visitThreadContinueInFlight(threadContinueInFlight)
            threadFingerprintMismatch != null ->
                visitor.visitThreadFingerprintMismatch(threadFingerprintMismatch)
            threadUnsupportedRequest != null ->
                visitor.visitThreadUnsupportedRequest(threadUnsupportedRequest)
            toolNameConflict != null -> visitor.visitToolNameConflict(toolNameConflict)
            toolReferenceUnresolved != null ->
                visitor.visitToolReferenceUnresolved(toolReferenceUnresolved)
            threadNotFound != null -> visitor.visitThreadNotFound(threadNotFound)
            skillReadBlockedUndecryptable != null ->
                visitor.visitSkillReadBlockedUndecryptable(skillReadBlockedUndecryptable)
            requestBodyTooLargeAfterDecoding != null ->
                visitor.visitRequestBodyTooLargeAfterDecoding(requestBodyTooLargeAfterDecoding)
            enforcedSpendLimitReached != null ->
                visitor.visitEnforcedSpendLimitReached(enforcedSpendLimitReached)
            rampLimitExceeded != null -> visitor.visitRampLimitExceeded(rampLimitExceeded)
            cmekKeyUnreachable != null -> visitor.visitCmekKeyUnreachable(cmekKeyUnreachable)
            compactionUnavailable != null ->
                visitor.visitCompactionUnavailable(compactionUnavailable)
            storageQuotaExceeded != null -> visitor.visitStorageQuotaExceeded(storageQuotaExceeded)
            fileNotDownloadable != null -> visitor.visitFileNotDownloadable(fileNotDownloadable)
            fileExpired != null -> visitor.visitFileExpired(fileExpired)
            cmekContextUnavailable != null ->
                visitor.visitCmekContextUnavailable(cmekContextUnavailable)
            orgApiKeyLimitExceeded != null ->
                visitor.visitOrgApiKeyLimitExceeded(orgApiKeyLimitExceeded)
            workspaceApiKeyLimitExceeded != null ->
                visitor.visitWorkspaceApiKeyLimitExceeded(workspaceApiKeyLimitExceeded)
            userProfileNotFound != null -> visitor.visitUserProfileNotFound(userProfileNotFound)
            externalKeyInWorkspacePolicy != null ->
                visitor.visitExternalKeyInWorkspacePolicy(externalKeyInWorkspacePolicy)
            alreadyMember != null -> visitor.visitAlreadyMember(alreadyMember)
            cannotInviteEmailsWithUnverifiedDomains != null ->
                visitor.visitCannotInviteEmailsWithUnverifiedDomains(
                    cannotInviteEmailsWithUnverifiedDomains
                )
            emailDomainNotAllowed != null ->
                visitor.visitEmailDomainNotAllowed(emailDomainNotAllowed)
            inviteAlreadyExists != null -> visitor.visitInviteAlreadyExists(inviteAlreadyExists)
            orgMemberLimitExceeded != null ->
                visitor.visitOrgMemberLimitExceeded(orgMemberLimitExceeded)
            rbacGroupGrantsOwnerAccess != null ->
                visitor.visitRbacGroupGrantsOwnerAccess(rbacGroupGrantsOwnerAccess)
            rbacGroupOwnerAccessUnconfirmed != null ->
                visitor.visitRbacGroupOwnerAccessUnconfirmed(rbacGroupOwnerAccessUnconfirmed)
            cannotRemoveLastMember != null ->
                visitor.visitCannotRemoveLastMember(cannotRemoveLastMember)
            concurrentMembershipChange != null ->
                visitor.visitConcurrentMembershipChange(concurrentMembershipChange)
            externalKeyValidationFailed != null ->
                visitor.visitExternalKeyValidationFailed(externalKeyValidationFailed)
            externalKeyValidationTimedOut != null ->
                visitor.visitExternalKeyValidationTimedOut(externalKeyValidationTimedOut)
            keyProfileMismatch != null -> visitor.visitKeyProfileMismatch(keyProfileMismatch)
            workspacePolicyViolation != null ->
                visitor.visitWorkspacePolicyViolation(workspacePolicyViolation)
            externalKeyChanged != null -> visitor.visitExternalKeyChanged(externalKeyChanged)
            workspaceMemberLimitExceeded != null ->
                visitor.visitWorkspaceMemberLimitExceeded(workspaceMemberLimitExceeded)
            organizationTypeNotSupported != null ->
                visitor.visitOrganizationTypeNotSupported(organizationTypeNotSupported)
            environmentCreateDisabled != null ->
                visitor.visitEnvironmentCreateDisabled(environmentCreateDisabled)
            environmentArchived != null -> visitor.visitEnvironmentArchived(environmentArchived)
            invalidConfigTypeChange != null ->
                visitor.visitInvalidConfigTypeChange(invalidConfigTypeChange)
            environmentUpdateDisabled != null ->
                visitor.visitEnvironmentUpdateDisabled(environmentUpdateDisabled)
            heartbeatPreconditionFailed != null ->
                visitor.visitHeartbeatPreconditionFailed(heartbeatPreconditionFailed)
            invalidMetadata != null -> visitor.visitInvalidMetadata(invalidMetadata)
            billingFrozen != null -> visitor.visitBillingFrozen(billingFrozen)
            spendLimitsAdminApiWritesNotEnabled != null ->
                visitor.visitSpendLimitsAdminApiWritesNotEnabled(
                    spendLimitsAdminApiWritesNotEnabled
                )
            unsupportedCharset != null -> visitor.visitUnsupportedCharset(unsupportedCharset)
            pluginNameTaken != null -> visitor.visitPluginNameTaken(pluginNameTaken)
            skillNameTaken != null -> visitor.visitSkillNameTaken(skillNameTaken)
            registrationPending != null -> visitor.visitRegistrationPending(registrationPending)
            scanFailed != null -> visitor.visitScanFailed(scanFailed)
            scanPending != null -> visitor.visitScanPending(scanPending)
            slackChannelSetUpSettingsRefused != null ->
                visitor.visitSlackChannelSetUpSettingsRefused(slackChannelSetUpSettingsRefused)
            apiKeyScopeMissing != null -> visitor.visitApiKeyScopeMissing(apiKeyScopeMissing)
            claudeTagWritesNotEnabled != null ->
                visitor.visitClaudeTagWritesNotEnabled(claudeTagWritesNotEnabled)
            complianceConfigurationRestricted != null ->
                visitor.visitComplianceConfigurationRestricted(complianceConfigurationRestricted)
            organizationLevelScopedApiKeyRequired != null ->
                visitor.visitOrganizationLevelScopedApiKeyRequired(
                    organizationLevelScopedApiKeyRequired
                )
            environmentNotFound != null -> visitor.visitEnvironmentNotFound(environmentNotFound)
            slackChannelSetUpEnvironmentNotFound != null ->
                visitor.visitSlackChannelSetUpEnvironmentNotFound(
                    slackChannelSetUpEnvironmentNotFound
                )
            slackChannelSetUpSettingsConflict != null ->
                visitor.visitSlackChannelSetUpSettingsConflict(slackChannelSetUpSettingsConflict)
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
    fun validate(): ErrorDetails = apply {
        if (validated) {
            return@apply
        }

        when {
            availableToolsLimitExceeded != null -> availableToolsLimitExceeded.validate()
            claudeCodeKeyCreatorNotMember != null -> claudeCodeKeyCreatorNotMember.validate()
            claudeCodeVersionTooOld != null -> claudeCodeVersionTooOld.validate()
            cmekKeyDisabled != null -> cmekKeyDisabled.validate()
            cmekKeyNetworkBlocked != null -> cmekKeyNetworkBlocked.validate()
            compactionBlockAmbiguous != null -> compactionBlockAmbiguous.validate()
            compactionBlockMisplaced != null -> compactionBlockMisplaced.validate()
            compactionContentMismatch != null -> compactionContentMismatch.validate()
            compactionIncompleteTurn != null -> compactionIncompleteTurn.validate()
            compactionNothingToSummarize != null -> compactionNothingToSummarize.validate()
            compactionSignatureInvalid != null -> compactionSignatureInvalid.validate()
            compactionTooManyToolReferences != null -> compactionTooManyToolReferences.validate()
            compactionToolChangesMismatch != null -> compactionToolChangesMismatch.validate()
            organizationOnHold != null -> organizationOnHold.validate()
            requestBodyEncodingInvalid != null -> requestBodyEncodingInvalid.validate()
            requestBodyEncodingUnsupported != null -> requestBodyEncodingUnsupported.validate()
            threadAlreadyContinued != null -> threadAlreadyContinued.validate()
            threadContinueInFlight != null -> threadContinueInFlight.validate()
            threadFingerprintMismatch != null -> threadFingerprintMismatch.validate()
            threadUnsupportedRequest != null -> threadUnsupportedRequest.validate()
            toolNameConflict != null -> toolNameConflict.validate()
            toolReferenceUnresolved != null -> toolReferenceUnresolved.validate()
            threadNotFound != null -> threadNotFound.validate()
            skillReadBlockedUndecryptable != null -> skillReadBlockedUndecryptable.validate()
            requestBodyTooLargeAfterDecoding != null -> requestBodyTooLargeAfterDecoding.validate()
            enforcedSpendLimitReached != null -> enforcedSpendLimitReached.validate()
            rampLimitExceeded != null -> rampLimitExceeded.validate()
            cmekKeyUnreachable != null -> cmekKeyUnreachable.validate()
            compactionUnavailable != null -> compactionUnavailable.validate()
            storageQuotaExceeded != null -> storageQuotaExceeded.validate()
            fileNotDownloadable != null -> fileNotDownloadable.validate()
            fileExpired != null -> fileExpired.validate()
            cmekContextUnavailable != null -> cmekContextUnavailable.validate()
            orgApiKeyLimitExceeded != null -> orgApiKeyLimitExceeded.validate()
            workspaceApiKeyLimitExceeded != null -> workspaceApiKeyLimitExceeded.validate()
            userProfileNotFound != null -> userProfileNotFound.validate()
            externalKeyInWorkspacePolicy != null -> externalKeyInWorkspacePolicy.validate()
            alreadyMember != null -> alreadyMember.validate()
            cannotInviteEmailsWithUnverifiedDomains != null ->
                cannotInviteEmailsWithUnverifiedDomains.validate()
            emailDomainNotAllowed != null -> emailDomainNotAllowed.validate()
            inviteAlreadyExists != null -> inviteAlreadyExists.validate()
            orgMemberLimitExceeded != null -> orgMemberLimitExceeded.validate()
            rbacGroupGrantsOwnerAccess != null -> rbacGroupGrantsOwnerAccess.validate()
            rbacGroupOwnerAccessUnconfirmed != null -> rbacGroupOwnerAccessUnconfirmed.validate()
            cannotRemoveLastMember != null -> cannotRemoveLastMember.validate()
            concurrentMembershipChange != null -> concurrentMembershipChange.validate()
            externalKeyValidationFailed != null -> externalKeyValidationFailed.validate()
            externalKeyValidationTimedOut != null -> externalKeyValidationTimedOut.validate()
            keyProfileMismatch != null -> keyProfileMismatch.validate()
            workspacePolicyViolation != null -> workspacePolicyViolation.validate()
            externalKeyChanged != null -> externalKeyChanged.validate()
            workspaceMemberLimitExceeded != null -> workspaceMemberLimitExceeded.validate()
            organizationTypeNotSupported != null -> organizationTypeNotSupported.validate()
            environmentCreateDisabled != null -> environmentCreateDisabled.validate()
            environmentArchived != null -> environmentArchived.validate()
            invalidConfigTypeChange != null -> invalidConfigTypeChange.validate()
            environmentUpdateDisabled != null -> environmentUpdateDisabled.validate()
            heartbeatPreconditionFailed != null -> heartbeatPreconditionFailed.validate()
            invalidMetadata != null -> invalidMetadata.validate()
            billingFrozen != null -> billingFrozen.validate()
            spendLimitsAdminApiWritesNotEnabled != null ->
                spendLimitsAdminApiWritesNotEnabled.validate()
            unsupportedCharset != null -> unsupportedCharset.validate()
            pluginNameTaken != null -> pluginNameTaken.validate()
            skillNameTaken != null -> skillNameTaken.validate()
            registrationPending != null -> registrationPending.validate()
            scanFailed != null -> scanFailed.validate()
            scanPending != null -> scanPending.validate()
            slackChannelSetUpSettingsRefused != null -> slackChannelSetUpSettingsRefused.validate()
            apiKeyScopeMissing != null -> apiKeyScopeMissing.validate()
            claudeTagWritesNotEnabled != null -> claudeTagWritesNotEnabled.validate()
            complianceConfigurationRestricted != null ->
                complianceConfigurationRestricted.validate()
            organizationLevelScopedApiKeyRequired != null ->
                organizationLevelScopedApiKeyRequired.validate()
            environmentNotFound != null -> environmentNotFound.validate()
            slackChannelSetUpEnvironmentNotFound != null ->
                slackChannelSetUpEnvironmentNotFound.validate()
            slackChannelSetUpSettingsConflict != null ->
                slackChannelSetUpSettingsConflict.validate()
            else -> throw AnthropicInvalidDataException("Unknown ErrorDetails: $_json")
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
        when {
            availableToolsLimitExceeded != null -> availableToolsLimitExceeded.validity()
            claudeCodeKeyCreatorNotMember != null -> claudeCodeKeyCreatorNotMember.validity()
            claudeCodeVersionTooOld != null -> claudeCodeVersionTooOld.validity()
            cmekKeyDisabled != null -> cmekKeyDisabled.validity()
            cmekKeyNetworkBlocked != null -> cmekKeyNetworkBlocked.validity()
            compactionBlockAmbiguous != null -> compactionBlockAmbiguous.validity()
            compactionBlockMisplaced != null -> compactionBlockMisplaced.validity()
            compactionContentMismatch != null -> compactionContentMismatch.validity()
            compactionIncompleteTurn != null -> compactionIncompleteTurn.validity()
            compactionNothingToSummarize != null -> compactionNothingToSummarize.validity()
            compactionSignatureInvalid != null -> compactionSignatureInvalid.validity()
            compactionTooManyToolReferences != null -> compactionTooManyToolReferences.validity()
            compactionToolChangesMismatch != null -> compactionToolChangesMismatch.validity()
            organizationOnHold != null -> organizationOnHold.validity()
            requestBodyEncodingInvalid != null -> requestBodyEncodingInvalid.validity()
            requestBodyEncodingUnsupported != null -> requestBodyEncodingUnsupported.validity()
            threadAlreadyContinued != null -> threadAlreadyContinued.validity()
            threadContinueInFlight != null -> threadContinueInFlight.validity()
            threadFingerprintMismatch != null -> threadFingerprintMismatch.validity()
            threadUnsupportedRequest != null -> threadUnsupportedRequest.validity()
            toolNameConflict != null -> toolNameConflict.validity()
            toolReferenceUnresolved != null -> toolReferenceUnresolved.validity()
            threadNotFound != null -> threadNotFound.validity()
            skillReadBlockedUndecryptable != null -> skillReadBlockedUndecryptable.validity()
            requestBodyTooLargeAfterDecoding != null -> requestBodyTooLargeAfterDecoding.validity()
            enforcedSpendLimitReached != null -> enforcedSpendLimitReached.validity()
            rampLimitExceeded != null -> rampLimitExceeded.validity()
            cmekKeyUnreachable != null -> cmekKeyUnreachable.validity()
            compactionUnavailable != null -> compactionUnavailable.validity()
            storageQuotaExceeded != null -> storageQuotaExceeded.validity()
            fileNotDownloadable != null -> fileNotDownloadable.validity()
            fileExpired != null -> fileExpired.validity()
            cmekContextUnavailable != null -> cmekContextUnavailable.validity()
            orgApiKeyLimitExceeded != null -> orgApiKeyLimitExceeded.validity()
            workspaceApiKeyLimitExceeded != null -> workspaceApiKeyLimitExceeded.validity()
            userProfileNotFound != null -> userProfileNotFound.validity()
            externalKeyInWorkspacePolicy != null -> externalKeyInWorkspacePolicy.validity()
            alreadyMember != null -> alreadyMember.validity()
            cannotInviteEmailsWithUnverifiedDomains != null ->
                cannotInviteEmailsWithUnverifiedDomains.validity()
            emailDomainNotAllowed != null -> emailDomainNotAllowed.validity()
            inviteAlreadyExists != null -> inviteAlreadyExists.validity()
            orgMemberLimitExceeded != null -> orgMemberLimitExceeded.validity()
            rbacGroupGrantsOwnerAccess != null -> rbacGroupGrantsOwnerAccess.validity()
            rbacGroupOwnerAccessUnconfirmed != null -> rbacGroupOwnerAccessUnconfirmed.validity()
            cannotRemoveLastMember != null -> cannotRemoveLastMember.validity()
            concurrentMembershipChange != null -> concurrentMembershipChange.validity()
            externalKeyValidationFailed != null -> externalKeyValidationFailed.validity()
            externalKeyValidationTimedOut != null -> externalKeyValidationTimedOut.validity()
            keyProfileMismatch != null -> keyProfileMismatch.validity()
            workspacePolicyViolation != null -> workspacePolicyViolation.validity()
            externalKeyChanged != null -> externalKeyChanged.validity()
            workspaceMemberLimitExceeded != null -> workspaceMemberLimitExceeded.validity()
            organizationTypeNotSupported != null -> organizationTypeNotSupported.validity()
            environmentCreateDisabled != null -> environmentCreateDisabled.validity()
            environmentArchived != null -> environmentArchived.validity()
            invalidConfigTypeChange != null -> invalidConfigTypeChange.validity()
            environmentUpdateDisabled != null -> environmentUpdateDisabled.validity()
            heartbeatPreconditionFailed != null -> heartbeatPreconditionFailed.validity()
            invalidMetadata != null -> invalidMetadata.validity()
            billingFrozen != null -> billingFrozen.validity()
            spendLimitsAdminApiWritesNotEnabled != null ->
                spendLimitsAdminApiWritesNotEnabled.validity()
            unsupportedCharset != null -> unsupportedCharset.validity()
            pluginNameTaken != null -> pluginNameTaken.validity()
            skillNameTaken != null -> skillNameTaken.validity()
            registrationPending != null -> registrationPending.validity()
            scanFailed != null -> scanFailed.validity()
            scanPending != null -> scanPending.validity()
            slackChannelSetUpSettingsRefused != null -> slackChannelSetUpSettingsRefused.validity()
            apiKeyScopeMissing != null -> apiKeyScopeMissing.validity()
            claudeTagWritesNotEnabled != null -> claudeTagWritesNotEnabled.validity()
            complianceConfigurationRestricted != null ->
                complianceConfigurationRestricted.validity()
            organizationLevelScopedApiKeyRequired != null ->
                organizationLevelScopedApiKeyRequired.validity()
            environmentNotFound != null -> environmentNotFound.validity()
            slackChannelSetUpEnvironmentNotFound != null ->
                slackChannelSetUpEnvironmentNotFound.validity()
            slackChannelSetUpSettingsConflict != null ->
                slackChannelSetUpSettingsConflict.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ErrorDetails &&
            availableToolsLimitExceeded == other.availableToolsLimitExceeded &&
            claudeCodeKeyCreatorNotMember == other.claudeCodeKeyCreatorNotMember &&
            claudeCodeVersionTooOld == other.claudeCodeVersionTooOld &&
            cmekKeyDisabled == other.cmekKeyDisabled &&
            cmekKeyNetworkBlocked == other.cmekKeyNetworkBlocked &&
            compactionBlockAmbiguous == other.compactionBlockAmbiguous &&
            compactionBlockMisplaced == other.compactionBlockMisplaced &&
            compactionContentMismatch == other.compactionContentMismatch &&
            compactionIncompleteTurn == other.compactionIncompleteTurn &&
            compactionNothingToSummarize == other.compactionNothingToSummarize &&
            compactionSignatureInvalid == other.compactionSignatureInvalid &&
            compactionTooManyToolReferences == other.compactionTooManyToolReferences &&
            compactionToolChangesMismatch == other.compactionToolChangesMismatch &&
            organizationOnHold == other.organizationOnHold &&
            requestBodyEncodingInvalid == other.requestBodyEncodingInvalid &&
            requestBodyEncodingUnsupported == other.requestBodyEncodingUnsupported &&
            threadAlreadyContinued == other.threadAlreadyContinued &&
            threadContinueInFlight == other.threadContinueInFlight &&
            threadFingerprintMismatch == other.threadFingerprintMismatch &&
            threadUnsupportedRequest == other.threadUnsupportedRequest &&
            toolNameConflict == other.toolNameConflict &&
            toolReferenceUnresolved == other.toolReferenceUnresolved &&
            threadNotFound == other.threadNotFound &&
            skillReadBlockedUndecryptable == other.skillReadBlockedUndecryptable &&
            requestBodyTooLargeAfterDecoding == other.requestBodyTooLargeAfterDecoding &&
            enforcedSpendLimitReached == other.enforcedSpendLimitReached &&
            rampLimitExceeded == other.rampLimitExceeded &&
            cmekKeyUnreachable == other.cmekKeyUnreachable &&
            compactionUnavailable == other.compactionUnavailable &&
            storageQuotaExceeded == other.storageQuotaExceeded &&
            fileNotDownloadable == other.fileNotDownloadable &&
            fileExpired == other.fileExpired &&
            cmekContextUnavailable == other.cmekContextUnavailable &&
            orgApiKeyLimitExceeded == other.orgApiKeyLimitExceeded &&
            workspaceApiKeyLimitExceeded == other.workspaceApiKeyLimitExceeded &&
            userProfileNotFound == other.userProfileNotFound &&
            externalKeyInWorkspacePolicy == other.externalKeyInWorkspacePolicy &&
            alreadyMember == other.alreadyMember &&
            cannotInviteEmailsWithUnverifiedDomains ==
                other.cannotInviteEmailsWithUnverifiedDomains &&
            emailDomainNotAllowed == other.emailDomainNotAllowed &&
            inviteAlreadyExists == other.inviteAlreadyExists &&
            orgMemberLimitExceeded == other.orgMemberLimitExceeded &&
            rbacGroupGrantsOwnerAccess == other.rbacGroupGrantsOwnerAccess &&
            rbacGroupOwnerAccessUnconfirmed == other.rbacGroupOwnerAccessUnconfirmed &&
            cannotRemoveLastMember == other.cannotRemoveLastMember &&
            concurrentMembershipChange == other.concurrentMembershipChange &&
            externalKeyValidationFailed == other.externalKeyValidationFailed &&
            externalKeyValidationTimedOut == other.externalKeyValidationTimedOut &&
            keyProfileMismatch == other.keyProfileMismatch &&
            workspacePolicyViolation == other.workspacePolicyViolation &&
            externalKeyChanged == other.externalKeyChanged &&
            workspaceMemberLimitExceeded == other.workspaceMemberLimitExceeded &&
            organizationTypeNotSupported == other.organizationTypeNotSupported &&
            environmentCreateDisabled == other.environmentCreateDisabled &&
            environmentArchived == other.environmentArchived &&
            invalidConfigTypeChange == other.invalidConfigTypeChange &&
            environmentUpdateDisabled == other.environmentUpdateDisabled &&
            heartbeatPreconditionFailed == other.heartbeatPreconditionFailed &&
            invalidMetadata == other.invalidMetadata &&
            billingFrozen == other.billingFrozen &&
            spendLimitsAdminApiWritesNotEnabled == other.spendLimitsAdminApiWritesNotEnabled &&
            unsupportedCharset == other.unsupportedCharset &&
            pluginNameTaken == other.pluginNameTaken &&
            skillNameTaken == other.skillNameTaken &&
            registrationPending == other.registrationPending &&
            scanFailed == other.scanFailed &&
            scanPending == other.scanPending &&
            slackChannelSetUpSettingsRefused == other.slackChannelSetUpSettingsRefused &&
            apiKeyScopeMissing == other.apiKeyScopeMissing &&
            claudeTagWritesNotEnabled == other.claudeTagWritesNotEnabled &&
            complianceConfigurationRestricted == other.complianceConfigurationRestricted &&
            organizationLevelScopedApiKeyRequired == other.organizationLevelScopedApiKeyRequired &&
            environmentNotFound == other.environmentNotFound &&
            slackChannelSetUpEnvironmentNotFound == other.slackChannelSetUpEnvironmentNotFound &&
            slackChannelSetUpSettingsConflict == other.slackChannelSetUpSettingsConflict
    }

    override fun hashCode(): Int =
        Objects.hash(
            availableToolsLimitExceeded,
            claudeCodeKeyCreatorNotMember,
            claudeCodeVersionTooOld,
            cmekKeyDisabled,
            cmekKeyNetworkBlocked,
            compactionBlockAmbiguous,
            compactionBlockMisplaced,
            compactionContentMismatch,
            compactionIncompleteTurn,
            compactionNothingToSummarize,
            compactionSignatureInvalid,
            compactionTooManyToolReferences,
            compactionToolChangesMismatch,
            organizationOnHold,
            requestBodyEncodingInvalid,
            requestBodyEncodingUnsupported,
            threadAlreadyContinued,
            threadContinueInFlight,
            threadFingerprintMismatch,
            threadUnsupportedRequest,
            toolNameConflict,
            toolReferenceUnresolved,
            threadNotFound,
            skillReadBlockedUndecryptable,
            requestBodyTooLargeAfterDecoding,
            enforcedSpendLimitReached,
            rampLimitExceeded,
            cmekKeyUnreachable,
            compactionUnavailable,
            storageQuotaExceeded,
            fileNotDownloadable,
            fileExpired,
            cmekContextUnavailable,
            orgApiKeyLimitExceeded,
            workspaceApiKeyLimitExceeded,
            userProfileNotFound,
            externalKeyInWorkspacePolicy,
            alreadyMember,
            cannotInviteEmailsWithUnverifiedDomains,
            emailDomainNotAllowed,
            inviteAlreadyExists,
            orgMemberLimitExceeded,
            rbacGroupGrantsOwnerAccess,
            rbacGroupOwnerAccessUnconfirmed,
            cannotRemoveLastMember,
            concurrentMembershipChange,
            externalKeyValidationFailed,
            externalKeyValidationTimedOut,
            keyProfileMismatch,
            workspacePolicyViolation,
            externalKeyChanged,
            workspaceMemberLimitExceeded,
            organizationTypeNotSupported,
            environmentCreateDisabled,
            environmentArchived,
            invalidConfigTypeChange,
            environmentUpdateDisabled,
            heartbeatPreconditionFailed,
            invalidMetadata,
            billingFrozen,
            spendLimitsAdminApiWritesNotEnabled,
            unsupportedCharset,
            pluginNameTaken,
            skillNameTaken,
            registrationPending,
            scanFailed,
            scanPending,
            slackChannelSetUpSettingsRefused,
            apiKeyScopeMissing,
            claudeTagWritesNotEnabled,
            complianceConfigurationRestricted,
            organizationLevelScopedApiKeyRequired,
            environmentNotFound,
            slackChannelSetUpEnvironmentNotFound,
            slackChannelSetUpSettingsConflict,
        )

    override fun toString(): String =
        when {
            availableToolsLimitExceeded != null ->
                "ErrorDetails{availableToolsLimitExceeded=$availableToolsLimitExceeded}"
            claudeCodeKeyCreatorNotMember != null ->
                "ErrorDetails{claudeCodeKeyCreatorNotMember=$claudeCodeKeyCreatorNotMember}"
            claudeCodeVersionTooOld != null ->
                "ErrorDetails{claudeCodeVersionTooOld=$claudeCodeVersionTooOld}"
            cmekKeyDisabled != null -> "ErrorDetails{cmekKeyDisabled=$cmekKeyDisabled}"
            cmekKeyNetworkBlocked != null ->
                "ErrorDetails{cmekKeyNetworkBlocked=$cmekKeyNetworkBlocked}"
            compactionBlockAmbiguous != null ->
                "ErrorDetails{compactionBlockAmbiguous=$compactionBlockAmbiguous}"
            compactionBlockMisplaced != null ->
                "ErrorDetails{compactionBlockMisplaced=$compactionBlockMisplaced}"
            compactionContentMismatch != null ->
                "ErrorDetails{compactionContentMismatch=$compactionContentMismatch}"
            compactionIncompleteTurn != null ->
                "ErrorDetails{compactionIncompleteTurn=$compactionIncompleteTurn}"
            compactionNothingToSummarize != null ->
                "ErrorDetails{compactionNothingToSummarize=$compactionNothingToSummarize}"
            compactionSignatureInvalid != null ->
                "ErrorDetails{compactionSignatureInvalid=$compactionSignatureInvalid}"
            compactionTooManyToolReferences != null ->
                "ErrorDetails{compactionTooManyToolReferences=$compactionTooManyToolReferences}"
            compactionToolChangesMismatch != null ->
                "ErrorDetails{compactionToolChangesMismatch=$compactionToolChangesMismatch}"
            organizationOnHold != null -> "ErrorDetails{organizationOnHold=$organizationOnHold}"
            requestBodyEncodingInvalid != null ->
                "ErrorDetails{requestBodyEncodingInvalid=$requestBodyEncodingInvalid}"
            requestBodyEncodingUnsupported != null ->
                "ErrorDetails{requestBodyEncodingUnsupported=$requestBodyEncodingUnsupported}"
            threadAlreadyContinued != null ->
                "ErrorDetails{threadAlreadyContinued=$threadAlreadyContinued}"
            threadContinueInFlight != null ->
                "ErrorDetails{threadContinueInFlight=$threadContinueInFlight}"
            threadFingerprintMismatch != null ->
                "ErrorDetails{threadFingerprintMismatch=$threadFingerprintMismatch}"
            threadUnsupportedRequest != null ->
                "ErrorDetails{threadUnsupportedRequest=$threadUnsupportedRequest}"
            toolNameConflict != null -> "ErrorDetails{toolNameConflict=$toolNameConflict}"
            toolReferenceUnresolved != null ->
                "ErrorDetails{toolReferenceUnresolved=$toolReferenceUnresolved}"
            threadNotFound != null -> "ErrorDetails{threadNotFound=$threadNotFound}"
            skillReadBlockedUndecryptable != null ->
                "ErrorDetails{skillReadBlockedUndecryptable=$skillReadBlockedUndecryptable}"
            requestBodyTooLargeAfterDecoding != null ->
                "ErrorDetails{requestBodyTooLargeAfterDecoding=$requestBodyTooLargeAfterDecoding}"
            enforcedSpendLimitReached != null ->
                "ErrorDetails{enforcedSpendLimitReached=$enforcedSpendLimitReached}"
            rampLimitExceeded != null -> "ErrorDetails{rampLimitExceeded=$rampLimitExceeded}"
            cmekKeyUnreachable != null -> "ErrorDetails{cmekKeyUnreachable=$cmekKeyUnreachable}"
            compactionUnavailable != null ->
                "ErrorDetails{compactionUnavailable=$compactionUnavailable}"
            storageQuotaExceeded != null ->
                "ErrorDetails{storageQuotaExceeded=$storageQuotaExceeded}"
            fileNotDownloadable != null -> "ErrorDetails{fileNotDownloadable=$fileNotDownloadable}"
            fileExpired != null -> "ErrorDetails{fileExpired=$fileExpired}"
            cmekContextUnavailable != null ->
                "ErrorDetails{cmekContextUnavailable=$cmekContextUnavailable}"
            orgApiKeyLimitExceeded != null ->
                "ErrorDetails{orgApiKeyLimitExceeded=$orgApiKeyLimitExceeded}"
            workspaceApiKeyLimitExceeded != null ->
                "ErrorDetails{workspaceApiKeyLimitExceeded=$workspaceApiKeyLimitExceeded}"
            userProfileNotFound != null -> "ErrorDetails{userProfileNotFound=$userProfileNotFound}"
            externalKeyInWorkspacePolicy != null ->
                "ErrorDetails{externalKeyInWorkspacePolicy=$externalKeyInWorkspacePolicy}"
            alreadyMember != null -> "ErrorDetails{alreadyMember=$alreadyMember}"
            cannotInviteEmailsWithUnverifiedDomains != null ->
                "ErrorDetails{cannotInviteEmailsWithUnverifiedDomains=$cannotInviteEmailsWithUnverifiedDomains}"
            emailDomainNotAllowed != null ->
                "ErrorDetails{emailDomainNotAllowed=$emailDomainNotAllowed}"
            inviteAlreadyExists != null -> "ErrorDetails{inviteAlreadyExists=$inviteAlreadyExists}"
            orgMemberLimitExceeded != null ->
                "ErrorDetails{orgMemberLimitExceeded=$orgMemberLimitExceeded}"
            rbacGroupGrantsOwnerAccess != null ->
                "ErrorDetails{rbacGroupGrantsOwnerAccess=$rbacGroupGrantsOwnerAccess}"
            rbacGroupOwnerAccessUnconfirmed != null ->
                "ErrorDetails{rbacGroupOwnerAccessUnconfirmed=$rbacGroupOwnerAccessUnconfirmed}"
            cannotRemoveLastMember != null ->
                "ErrorDetails{cannotRemoveLastMember=$cannotRemoveLastMember}"
            concurrentMembershipChange != null ->
                "ErrorDetails{concurrentMembershipChange=$concurrentMembershipChange}"
            externalKeyValidationFailed != null ->
                "ErrorDetails{externalKeyValidationFailed=$externalKeyValidationFailed}"
            externalKeyValidationTimedOut != null ->
                "ErrorDetails{externalKeyValidationTimedOut=$externalKeyValidationTimedOut}"
            keyProfileMismatch != null -> "ErrorDetails{keyProfileMismatch=$keyProfileMismatch}"
            workspacePolicyViolation != null ->
                "ErrorDetails{workspacePolicyViolation=$workspacePolicyViolation}"
            externalKeyChanged != null -> "ErrorDetails{externalKeyChanged=$externalKeyChanged}"
            workspaceMemberLimitExceeded != null ->
                "ErrorDetails{workspaceMemberLimitExceeded=$workspaceMemberLimitExceeded}"
            organizationTypeNotSupported != null ->
                "ErrorDetails{organizationTypeNotSupported=$organizationTypeNotSupported}"
            environmentCreateDisabled != null ->
                "ErrorDetails{environmentCreateDisabled=$environmentCreateDisabled}"
            environmentArchived != null -> "ErrorDetails{environmentArchived=$environmentArchived}"
            invalidConfigTypeChange != null ->
                "ErrorDetails{invalidConfigTypeChange=$invalidConfigTypeChange}"
            environmentUpdateDisabled != null ->
                "ErrorDetails{environmentUpdateDisabled=$environmentUpdateDisabled}"
            heartbeatPreconditionFailed != null ->
                "ErrorDetails{heartbeatPreconditionFailed=$heartbeatPreconditionFailed}"
            invalidMetadata != null -> "ErrorDetails{invalidMetadata=$invalidMetadata}"
            billingFrozen != null -> "ErrorDetails{billingFrozen=$billingFrozen}"
            spendLimitsAdminApiWritesNotEnabled != null ->
                "ErrorDetails{spendLimitsAdminApiWritesNotEnabled=$spendLimitsAdminApiWritesNotEnabled}"
            unsupportedCharset != null -> "ErrorDetails{unsupportedCharset=$unsupportedCharset}"
            pluginNameTaken != null -> "ErrorDetails{pluginNameTaken=$pluginNameTaken}"
            skillNameTaken != null -> "ErrorDetails{skillNameTaken=$skillNameTaken}"
            registrationPending != null -> "ErrorDetails{registrationPending=$registrationPending}"
            scanFailed != null -> "ErrorDetails{scanFailed=$scanFailed}"
            scanPending != null -> "ErrorDetails{scanPending=$scanPending}"
            slackChannelSetUpSettingsRefused != null ->
                "ErrorDetails{slackChannelSetUpSettingsRefused=$slackChannelSetUpSettingsRefused}"
            apiKeyScopeMissing != null -> "ErrorDetails{apiKeyScopeMissing=$apiKeyScopeMissing}"
            claudeTagWritesNotEnabled != null ->
                "ErrorDetails{claudeTagWritesNotEnabled=$claudeTagWritesNotEnabled}"
            complianceConfigurationRestricted != null ->
                "ErrorDetails{complianceConfigurationRestricted=$complianceConfigurationRestricted}"
            organizationLevelScopedApiKeyRequired != null ->
                "ErrorDetails{organizationLevelScopedApiKeyRequired=$organizationLevelScopedApiKeyRequired}"
            environmentNotFound != null -> "ErrorDetails{environmentNotFound=$environmentNotFound}"
            slackChannelSetUpEnvironmentNotFound != null ->
                "ErrorDetails{slackChannelSetUpEnvironmentNotFound=$slackChannelSetUpEnvironmentNotFound}"
            slackChannelSetUpSettingsConflict != null ->
                "ErrorDetails{slackChannelSetUpSettingsConflict=$slackChannelSetUpSettingsConflict}"
            _json != null -> "ErrorDetails{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ErrorDetails")
        }

    companion object {

        /** The tools available after a message exceed a limit on their number or their size. */
        @JvmStatic
        fun ofAvailableToolsLimitExceeded(
            availableToolsLimitExceeded: AvailableToolsLimitExceededErrorDetails
        ) = ErrorDetails(availableToolsLimitExceeded = availableToolsLimitExceeded)

        /**
         * The API key belongs to the Claude Code workspace, and the user who created it is no
         * longer a member of the organization or of that workspace.
         */
        @JvmStatic
        fun ofClaudeCodeKeyCreatorNotMember(
            claudeCodeKeyCreatorNotMember: ClaudeCodeKeyCreatorNotMemberErrorDetails
        ) = ErrorDetails(claudeCodeKeyCreatorNotMember = claudeCodeKeyCreatorNotMember)

        /**
         * The request comes from a Claude Code version older than the minimum that the requested
         * model or the organization requires. Update Claude Code, then retry.
         */
        @JvmStatic
        fun ofClaudeCodeVersionTooOld(
            claudeCodeVersionTooOld: ClaudeCodeVersionTooOldErrorDetails
        ) = ErrorDetails(claudeCodeVersionTooOld = claudeCodeVersionTooOld)

        /**
         * The workspace's customer-managed encryption key is disabled or revoked. Re-enable the
         * key, then retry.
         */
        @JvmStatic
        fun ofCmekKeyDisabled(cmekKeyDisabled: CmekKeyDisabledErrorDetails) =
            ErrorDetails(cmekKeyDisabled = cmekKeyDisabled)

        /**
         * The key management service's network access controls blocked the request to the
         * workspace's customer-managed encryption key. The key itself may be healthy.
         */
        @JvmStatic
        fun ofCmekKeyNetworkBlocked(cmekKeyNetworkBlocked: CmekKeyNetworkBlockedErrorDetails) =
            ErrorDetails(cmekKeyNetworkBlocked = cmekKeyNetworkBlocked)

        /**
         * `messages` holds more than one `compaction` block with content. Send only the block the
         * conversation returned last.
         */
        @JvmStatic
        fun ofCompactionBlockAmbiguous(
            compactionBlockAmbiguous: CompactionBlockAmbiguousErrorDetails
        ) = ErrorDetails(compactionBlockAmbiguous = compactionBlockAmbiguous)

        /**
         * The `compaction` block is not the first block of the conversation, or is in a `system`
         * message.
         */
        @JvmStatic
        fun ofCompactionBlockMisplaced(
            compactionBlockMisplaced: CompactionBlockMisplacedErrorDetails
        ) = ErrorDetails(compactionBlockMisplaced = compactionBlockMisplaced)

        /** The `compaction` block's `content` differs from the text its `signature` recorded. */
        @JvmStatic
        fun ofCompactionContentMismatch(
            compactionContentMismatch: CompactionContentMismatchErrorDetails
        ) = ErrorDetails(compactionContentMismatch = compactionContentMismatch)

        /** The compaction request ends on an `assistant` turn that is not finished. */
        @JvmStatic
        fun ofCompactionIncompleteTurn(
            compactionIncompleteTurn: CompactionIncompleteTurnErrorDetails
        ) = ErrorDetails(compactionIncompleteTurn = compactionIncompleteTurn)

        /** `messages` holds no `user` or `assistant` content to summarize or adopt. */
        @JvmStatic
        fun ofCompactionNothingToSummarize(
            compactionNothingToSummarize: CompactionNothingToSummarizeErrorDetails
        ) = ErrorDetails(compactionNothingToSummarize = compactionNothingToSummarize)

        /** The `compaction` block's `signature` does not verify. */
        @JvmStatic
        fun ofCompactionSignatureInvalid(
            compactionSignatureInvalid: CompactionSignatureInvalidErrorDetails
        ) = ErrorDetails(compactionSignatureInvalid = compactionSignatureInvalid)

        /** The conversation references more distinct tools than a `compaction` block can record. */
        @JvmStatic
        fun ofCompactionTooManyToolReferences(
            compactionTooManyToolReferences: CompactionTooManyToolReferencesErrorDetails
        ) = ErrorDetails(compactionTooManyToolReferences = compactionTooManyToolReferences)

        /**
         * The `compaction` block's `tool_changes` differs from what was returned with the block.
         */
        @JvmStatic
        fun ofCompactionToolChangesMismatch(
            compactionToolChangesMismatch: CompactionToolChangesMismatchErrorDetails
        ) = ErrorDetails(compactionToolChangesMismatch = compactionToolChangesMismatch)

        /**
         * Anthropic has disabled the organization for trust and safety reasons. An organization
         * disabled for any other reason, such as billing, does not get this code. `appeal_url`,
         * when present, is where the organization can appeal.
         */
        @JvmStatic
        fun ofOrganizationOnHold(organizationOnHold: OrganizationOnHoldErrorDetails) =
            ErrorDetails(organizationOnHold = organizationOnHold)

        /** The request body is not valid for its `Content-Encoding`. */
        @JvmStatic
        fun ofRequestBodyEncodingInvalid(
            requestBodyEncodingInvalid: RequestBodyEncodingInvalidErrorDetails
        ) = ErrorDetails(requestBodyEncodingInvalid = requestBodyEncodingInvalid)

        /** The request's `Content-Encoding` is not supported. */
        @JvmStatic
        fun ofRequestBodyEncodingUnsupported(
            requestBodyEncodingUnsupported: RequestBodyEncodingUnsupportedErrorDetails
        ) = ErrorDetails(requestBodyEncodingUnsupported = requestBodyEncodingUnsupported)

        /**
         * The response that `previous_message_id` names has already been continued, and each
         * response can be continued only once. Continue from the Thread's most recent response
         * `id`.
         */
        @JvmStatic
        fun ofThreadAlreadyContinued(threadAlreadyContinued: ThreadAlreadyContinuedErrorDetails) =
            ErrorDetails(threadAlreadyContinued = threadAlreadyContinued)

        /**
         * Another request is still continuing from the same `previous_message_id`. Wait for it to
         * complete and continue from its response `id`, or replay the full conversation with
         * `thread: {"type": "create"}`.
         */
        @JvmStatic
        fun ofThreadContinueInFlight(threadContinueInFlight: ThreadContinueInFlightErrorDetails) =
            ErrorDetails(threadContinueInFlight = threadContinueInFlight)

        /**
         * A field that must stay the same across a Thread's turns differs from the value stored
         * with the Thread; the message names each such field. Send the stored values, or replay the
         * full conversation with `thread: {"type": "create"}` to change them.
         */
        @JvmStatic
        fun ofThreadFingerprintMismatch(
            threadFingerprintMismatch: ThreadFingerprintMismatchErrorDetails
        ) = ErrorDetails(threadFingerprintMismatch = threadFingerprintMismatch)

        /**
         * The request combines `thread` with a request field or an organization setting that
         * Threads do not support; `unsupported`, when present, names it. Send the full conversation
         * without `thread`, or change that field.
         */
        @JvmStatic
        fun ofThreadUnsupportedRequest(
            threadUnsupportedRequest: ThreadUnsupportedRequestErrorDetails
        ) = ErrorDetails(threadUnsupportedRequest = threadUnsupportedRequest)

        /**
         * A tool made available in `messages` takes a name that an available tool of another kind,
         * or of another MCP server, already holds.
         */
        @JvmStatic
        fun ofToolNameConflict(toolNameConflict: ToolNameConflictErrorDetails) =
            ErrorDetails(toolNameConflict = toolNameConflict)

        /**
         * A tool change or MCP tool listing references a tool or MCP server that is not defined at
         * that position in the conversation.
         */
        @JvmStatic
        fun ofToolReferenceUnresolved(
            toolReferenceUnresolved: ToolReferenceUnresolvedErrorDetails
        ) = ErrorDetails(toolReferenceUnresolved = toolReferenceUnresolved)

        /**
         * No usable Thread state was found for `previous_message_id`. Replay the full conversation
         * with `thread: {"type": "create"}` to start a new Thread.
         */
        @JvmStatic
        fun ofThreadNotFound(threadNotFound: ThreadNotFoundErrorDetails) =
            ErrorDetails(threadNotFound = threadNotFound)

        /**
         * A custom skill the request names is stored under an encryption key state the workspace
         * cannot currently decrypt.
         */
        @JvmStatic
        fun ofSkillReadBlockedUndecryptable(
            skillReadBlockedUndecryptable: SkillReadBlockedUndecryptableErrorDetails
        ) = ErrorDetails(skillReadBlockedUndecryptable = skillReadBlockedUndecryptable)

        /** The compressed request body exceeds the request size limit once decoded. */
        @JvmStatic
        fun ofRequestBodyTooLargeAfterDecoding(
            requestBodyTooLargeAfterDecoding: RequestBodyTooLargeAfterDecodingErrorDetails
        ) = ErrorDetails(requestBodyTooLargeAfterDecoding = requestBodyTooLargeAfterDecoding)

        /** The organization has crossed the monthly API usage threshold of its API tier. */
        @JvmStatic
        fun ofEnforcedSpendLimitReached(
            enforcedSpendLimitReached: EnforcedSpendLimitReachedErrorDetails
        ) = ErrorDetails(enforcedSpendLimitReached = enforcedSpendLimitReached)

        /**
         * The request would exceed a ramp limit, which caps how fast usage can grow. Retry after
         * the time in the `retry-after` header.
         */
        @JvmStatic
        fun ofRampLimitExceeded(rampLimitExceeded: RampLimitExceededErrorDetails) =
            ErrorDetails(rampLimitExceeded = rampLimitExceeded)

        /**
         * The workspace's customer-managed encryption key could not be reached. Retry the request;
         * if this continues, check that the key is available in your key management service, or
         * contact Anthropic support.
         */
        @JvmStatic
        fun ofCmekKeyUnreachable(cmekKeyUnreachable: CmekKeyUnreachableErrorDetails) =
            ErrorDetails(cmekKeyUnreachable = cmekKeyUnreachable)

        /**
         * The compaction could not be completed for a transient server reason. Retry the request.
         */
        @JvmStatic
        fun ofCompactionUnavailable(compactionUnavailable: CompactionUnavailableErrorDetails) =
            ErrorDetails(compactionUnavailable = compactionUnavailable)

        /**
         * The organization's file storage quota is exhausted. Delete files to free up space, then
         * retry the upload.
         */
        @JvmStatic
        fun ofStorageQuotaExceeded(storageQuotaExceeded: StorageQuotaExceededErrorDetails) =
            ErrorDetails(storageQuotaExceeded = storageQuotaExceeded)

        /**
         * The file has `downloadable: false`. Only files generated by a tool, such as the code
         * execution tool, can be downloaded.
         */
        @JvmStatic
        fun ofFileNotDownloadable(fileNotDownloadable: FileNotDownloadableErrorDetails) =
            ErrorDetails(fileNotDownloadable = fileNotDownloadable)

        /** The file's `expires_at` has passed, so its content is no longer available. */
        @JvmStatic
        fun ofFileExpired(fileExpired: FileExpiredErrorDetails) =
            ErrorDetails(fileExpired = fileExpired)

        /**
         * The workspace's encryption settings could not be read, so the request was refused. Retry
         * in a few minutes.
         */
        @JvmStatic
        fun ofCmekContextUnavailable(cmekContextUnavailable: CmekContextUnavailableErrorDetails) =
            ErrorDetails(cmekContextUnavailable = cmekContextUnavailable)

        /** The organization has reached its limit of active API keys. */
        @JvmStatic
        fun ofOrgApiKeyLimitExceeded(orgApiKeyLimitExceeded: OrgApiKeyLimitExceededErrorDetails) =
            ErrorDetails(orgApiKeyLimitExceeded = orgApiKeyLimitExceeded)

        /** The workspace has reached its limit of active API keys. */
        @JvmStatic
        fun ofWorkspaceApiKeyLimitExceeded(
            workspaceApiKeyLimitExceeded: WorkspaceApiKeyLimitExceededErrorDetails
        ) = ErrorDetails(workspaceApiKeyLimitExceeded = workspaceApiKeyLimitExceeded)

        /**
         * The request depends on a user profile that does not exist in the organization or is no
         * longer active. The request either names this user profile by ID, or names a resource,
         * such as an External Key, that is bound to it.
         */
        @JvmStatic
        fun ofUserProfileNotFound(userProfileNotFound: UserProfileNotFoundErrorDetails) =
            ErrorDetails(userProfileNotFound = userProfileNotFound)

        /**
         * The External Key can't be deleted or reconfigured while the organization's Workspace
         * Policy lists it as an allowed key.
         */
        @JvmStatic
        fun ofExternalKeyInWorkspacePolicy(
            externalKeyInWorkspacePolicy: ExternalKeyInWorkspacePolicyErrorDetails
        ) = ErrorDetails(externalKeyInWorkspacePolicy = externalKeyInWorkspacePolicy)

        /**
         * The email address already belongs to a user in the organization. The message names the
         * user.
         */
        @JvmStatic
        fun ofAlreadyMember(alreadyMember: AlreadyMemberErrorDetails) =
            ErrorDetails(alreadyMember = alreadyMember)

        /**
         * The email address's domain is not one of the verified domains of the organization's
         * parent organization.
         */
        @JvmStatic
        fun ofCannotInviteEmailsWithUnverifiedDomains(
            cannotInviteEmailsWithUnverifiedDomains:
                CannotInviteEmailsWithUnverifiedDomainsErrorDetails
        ) =
            ErrorDetails(
                cannotInviteEmailsWithUnverifiedDomains = cannotInviteEmailsWithUnverifiedDomains
            )

        /**
         * The organization restricts invites to an allowed list of email domains, and the list is
         * empty or does not include the email address's domain.
         */
        @JvmStatic
        fun ofEmailDomainNotAllowed(emailDomainNotAllowed: EmailDomainNotAllowedErrorDetails) =
            ErrorDetails(emailDomainNotAllowed = emailDomainNotAllowed)

        /** A pending invite already exists for the email address. The message names the invite. */
        @JvmStatic
        fun ofInviteAlreadyExists(inviteAlreadyExists: InviteAlreadyExistsErrorDetails) =
            ErrorDetails(inviteAlreadyExists = inviteAlreadyExists)

        /**
         * The organization has reached the member limit that Anthropic set for it. Pending invites
         * count toward the limit.
         */
        @JvmStatic
        fun ofOrgMemberLimitExceeded(orgMemberLimitExceeded: OrgMemberLimitExceededErrorDetails) =
            ErrorDetails(orgMemberLimitExceeded = orgMemberLimitExceeded)

        /**
         * A role attached to an RBAC Group in the request grants owner access, so this API does not
         * add members to that group (directly or through an invite), remove its members, or delete
         * it. Make the change in the organization's admin settings in Claude.
         */
        @JvmStatic
        fun ofRbacGroupGrantsOwnerAccess(
            rbacGroupGrantsOwnerAccess: RbacGroupGrantsOwnerAccessErrorDetails
        ) = ErrorDetails(rbacGroupGrantsOwnerAccess = rbacGroupGrantsOwnerAccess)

        /**
         * The API could not finish checking whether an RBAC Group in the request grants owner
         * access, so it made no change. Retrying the same request will not help. Make the change in
         * the organization's admin settings in Claude.
         */
        @JvmStatic
        fun ofRbacGroupOwnerAccessUnconfirmed(
            rbacGroupOwnerAccessUnconfirmed: RbacGroupOwnerAccessUnconfirmedErrorDetails
        ) = ErrorDetails(rbacGroupOwnerAccessUnconfirmed = rbacGroupOwnerAccessUnconfirmed)

        /**
         * The user is the organization's only member. Add another member before removing this one.
         */
        @JvmStatic
        fun ofCannotRemoveLastMember(cannotRemoveLastMember: CannotRemoveLastMemberErrorDetails) =
            ErrorDetails(cannotRemoveLastMember = cannotRemoveLastMember)

        /**
         * Another change to the organization's members is in progress. Retry the request once it
         * completes.
         */
        @JvmStatic
        fun ofConcurrentMembershipChange(
            concurrentMembershipChange: ConcurrentMembershipChangeErrorDetails
        ) = ErrorDetails(concurrentMembershipChange = concurrentMembershipChange)

        /**
         * The External Key failed Anthropic's validation, so the request was refused. The message
         * gives the reason, if there is one.
         */
        @JvmStatic
        fun ofExternalKeyValidationFailed(
            externalKeyValidationFailed: ExternalKeyValidationFailedErrorDetails
        ) = ErrorDetails(externalKeyValidationFailed = externalKeyValidationFailed)

        /**
         * Anthropic's validation of the External Key did not finish in time, so the request was
         * refused. Wait a moment, then send the request again.
         */
        @JvmStatic
        fun ofExternalKeyValidationTimedOut(
            externalKeyValidationTimedOut: ExternalKeyValidationTimedOutErrorDetails
        ) = ErrorDetails(externalKeyValidationTimedOut = externalKeyValidationTimedOut)

        /**
         * The External Key is bound to a user profile, and the Workspace does not have that user
         * profile. A Workspace can use only a key bound to its own user profile or a key that isn't
         * bound to one.
         */
        @JvmStatic
        fun ofKeyProfileMismatch(keyProfileMismatch: KeyProfileMismatchErrorDetails) =
            ErrorDetails(keyProfileMismatch = keyProfileMismatch)

        /**
         * The request breaks the organization's Workspace Policy. The message says which rule it
         * broke; an organization admin can see and change the policy in the Console.
         */
        @JvmStatic
        fun ofWorkspacePolicyViolation(
            workspacePolicyViolation: WorkspacePolicyViolationErrorDetails
        ) = ErrorDetails(workspacePolicyViolation = workspacePolicyViolation)

        /**
         * Returns an immutable instance of [ErrorDetails] whose [ofWorkspacePolicyViolation]
         * variant is built from the given required [field].
         */
        @JvmStatic
        fun ofWorkspacePolicyViolation(field: WorkspacePolicyField) =
            ofWorkspacePolicyViolation(WorkspacePolicyViolationErrorDetails.of(field))

        /** The external key changed while the request was validating it. Send the request again. */
        @JvmStatic
        fun ofExternalKeyChanged(externalKeyChanged: ExternalKeyChangedErrorDetails) =
            ErrorDetails(externalKeyChanged = externalKeyChanged)

        /** The workspace has reached the member limit that Anthropic set for it. */
        @JvmStatic
        fun ofWorkspaceMemberLimitExceeded(
            workspaceMemberLimitExceeded: WorkspaceMemberLimitExceededErrorDetails
        ) = ErrorDetails(workspaceMemberLimitExceeded = workspaceMemberLimitExceeded)

        /**
         * The endpoint does not support the organization's type. Each endpoint's description says
         * which organization types it supports.
         */
        @JvmStatic
        fun ofOrganizationTypeNotSupported(
            organizationTypeNotSupported: OrganizationTypeNotSupportedErrorDetails
        ) = ErrorDetails(organizationTypeNotSupported = organizationTypeNotSupported)

        /**
         * Anthropic has turned off creating environments for this organization. Existing
         * environments still work.
         */
        @JvmStatic
        fun ofEnvironmentCreateDisabled(
            environmentCreateDisabled: BetaEnvironmentCreateDisabledErrorDetails
        ) = ErrorDetails(environmentCreateDisabled = environmentCreateDisabled)

        /** The environment is archived, so it cannot be updated. */
        @JvmStatic
        fun ofEnvironmentArchived(environmentArchived: BetaEnvironmentArchivedErrorDetails) =
            ErrorDetails(environmentArchived = environmentArchived)

        /**
         * `config.type` differs from the environment's stored type, which cannot change after
         * creation.
         */
        @JvmStatic
        fun ofInvalidConfigTypeChange(
            invalidConfigTypeChange: BetaInvalidConfigTypeChangeErrorDetails
        ) = ErrorDetails(invalidConfigTypeChange = invalidConfigTypeChange)

        /**
         * Anthropic has turned off updating environments for this organization. Existing
         * environments still work as they are.
         */
        @JvmStatic
        fun ofEnvironmentUpdateDisabled(
            environmentUpdateDisabled: BetaEnvironmentUpdateDisabledErrorDetails
        ) = ErrorDetails(environmentUpdateDisabled = environmentUpdateDisabled)

        /**
         * `expected_last_heartbeat` does not match the stored heartbeat, so another worker may hold
         * the lease.
         */
        @JvmStatic
        fun ofHeartbeatPreconditionFailed(
            heartbeatPreconditionFailed: BetaHeartbeatPreconditionFailedErrorDetails
        ) = ErrorDetails(heartbeatPreconditionFailed = heartbeatPreconditionFailed)

        /**
         * Returns an immutable instance of [ErrorDetails] whose [ofHeartbeatPreconditionFailed]
         * variant is built from the given required [currentState].
         */
        @JvmStatic
        fun ofHeartbeatPreconditionFailed(currentState: BetaWorkLeaseState) =
            ofHeartbeatPreconditionFailed(
                BetaHeartbeatPreconditionFailedErrorDetails.of(currentState)
            )

        /**
         * `metadata` has too many keys, a key or value that is too long, or a reserved key. The
         * message names each problem.
         */
        @JvmStatic
        fun ofInvalidMetadata(invalidMetadata: BetaInvalidMetadataErrorDetails) =
            ErrorDetails(invalidMetadata = invalidMetadata)

        /**
         * Anthropic has temporarily disabled billing changes for the user account the credential
         * belongs to. Contact support.
         */
        @JvmStatic
        fun ofBillingFrozen(billingFrozen: BetaBillingFrozenErrorDetails) =
            ErrorDetails(billingFrozen = billingFrozen)

        /**
         * The organization cannot retrieve, set or delete spend limits with an Admin API
         * credential.
         */
        @JvmStatic
        fun ofSpendLimitsAdminApiWritesNotEnabled(
            spendLimitsAdminApiWritesNotEnabled: BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails
        ) = ErrorDetails(spendLimitsAdminApiWritesNotEnabled = spendLimitsAdminApiWritesNotEnabled)

        /**
         * The request's `Content-Type` header names a charset other than UTF-8, ISO-8859-1 or
         * US-ASCII. Send the request with no charset, or with one of these.
         */
        @JvmStatic
        fun ofUnsupportedCharset(unsupportedCharset: BetaUnsupportedCharsetErrorDetails) =
            ErrorDetails(unsupportedCharset = unsupportedCharset)

        /**
         * The marketplace already holds this name. `plugin_id` is the Plugin that holds it, and can
         * be absent.
         */
        @JvmStatic
        fun ofPluginNameTaken(pluginNameTaken: BetaPluginNameTakenErrorDetails) =
            ErrorDetails(pluginNameTaken = pluginNameTaken)

        /**
         * One of the Plugin's skills has the name of an organization skill. `skill_name` is that
         * name.
         */
        @JvmStatic
        fun ofSkillNameTaken(skillNameTaken: BetaSkillNameTakenErrorDetails) =
            ErrorDetails(skillNameTaken = skillNameTaken)

        /**
         * Returns an immutable instance of [ErrorDetails] whose [ofSkillNameTaken] variant is built
         * from the given required [skillName].
         */
        @JvmStatic
        fun ofSkillNameTaken(skillName: String) =
            ofSkillNameTaken(BetaSkillNameTakenErrorDetails.of(skillName))

        /**
         * The Plugin version was stored but is not yet usable in claude.ai. `plugin_id` and
         * `plugin_version_id` identify what was stored.
         */
        @JvmStatic
        fun ofRegistrationPending(registrationPending: BetaRegistrationPendingErrorDetails) =
            ErrorDetails(registrationPending = registrationPending)

        /**
         * The version in `plugin_version_id` did not pass its content scan, so it cannot be served.
         */
        @JvmStatic
        fun ofScanFailed(scanFailed: BetaScanFailedErrorDetails) =
            ErrorDetails(scanFailed = scanFailed)

        /**
         * Returns an immutable instance of [ErrorDetails] whose [ofScanFailed] variant is built
         * from the given required [pluginVersionId].
         */
        @JvmStatic
        fun ofScanFailed(pluginVersionId: String) =
            ofScanFailed(BetaScanFailedErrorDetails.of(pluginVersionId))

        /**
         * The content scan of the version in `plugin_version_id` is still running. Retry once the
         * scan finishes.
         */
        @JvmStatic
        fun ofScanPending(scanPending: BetaScanPendingErrorDetails) =
            ErrorDetails(scanPending = scanPending)

        /**
         * Returns an immutable instance of [ErrorDetails] whose [ofScanPending] variant is built
         * from the given required [pluginVersionId].
         */
        @JvmStatic
        fun ofScanPending(pluginVersionId: String) =
            ofScanPending(BetaScanPendingErrorDetails.of(pluginVersionId))

        /**
         * The request set the channel up, and `settings` was then refused. The channel stays set up
         * with every setting unset.
         */
        @JvmStatic
        fun ofSlackChannelSetUpSettingsRefused(
            slackChannelSetUpSettingsRefused: BetaSlackChannelSetUpSettingsRefusedErrorDetails
        ) = ErrorDetails(slackChannelSetUpSettingsRefused = slackChannelSetUpSettingsRefused)

        /**
         * Returns an immutable instance of [ErrorDetails] whose
         * [ofSlackChannelSetUpSettingsRefused] variant is built from the given required
         * [slackChannelId].
         */
        @JvmStatic
        fun ofSlackChannelSetUpSettingsRefused(slackChannelId: String) =
            ofSlackChannelSetUpSettingsRefused(
                BetaSlackChannelSetUpSettingsRefusedErrorDetails.of(slackChannelId)
            )

        /**
         * The API key lacks the scope the request needs: `read:claude_tag` for a read,
         * `write:claude_tag` for a change.
         */
        @JvmStatic
        fun ofApiKeyScopeMissing(apiKeyScopeMissing: BetaApiKeyScopeMissingErrorDetails) =
            ErrorDetails(apiKeyScopeMissing = apiKeyScopeMissing)

        /** Changes through the Claude Tag API are not enabled for the organization. */
        @JvmStatic
        fun ofClaudeTagWritesNotEnabled(
            claudeTagWritesNotEnabled: BetaClaudeTagWritesNotEnabledErrorDetails
        ) = ErrorDetails(claudeTagWritesNotEnabled = claudeTagWritesNotEnabled)

        /**
         * The organization's compliance configuration does not let Claude Tag store anything new.
         */
        @JvmStatic
        fun ofComplianceConfigurationRestricted(
            complianceConfigurationRestricted: BetaComplianceConfigurationRestrictedErrorDetails
        ) = ErrorDetails(complianceConfigurationRestricted = complianceConfigurationRestricted)

        /** The credential is not a scoped API key that is bound at the organization level. */
        @JvmStatic
        fun ofOrganizationLevelScopedApiKeyRequired(
            organizationLevelScopedApiKeyRequired:
                BetaOrganizationLevelScopedApiKeyRequiredErrorDetails
        ) =
            ErrorDetails(
                organizationLevelScopedApiKeyRequired = organizationLevelScopedApiKeyRequired
            )

        /** `settings.environment_id` names no Environment within the organization's reach. */
        @JvmStatic
        fun ofEnvironmentNotFound(environmentNotFound: BetaEnvironmentNotFoundErrorDetails) =
            ErrorDetails(environmentNotFound = environmentNotFound)

        /**
         * `settings.environment_id` names no Environment within the organization's reach, and the
         * request had set the channel up by then. The channel stays set up with every setting
         * unset.
         */
        @JvmStatic
        fun ofSlackChannelSetUpEnvironmentNotFound(
            slackChannelSetUpEnvironmentNotFound:
                BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails
        ) =
            ErrorDetails(
                slackChannelSetUpEnvironmentNotFound = slackChannelSetUpEnvironmentNotFound
            )

        /**
         * Returns an immutable instance of [ErrorDetails] whose
         * [ofSlackChannelSetUpEnvironmentNotFound] variant is built from the given required
         * [slackChannelId].
         */
        @JvmStatic
        fun ofSlackChannelSetUpEnvironmentNotFound(slackChannelId: String) =
            ofSlackChannelSetUpEnvironmentNotFound(
                BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails.of(slackChannelId)
            )

        /**
         * The request set the channel up, and something else then changed the channel or removed
         * it. Nothing of `settings` was stored.
         */
        @JvmStatic
        fun ofSlackChannelSetUpSettingsConflict(
            slackChannelSetUpSettingsConflict: BetaSlackChannelSetUpSettingsConflictErrorDetails
        ) = ErrorDetails(slackChannelSetUpSettingsConflict = slackChannelSetUpSettingsConflict)

        /**
         * Returns an immutable instance of [ErrorDetails] whose
         * [ofSlackChannelSetUpSettingsConflict] variant is built from the given required
         * [slackChannelId].
         */
        @JvmStatic
        fun ofSlackChannelSetUpSettingsConflict(slackChannelId: String) =
            ofSlackChannelSetUpSettingsConflict(
                BetaSlackChannelSetUpSettingsConflictErrorDetails.of(slackChannelId)
            )
    }

    /**
     * An interface that defines how to map each variant of [ErrorDetails] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The tools available after a message exceed a limit on their number or their size. */
        fun visitAvailableToolsLimitExceeded(
            availableToolsLimitExceeded: AvailableToolsLimitExceededErrorDetails
        ): T

        /**
         * The API key belongs to the Claude Code workspace, and the user who created it is no
         * longer a member of the organization or of that workspace.
         */
        fun visitClaudeCodeKeyCreatorNotMember(
            claudeCodeKeyCreatorNotMember: ClaudeCodeKeyCreatorNotMemberErrorDetails
        ): T

        /**
         * The request comes from a Claude Code version older than the minimum that the requested
         * model or the organization requires. Update Claude Code, then retry.
         */
        fun visitClaudeCodeVersionTooOld(
            claudeCodeVersionTooOld: ClaudeCodeVersionTooOldErrorDetails
        ): T

        /**
         * The workspace's customer-managed encryption key is disabled or revoked. Re-enable the
         * key, then retry.
         */
        fun visitCmekKeyDisabled(cmekKeyDisabled: CmekKeyDisabledErrorDetails): T

        /**
         * The key management service's network access controls blocked the request to the
         * workspace's customer-managed encryption key. The key itself may be healthy.
         */
        fun visitCmekKeyNetworkBlocked(cmekKeyNetworkBlocked: CmekKeyNetworkBlockedErrorDetails): T

        /**
         * `messages` holds more than one `compaction` block with content. Send only the block the
         * conversation returned last.
         */
        fun visitCompactionBlockAmbiguous(
            compactionBlockAmbiguous: CompactionBlockAmbiguousErrorDetails
        ): T

        /**
         * The `compaction` block is not the first block of the conversation, or is in a `system`
         * message.
         */
        fun visitCompactionBlockMisplaced(
            compactionBlockMisplaced: CompactionBlockMisplacedErrorDetails
        ): T

        /** The `compaction` block's `content` differs from the text its `signature` recorded. */
        fun visitCompactionContentMismatch(
            compactionContentMismatch: CompactionContentMismatchErrorDetails
        ): T

        /** The compaction request ends on an `assistant` turn that is not finished. */
        fun visitCompactionIncompleteTurn(
            compactionIncompleteTurn: CompactionIncompleteTurnErrorDetails
        ): T

        /** `messages` holds no `user` or `assistant` content to summarize or adopt. */
        fun visitCompactionNothingToSummarize(
            compactionNothingToSummarize: CompactionNothingToSummarizeErrorDetails
        ): T

        /** The `compaction` block's `signature` does not verify. */
        fun visitCompactionSignatureInvalid(
            compactionSignatureInvalid: CompactionSignatureInvalidErrorDetails
        ): T

        /** The conversation references more distinct tools than a `compaction` block can record. */
        fun visitCompactionTooManyToolReferences(
            compactionTooManyToolReferences: CompactionTooManyToolReferencesErrorDetails
        ): T

        /**
         * The `compaction` block's `tool_changes` differs from what was returned with the block.
         */
        fun visitCompactionToolChangesMismatch(
            compactionToolChangesMismatch: CompactionToolChangesMismatchErrorDetails
        ): T

        /**
         * Anthropic has disabled the organization for trust and safety reasons. An organization
         * disabled for any other reason, such as billing, does not get this code. `appeal_url`,
         * when present, is where the organization can appeal.
         */
        fun visitOrganizationOnHold(organizationOnHold: OrganizationOnHoldErrorDetails): T

        /** The request body is not valid for its `Content-Encoding`. */
        fun visitRequestBodyEncodingInvalid(
            requestBodyEncodingInvalid: RequestBodyEncodingInvalidErrorDetails
        ): T

        /** The request's `Content-Encoding` is not supported. */
        fun visitRequestBodyEncodingUnsupported(
            requestBodyEncodingUnsupported: RequestBodyEncodingUnsupportedErrorDetails
        ): T

        /**
         * The response that `previous_message_id` names has already been continued, and each
         * response can be continued only once. Continue from the Thread's most recent response
         * `id`.
         */
        fun visitThreadAlreadyContinued(
            threadAlreadyContinued: ThreadAlreadyContinuedErrorDetails
        ): T

        /**
         * Another request is still continuing from the same `previous_message_id`. Wait for it to
         * complete and continue from its response `id`, or replay the full conversation with
         * `thread: {"type": "create"}`.
         */
        fun visitThreadContinueInFlight(
            threadContinueInFlight: ThreadContinueInFlightErrorDetails
        ): T

        /**
         * A field that must stay the same across a Thread's turns differs from the value stored
         * with the Thread; the message names each such field. Send the stored values, or replay the
         * full conversation with `thread: {"type": "create"}` to change them.
         */
        fun visitThreadFingerprintMismatch(
            threadFingerprintMismatch: ThreadFingerprintMismatchErrorDetails
        ): T

        /**
         * The request combines `thread` with a request field or an organization setting that
         * Threads do not support; `unsupported`, when present, names it. Send the full conversation
         * without `thread`, or change that field.
         */
        fun visitThreadUnsupportedRequest(
            threadUnsupportedRequest: ThreadUnsupportedRequestErrorDetails
        ): T

        /**
         * A tool made available in `messages` takes a name that an available tool of another kind,
         * or of another MCP server, already holds.
         */
        fun visitToolNameConflict(toolNameConflict: ToolNameConflictErrorDetails): T

        /**
         * A tool change or MCP tool listing references a tool or MCP server that is not defined at
         * that position in the conversation.
         */
        fun visitToolReferenceUnresolved(
            toolReferenceUnresolved: ToolReferenceUnresolvedErrorDetails
        ): T

        /**
         * No usable Thread state was found for `previous_message_id`. Replay the full conversation
         * with `thread: {"type": "create"}` to start a new Thread.
         */
        fun visitThreadNotFound(threadNotFound: ThreadNotFoundErrorDetails): T

        /**
         * A custom skill the request names is stored under an encryption key state the workspace
         * cannot currently decrypt.
         */
        fun visitSkillReadBlockedUndecryptable(
            skillReadBlockedUndecryptable: SkillReadBlockedUndecryptableErrorDetails
        ): T

        /** The compressed request body exceeds the request size limit once decoded. */
        fun visitRequestBodyTooLargeAfterDecoding(
            requestBodyTooLargeAfterDecoding: RequestBodyTooLargeAfterDecodingErrorDetails
        ): T

        /** The organization has crossed the monthly API usage threshold of its API tier. */
        fun visitEnforcedSpendLimitReached(
            enforcedSpendLimitReached: EnforcedSpendLimitReachedErrorDetails
        ): T

        /**
         * The request would exceed a ramp limit, which caps how fast usage can grow. Retry after
         * the time in the `retry-after` header.
         */
        fun visitRampLimitExceeded(rampLimitExceeded: RampLimitExceededErrorDetails): T

        /**
         * The workspace's customer-managed encryption key could not be reached. Retry the request;
         * if this continues, check that the key is available in your key management service, or
         * contact Anthropic support.
         */
        fun visitCmekKeyUnreachable(cmekKeyUnreachable: CmekKeyUnreachableErrorDetails): T

        /**
         * The compaction could not be completed for a transient server reason. Retry the request.
         */
        fun visitCompactionUnavailable(compactionUnavailable: CompactionUnavailableErrorDetails): T

        /**
         * The organization's file storage quota is exhausted. Delete files to free up space, then
         * retry the upload.
         */
        fun visitStorageQuotaExceeded(storageQuotaExceeded: StorageQuotaExceededErrorDetails): T

        /**
         * The file has `downloadable: false`. Only files generated by a tool, such as the code
         * execution tool, can be downloaded.
         */
        fun visitFileNotDownloadable(fileNotDownloadable: FileNotDownloadableErrorDetails): T

        /** The file's `expires_at` has passed, so its content is no longer available. */
        fun visitFileExpired(fileExpired: FileExpiredErrorDetails): T

        /**
         * The workspace's encryption settings could not be read, so the request was refused. Retry
         * in a few minutes.
         */
        fun visitCmekContextUnavailable(
            cmekContextUnavailable: CmekContextUnavailableErrorDetails
        ): T

        /** The organization has reached its limit of active API keys. */
        fun visitOrgApiKeyLimitExceeded(
            orgApiKeyLimitExceeded: OrgApiKeyLimitExceededErrorDetails
        ): T

        /** The workspace has reached its limit of active API keys. */
        fun visitWorkspaceApiKeyLimitExceeded(
            workspaceApiKeyLimitExceeded: WorkspaceApiKeyLimitExceededErrorDetails
        ): T

        /**
         * The request depends on a user profile that does not exist in the organization or is no
         * longer active. The request either names this user profile by ID, or names a resource,
         * such as an External Key, that is bound to it.
         */
        fun visitUserProfileNotFound(userProfileNotFound: UserProfileNotFoundErrorDetails): T

        /**
         * The External Key can't be deleted or reconfigured while the organization's Workspace
         * Policy lists it as an allowed key.
         */
        fun visitExternalKeyInWorkspacePolicy(
            externalKeyInWorkspacePolicy: ExternalKeyInWorkspacePolicyErrorDetails
        ): T

        /**
         * The email address already belongs to a user in the organization. The message names the
         * user.
         */
        fun visitAlreadyMember(alreadyMember: AlreadyMemberErrorDetails): T

        /**
         * The email address's domain is not one of the verified domains of the organization's
         * parent organization.
         */
        fun visitCannotInviteEmailsWithUnverifiedDomains(
            cannotInviteEmailsWithUnverifiedDomains:
                CannotInviteEmailsWithUnverifiedDomainsErrorDetails
        ): T

        /**
         * The organization restricts invites to an allowed list of email domains, and the list is
         * empty or does not include the email address's domain.
         */
        fun visitEmailDomainNotAllowed(emailDomainNotAllowed: EmailDomainNotAllowedErrorDetails): T

        /** A pending invite already exists for the email address. The message names the invite. */
        fun visitInviteAlreadyExists(inviteAlreadyExists: InviteAlreadyExistsErrorDetails): T

        /**
         * The organization has reached the member limit that Anthropic set for it. Pending invites
         * count toward the limit.
         */
        fun visitOrgMemberLimitExceeded(
            orgMemberLimitExceeded: OrgMemberLimitExceededErrorDetails
        ): T

        /**
         * A role attached to an RBAC Group in the request grants owner access, so this API does not
         * add members to that group (directly or through an invite), remove its members, or delete
         * it. Make the change in the organization's admin settings in Claude.
         */
        fun visitRbacGroupGrantsOwnerAccess(
            rbacGroupGrantsOwnerAccess: RbacGroupGrantsOwnerAccessErrorDetails
        ): T

        /**
         * The API could not finish checking whether an RBAC Group in the request grants owner
         * access, so it made no change. Retrying the same request will not help. Make the change in
         * the organization's admin settings in Claude.
         */
        fun visitRbacGroupOwnerAccessUnconfirmed(
            rbacGroupOwnerAccessUnconfirmed: RbacGroupOwnerAccessUnconfirmedErrorDetails
        ): T

        /**
         * The user is the organization's only member. Add another member before removing this one.
         */
        fun visitCannotRemoveLastMember(
            cannotRemoveLastMember: CannotRemoveLastMemberErrorDetails
        ): T

        /**
         * Another change to the organization's members is in progress. Retry the request once it
         * completes.
         */
        fun visitConcurrentMembershipChange(
            concurrentMembershipChange: ConcurrentMembershipChangeErrorDetails
        ): T

        /**
         * The External Key failed Anthropic's validation, so the request was refused. The message
         * gives the reason, if there is one.
         */
        fun visitExternalKeyValidationFailed(
            externalKeyValidationFailed: ExternalKeyValidationFailedErrorDetails
        ): T

        /**
         * Anthropic's validation of the External Key did not finish in time, so the request was
         * refused. Wait a moment, then send the request again.
         */
        fun visitExternalKeyValidationTimedOut(
            externalKeyValidationTimedOut: ExternalKeyValidationTimedOutErrorDetails
        ): T

        /**
         * The External Key is bound to a user profile, and the Workspace does not have that user
         * profile. A Workspace can use only a key bound to its own user profile or a key that isn't
         * bound to one.
         */
        fun visitKeyProfileMismatch(keyProfileMismatch: KeyProfileMismatchErrorDetails): T

        /**
         * The request breaks the organization's Workspace Policy. The message says which rule it
         * broke; an organization admin can see and change the policy in the Console.
         */
        fun visitWorkspacePolicyViolation(
            workspacePolicyViolation: WorkspacePolicyViolationErrorDetails
        ): T

        /** The external key changed while the request was validating it. Send the request again. */
        fun visitExternalKeyChanged(externalKeyChanged: ExternalKeyChangedErrorDetails): T

        /** The workspace has reached the member limit that Anthropic set for it. */
        fun visitWorkspaceMemberLimitExceeded(
            workspaceMemberLimitExceeded: WorkspaceMemberLimitExceededErrorDetails
        ): T

        /**
         * The endpoint does not support the organization's type. Each endpoint's description says
         * which organization types it supports.
         */
        fun visitOrganizationTypeNotSupported(
            organizationTypeNotSupported: OrganizationTypeNotSupportedErrorDetails
        ): T

        /**
         * Anthropic has turned off creating environments for this organization. Existing
         * environments still work.
         */
        fun visitEnvironmentCreateDisabled(
            environmentCreateDisabled: BetaEnvironmentCreateDisabledErrorDetails
        ): T

        /** The environment is archived, so it cannot be updated. */
        fun visitEnvironmentArchived(environmentArchived: BetaEnvironmentArchivedErrorDetails): T

        /**
         * `config.type` differs from the environment's stored type, which cannot change after
         * creation.
         */
        fun visitInvalidConfigTypeChange(
            invalidConfigTypeChange: BetaInvalidConfigTypeChangeErrorDetails
        ): T

        /**
         * Anthropic has turned off updating environments for this organization. Existing
         * environments still work as they are.
         */
        fun visitEnvironmentUpdateDisabled(
            environmentUpdateDisabled: BetaEnvironmentUpdateDisabledErrorDetails
        ): T

        /**
         * `expected_last_heartbeat` does not match the stored heartbeat, so another worker may hold
         * the lease.
         */
        fun visitHeartbeatPreconditionFailed(
            heartbeatPreconditionFailed: BetaHeartbeatPreconditionFailedErrorDetails
        ): T

        /**
         * `metadata` has too many keys, a key or value that is too long, or a reserved key. The
         * message names each problem.
         */
        fun visitInvalidMetadata(invalidMetadata: BetaInvalidMetadataErrorDetails): T

        /**
         * Anthropic has temporarily disabled billing changes for the user account the credential
         * belongs to. Contact support.
         */
        fun visitBillingFrozen(billingFrozen: BetaBillingFrozenErrorDetails): T

        /**
         * The organization cannot retrieve, set or delete spend limits with an Admin API
         * credential.
         */
        fun visitSpendLimitsAdminApiWritesNotEnabled(
            spendLimitsAdminApiWritesNotEnabled: BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails
        ): T

        /**
         * The request's `Content-Type` header names a charset other than UTF-8, ISO-8859-1 or
         * US-ASCII. Send the request with no charset, or with one of these.
         */
        fun visitUnsupportedCharset(unsupportedCharset: BetaUnsupportedCharsetErrorDetails): T

        /**
         * The marketplace already holds this name. `plugin_id` is the Plugin that holds it, and can
         * be absent.
         */
        fun visitPluginNameTaken(pluginNameTaken: BetaPluginNameTakenErrorDetails): T

        /**
         * One of the Plugin's skills has the name of an organization skill. `skill_name` is that
         * name.
         */
        fun visitSkillNameTaken(skillNameTaken: BetaSkillNameTakenErrorDetails): T

        /**
         * The Plugin version was stored but is not yet usable in claude.ai. `plugin_id` and
         * `plugin_version_id` identify what was stored.
         */
        fun visitRegistrationPending(registrationPending: BetaRegistrationPendingErrorDetails): T

        /**
         * The version in `plugin_version_id` did not pass its content scan, so it cannot be served.
         */
        fun visitScanFailed(scanFailed: BetaScanFailedErrorDetails): T

        /**
         * The content scan of the version in `plugin_version_id` is still running. Retry once the
         * scan finishes.
         */
        fun visitScanPending(scanPending: BetaScanPendingErrorDetails): T

        /**
         * The request set the channel up, and `settings` was then refused. The channel stays set up
         * with every setting unset.
         */
        fun visitSlackChannelSetUpSettingsRefused(
            slackChannelSetUpSettingsRefused: BetaSlackChannelSetUpSettingsRefusedErrorDetails
        ): T

        /**
         * The API key lacks the scope the request needs: `read:claude_tag` for a read,
         * `write:claude_tag` for a change.
         */
        fun visitApiKeyScopeMissing(apiKeyScopeMissing: BetaApiKeyScopeMissingErrorDetails): T

        /** Changes through the Claude Tag API are not enabled for the organization. */
        fun visitClaudeTagWritesNotEnabled(
            claudeTagWritesNotEnabled: BetaClaudeTagWritesNotEnabledErrorDetails
        ): T

        /**
         * The organization's compliance configuration does not let Claude Tag store anything new.
         */
        fun visitComplianceConfigurationRestricted(
            complianceConfigurationRestricted: BetaComplianceConfigurationRestrictedErrorDetails
        ): T

        /** The credential is not a scoped API key that is bound at the organization level. */
        fun visitOrganizationLevelScopedApiKeyRequired(
            organizationLevelScopedApiKeyRequired:
                BetaOrganizationLevelScopedApiKeyRequiredErrorDetails
        ): T

        /** `settings.environment_id` names no Environment within the organization's reach. */
        fun visitEnvironmentNotFound(environmentNotFound: BetaEnvironmentNotFoundErrorDetails): T

        /**
         * `settings.environment_id` names no Environment within the organization's reach, and the
         * request had set the channel up by then. The channel stays set up with every setting
         * unset.
         */
        fun visitSlackChannelSetUpEnvironmentNotFound(
            slackChannelSetUpEnvironmentNotFound:
                BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails
        ): T

        /**
         * The request set the channel up, and something else then changed the channel or removed
         * it. Nothing of `settings` was stored.
         */
        fun visitSlackChannelSetUpSettingsConflict(
            slackChannelSetUpSettingsConflict: BetaSlackChannelSetUpSettingsConflictErrorDetails
        ): T

        /**
         * Maps an unknown variant of [ErrorDetails] to a value of type [T].
         *
         * An instance of [ErrorDetails] can contain an unknown variant if it was deserialized from
         * data that doesn't match any known variant. For example, if the SDK is on an older version
         * than the API, then the API may respond with new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ErrorDetails: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<ErrorDetails>(ErrorDetails::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ErrorDetails {
            val json = JsonValue.fromJsonNode(node)
            val errorCode = json.asObject().getOrNull()?.get("error_code")?.asString()?.getOrNull()

            when (errorCode) {
                "available_tools_limit_exceeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<AvailableToolsLimitExceededErrorDetails>(),
                        )
                        ?.let { ErrorDetails(availableToolsLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "claude_code_key_creator_not_member" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ClaudeCodeKeyCreatorNotMemberErrorDetails>(),
                        )
                        ?.let { ErrorDetails(claudeCodeKeyCreatorNotMember = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "claude_code_version_too_old" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ClaudeCodeVersionTooOldErrorDetails>(),
                        )
                        ?.let { ErrorDetails(claudeCodeVersionTooOld = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "cmek_key_disabled" -> {
                    return tryDeserialize(node, jacksonTypeRef<CmekKeyDisabledErrorDetails>())
                        ?.let { ErrorDetails(cmekKeyDisabled = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "cmek_key_network_blocked" -> {
                    return tryDeserialize(node, jacksonTypeRef<CmekKeyNetworkBlockedErrorDetails>())
                        ?.let { ErrorDetails(cmekKeyNetworkBlocked = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_block_ambiguous" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionBlockAmbiguousErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionBlockAmbiguous = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_block_misplaced" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionBlockMisplacedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionBlockMisplaced = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_content_mismatch" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionContentMismatchErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionContentMismatch = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_incomplete_turn" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionIncompleteTurnErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionIncompleteTurn = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_nothing_to_summarize" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionNothingToSummarizeErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionNothingToSummarize = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_signature_invalid" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionSignatureInvalidErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionSignatureInvalid = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_too_many_tool_references" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionTooManyToolReferencesErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionTooManyToolReferences = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_tool_changes_mismatch" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CompactionToolChangesMismatchErrorDetails>(),
                        )
                        ?.let { ErrorDetails(compactionToolChangesMismatch = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "organization_on_hold" -> {
                    return tryDeserialize(node, jacksonTypeRef<OrganizationOnHoldErrorDetails>())
                        ?.let { ErrorDetails(organizationOnHold = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "request_body_encoding_invalid" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<RequestBodyEncodingInvalidErrorDetails>(),
                        )
                        ?.let { ErrorDetails(requestBodyEncodingInvalid = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "request_body_encoding_unsupported" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<RequestBodyEncodingUnsupportedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(requestBodyEncodingUnsupported = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "thread_already_continued" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ThreadAlreadyContinuedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(threadAlreadyContinued = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "thread_continue_in_flight" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ThreadContinueInFlightErrorDetails>(),
                        )
                        ?.let { ErrorDetails(threadContinueInFlight = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "thread_fingerprint_mismatch" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ThreadFingerprintMismatchErrorDetails>(),
                        )
                        ?.let { ErrorDetails(threadFingerprintMismatch = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "thread_unsupported_request" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ThreadUnsupportedRequestErrorDetails>(),
                        )
                        ?.let { ErrorDetails(threadUnsupportedRequest = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "tool_name_conflict" -> {
                    return tryDeserialize(node, jacksonTypeRef<ToolNameConflictErrorDetails>())
                        ?.let { ErrorDetails(toolNameConflict = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "tool_reference_unresolved" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ToolReferenceUnresolvedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(toolReferenceUnresolved = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "thread_not_found" -> {
                    return tryDeserialize(node, jacksonTypeRef<ThreadNotFoundErrorDetails>())?.let {
                        ErrorDetails(threadNotFound = it, _json = json)
                    } ?: ErrorDetails(_json = json)
                }
                "skill_read_blocked_undecryptable" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<SkillReadBlockedUndecryptableErrorDetails>(),
                        )
                        ?.let { ErrorDetails(skillReadBlockedUndecryptable = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "request_body_too_large_after_decoding" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<RequestBodyTooLargeAfterDecodingErrorDetails>(),
                        )
                        ?.let { ErrorDetails(requestBodyTooLargeAfterDecoding = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "enforced_spend_limit_reached" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<EnforcedSpendLimitReachedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(enforcedSpendLimitReached = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "ramp_limit_exceeded" -> {
                    return tryDeserialize(node, jacksonTypeRef<RampLimitExceededErrorDetails>())
                        ?.let { ErrorDetails(rampLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "cmek_key_unreachable" -> {
                    return tryDeserialize(node, jacksonTypeRef<CmekKeyUnreachableErrorDetails>())
                        ?.let { ErrorDetails(cmekKeyUnreachable = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compaction_unavailable" -> {
                    return tryDeserialize(node, jacksonTypeRef<CompactionUnavailableErrorDetails>())
                        ?.let { ErrorDetails(compactionUnavailable = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "storage_quota_exceeded" -> {
                    return tryDeserialize(node, jacksonTypeRef<StorageQuotaExceededErrorDetails>())
                        ?.let { ErrorDetails(storageQuotaExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "file_not_downloadable" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileNotDownloadableErrorDetails>())
                        ?.let { ErrorDetails(fileNotDownloadable = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "file_expired" -> {
                    return tryDeserialize(node, jacksonTypeRef<FileExpiredErrorDetails>())?.let {
                        ErrorDetails(fileExpired = it, _json = json)
                    } ?: ErrorDetails(_json = json)
                }
                "cmek_context_unavailable" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CmekContextUnavailableErrorDetails>(),
                        )
                        ?.let { ErrorDetails(cmekContextUnavailable = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "org_api_key_limit_exceeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<OrgApiKeyLimitExceededErrorDetails>(),
                        )
                        ?.let { ErrorDetails(orgApiKeyLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "workspace_api_key_limit_exceeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<WorkspaceApiKeyLimitExceededErrorDetails>(),
                        )
                        ?.let { ErrorDetails(workspaceApiKeyLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "user_profile_not_found" -> {
                    return tryDeserialize(node, jacksonTypeRef<UserProfileNotFoundErrorDetails>())
                        ?.let { ErrorDetails(userProfileNotFound = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "external_key_in_workspace_policy" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ExternalKeyInWorkspacePolicyErrorDetails>(),
                        )
                        ?.let { ErrorDetails(externalKeyInWorkspacePolicy = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "already_member" -> {
                    return tryDeserialize(node, jacksonTypeRef<AlreadyMemberErrorDetails>())?.let {
                        ErrorDetails(alreadyMember = it, _json = json)
                    } ?: ErrorDetails(_json = json)
                }
                "cannot_invite_emails_with_unverified_domains" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CannotInviteEmailsWithUnverifiedDomainsErrorDetails>(),
                        )
                        ?.let {
                            ErrorDetails(cannotInviteEmailsWithUnverifiedDomains = it, _json = json)
                        } ?: ErrorDetails(_json = json)
                }
                "email_domain_not_allowed" -> {
                    return tryDeserialize(node, jacksonTypeRef<EmailDomainNotAllowedErrorDetails>())
                        ?.let { ErrorDetails(emailDomainNotAllowed = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "invite_already_exists" -> {
                    return tryDeserialize(node, jacksonTypeRef<InviteAlreadyExistsErrorDetails>())
                        ?.let { ErrorDetails(inviteAlreadyExists = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "org_member_limit_exceeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<OrgMemberLimitExceededErrorDetails>(),
                        )
                        ?.let { ErrorDetails(orgMemberLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "rbac_group_grants_owner_access" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<RbacGroupGrantsOwnerAccessErrorDetails>(),
                        )
                        ?.let { ErrorDetails(rbacGroupGrantsOwnerAccess = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "rbac_group_owner_access_unconfirmed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<RbacGroupOwnerAccessUnconfirmedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(rbacGroupOwnerAccessUnconfirmed = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "cannot_remove_last_member" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<CannotRemoveLastMemberErrorDetails>(),
                        )
                        ?.let { ErrorDetails(cannotRemoveLastMember = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "concurrent_membership_change" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ConcurrentMembershipChangeErrorDetails>(),
                        )
                        ?.let { ErrorDetails(concurrentMembershipChange = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "external_key_validation_failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ExternalKeyValidationFailedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(externalKeyValidationFailed = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "external_key_validation_timed_out" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<ExternalKeyValidationTimedOutErrorDetails>(),
                        )
                        ?.let { ErrorDetails(externalKeyValidationTimedOut = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "key_profile_mismatch" -> {
                    return tryDeserialize(node, jacksonTypeRef<KeyProfileMismatchErrorDetails>())
                        ?.let { ErrorDetails(keyProfileMismatch = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "workspace_policy_violation" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<WorkspacePolicyViolationErrorDetails>(),
                        )
                        ?.let { ErrorDetails(workspacePolicyViolation = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "external_key_changed" -> {
                    return tryDeserialize(node, jacksonTypeRef<ExternalKeyChangedErrorDetails>())
                        ?.let { ErrorDetails(externalKeyChanged = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "workspace_member_limit_exceeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<WorkspaceMemberLimitExceededErrorDetails>(),
                        )
                        ?.let { ErrorDetails(workspaceMemberLimitExceeded = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "organization_type_not_supported" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<OrganizationTypeNotSupportedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(organizationTypeNotSupported = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "environment_create_disabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaEnvironmentCreateDisabledErrorDetails>(),
                        )
                        ?.let { ErrorDetails(environmentCreateDisabled = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "environment_archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaEnvironmentArchivedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(environmentArchived = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "invalid_config_type_change" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaInvalidConfigTypeChangeErrorDetails>(),
                        )
                        ?.let { ErrorDetails(invalidConfigTypeChange = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "environment_update_disabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaEnvironmentUpdateDisabledErrorDetails>(),
                        )
                        ?.let { ErrorDetails(environmentUpdateDisabled = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "heartbeat_precondition_failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaHeartbeatPreconditionFailedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(heartbeatPreconditionFailed = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "invalid_metadata" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaInvalidMetadataErrorDetails>())
                        ?.let { ErrorDetails(invalidMetadata = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "billing_frozen" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaBillingFrozenErrorDetails>())
                        ?.let { ErrorDetails(billingFrozen = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "spend_limits_admin_api_writes_not_enabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails>(),
                        )
                        ?.let {
                            ErrorDetails(spendLimitsAdminApiWritesNotEnabled = it, _json = json)
                        } ?: ErrorDetails(_json = json)
                }
                "unsupported_charset" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaUnsupportedCharsetErrorDetails>(),
                        )
                        ?.let { ErrorDetails(unsupportedCharset = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "plugin_name_taken" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaPluginNameTakenErrorDetails>())
                        ?.let { ErrorDetails(pluginNameTaken = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "skill_name_taken" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaSkillNameTakenErrorDetails>())
                        ?.let { ErrorDetails(skillNameTaken = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "registration_pending" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaRegistrationPendingErrorDetails>(),
                        )
                        ?.let { ErrorDetails(registrationPending = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "scan_failed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaScanFailedErrorDetails>())?.let {
                        ErrorDetails(scanFailed = it, _json = json)
                    } ?: ErrorDetails(_json = json)
                }
                "scan_pending" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaScanPendingErrorDetails>())
                        ?.let { ErrorDetails(scanPending = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "slack_channel_set_up_settings_refused" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaSlackChannelSetUpSettingsRefusedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(slackChannelSetUpSettingsRefused = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "api_key_scope_missing" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaApiKeyScopeMissingErrorDetails>(),
                        )
                        ?.let { ErrorDetails(apiKeyScopeMissing = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "claude_tag_writes_not_enabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaClaudeTagWritesNotEnabledErrorDetails>(),
                        )
                        ?.let { ErrorDetails(claudeTagWritesNotEnabled = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "compliance_configuration_restricted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceConfigurationRestrictedErrorDetails>(),
                        )
                        ?.let { ErrorDetails(complianceConfigurationRestricted = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "organization_level_scoped_api_key_required" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaOrganizationLevelScopedApiKeyRequiredErrorDetails>(),
                        )
                        ?.let {
                            ErrorDetails(organizationLevelScopedApiKeyRequired = it, _json = json)
                        } ?: ErrorDetails(_json = json)
                }
                "environment_not_found" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaEnvironmentNotFoundErrorDetails>(),
                        )
                        ?.let { ErrorDetails(environmentNotFound = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
                "slack_channel_set_up_environment_not_found" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails>(),
                        )
                        ?.let {
                            ErrorDetails(slackChannelSetUpEnvironmentNotFound = it, _json = json)
                        } ?: ErrorDetails(_json = json)
                }
                "slack_channel_set_up_settings_conflict" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaSlackChannelSetUpSettingsConflictErrorDetails>(),
                        )
                        ?.let { ErrorDetails(slackChannelSetUpSettingsConflict = it, _json = json) }
                        ?: ErrorDetails(_json = json)
                }
            }

            return ErrorDetails(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<ErrorDetails>(ErrorDetails::class) {

        override fun serialize(
            value: ErrorDetails,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.availableToolsLimitExceeded != null ->
                    generator.writeObject(value.availableToolsLimitExceeded)
                value.claudeCodeKeyCreatorNotMember != null ->
                    generator.writeObject(value.claudeCodeKeyCreatorNotMember)
                value.claudeCodeVersionTooOld != null ->
                    generator.writeObject(value.claudeCodeVersionTooOld)
                value.cmekKeyDisabled != null -> generator.writeObject(value.cmekKeyDisabled)
                value.cmekKeyNetworkBlocked != null ->
                    generator.writeObject(value.cmekKeyNetworkBlocked)
                value.compactionBlockAmbiguous != null ->
                    generator.writeObject(value.compactionBlockAmbiguous)
                value.compactionBlockMisplaced != null ->
                    generator.writeObject(value.compactionBlockMisplaced)
                value.compactionContentMismatch != null ->
                    generator.writeObject(value.compactionContentMismatch)
                value.compactionIncompleteTurn != null ->
                    generator.writeObject(value.compactionIncompleteTurn)
                value.compactionNothingToSummarize != null ->
                    generator.writeObject(value.compactionNothingToSummarize)
                value.compactionSignatureInvalid != null ->
                    generator.writeObject(value.compactionSignatureInvalid)
                value.compactionTooManyToolReferences != null ->
                    generator.writeObject(value.compactionTooManyToolReferences)
                value.compactionToolChangesMismatch != null ->
                    generator.writeObject(value.compactionToolChangesMismatch)
                value.organizationOnHold != null -> generator.writeObject(value.organizationOnHold)
                value.requestBodyEncodingInvalid != null ->
                    generator.writeObject(value.requestBodyEncodingInvalid)
                value.requestBodyEncodingUnsupported != null ->
                    generator.writeObject(value.requestBodyEncodingUnsupported)
                value.threadAlreadyContinued != null ->
                    generator.writeObject(value.threadAlreadyContinued)
                value.threadContinueInFlight != null ->
                    generator.writeObject(value.threadContinueInFlight)
                value.threadFingerprintMismatch != null ->
                    generator.writeObject(value.threadFingerprintMismatch)
                value.threadUnsupportedRequest != null ->
                    generator.writeObject(value.threadUnsupportedRequest)
                value.toolNameConflict != null -> generator.writeObject(value.toolNameConflict)
                value.toolReferenceUnresolved != null ->
                    generator.writeObject(value.toolReferenceUnresolved)
                value.threadNotFound != null -> generator.writeObject(value.threadNotFound)
                value.skillReadBlockedUndecryptable != null ->
                    generator.writeObject(value.skillReadBlockedUndecryptable)
                value.requestBodyTooLargeAfterDecoding != null ->
                    generator.writeObject(value.requestBodyTooLargeAfterDecoding)
                value.enforcedSpendLimitReached != null ->
                    generator.writeObject(value.enforcedSpendLimitReached)
                value.rampLimitExceeded != null -> generator.writeObject(value.rampLimitExceeded)
                value.cmekKeyUnreachable != null -> generator.writeObject(value.cmekKeyUnreachable)
                value.compactionUnavailable != null ->
                    generator.writeObject(value.compactionUnavailable)
                value.storageQuotaExceeded != null ->
                    generator.writeObject(value.storageQuotaExceeded)
                value.fileNotDownloadable != null ->
                    generator.writeObject(value.fileNotDownloadable)
                value.fileExpired != null -> generator.writeObject(value.fileExpired)
                value.cmekContextUnavailable != null ->
                    generator.writeObject(value.cmekContextUnavailable)
                value.orgApiKeyLimitExceeded != null ->
                    generator.writeObject(value.orgApiKeyLimitExceeded)
                value.workspaceApiKeyLimitExceeded != null ->
                    generator.writeObject(value.workspaceApiKeyLimitExceeded)
                value.userProfileNotFound != null ->
                    generator.writeObject(value.userProfileNotFound)
                value.externalKeyInWorkspacePolicy != null ->
                    generator.writeObject(value.externalKeyInWorkspacePolicy)
                value.alreadyMember != null -> generator.writeObject(value.alreadyMember)
                value.cannotInviteEmailsWithUnverifiedDomains != null ->
                    generator.writeObject(value.cannotInviteEmailsWithUnverifiedDomains)
                value.emailDomainNotAllowed != null ->
                    generator.writeObject(value.emailDomainNotAllowed)
                value.inviteAlreadyExists != null ->
                    generator.writeObject(value.inviteAlreadyExists)
                value.orgMemberLimitExceeded != null ->
                    generator.writeObject(value.orgMemberLimitExceeded)
                value.rbacGroupGrantsOwnerAccess != null ->
                    generator.writeObject(value.rbacGroupGrantsOwnerAccess)
                value.rbacGroupOwnerAccessUnconfirmed != null ->
                    generator.writeObject(value.rbacGroupOwnerAccessUnconfirmed)
                value.cannotRemoveLastMember != null ->
                    generator.writeObject(value.cannotRemoveLastMember)
                value.concurrentMembershipChange != null ->
                    generator.writeObject(value.concurrentMembershipChange)
                value.externalKeyValidationFailed != null ->
                    generator.writeObject(value.externalKeyValidationFailed)
                value.externalKeyValidationTimedOut != null ->
                    generator.writeObject(value.externalKeyValidationTimedOut)
                value.keyProfileMismatch != null -> generator.writeObject(value.keyProfileMismatch)
                value.workspacePolicyViolation != null ->
                    generator.writeObject(value.workspacePolicyViolation)
                value.externalKeyChanged != null -> generator.writeObject(value.externalKeyChanged)
                value.workspaceMemberLimitExceeded != null ->
                    generator.writeObject(value.workspaceMemberLimitExceeded)
                value.organizationTypeNotSupported != null ->
                    generator.writeObject(value.organizationTypeNotSupported)
                value.environmentCreateDisabled != null ->
                    generator.writeObject(value.environmentCreateDisabled)
                value.environmentArchived != null ->
                    generator.writeObject(value.environmentArchived)
                value.invalidConfigTypeChange != null ->
                    generator.writeObject(value.invalidConfigTypeChange)
                value.environmentUpdateDisabled != null ->
                    generator.writeObject(value.environmentUpdateDisabled)
                value.heartbeatPreconditionFailed != null ->
                    generator.writeObject(value.heartbeatPreconditionFailed)
                value.invalidMetadata != null -> generator.writeObject(value.invalidMetadata)
                value.billingFrozen != null -> generator.writeObject(value.billingFrozen)
                value.spendLimitsAdminApiWritesNotEnabled != null ->
                    generator.writeObject(value.spendLimitsAdminApiWritesNotEnabled)
                value.unsupportedCharset != null -> generator.writeObject(value.unsupportedCharset)
                value.pluginNameTaken != null -> generator.writeObject(value.pluginNameTaken)
                value.skillNameTaken != null -> generator.writeObject(value.skillNameTaken)
                value.registrationPending != null ->
                    generator.writeObject(value.registrationPending)
                value.scanFailed != null -> generator.writeObject(value.scanFailed)
                value.scanPending != null -> generator.writeObject(value.scanPending)
                value.slackChannelSetUpSettingsRefused != null ->
                    generator.writeObject(value.slackChannelSetUpSettingsRefused)
                value.apiKeyScopeMissing != null -> generator.writeObject(value.apiKeyScopeMissing)
                value.claudeTagWritesNotEnabled != null ->
                    generator.writeObject(value.claudeTagWritesNotEnabled)
                value.complianceConfigurationRestricted != null ->
                    generator.writeObject(value.complianceConfigurationRestricted)
                value.organizationLevelScopedApiKeyRequired != null ->
                    generator.writeObject(value.organizationLevelScopedApiKeyRequired)
                value.environmentNotFound != null ->
                    generator.writeObject(value.environmentNotFound)
                value.slackChannelSetUpEnvironmentNotFound != null ->
                    generator.writeObject(value.slackChannelSetUpEnvironmentNotFound)
                value.slackChannelSetUpSettingsConflict != null ->
                    generator.writeObject(value.slackChannelSetUpSettingsConflict)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ErrorDetails")
            }
        }
    }

    class ErrorCode private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField
            val AVAILABLE_TOOLS_LIMIT_EXCEEDED =
                ErrorCode(JsonField.of("available_tools_limit_exceeded"))

            @JvmField
            val CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER =
                ErrorCode(JsonField.of("claude_code_key_creator_not_member"))

            @JvmField
            val CLAUDE_CODE_VERSION_TOO_OLD = ErrorCode(JsonField.of("claude_code_version_too_old"))

            @JvmField val CMEK_KEY_DISABLED = ErrorCode(JsonField.of("cmek_key_disabled"))

            @JvmField
            val CMEK_KEY_NETWORK_BLOCKED = ErrorCode(JsonField.of("cmek_key_network_blocked"))

            @JvmField
            val COMPACTION_BLOCK_AMBIGUOUS = ErrorCode(JsonField.of("compaction_block_ambiguous"))

            @JvmField
            val COMPACTION_BLOCK_MISPLACED = ErrorCode(JsonField.of("compaction_block_misplaced"))

            @JvmField
            val COMPACTION_CONTENT_MISMATCH = ErrorCode(JsonField.of("compaction_content_mismatch"))

            @JvmField
            val COMPACTION_INCOMPLETE_TURN = ErrorCode(JsonField.of("compaction_incomplete_turn"))

            @JvmField
            val COMPACTION_NOTHING_TO_SUMMARIZE =
                ErrorCode(JsonField.of("compaction_nothing_to_summarize"))

            @JvmField
            val COMPACTION_SIGNATURE_INVALID =
                ErrorCode(JsonField.of("compaction_signature_invalid"))

            @JvmField
            val COMPACTION_TOO_MANY_TOOL_REFERENCES =
                ErrorCode(JsonField.of("compaction_too_many_tool_references"))

            @JvmField
            val COMPACTION_TOOL_CHANGES_MISMATCH =
                ErrorCode(JsonField.of("compaction_tool_changes_mismatch"))

            @JvmField val ORGANIZATION_ON_HOLD = ErrorCode(JsonField.of("organization_on_hold"))

            @JvmField
            val REQUEST_BODY_ENCODING_INVALID =
                ErrorCode(JsonField.of("request_body_encoding_invalid"))

            @JvmField
            val REQUEST_BODY_ENCODING_UNSUPPORTED =
                ErrorCode(JsonField.of("request_body_encoding_unsupported"))

            @JvmField
            val THREAD_ALREADY_CONTINUED = ErrorCode(JsonField.of("thread_already_continued"))

            @JvmField
            val THREAD_CONTINUE_IN_FLIGHT = ErrorCode(JsonField.of("thread_continue_in_flight"))

            @JvmField
            val THREAD_FINGERPRINT_MISMATCH = ErrorCode(JsonField.of("thread_fingerprint_mismatch"))

            @JvmField
            val THREAD_UNSUPPORTED_REQUEST = ErrorCode(JsonField.of("thread_unsupported_request"))

            @JvmField val TOOL_NAME_CONFLICT = ErrorCode(JsonField.of("tool_name_conflict"))

            @JvmField
            val TOOL_REFERENCE_UNRESOLVED = ErrorCode(JsonField.of("tool_reference_unresolved"))

            @JvmField val THREAD_NOT_FOUND = ErrorCode(JsonField.of("thread_not_found"))

            @JvmField
            val SKILL_READ_BLOCKED_UNDECRYPTABLE =
                ErrorCode(JsonField.of("skill_read_blocked_undecryptable"))

            @JvmField
            val REQUEST_BODY_TOO_LARGE_AFTER_DECODING =
                ErrorCode(JsonField.of("request_body_too_large_after_decoding"))

            @JvmField
            val ENFORCED_SPEND_LIMIT_REACHED =
                ErrorCode(JsonField.of("enforced_spend_limit_reached"))

            @JvmField val RAMP_LIMIT_EXCEEDED = ErrorCode(JsonField.of("ramp_limit_exceeded"))

            @JvmField val CMEK_KEY_UNREACHABLE = ErrorCode(JsonField.of("cmek_key_unreachable"))

            @JvmField val COMPACTION_UNAVAILABLE = ErrorCode(JsonField.of("compaction_unavailable"))

            @JvmField val STORAGE_QUOTA_EXCEEDED = ErrorCode(JsonField.of("storage_quota_exceeded"))

            @JvmField val FILE_NOT_DOWNLOADABLE = ErrorCode(JsonField.of("file_not_downloadable"))

            @JvmField val FILE_EXPIRED = ErrorCode(JsonField.of("file_expired"))

            @JvmField
            val CMEK_CONTEXT_UNAVAILABLE = ErrorCode(JsonField.of("cmek_context_unavailable"))

            @JvmField
            val ORG_API_KEY_LIMIT_EXCEEDED = ErrorCode(JsonField.of("org_api_key_limit_exceeded"))

            @JvmField
            val WORKSPACE_API_KEY_LIMIT_EXCEEDED =
                ErrorCode(JsonField.of("workspace_api_key_limit_exceeded"))

            @JvmField val USER_PROFILE_NOT_FOUND = ErrorCode(JsonField.of("user_profile_not_found"))

            @JvmField
            val EXTERNAL_KEY_IN_WORKSPACE_POLICY =
                ErrorCode(JsonField.of("external_key_in_workspace_policy"))

            @JvmField val ALREADY_MEMBER = ErrorCode(JsonField.of("already_member"))

            @JvmField
            val CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS =
                ErrorCode(JsonField.of("cannot_invite_emails_with_unverified_domains"))

            @JvmField
            val EMAIL_DOMAIN_NOT_ALLOWED = ErrorCode(JsonField.of("email_domain_not_allowed"))

            @JvmField val INVITE_ALREADY_EXISTS = ErrorCode(JsonField.of("invite_already_exists"))

            @JvmField
            val ORG_MEMBER_LIMIT_EXCEEDED = ErrorCode(JsonField.of("org_member_limit_exceeded"))

            @JvmField
            val RBAC_GROUP_GRANTS_OWNER_ACCESS =
                ErrorCode(JsonField.of("rbac_group_grants_owner_access"))

            @JvmField
            val RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED =
                ErrorCode(JsonField.of("rbac_group_owner_access_unconfirmed"))

            @JvmField
            val CANNOT_REMOVE_LAST_MEMBER = ErrorCode(JsonField.of("cannot_remove_last_member"))

            @JvmField
            val CONCURRENT_MEMBERSHIP_CHANGE =
                ErrorCode(JsonField.of("concurrent_membership_change"))

            @JvmField
            val EXTERNAL_KEY_VALIDATION_FAILED =
                ErrorCode(JsonField.of("external_key_validation_failed"))

            @JvmField
            val EXTERNAL_KEY_VALIDATION_TIMED_OUT =
                ErrorCode(JsonField.of("external_key_validation_timed_out"))

            @JvmField val KEY_PROFILE_MISMATCH = ErrorCode(JsonField.of("key_profile_mismatch"))

            @JvmField
            val WORKSPACE_POLICY_VIOLATION = ErrorCode(JsonField.of("workspace_policy_violation"))

            @JvmField val EXTERNAL_KEY_CHANGED = ErrorCode(JsonField.of("external_key_changed"))

            @JvmField
            val WORKSPACE_MEMBER_LIMIT_EXCEEDED =
                ErrorCode(JsonField.of("workspace_member_limit_exceeded"))

            @JvmField
            val ORGANIZATION_TYPE_NOT_SUPPORTED =
                ErrorCode(JsonField.of("organization_type_not_supported"))

            @JvmField
            val ENVIRONMENT_CREATE_DISABLED = ErrorCode(JsonField.of("environment_create_disabled"))

            @JvmField val ENVIRONMENT_ARCHIVED = ErrorCode(JsonField.of("environment_archived"))

            @JvmField
            val INVALID_CONFIG_TYPE_CHANGE = ErrorCode(JsonField.of("invalid_config_type_change"))

            @JvmField
            val ENVIRONMENT_UPDATE_DISABLED = ErrorCode(JsonField.of("environment_update_disabled"))

            @JvmField
            val HEARTBEAT_PRECONDITION_FAILED =
                ErrorCode(JsonField.of("heartbeat_precondition_failed"))

            @JvmField val INVALID_METADATA = ErrorCode(JsonField.of("invalid_metadata"))

            @JvmField val BILLING_FROZEN = ErrorCode(JsonField.of("billing_frozen"))

            @JvmField
            val SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED =
                ErrorCode(JsonField.of("spend_limits_admin_api_writes_not_enabled"))

            @JvmField val UNSUPPORTED_CHARSET = ErrorCode(JsonField.of("unsupported_charset"))

            @JvmField val PLUGIN_NAME_TAKEN = ErrorCode(JsonField.of("plugin_name_taken"))

            @JvmField val SKILL_NAME_TAKEN = ErrorCode(JsonField.of("skill_name_taken"))

            @JvmField val REGISTRATION_PENDING = ErrorCode(JsonField.of("registration_pending"))

            @JvmField val SCAN_FAILED = ErrorCode(JsonField.of("scan_failed"))

            @JvmField val SCAN_PENDING = ErrorCode(JsonField.of("scan_pending"))

            @JvmField
            val SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED =
                ErrorCode(JsonField.of("slack_channel_set_up_settings_refused"))

            @JvmField val API_KEY_SCOPE_MISSING = ErrorCode(JsonField.of("api_key_scope_missing"))

            @JvmField
            val CLAUDE_TAG_WRITES_NOT_ENABLED =
                ErrorCode(JsonField.of("claude_tag_writes_not_enabled"))

            @JvmField
            val COMPLIANCE_CONFIGURATION_RESTRICTED =
                ErrorCode(JsonField.of("compliance_configuration_restricted"))

            @JvmField
            val ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED =
                ErrorCode(JsonField.of("organization_level_scoped_api_key_required"))

            @JvmField val ENVIRONMENT_NOT_FOUND = ErrorCode(JsonField.of("environment_not_found"))

            @JvmField
            val SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND =
                ErrorCode(JsonField.of("slack_channel_set_up_environment_not_found"))

            @JvmField
            val SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT =
                ErrorCode(JsonField.of("slack_channel_set_up_settings_conflict"))

            @JvmStatic
            fun of(value: String): ErrorCode =
                // Intern known values so `==` works
                when (value) {
                    "available_tools_limit_exceeded" -> AVAILABLE_TOOLS_LIMIT_EXCEEDED
                    "claude_code_key_creator_not_member" -> CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER
                    "claude_code_version_too_old" -> CLAUDE_CODE_VERSION_TOO_OLD
                    "cmek_key_disabled" -> CMEK_KEY_DISABLED
                    "cmek_key_network_blocked" -> CMEK_KEY_NETWORK_BLOCKED
                    "compaction_block_ambiguous" -> COMPACTION_BLOCK_AMBIGUOUS
                    "compaction_block_misplaced" -> COMPACTION_BLOCK_MISPLACED
                    "compaction_content_mismatch" -> COMPACTION_CONTENT_MISMATCH
                    "compaction_incomplete_turn" -> COMPACTION_INCOMPLETE_TURN
                    "compaction_nothing_to_summarize" -> COMPACTION_NOTHING_TO_SUMMARIZE
                    "compaction_signature_invalid" -> COMPACTION_SIGNATURE_INVALID
                    "compaction_too_many_tool_references" -> COMPACTION_TOO_MANY_TOOL_REFERENCES
                    "compaction_tool_changes_mismatch" -> COMPACTION_TOOL_CHANGES_MISMATCH
                    "organization_on_hold" -> ORGANIZATION_ON_HOLD
                    "request_body_encoding_invalid" -> REQUEST_BODY_ENCODING_INVALID
                    "request_body_encoding_unsupported" -> REQUEST_BODY_ENCODING_UNSUPPORTED
                    "thread_already_continued" -> THREAD_ALREADY_CONTINUED
                    "thread_continue_in_flight" -> THREAD_CONTINUE_IN_FLIGHT
                    "thread_fingerprint_mismatch" -> THREAD_FINGERPRINT_MISMATCH
                    "thread_unsupported_request" -> THREAD_UNSUPPORTED_REQUEST
                    "tool_name_conflict" -> TOOL_NAME_CONFLICT
                    "tool_reference_unresolved" -> TOOL_REFERENCE_UNRESOLVED
                    "thread_not_found" -> THREAD_NOT_FOUND
                    "skill_read_blocked_undecryptable" -> SKILL_READ_BLOCKED_UNDECRYPTABLE
                    "request_body_too_large_after_decoding" -> REQUEST_BODY_TOO_LARGE_AFTER_DECODING
                    "enforced_spend_limit_reached" -> ENFORCED_SPEND_LIMIT_REACHED
                    "ramp_limit_exceeded" -> RAMP_LIMIT_EXCEEDED
                    "cmek_key_unreachable" -> CMEK_KEY_UNREACHABLE
                    "compaction_unavailable" -> COMPACTION_UNAVAILABLE
                    "storage_quota_exceeded" -> STORAGE_QUOTA_EXCEEDED
                    "file_not_downloadable" -> FILE_NOT_DOWNLOADABLE
                    "file_expired" -> FILE_EXPIRED
                    "cmek_context_unavailable" -> CMEK_CONTEXT_UNAVAILABLE
                    "org_api_key_limit_exceeded" -> ORG_API_KEY_LIMIT_EXCEEDED
                    "workspace_api_key_limit_exceeded" -> WORKSPACE_API_KEY_LIMIT_EXCEEDED
                    "user_profile_not_found" -> USER_PROFILE_NOT_FOUND
                    "external_key_in_workspace_policy" -> EXTERNAL_KEY_IN_WORKSPACE_POLICY
                    "already_member" -> ALREADY_MEMBER
                    "cannot_invite_emails_with_unverified_domains" ->
                        CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS
                    "email_domain_not_allowed" -> EMAIL_DOMAIN_NOT_ALLOWED
                    "invite_already_exists" -> INVITE_ALREADY_EXISTS
                    "org_member_limit_exceeded" -> ORG_MEMBER_LIMIT_EXCEEDED
                    "rbac_group_grants_owner_access" -> RBAC_GROUP_GRANTS_OWNER_ACCESS
                    "rbac_group_owner_access_unconfirmed" -> RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED
                    "cannot_remove_last_member" -> CANNOT_REMOVE_LAST_MEMBER
                    "concurrent_membership_change" -> CONCURRENT_MEMBERSHIP_CHANGE
                    "external_key_validation_failed" -> EXTERNAL_KEY_VALIDATION_FAILED
                    "external_key_validation_timed_out" -> EXTERNAL_KEY_VALIDATION_TIMED_OUT
                    "key_profile_mismatch" -> KEY_PROFILE_MISMATCH
                    "workspace_policy_violation" -> WORKSPACE_POLICY_VIOLATION
                    "external_key_changed" -> EXTERNAL_KEY_CHANGED
                    "workspace_member_limit_exceeded" -> WORKSPACE_MEMBER_LIMIT_EXCEEDED
                    "organization_type_not_supported" -> ORGANIZATION_TYPE_NOT_SUPPORTED
                    "environment_create_disabled" -> ENVIRONMENT_CREATE_DISABLED
                    "environment_archived" -> ENVIRONMENT_ARCHIVED
                    "invalid_config_type_change" -> INVALID_CONFIG_TYPE_CHANGE
                    "environment_update_disabled" -> ENVIRONMENT_UPDATE_DISABLED
                    "heartbeat_precondition_failed" -> HEARTBEAT_PRECONDITION_FAILED
                    "invalid_metadata" -> INVALID_METADATA
                    "billing_frozen" -> BILLING_FROZEN
                    "spend_limits_admin_api_writes_not_enabled" ->
                        SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED
                    "unsupported_charset" -> UNSUPPORTED_CHARSET
                    "plugin_name_taken" -> PLUGIN_NAME_TAKEN
                    "skill_name_taken" -> SKILL_NAME_TAKEN
                    "registration_pending" -> REGISTRATION_PENDING
                    "scan_failed" -> SCAN_FAILED
                    "scan_pending" -> SCAN_PENDING
                    "slack_channel_set_up_settings_refused" -> SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED
                    "api_key_scope_missing" -> API_KEY_SCOPE_MISSING
                    "claude_tag_writes_not_enabled" -> CLAUDE_TAG_WRITES_NOT_ENABLED
                    "compliance_configuration_restricted" -> COMPLIANCE_CONFIGURATION_RESTRICTED
                    "organization_level_scoped_api_key_required" ->
                        ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED
                    "environment_not_found" -> ENVIRONMENT_NOT_FOUND
                    "slack_channel_set_up_environment_not_found" ->
                        SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND
                    "slack_channel_set_up_settings_conflict" ->
                        SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT
                    else -> ErrorCode(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): ErrorCode =
                value.asString().getOrNull()?.let { of(it) } ?: ErrorCode(value)
        }

        /** An enum containing [ErrorCode]'s known values. */
        enum class Known {
            AVAILABLE_TOOLS_LIMIT_EXCEEDED,
            CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER,
            CLAUDE_CODE_VERSION_TOO_OLD,
            CMEK_KEY_DISABLED,
            CMEK_KEY_NETWORK_BLOCKED,
            COMPACTION_BLOCK_AMBIGUOUS,
            COMPACTION_BLOCK_MISPLACED,
            COMPACTION_CONTENT_MISMATCH,
            COMPACTION_INCOMPLETE_TURN,
            COMPACTION_NOTHING_TO_SUMMARIZE,
            COMPACTION_SIGNATURE_INVALID,
            COMPACTION_TOO_MANY_TOOL_REFERENCES,
            COMPACTION_TOOL_CHANGES_MISMATCH,
            ORGANIZATION_ON_HOLD,
            REQUEST_BODY_ENCODING_INVALID,
            REQUEST_BODY_ENCODING_UNSUPPORTED,
            THREAD_ALREADY_CONTINUED,
            THREAD_CONTINUE_IN_FLIGHT,
            THREAD_FINGERPRINT_MISMATCH,
            THREAD_UNSUPPORTED_REQUEST,
            TOOL_NAME_CONFLICT,
            TOOL_REFERENCE_UNRESOLVED,
            THREAD_NOT_FOUND,
            SKILL_READ_BLOCKED_UNDECRYPTABLE,
            REQUEST_BODY_TOO_LARGE_AFTER_DECODING,
            ENFORCED_SPEND_LIMIT_REACHED,
            RAMP_LIMIT_EXCEEDED,
            CMEK_KEY_UNREACHABLE,
            COMPACTION_UNAVAILABLE,
            STORAGE_QUOTA_EXCEEDED,
            FILE_NOT_DOWNLOADABLE,
            FILE_EXPIRED,
            CMEK_CONTEXT_UNAVAILABLE,
            ORG_API_KEY_LIMIT_EXCEEDED,
            WORKSPACE_API_KEY_LIMIT_EXCEEDED,
            USER_PROFILE_NOT_FOUND,
            EXTERNAL_KEY_IN_WORKSPACE_POLICY,
            ALREADY_MEMBER,
            CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS,
            EMAIL_DOMAIN_NOT_ALLOWED,
            INVITE_ALREADY_EXISTS,
            ORG_MEMBER_LIMIT_EXCEEDED,
            RBAC_GROUP_GRANTS_OWNER_ACCESS,
            RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED,
            CANNOT_REMOVE_LAST_MEMBER,
            CONCURRENT_MEMBERSHIP_CHANGE,
            EXTERNAL_KEY_VALIDATION_FAILED,
            EXTERNAL_KEY_VALIDATION_TIMED_OUT,
            KEY_PROFILE_MISMATCH,
            WORKSPACE_POLICY_VIOLATION,
            EXTERNAL_KEY_CHANGED,
            WORKSPACE_MEMBER_LIMIT_EXCEEDED,
            ORGANIZATION_TYPE_NOT_SUPPORTED,
            ENVIRONMENT_CREATE_DISABLED,
            ENVIRONMENT_ARCHIVED,
            INVALID_CONFIG_TYPE_CHANGE,
            ENVIRONMENT_UPDATE_DISABLED,
            HEARTBEAT_PRECONDITION_FAILED,
            INVALID_METADATA,
            BILLING_FROZEN,
            SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED,
            UNSUPPORTED_CHARSET,
            PLUGIN_NAME_TAKEN,
            SKILL_NAME_TAKEN,
            REGISTRATION_PENDING,
            SCAN_FAILED,
            SCAN_PENDING,
            SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED,
            API_KEY_SCOPE_MISSING,
            CLAUDE_TAG_WRITES_NOT_ENABLED,
            COMPLIANCE_CONFIGURATION_RESTRICTED,
            ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED,
            ENVIRONMENT_NOT_FOUND,
            SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND,
            SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT,
        }

        /**
         * An enum containing [ErrorCode]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ErrorCode] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AVAILABLE_TOOLS_LIMIT_EXCEEDED,
            CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER,
            CLAUDE_CODE_VERSION_TOO_OLD,
            CMEK_KEY_DISABLED,
            CMEK_KEY_NETWORK_BLOCKED,
            COMPACTION_BLOCK_AMBIGUOUS,
            COMPACTION_BLOCK_MISPLACED,
            COMPACTION_CONTENT_MISMATCH,
            COMPACTION_INCOMPLETE_TURN,
            COMPACTION_NOTHING_TO_SUMMARIZE,
            COMPACTION_SIGNATURE_INVALID,
            COMPACTION_TOO_MANY_TOOL_REFERENCES,
            COMPACTION_TOOL_CHANGES_MISMATCH,
            ORGANIZATION_ON_HOLD,
            REQUEST_BODY_ENCODING_INVALID,
            REQUEST_BODY_ENCODING_UNSUPPORTED,
            THREAD_ALREADY_CONTINUED,
            THREAD_CONTINUE_IN_FLIGHT,
            THREAD_FINGERPRINT_MISMATCH,
            THREAD_UNSUPPORTED_REQUEST,
            TOOL_NAME_CONFLICT,
            TOOL_REFERENCE_UNRESOLVED,
            THREAD_NOT_FOUND,
            SKILL_READ_BLOCKED_UNDECRYPTABLE,
            REQUEST_BODY_TOO_LARGE_AFTER_DECODING,
            ENFORCED_SPEND_LIMIT_REACHED,
            RAMP_LIMIT_EXCEEDED,
            CMEK_KEY_UNREACHABLE,
            COMPACTION_UNAVAILABLE,
            STORAGE_QUOTA_EXCEEDED,
            FILE_NOT_DOWNLOADABLE,
            FILE_EXPIRED,
            CMEK_CONTEXT_UNAVAILABLE,
            ORG_API_KEY_LIMIT_EXCEEDED,
            WORKSPACE_API_KEY_LIMIT_EXCEEDED,
            USER_PROFILE_NOT_FOUND,
            EXTERNAL_KEY_IN_WORKSPACE_POLICY,
            ALREADY_MEMBER,
            CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS,
            EMAIL_DOMAIN_NOT_ALLOWED,
            INVITE_ALREADY_EXISTS,
            ORG_MEMBER_LIMIT_EXCEEDED,
            RBAC_GROUP_GRANTS_OWNER_ACCESS,
            RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED,
            CANNOT_REMOVE_LAST_MEMBER,
            CONCURRENT_MEMBERSHIP_CHANGE,
            EXTERNAL_KEY_VALIDATION_FAILED,
            EXTERNAL_KEY_VALIDATION_TIMED_OUT,
            KEY_PROFILE_MISMATCH,
            WORKSPACE_POLICY_VIOLATION,
            EXTERNAL_KEY_CHANGED,
            WORKSPACE_MEMBER_LIMIT_EXCEEDED,
            ORGANIZATION_TYPE_NOT_SUPPORTED,
            ENVIRONMENT_CREATE_DISABLED,
            ENVIRONMENT_ARCHIVED,
            INVALID_CONFIG_TYPE_CHANGE,
            ENVIRONMENT_UPDATE_DISABLED,
            HEARTBEAT_PRECONDITION_FAILED,
            INVALID_METADATA,
            BILLING_FROZEN,
            SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED,
            UNSUPPORTED_CHARSET,
            PLUGIN_NAME_TAKEN,
            SKILL_NAME_TAKEN,
            REGISTRATION_PENDING,
            SCAN_FAILED,
            SCAN_PENDING,
            SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED,
            API_KEY_SCOPE_MISSING,
            CLAUDE_TAG_WRITES_NOT_ENABLED,
            COMPLIANCE_CONFIGURATION_RESTRICTED,
            ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED,
            ENVIRONMENT_NOT_FOUND,
            SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND,
            SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT,
            /**
             * An enum member indicating that [ErrorCode] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                AVAILABLE_TOOLS_LIMIT_EXCEEDED -> Value.AVAILABLE_TOOLS_LIMIT_EXCEEDED
                CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER -> Value.CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER
                CLAUDE_CODE_VERSION_TOO_OLD -> Value.CLAUDE_CODE_VERSION_TOO_OLD
                CMEK_KEY_DISABLED -> Value.CMEK_KEY_DISABLED
                CMEK_KEY_NETWORK_BLOCKED -> Value.CMEK_KEY_NETWORK_BLOCKED
                COMPACTION_BLOCK_AMBIGUOUS -> Value.COMPACTION_BLOCK_AMBIGUOUS
                COMPACTION_BLOCK_MISPLACED -> Value.COMPACTION_BLOCK_MISPLACED
                COMPACTION_CONTENT_MISMATCH -> Value.COMPACTION_CONTENT_MISMATCH
                COMPACTION_INCOMPLETE_TURN -> Value.COMPACTION_INCOMPLETE_TURN
                COMPACTION_NOTHING_TO_SUMMARIZE -> Value.COMPACTION_NOTHING_TO_SUMMARIZE
                COMPACTION_SIGNATURE_INVALID -> Value.COMPACTION_SIGNATURE_INVALID
                COMPACTION_TOO_MANY_TOOL_REFERENCES -> Value.COMPACTION_TOO_MANY_TOOL_REFERENCES
                COMPACTION_TOOL_CHANGES_MISMATCH -> Value.COMPACTION_TOOL_CHANGES_MISMATCH
                ORGANIZATION_ON_HOLD -> Value.ORGANIZATION_ON_HOLD
                REQUEST_BODY_ENCODING_INVALID -> Value.REQUEST_BODY_ENCODING_INVALID
                REQUEST_BODY_ENCODING_UNSUPPORTED -> Value.REQUEST_BODY_ENCODING_UNSUPPORTED
                THREAD_ALREADY_CONTINUED -> Value.THREAD_ALREADY_CONTINUED
                THREAD_CONTINUE_IN_FLIGHT -> Value.THREAD_CONTINUE_IN_FLIGHT
                THREAD_FINGERPRINT_MISMATCH -> Value.THREAD_FINGERPRINT_MISMATCH
                THREAD_UNSUPPORTED_REQUEST -> Value.THREAD_UNSUPPORTED_REQUEST
                TOOL_NAME_CONFLICT -> Value.TOOL_NAME_CONFLICT
                TOOL_REFERENCE_UNRESOLVED -> Value.TOOL_REFERENCE_UNRESOLVED
                THREAD_NOT_FOUND -> Value.THREAD_NOT_FOUND
                SKILL_READ_BLOCKED_UNDECRYPTABLE -> Value.SKILL_READ_BLOCKED_UNDECRYPTABLE
                REQUEST_BODY_TOO_LARGE_AFTER_DECODING -> Value.REQUEST_BODY_TOO_LARGE_AFTER_DECODING
                ENFORCED_SPEND_LIMIT_REACHED -> Value.ENFORCED_SPEND_LIMIT_REACHED
                RAMP_LIMIT_EXCEEDED -> Value.RAMP_LIMIT_EXCEEDED
                CMEK_KEY_UNREACHABLE -> Value.CMEK_KEY_UNREACHABLE
                COMPACTION_UNAVAILABLE -> Value.COMPACTION_UNAVAILABLE
                STORAGE_QUOTA_EXCEEDED -> Value.STORAGE_QUOTA_EXCEEDED
                FILE_NOT_DOWNLOADABLE -> Value.FILE_NOT_DOWNLOADABLE
                FILE_EXPIRED -> Value.FILE_EXPIRED
                CMEK_CONTEXT_UNAVAILABLE -> Value.CMEK_CONTEXT_UNAVAILABLE
                ORG_API_KEY_LIMIT_EXCEEDED -> Value.ORG_API_KEY_LIMIT_EXCEEDED
                WORKSPACE_API_KEY_LIMIT_EXCEEDED -> Value.WORKSPACE_API_KEY_LIMIT_EXCEEDED
                USER_PROFILE_NOT_FOUND -> Value.USER_PROFILE_NOT_FOUND
                EXTERNAL_KEY_IN_WORKSPACE_POLICY -> Value.EXTERNAL_KEY_IN_WORKSPACE_POLICY
                ALREADY_MEMBER -> Value.ALREADY_MEMBER
                CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS ->
                    Value.CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS
                EMAIL_DOMAIN_NOT_ALLOWED -> Value.EMAIL_DOMAIN_NOT_ALLOWED
                INVITE_ALREADY_EXISTS -> Value.INVITE_ALREADY_EXISTS
                ORG_MEMBER_LIMIT_EXCEEDED -> Value.ORG_MEMBER_LIMIT_EXCEEDED
                RBAC_GROUP_GRANTS_OWNER_ACCESS -> Value.RBAC_GROUP_GRANTS_OWNER_ACCESS
                RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED -> Value.RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED
                CANNOT_REMOVE_LAST_MEMBER -> Value.CANNOT_REMOVE_LAST_MEMBER
                CONCURRENT_MEMBERSHIP_CHANGE -> Value.CONCURRENT_MEMBERSHIP_CHANGE
                EXTERNAL_KEY_VALIDATION_FAILED -> Value.EXTERNAL_KEY_VALIDATION_FAILED
                EXTERNAL_KEY_VALIDATION_TIMED_OUT -> Value.EXTERNAL_KEY_VALIDATION_TIMED_OUT
                KEY_PROFILE_MISMATCH -> Value.KEY_PROFILE_MISMATCH
                WORKSPACE_POLICY_VIOLATION -> Value.WORKSPACE_POLICY_VIOLATION
                EXTERNAL_KEY_CHANGED -> Value.EXTERNAL_KEY_CHANGED
                WORKSPACE_MEMBER_LIMIT_EXCEEDED -> Value.WORKSPACE_MEMBER_LIMIT_EXCEEDED
                ORGANIZATION_TYPE_NOT_SUPPORTED -> Value.ORGANIZATION_TYPE_NOT_SUPPORTED
                ENVIRONMENT_CREATE_DISABLED -> Value.ENVIRONMENT_CREATE_DISABLED
                ENVIRONMENT_ARCHIVED -> Value.ENVIRONMENT_ARCHIVED
                INVALID_CONFIG_TYPE_CHANGE -> Value.INVALID_CONFIG_TYPE_CHANGE
                ENVIRONMENT_UPDATE_DISABLED -> Value.ENVIRONMENT_UPDATE_DISABLED
                HEARTBEAT_PRECONDITION_FAILED -> Value.HEARTBEAT_PRECONDITION_FAILED
                INVALID_METADATA -> Value.INVALID_METADATA
                BILLING_FROZEN -> Value.BILLING_FROZEN
                SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED ->
                    Value.SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED
                UNSUPPORTED_CHARSET -> Value.UNSUPPORTED_CHARSET
                PLUGIN_NAME_TAKEN -> Value.PLUGIN_NAME_TAKEN
                SKILL_NAME_TAKEN -> Value.SKILL_NAME_TAKEN
                REGISTRATION_PENDING -> Value.REGISTRATION_PENDING
                SCAN_FAILED -> Value.SCAN_FAILED
                SCAN_PENDING -> Value.SCAN_PENDING
                SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED -> Value.SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED
                API_KEY_SCOPE_MISSING -> Value.API_KEY_SCOPE_MISSING
                CLAUDE_TAG_WRITES_NOT_ENABLED -> Value.CLAUDE_TAG_WRITES_NOT_ENABLED
                COMPLIANCE_CONFIGURATION_RESTRICTED -> Value.COMPLIANCE_CONFIGURATION_RESTRICTED
                ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED ->
                    Value.ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED
                ENVIRONMENT_NOT_FOUND -> Value.ENVIRONMENT_NOT_FOUND
                SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND ->
                    Value.SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND
                SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT ->
                    Value.SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                AVAILABLE_TOOLS_LIMIT_EXCEEDED -> Known.AVAILABLE_TOOLS_LIMIT_EXCEEDED
                CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER -> Known.CLAUDE_CODE_KEY_CREATOR_NOT_MEMBER
                CLAUDE_CODE_VERSION_TOO_OLD -> Known.CLAUDE_CODE_VERSION_TOO_OLD
                CMEK_KEY_DISABLED -> Known.CMEK_KEY_DISABLED
                CMEK_KEY_NETWORK_BLOCKED -> Known.CMEK_KEY_NETWORK_BLOCKED
                COMPACTION_BLOCK_AMBIGUOUS -> Known.COMPACTION_BLOCK_AMBIGUOUS
                COMPACTION_BLOCK_MISPLACED -> Known.COMPACTION_BLOCK_MISPLACED
                COMPACTION_CONTENT_MISMATCH -> Known.COMPACTION_CONTENT_MISMATCH
                COMPACTION_INCOMPLETE_TURN -> Known.COMPACTION_INCOMPLETE_TURN
                COMPACTION_NOTHING_TO_SUMMARIZE -> Known.COMPACTION_NOTHING_TO_SUMMARIZE
                COMPACTION_SIGNATURE_INVALID -> Known.COMPACTION_SIGNATURE_INVALID
                COMPACTION_TOO_MANY_TOOL_REFERENCES -> Known.COMPACTION_TOO_MANY_TOOL_REFERENCES
                COMPACTION_TOOL_CHANGES_MISMATCH -> Known.COMPACTION_TOOL_CHANGES_MISMATCH
                ORGANIZATION_ON_HOLD -> Known.ORGANIZATION_ON_HOLD
                REQUEST_BODY_ENCODING_INVALID -> Known.REQUEST_BODY_ENCODING_INVALID
                REQUEST_BODY_ENCODING_UNSUPPORTED -> Known.REQUEST_BODY_ENCODING_UNSUPPORTED
                THREAD_ALREADY_CONTINUED -> Known.THREAD_ALREADY_CONTINUED
                THREAD_CONTINUE_IN_FLIGHT -> Known.THREAD_CONTINUE_IN_FLIGHT
                THREAD_FINGERPRINT_MISMATCH -> Known.THREAD_FINGERPRINT_MISMATCH
                THREAD_UNSUPPORTED_REQUEST -> Known.THREAD_UNSUPPORTED_REQUEST
                TOOL_NAME_CONFLICT -> Known.TOOL_NAME_CONFLICT
                TOOL_REFERENCE_UNRESOLVED -> Known.TOOL_REFERENCE_UNRESOLVED
                THREAD_NOT_FOUND -> Known.THREAD_NOT_FOUND
                SKILL_READ_BLOCKED_UNDECRYPTABLE -> Known.SKILL_READ_BLOCKED_UNDECRYPTABLE
                REQUEST_BODY_TOO_LARGE_AFTER_DECODING -> Known.REQUEST_BODY_TOO_LARGE_AFTER_DECODING
                ENFORCED_SPEND_LIMIT_REACHED -> Known.ENFORCED_SPEND_LIMIT_REACHED
                RAMP_LIMIT_EXCEEDED -> Known.RAMP_LIMIT_EXCEEDED
                CMEK_KEY_UNREACHABLE -> Known.CMEK_KEY_UNREACHABLE
                COMPACTION_UNAVAILABLE -> Known.COMPACTION_UNAVAILABLE
                STORAGE_QUOTA_EXCEEDED -> Known.STORAGE_QUOTA_EXCEEDED
                FILE_NOT_DOWNLOADABLE -> Known.FILE_NOT_DOWNLOADABLE
                FILE_EXPIRED -> Known.FILE_EXPIRED
                CMEK_CONTEXT_UNAVAILABLE -> Known.CMEK_CONTEXT_UNAVAILABLE
                ORG_API_KEY_LIMIT_EXCEEDED -> Known.ORG_API_KEY_LIMIT_EXCEEDED
                WORKSPACE_API_KEY_LIMIT_EXCEEDED -> Known.WORKSPACE_API_KEY_LIMIT_EXCEEDED
                USER_PROFILE_NOT_FOUND -> Known.USER_PROFILE_NOT_FOUND
                EXTERNAL_KEY_IN_WORKSPACE_POLICY -> Known.EXTERNAL_KEY_IN_WORKSPACE_POLICY
                ALREADY_MEMBER -> Known.ALREADY_MEMBER
                CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS ->
                    Known.CANNOT_INVITE_EMAILS_WITH_UNVERIFIED_DOMAINS
                EMAIL_DOMAIN_NOT_ALLOWED -> Known.EMAIL_DOMAIN_NOT_ALLOWED
                INVITE_ALREADY_EXISTS -> Known.INVITE_ALREADY_EXISTS
                ORG_MEMBER_LIMIT_EXCEEDED -> Known.ORG_MEMBER_LIMIT_EXCEEDED
                RBAC_GROUP_GRANTS_OWNER_ACCESS -> Known.RBAC_GROUP_GRANTS_OWNER_ACCESS
                RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED -> Known.RBAC_GROUP_OWNER_ACCESS_UNCONFIRMED
                CANNOT_REMOVE_LAST_MEMBER -> Known.CANNOT_REMOVE_LAST_MEMBER
                CONCURRENT_MEMBERSHIP_CHANGE -> Known.CONCURRENT_MEMBERSHIP_CHANGE
                EXTERNAL_KEY_VALIDATION_FAILED -> Known.EXTERNAL_KEY_VALIDATION_FAILED
                EXTERNAL_KEY_VALIDATION_TIMED_OUT -> Known.EXTERNAL_KEY_VALIDATION_TIMED_OUT
                KEY_PROFILE_MISMATCH -> Known.KEY_PROFILE_MISMATCH
                WORKSPACE_POLICY_VIOLATION -> Known.WORKSPACE_POLICY_VIOLATION
                EXTERNAL_KEY_CHANGED -> Known.EXTERNAL_KEY_CHANGED
                WORKSPACE_MEMBER_LIMIT_EXCEEDED -> Known.WORKSPACE_MEMBER_LIMIT_EXCEEDED
                ORGANIZATION_TYPE_NOT_SUPPORTED -> Known.ORGANIZATION_TYPE_NOT_SUPPORTED
                ENVIRONMENT_CREATE_DISABLED -> Known.ENVIRONMENT_CREATE_DISABLED
                ENVIRONMENT_ARCHIVED -> Known.ENVIRONMENT_ARCHIVED
                INVALID_CONFIG_TYPE_CHANGE -> Known.INVALID_CONFIG_TYPE_CHANGE
                ENVIRONMENT_UPDATE_DISABLED -> Known.ENVIRONMENT_UPDATE_DISABLED
                HEARTBEAT_PRECONDITION_FAILED -> Known.HEARTBEAT_PRECONDITION_FAILED
                INVALID_METADATA -> Known.INVALID_METADATA
                BILLING_FROZEN -> Known.BILLING_FROZEN
                SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED ->
                    Known.SPEND_LIMITS_ADMIN_API_WRITES_NOT_ENABLED
                UNSUPPORTED_CHARSET -> Known.UNSUPPORTED_CHARSET
                PLUGIN_NAME_TAKEN -> Known.PLUGIN_NAME_TAKEN
                SKILL_NAME_TAKEN -> Known.SKILL_NAME_TAKEN
                REGISTRATION_PENDING -> Known.REGISTRATION_PENDING
                SCAN_FAILED -> Known.SCAN_FAILED
                SCAN_PENDING -> Known.SCAN_PENDING
                SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED -> Known.SLACK_CHANNEL_SET_UP_SETTINGS_REFUSED
                API_KEY_SCOPE_MISSING -> Known.API_KEY_SCOPE_MISSING
                CLAUDE_TAG_WRITES_NOT_ENABLED -> Known.CLAUDE_TAG_WRITES_NOT_ENABLED
                COMPLIANCE_CONFIGURATION_RESTRICTED -> Known.COMPLIANCE_CONFIGURATION_RESTRICTED
                ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED ->
                    Known.ORGANIZATION_LEVEL_SCOPED_API_KEY_REQUIRED
                ENVIRONMENT_NOT_FOUND -> Known.ENVIRONMENT_NOT_FOUND
                SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND ->
                    Known.SLACK_CHANNEL_SET_UP_ENVIRONMENT_NOT_FOUND
                SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT ->
                    Known.SLACK_CHANNEL_SET_UP_SETTINGS_CONFLICT
                else -> throw AnthropicInvalidDataException("Unknown ErrorCode: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
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
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): ErrorCode = apply {
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

            return other is ErrorCode && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
