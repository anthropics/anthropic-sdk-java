package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.users.UserUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val userService =
            client.beta().organization().users().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationUser = userService.retrieve("user_id")

        betaOrganizationUser.validate()
    }

    @Test
    fun update() {
        val userService =
            client.beta().organization().users().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationUser =
            userService.update(
                UserUpdateParams.builder()
                    .userId("user_id")
                    .role(UserUpdateParams.Role.USER)
                    .build()
            )

        betaOrganizationUser.validate()
    }

    @Test
    fun list() {
        val userService =
            client.beta().organization().users().withOptions { it.putQueryParam("beta", "true") }

        val page = userService.list()

        page.response().validate()
    }

    @Test
    fun remove() {
        val userService =
            client.beta().organization().users().withOptions { it.putQueryParam("beta", "true") }

        val user = userService.remove("user_id")

        user.validate()
    }
}
