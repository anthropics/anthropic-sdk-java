package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StorageProfileMismatchErrorDetailsTest {

    @Test
    fun create() {
        val storageProfileMismatchErrorDetails = StorageProfileMismatchErrorDetails.of("field")

        assertThat(storageProfileMismatchErrorDetails.field()).isEqualTo("field")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val storageProfileMismatchErrorDetails = StorageProfileMismatchErrorDetails.of("field")

        val roundtrippedStorageProfileMismatchErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(storageProfileMismatchErrorDetails),
                jacksonTypeRef<StorageProfileMismatchErrorDetails>(),
            )

        assertThat(roundtrippedStorageProfileMismatchErrorDetails)
            .isEqualTo(storageProfileMismatchErrorDetails)
    }
}
