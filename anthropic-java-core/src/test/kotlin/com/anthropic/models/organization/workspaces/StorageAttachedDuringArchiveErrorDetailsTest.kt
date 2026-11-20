package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StorageAttachedDuringArchiveErrorDetailsTest {

    @Test
    fun create() {
        val storageAttachedDuringArchiveErrorDetails =
            StorageAttachedDuringArchiveErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val storageAttachedDuringArchiveErrorDetails =
            StorageAttachedDuringArchiveErrorDetails.builder().build()

        val roundtrippedStorageAttachedDuringArchiveErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(storageAttachedDuringArchiveErrorDetails),
                jacksonTypeRef<StorageAttachedDuringArchiveErrorDetails>(),
            )

        assertThat(roundtrippedStorageAttachedDuringArchiveErrorDetails)
            .isEqualTo(storageAttachedDuringArchiveErrorDetails)
    }
}
