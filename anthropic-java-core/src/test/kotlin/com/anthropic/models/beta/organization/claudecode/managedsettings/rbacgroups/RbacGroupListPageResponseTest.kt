package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupListPageResponseTest {

    @Test
    fun create() {
        val rbacGroupListPageResponse =
            RbacGroupListPageResponse.builder()
                .addData(
                    BetaClaudeCodeRbacGroupManagedSettingsSummary.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(rbacGroupListPageResponse.data())
            .containsExactly(
                BetaClaudeCodeRbacGroupManagedSettingsSummary.builder()
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                    .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )
        assertThat(rbacGroupListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupListPageResponse =
            RbacGroupListPageResponse.builder()
                .addData(
                    BetaClaudeCodeRbacGroupManagedSettingsSummary.builder()
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                        .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedRbacGroupListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupListPageResponse),
                jacksonTypeRef<RbacGroupListPageResponse>(),
            )

        assertThat(roundtrippedRbacGroupListPageResponse).isEqualTo(rbacGroupListPageResponse)
    }
}
