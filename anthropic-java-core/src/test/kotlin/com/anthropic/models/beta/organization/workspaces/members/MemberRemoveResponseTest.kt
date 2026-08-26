// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberRemoveResponseTest {

    @Test
    fun create() {
        val memberRemoveResponse =
            MemberRemoveResponse.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        assertThat(memberRemoveResponse.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(memberRemoveResponse.workspaceId()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memberRemoveResponse =
            MemberRemoveResponse.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .build()

        val roundtrippedMemberRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memberRemoveResponse),
                jacksonTypeRef<MemberRemoveResponse>(),
            )

        assertThat(roundtrippedMemberRemoveResponse).isEqualTo(memberRemoveResponse)
    }
}
