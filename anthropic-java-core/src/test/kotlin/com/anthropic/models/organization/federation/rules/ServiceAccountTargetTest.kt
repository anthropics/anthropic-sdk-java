package com.anthropic.models.organization.federation.rules

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountTargetTest {

    @Test
    fun create() {
        val serviceAccountTarget =
            ServiceAccountTarget.builder()
                .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .serviceAccountName("service_account_name")
                .build()

        assertThat(serviceAccountTarget.serviceAccountId())
            .isEqualTo("svac_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(serviceAccountTarget.serviceAccountName()).contains("service_account_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serviceAccountTarget =
            ServiceAccountTarget.builder()
                .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .serviceAccountName("service_account_name")
                .build()

        val roundtrippedServiceAccountTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountTarget),
                jacksonTypeRef<ServiceAccountTarget>(),
            )

        assertThat(roundtrippedServiceAccountTarget).isEqualTo(serviceAccountTarget)
    }
}
