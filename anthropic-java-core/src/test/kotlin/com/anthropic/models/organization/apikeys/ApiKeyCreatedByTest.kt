package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyCreatedByTest {

    @Test
    fun create() {
        val apiKeyCreatedBy =
            ApiKeyCreatedBy.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .type(ApiKeyCreatedBy.Type.USER)
                .build()

        assertThat(apiKeyCreatedBy.id()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(apiKeyCreatedBy.type()).isEqualTo(ApiKeyCreatedBy.Type.USER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val apiKeyCreatedBy =
            ApiKeyCreatedBy.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .type(ApiKeyCreatedBy.Type.USER)
                .build()

        val roundtrippedApiKeyCreatedBy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(apiKeyCreatedBy),
                jacksonTypeRef<ApiKeyCreatedBy>(),
            )

        assertThat(roundtrippedApiKeyCreatedBy).isEqualTo(apiKeyCreatedBy)
    }
}
