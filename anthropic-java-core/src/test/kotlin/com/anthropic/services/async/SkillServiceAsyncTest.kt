// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.skills.SkillCreateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SkillServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillServiceAsync = client.skills()

        val skillFuture =
            skillServiceAsync.create(
                SkillCreateParams.builder()
                    .addFile("Example data".byteInputStream())
                    .displayName("display_name")
                    .build()
            )

        val skill = skillFuture.get()
        skill.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillServiceAsync = client.skills()

        val skillFuture = skillServiceAsync.retrieve("skill_id")

        val skill = skillFuture.get()
        skill.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillServiceAsync = client.skills()

        val pageFuture = skillServiceAsync.list()

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
        val skillServiceAsync = client.skills()

        val deletedSkillFuture = skillServiceAsync.delete("skill_id")

        val deletedSkill = deletedSkillFuture.get()
        deletedSkill.validate()
    }
}
