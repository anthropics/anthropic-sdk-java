// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermissionErrorTest {

    @Test
    fun create() {
        val permissionError = PermissionError.builder().message("message").build()

        assertThat(permissionError.message()).isEqualTo("message")
    }
}
