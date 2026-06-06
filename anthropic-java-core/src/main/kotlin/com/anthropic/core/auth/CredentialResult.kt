package com.anthropic.core.auth

// CredentialResult bundles the resolved first-party credentials with config-level metadata
// that needs to be propagated to the client (e.g. baseUrl, workspaceId). It carries either
// an [AccessTokenProvider] (OAuth, workload identity federation, profile files) or static
// header credentials (API key / auth token), never both.
//
// organizationId is intentionally absent: the server exposes the caller's
// organization only as the anthropic-organization-id *response* header, not
// as a request header, so there is no way for the SDK to act on a
// config-level organization ID today.
internal class CredentialResult
private constructor(
    @get:JvmName("provider") val provider: AccessTokenProvider?,
    @get:JvmName("baseUrl") val baseUrl: String? = null,
    @get:JvmName("workspaceId") val workspaceId: String? = null,
    @get:JvmName("apiKey") val apiKey: String? = null,
    @get:JvmName("authToken") val authToken: String? = null,
) {

    init {
        require((provider != null) != (apiKey != null || authToken != null)) {
            "Exactly one of a token provider or static credentials must be set."
        }
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun token(
            provider: AccessTokenProvider,
            baseUrl: String? = null,
            workspaceId: String? = null,
        ): CredentialResult = CredentialResult(provider, baseUrl, workspaceId)

        @JvmStatic
        fun staticHeaders(apiKey: String?, authToken: String?): CredentialResult =
            CredentialResult(provider = null, apiKey = apiKey, authToken = authToken)
    }
}
