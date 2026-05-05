package com.anthropic.credentials

import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.InMemoryIdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.errors.UnexpectedStatusCodeException
import com.anthropic.internal.credentials.WorkloadIdentityCredentials
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.Instant
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources

internal class WorkloadIdentityCredentialsTest {

    @Test
    fun exchangesTokenSuccessfully() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val mockClient = MockHttpClient { request ->
            assertThat(request.method).isEqualTo(HttpMethod.POST)
            assertThat(request.pathSegments).contains("v1", "oauth", "token")

            val body = extractBody(request)
            val params = parseJsonParams(body)
            assertThat(params["grant_type"])
                .isEqualTo("urn:ietf:params:oauth:grant-type:jwt-bearer")
            assertThat(params["assertion"]).isEqualTo("identity-jwt")
            assertThat(params["federation_rule_id"]).isEqualTo("fdrl_123")
            assertThat(params["organization_id"]).isEqualTo("org_123")
            assertThat(params).doesNotContainKey("service_account_id")
            assertThat(params).doesNotContainKey("workspace_id")

            createResponse(200, """{"access_token": "exchanged-token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        val token = credentials.get("https://api.anthropic.com", false)

        assertThat(token.token).isEqualTo("exchanged-token")
        assertThat(token.expiresAt).isNotNull
        assertThat(token.expiresAt!!.isAfter(Instant.now())).isTrue()
    }

    @Test
    fun includesServiceAccountIdWhenProvided() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        var capturedParams: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            capturedParams = parseJsonParams(extractBody(request))
            createResponse(200, """{"access_token": "token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = "svac_456",
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedParams).isNotNull
        assertThat(capturedParams!!["service_account_id"]).isEqualTo("svac_456")
        assertThat(capturedParams!!).doesNotContainKey("workspace_id")
    }

    @Test
    fun includesWorkspaceIdWhenProvided() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        var capturedParams: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            capturedParams = parseJsonParams(extractBody(request))
            createResponse(200, """{"access_token": "token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = "wrkspc_01abc",
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedParams).isNotNull
        assertThat(capturedParams!!["workspace_id"]).isEqualTo("wrkspc_01abc")
    }

    @Test
    fun includesWorkspaceIdDefaultSentinel() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        var capturedParams: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            capturedParams = parseJsonParams(extractBody(request))
            createResponse(200, """{"access_token": "token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = "default",
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedParams).isNotNull
        assertThat(capturedParams!!["workspace_id"]).isEqualTo("default")
    }

    @Test
    // A 401 logs a stderr hint as a side effect; lock so the write doesn't land in another test's
    // captureStderr buffer when running in parallel.
    @ResourceLock(Resources.SYSTEM_ERR)
    fun throwsOnNon200Response() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val serverBody = """{"error": "invalid_grant"}"""
        val mockClient = MockHttpClient { createResponse(401, serverBody) }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { credentials.get("https://api.anthropic.com", false) }
            .isInstanceOfSatisfying(AnthropicServiceException::class.java) { e ->
                assertThat(e.statusCode()).isEqualTo(401)
                assertThat(e.body()).isEqualTo(parsedJson(serverBody))
            }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun logsHintOn401WithoutWorkspaceId() {
        // A 401 token exchange logs a federation-diagnostics hint. With no workspace_id configured
        // the hint additionally suggests setting one. The hint goes to stderr (the SDK's
        // diagnostic-warning channel); the thrown exception type and body() must stay
        // byte-identical to what the server sent so callers that catch
        // UnexpectedStatusCodeException or introspect the body see no deviation.
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val serverBody = """{"error": "unauthorized"}"""
        val mockClient = MockHttpClient { createResponse(401, serverBody) }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        val stderr = captureStderr {
            assertThatThrownBy { credentials.get("https://api.anthropic.com", false) }
                .isInstanceOfSatisfying(UnexpectedStatusCodeException::class.java) { e ->
                    assertThat(e.statusCode()).isEqualTo(401)
                    // The hint must not leak into the error body or message.
                    assertThat(e.body()).isEqualTo(parsedJson(serverBody))
                    assertThat(e.message).doesNotContain("ANTHROPIC_WORKSPACE_ID")
                }
        }

        assertThat(stderr).contains("Ensure your federation rule matches your identity token")
        assertThat(stderr).contains("ANTHROPIC_WORKSPACE_ID")
        assertThat(stderr).contains("View your authentication events")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun logsHintWithoutWorkspaceGuidanceWhenWorkspaceIdSet() {
        // When workspaceId is already set the workspace-scoping suggestion is dropped, but the
        // rest of the hint (check the rule, check the auth events) is still emitted.
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val serverBody = """{"error": "unauthorized"}"""
        val mockClient = MockHttpClient { createResponse(401, serverBody) }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = "wrkspc_x",
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        val stderr = captureStderr {
            assertThatThrownBy { credentials.get("https://api.anthropic.com", false) }
                .isInstanceOfSatisfying(UnexpectedStatusCodeException::class.java) { e ->
                    assertThat(e.statusCode()).isEqualTo(401)
                    assertThat(e.body()).isEqualTo(parsedJson(serverBody))
                }
        }

        assertThat(stderr).contains("Ensure your federation rule")
        assertThat(stderr).contains("View your authentication events")
        assertThat(stderr).doesNotContain("ANTHROPIC_WORKSPACE_ID")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun doesNotLogHintOnNon401WithoutWorkspaceId() {
        // The hint is 401-specific; a 5xx or 400 shouldn't suggest a federation-config change.
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val serverBody = """{"error": "server_error"}"""
        val mockClient = MockHttpClient { createResponse(500, serverBody) }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        val stderr = captureStderr {
            assertThatThrownBy { credentials.get("https://api.anthropic.com", false) }
                .isInstanceOfSatisfying(UnexpectedStatusCodeException::class.java) { e ->
                    assertThat(e.statusCode()).isEqualTo(500)
                    assertThat(e.body()).isEqualTo(parsedJson(serverBody))
                }
        }

        assertThat(stderr).doesNotContain("Ensure your federation rule")
        assertThat(stderr).doesNotContain("View your authentication events")
        assertThat(stderr).doesNotContain("ANTHROPIC_WORKSPACE_ID")
    }

    @Test
    fun validatesBetaHeaders() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        var capturedRequest: HttpRequest? = null
        val mockClient = MockHttpClient { request ->
            capturedRequest = request
            createResponse(200, """{"access_token": "token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                workspaceId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedRequest).isNotNull
        val betaHeader = capturedRequest!!.headers.values("anthropic-beta").joinToString(",")
        assertThat(betaHeader).contains("oauth-2025-04-20")
        assertThat(betaHeader).contains("oidc-federation-2026-04-01")
    }

    /**
     * Runs [block] with `System.err` redirected to a buffer and returns whatever was written. The
     * SDK's diagnostic warnings (including the federation-diagnostics hint) go to stderr, so this
     * is the channel to inspect.
     */
    private fun captureStderr(block: () -> Unit): String {
        val original = System.err
        val buffer = ByteArrayOutputStream()
        System.setErr(PrintStream(buffer, true, "UTF-8"))
        try {
            block()
        } finally {
            System.setErr(original)
        }
        return buffer.toString("UTF-8")
    }

    private fun extractBody(request: HttpRequest): String {
        val body = request.body ?: return ""
        val outputStream = ByteArrayOutputStream()
        body.writeTo(outputStream)
        return outputStream.toString("UTF-8")
    }

    private fun parseJsonParams(body: String): Map<String, String> = jsonMapper().readValue(body)

    /** Parses a JSON string into the same [JsonValue] form the SDK exposes via `body()`. */
    private fun parsedJson(json: String): JsonValue =
        jsonMapper().readValue(json, JsonValue::class.java)

    private fun createResponse(statusCode: Int, body: String): HttpResponse {
        return object : HttpResponse {
            override fun statusCode() = statusCode

            override fun headers() = com.anthropic.core.http.Headers.builder().build()

            override fun body() = ByteArrayInputStream(body.toByteArray())

            override fun close() {}
        }
    }

    private class MockHttpClient(private val syncHandler: (HttpRequest) -> HttpResponse) :
        HttpClient {
        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            return syncHandler(request)
        }

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            return CompletableFuture.completedFuture(syncHandler(request))
        }

        override fun close() {}
    }
}
