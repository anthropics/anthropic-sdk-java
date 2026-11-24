package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("slackChannel" to Optional.of(slackChannel)))
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

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("slackWorkspace" to Optional.of(slackWorkspace)))
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

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("slackParentChannel" to Optional.of(slackParentChannel)))
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

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("slackDefault" to Optional.of(slackDefault)))
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

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("default_" to Optional.of(default_)))
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
                    .channelId("C0456EFGH")
                    .workspaceId("T0123ABCD")
                    .build()
            )

        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackGridChannel(slackGridChannel)

        assertThat(presentVariants(betaClaudeTagSettingSource))
            .isEqualTo(mapOf("slackGridChannel" to Optional.of(slackGridChannel)))
    }

    @Test
    fun ofSlackGridChannelRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingSource =
            BetaClaudeTagSettingSource.ofSlackGridChannel(
                BetaClaudeTagSlackGridChannelSettingSource.of(
                    BetaClaudeTagSlackChannelIdentifiers.builder()
                        .channelId("C0456EFGH")
                        .workspaceId("T0123ABCD")
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

    private fun presentVariants(
        betaClaudeTagSettingSource: BetaClaudeTagSettingSource
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "slackChannel" to betaClaudeTagSettingSource.slackChannel(),
                "slackWorkspace" to betaClaudeTagSettingSource.slackWorkspace(),
                "slackParentChannel" to betaClaudeTagSettingSource.slackParentChannel(),
                "slackDefault" to betaClaudeTagSettingSource.slackDefault(),
                "default_" to betaClaudeTagSettingSource.default_(),
                "slackGridChannel" to betaClaudeTagSettingSource.slackGridChannel(),
            )
            .filterValues { it.isPresent }
}
