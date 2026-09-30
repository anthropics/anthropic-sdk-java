package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyAttachedAttachmentTest {

    @Test
    fun create() {
        val externalKeyAttachedAttachment = ExternalKeyAttachedAttachment.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyAttachedAttachment = ExternalKeyAttachedAttachment.builder().build()

        val roundtrippedExternalKeyAttachedAttachment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyAttachedAttachment),
                jacksonTypeRef<ExternalKeyAttachedAttachment>(),
            )

        assertThat(roundtrippedExternalKeyAttachedAttachment)
            .isEqualTo(externalKeyAttachedAttachment)
    }
}
