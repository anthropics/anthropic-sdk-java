package com.anthropic.models

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaCmekNotApprovedErrorDetails
import com.anthropic.models.beta.BetaExternalKeyNotFoundErrorDetails
import com.anthropic.models.beta.BetaExternalKeyProviderMismatchErrorDetails
import com.anthropic.models.beta.BetaGcpProjectControlledByAnotherOrganizationErrorDetails
import com.anthropic.models.beta.BetaGcpProjectNotVerifiedErrorDetails
import com.anthropic.models.beta.BetaOrganizationNotEnrolledInBetaErrorDetails
import com.anthropic.models.beta.BetaRegionNotSupportedErrorDetails
import com.anthropic.models.beta.BetaRequestBodyTimeoutErrorDetails
import com.anthropic.models.beta.BetaStorageInUseErrorDetails
import com.anthropic.models.beta.BetaStorageNotApprovedErrorDetails
import com.anthropic.models.beta.BetaUnsupportedCharsetErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentArchivedErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentCreateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentNotFoundErrorDetails
import com.anthropic.models.beta.environments.BetaEnvironmentUpdateDisabledErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidConfigTypeChangeErrorDetails
import com.anthropic.models.beta.environments.BetaInvalidMetadataErrorDetails
import com.anthropic.models.beta.environments.work.BetaHeartbeatPreconditionFailedErrorDetails
import com.anthropic.models.beta.environments.work.BetaWorkLeaseState
import com.anthropic.models.beta.organization.BetaApprovalRequiredErrorDetails
import com.anthropic.models.beta.organization.BetaAuthorizationCheckUnavailableErrorDetails
import com.anthropic.models.beta.organization.BetaHumanReviewNotAvailableErrorDetails
import com.anthropic.models.beta.organization.BetaOrganizationNotEligibleErrorDetails
import com.anthropic.models.beta.organization.claudecode.managedsettings.BetaCmekFeatureUnsupportedErrorDetails
import com.anthropic.models.beta.organization.claudecode.managedsettings.BetaRbacGroupManagedSettingsNotFoundErrorDetails
import com.anthropic.models.beta.organization.claudecode.managedsettings.BetaRbacGroupOrderLimitExceededErrorDetails
import com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsDocumentLimitExceededErrorDetails
import com.anthropic.models.beta.organization.claudecode.managedsettings.BetaSettingsHistoryLimitExceededErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaBundleAttachmentNotSupportedErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaBundleOnCallErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpEnvironmentNotFoundErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsConflictErrorDetails
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsRefusedErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageFailedCheck
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageInUseErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageInWorkspacePolicyErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaExternalStorageNotAvailableErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaGrantRequiredErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaInvalidStorageArnErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaProviderNotAllowedErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageAlreadyRegisteredErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageAttachLimitReachedErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageChangedConcurrentlyErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageNotAttachableErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageProfileStillRequiredErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaStorageVerificationFailedErrorDetails
import com.anthropic.models.beta.organization.externalstorage.BetaUnsupportedRegionErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaPluginNameTakenErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaRegistrationPendingErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaScanFailedErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaScanPendingErrorDetails
import com.anthropic.models.beta.organization.plugins.BetaSkillNameTakenErrorDetails
import com.anthropic.models.beta.organization.rbacgroups.BetaRbacGroupNotFoundErrorDetails
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutManagedMemberErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutMembershipAdminErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutOwnerErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutPrimaryOwnerErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutPrivilegedMemberErrorDetails
import com.anthropic.models.beta.organization.users.BetaCannotSignOutSelfErrorDetails
import com.anthropic.models.beta.organization.workspaces.BetaHumanReviewOnWithExternalStorageErrorDetails
import com.anthropic.models.beta.organization.workspaces.BetaTranscriptsStorageRequiredErrorDetails
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceChangedConcurrentlyErrorDetails
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceChangedConcurrentlyField
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
import com.anthropic.models.messages.ImageDimensionsTooLargeErrorDetails
import com.anthropic.models.messages.ThreadAlreadyContinuedErrorDetails
import com.anthropic.models.messages.ThreadContinueInFlightErrorDetails
import com.anthropic.models.messages.ThreadFingerprintMismatchErrorDetails
import com.anthropic.models.messages.ThreadNotFoundErrorDetails
import com.anthropic.models.messages.ThreadUnsupportedFeatureName
import com.anthropic.models.messages.ThreadUnsupportedRequestErrorDetails
import com.anthropic.models.messages.ToolNameConflictErrorDetails
import com.anthropic.models.messages.ToolReferenceUnresolvedErrorDetails
import com.anthropic.models.organization.ChangeOutcomeUnknownErrorDetails
import com.anthropic.models.organization.ConcurrentMembershipChangeErrorDetails
import com.anthropic.models.organization.OrgMemberLimitExceededErrorDetails
import com.anthropic.models.organization.RbacRoleNotFoundErrorDetails
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
import com.anthropic.models.organization.workspaces.StorageAttachedDuringArchiveErrorDetails
import com.anthropic.models.organization.workspaces.StorageProfileMismatchErrorDetails
import com.anthropic.models.organization.workspaces.WorkspaceCreatedIncompleteErrorDetails
import com.anthropic.models.organization.workspaces.WorkspaceMemberLimitExceededErrorDetails
import com.anthropic.models.organization.workspaces.WorkspacePolicyField
import com.anthropic.models.organization.workspaces.WorkspacePolicyViolationErrorDetails
import com.anthropic.models.skills.SkillReadBlockedUndecryptableErrorDetails
import com.anthropic.models.userprofiles.UserProfileBillingNotAuthorizedErrorDetails
import com.anthropic.models.userprofiles.UserProfileNotBillableErrorDetails
import com.anthropic.models.userprofiles.UserProfileNotFoundErrorDetails
import com.anthropic.models.userprofiles.UserProfileRequiredErrorDetails
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("availableToolsLimitExceeded" to Optional.of(availableToolsLimitExceeded))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("claudeCodeKeyCreatorNotMember" to Optional.of(claudeCodeKeyCreatorNotMember))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("claudeCodeVersionTooOld" to Optional.of(claudeCodeVersionTooOld)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekKeyDisabled" to Optional.of(cmekKeyDisabled)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekKeyNetworkBlocked" to Optional.of(cmekKeyNetworkBlocked)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("compactionBlockAmbiguous" to Optional.of(compactionBlockAmbiguous)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("compactionBlockMisplaced" to Optional.of(compactionBlockMisplaced)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("compactionContentMismatch" to Optional.of(compactionContentMismatch)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("compactionIncompleteTurn" to Optional.of(compactionIncompleteTurn)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("compactionNothingToSummarize" to Optional.of(compactionNothingToSummarize))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("compactionSignatureInvalid" to Optional.of(compactionSignatureInvalid))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "compactionTooManyToolReferences" to
                        Optional.of(compactionTooManyToolReferences)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("compactionToolChangesMismatch" to Optional.of(compactionToolChangesMismatch))
            )
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
    fun ofImageDimensionsTooLarge() {
        val imageDimensionsTooLarge =
            ImageDimensionsTooLargeErrorDetails.builder()
                .maxSideLengthPixels(0L)
                .addPath("string")
                .build()

        val errorDetails = ErrorDetails.ofImageDimensionsTooLarge(imageDimensionsTooLarge)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("imageDimensionsTooLarge" to Optional.of(imageDimensionsTooLarge)))
    }

    @Test
    fun ofImageDimensionsTooLargeRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofImageDimensionsTooLarge(
                ImageDimensionsTooLargeErrorDetails.builder()
                    .maxSideLengthPixels(0L)
                    .addPath("string")
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
    fun ofOrganizationOnHold() {
        val organizationOnHold =
            OrganizationOnHoldErrorDetails.builder().appealUrl("appeal_url").build()

        val errorDetails = ErrorDetails.ofOrganizationOnHold(organizationOnHold)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("organizationOnHold" to Optional.of(organizationOnHold)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("requestBodyEncodingInvalid" to Optional.of(requestBodyEncodingInvalid))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "requestBodyEncodingUnsupported" to Optional.of(requestBodyEncodingUnsupported)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("threadAlreadyContinued" to Optional.of(threadAlreadyContinued)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("threadContinueInFlight" to Optional.of(threadContinueInFlight)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("threadFingerprintMismatch" to Optional.of(threadFingerprintMismatch)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("threadUnsupportedRequest" to Optional.of(threadUnsupportedRequest)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("toolNameConflict" to Optional.of(toolNameConflict)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("toolReferenceUnresolved" to Optional.of(toolReferenceUnresolved)))
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
    fun ofUserProfileRequired() {
        val userProfileRequired = UserProfileRequiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofUserProfileRequired(userProfileRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("userProfileRequired" to Optional.of(userProfileRequired)))
    }

    @Test
    fun ofUserProfileRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUserProfileRequired(UserProfileRequiredErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofLinkedOrganizationBillingUnavailable() {
        val linkedOrganizationBillingUnavailable =
            LinkedOrganizationBillingUnavailableErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofLinkedOrganizationBillingUnavailable(
                linkedOrganizationBillingUnavailable
            )

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "linkedOrganizationBillingUnavailable" to
                        Optional.of(linkedOrganizationBillingUnavailable)
                )
            )
    }

    @Test
    fun ofLinkedOrganizationBillingUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofLinkedOrganizationBillingUnavailable(
                LinkedOrganizationBillingUnavailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofLinkedOrganizationSpendLimitReached() {
        val linkedOrganizationSpendLimitReached =
            LinkedOrganizationSpendLimitReachedErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofLinkedOrganizationSpendLimitReached(linkedOrganizationSpendLimitReached)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "linkedOrganizationSpendLimitReached" to
                        Optional.of(linkedOrganizationSpendLimitReached)
                )
            )
    }

    @Test
    fun ofLinkedOrganizationSpendLimitReachedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofLinkedOrganizationSpendLimitReached(
                LinkedOrganizationSpendLimitReachedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofUserProfileBillingNotAuthorized() {
        val userProfileBillingNotAuthorized =
            UserProfileBillingNotAuthorizedErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofUserProfileBillingNotAuthorized(userProfileBillingNotAuthorized)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "userProfileBillingNotAuthorized" to
                        Optional.of(userProfileBillingNotAuthorized)
                )
            )
    }

    @Test
    fun ofUserProfileBillingNotAuthorizedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUserProfileBillingNotAuthorized(
                UserProfileBillingNotAuthorizedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofUserProfileNotBillable() {
        val userProfileNotBillable = UserProfileNotBillableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofUserProfileNotBillable(userProfileNotBillable)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("userProfileNotBillable" to Optional.of(userProfileNotBillable)))
    }

    @Test
    fun ofUserProfileNotBillableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUserProfileNotBillable(
                UserProfileNotBillableErrorDetails.builder().build()
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("threadNotFound" to Optional.of(threadNotFound)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("skillReadBlockedUndecryptable" to Optional.of(skillReadBlockedUndecryptable))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "requestBodyTooLargeAfterDecoding" to
                        Optional.of(requestBodyTooLargeAfterDecoding)
                )
            )
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
    fun ofContractSpendLimitReached() {
        val contractSpendLimitReached = ContractSpendLimitReachedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofContractSpendLimitReached(contractSpendLimitReached)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("contractSpendLimitReached" to Optional.of(contractSpendLimitReached)))
    }

    @Test
    fun ofContractSpendLimitReachedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofContractSpendLimitReached(
                ContractSpendLimitReachedErrorDetails.builder().build()
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("enforcedSpendLimitReached" to Optional.of(enforcedSpendLimitReached)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("rampLimitExceeded" to Optional.of(rampLimitExceeded)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekKeyUnreachable" to Optional.of(cmekKeyUnreachable)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("compactionUnavailable" to Optional.of(compactionUnavailable)))
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
    fun ofWorkspaceHumanReviewDisabled() {
        val workspaceHumanReviewDisabled =
            WorkspaceHumanReviewDisabledErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofWorkspaceHumanReviewDisabled(workspaceHumanReviewDisabled)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("workspaceHumanReviewDisabled" to Optional.of(workspaceHumanReviewDisabled))
            )
    }

    @Test
    fun ofWorkspaceHumanReviewDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspaceHumanReviewDisabled(
                WorkspaceHumanReviewDisabledErrorDetails.builder().build()
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageQuotaExceeded" to Optional.of(storageQuotaExceeded)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("fileNotDownloadable" to Optional.of(fileNotDownloadable)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("fileExpired" to Optional.of(fileExpired)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekContextUnavailable" to Optional.of(cmekContextUnavailable)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("orgApiKeyLimitExceeded" to Optional.of(orgApiKeyLimitExceeded)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("workspaceApiKeyLimitExceeded" to Optional.of(workspaceApiKeyLimitExceeded))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("sandboxEgressRestricted" to Optional.of(sandboxEgressRestricted)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("userProfileNotFound" to Optional.of(userProfileNotFound)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("externalKeyInWorkspacePolicy" to Optional.of(externalKeyInWorkspacePolicy))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("alreadyMember" to Optional.of(alreadyMember)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "cannotInviteEmailsWithUnverifiedDomains" to
                        Optional.of(cannotInviteEmailsWithUnverifiedDomains)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("emailDomainNotAllowed" to Optional.of(emailDomainNotAllowed)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("inviteAlreadyExists" to Optional.of(inviteAlreadyExists)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("orgMemberLimitExceeded" to Optional.of(orgMemberLimitExceeded)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("rbacGroupGrantsOwnerAccess" to Optional.of(rbacGroupGrantsOwnerAccess))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "rbacGroupOwnerAccessUnconfirmed" to
                        Optional.of(rbacGroupOwnerAccessUnconfirmed)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cannotRemoveLastMember" to Optional.of(cannotRemoveLastMember)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("concurrentMembershipChange" to Optional.of(concurrentMembershipChange))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("externalKeyValidationFailed" to Optional.of(externalKeyValidationFailed))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("externalKeyValidationTimedOut" to Optional.of(externalKeyValidationTimedOut))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("keyProfileMismatch" to Optional.of(keyProfileMismatch)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("workspacePolicyViolation" to Optional.of(workspacePolicyViolation)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("externalKeyChanged" to Optional.of(externalKeyChanged)))
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
    fun ofWorkspaceCreatedIncomplete() {
        val workspaceCreatedIncomplete =
            WorkspaceCreatedIncompleteErrorDetails.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")

        val errorDetails = ErrorDetails.ofWorkspaceCreatedIncomplete(workspaceCreatedIncomplete)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("workspaceCreatedIncomplete" to Optional.of(workspaceCreatedIncomplete))
            )
    }

    @Test
    fun ofWorkspaceCreatedIncompleteRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspaceCreatedIncomplete(
                WorkspaceCreatedIncompleteErrorDetails.of("wrkspc_01AbCdEfGhJkLmNpQrStUvWx")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageProfileMismatch() {
        val storageProfileMismatch = StorageProfileMismatchErrorDetails.of("field")

        val errorDetails = ErrorDetails.ofStorageProfileMismatch(storageProfileMismatch)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageProfileMismatch" to Optional.of(storageProfileMismatch)))
    }

    @Test
    fun ofStorageProfileMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageProfileMismatch(StorageProfileMismatchErrorDetails.of("field"))

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofChangeOutcomeUnknown() {
        val changeOutcomeUnknown = ChangeOutcomeUnknownErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofChangeOutcomeUnknown(changeOutcomeUnknown)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("changeOutcomeUnknown" to Optional.of(changeOutcomeUnknown)))
    }

    @Test
    fun ofChangeOutcomeUnknownRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofChangeOutcomeUnknown(ChangeOutcomeUnknownErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageAttachedDuringArchive() {
        val storageAttachedDuringArchive =
            StorageAttachedDuringArchiveErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageAttachedDuringArchive(storageAttachedDuringArchive)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("storageAttachedDuringArchive" to Optional.of(storageAttachedDuringArchive))
            )
    }

    @Test
    fun ofStorageAttachedDuringArchiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageAttachedDuringArchive(
                StorageAttachedDuringArchiveErrorDetails.builder().build()
            )

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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("workspaceMemberLimitExceeded" to Optional.of(workspaceMemberLimitExceeded))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("organizationTypeNotSupported" to Optional.of(organizationTypeNotSupported))
            )
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
    fun ofParentOrganizationRequired() {
        val parentOrganizationRequired = ParentOrganizationRequiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofParentOrganizationRequired(parentOrganizationRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("parentOrganizationRequired" to Optional.of(parentOrganizationRequired))
            )
    }

    @Test
    fun ofParentOrganizationRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofParentOrganizationRequired(
                ParentOrganizationRequiredErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacRoleNotFound() {
        val rbacRoleNotFound = RbacRoleNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRbacRoleNotFound(rbacRoleNotFound)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("rbacRoleNotFound" to Optional.of(rbacRoleNotFound)))
    }

    @Test
    fun ofRbacRoleNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacRoleNotFound(RbacRoleNotFoundErrorDetails.builder().build())

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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("environmentCreateDisabled" to Optional.of(environmentCreateDisabled)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("environmentArchived" to Optional.of(environmentArchived)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("invalidConfigTypeChange" to Optional.of(invalidConfigTypeChange)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("environmentUpdateDisabled" to Optional.of(environmentUpdateDisabled)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("heartbeatPreconditionFailed" to Optional.of(heartbeatPreconditionFailed))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("invalidMetadata" to Optional.of(invalidMetadata)))
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
    fun ofGrantRequired() {
        val grantRequired = BetaGrantRequiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofGrantRequired(grantRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("grantRequired" to Optional.of(grantRequired)))
    }

    @Test
    fun ofGrantRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofGrantRequired(BetaGrantRequiredErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofOrganizationNotEligible() {
        val organizationNotEligible = BetaOrganizationNotEligibleErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofOrganizationNotEligible(organizationNotEligible)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("organizationNotEligible" to Optional.of(organizationNotEligible)))
    }

    @Test
    fun ofOrganizationNotEligibleRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofOrganizationNotEligible(
                BetaOrganizationNotEligibleErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofProviderNotAllowed() {
        val providerNotAllowed = BetaProviderNotAllowedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofProviderNotAllowed(providerNotAllowed)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("providerNotAllowed" to Optional.of(providerNotAllowed)))
    }

    @Test
    fun ofProviderNotAllowedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofProviderNotAllowed(BetaProviderNotAllowedErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofUnsupportedRegion() {
        val unsupportedRegion = BetaUnsupportedRegionErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofUnsupportedRegion(unsupportedRegion)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("unsupportedRegion" to Optional.of(unsupportedRegion)))
    }

    @Test
    fun ofUnsupportedRegionRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofUnsupportedRegion(BetaUnsupportedRegionErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageAlreadyRegistered() {
        val storageAlreadyRegistered = BetaStorageAlreadyRegisteredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageAlreadyRegistered(storageAlreadyRegistered)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageAlreadyRegistered" to Optional.of(storageAlreadyRegistered)))
    }

    @Test
    fun ofStorageAlreadyRegisteredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageAlreadyRegistered(
                BetaStorageAlreadyRegisteredErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofAuthorizationCheckUnavailable() {
        val authorizationCheckUnavailable =
            BetaAuthorizationCheckUnavailableErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofAuthorizationCheckUnavailable(authorizationCheckUnavailable)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("authorizationCheckUnavailable" to Optional.of(authorizationCheckUnavailable))
            )
    }

    @Test
    fun ofAuthorizationCheckUnavailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofAuthorizationCheckUnavailable(
                BetaAuthorizationCheckUnavailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofInvalidStorageArn() {
        val invalidStorageArn = BetaInvalidStorageArnErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofInvalidStorageArn(invalidStorageArn)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("invalidStorageArn" to Optional.of(invalidStorageArn)))
    }

    @Test
    fun ofInvalidStorageArnRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofInvalidStorageArn(BetaInvalidStorageArnErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalStorageInUse() {
        val externalStorageInUse = BetaExternalStorageInUseErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalStorageInUse(externalStorageInUse)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("externalStorageInUse" to Optional.of(externalStorageInUse)))
    }

    @Test
    fun ofExternalStorageInUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalStorageInUse(
                BetaExternalStorageInUseErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalStorageInWorkspacePolicy() {
        val externalStorageInWorkspacePolicy =
            BetaExternalStorageInWorkspacePolicyErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofExternalStorageInWorkspacePolicy(externalStorageInWorkspacePolicy)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "externalStorageInWorkspacePolicy" to
                        Optional.of(externalStorageInWorkspacePolicy)
                )
            )
    }

    @Test
    fun ofExternalStorageInWorkspacePolicyRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalStorageInWorkspacePolicy(
                BetaExternalStorageInWorkspacePolicyErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageVerificationFailed() {
        val storageVerificationFailed =
            BetaStorageVerificationFailedErrorDetails.builder()
                .addFailedCheck(
                    BetaExternalStorageFailedCheck.builder()
                        .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                        .message("message")
                        .role(BetaExternalStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .field("inference_data_privacy.safeguards_storage")
                .build()

        val errorDetails = ErrorDetails.ofStorageVerificationFailed(storageVerificationFailed)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageVerificationFailed" to Optional.of(storageVerificationFailed)))
    }

    @Test
    fun ofStorageVerificationFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageVerificationFailed(
                BetaStorageVerificationFailedErrorDetails.builder()
                    .addFailedCheck(
                        BetaExternalStorageFailedCheck.builder()
                            .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                            .message("message")
                            .role(BetaExternalStorageFailedCheck.Role.INGEST)
                            .build()
                    )
                    .field("inference_data_privacy.safeguards_storage")
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
    fun ofCmekNotApproved() {
        val cmekNotApproved = BetaCmekNotApprovedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekNotApproved(cmekNotApproved)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekNotApproved" to Optional.of(cmekNotApproved)))
    }

    @Test
    fun ofCmekNotApprovedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekNotApproved(BetaCmekNotApprovedErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyNotFound() {
        val externalKeyNotFound = BetaExternalKeyNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalKeyNotFound(externalKeyNotFound)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("externalKeyNotFound" to Optional.of(externalKeyNotFound)))
    }

    @Test
    fun ofExternalKeyNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyNotFound(
                BetaExternalKeyNotFoundErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalKeyProviderMismatch() {
        val externalKeyProviderMismatch =
            BetaExternalKeyProviderMismatchErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalKeyProviderMismatch(externalKeyProviderMismatch)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("externalKeyProviderMismatch" to Optional.of(externalKeyProviderMismatch))
            )
    }

    @Test
    fun ofExternalKeyProviderMismatchRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalKeyProviderMismatch(
                BetaExternalKeyProviderMismatchErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofGcpProjectNotVerified() {
        val gcpProjectNotVerified = BetaGcpProjectNotVerifiedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofGcpProjectNotVerified(gcpProjectNotVerified)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("gcpProjectNotVerified" to Optional.of(gcpProjectNotVerified)))
    }

    @Test
    fun ofGcpProjectNotVerifiedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofGcpProjectNotVerified(
                BetaGcpProjectNotVerifiedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRegionNotSupported() {
        val regionNotSupported = BetaRegionNotSupportedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRegionNotSupported(regionNotSupported)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("regionNotSupported" to Optional.of(regionNotSupported)))
    }

    @Test
    fun ofRegionNotSupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRegionNotSupported(BetaRegionNotSupportedErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageNotApproved() {
        val storageNotApproved = BetaStorageNotApprovedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageNotApproved(storageNotApproved)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageNotApproved" to Optional.of(storageNotApproved)))
    }

    @Test
    fun ofStorageNotApprovedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageNotApproved(BetaStorageNotApprovedErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofGcpProjectControlledByAnotherOrganization() {
        val gcpProjectControlledByAnotherOrganization =
            BetaGcpProjectControlledByAnotherOrganizationErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofGcpProjectControlledByAnotherOrganization(
                gcpProjectControlledByAnotherOrganization
            )

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "gcpProjectControlledByAnotherOrganization" to
                        Optional.of(gcpProjectControlledByAnotherOrganization)
                )
            )
    }

    @Test
    fun ofGcpProjectControlledByAnotherOrganizationRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofGcpProjectControlledByAnotherOrganization(
                BetaGcpProjectControlledByAnotherOrganizationErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageInUse() {
        val storageInUse = BetaStorageInUseErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageInUse(storageInUse)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageInUse" to Optional.of(storageInUse)))
    }

    @Test
    fun ofStorageInUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageInUse(BetaStorageInUseErrorDetails.builder().build())

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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cannotSignOutPrimaryOwner" to Optional.of(cannotSignOutPrimaryOwner)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cannotSignOutSelf" to Optional.of(cannotSignOutSelf)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("organizationNotEnrolledInBeta" to Optional.of(organizationNotEnrolledInBeta))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("cannotSignOutManagedMember" to Optional.of(cannotSignOutManagedMember))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("cannotSignOutMembershipAdmin" to Optional.of(cannotSignOutMembershipAdmin))
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cannotSignOutOwner" to Optional.of(cannotSignOutOwner)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("cannotSignOutPrivilegedMember" to Optional.of(cannotSignOutPrivilegedMember))
            )
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
    fun ofApprovalRequired() {
        val approvalRequired =
            BetaApprovalRequiredErrorDetails.of("inference_data_privacy.human_review")

        val errorDetails = ErrorDetails.ofApprovalRequired(approvalRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("approvalRequired" to Optional.of(approvalRequired)))
    }

    @Test
    fun ofApprovalRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofApprovalRequired(
                BetaApprovalRequiredErrorDetails.of("inference_data_privacy.human_review")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofExternalStorageNotAvailable() {
        val externalStorageNotAvailable =
            BetaExternalStorageNotAvailableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofExternalStorageNotAvailable(externalStorageNotAvailable)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("externalStorageNotAvailable" to Optional.of(externalStorageNotAvailable))
            )
    }

    @Test
    fun ofExternalStorageNotAvailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofExternalStorageNotAvailable(
                BetaExternalStorageNotAvailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofHumanReviewNotAvailable() {
        val humanReviewNotAvailable = BetaHumanReviewNotAvailableErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofHumanReviewNotAvailable(humanReviewNotAvailable)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("humanReviewNotAvailable" to Optional.of(humanReviewNotAvailable)))
    }

    @Test
    fun ofHumanReviewNotAvailableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofHumanReviewNotAvailable(
                BetaHumanReviewNotAvailableErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofHumanReviewOnWithExternalStorage() {
        val humanReviewOnWithExternalStorage =
            BetaHumanReviewOnWithExternalStorageErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofHumanReviewOnWithExternalStorage(humanReviewOnWithExternalStorage)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "humanReviewOnWithExternalStorage" to
                        Optional.of(humanReviewOnWithExternalStorage)
                )
            )
    }

    @Test
    fun ofHumanReviewOnWithExternalStorageRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofHumanReviewOnWithExternalStorage(
                BetaHumanReviewOnWithExternalStorageErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageNotAttachable() {
        val storageNotAttachable =
            BetaStorageNotAttachableErrorDetails.of("inference_data_privacy.safeguards_storage")

        val errorDetails = ErrorDetails.ofStorageNotAttachable(storageNotAttachable)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageNotAttachable" to Optional.of(storageNotAttachable)))
    }

    @Test
    fun ofStorageNotAttachableRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageNotAttachable(
                BetaStorageNotAttachableErrorDetails.of("inference_data_privacy.safeguards_storage")
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofTranscriptsStorageRequired() {
        val transcriptsStorageRequired =
            BetaTranscriptsStorageRequiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofTranscriptsStorageRequired(transcriptsStorageRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("transcriptsStorageRequired" to Optional.of(transcriptsStorageRequired))
            )
    }

    @Test
    fun ofTranscriptsStorageRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofTranscriptsStorageRequired(
                BetaTranscriptsStorageRequiredErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageAttachLimitReached() {
        val storageAttachLimitReached = BetaStorageAttachLimitReachedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageAttachLimitReached(storageAttachLimitReached)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("storageAttachLimitReached" to Optional.of(storageAttachLimitReached)))
    }

    @Test
    fun ofStorageAttachLimitReachedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageAttachLimitReached(
                BetaStorageAttachLimitReachedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageProfileStillRequired() {
        val storageProfileStillRequired =
            BetaStorageProfileStillRequiredErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofStorageProfileStillRequired(storageProfileStillRequired)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("storageProfileStillRequired" to Optional.of(storageProfileStillRequired))
            )
    }

    @Test
    fun ofStorageProfileStillRequiredRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageProfileStillRequired(
                BetaStorageProfileStillRequiredErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofStorageChangedConcurrently() {
        val storageChangedConcurrently =
            BetaStorageChangedConcurrentlyErrorDetails.of(
                "inference_data_privacy.safeguards_storage"
            )

        val errorDetails = ErrorDetails.ofStorageChangedConcurrently(storageChangedConcurrently)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("storageChangedConcurrently" to Optional.of(storageChangedConcurrently))
            )
    }

    @Test
    fun ofStorageChangedConcurrentlyRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofStorageChangedConcurrently(
                BetaStorageChangedConcurrentlyErrorDetails.of(
                    "inference_data_privacy.safeguards_storage"
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
    fun ofWorkspaceChangedConcurrently() {
        val workspaceChangedConcurrently =
            BetaWorkspaceChangedConcurrentlyErrorDetails.of(
                BetaWorkspaceChangedConcurrentlyField.INFERENCE_DATA_PRIVACY_HUMAN_REVIEW
            )

        val errorDetails = ErrorDetails.ofWorkspaceChangedConcurrently(workspaceChangedConcurrently)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("workspaceChangedConcurrently" to Optional.of(workspaceChangedConcurrently))
            )
    }

    @Test
    fun ofWorkspaceChangedConcurrentlyRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofWorkspaceChangedConcurrently(
                BetaWorkspaceChangedConcurrentlyErrorDetails.of(
                    BetaWorkspaceChangedConcurrentlyField.INFERENCE_DATA_PRIVACY_HUMAN_REVIEW
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
    fun ofSpendLimitsAdminApiWritesNotEnabled() {
        val spendLimitsAdminApiWritesNotEnabled =
            BetaSpendLimitsAdminApiWritesNotEnabledErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofSpendLimitsAdminApiWritesNotEnabled(spendLimitsAdminApiWritesNotEnabled)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "spendLimitsAdminApiWritesNotEnabled" to
                        Optional.of(spendLimitsAdminApiWritesNotEnabled)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("unsupportedCharset" to Optional.of(unsupportedCharset)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("pluginNameTaken" to Optional.of(pluginNameTaken)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("skillNameTaken" to Optional.of(skillNameTaken)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("registrationPending" to Optional.of(registrationPending)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("scanFailed" to Optional.of(scanFailed)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("scanPending" to Optional.of(scanPending)))
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
    fun ofRequestBodyTimeout() {
        val requestBodyTimeout = BetaRequestBodyTimeoutErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRequestBodyTimeout(requestBodyTimeout)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("requestBodyTimeout" to Optional.of(requestBodyTimeout)))
    }

    @Test
    fun ofRequestBodyTimeoutRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRequestBodyTimeout(BetaRequestBodyTimeoutErrorDetails.builder().build())

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSettingsDocumentLimitExceeded() {
        val settingsDocumentLimitExceeded =
            BetaSettingsDocumentLimitExceededErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofSettingsDocumentLimitExceeded(settingsDocumentLimitExceeded)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("settingsDocumentLimitExceeded" to Optional.of(settingsDocumentLimitExceeded))
            )
    }

    @Test
    fun ofSettingsDocumentLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSettingsDocumentLimitExceeded(
                BetaSettingsDocumentLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofSettingsHistoryLimitExceeded() {
        val settingsHistoryLimitExceeded =
            BetaSettingsHistoryLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofSettingsHistoryLimitExceeded(settingsHistoryLimitExceeded)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("settingsHistoryLimitExceeded" to Optional.of(settingsHistoryLimitExceeded))
            )
    }

    @Test
    fun ofSettingsHistoryLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofSettingsHistoryLimitExceeded(
                BetaSettingsHistoryLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofCmekFeatureUnsupported() {
        val cmekFeatureUnsupported = BetaCmekFeatureUnsupportedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofCmekFeatureUnsupported(cmekFeatureUnsupported)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("cmekFeatureUnsupported" to Optional.of(cmekFeatureUnsupported)))
    }

    @Test
    fun ofCmekFeatureUnsupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofCmekFeatureUnsupported(
                BetaCmekFeatureUnsupportedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacGroupManagedSettingsNotFound() {
        val rbacGroupManagedSettingsNotFound =
            BetaRbacGroupManagedSettingsNotFoundErrorDetails.builder().build()

        val errorDetails =
            ErrorDetails.ofRbacGroupManagedSettingsNotFound(rbacGroupManagedSettingsNotFound)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "rbacGroupManagedSettingsNotFound" to
                        Optional.of(rbacGroupManagedSettingsNotFound)
                )
            )
    }

    @Test
    fun ofRbacGroupManagedSettingsNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacGroupManagedSettingsNotFound(
                BetaRbacGroupManagedSettingsNotFoundErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacGroupOrderLimitExceeded() {
        val rbacGroupOrderLimitExceeded =
            BetaRbacGroupOrderLimitExceededErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRbacGroupOrderLimitExceeded(rbacGroupOrderLimitExceeded)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("rbacGroupOrderLimitExceeded" to Optional.of(rbacGroupOrderLimitExceeded))
            )
    }

    @Test
    fun ofRbacGroupOrderLimitExceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacGroupOrderLimitExceeded(
                BetaRbacGroupOrderLimitExceededErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofRbacGroupNotFound() {
        val rbacGroupNotFound = BetaRbacGroupNotFoundErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofRbacGroupNotFound(rbacGroupNotFound)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("rbacGroupNotFound" to Optional.of(rbacGroupNotFound)))
    }

    @Test
    fun ofRbacGroupNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofRbacGroupNotFound(BetaRbacGroupNotFoundErrorDetails.builder().build())

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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "slackChannelSetUpSettingsRefused" to
                        Optional.of(slackChannelSetUpSettingsRefused)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("apiKeyScopeMissing" to Optional.of(apiKeyScopeMissing)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("claudeTagWritesNotEnabled" to Optional.of(claudeTagWritesNotEnabled)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "complianceConfigurationRestricted" to
                        Optional.of(complianceConfigurationRestricted)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "organizationLevelScopedApiKeyRequired" to
                        Optional.of(organizationLevelScopedApiKeyRequired)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("environmentNotFound" to Optional.of(environmentNotFound)))
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "slackChannelSetUpEnvironmentNotFound" to
                        Optional.of(slackChannelSetUpEnvironmentNotFound)
                )
            )
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

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf(
                    "slackChannelSetUpSettingsConflict" to
                        Optional.of(slackChannelSetUpSettingsConflict)
                )
            )
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
    fun ofBundleAttachmentNotSupported() {
        val bundleAttachmentNotSupported =
            BetaBundleAttachmentNotSupportedErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofBundleAttachmentNotSupported(bundleAttachmentNotSupported)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(
                mapOf("bundleAttachmentNotSupported" to Optional.of(bundleAttachmentNotSupported))
            )
    }

    @Test
    fun ofBundleAttachmentNotSupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofBundleAttachmentNotSupported(
                BetaBundleAttachmentNotSupportedErrorDetails.builder().build()
            )

        val roundtrippedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(errorDetails),
                jacksonTypeRef<ErrorDetails>(),
            )

        assertThat(roundtrippedErrorDetails).isEqualTo(errorDetails)
    }

    @Test
    fun ofBundleOnCall() {
        val bundleOnCall = BetaBundleOnCallErrorDetails.builder().build()

        val errorDetails = ErrorDetails.ofBundleOnCall(bundleOnCall)

        assertThat(presentVariants(errorDetails))
            .isEqualTo(mapOf("bundleOnCall" to Optional.of(bundleOnCall)))
    }

    @Test
    fun ofBundleOnCallRoundtrip() {
        val jsonMapper = jsonMapper()
        val errorDetails =
            ErrorDetails.ofBundleOnCall(BetaBundleOnCallErrorDetails.builder().build())

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

    private fun presentVariants(errorDetails: ErrorDetails): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "availableToolsLimitExceeded" to errorDetails.availableToolsLimitExceeded(),
                "claudeCodeKeyCreatorNotMember" to errorDetails.claudeCodeKeyCreatorNotMember(),
                "claudeCodeVersionTooOld" to errorDetails.claudeCodeVersionTooOld(),
                "cmekKeyDisabled" to errorDetails.cmekKeyDisabled(),
                "cmekKeyNetworkBlocked" to errorDetails.cmekKeyNetworkBlocked(),
                "compactionBlockAmbiguous" to errorDetails.compactionBlockAmbiguous(),
                "compactionBlockMisplaced" to errorDetails.compactionBlockMisplaced(),
                "compactionContentMismatch" to errorDetails.compactionContentMismatch(),
                "compactionIncompleteTurn" to errorDetails.compactionIncompleteTurn(),
                "compactionNothingToSummarize" to errorDetails.compactionNothingToSummarize(),
                "compactionSignatureInvalid" to errorDetails.compactionSignatureInvalid(),
                "compactionTooManyToolReferences" to errorDetails.compactionTooManyToolReferences(),
                "compactionToolChangesMismatch" to errorDetails.compactionToolChangesMismatch(),
                "imageDimensionsTooLarge" to errorDetails.imageDimensionsTooLarge(),
                "organizationOnHold" to errorDetails.organizationOnHold(),
                "requestBodyEncodingInvalid" to errorDetails.requestBodyEncodingInvalid(),
                "requestBodyEncodingUnsupported" to errorDetails.requestBodyEncodingUnsupported(),
                "threadAlreadyContinued" to errorDetails.threadAlreadyContinued(),
                "threadContinueInFlight" to errorDetails.threadContinueInFlight(),
                "threadFingerprintMismatch" to errorDetails.threadFingerprintMismatch(),
                "threadUnsupportedRequest" to errorDetails.threadUnsupportedRequest(),
                "toolNameConflict" to errorDetails.toolNameConflict(),
                "toolReferenceUnresolved" to errorDetails.toolReferenceUnresolved(),
                "userProfileRequired" to errorDetails.userProfileRequired(),
                "linkedOrganizationBillingUnavailable" to
                    errorDetails.linkedOrganizationBillingUnavailable(),
                "linkedOrganizationSpendLimitReached" to
                    errorDetails.linkedOrganizationSpendLimitReached(),
                "userProfileBillingNotAuthorized" to errorDetails.userProfileBillingNotAuthorized(),
                "userProfileNotBillable" to errorDetails.userProfileNotBillable(),
                "threadNotFound" to errorDetails.threadNotFound(),
                "skillReadBlockedUndecryptable" to errorDetails.skillReadBlockedUndecryptable(),
                "requestBodyTooLargeAfterDecoding" to
                    errorDetails.requestBodyTooLargeAfterDecoding(),
                "contractSpendLimitReached" to errorDetails.contractSpendLimitReached(),
                "enforcedSpendLimitReached" to errorDetails.enforcedSpendLimitReached(),
                "rampLimitExceeded" to errorDetails.rampLimitExceeded(),
                "cmekKeyUnreachable" to errorDetails.cmekKeyUnreachable(),
                "compactionUnavailable" to errorDetails.compactionUnavailable(),
                "workspaceHumanReviewDisabled" to errorDetails.workspaceHumanReviewDisabled(),
                "storageQuotaExceeded" to errorDetails.storageQuotaExceeded(),
                "fileNotDownloadable" to errorDetails.fileNotDownloadable(),
                "fileExpired" to errorDetails.fileExpired(),
                "cmekContextUnavailable" to errorDetails.cmekContextUnavailable(),
                "orgApiKeyLimitExceeded" to errorDetails.orgApiKeyLimitExceeded(),
                "workspaceApiKeyLimitExceeded" to errorDetails.workspaceApiKeyLimitExceeded(),
                "sandboxEgressRestricted" to errorDetails.sandboxEgressRestricted(),
                "userProfileNotFound" to errorDetails.userProfileNotFound(),
                "externalKeyInWorkspacePolicy" to errorDetails.externalKeyInWorkspacePolicy(),
                "alreadyMember" to errorDetails.alreadyMember(),
                "cannotInviteEmailsWithUnverifiedDomains" to
                    errorDetails.cannotInviteEmailsWithUnverifiedDomains(),
                "emailDomainNotAllowed" to errorDetails.emailDomainNotAllowed(),
                "inviteAlreadyExists" to errorDetails.inviteAlreadyExists(),
                "orgMemberLimitExceeded" to errorDetails.orgMemberLimitExceeded(),
                "rbacGroupGrantsOwnerAccess" to errorDetails.rbacGroupGrantsOwnerAccess(),
                "rbacGroupOwnerAccessUnconfirmed" to errorDetails.rbacGroupOwnerAccessUnconfirmed(),
                "cannotRemoveLastMember" to errorDetails.cannotRemoveLastMember(),
                "concurrentMembershipChange" to errorDetails.concurrentMembershipChange(),
                "externalKeyValidationFailed" to errorDetails.externalKeyValidationFailed(),
                "externalKeyValidationTimedOut" to errorDetails.externalKeyValidationTimedOut(),
                "keyProfileMismatch" to errorDetails.keyProfileMismatch(),
                "workspacePolicyViolation" to errorDetails.workspacePolicyViolation(),
                "externalKeyChanged" to errorDetails.externalKeyChanged(),
                "workspaceCreatedIncomplete" to errorDetails.workspaceCreatedIncomplete(),
                "storageProfileMismatch" to errorDetails.storageProfileMismatch(),
                "changeOutcomeUnknown" to errorDetails.changeOutcomeUnknown(),
                "storageAttachedDuringArchive" to errorDetails.storageAttachedDuringArchive(),
                "workspaceMemberLimitExceeded" to errorDetails.workspaceMemberLimitExceeded(),
                "organizationTypeNotSupported" to errorDetails.organizationTypeNotSupported(),
                "parentOrganizationRequired" to errorDetails.parentOrganizationRequired(),
                "rbacRoleNotFound" to errorDetails.rbacRoleNotFound(),
                "environmentCreateDisabled" to errorDetails.environmentCreateDisabled(),
                "environmentArchived" to errorDetails.environmentArchived(),
                "invalidConfigTypeChange" to errorDetails.invalidConfigTypeChange(),
                "environmentUpdateDisabled" to errorDetails.environmentUpdateDisabled(),
                "heartbeatPreconditionFailed" to errorDetails.heartbeatPreconditionFailed(),
                "invalidMetadata" to errorDetails.invalidMetadata(),
                "grantRequired" to errorDetails.grantRequired(),
                "organizationNotEligible" to errorDetails.organizationNotEligible(),
                "providerNotAllowed" to errorDetails.providerNotAllowed(),
                "unsupportedRegion" to errorDetails.unsupportedRegion(),
                "storageAlreadyRegistered" to errorDetails.storageAlreadyRegistered(),
                "authorizationCheckUnavailable" to errorDetails.authorizationCheckUnavailable(),
                "invalidStorageArn" to errorDetails.invalidStorageArn(),
                "externalStorageInUse" to errorDetails.externalStorageInUse(),
                "externalStorageInWorkspacePolicy" to
                    errorDetails.externalStorageInWorkspacePolicy(),
                "storageVerificationFailed" to errorDetails.storageVerificationFailed(),
                "cmekNotApproved" to errorDetails.cmekNotApproved(),
                "externalKeyNotFound" to errorDetails.externalKeyNotFound(),
                "externalKeyProviderMismatch" to errorDetails.externalKeyProviderMismatch(),
                "gcpProjectNotVerified" to errorDetails.gcpProjectNotVerified(),
                "regionNotSupported" to errorDetails.regionNotSupported(),
                "storageNotApproved" to errorDetails.storageNotApproved(),
                "gcpProjectControlledByAnotherOrganization" to
                    errorDetails.gcpProjectControlledByAnotherOrganization(),
                "storageInUse" to errorDetails.storageInUse(),
                "cannotSignOutPrimaryOwner" to errorDetails.cannotSignOutPrimaryOwner(),
                "cannotSignOutSelf" to errorDetails.cannotSignOutSelf(),
                "organizationNotEnrolledInBeta" to errorDetails.organizationNotEnrolledInBeta(),
                "cannotSignOutManagedMember" to errorDetails.cannotSignOutManagedMember(),
                "cannotSignOutMembershipAdmin" to errorDetails.cannotSignOutMembershipAdmin(),
                "cannotSignOutOwner" to errorDetails.cannotSignOutOwner(),
                "cannotSignOutPrivilegedMember" to errorDetails.cannotSignOutPrivilegedMember(),
                "approvalRequired" to errorDetails.approvalRequired(),
                "externalStorageNotAvailable" to errorDetails.externalStorageNotAvailable(),
                "humanReviewNotAvailable" to errorDetails.humanReviewNotAvailable(),
                "humanReviewOnWithExternalStorage" to
                    errorDetails.humanReviewOnWithExternalStorage(),
                "storageNotAttachable" to errorDetails.storageNotAttachable(),
                "transcriptsStorageRequired" to errorDetails.transcriptsStorageRequired(),
                "storageAttachLimitReached" to errorDetails.storageAttachLimitReached(),
                "storageProfileStillRequired" to errorDetails.storageProfileStillRequired(),
                "storageChangedConcurrently" to errorDetails.storageChangedConcurrently(),
                "workspaceChangedConcurrently" to errorDetails.workspaceChangedConcurrently(),
                "spendLimitsAdminApiWritesNotEnabled" to
                    errorDetails.spendLimitsAdminApiWritesNotEnabled(),
                "unsupportedCharset" to errorDetails.unsupportedCharset(),
                "pluginNameTaken" to errorDetails.pluginNameTaken(),
                "skillNameTaken" to errorDetails.skillNameTaken(),
                "registrationPending" to errorDetails.registrationPending(),
                "scanFailed" to errorDetails.scanFailed(),
                "scanPending" to errorDetails.scanPending(),
                "requestBodyTimeout" to errorDetails.requestBodyTimeout(),
                "settingsDocumentLimitExceeded" to errorDetails.settingsDocumentLimitExceeded(),
                "settingsHistoryLimitExceeded" to errorDetails.settingsHistoryLimitExceeded(),
                "cmekFeatureUnsupported" to errorDetails.cmekFeatureUnsupported(),
                "rbacGroupManagedSettingsNotFound" to
                    errorDetails.rbacGroupManagedSettingsNotFound(),
                "rbacGroupOrderLimitExceeded" to errorDetails.rbacGroupOrderLimitExceeded(),
                "rbacGroupNotFound" to errorDetails.rbacGroupNotFound(),
                "slackChannelSetUpSettingsRefused" to
                    errorDetails.slackChannelSetUpSettingsRefused(),
                "apiKeyScopeMissing" to errorDetails.apiKeyScopeMissing(),
                "claudeTagWritesNotEnabled" to errorDetails.claudeTagWritesNotEnabled(),
                "complianceConfigurationRestricted" to
                    errorDetails.complianceConfigurationRestricted(),
                "organizationLevelScopedApiKeyRequired" to
                    errorDetails.organizationLevelScopedApiKeyRequired(),
                "environmentNotFound" to errorDetails.environmentNotFound(),
                "slackChannelSetUpEnvironmentNotFound" to
                    errorDetails.slackChannelSetUpEnvironmentNotFound(),
                "slackChannelSetUpSettingsConflict" to
                    errorDetails.slackChannelSetUpSettingsConflict(),
                "bundleAttachmentNotSupported" to errorDetails.bundleAttachmentNotSupported(),
                "bundleOnCall" to errorDetails.bundleOnCall(),
            )
            .filterValues { it.isPresent }
}
