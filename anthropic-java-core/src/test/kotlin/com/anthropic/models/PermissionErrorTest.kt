// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermissionErrorTest {

    @Test
    fun create() {
        val permissionError = PermissionError.builder().message("message").build()

        assertThat(permissionError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val permissionError = PermissionError.builder().message("message").build()

        val roundtrippedPermissionError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(permissionError),
                jacksonTypeRef<PermissionError>(),
            )

        assertThat(roundtrippedPermissionError).isEqualTo(permissionError)
    }
}
