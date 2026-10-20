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

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val versionServiceAsync = client.skills().versions()

        val skillVersionFuture =
            versionServiceAsync.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of("Example data".byteInputStream()))
                    .build()
            )

        val skillVersion = skillVersionFuture.get()
        skillVersion.validate()
    }

    @Test
    fun retrieve() {
        val versionServiceAsync = client.skills().versions()

        val skillVersionFuture =
            versionServiceAsync.retrieve(
                VersionRetrieveParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val skillVersion = skillVersionFuture.get()
        skillVersion.validate()
    }

    @Test
    fun list() {
        val versionServiceAsync = client.skills().versions()

        val pageFuture = versionServiceAsync.list("skill_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val versionServiceAsync = client.skills().versions()

        val deletedSkillVersionFuture =
            versionServiceAsync.delete(
                VersionDeleteParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val deletedSkillVersion = deletedSkillVersionFuture.get()
        deletedSkillVersion.validate()
    }
}
