package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsExecutionIdentityTest {

    @Test
    fun ofAwsRole() {
        val awsRole = BetaManagedAgentsAwsRoleExecutionIdentity.of("role_arn")

        val betaManagedAgentsExecutionIdentity =
            BetaManagedAgentsExecutionIdentity.ofAwsRole(awsRole)

        assertThat(betaManagedAgentsExecutionIdentity.awsRole()).contains(awsRole)
        assertThat(betaManagedAgentsExecutionIdentity.serviceAccount()).isEmpty
    }

    @Test
    fun ofAwsRoleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsExecutionIdentity =
            BetaManagedAgentsExecutionIdentity.ofAwsRole(
                BetaManagedAgentsAwsRoleExecutionIdentity.of("role_arn")
            )

        val roundtrippedBetaManagedAgentsExecutionIdentity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsExecutionIdentity),
                jacksonTypeRef<BetaManagedAgentsExecutionIdentity>(),
            )

        assertThat(roundtrippedBetaManagedAgentsExecutionIdentity)
            .isEqualTo(betaManagedAgentsExecutionIdentity)
    }

    @Test
    fun ofServiceAccount() {
        val serviceAccount = BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()

        val betaManagedAgentsExecutionIdentity =
            BetaManagedAgentsExecutionIdentity.ofServiceAccount(serviceAccount)

        assertThat(betaManagedAgentsExecutionIdentity.awsRole()).isEmpty
        assertThat(betaManagedAgentsExecutionIdentity.serviceAccount()).contains(serviceAccount)
    }

    @Test
    fun ofServiceAccountRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsExecutionIdentity =
            BetaManagedAgentsExecutionIdentity.ofServiceAccount(
                BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
            )

        val roundtrippedBetaManagedAgentsExecutionIdentity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsExecutionIdentity),
                jacksonTypeRef<BetaManagedAgentsExecutionIdentity>(),
            )

        assertThat(roundtrippedBetaManagedAgentsExecutionIdentity)
            .isEqualTo(betaManagedAgentsExecutionIdentity)
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
        val betaManagedAgentsExecutionIdentity =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsExecutionIdentity>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsExecutionIdentity.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
