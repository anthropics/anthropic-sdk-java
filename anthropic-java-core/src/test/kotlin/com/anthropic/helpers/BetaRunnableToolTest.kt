package com.anthropic.helpers

import com.anthropic.core.NestedClassJavaFixtures
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRunnableToolTest {

    @Test
    fun of_derivesToolDefinitionFromParametersType() {
        val tool =
            BetaRunnableTool.of(
                NestedClassJavaFixtures.GetWeather::class.java,
                { BetaToolResultBlockParam.Content.ofString(it.location) },
            )

        val tools = paramsBuilder().addTool(tool).build().tools().get()

        assertThat(tools)
            .isEqualTo(
                paramsBuilder()
                    .addTool(NestedClassJavaFixtures.GetWeather::class.java)
                    .build()
                    .tools()
                    .get()
            )
        assertThat(tools.single().asBetaTool().name()).isEqualTo("get_weather")
        assertThat(tools.single().asBetaTool().description())
            .hasValue("Get the weather in a given location")
    }

    @Test
    fun of_usesGivenToolDefinition() {
        val definition =
            BetaTool.builder()
                .name("get_weather")
                .inputSchema(BetaTool.InputSchema.builder().build())
                .build()
        val tool = BetaRunnableTool.of(definition) { BetaToolResultBlockParam.Content.ofString(it) }

        val tools = paramsBuilder().addTool(tool).build().tools().get()

        assertThat(tools).isEqualTo(paramsBuilder().addTool(definition).build().tools().get())
    }

    private fun paramsBuilder() =
        MessageCreateParams.builder()
            .model(Model.CLAUDE_SONNET_4_5)
            .maxTokens(1000)
            .addUserMessage("What is the weather in San Francisco?")
}
