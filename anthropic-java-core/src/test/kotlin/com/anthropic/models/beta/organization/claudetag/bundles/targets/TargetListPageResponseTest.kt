package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TargetListPageResponseTest {

    @Test
    fun create() {
        val targetListPageResponse =
            TargetListPageResponse.builder()
                .addData(
                    BetaClaudeTagSlackChannelBundleTarget.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .slackChannelId("slack_channel_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(targetListPageResponse.data())
            .containsExactly(
                BetaClaudeTagBundleTarget.ofSlackChannel(
                    BetaClaudeTagSlackChannelBundleTarget.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .slackChannelId("slack_channel_id")
                        .build()
                )
            )
        assertThat(targetListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val targetListPageResponse =
            TargetListPageResponse.builder()
                .addData(
                    BetaClaudeTagSlackChannelBundleTarget.builder()
                        .id("id")
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .slackChannelId("slack_channel_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedTargetListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(targetListPageResponse),
                jacksonTypeRef<TargetListPageResponse>(),
            )

        assertThat(roundtrippedTargetListPageResponse).isEqualTo(targetListPageResponse)
    }
}
