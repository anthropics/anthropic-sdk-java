package com.anthropic.models.organization.serviceaccounts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountUpdateParamsTest {

    @Test
    fun create() {
        ServiceAccountUpdateParams.builder()
            .serviceAccountId("service_account_id")
            .description("description")
            .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountUpdateParams.builder().serviceAccountId("service_account_id").build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            ServiceAccountUpdateParams.builder()
                .serviceAccountId("service_account_id")
                .description("description")
                .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                .build()

        val body = params._body()

        assertThat(body.description()).contains("description")
        assertThat(body.organizationRole())
            .contains(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ServiceAccountUpdateParams.builder().serviceAccountId("service_account_id").build()

        val body = params._body()
    }
}
