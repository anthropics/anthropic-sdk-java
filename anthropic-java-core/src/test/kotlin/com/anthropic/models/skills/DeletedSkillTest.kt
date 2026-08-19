// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedSkillTest {

    @Test
    fun create() {
        val deletedSkill = DeletedSkill.of("skill_01JAbcdefghijklmnopqrstuvw")

        assertThat(deletedSkill.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedSkill = DeletedSkill.of("skill_01JAbcdefghijklmnopqrstuvw")

        val roundtrippedDeletedSkill =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedSkill),
                jacksonTypeRef<DeletedSkill>(),
            )

        assertThat(roundtrippedDeletedSkill).isEqualTo(deletedSkill)
    }
}
