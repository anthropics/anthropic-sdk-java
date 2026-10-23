package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.MultipartField
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import java.io.InputStream
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SkillServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val skillServiceAsync =
            client.beta().skills().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillFuture =
            skillServiceAsync.create(
                SkillCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of<InputStream>("Example data".byteInputStream()))
                    .displayName("display_name")
                    .build()
            )

        val betaSkill = betaSkillFuture.get()
        betaSkill.validate()
    }

    @Test
    fun retrieve() {
        val skillServiceAsync =
            client.beta().skills().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillFuture =
            skillServiceAsync.retrieve(
                SkillRetrieveParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaSkill = betaSkillFuture.get()
        betaSkill.validate()
    }

    @Test
    fun list() {
        val skillServiceAsync =
            client.beta().skills().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = skillServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val skillServiceAsync =
            client.beta().skills().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedSkillFuture =
            skillServiceAsync.delete(
                SkillDeleteParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDeletedSkill = betaDeletedSkillFuture.get()
        betaDeletedSkill.validate()
    }
}
