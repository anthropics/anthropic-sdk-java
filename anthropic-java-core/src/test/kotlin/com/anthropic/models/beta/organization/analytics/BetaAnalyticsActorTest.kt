package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaAnalyticsActorTest {

    @Test
    fun ofUser() {
        val user =
            BetaAnalyticsReportUserActor.builder()
                .deleted(false)
                .emailAddress("user@emaildomain.com")
                .name("Jane Doe")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val betaAnalyticsActor = BetaAnalyticsActor.ofUser(user)

        assertThat(presentVariants(betaAnalyticsActor))
            .isEqualTo(mapOf("user" to Optional.of(user)))
    }

    @Test
    fun ofUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsActor =
            BetaAnalyticsActor.ofUser(
                BetaAnalyticsReportUserActor.builder()
                    .deleted(false)
                    .emailAddress("user@emaildomain.com")
                    .name("Jane Doe")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val roundtrippedBetaAnalyticsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsActor),
                jacksonTypeRef<BetaAnalyticsActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsActor).isEqualTo(betaAnalyticsActor)
    }

    @Test
    fun ofServiceAccount() {
        val serviceAccount = BetaAnalyticsServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        val betaAnalyticsActor = BetaAnalyticsActor.ofServiceAccount(serviceAccount)

        assertThat(presentVariants(betaAnalyticsActor))
            .isEqualTo(mapOf("serviceAccount" to Optional.of(serviceAccount)))
    }

    @Test
    fun ofServiceAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsActor =
            BetaAnalyticsActor.ofServiceAccount(
                BetaAnalyticsServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")
            )

        val roundtrippedBetaAnalyticsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsActor),
                jacksonTypeRef<BetaAnalyticsActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsActor).isEqualTo(betaAnalyticsActor)
    }

    @Test
    fun ofFederated() {
        val federated =
            BetaAnalyticsFederatedActor.builder()
                .provider(
                    BetaAnalyticsFederatedActorAwsProvider.builder()
                        .accountId("account_id")
                        .signedPrincipal("signed_principal")
                        .build()
                )
                .subject("subject")
                .build()

        val betaAnalyticsActor = BetaAnalyticsActor.ofFederated(federated)

        assertThat(presentVariants(betaAnalyticsActor))
            .isEqualTo(mapOf("federated" to Optional.of(federated)))
    }

    @Test
    fun ofFederatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsActor =
            BetaAnalyticsActor.ofFederated(
                BetaAnalyticsFederatedActor.builder()
                    .provider(
                        BetaAnalyticsFederatedActorAwsProvider.builder()
                            .accountId("account_id")
                            .signedPrincipal("signed_principal")
                            .build()
                    )
                    .subject("subject")
                    .build()
            )

        val roundtrippedBetaAnalyticsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsActor),
                jacksonTypeRef<BetaAnalyticsActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsActor).isEqualTo(betaAnalyticsActor)
    }

    @Test
    fun ofApi() {
        val api = BetaAnalyticsApiActor.of("apikey_01BcDeFgHjKmNpQrStUvWxYz")

        val betaAnalyticsActor = BetaAnalyticsActor.ofApi(api)

        assertThat(presentVariants(betaAnalyticsActor)).isEqualTo(mapOf("api" to Optional.of(api)))
    }

    @Test
    fun ofApiRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsActor =
            BetaAnalyticsActor.ofApi(BetaAnalyticsApiActor.of("apikey_01BcDeFgHjKmNpQrStUvWxYz"))

        val roundtrippedBetaAnalyticsActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsActor),
                jacksonTypeRef<BetaAnalyticsActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsActor).isEqualTo(betaAnalyticsActor)
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
        val betaAnalyticsActor =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaAnalyticsActor>())

        val e = assertThrows<AnthropicInvalidDataException> { betaAnalyticsActor.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(betaAnalyticsActor: BetaAnalyticsActor): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "user" to betaAnalyticsActor.user(),
                "serviceAccount" to betaAnalyticsActor.serviceAccount(),
                "federated" to betaAnalyticsActor.federated(),
                "api" to betaAnalyticsActor.api(),
            )
            .filterValues { it.isPresent }
}
