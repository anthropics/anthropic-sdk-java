// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedSkillVersionTest {

    @Test
    fun create() {
        val deletedSkillVersion = DeletedSkillVersion.of("id")

        assertThat(deletedSkillVersion.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedSkillVersion = DeletedSkillVersion.of("id")

        val roundtrippedDeletedSkillVersion =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedSkillVersion),
                jacksonTypeRef<DeletedSkillVersion>(),
            )

        assertThat(roundtrippedDeletedSkillVersion).isEqualTo(deletedSkillVersion)
    }
}
