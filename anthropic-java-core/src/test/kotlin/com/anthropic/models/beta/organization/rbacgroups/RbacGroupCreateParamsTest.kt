package com.anthropic.models.beta.organization.rbacgroups

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupCreateParamsTest {

    @Test
    fun create() {
        RbacGroupCreateParams.builder().name("Engineering").build()
    }

    @Test
    fun body() {
        val params = RbacGroupCreateParams.builder().name("Engineering").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("Engineering")
    }
}
