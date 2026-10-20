package com.anthropic.models.userprofiles.actionurls

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class UserProfileActionTest {

    @Test
    fun ofEnrollEndUser() {
        val enrollEndUser = UserProfileEnrollEndUserAction.builder().build()

        val userProfileAction = UserProfileAction.ofEnrollEndUser(enrollEndUser)

        assertThat(userProfileAction.enrollEndUser()).contains(enrollEndUser)
    }

    @Test
    fun ofEnrollEndUserRoundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileAction =
            UserProfileAction.ofEnrollEndUser(UserProfileEnrollEndUserAction.builder().build())

        val roundtrippedUserProfileAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileAction),
                jacksonTypeRef<UserProfileAction>(),
            )

        assertThat(roundtrippedUserProfileAction).isEqualTo(userProfileAction)
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
        val userProfileAction =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<UserProfileAction>())

        val e = assertThrows<AnthropicInvalidDataException> { userProfileAction.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
