// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.serviceaccounts

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountRemoveResponseTest {

    @Test
    fun create() {
        val serviceAccountRemoveResponse =
            ServiceAccountRemoveResponse.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(serviceAccountRemoveResponse.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(serviceAccountRemoveResponse.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serviceAccountRemoveResponse =
            ServiceAccountRemoveResponse.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedServiceAccountRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountRemoveResponse),
                jacksonTypeRef<ServiceAccountRemoveResponse>(),
            )

        assertThat(roundtrippedServiceAccountRemoveResponse).isEqualTo(serviceAccountRemoveResponse)
    }
}
