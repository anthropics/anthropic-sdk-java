// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InviteDeleteResponseTest {

    @Test
    fun create() {
        val inviteDeleteResponse = InviteDeleteResponse.of("invite_015gWxCN9Hfg2QhZwTK7Mdeu")

        assertThat(inviteDeleteResponse.id()).isEqualTo("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val inviteDeleteResponse = InviteDeleteResponse.of("invite_015gWxCN9Hfg2QhZwTK7Mdeu")

        val roundtrippedInviteDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(inviteDeleteResponse),
                jacksonTypeRef<InviteDeleteResponse>(),
            )

        assertThat(roundtrippedInviteDeleteResponse).isEqualTo(inviteDeleteResponse)
    }
}
