// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillDeleteResponseTest {

    @Test
    fun create() {
        val skillDeleteResponse =
            SkillDeleteResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .build()

        assertThat(skillDeleteResponse.id()).isEqualTo("skill_01JAbcdefghijklmnopqrstuvw")
        assertThat(skillDeleteResponse.type()).isEqualTo("type")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillDeleteResponse =
            SkillDeleteResponse.builder()
                .id("skill_01JAbcdefghijklmnopqrstuvw")
                .type("type")
                .build()

        val roundtrippedSkillDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillDeleteResponse),
                jacksonTypeRef<SkillDeleteResponse>(),
            )

        assertThat(roundtrippedSkillDeleteResponse).isEqualTo(skillDeleteResponse)
    }
}
