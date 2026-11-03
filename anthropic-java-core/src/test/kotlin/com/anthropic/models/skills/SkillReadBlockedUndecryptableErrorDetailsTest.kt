package com.anthropic.models.skills

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SkillReadBlockedUndecryptableErrorDetailsTest {

    @Test
    fun create() {
        val skillReadBlockedUndecryptableErrorDetails =
            SkillReadBlockedUndecryptableErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val skillReadBlockedUndecryptableErrorDetails =
            SkillReadBlockedUndecryptableErrorDetails.builder().build()

        val roundtrippedSkillReadBlockedUndecryptableErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(skillReadBlockedUndecryptableErrorDetails),
                jacksonTypeRef<SkillReadBlockedUndecryptableErrorDetails>(),
            )

        assertThat(roundtrippedSkillReadBlockedUndecryptableErrorDetails)
            .isEqualTo(skillReadBlockedUndecryptableErrorDetails)
    }
}
