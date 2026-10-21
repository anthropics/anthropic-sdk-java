package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder().build()

        val roundtrippedBetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
                ),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError)
    }
}
