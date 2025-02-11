// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PermissionErrorTest {

    @Test
    fun createPermissionError() {
        val permissionError = PermissionError.builder().message("message").build()
        assertThat(permissionError).isNotNull
        assertThat(permissionError.message()).isEqualTo("message")
    }
}
