// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.skills

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.MultipartField
import com.anthropic.models.skills.versions.VersionCreateParams
import com.anthropic.models.skills.versions.VersionDeleteParams
import com.anthropic.models.skills.versions.VersionRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VersionServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionService = client.skills().versions()

        val skillVersion =
            versionService.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of("Example data".byteInputStream()))
                    .build()
            )

        skillVersion.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionService = client.skills().versions()

        val skillVersion =
            versionService.retrieve(
                VersionRetrieveParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        skillVersion.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionService = client.skills().versions()

        val page = versionService.list("skill_id")

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionService = client.skills().versions()

        val deletedSkillVersion =
            versionService.delete(
                VersionDeleteParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        deletedSkillVersion.validate()
    }
}
