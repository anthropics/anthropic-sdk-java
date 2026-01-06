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

@ExtendWith(TestServerExtension::class)
internal class VersionServiceAsyncTest {

    @Disabled("prism binary unsupported")
    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().skills().versions()

        val versionFuture =
            versionServiceAsync.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.of("string"))
                    .addFile("some content".byteInputStream())
                    .build()
            )

        val version = versionFuture.get()
        version.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().skills().versions()

        val versionFuture =
            versionServiceAsync.retrieve(
                VersionRetrieveParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        val version = versionFuture.get()
        version.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().skills().versions()

        val pageFuture = versionServiceAsync.list("skill_id")

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
        val versionServiceAsync = client.beta().skills().versions()

        val versionFuture =
            versionServiceAsync.delete(
                VersionDeleteParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.of("string"))
                    .build()
            )

        val version = versionFuture.get()
        version.validate()
    }
}
