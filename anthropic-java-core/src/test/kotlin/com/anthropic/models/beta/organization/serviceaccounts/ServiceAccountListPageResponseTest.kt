// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountListPageResponseTest {

    @Test
    fun create() {
        val serviceAccountListPageResponse =
            ServiceAccountListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(serviceAccountListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(serviceAccountListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serviceAccountListPageResponse =
            ServiceAccountListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedServiceAccountListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountListPageResponse),
                jacksonTypeRef<ServiceAccountListPageResponse>(),
            )

        assertThat(roundtrippedServiceAccountListPageResponse)
            .isEqualTo(serviceAccountListPageResponse)
    }
}
