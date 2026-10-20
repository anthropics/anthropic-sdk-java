package com.anthropic.models.beta.userprofiles.actionurls

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaUserProfileActionTest {

    @Test
    fun ofEnrollEndUser() {
        val enrollEndUser = BetaUserProfileEnrollEndUserAction.builder().build()

        val betaUserProfileAction = BetaUserProfileAction.ofEnrollEndUser(enrollEndUser)

        assertThat(betaUserProfileAction.enrollEndUser()).contains(enrollEndUser)
    }

    @Test
    fun ofEnrollEndUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaUserProfileAction =
            BetaUserProfileAction.ofEnrollEndUser(
                BetaUserProfileEnrollEndUserAction.builder().build()
            )

        val roundtrippedBetaUserProfileAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUserProfileAction),
                jacksonTypeRef<BetaUserProfileAction>(),
            )

        assertThat(roundtrippedBetaUserProfileAction).isEqualTo(betaUserProfileAction)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaUserProfileAction =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaUserProfileAction>())

        val e = assertThrows<AnthropicInvalidDataException> { betaUserProfileAction.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
