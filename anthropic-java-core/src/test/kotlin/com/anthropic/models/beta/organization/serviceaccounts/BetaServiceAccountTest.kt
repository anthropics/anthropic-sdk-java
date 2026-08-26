// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServiceAccountTest {

    @Test
    fun create() {
        val betaServiceAccount =
            BetaServiceAccount.builder()
                .id("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .name("ci-deploy-bot")
                .organizationRole(BetaServiceAccount.OrganizationRole.ADMIN)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        assertThat(betaServiceAccount.id()).isEqualTo("svac_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaServiceAccount.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaServiceAccount.archivedByActorId()).contains("archived_by_actor_id")
        assertThat(betaServiceAccount.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaServiceAccount.createdByActorId()).contains("created_by_actor_id")
        assertThat(betaServiceAccount.description()).contains("description")
        assertThat(betaServiceAccount.name()).isEqualTo("ci-deploy-bot")
        assertThat(betaServiceAccount.organizationRole())
            .isEqualTo(BetaServiceAccount.OrganizationRole.ADMIN)
        assertThat(betaServiceAccount.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaServiceAccount.updatedByActorId()).contains("updated_by_actor_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServiceAccount =
            BetaServiceAccount.builder()
                .id("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .archivedByActorId("archived_by_actor_id")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .createdByActorId("created_by_actor_id")
                .description("description")
                .name("ci-deploy-bot")
                .organizationRole(BetaServiceAccount.OrganizationRole.ADMIN)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .updatedByActorId("updated_by_actor_id")
                .build()

        val roundtrippedBetaServiceAccount =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServiceAccount),
                jacksonTypeRef<BetaServiceAccount>(),
            )

        assertThat(roundtrippedBetaServiceAccount).isEqualTo(betaServiceAccount)
    }
}
