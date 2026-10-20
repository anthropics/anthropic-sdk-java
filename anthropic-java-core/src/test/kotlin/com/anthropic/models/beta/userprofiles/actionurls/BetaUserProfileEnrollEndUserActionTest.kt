package com.anthropic.models.beta.userprofiles.actionurls

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUserProfileEnrollEndUserActionTest {

    @Test
    fun create() {
        val betaUserProfileEnrollEndUserAction =
            BetaUserProfileEnrollEndUserAction.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileEnrollEndUserAction =
            BetaUserProfileEnrollEndUserAction.builder().build()

        val roundtrippedBetaUserProfileEnrollEndUserAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileEnrollEndUserAction),
                jacksonTypeRef<BetaUserProfileEnrollEndUserAction>(),
            )

        assertThat(roundtrippedBetaUserProfileEnrollEndUserAction)
            .isEqualTo(betaUserProfileEnrollEndUserAction)
    }
}
