// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberRemoveParamsTest {

    @Test
    fun create() {
        MemberRemoveParams.builder().workspaceId("workspace_id").userId("user_id").build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberRemoveParams.builder().workspaceId("workspace_id").userId("user_id").build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        assertThat(params._pathParam(1)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }
}
