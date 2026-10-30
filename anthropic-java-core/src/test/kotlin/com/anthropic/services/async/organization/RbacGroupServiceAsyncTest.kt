package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.organization.rbacgroups.RbacGroupUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val rbacGroupServiceAsync = client.organization().rbacGroups()

        val rbacGroupFuture =
            rbacGroupServiceAsync.create(
                RbacGroupCreateParams.builder().name("Engineering").build()
            )

        val rbacGroup = rbacGroupFuture.get()
        rbacGroup.validate()
    }

    @Test
    fun retrieve() {
        val rbacGroupServiceAsync = client.organization().rbacGroups()

        val rbacGroupFuture = rbacGroupServiceAsync.retrieve("rbac_group_id")

        val rbacGroup = rbacGroupFuture.get()
        rbacGroup.validate()
    }

    @Test
    fun update() {
        val rbacGroupServiceAsync = client.organization().rbacGroups()

        val rbacGroupFuture =
            rbacGroupServiceAsync.update(
                RbacGroupUpdateParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .name("Engineering")
                    .build()
            )

        val rbacGroup = rbacGroupFuture.get()
        rbacGroup.validate()
    }

    @Test
    fun list() {
        val rbacGroupServiceAsync = client.organization().rbacGroups()

        val pageFuture = rbacGroupServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val rbacGroupServiceAsync = client.organization().rbacGroups()

        val deletedRbacGroupFuture = rbacGroupServiceAsync.delete("rbac_group_id")

        val deletedRbacGroup = deletedRbacGroupFuture.get()
        deletedRbacGroup.validate()
    }
}
