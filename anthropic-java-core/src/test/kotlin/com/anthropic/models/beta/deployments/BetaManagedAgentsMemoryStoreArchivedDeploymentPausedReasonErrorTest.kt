// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.Type
                        .MEMORY_STORE_ARCHIVED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.type())
            .isEqualTo(
                BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.Type
                    .MEMORY_STORE_ARCHIVED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError =
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.Type
                        .MEMORY_STORE_ARCHIVED_ERROR
                )
                .build()

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
