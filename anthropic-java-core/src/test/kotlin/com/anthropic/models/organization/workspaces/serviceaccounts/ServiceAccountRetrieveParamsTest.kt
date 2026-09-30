package com.anthropic.models.organization.workspaces.serviceaccounts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountRetrieveParamsTest {

    @Test
    fun create() {
        ServiceAccountRetrieveParams.builder()
            .workspaceId("workspace_id")
            .serviceAccountId("service_account_id")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountRetrieveParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        assertThat(params._pathParam(1)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }
}
