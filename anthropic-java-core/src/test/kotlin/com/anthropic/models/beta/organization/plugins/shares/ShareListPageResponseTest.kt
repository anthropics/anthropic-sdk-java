package com.anthropic.models.beta.organization.plugins.shares

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.plugins.BetaPluginTargetOrganization
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShareListPageResponseTest {

    @Test
    fun create() {
        val shareListPageResponse =
            ShareListPageResponse.builder()
                .addData(
                    BetaPluginShare.builder()
                        .grantedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .target(BetaPluginTargetOrganization.builder().build())
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        assertThat(shareListPageResponse.data())
            .containsExactly(
                BetaPluginShare.builder()
                    .grantedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                    .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                    .target(BetaPluginTargetOrganization.builder().build())
                    .build()
            )
        assertThat(shareListPageResponse.nextPage()).contains("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val shareListPageResponse =
            ShareListPageResponse.builder()
                .addData(
                    BetaPluginShare.builder()
                        .grantedAt(OffsetDateTime.parse("2026-03-14T09:26:53.589793Z"))
                        .pluginId("plugin_01JyHfbRkZvD1gW7oTqXc3Ne")
                        .target(BetaPluginTargetOrganization.builder().build())
                        .build()
                )
                .nextPage("page_MjAyNi0wOS0xNlQxNDowNTowOVo")
                .build()

        val roundtrippedShareListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(shareListPageResponse),
                jacksonTypeRef<ShareListPageResponse>(),
            )

        assertThat(roundtrippedShareListPageResponse).isEqualTo(shareListPageResponse)
    }
}
