package com.anthropic.models.beta.organization.claudetag.bundles.targets

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

internal class BetaClaudeTagBundleTargetTest {

    @Test
    fun ofSlackChannel() {
        val slackChannel =
            BetaClaudeTagSlackChannelBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackChannelId("slack_channel_id")
                .build()

        val betaClaudeTagBundleTarget = BetaClaudeTagBundleTarget.ofSlackChannel(slackChannel)

        assertThat(betaClaudeTagBundleTarget.slackChannel()).contains(slackChannel)
        assertThat(betaClaudeTagBundleTarget.slackWorkspace()).isEmpty
    }

    @Test
    fun ofSlackChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagBundleTarget =
            BetaClaudeTagBundleTarget.ofSlackChannel(
                BetaClaudeTagSlackChannelBundleTarget.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .slackChannelId("slack_channel_id")
                    .build()
            )

        val roundtrippedBetaClaudeTagBundleTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagBundleTarget),
                jacksonTypeRef<BetaClaudeTagBundleTarget>(),
            )

        assertThat(roundtrippedBetaClaudeTagBundleTarget).isEqualTo(betaClaudeTagBundleTarget)
    }

    @Test
    fun ofSlackWorkspace() {
        val slackWorkspace =
            BetaClaudeTagSlackWorkspaceBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackWorkspaceId("slack_workspace_id")
                .build()

        val betaClaudeTagBundleTarget = BetaClaudeTagBundleTarget.ofSlackWorkspace(slackWorkspace)

        assertThat(betaClaudeTagBundleTarget.slackChannel()).isEmpty
        assertThat(betaClaudeTagBundleTarget.slackWorkspace()).contains(slackWorkspace)
    }

    @Test
    fun ofSlackWorkspaceRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagBundleTarget =
            BetaClaudeTagBundleTarget.ofSlackWorkspace(
                BetaClaudeTagSlackWorkspaceBundleTarget.builder()
                    .id("id")
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .slackWorkspaceId("slack_workspace_id")
                    .build()
            )

        val roundtrippedBetaClaudeTagBundleTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagBundleTarget),
                jacksonTypeRef<BetaClaudeTagBundleTarget>(),
            )

        assertThat(roundtrippedBetaClaudeTagBundleTarget).isEqualTo(betaClaudeTagBundleTarget)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaClaudeTagBundleTarget =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to "id",
                            "created_at" to "2019-12-27T18:11:19.117Z",
                        )
                    ),
                    jacksonTypeRef<BetaClaudeTagBundleTarget>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaClaudeTagBundleTarget.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaClaudeTagBundleTarget.id()).isEqualTo("id")
        assertThat(betaClaudeTagBundleTarget.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))

        val mismatchedBetaClaudeTagBundleTarget =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to listOf("invalid"),
                            "created_at" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaClaudeTagBundleTarget>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaClaudeTagBundleTarget.id() }
        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaClaudeTagBundleTarget.createdAt()
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
        val betaClaudeTagBundleTarget =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaClaudeTagBundleTarget>())

        val e = assertThrows<AnthropicInvalidDataException> { betaClaudeTagBundleTarget.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaClaudeTagBundleTarget.id() }
        assertThrows<AnthropicInvalidDataException> { betaClaudeTagBundleTarget.createdAt() }
    }
}
