package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.beta.organization.rbacgroups.RbacGroupUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class RbacGroupServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupServiceAsync = client.beta().organization().rbacGroups()

        val betaRbacGroupFuture =
            rbacGroupServiceAsync.create(
                RbacGroupCreateParams.builder().name("Engineering").build()
            )

        val betaRbacGroup = betaRbacGroupFuture.get()
        betaRbacGroup.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupServiceAsync = client.beta().organization().rbacGroups()

        val betaRbacGroupFuture = rbacGroupServiceAsync.retrieve("rbac_group_id")

        val betaRbacGroup = betaRbacGroupFuture.get()
        betaRbacGroup.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupServiceAsync = client.beta().organization().rbacGroups()

        val betaRbacGroupFuture =
            rbacGroupServiceAsync.update(
                RbacGroupUpdateParams.builder()
                    .rbacGroupId("rbac_group_id")
                    .name("Engineering")
                    .build()
            )

        val betaRbacGroup = betaRbacGroupFuture.get()
        betaRbacGroup.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupServiceAsync = client.beta().organization().rbacGroups()

        val pageFuture = rbacGroupServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val rbacGroupServiceAsync = client.beta().organization().rbacGroups()

        val rbacGroupFuture = rbacGroupServiceAsync.delete("rbac_group_id")

        val rbacGroup = rbacGroupFuture.get()
        rbacGroup.validate()
    }
}
