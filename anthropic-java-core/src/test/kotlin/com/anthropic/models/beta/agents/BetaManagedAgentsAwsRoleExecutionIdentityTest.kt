package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAwsRoleExecutionIdentityTest {

    @Test
    fun create() {
        val betaManagedAgentsAwsRoleExecutionIdentity =
            BetaManagedAgentsAwsRoleExecutionIdentity.of("role_arn")

        assertThat(betaManagedAgentsAwsRoleExecutionIdentity.roleArn()).isEqualTo("role_arn")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAwsRoleExecutionIdentity =
            BetaManagedAgentsAwsRoleExecutionIdentity.of("role_arn")

        val roundtrippedBetaManagedAgentsAwsRoleExecutionIdentity =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAwsRoleExecutionIdentity),
                jacksonTypeRef<BetaManagedAgentsAwsRoleExecutionIdentity>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAwsRoleExecutionIdentity)
            .isEqualTo(betaManagedAgentsAwsRoleExecutionIdentity)
    }
}
