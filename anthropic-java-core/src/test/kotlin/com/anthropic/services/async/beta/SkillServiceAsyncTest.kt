// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SkillServiceAsyncTest {

    @Disabled("prism binary unsupported")
    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillServiceAsync = client.beta().skills()

        val skillFuture =
            skillServiceAsync.create(
                SkillCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .displayTitle("display_title")
                    .addFile("some content".byteInputStream())
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
        val skillServiceAsync = client.beta().skills()

        val skillFuture =
            skillServiceAsync.retrieve(
                SkillRetrieveParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

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
        val skillServiceAsync = client.beta().skills()

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
        val skillServiceAsync = client.beta().skills()

        val skillFuture =
            skillServiceAsync.delete(
                SkillDeleteParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val skill = skillFuture.get()
        skill.validate()
    }
}
