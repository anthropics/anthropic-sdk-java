package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationInfoTest {

    @Test
    fun create() {
        val organizationInfo =
            OrganizationInfo.builder()
                .id("12345678-1234-5678-1234-567812345678")
                .name("Organization Name")
                .build()

        assertThat(organizationInfo.id()).isEqualTo("12345678-1234-5678-1234-567812345678")
        assertThat(organizationInfo.name()).isEqualTo("Organization Name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationInfo =
            OrganizationInfo.builder()
                .id("12345678-1234-5678-1234-567812345678")
                .name("Organization Name")
                .build()

        val roundtrippedOrganizationInfo =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationInfo),
                jacksonTypeRef<OrganizationInfo>(),
            )

        assertThat(roundtrippedOrganizationInfo).isEqualTo(organizationInfo)
    }
}
