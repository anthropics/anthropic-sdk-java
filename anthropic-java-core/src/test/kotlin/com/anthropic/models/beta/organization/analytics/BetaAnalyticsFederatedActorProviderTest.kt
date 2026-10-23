package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaAnalyticsFederatedActorProviderTest {

    @Test
    fun ofAws() {
        val aws =
            BetaAnalyticsFederatedActorAwsProvider.builder()
                .accountId("account_id")
                .signedPrincipal("signed_principal")
                .build()

        val betaAnalyticsFederatedActorProvider = BetaAnalyticsFederatedActorProvider.ofAws(aws)

        assertThat(betaAnalyticsFederatedActorProvider.aws()).contains(aws)
        assertThat(betaAnalyticsFederatedActorProvider.azure()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.gcp()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.oidc()).isEmpty
    }

    @Test
    fun ofAwsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorProvider =
            BetaAnalyticsFederatedActorProvider.ofAws(
                BetaAnalyticsFederatedActorAwsProvider.builder()
                    .accountId("account_id")
                    .signedPrincipal("signed_principal")
                    .build()
            )

        val roundtrippedBetaAnalyticsFederatedActorProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorProvider)
            .isEqualTo(betaAnalyticsFederatedActorProvider)
    }

    @Test
    fun ofAzure() {
        val azure = BetaAnalyticsFederatedActorAzureProvider.of("subscription_id")

        val betaAnalyticsFederatedActorProvider = BetaAnalyticsFederatedActorProvider.ofAzure(azure)

        assertThat(betaAnalyticsFederatedActorProvider.aws()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.azure()).contains(azure)
        assertThat(betaAnalyticsFederatedActorProvider.gcp()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.oidc()).isEmpty
    }

    @Test
    fun ofAzureRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorProvider =
            BetaAnalyticsFederatedActorProvider.ofAzure(
                BetaAnalyticsFederatedActorAzureProvider.of("subscription_id")
            )

        val roundtrippedBetaAnalyticsFederatedActorProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorProvider)
            .isEqualTo(betaAnalyticsFederatedActorProvider)
    }

    @Test
    fun ofGcp() {
        val gcp = BetaAnalyticsFederatedActorGcpProvider.of("project_number")

        val betaAnalyticsFederatedActorProvider = BetaAnalyticsFederatedActorProvider.ofGcp(gcp)

        assertThat(betaAnalyticsFederatedActorProvider.aws()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.azure()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.gcp()).contains(gcp)
        assertThat(betaAnalyticsFederatedActorProvider.oidc()).isEmpty
    }

    @Test
    fun ofGcpRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorProvider =
            BetaAnalyticsFederatedActorProvider.ofGcp(
                BetaAnalyticsFederatedActorGcpProvider.of("project_number")
            )

        val roundtrippedBetaAnalyticsFederatedActorProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorProvider)
            .isEqualTo(betaAnalyticsFederatedActorProvider)
    }

    @Test
    fun ofOidc() {
        val oidc = BetaAnalyticsFederatedActorOidcProvider.of("issuer")

        val betaAnalyticsFederatedActorProvider = BetaAnalyticsFederatedActorProvider.ofOidc(oidc)

        assertThat(betaAnalyticsFederatedActorProvider.aws()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.azure()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.gcp()).isEmpty
        assertThat(betaAnalyticsFederatedActorProvider.oidc()).contains(oidc)
    }

    @Test
    fun ofOidcRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorProvider =
            BetaAnalyticsFederatedActorProvider.ofOidc(
                BetaAnalyticsFederatedActorOidcProvider.of("issuer")
            )

        val roundtrippedBetaAnalyticsFederatedActorProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorProvider)
            .isEqualTo(betaAnalyticsFederatedActorProvider)
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
        val betaAnalyticsFederatedActorProvider =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaAnalyticsFederatedActorProvider>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaAnalyticsFederatedActorProvider.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
