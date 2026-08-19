// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.skills

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.MultipartField
import com.anthropic.models.skills.versions.VersionCreateParams
import com.anthropic.models.skills.versions.VersionDeleteParams
import com.anthropic.models.skills.versions.VersionRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VersionServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.skills().versions()

        val skillVersionFuture =
            versionServiceAsync.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .addFile(MultipartField.of("Example data".byteInputStream()))
                    .build()
            )

        val skillVersion = skillVersionFuture.get()
        skillVersion.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.skills().versions()

        val skillVersionFuture =
            versionServiceAsync.retrieve(
                VersionRetrieveParams.builder().skillId("skill_id").version("version").build()
            )

        val skillVersion = skillVersionFuture.get()
        skillVersion.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.skills().versions()

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
        val versionServiceAsync = client.skills().versions()

        val deletedSkillVersionFuture =
            versionServiceAsync.delete(
                VersionDeleteParams.builder().skillId("skill_id").version("version").build()
            )

        val deletedSkillVersion = deletedSkillVersionFuture.get()
        deletedSkillVersion.validate()
    }
}
