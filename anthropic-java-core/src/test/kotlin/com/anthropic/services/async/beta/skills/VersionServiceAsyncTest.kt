// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.skills

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlinx.coroutines.runBlocking

@ExtendWith(TestServerExtension::class)
internal class VersionServiceAsyncTest {

    @Disabled(
        "multipartFormData does not handle List<InputStream>: AnthropicInvalidDataException: Unexpected JsonNode type in array: BINARY"
    )
    @Test
    fun create() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val versionServiceAsync = client.beta().skills().versions()

            val version =
                versionServiceAsync.create(
                    VersionCreateParams.builder()
                        .skillId("skill_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .addFile("Example data".byteInputStream())
                        .build()
                )

            version.validate()
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
            val versionServiceAsync = client.beta().skills().versions()

            val version =
                versionServiceAsync.retrieve(
                    VersionRetrieveParams.builder()
                        .skillId("skill_id")
                        .version("version")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            version.validate()
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
            val versionServiceAsync = client.beta().skills().versions()

            val page = versionServiceAsync.list("skill_id")

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
            val versionServiceAsync = client.beta().skills().versions()

            val version =
                versionServiceAsync.delete(
                    VersionDeleteParams.builder()
                        .skillId("skill_id")
                        .version("version")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            version.validate()
        }
    }
}
