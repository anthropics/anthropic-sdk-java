// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.vaults

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsMcpOAuthRefreshUpdateParams
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsMcpOAuthUpdateParams
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsStaticBearerCreateParams
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
import com.anthropic.models.beta.vaults.credentials.CredentialArchiveParams
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialDeleteParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.beta.vaults.credentials.CredentialUpdateParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CredentialServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val betaManagedAgentsCredentialFuture =
            credentialServiceAsync.create(
                CredentialCreateParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .auth(
                        BetaManagedAgentsStaticBearerCreateParams.builder()
                            .token("bearer_exampletoken")
                            .mcpServerUrl("https://example-server.modelcontextprotocol.io/sse")
                            .type(BetaManagedAgentsStaticBearerCreateParams.Type.STATIC_BEARER)
                            .build()
                    )
                    .displayName("Example credential")
                    .metadata(
                        CredentialCreateParams.Metadata.builder()
                            .putAdditionalProperty("environment", JsonValue.from("production"))
                            .build()
                    )
                    .build()
            )

        val betaManagedAgentsCredential = betaManagedAgentsCredentialFuture.get()
        betaManagedAgentsCredential.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val betaManagedAgentsCredentialFuture =
            credentialServiceAsync.retrieve(
                CredentialRetrieveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsCredential = betaManagedAgentsCredentialFuture.get()
        betaManagedAgentsCredential.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val betaManagedAgentsCredentialFuture =
            credentialServiceAsync.update(
                CredentialUpdateParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .auth(
                        BetaManagedAgentsMcpOAuthUpdateParams.builder()
                            .type(BetaManagedAgentsMcpOAuthUpdateParams.Type.MCP_OAUTH)
                            .accessToken("x")
                            .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .refresh(
                                BetaManagedAgentsMcpOAuthRefreshUpdateParams.builder()
                                    .refreshToken("x")
                                    .scope("scope")
                                    .tokenEndpointAuth(
                                        BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.builder()
                                            .type(
                                                BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
                                                    .Type
                                                    .CLIENT_SECRET_BASIC
                                            )
                                            .clientSecret("x")
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .displayName("Example credential")
                    .metadata(
                        CredentialUpdateParams.Metadata.builder()
                            .putAdditionalProperty("environment", JsonValue.from("production"))
                            .build()
                    )
                    .build()
            )

        val betaManagedAgentsCredential = betaManagedAgentsCredentialFuture.get()
        betaManagedAgentsCredential.validate()
    }

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val pageFuture = credentialServiceAsync.list("vlt_011CZkZDLs7fYzm1hXNPeRjv")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val betaManagedAgentsDeletedCredentialFuture =
            credentialServiceAsync.delete(
                CredentialDeleteParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeletedCredential = betaManagedAgentsDeletedCredentialFuture.get()
        betaManagedAgentsDeletedCredential.validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val credentialServiceAsync = client.beta().vaults().credentials()

        val betaManagedAgentsCredentialFuture =
            credentialServiceAsync.archive(
                CredentialArchiveParams.builder()
                    .vaultId("vlt_011CZkZDLs7fYzm1hXNPeRjv")
                    .credentialId("vcrd_011CZkZEMt8gZan2iYOQfSkw")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsCredential = betaManagedAgentsCredentialFuture.get()
        betaManagedAgentsCredential.validate()
    }
}
