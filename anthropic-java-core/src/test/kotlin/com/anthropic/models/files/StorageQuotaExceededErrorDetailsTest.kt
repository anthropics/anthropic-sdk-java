package com.anthropic.models.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StorageQuotaExceededErrorDetailsTest {

    @Test
    fun create() {
        val storageQuotaExceededErrorDetails = StorageQuotaExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val storageQuotaExceededErrorDetails = StorageQuotaExceededErrorDetails.builder().build()

        val roundtrippedStorageQuotaExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(storageQuotaExceededErrorDetails),
                jacksonTypeRef<StorageQuotaExceededErrorDetails>(),
            )

        assertThat(roundtrippedStorageQuotaExceededErrorDetails)
            .isEqualTo(storageQuotaExceededErrorDetails)
    }
}
