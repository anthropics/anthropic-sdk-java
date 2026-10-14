package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAwsRoleExecutionIdentityParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAwsRoleExecutionIdentityParams =
            BetaManagedAgentsAwsRoleExecutionIdentityParams.of("role_arn")

        assertThat(betaManagedAgentsAwsRoleExecutionIdentityParams.roleArn()).isEqualTo("role_arn")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAwsRoleExecutionIdentityParams =
            BetaManagedAgentsAwsRoleExecutionIdentityParams.of("role_arn")

        val roundtrippedBetaManagedAgentsAwsRoleExecutionIdentityParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAwsRoleExecutionIdentityParams),
                jacksonTypeRef<BetaManagedAgentsAwsRoleExecutionIdentityParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAwsRoleExecutionIdentityParams)
            .isEqualTo(betaManagedAgentsAwsRoleExecutionIdentityParams)
    }
}
