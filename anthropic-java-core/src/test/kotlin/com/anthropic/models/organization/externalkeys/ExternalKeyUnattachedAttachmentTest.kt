package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyUnattachedAttachmentTest {

    @Test
    fun create() {
        val externalKeyUnattachedAttachment = ExternalKeyUnattachedAttachment.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyUnattachedAttachment = ExternalKeyUnattachedAttachment.builder().build()

        val roundtrippedExternalKeyUnattachedAttachment =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyUnattachedAttachment),
                jacksonTypeRef<ExternalKeyUnattachedAttachment>(),
            )

        assertThat(roundtrippedExternalKeyUnattachedAttachment)
            .isEqualTo(externalKeyUnattachedAttachment)
    }
}
