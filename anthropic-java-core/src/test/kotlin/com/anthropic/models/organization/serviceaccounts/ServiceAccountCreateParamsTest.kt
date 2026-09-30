package com.anthropic.models.organization.serviceaccounts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountCreateParamsTest {

    @Test
    fun create() {
        ServiceAccountCreateParams.builder()
            .name("ci-deploy-bot")
            .description("description")
            .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
            .build()
    }

    @Test
    fun body() {
        val params =
            ServiceAccountCreateParams.builder()
                .name("ci-deploy-bot")
                .description("description")
                .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                .build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("ci-deploy-bot")
        assertThat(body.description()).contains("description")
        assertThat(body.organizationRole())
            .contains(ServiceAccountCreateParams.OrganizationRole.ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ServiceAccountCreateParams.builder().name("ci-deploy-bot").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("ci-deploy-bot")
    }
}
