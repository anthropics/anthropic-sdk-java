// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMemoryStoreResourceTest {

    @Test
    fun create() {
        val betaManagedAgentsMemoryStoreResource =
            BetaManagedAgentsMemoryStoreResource.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResource.Access.READ_WRITE)
                .description("description")
                .instructions("instructions")
                .mountPath("mount_path")
                .name("name")
                .build()

        assertThat(betaManagedAgentsMemoryStoreResource.memoryStoreId())
            .isEqualTo("memory_store_id")
        assertThat(betaManagedAgentsMemoryStoreResource.type())
            .isEqualTo(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
        assertThat(betaManagedAgentsMemoryStoreResource.access())
            .contains(BetaManagedAgentsMemoryStoreResource.Access.READ_WRITE)
        assertThat(betaManagedAgentsMemoryStoreResource.description()).contains("description")
        assertThat(betaManagedAgentsMemoryStoreResource.instructions()).contains("instructions")
        assertThat(betaManagedAgentsMemoryStoreResource.mountPath()).contains("mount_path")
        assertThat(betaManagedAgentsMemoryStoreResource.name()).contains("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMemoryStoreResource =
            BetaManagedAgentsMemoryStoreResource.builder()
                .memoryStoreId("memory_store_id")
                .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
                .access(BetaManagedAgentsMemoryStoreResource.Access.READ_WRITE)
                .description("description")
                .instructions("instructions")
                .mountPath("mount_path")
                .name("name")
                .build()

        val roundtrippedBetaManagedAgentsMemoryStoreResource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMemoryStoreResource),
                jacksonTypeRef<BetaManagedAgentsMemoryStoreResource>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMemoryStoreResource)
            .isEqualTo(betaManagedAgentsMemoryStoreResource)
    }
}
