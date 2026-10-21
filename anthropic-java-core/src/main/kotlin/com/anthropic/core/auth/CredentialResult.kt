package com.anthropic.core.auth

/** The resolved first-party credential of a client. */
internal sealed class CredentialResult {

    /**
     * An [AccessTokenProvider] (OAuth, workload identity federation, profile files), with the
     * config-level metadata that needs to be propagated to the client.
     *
     * There is no organization ID: the server exposes the caller's organization only as the
     * `anthropic-organization-id` *response* header, so the SDK has no way to act on a config-level
     * one today.
     */
    class Token(
        @get:JvmName("provider") val provider: AccessTokenProvider,
        @get:JvmName("baseUrl") val baseUrl: String? = null,
        @get:JvmName("workspaceId") val workspaceId: String? = null,
    ) : CredentialResult()

    /** An API key or an auth token, sent as `Authorization: Bearer`. */
    class Static(@get:JvmName("credential") val credential: String) : CredentialResult()
}
