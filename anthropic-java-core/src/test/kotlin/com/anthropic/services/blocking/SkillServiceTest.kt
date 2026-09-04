// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.MultipartField
import com.anthropic.models.skills.SkillCreateParams
import com.anthropic.models.skills.SkillDeleteParams
import com.anthropic.models.skills.SkillRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SkillServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillService = client.skills()

        val skill =
            skillService.create(
                SkillCreateParams.builder()
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of("Example data".byteInputStream()))
                    .displayName("display_name")
                    .build()
            )

        skill.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillService = client.skills()

        val skill =
            skillService.retrieve(
                SkillRetrieveParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        skill.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillService = client.skills()

        val page = skillService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val skillService = client.skills()

        val deletedSkill =
            skillService.delete(
                SkillDeleteParams.builder()
                    .skillId("skill_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        deletedSkill.validate()
    }
}
