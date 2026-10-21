package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class UserProfileEnforcementActionTest {

    @Test
    fun ofRestriction() {
        val restriction =
            UserProfileEnforcementActionRestriction.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(UserProfileEnforcementActionNextStep.VERIFY)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                .build()

        val userProfileEnforcementAction = UserProfileEnforcementAction.ofRestriction(restriction)

        assertThat(userProfileEnforcementAction.restriction()).contains(restriction)
        assertThat(userProfileEnforcementAction.ban()).isEmpty
    }

    @Test
    fun ofRestrictionRoundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEnforcementAction =
            UserProfileEnforcementAction.ofRestriction(
                UserProfileEnforcementActionRestriction.builder()
                    .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                    .action(UserProfileEnforcementActionNextStep.VERIFY)
                    .expiresAt(null)
                    .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                    .reason(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)
                    .build()
            )

        val roundtrippedUserProfileEnforcementAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEnforcementAction),
                jacksonTypeRef<UserProfileEnforcementAction>(),
            )

        assertThat(roundtrippedUserProfileEnforcementAction).isEqualTo(userProfileEnforcementAction)
    }

    @Test
    fun ofBan() {
        val ban =
            UserProfileEnforcementActionBan.builder()
                .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                .action(null)
                .expiresAt(null)
                .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                .reason(UserProfileEnforcementActionReason.USAGE_POLICY_VIOLATION)
                .build()

        val userProfileEnforcementAction = UserProfileEnforcementAction.ofBan(ban)

        assertThat(userProfileEnforcementAction.restriction()).isEmpty
        assertThat(userProfileEnforcementAction.ban()).contains(ban)
    }

    @Test
    fun ofBanRoundtrip() {
        val jsonMapper = jsonMapper()
        val userProfileEnforcementAction =
            UserProfileEnforcementAction.ofBan(
                UserProfileEnforcementActionBan.builder()
                    .id("enac_011CZkZCu8hGbp5mYRQgUmz9")
                    .action(null)
                    .expiresAt(null)
                    .openedAt(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
                    .reason(UserProfileEnforcementActionReason.USAGE_POLICY_VIOLATION)
                    .build()
            )

        val roundtrippedUserProfileEnforcementAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userProfileEnforcementAction),
                jacksonTypeRef<UserProfileEnforcementAction>(),
            )

        assertThat(roundtrippedUserProfileEnforcementAction).isEqualTo(userProfileEnforcementAction)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val userProfileEnforcementAction =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to "enac_011CZkZCu8hGbp5mYRQgUmz9",
                            "action" to "verify",
                            "expires_at" to null,
                            "opened_at" to "2026-10-02T19:30:00Z",
                            "reason" to "fraud_or_abuse",
                        )
                    ),
                    jacksonTypeRef<UserProfileEnforcementAction>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> { userProfileEnforcementAction.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(userProfileEnforcementAction.id()).isEqualTo("enac_011CZkZCu8hGbp5mYRQgUmz9")
        assertThat(userProfileEnforcementAction.action())
            .contains(UserProfileEnforcementActionNextStep.VERIFY)
        assertThat(userProfileEnforcementAction.expiresAt()).isEmpty
        assertThat(userProfileEnforcementAction.openedAt())
            .isEqualTo(OffsetDateTime.parse("2026-10-02T19:30:00Z"))
        assertThat(userProfileEnforcementAction.reason())
            .isEqualTo(UserProfileEnforcementActionReason.FRAUD_OR_ABUSE)

        val mismatchedUserProfileEnforcementAction =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to listOf("invalid"),
                            "expires_at" to listOf("invalid"),
                            "opened_at" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<UserProfileEnforcementAction>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedUserProfileEnforcementAction.id() }
        assertThat(mismatchedUserProfileEnforcementAction.expiresAt()).isEmpty
        assertThrows<AnthropicInvalidDataException> {
            mismatchedUserProfileEnforcementAction.openedAt()
        }
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
        val userProfileEnforcementAction =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<UserProfileEnforcementAction>())

        val e =
            assertThrows<AnthropicInvalidDataException> { userProfileEnforcementAction.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { userProfileEnforcementAction.id() }
        assertThat(userProfileEnforcementAction.action()).isEmpty
        assertThat(userProfileEnforcementAction.expiresAt()).isEmpty
        assertThrows<AnthropicInvalidDataException> { userProfileEnforcementAction.openedAt() }
        assertThrows<AnthropicInvalidDataException> { userProfileEnforcementAction.reason() }
    }
}
