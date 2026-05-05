package com.anthropic.internal.credentials

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.IdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.errors.UnexpectedStatusCodeException
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.json.JsonMapper
import java.io.OutputStream
import java.time.Instant
import java.util.concurrent.CompletableFuture

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class TokenResponse(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: Long?,
    @JsonProperty("token_type") val tokenType: String? = null,
)

/**
 * [AccessTokenProvider] for workload identity federation.
 *
 * It obtains an identity token from the configured [IdentityTokenProvider] and exchanges it at the
 * token endpoint for an Anthropic access token, scoped by [federationRuleId], [organizationId], and
 * optionally [serviceAccountId] and [workspaceId].
 *
 * @param workspaceId Optional `wrkspc_*` tagged ID, or the literal `"default"` to scope the token
 *   to the organization's default workspace. When omitted the server picks the rule's sole enabled
 *   workspace, else the org default if the rule covers it. Required when the rule enables more than
 *   one non-default workspace, or to target a specific workspace other than the one the server
 *   would pick. The minted token is workspace-scoped: per-request workspace selection (the
 *   `anthropic-workspace-id` header) is not supported for federation tokens — switching workspaces
 *   requires a new token exchange with a different `workspaceId`.
 */
internal class WorkloadIdentityCredentials(
    private val identityTokenProvider: IdentityTokenProvider,
    private val federationRuleId: String,
    private val organizationId: String,
    private val serviceAccountId: String?,
    private val workspaceId: String?,
    private val httpClient: HttpClient,
    private val jsonMapper: JsonMapper,
) : AccessTokenProvider {

    override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
        val identityToken = identityTokenProvider.get()
        return exchangeToken(baseUrl, identityToken)
    }

    override fun getAsync(baseUrl: String, forceRefresh: Boolean): CompletableFuture<AccessToken> {
        return identityTokenProvider
            .getAsync()
            .thenCompose { identityToken ->
                val request = buildRequest(baseUrl, identityToken)
                httpClient.executeAsync(request, RequestOptions.none())
            }
            .thenApply { response -> parseResponse(response) }
    }

    private fun exchangeToken(baseUrl: String, identityToken: String): AccessToken {
        val request = buildRequest(baseUrl, identityToken)
        val response = httpClient.execute(request, RequestOptions.none())
        return parseResponse(response)
    }

    private fun buildRequest(baseUrl: String, identityToken: String): HttpRequest {
        val jsonBody = buildJsonBody(identityToken)
        val body = JsonBody(jsonBody)

        return HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl(baseUrl)
            .addPathSegments("v1", "oauth", "token")
            .putHeader("Content-Type", "application/json")
            .putHeader("anthropic-beta", "oauth-2025-04-20,oidc-federation-2026-04-01")
            .body(body)
            .build()
    }

    private fun buildJsonBody(identityToken: String): Map<String, String> {
        val params =
            mutableMapOf(
                "grant_type" to "urn:ietf:params:oauth:grant-type:jwt-bearer",
                "assertion" to identityToken,
                "federation_rule_id" to federationRuleId,
                "organization_id" to organizationId,
            )
        serviceAccountId?.let { params["service_account_id"] = it }
        workspaceId?.let { params["workspace_id"] = it }
        return params
    }

    private fun parseResponse(response: com.anthropic.core.http.HttpResponse): AccessToken {
        response.use { res ->
            val statusCode = res.statusCode()
            if (statusCode !in 200..299) {
                val bodyText = res.body().bufferedReader().readText()
                warnFederationDiagnostics(statusCode)
                throw UnexpectedStatusCodeException.builder()
                    .statusCode(statusCode)
                    .headers(res.headers())
                    .body(tryParseJson(bodyText))
                    .build()
            }

            val tokenResponse = jsonMapper.readValue(res.body(), TokenResponse::class.java)
            val expiresAt = tokenResponse.expiresIn?.let { Instant.now().plusSeconds(it) }
            return AccessToken(tokenResponse.accessToken, expiresAt)
        }
    }

    /**
     * Logs a diagnostic hint to stderr for a 401 token-exchange response. Other statuses get no
     * hint: a 5xx or 400 is unlikely to be a federation-configuration problem.
     *
     * The hint always reminds the caller to check that the federation rule matches the identity
     * token and points at the Workload identity page of Claude Console for the authentication event
     * log. When no `workspaceId` is configured it additionally suggests setting one, since a rule
     * scoped to multiple workspaces is a common cause the server cannot resolve on its own.
     *
     * The hint is delivered via stderr (matching the SDK's other diagnostic warnings) rather than
     * the exception message: the [UnexpectedStatusCodeException] thrown for token-exchange failures
     * is Stainless-generated, `final`, and has a private constructor, so it can't carry a custom
     * message without changing the catchable type. Both the exception type and `body()` stay
     * byte-identical to what the server returned.
     */
    private fun warnFederationDiagnostics(statusCode: Int) {
        if (statusCode != 401) {
            return
        }
        val parts = mutableListOf("Ensure your federation rule matches your identity token")
        if (workspaceId == null) {
            parts.add(
                "If your federation rule is scoped to multiple workspaces, set the " +
                    "ANTHROPIC_WORKSPACE_ID environment variable, the 'workspace_id' config " +
                    "key, or ProfileConfig.Builder.workspaceId(...)"
            )
        }
        parts.add(
            "View your authentication events in the Workload identity page of Claude Console " +
                "for more details"
        )
        System.err.println("WARNING: " + parts.joinToString(". ") + ".")
    }

    private fun tryParseJson(text: String): JsonValue =
        try {
            jsonMapper.readValue(text, JsonValue::class.java)
        } catch (_: Exception) {
            JsonMissing.of()
        }

    private inner class JsonBody(private val params: Map<String, String>) : HttpRequestBody {
        private val bytes: ByteArray by lazy { jsonMapper.writeValueAsBytes(params) }

        override fun writeTo(outputStream: OutputStream) {
            outputStream.write(bytes)
        }

        override fun contentType(): String = "application/json"

        override fun contentLength(): Long = bytes.size.toLong()

        override fun repeatable(): Boolean = true

        override fun close() {}
    }
}
