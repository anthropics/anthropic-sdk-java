package com.anthropic.core.auth

// CredentialResult bundles an [AccessTokenProvider] with config-level metadata
// that needs to be propagated to the client (e.g. baseUrl, workspaceId).
//
// organizationId is intentionally absent: the server exposes the caller's
// organization only as the anthropic-organization-id *response* header, not
// as a request header, so there is no way for the SDK to act on a
// config-level organization ID today.
internal class CredentialResult(
    @get:JvmName("provider") val provider: AccessTokenProvider,
    @get:JvmName("baseUrl") val baseUrl: String? = null,
    @get:JvmName("workspaceId") val workspaceId: String? = null,
)
