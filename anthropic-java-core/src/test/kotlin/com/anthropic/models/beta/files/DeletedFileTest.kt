// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedFileTest {

    @Test
    fun create() {
        val deletedFile = DeletedFile.builder().id("id").type(DeletedFile.Type.FILE_DELETED).build()

        assertThat(deletedFile.id()).isEqualTo("id")
        assertThat(deletedFile.type()).contains(DeletedFile.Type.FILE_DELETED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedFile = DeletedFile.builder().id("id").type(DeletedFile.Type.FILE_DELETED).build()

        val roundtrippedDeletedFile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedFile),
                jacksonTypeRef<DeletedFile>(),
            )

        assertThat(roundtrippedDeletedFile).isEqualTo(deletedFile)
    }
}
