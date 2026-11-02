package com.anthropic.services.async.organization.rbacgroups

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.rbacgroups.members.MemberAddParams
import com.anthropic.models.organization.rbacgroups.members.MemberRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class MemberServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val memberServiceAsync = client.organization().rbacGroups().members()

        val pageFuture = memberServiceAsync.list("rbac_group_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val memberServiceAsync = client.organization().rbacGroups().members()

        val rbacGroupMemberFuture =
            memberServiceAsync.add(
                MemberAddParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                    .build()
            )

        val rbacGroupMember = rbacGroupMemberFuture.get()
        rbacGroupMember.validate()
    }

    @Test
    fun remove() {
        val memberServiceAsync = client.organization().rbacGroups().members()

        val deletedRbacGroupMemberFuture =
            memberServiceAsync.remove(
                MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()
            )

        val deletedRbacGroupMember = deletedRbacGroupMemberFuture.get()
        deletedRbacGroupMember.validate()
    }
}
