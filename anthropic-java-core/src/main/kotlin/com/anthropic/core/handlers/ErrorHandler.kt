@file:JvmName("ErrorHandler")

package com.anthropic.core.handlers

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonString
import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.ClientClosedRequestException
import com.anthropic.errors.ConflictException
import com.anthropic.errors.ContentTooLargeException
import com.anthropic.errors.GatewayTimeoutException
import com.anthropic.errors.InternalServerException
import com.anthropic.errors.NotFoundException
import com.anthropic.errors.NotImplementedException
import com.anthropic.errors.OverloadedException
import com.anthropic.errors.PermissionDeniedException
import com.anthropic.errors.PreconditionFailedException
import com.anthropic.errors.RateLimitException
import com.anthropic.errors.RequestHeaderFieldsTooLargeException
import com.anthropic.errors.RequestTimeoutException
import com.anthropic.errors.ServiceUnavailableException
import com.anthropic.errors.UnauthorizedException
import com.anthropic.errors.UnexpectedStatusCodeException
import com.anthropic.errors.UnprocessableEntityException
import com.anthropic.models.ClaudeCodeKeyCreatorNotMemberException
import com.anthropic.models.ClaudeCodeVersionTooOldException
import com.anthropic.models.CmekContextUnavailableException
import com.anthropic.models.CmekKeyDisabledException
import com.anthropic.models.CmekKeyNetworkBlockedException
import com.anthropic.models.CmekKeyUnreachableException
import com.anthropic.models.EnforcedSpendLimitReachedException
import com.anthropic.models.OrganizationOnHoldException
import com.anthropic.models.OrganizationTypeNotSupportedException
import com.anthropic.models.RequestBodyEncodingInvalidException
import com.anthropic.models.RequestBodyEncodingUnsupportedException
import com.anthropic.models.RequestBodyTooLargeAfterDecodingException
import com.anthropic.models.beta.BetaUnsupportedCharsetException
import com.anthropic.models.beta.environments.BetaEnvironmentArchivedException
import com.anthropic.models.beta.environments.BetaEnvironmentCreateDisabledException
import com.anthropic.models.beta.environments.BetaEnvironmentNotFoundException
import com.anthropic.models.beta.environments.BetaEnvironmentUpdateDisabledException
import com.anthropic.models.beta.environments.BetaInvalidConfigTypeChangeException
import com.anthropic.models.beta.environments.BetaInvalidMetadataException
import com.anthropic.models.beta.environments.work.BetaHeartbeatPreconditionFailedException
import com.anthropic.models.beta.organization.BetaBillingFrozenException
import com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingException
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledException
import com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedException
import com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredException
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpEnvironmentNotFoundException
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsConflictException
import com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsRefusedException
import com.anthropic.models.beta.organization.plugins.BetaPluginNameTakenException
import com.anthropic.models.beta.organization.plugins.BetaRegistrationPendingException
import com.anthropic.models.beta.organization.plugins.BetaScanFailedException
import com.anthropic.models.beta.organization.plugins.BetaScanPendingException
import com.anthropic.models.beta.organization.plugins.BetaSkillNameTakenException
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitsAdminApiWritesNotEnabledException
import com.anthropic.models.files.FileExpiredException
import com.anthropic.models.files.FileNotDownloadableException
import com.anthropic.models.files.StorageQuotaExceededException
import com.anthropic.models.messages.AvailableToolsLimitExceededException
import com.anthropic.models.messages.CompactionBlockAmbiguousException
import com.anthropic.models.messages.CompactionBlockMisplacedException
import com.anthropic.models.messages.CompactionContentMismatchException
import com.anthropic.models.messages.CompactionIncompleteTurnException
import com.anthropic.models.messages.CompactionNothingToSummarizeException
import com.anthropic.models.messages.CompactionSignatureInvalidException
import com.anthropic.models.messages.CompactionTooManyToolReferencesException
import com.anthropic.models.messages.CompactionToolChangesMismatchException
import com.anthropic.models.messages.CompactionUnavailableException
import com.anthropic.models.messages.ThreadAlreadyContinuedException
import com.anthropic.models.messages.ThreadContinueInFlightException
import com.anthropic.models.messages.ThreadFingerprintMismatchException
import com.anthropic.models.messages.ThreadNotFoundException
import com.anthropic.models.messages.ThreadUnsupportedRequestException
import com.anthropic.models.messages.ToolNameConflictException
import com.anthropic.models.messages.ToolReferenceUnresolvedException
import com.anthropic.models.organization.ConcurrentMembershipChangeException
import com.anthropic.models.organization.OrgMemberLimitExceededException
import com.anthropic.models.organization.apikeys.OrgApiKeyLimitExceededException
import com.anthropic.models.organization.apikeys.WorkspaceApiKeyLimitExceededException
import com.anthropic.models.organization.externalkeys.ExternalKeyChangedException
import com.anthropic.models.organization.externalkeys.ExternalKeyInWorkspacePolicyException
import com.anthropic.models.organization.invites.AlreadyMemberException
import com.anthropic.models.organization.invites.CannotInviteEmailsWithUnverifiedDomainsException
import com.anthropic.models.organization.invites.EmailDomainNotAllowedException
import com.anthropic.models.organization.invites.InviteAlreadyExistsException
import com.anthropic.models.organization.rbacgroups.RbacGroupGrantsOwnerAccessException
import com.anthropic.models.organization.rbacgroups.RbacGroupOwnerAccessUnconfirmedException
import com.anthropic.models.organization.users.CannotRemoveLastMemberException
import com.anthropic.models.organization.workspaces.KeyProfileMismatchException
import com.anthropic.models.organization.workspaces.WorkspaceMemberLimitExceededException
import com.anthropic.models.organization.workspaces.WorkspacePolicyViolationException
import com.anthropic.models.skills.SkillReadBlockedUndecryptableException
import com.anthropic.models.userprofiles.UserProfileNotFoundException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull

@JvmSynthetic
internal fun errorBodyHandler(jsonMapper: JsonMapper): Handler<JsonValue> {
    val reader =
        jsonMapper
            .readerFor(jacksonTypeRef<JsonValue>())
            .with(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)

    return object : Handler<JsonValue> {
        override fun handle(response: HttpResponse): JsonValue {
            val body =
                try {
                    response.body().readBytes()
                } catch (e: Exception) {
                    return JsonMissing.of()
                }
            return try {
                reader.readValue<JsonValue>(body)
            } catch (e: Exception) {
                // Not JSON (e.g. a plain-text gateway error), so keep the raw text.
                if (body.isEmpty()) JsonMissing.of()
                else JsonString.of(body.toString(Charsets.UTF_8))
            }
        }
    }
}

@JvmSynthetic
internal fun errorHandler(errorBodyHandler: Handler<JsonValue>): Handler<HttpResponse> =
    object : Handler<HttpResponse> {
        override fun handle(response: HttpResponse): HttpResponse =
            when (val statusCode = response.statusCode()) {
                in 200..299 -> response
                else -> {
                    val body = errorBodyHandler.handle(response)
                    val errorCode =
                        body.valueAt<String>("error", "details", "error_code").getOrNull()
                    errorCodeExceptions[statusCode to errorCode]?.let {
                        throw it(response.headers(), body)
                    }
                    when (statusCode) {
                        400 ->
                            throw BadRequestException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        401 ->
                            throw UnauthorizedException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        403 ->
                            throw PermissionDeniedException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        404 ->
                            throw NotFoundException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        408 ->
                            throw RequestTimeoutException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        409 ->
                            throw ConflictException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        412 ->
                            throw PreconditionFailedException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        413 ->
                            throw ContentTooLargeException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        422 ->
                            throw UnprocessableEntityException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        429 ->
                            throw RateLimitException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        431 ->
                            throw RequestHeaderFieldsTooLargeException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        499 ->
                            throw ClientClosedRequestException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        501 ->
                            throw NotImplementedException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        503 ->
                            throw ServiceUnavailableException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        504 ->
                            throw GatewayTimeoutException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        529 ->
                            throw OverloadedException.builder()
                                .headers(response.headers())
                                .body(body)
                                .build()
                        in 500..599 ->
                            throw InternalServerException.builder()
                                .statusCode(statusCode)
                                .headers(response.headers())
                                .body(body)
                                .build()
                        else ->
                            throw UnexpectedStatusCodeException.builder()
                                .statusCode(statusCode)
                                .headers(response.headers())
                                .body(body)
                                .build()
                    }
                }
            }
    }

private val errorCodeExceptions:
    Map<Pair<Int, String?>, (Headers, JsonValue) -> AnthropicServiceException> =
    mapOf(
        (400 to "already_member") to
            { headers, body ->
                AlreadyMemberException.builder().headers(headers).body(body).build()
            },
        (400 to "available_tools_limit_exceeded") to
            { headers, body ->
                AvailableToolsLimitExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "cannot_invite_emails_with_unverified_domains") to
            { headers, body ->
                CannotInviteEmailsWithUnverifiedDomainsException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (400 to "cannot_remove_last_member") to
            { headers, body ->
                CannotRemoveLastMemberException.builder().headers(headers).body(body).build()
            },
        (400 to "claude_code_key_creator_not_member") to
            { headers, body ->
                ClaudeCodeKeyCreatorNotMemberException.builder().headers(headers).body(body).build()
            },
        (400 to "claude_code_version_too_old") to
            { headers, body ->
                ClaudeCodeVersionTooOldException.builder().headers(headers).body(body).build()
            },
        (400 to "cmek_key_disabled") to
            { headers, body ->
                CmekKeyDisabledException.builder().headers(headers).body(body).build()
            },
        (400 to "cmek_key_network_blocked") to
            { headers, body ->
                CmekKeyNetworkBlockedException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_block_ambiguous") to
            { headers, body ->
                CompactionBlockAmbiguousException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_block_misplaced") to
            { headers, body ->
                CompactionBlockMisplacedException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_content_mismatch") to
            { headers, body ->
                CompactionContentMismatchException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_incomplete_turn") to
            { headers, body ->
                CompactionIncompleteTurnException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_nothing_to_summarize") to
            { headers, body ->
                CompactionNothingToSummarizeException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_signature_invalid") to
            { headers, body ->
                CompactionSignatureInvalidException.builder().headers(headers).body(body).build()
            },
        (400 to "compaction_too_many_tool_references") to
            { headers, body ->
                CompactionTooManyToolReferencesException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (400 to "compaction_tool_changes_mismatch") to
            { headers, body ->
                CompactionToolChangesMismatchException.builder().headers(headers).body(body).build()
            },
        (400 to "email_domain_not_allowed") to
            { headers, body ->
                EmailDomainNotAllowedException.builder().headers(headers).body(body).build()
            },
        (400 to "environment_archived") to
            { headers, body ->
                BetaEnvironmentArchivedException.builder().headers(headers).body(body).build()
            },
        (400 to "external_key_in_workspace_policy") to
            { headers, body ->
                ExternalKeyInWorkspacePolicyException.builder().headers(headers).body(body).build()
            },
        (400 to "file_not_downloadable") to
            { headers, body ->
                FileNotDownloadableException.builder().headers(headers).body(body).build()
            },
        (400 to "invalid_config_type_change") to
            { headers, body ->
                BetaInvalidConfigTypeChangeException.builder().headers(headers).body(body).build()
            },
        (400 to "invalid_metadata") to
            { headers, body ->
                BetaInvalidMetadataException.builder().headers(headers).body(body).build()
            },
        (400 to "invite_already_exists") to
            { headers, body ->
                InviteAlreadyExistsException.builder().headers(headers).body(body).build()
            },
        (400 to "key_profile_mismatch") to
            { headers, body ->
                KeyProfileMismatchException.builder().headers(headers).body(body).build()
            },
        (400 to "org_api_key_limit_exceeded") to
            { headers, body ->
                OrgApiKeyLimitExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "org_member_limit_exceeded") to
            { headers, body ->
                OrgMemberLimitExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "organization_on_hold") to
            { headers, body ->
                OrganizationOnHoldException.builder().headers(headers).body(body).build()
            },
        (400 to "organization_type_not_supported") to
            { headers, body ->
                OrganizationTypeNotSupportedException.builder().headers(headers).body(body).build()
            },
        (400 to "rbac_group_grants_owner_access") to
            { headers, body ->
                RbacGroupGrantsOwnerAccessException.builder().headers(headers).body(body).build()
            },
        (400 to "rbac_group_owner_access_unconfirmed") to
            { headers, body ->
                RbacGroupOwnerAccessUnconfirmedException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (400 to "request_body_encoding_invalid") to
            { headers, body ->
                RequestBodyEncodingInvalidException.builder().headers(headers).body(body).build()
            },
        (400 to "request_body_encoding_unsupported") to
            { headers, body ->
                RequestBodyEncodingUnsupportedException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (400 to "scan_failed") to
            { headers, body ->
                BetaScanFailedException.builder().headers(headers).body(body).build()
            },
        (400 to "slack_channel_set_up_settings_refused") to
            { headers, body ->
                BetaSlackChannelSetUpSettingsRefusedException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (400 to "storage_quota_exceeded") to
            { headers, body ->
                StorageQuotaExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "thread_already_continued") to
            { headers, body ->
                ThreadAlreadyContinuedException.builder().headers(headers).body(body).build()
            },
        (400 to "thread_continue_in_flight") to
            { headers, body ->
                ThreadContinueInFlightException.builder().headers(headers).body(body).build()
            },
        (400 to "thread_fingerprint_mismatch") to
            { headers, body ->
                ThreadFingerprintMismatchException.builder().headers(headers).body(body).build()
            },
        (400 to "thread_unsupported_request") to
            { headers, body ->
                ThreadUnsupportedRequestException.builder().headers(headers).body(body).build()
            },
        (400 to "tool_name_conflict") to
            { headers, body ->
                ToolNameConflictException.builder().headers(headers).body(body).build()
            },
        (400 to "tool_reference_unresolved") to
            { headers, body ->
                ToolReferenceUnresolvedException.builder().headers(headers).body(body).build()
            },
        (400 to "unsupported_charset") to
            { headers, body ->
                BetaUnsupportedCharsetException.builder().headers(headers).body(body).build()
            },
        (400 to "user_profile_not_found") to
            { headers, body ->
                UserProfileNotFoundException.builder().headers(headers).body(body).build()
            },
        (400 to "workspace_api_key_limit_exceeded") to
            { headers, body ->
                WorkspaceApiKeyLimitExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "workspace_member_limit_exceeded") to
            { headers, body ->
                WorkspaceMemberLimitExceededException.builder().headers(headers).body(body).build()
            },
        (400 to "workspace_policy_violation") to
            { headers, body ->
                WorkspacePolicyViolationException.builder().headers(headers).body(body).build()
            },
        (403 to "api_key_scope_missing") to
            { headers, body ->
                BetaApiKeyScopeMissingException.builder().headers(headers).body(body).build()
            },
        (403 to "billing_frozen") to
            { headers, body ->
                BetaBillingFrozenException.builder().headers(headers).body(body).build()
            },
        (403 to "claude_tag_writes_not_enabled") to
            { headers, body ->
                BetaClaudeTagWritesNotEnabledException.builder().headers(headers).body(body).build()
            },
        (403 to "compliance_configuration_restricted") to
            { headers, body ->
                BetaComplianceConfigurationRestrictedException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (403 to "environment_create_disabled") to
            { headers, body ->
                BetaEnvironmentCreateDisabledException.builder().headers(headers).body(body).build()
            },
        (403 to "environment_update_disabled") to
            { headers, body ->
                BetaEnvironmentUpdateDisabledException.builder().headers(headers).body(body).build()
            },
        (403 to "organization_level_scoped_api_key_required") to
            { headers, body ->
                BetaOrganizationLevelScopedApiKeyRequiredException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (403 to "spend_limits_admin_api_writes_not_enabled") to
            { headers, body ->
                BetaSpendLimitsAdminApiWritesNotEnabledException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (404 to "environment_not_found") to
            { headers, body ->
                BetaEnvironmentNotFoundException.builder().headers(headers).body(body).build()
            },
        (404 to "file_expired") to
            { headers, body ->
                FileExpiredException.builder().headers(headers).body(body).build()
            },
        (404 to "slack_channel_set_up_environment_not_found") to
            { headers, body ->
                BetaSlackChannelSetUpEnvironmentNotFoundException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (404 to "thread_not_found") to
            { headers, body ->
                ThreadNotFoundException.builder().headers(headers).body(body).build()
            },
        (409 to "concurrent_membership_change") to
            { headers, body ->
                ConcurrentMembershipChangeException.builder().headers(headers).body(body).build()
            },
        (409 to "external_key_changed") to
            { headers, body ->
                ExternalKeyChangedException.builder().headers(headers).body(body).build()
            },
        (409 to "plugin_name_taken") to
            { headers, body ->
                BetaPluginNameTakenException.builder().headers(headers).body(body).build()
            },
        (409 to "scan_pending") to
            { headers, body ->
                BetaScanPendingException.builder().headers(headers).body(body).build()
            },
        (409 to "skill_name_taken") to
            { headers, body ->
                BetaSkillNameTakenException.builder().headers(headers).body(body).build()
            },
        (409 to "skill_read_blocked_undecryptable") to
            { headers, body ->
                SkillReadBlockedUndecryptableException.builder().headers(headers).body(body).build()
            },
        (409 to "slack_channel_set_up_settings_conflict") to
            { headers, body ->
                BetaSlackChannelSetUpSettingsConflictException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (412 to "heartbeat_precondition_failed") to
            { headers, body ->
                BetaHeartbeatPreconditionFailedException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (413 to "request_body_too_large_after_decoding") to
            { headers, body ->
                RequestBodyTooLargeAfterDecodingException.builder()
                    .headers(headers)
                    .body(body)
                    .build()
            },
        (429 to "enforced_spend_limit_reached") to
            { headers, body ->
                EnforcedSpendLimitReachedException.builder().headers(headers).body(body).build()
            },
        (503 to "cmek_context_unavailable") to
            { headers, body ->
                CmekContextUnavailableException.builder().headers(headers).body(body).build()
            },
        (503 to "cmek_key_unreachable") to
            { headers, body ->
                CmekKeyUnreachableException.builder().headers(headers).body(body).build()
            },
        (503 to "registration_pending") to
            { headers, body ->
                BetaRegistrationPendingException.builder().headers(headers).body(body).build()
            },
        (529 to "compaction_unavailable") to
            { headers, body ->
                CompactionUnavailableException.builder().headers(headers).body(body).build()
            },
    )
