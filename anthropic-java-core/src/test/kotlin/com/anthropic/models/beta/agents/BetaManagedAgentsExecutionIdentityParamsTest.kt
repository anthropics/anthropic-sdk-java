package com.anthropic.models.beta.agents

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

internal class BetaManagedAgentsExecutionIdentityParamsTest {

    @Test
    fun ofAwsRole() {
        val awsRole =
            BetaManagedAgentsAwsRoleExecutionIdentityParams.of("arn:aws:iam::228601553692:role/T")

        val betaManagedAgentsExecutionIdentityParams =
            BetaManagedAgentsExecutionIdentityParams.ofAwsRole(awsRole)

        assertThat(presentVariants(betaManagedAgentsExecutionIdentityParams))
            .isEqualTo(mapOf("awsRole" to Optional.of(awsRole)))
    }

    @Test
    fun ofAwsRoleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsExecutionIdentityParams =
            BetaManagedAgentsExecutionIdentityParams.ofAwsRole(
                BetaManagedAgentsAwsRoleExecutionIdentityParams.of(
                    "arn:aws:iam::228601553692:role/T"
                )
            )

        val roundtrippedBetaManagedAgentsExecutionIdentityParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsExecutionIdentityParams),
                jacksonTypeRef<BetaManagedAgentsExecutionIdentityParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsExecutionIdentityParams)
            .isEqualTo(betaManagedAgentsExecutionIdentityParams)
    }

    @Test
    fun ofServiceAccount() {
        val serviceAccount =
            BetaManagedAgentsServiceAccountExecutionIdentityParams.builder().build()

        val betaManagedAgentsExecutionIdentityParams =
            BetaManagedAgentsExecutionIdentityParams.ofServiceAccount(serviceAccount)

        assertThat(presentVariants(betaManagedAgentsExecutionIdentityParams))
            .isEqualTo(mapOf("serviceAccount" to Optional.of(serviceAccount)))
    }

    @Test
    fun ofServiceAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsExecutionIdentityParams =
            BetaManagedAgentsExecutionIdentityParams.ofServiceAccount(
                BetaManagedAgentsServiceAccountExecutionIdentityParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsExecutionIdentityParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsExecutionIdentityParams),
                jacksonTypeRef<BetaManagedAgentsExecutionIdentityParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsExecutionIdentityParams)
            .isEqualTo(betaManagedAgentsExecutionIdentityParams)
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
        val betaManagedAgentsExecutionIdentityParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsExecutionIdentityParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsExecutionIdentityParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsExecutionIdentityParams: BetaManagedAgentsExecutionIdentityParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "awsRole" to betaManagedAgentsExecutionIdentityParams.awsRole(),
                "serviceAccount" to betaManagedAgentsExecutionIdentityParams.serviceAccount(),
            )
            .filterValues { it.isPresent }
}
