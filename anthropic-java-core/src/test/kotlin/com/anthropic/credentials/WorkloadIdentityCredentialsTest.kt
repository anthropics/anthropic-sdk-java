package com.anthropic.credentials

import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.InMemoryIdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.jsonMapper
import com.anthropic.errors.UnexpectedStatusCodeException
import com.anthropic.internal.credentials.WorkloadIdentityCredentials
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.Instant
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

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

            createResponse(200, """{"access_token": "exchanged-token", "expires_in": 3600}""")
        }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
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
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedParams).isNotNull
        assertThat(capturedParams!!["service_account_id"]).isEqualTo("svac_456")
    }

    @Test
    fun throwsOnNon200Response() {
        val identityProvider = InMemoryIdentityTokenProvider("identity-jwt")
        val mockClient = MockHttpClient { createResponse(401, """{"error": "invalid_grant"}""") }

        val credentials =
            WorkloadIdentityCredentials(
                identityTokenProvider = identityProvider,
                federationRuleId = "fdrl_123",
                organizationId = "org_123",
                serviceAccountId = null,
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        assertThatThrownBy { credentials.get("https://api.anthropic.com", false) }
            .isInstanceOf(UnexpectedStatusCodeException::class.java)
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
                httpClient = mockClient,
                jsonMapper = jsonMapper(),
            )

        credentials.get("https://api.anthropic.com", false)

        assertThat(capturedRequest).isNotNull
        val betaHeader = capturedRequest!!.headers.values("anthropic-beta").joinToString(",")
        assertThat(betaHeader).contains("oauth-2025-04-20")
        assertThat(betaHeader).contains("oidc-federation-2026-04-01")
    }

    private fun extractBody(request: HttpRequest): String {
        val body = request.body ?: return ""
        val outputStream = ByteArrayOutputStream()
        body.writeTo(outputStream)
        return outputStream.toString("UTF-8")
    }

    private fun parseJsonParams(body: String): Map<String, String> = jsonMapper().readValue(body)

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
