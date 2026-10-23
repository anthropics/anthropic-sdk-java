package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaClaudeTagSettingSourceTest {

    @Test
    fun ofSlackChannel() {
        val slackChannel = BetaClaudeTagSlackChannelSettingSource.of("slack_channel_id")

        val betaClaudeTagSettingSource = BetaClaudeTagSettingSource.ofSlackChannel(slackChannel)

        assertThat(betaClaudeTagSettingSource.slackChannel()).contains(slackChannel)
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackDefault()).isEmpty
        assertThat(betaClaudeTagSettingSource.default_()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).isEmpty
    }

    @Test
    fun ofSlackChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackChannel(
                BetaClaudeTagSlackChannelSettingSource.of("slack_channel_id")
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun ofSlackWorkspace() {
        val slackWorkspace = BetaClaudeTagSlackWorkspaceSettingSource.of("slack_workspace_id")

        val betaClaudeTagSettingSource = BetaClaudeTagSettingSource.ofSlackWorkspace(slackWorkspace)

        assertThat(betaClaudeTagSettingSource.slackChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).contains(slackWorkspace)
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackDefault()).isEmpty
        assertThat(betaClaudeTagSettingSource.default_()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).isEmpty
    }

    @Test
    fun ofSlackWorkspaceRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackWorkspace(
                BetaClaudeTagSlackWorkspaceSettingSource.of("slack_workspace_id")
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun ofSlackParentChannel() {
        val slackParentChannel = BetaClaudeTagSlackParentChannelSettingSource.of("slack_channel_id")

        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackParentChannel(slackParentChannel)

        assertThat(betaClaudeTagSettingSource.slackChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).contains(slackParentChannel)
        assertThat(betaClaudeTagSettingSource.slackDefault()).isEmpty
        assertThat(betaClaudeTagSettingSource.default_()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).isEmpty
    }

    @Test
    fun ofSlackParentChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackParentChannel(
                BetaClaudeTagSlackParentChannelSettingSource.of("slack_channel_id")
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun ofSlackDefault() {
        val slackDefault = BetaClaudeTagSlackDefaultSettingSource.builder().build()

        val betaClaudeTagSettingSource = BetaClaudeTagSettingSource.ofSlackDefault(slackDefault)

        assertThat(betaClaudeTagSettingSource.slackChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackDefault()).contains(slackDefault)
        assertThat(betaClaudeTagSettingSource.default_()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).isEmpty
    }

    @Test
    fun ofSlackDefaultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackDefault(
                BetaClaudeTagSlackDefaultSettingSource.builder().build()
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun ofDefault() {
        val default_ = BetaClaudeTagDefaultSettingSource.builder().build()

        val betaClaudeTagSettingSource = BetaClaudeTagSettingSource.ofDefault(default_)

        assertThat(betaClaudeTagSettingSource.slackChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackDefault()).isEmpty
        assertThat(betaClaudeTagSettingSource.default_()).contains(default_)
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).isEmpty
    }

    @Test
    fun ofDefaultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofDefault(
                BetaClaudeTagDefaultSettingSource.builder().build()
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun ofSlackGridChannel() {
        val slackGridChannel =
            BetaClaudeTagSlackGridChannelSettingSource.of(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("xx")
                    .workspaceId("xx")
                    .build()
            )

        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)

        assertThat(betaClaudeTagSettingSource.slackChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackWorkspace()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackParentChannel()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackDefault()).isEmpty
        assertThat(betaClaudeTagSettingSource.default_()).isEmpty
        assertThat(betaClaudeTagSettingSource.slackGridChannel()).contains(slackGridChannel)
    }

    @Test
    fun ofSlackGridChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackGridChannel(
                BetaClaudeTagSlackGridChannelSettingSource.of(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("xx")
                        .workspaceId("xx")
                        .build()
                )
            )

        val roundtrippedBetaClaudeTagSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingSource),
                jacksonTypeRef<BetaClaudeTagSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingSource).isEqualTo(betaClaudeTagSettingSource)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaClaudeTagSettingSource =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "slack_channel_id" to "slack_channel_id")
                    ),
                    jacksonTypeRef<BetaClaudeTagSettingSource>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> { betaClaudeTagSettingSource.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaClaudeTagSettingSource.slackChannelId()).contains("slack_channel_id")

        val mismatchedBetaClaudeTagSettingSource =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "slack_channel_id" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaClaudeTagSettingSource>(),
                )

        assertThat(mismatchedBetaClaudeTagSettingSource.slackChannelId()).isEmpty
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
        val betaClaudeTagSettingSource =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaClaudeTagSettingSource>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaClaudeTagSettingSource.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaClaudeTagSettingSource.slackChannelId()).isEmpty
    }
}
