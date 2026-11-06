package com.anthropic.models

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaOrganizationNotEnrolledInBetaErrorDetails
import com.anthropic.models.beta.BetaUnsupportedCharsetErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentArchivedErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentCreateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentNotFoundErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentUpdateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidConfigTypeChangeErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidMetadataErrorDetails
import com.anthropic.models.beta.environments.work.BetaHeartbeatPreconditionFailedErrorDetails
import com.anthropic.models.beta.environments.work.BetaWorkLeaseState
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
import com.anthropic.models.beta.organization.users.BetaCannotSignOutManagedMemberErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutMembershipAdminErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutOwnerErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutPrimaryOwnerErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutPrivilegedMemberErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutSelfErrorDetails
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
import com.anthropic.models.messages.ThreadUnsupportedFeatureName
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
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ErrorDetailsTest {

    @Test
    fun ofAvailableToolsLimitExceeded() {
        val availableToolsLimitExceeded = AvailableToolsLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofAvailableToolsLimitExceeded(availableToolsLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).contains(availableToolsLimitExceeded)
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofAvailableToolsLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofAvailableToolsLimitExceeded(
                AvailableToolsLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofClaudeCodeKeyCreatorNotMember() {
        val claudeCodeKeyCreatorNotMember =
            ClaudeCodeKeyCreatorNotMemberErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofClaudeCodeKeyCreatorNotMember(claudeCodeKeyCreatorNotMember)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember())
            .contains(claudeCodeKeyCreatorNotMember)
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofClaudeCodeKeyCreatorNotMemberRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofClaudeCodeKeyCreatorNotMember(
                ClaudeCodeKeyCreatorNotMemberErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofClaudeCodeVersionTooOld() {
        val claudeCodeVersionTooOld = ClaudeCodeVersionTooOldErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofClaudeCodeVersionTooOld(claudeCodeVersionTooOld)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).contains(claudeCodeVersionTooOld)
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofClaudeCodeVersionTooOldRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofClaudeCodeVersionTooOld(
                ClaudeCodeVersionTooOldErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCmekKeyDisabled() {
        val cmekKeyDisabled = CmekKeyDisabledErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekKeyDisabled(cmekKeyDisabled)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).contains(cmekKeyDisabled)
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCmekKeyDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekKeyDisabled(CmekKeyDisabledErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCmekKeyNetworkBlocked() {
        val cmekKeyNetworkBlocked = CmekKeyNetworkBlockedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekKeyNetworkBlocked(cmekKeyNetworkBlocked)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).contains(cmekKeyNetworkBlocked)
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCmekKeyNetworkBlockedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekKeyNetworkBlocked(
                CmekKeyNetworkBlockedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionBlockAmbiguous() {
        val compactionBlockAmbiguous = CompactionBlockAmbiguousErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionBlockAmbiguous(compactionBlockAmbiguous)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).contains(compactionBlockAmbiguous)
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionBlockAmbiguousRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionBlockAmbiguous(
                CompactionBlockAmbiguousErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionBlockMisplaced() {
        val compactionBlockMisplaced = CompactionBlockMisplacedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionBlockMisplaced(compactionBlockMisplaced)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).contains(compactionBlockMisplaced)
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionBlockMisplacedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionBlockMisplaced(
                CompactionBlockMisplacedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionContentMismatch() {
        val compactionContentMismatch = CompactionContentMismatchErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionContentMismatch(compactionContentMismatch)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).contains(compactionContentMismatch)
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionContentMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionContentMismatch(
                CompactionContentMismatchErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionIncompleteTurn() {
        val compactionIncompleteTurn = CompactionIncompleteTurnErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionIncompleteTurn(compactionIncompleteTurn)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).contains(compactionIncompleteTurn)
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionIncompleteTurnRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionIncompleteTurn(
                CompactionIncompleteTurnErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionNothingToSummarize() {
        val compactionNothingToSummarize =
            CompactionNothingToSummarizeErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionNothingToSummarize(compactionNothingToSummarize)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize())
            .contains(compactionNothingToSummarize)
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionNothingToSummarizeRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionNothingToSummarize(
                CompactionNothingToSummarizeErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionSignatureInvalid() {
        val compactionSignatureInvalid = CompactionSignatureInvalidErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionSignatureInvalid(compactionSignatureInvalid)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).contains(compactionSignatureInvalid)
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionSignatureInvalidRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionSignatureInvalid(
                CompactionSignatureInvalidErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionTooManyToolReferences() {
        val compactionTooManyToolReferences =
            CompactionTooManyToolReferencesErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofCompactionTooManyToolReferences(compactionTooManyToolReferences)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences())
            .contains(compactionTooManyToolReferences)
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionTooManyToolReferencesRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionTooManyToolReferences(
                CompactionTooManyToolReferencesErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionToolChangesMismatch() {
        val compactionToolChangesMismatch =
            CompactionToolChangesMismatchErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofCompactionToolChangesMismatch(compactionToolChangesMismatch)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch())
            .contains(compactionToolChangesMismatch)
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionToolChangesMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionToolChangesMismatch(
                CompactionToolChangesMismatchErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrganizationOnHold() {
        val organizationOnHold =
            OrganizationOnHoldErrorDetails.builder().appealUrl("appeal_url").build()

        val errorDetails = ErrorDetails.ofOrganizationOnHold(organizationOnHold)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).contains(organizationOnHold)
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrganizationOnHoldRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrganizationOnHold(
                OrganizationOnHoldErrorDetails.builder().appealUrl("appeal_url").build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRequestBodyEncodingInvalid() {
        val requestBodyEncodingInvalid = RequestBodyEncodingInvalidErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRequestBodyEncodingInvalid(requestBodyEncodingInvalid)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).contains(requestBodyEncodingInvalid)
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRequestBodyEncodingInvalidRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRequestBodyEncodingInvalid(
                RequestBodyEncodingInvalidErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRequestBodyEncodingUnsupported() {
        val requestBodyEncodingUnsupported =
            RequestBodyEncodingUnsupportedErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofRequestBodyEncodingUnsupported(requestBodyEncodingUnsupported)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported())
            .contains(requestBodyEncodingUnsupported)
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRequestBodyEncodingUnsupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRequestBodyEncodingUnsupported(
                RequestBodyEncodingUnsupportedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofThreadAlreadyContinued() {
        val threadAlreadyContinued = ThreadAlreadyContinuedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofThreadAlreadyContinued(threadAlreadyContinued)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).contains(threadAlreadyContinued)
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofThreadAlreadyContinuedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofThreadAlreadyContinued(
                ThreadAlreadyContinuedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofThreadContinueInFlight() {
        val threadContinueInFlight = ThreadContinueInFlightErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofThreadContinueInFlight(threadContinueInFlight)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).contains(threadContinueInFlight)
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofThreadContinueInFlightRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofThreadContinueInFlight(
                ThreadContinueInFlightErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofThreadFingerprintMismatch() {
        val threadFingerprintMismatch = ThreadFingerprintMismatchErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofThreadFingerprintMismatch(threadFingerprintMismatch)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).contains(threadFingerprintMismatch)
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofThreadFingerprintMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofThreadFingerprintMismatch(
                ThreadFingerprintMismatchErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofThreadUnsupportedRequest() {
        val threadUnsupportedRequest =
            ThreadUnsupportedRequestErrorDetails.builder()
                .addUnsupported(ThreadUnsupportedFeatureName.CACHE_CONTROL)
                .build()

        val errorDetails = ErrorDetails.ofThreadUnsupportedRequest(threadUnsupportedRequest)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).contains(threadUnsupportedRequest)
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofThreadUnsupportedRequestRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofThreadUnsupportedRequest(
                ThreadUnsupportedRequestErrorDetails.builder()
                    .addUnsupported(ThreadUnsupportedFeatureName.CACHE_CONTROL)
                    .build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofToolNameConflict() {
        val toolNameConflict = ToolNameConflictErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofToolNameConflict(toolNameConflict)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).contains(toolNameConflict)
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofToolNameConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofToolNameConflict(ToolNameConflictErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofToolReferenceUnresolved() {
        val toolReferenceUnresolved = ToolReferenceUnresolvedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofToolReferenceUnresolved(toolReferenceUnresolved)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).contains(toolReferenceUnresolved)
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofToolReferenceUnresolvedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofToolReferenceUnresolved(
                ToolReferenceUnresolvedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofThreadNotFound() {
        val threadNotFound = ThreadNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofThreadNotFound(threadNotFound)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).contains(threadNotFound)
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofThreadNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofThreadNotFound(ThreadNotFoundErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSkillReadBlockedUndecryptable() {
        val skillReadBlockedUndecryptable =
            SkillReadBlockedUndecryptableErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofSkillReadBlockedUndecryptable(skillReadBlockedUndecryptable)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable())
            .contains(skillReadBlockedUndecryptable)
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSkillReadBlockedUndecryptableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSkillReadBlockedUndecryptable(
                SkillReadBlockedUndecryptableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRequestBodyTooLargeAfterDecoding() {
        val requestBodyTooLargeAfterDecoding =
            RequestBodyTooLargeAfterDecodingErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofRequestBodyTooLargeAfterDecoding(requestBodyTooLargeAfterDecoding)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding())
            .contains(requestBodyTooLargeAfterDecoding)
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRequestBodyTooLargeAfterDecodingRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRequestBodyTooLargeAfterDecoding(
                RequestBodyTooLargeAfterDecodingErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEnforcedSpendLimitReached() {
        val enforcedSpendLimitReached = EnforcedSpendLimitReachedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEnforcedSpendLimitReached(enforcedSpendLimitReached)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).contains(enforcedSpendLimitReached)
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEnforcedSpendLimitReachedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEnforcedSpendLimitReached(
                EnforcedSpendLimitReachedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRampLimitExceeded() {
        val rampLimitExceeded = RampLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRampLimitExceeded(rampLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).contains(rampLimitExceeded)
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRampLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRampLimitExceeded(RampLimitExceededErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCmekKeyUnreachable() {
        val cmekKeyUnreachable = CmekKeyUnreachableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekKeyUnreachable(cmekKeyUnreachable)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).contains(cmekKeyUnreachable)
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCmekKeyUnreachableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekKeyUnreachable(CmekKeyUnreachableErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCompactionUnavailable() {
        val compactionUnavailable = CompactionUnavailableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCompactionUnavailable(compactionUnavailable)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).contains(compactionUnavailable)
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCompactionUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCompactionUnavailable(
                CompactionUnavailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageQuotaExceeded() {
        val storageQuotaExceeded = StorageQuotaExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageQuotaExceeded(storageQuotaExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).contains(storageQuotaExceeded)
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofStorageQuotaExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageQuotaExceeded(StorageQuotaExceededErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofFileNotDownloadable() {
        val fileNotDownloadable = FileNotDownloadableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofFileNotDownloadable(fileNotDownloadable)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).contains(fileNotDownloadable)
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofFileNotDownloadableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofFileNotDownloadable(FileNotDownloadableErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofFileExpired() {
        val fileExpired = FileExpiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofFileExpired(fileExpired)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).contains(fileExpired)
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofFileExpiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails = ErrorDetails.ofFileExpired(FileExpiredErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCmekContextUnavailable() {
        val cmekContextUnavailable = CmekContextUnavailableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekContextUnavailable(cmekContextUnavailable)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).contains(cmekContextUnavailable)
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCmekContextUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekContextUnavailable(
                CmekContextUnavailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrgApiKeyLimitExceeded() {
        val orgApiKeyLimitExceeded = OrgApiKeyLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofOrgApiKeyLimitExceeded(orgApiKeyLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).contains(orgApiKeyLimitExceeded)
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrgApiKeyLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrgApiKeyLimitExceeded(
                OrgApiKeyLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofWorkspaceApiKeyLimitExceeded() {
        val workspaceApiKeyLimitExceeded =
            WorkspaceApiKeyLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofWorkspaceApiKeyLimitExceeded(workspaceApiKeyLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded())
            .contains(workspaceApiKeyLimitExceeded)
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofWorkspaceApiKeyLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspaceApiKeyLimitExceeded(
                WorkspaceApiKeyLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSandboxEgressRestricted() {
        val sandboxEgressRestricted = SandboxEgressRestrictedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofSandboxEgressRestricted(sandboxEgressRestricted)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).contains(sandboxEgressRestricted)
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSandboxEgressRestrictedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSandboxEgressRestricted(
                SandboxEgressRestrictedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofUserProfileNotFound() {
        val userProfileNotFound = UserProfileNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofUserProfileNotFound(userProfileNotFound)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).contains(userProfileNotFound)
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofUserProfileNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUserProfileNotFound(UserProfileNotFoundErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyInWorkspacePolicy() {
        val externalKeyInWorkspacePolicy =
            ExternalKeyInWorkspacePolicyErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalKeyInWorkspacePolicy(externalKeyInWorkspacePolicy)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy())
            .contains(externalKeyInWorkspacePolicy)
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofExternalKeyInWorkspacePolicyRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyInWorkspacePolicy(
                ExternalKeyInWorkspacePolicyErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofAlreadyMember() {
        val alreadyMember = AlreadyMemberErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofAlreadyMember(alreadyMember)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).contains(alreadyMember)
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofAlreadyMemberRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails = ErrorDetails.ofAlreadyMember(AlreadyMemberErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotInviteEmailsWithUnverifiedDomains() {
        val cannotInviteEmailsWithUnverifiedDomains =
            CannotInviteEmailsWithUnverifiedDomainsErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofCannotInviteEmailsWithUnverifiedDomains(
                cannotInviteEmailsWithUnverifiedDomains
            )

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains())
            .contains(cannotInviteEmailsWithUnverifiedDomains)
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotInviteEmailsWithUnverifiedDomainsRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotInviteEmailsWithUnverifiedDomains(
                CannotInviteEmailsWithUnverifiedDomainsErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEmailDomainNotAllowed() {
        val emailDomainNotAllowed = EmailDomainNotAllowedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEmailDomainNotAllowed(emailDomainNotAllowed)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).contains(emailDomainNotAllowed)
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEmailDomainNotAllowedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEmailDomainNotAllowed(
                EmailDomainNotAllowedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofInviteAlreadyExists() {
        val inviteAlreadyExists = InviteAlreadyExistsErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofInviteAlreadyExists(inviteAlreadyExists)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).contains(inviteAlreadyExists)
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofInviteAlreadyExistsRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofInviteAlreadyExists(InviteAlreadyExistsErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrgMemberLimitExceeded() {
        val orgMemberLimitExceeded = OrgMemberLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofOrgMemberLimitExceeded(orgMemberLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).contains(orgMemberLimitExceeded)
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrgMemberLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrgMemberLimitExceeded(
                OrgMemberLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacGroupGrantsOwnerAccess() {
        val rbacGroupGrantsOwnerAccess = RbacGroupGrantsOwnerAccessErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRbacGroupGrantsOwnerAccess(rbacGroupGrantsOwnerAccess)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).contains(rbacGroupGrantsOwnerAccess)
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRbacGroupGrantsOwnerAccessRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacGroupGrantsOwnerAccess(
                RbacGroupGrantsOwnerAccessErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacGroupOwnerAccessUnconfirmed() {
        val rbacGroupOwnerAccessUnconfirmed =
            RbacGroupOwnerAccessUnconfirmedErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofRbacGroupOwnerAccessUnconfirmed(rbacGroupOwnerAccessUnconfirmed)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed())
            .contains(rbacGroupOwnerAccessUnconfirmed)
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRbacGroupOwnerAccessUnconfirmedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacGroupOwnerAccessUnconfirmed(
                RbacGroupOwnerAccessUnconfirmedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotRemoveLastMember() {
        val cannotRemoveLastMember = CannotRemoveLastMemberErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotRemoveLastMember(cannotRemoveLastMember)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).contains(cannotRemoveLastMember)
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotRemoveLastMemberRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotRemoveLastMember(
                CannotRemoveLastMemberErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofConcurrentMembershipChange() {
        val concurrentMembershipChange = ConcurrentMembershipChangeErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofConcurrentMembershipChange(concurrentMembershipChange)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).contains(concurrentMembershipChange)
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofConcurrentMembershipChangeRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofConcurrentMembershipChange(
                ConcurrentMembershipChangeErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyValidationFailed() {
        val externalKeyValidationFailed = ExternalKeyValidationFailedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalKeyValidationFailed(externalKeyValidationFailed)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).contains(externalKeyValidationFailed)
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofExternalKeyValidationFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyValidationFailed(
                ExternalKeyValidationFailedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyValidationTimedOut() {
        val externalKeyValidationTimedOut =
            ExternalKeyValidationTimedOutErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofExternalKeyValidationTimedOut(externalKeyValidationTimedOut)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut())
            .contains(externalKeyValidationTimedOut)
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofExternalKeyValidationTimedOutRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyValidationTimedOut(
                ExternalKeyValidationTimedOutErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofKeyProfileMismatch() {
        val keyProfileMismatch = KeyProfileMismatchErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofKeyProfileMismatch(keyProfileMismatch)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).contains(keyProfileMismatch)
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofKeyProfileMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofKeyProfileMismatch(KeyProfileMismatchErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofWorkspacePolicyViolation() {
        val workspacePolicyViolation =
            WorkspacePolicyViolationErrorDetails.of(WorkspacePolicyField.EXTERNAL_KEY_ID)

        val errorDetails = ErrorDetails.ofWorkspacePolicyViolation(workspacePolicyViolation)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).contains(workspacePolicyViolation)
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofWorkspacePolicyViolationRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspacePolicyViolation(
                WorkspacePolicyViolationErrorDetails.of(WorkspacePolicyField.EXTERNAL_KEY_ID)
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyChanged() {
        val externalKeyChanged = ExternalKeyChangedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalKeyChanged(externalKeyChanged)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).contains(externalKeyChanged)
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofExternalKeyChangedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyChanged(ExternalKeyChangedErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofWorkspaceMemberLimitExceeded() {
        val workspaceMemberLimitExceeded =
            WorkspaceMemberLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofWorkspaceMemberLimitExceeded(workspaceMemberLimitExceeded)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded())
            .contains(workspaceMemberLimitExceeded)
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofWorkspaceMemberLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspaceMemberLimitExceeded(
                WorkspaceMemberLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrganizationTypeNotSupported() {
        val organizationTypeNotSupported =
            OrganizationTypeNotSupportedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofOrganizationTypeNotSupported(organizationTypeNotSupported)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported())
            .contains(organizationTypeNotSupported)
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrganizationTypeNotSupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrganizationTypeNotSupported(
                OrganizationTypeNotSupportedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEnvironmentCreateDisabled() {
        val environmentCreateDisabled = BetaEnvironmentCreateDisabledErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEnvironmentCreateDisabled(environmentCreateDisabled)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).contains(environmentCreateDisabled)
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEnvironmentCreateDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEnvironmentCreateDisabled(
                BetaEnvironmentCreateDisabledErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEnvironmentArchived() {
        val environmentArchived = BetaEnvironmentArchivedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEnvironmentArchived(environmentArchived)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).contains(environmentArchived)
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEnvironmentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEnvironmentArchived(
                BetaEnvironmentArchivedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofInvalidConfigTypeChange() {
        val invalidConfigTypeChange = BetaInvalidConfigTypeChangeErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofInvalidConfigTypeChange(invalidConfigTypeChange)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).contains(invalidConfigTypeChange)
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofInvalidConfigTypeChangeRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofInvalidConfigTypeChange(
                BetaInvalidConfigTypeChangeErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEnvironmentUpdateDisabled() {
        val environmentUpdateDisabled = BetaEnvironmentUpdateDisabledErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEnvironmentUpdateDisabled(environmentUpdateDisabled)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).contains(environmentUpdateDisabled)
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEnvironmentUpdateDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEnvironmentUpdateDisabled(
                BetaEnvironmentUpdateDisabledErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofHeartbeatPreconditionFailed() {
        val heartbeatPreconditionFailed =
            BetaHeartbeatPreconditionFailedErrorDetails.of(
                BetaWorkLeaseState.builder()
                    .lastHeartbeat("last_heartbeat")
                    .leaseExtended(true)
                    .state(BetaWorkLeaseState.State.QUEUED)
                    .ttlSeconds(0L)
                    .build()
            )

        val errorDetails = ErrorDetails.ofHeartbeatPreconditionFailed(heartbeatPreconditionFailed)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).contains(heartbeatPreconditionFailed)
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofHeartbeatPreconditionFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofHeartbeatPreconditionFailed(
                BetaHeartbeatPreconditionFailedErrorDetails.of(
                    BetaWorkLeaseState.builder()
                        .lastHeartbeat("last_heartbeat")
                        .leaseExtended(true)
                        .state(BetaWorkLeaseState.State.QUEUED)
                        .ttlSeconds(0L)
                        .build()
                )
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofInvalidMetadata() {
        val invalidMetadata = BetaInvalidMetadataErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofInvalidMetadata(invalidMetadata)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).contains(invalidMetadata)
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofInvalidMetadataRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofInvalidMetadata(BetaInvalidMetadataErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutPrimaryOwner() {
        val cannotSignOutPrimaryOwner = BetaCannotSignOutPrimaryOwnerErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotSignOutPrimaryOwner(cannotSignOutPrimaryOwner)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).contains(cannotSignOutPrimaryOwner)
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutPrimaryOwnerRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutPrimaryOwner(
                BetaCannotSignOutPrimaryOwnerErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutSelf() {
        val cannotSignOutSelf = BetaCannotSignOutSelfErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotSignOutSelf(cannotSignOutSelf)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).contains(cannotSignOutSelf)
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutSelfRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutSelf(BetaCannotSignOutSelfErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrganizationNotEnrolledInBeta() {
        val organizationNotEnrolledInBeta =
            BetaOrganizationNotEnrolledInBetaErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofOrganizationNotEnrolledInBeta(organizationNotEnrolledInBeta)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta())
            .contains(organizationNotEnrolledInBeta)
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrganizationNotEnrolledInBetaRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrganizationNotEnrolledInBeta(
                BetaOrganizationNotEnrolledInBetaErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutManagedMember() {
        val cannotSignOutManagedMember =
            BetaCannotSignOutManagedMemberErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotSignOutManagedMember(cannotSignOutManagedMember)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).contains(cannotSignOutManagedMember)
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutManagedMemberRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutManagedMember(
                BetaCannotSignOutManagedMemberErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutMembershipAdmin() {
        val cannotSignOutMembershipAdmin =
            BetaCannotSignOutMembershipAdminErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotSignOutMembershipAdmin(cannotSignOutMembershipAdmin)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin())
            .contains(cannotSignOutMembershipAdmin)
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutMembershipAdminRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutMembershipAdmin(
                BetaCannotSignOutMembershipAdminErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutOwner() {
        val cannotSignOutOwner = BetaCannotSignOutOwnerErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCannotSignOutOwner(cannotSignOutOwner)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).contains(cannotSignOutOwner)
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutOwnerRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutOwner(BetaCannotSignOutOwnerErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCannotSignOutPrivilegedMember() {
        val cannotSignOutPrivilegedMember =
            BetaCannotSignOutPrivilegedMemberErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofCannotSignOutPrivilegedMember(cannotSignOutPrivilegedMember)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember())
            .contains(cannotSignOutPrivilegedMember)
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofCannotSignOutPrivilegedMemberRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCannotSignOutPrivilegedMember(
                BetaCannotSignOutPrivilegedMemberErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSpendLimitsAdminApiWritesNotEnabled() {
        val spendLimitsAdminApiWritesNotEnabled =
            BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofSpendLimitsAdminApiWritesNotEnabled(spendLimitsAdminApiWritesNotEnabled)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled())
            .contains(spendLimitsAdminApiWritesNotEnabled)
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSpendLimitsAdminApiWritesNotEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSpendLimitsAdminApiWritesNotEnabled(
                BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofUnsupportedCharset() {
        val unsupportedCharset = BetaUnsupportedCharsetErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofUnsupportedCharset(unsupportedCharset)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).contains(unsupportedCharset)
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofUnsupportedCharsetRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUnsupportedCharset(BetaUnsupportedCharsetErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofPluginNameTaken() {
        val pluginNameTaken =
            BetaPluginNameTakenErrorDetails.builder().pluginId("plugin_id").build()

        val errorDetails = ErrorDetails.ofPluginNameTaken(pluginNameTaken)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).contains(pluginNameTaken)
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofPluginNameTakenRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofPluginNameTaken(
                BetaPluginNameTakenErrorDetails.builder().pluginId("plugin_id").build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSkillNameTaken() {
        val skillNameTaken = BetaSkillNameTakenErrorDetails.of("skill_name")

        val errorDetails = ErrorDetails.ofSkillNameTaken(skillNameTaken)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).contains(skillNameTaken)
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSkillNameTakenRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSkillNameTaken(BetaSkillNameTakenErrorDetails.of("skill_name"))

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRegistrationPending() {
        val registrationPending =
            BetaRegistrationPendingErrorDetails.builder()
                .pluginId("plugin_id")
                .pluginVersionId("plugin_version_id")
                .build()

        val errorDetails = ErrorDetails.ofRegistrationPending(registrationPending)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).contains(registrationPending)
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofRegistrationPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRegistrationPending(
                BetaRegistrationPendingErrorDetails.builder()
                    .pluginId("plugin_id")
                    .pluginVersionId("plugin_version_id")
                    .build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofScanFailed() {
        val scanFailed = BetaScanFailedErrorDetails.of("plugin_version_id")

        val errorDetails = ErrorDetails.ofScanFailed(scanFailed)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).contains(scanFailed)
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofScanFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofScanFailed(BetaScanFailedErrorDetails.of("plugin_version_id"))

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofScanPending() {
        val scanPending = BetaScanPendingErrorDetails.of("plugin_version_id")

        val errorDetails = ErrorDetails.ofScanPending(scanPending)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).contains(scanPending)
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofScanPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofScanPending(BetaScanPendingErrorDetails.of("plugin_version_id"))

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSlackChannelSetUpSettingsRefused() {
        val slackChannelSetUpSettingsRefused =
            BetaSlackChannelSetUpSettingsRefusedErrorDetails.of("slack_channel_id")

        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpSettingsRefused(slackChannelSetUpSettingsRefused)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused())
            .contains(slackChannelSetUpSettingsRefused)
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSlackChannelSetUpSettingsRefusedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpSettingsRefused(
                BetaSlackChannelSetUpSettingsRefusedErrorDetails.of("slack_channel_id")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofApiKeyScopeMissing() {
        val apiKeyScopeMissing = BetaApiKeyScopeMissingErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofApiKeyScopeMissing(apiKeyScopeMissing)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).contains(apiKeyScopeMissing)
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofApiKeyScopeMissingRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofApiKeyScopeMissing(BetaApiKeyScopeMissingErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofClaudeTagWritesNotEnabled() {
        val claudeTagWritesNotEnabled = BetaClaudeTagWritesNotEnabledErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofClaudeTagWritesNotEnabled(claudeTagWritesNotEnabled)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).contains(claudeTagWritesNotEnabled)
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofClaudeTagWritesNotEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofClaudeTagWritesNotEnabled(
                BetaClaudeTagWritesNotEnabledErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofComplianceConfigurationRestricted() {
        val complianceConfigurationRestricted =
            BetaComplianceConfigurationRestrictedErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofComplianceConfigurationRestricted(complianceConfigurationRestricted)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted())
            .contains(complianceConfigurationRestricted)
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofComplianceConfigurationRestrictedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofComplianceConfigurationRestricted(
                BetaComplianceConfigurationRestrictedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrganizationLevelScopedApiKeyRequired() {
        val organizationLevelScopedApiKeyRequired =
            BetaOrganizationLevelScopedApiKeyRequiredErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofOrganizationLevelScopedApiKeyRequired(
                organizationLevelScopedApiKeyRequired
            )

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired())
            .contains(organizationLevelScopedApiKeyRequired)
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofOrganizationLevelScopedApiKeyRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrganizationLevelScopedApiKeyRequired(
                BetaOrganizationLevelScopedApiKeyRequiredErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofEnvironmentNotFound() {
        val environmentNotFound = BetaEnvironmentNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofEnvironmentNotFound(environmentNotFound)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).contains(environmentNotFound)
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofEnvironmentNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofEnvironmentNotFound(
                BetaEnvironmentNotFoundErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSlackChannelSetUpEnvironmentNotFound() {
        val slackChannelSetUpEnvironmentNotFound =
            BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails.of("slack_channel_id")

        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpEnvironmentNotFound(
                slackChannelSetUpEnvironmentNotFound
            )

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound())
            .contains(slackChannelSetUpEnvironmentNotFound)
        assertThat(errorDetails.slackChannelSetUpSettingsConflict()).isEmpty
    }

    @Test
    fun ofSlackChannelSetUpEnvironmentNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpEnvironmentNotFound(
                BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails.of("slack_channel_id")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSlackChannelSetUpSettingsConflict() {
        val slackChannelSetUpSettingsConflict =
            BetaSlackChannelSetUpSettingsConflictErrorDetails.of("slack_channel_id")

        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpSettingsConflict(slackChannelSetUpSettingsConflict)

        assertThat(errorDetails.availableToolsLimitExceeded()).isEmpty
        assertThat(errorDetails.claudeCodeKeyCreatorNotMember()).isEmpty
        assertThat(errorDetails.claudeCodeVersionTooOld()).isEmpty
        assertThat(errorDetails.cmekKeyDisabled()).isEmpty
        assertThat(errorDetails.cmekKeyNetworkBlocked()).isEmpty
        assertThat(errorDetails.compactionBlockAmbiguous()).isEmpty
        assertThat(errorDetails.compactionBlockMisplaced()).isEmpty
        assertThat(errorDetails.compactionContentMismatch()).isEmpty
        assertThat(errorDetails.compactionIncompleteTurn()).isEmpty
        assertThat(errorDetails.compactionNothingToSummarize()).isEmpty
        assertThat(errorDetails.compactionSignatureInvalid()).isEmpty
        assertThat(errorDetails.compactionTooManyToolReferences()).isEmpty
        assertThat(errorDetails.compactionToolChangesMismatch()).isEmpty
        assertThat(errorDetails.organizationOnHold()).isEmpty
        assertThat(errorDetails.requestBodyEncodingInvalid()).isEmpty
        assertThat(errorDetails.requestBodyEncodingUnsupported()).isEmpty
        assertThat(errorDetails.threadAlreadyContinued()).isEmpty
        assertThat(errorDetails.threadContinueInFlight()).isEmpty
        assertThat(errorDetails.threadFingerprintMismatch()).isEmpty
        assertThat(errorDetails.threadUnsupportedRequest()).isEmpty
        assertThat(errorDetails.toolNameConflict()).isEmpty
        assertThat(errorDetails.toolReferenceUnresolved()).isEmpty
        assertThat(errorDetails.threadNotFound()).isEmpty
        assertThat(errorDetails.skillReadBlockedUndecryptable()).isEmpty
        assertThat(errorDetails.requestBodyTooLargeAfterDecoding()).isEmpty
        assertThat(errorDetails.enforcedSpendLimitReached()).isEmpty
        assertThat(errorDetails.rampLimitExceeded()).isEmpty
        assertThat(errorDetails.cmekKeyUnreachable()).isEmpty
        assertThat(errorDetails.compactionUnavailable()).isEmpty
        assertThat(errorDetails.storageQuotaExceeded()).isEmpty
        assertThat(errorDetails.fileNotDownloadable()).isEmpty
        assertThat(errorDetails.fileExpired()).isEmpty
        assertThat(errorDetails.cmekContextUnavailable()).isEmpty
        assertThat(errorDetails.orgApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.workspaceApiKeyLimitExceeded()).isEmpty
        assertThat(errorDetails.sandboxEgressRestricted()).isEmpty
        assertThat(errorDetails.userProfileNotFound()).isEmpty
        assertThat(errorDetails.externalKeyInWorkspacePolicy()).isEmpty
        assertThat(errorDetails.alreadyMember()).isEmpty
        assertThat(errorDetails.cannotInviteEmailsWithUnverifiedDomains()).isEmpty
        assertThat(errorDetails.emailDomainNotAllowed()).isEmpty
        assertThat(errorDetails.inviteAlreadyExists()).isEmpty
        assertThat(errorDetails.orgMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.rbacGroupGrantsOwnerAccess()).isEmpty
        assertThat(errorDetails.rbacGroupOwnerAccessUnconfirmed()).isEmpty
        assertThat(errorDetails.cannotRemoveLastMember()).isEmpty
        assertThat(errorDetails.concurrentMembershipChange()).isEmpty
        assertThat(errorDetails.externalKeyValidationFailed()).isEmpty
        assertThat(errorDetails.externalKeyValidationTimedOut()).isEmpty
        assertThat(errorDetails.keyProfileMismatch()).isEmpty
        assertThat(errorDetails.workspacePolicyViolation()).isEmpty
        assertThat(errorDetails.externalKeyChanged()).isEmpty
        assertThat(errorDetails.workspaceMemberLimitExceeded()).isEmpty
        assertThat(errorDetails.organizationTypeNotSupported()).isEmpty
        assertThat(errorDetails.environmentCreateDisabled()).isEmpty
        assertThat(errorDetails.environmentArchived()).isEmpty
        assertThat(errorDetails.invalidConfigTypeChange()).isEmpty
        assertThat(errorDetails.environmentUpdateDisabled()).isEmpty
        assertThat(errorDetails.heartbeatPreconditionFailed()).isEmpty
        assertThat(errorDetails.invalidMetadata()).isEmpty
        assertThat(errorDetails.cannotSignOutPrimaryOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutSelf()).isEmpty
        assertThat(errorDetails.organizationNotEnrolledInBeta()).isEmpty
        assertThat(errorDetails.cannotSignOutManagedMember()).isEmpty
        assertThat(errorDetails.cannotSignOutMembershipAdmin()).isEmpty
        assertThat(errorDetails.cannotSignOutOwner()).isEmpty
        assertThat(errorDetails.cannotSignOutPrivilegedMember()).isEmpty
        assertThat(errorDetails.spendLimitsAdminApiWritesNotEnabled()).isEmpty
        assertThat(errorDetails.unsupportedCharset()).isEmpty
        assertThat(errorDetails.pluginNameTaken()).isEmpty
        assertThat(errorDetails.skillNameTaken()).isEmpty
        assertThat(errorDetails.registrationPending()).isEmpty
        assertThat(errorDetails.scanFailed()).isEmpty
        assertThat(errorDetails.scanPending()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsRefused()).isEmpty
        assertThat(errorDetails.apiKeyScopeMissing()).isEmpty
        assertThat(errorDetails.claudeTagWritesNotEnabled()).isEmpty
        assertThat(errorDetails.complianceConfigurationRestricted()).isEmpty
        assertThat(errorDetails.organizationLevelScopedApiKeyRequired()).isEmpty
        assertThat(errorDetails.environmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpEnvironmentNotFound()).isEmpty
        assertThat(errorDetails.slackChannelSetUpSettingsConflict())
            .contains(slackChannelSetUpSettingsConflict)
    }

    @Test
    fun ofSlackChannelSetUpSettingsConflictRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSlackChannelSetUpSettingsConflict(
                BetaSlackChannelSetUpSettingsConflictErrorDetails.of("slack_channel_id")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val errorDetails =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "error_code" to "unknown_variant",
                            "plugin_id" to "plugin_id",
                            "plugin_version_id" to "plugin_version_id",
                            "slack_channel_id" to "slack_channel_id",
                        )
                    ),
                    jacksonTypeRef<ErrorDetails>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { errorDetails.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(errorDetails.pluginId()).contains("plugin_id")
        assertThat(errorDetails.pluginVersionId()).contains("plugin_version_id")
        assertThat(errorDetails.slackChannelId()).contains("slack_channel_id")

        val mismatchedErrorDetails =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "error_code" to "unknown_variant",
                            "plugin_id" to listOf("invalid"),
                            "plugin_version_id" to listOf("invalid"),
                            "slack_channel_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<ErrorDetails>(),
                )

        assertThat(mismatchedErrorDetails.pluginId()).isEmpty
        assertThat(mismatchedErrorDetails.pluginVersionId()).isEmpty
        assertThat(mismatchedErrorDetails.slackChannelId()).isEmpty
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val errorDetails = jsonMapper().convertValue(testCase.value, jacksonTypeRef<ErrorDetails>())

        val e = assertThrows<AnthropicInvalidDataException> { errorDetails.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(errorDetails.pluginId()).isEmpty
        assertThat(errorDetails.pluginVersionId()).isEmpty
        assertThat(errorDetails.slackChannelId()).isEmpty
    }
}
