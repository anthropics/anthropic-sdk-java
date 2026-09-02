// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.MultipartField
import com.anthropic.models.skills.SkillCreateParams
import com.anthropic.models.skills.SkillDeleteParams
import com.anthropic.models.skills.SkillRetrieveParams
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
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of("Example data".byteInputStream()))
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

        val skillFuture =
            skillServiceAsync.retrieve(
                SkillRetrieveParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
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

        val deletedSkillFuture =
            skillServiceAsync.delete(
                SkillDeleteParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val deletedSkill = deletedSkillFuture.get()
        deletedSkill.validate()
    }
}
