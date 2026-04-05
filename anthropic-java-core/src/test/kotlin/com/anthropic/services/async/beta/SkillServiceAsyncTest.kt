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
import kotlinx.coroutines.runBlocking

@ExtendWith(TestServerExtension::class)
internal class SkillServiceAsyncTest {

    @Disabled(
        "multipartFormData does not handle List<InputStream>: ApiInvalidDataException: Unexpected JsonNode type in array: BINARY"
    )
    @Test
    fun create() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val skillServiceAsync = client.beta().skills()

            val skill =
                skillServiceAsync.create(
                    SkillCreateParams.builder()
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .displayTitle("display_title")
                        .addFile("Example data".byteInputStream())
                        .build()
                )

            skill.validate()
        }
    }

    @Test
    fun retrieve() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val skillServiceAsync = client.beta().skills()

            val skill =
                skillServiceAsync.retrieve(
                    SkillRetrieveParams.builder()
                        .skillId("skill_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            skill.validate()
        }
    }

    @Test
    fun list() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val skillServiceAsync = client.beta().skills()

            val page = skillServiceAsync.list()

            page.response().validate()
        }
    }

    @Test
    fun delete() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val skillServiceAsync = client.beta().skills()

            val skill =
                skillServiceAsync.delete(
                    SkillDeleteParams.builder()
                        .skillId("skill_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            skill.validate()
        }
    }
}
