package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.X
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StructuredThreadCreateParamsTest {

    @Test
    fun `the output type changes the builder and keeps what is set before and after it`() {
        val params =
            ThreadCreateParams.builder()
                .model("claude-sonnet-4-5")
                .unsupportedRequestBehavior(UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
                .outputConfig(LOW_EFFORT)
                .outputConfig(X::class.java)
                .maxTokens(1024)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams)
            .isEqualTo(
                ThreadCreateParams.builder()
                    .model("claude-sonnet-4-5")
                    .unsupportedRequestBehavior(UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
                    .outputConfig(LOW_EFFORT.toBuilder().format(FORMAT).build())
                    .maxTokens(1024)
                    .build()
            )
        assertThat(params.initialMessageParams).isEqualTo(params.rawParams.initialMessageParams)
        assertThat(params.unsupportedRequestBehavior())
            .isEqualTo(UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        assertThat(params.messages()).isEmpty()
        assertThat(params.betas()).isEmpty
    }

    @Test
    fun `a structured output config sets the output type and the whole output config`() {
        val params =
            builder()
                .outputConfig(LOW_EFFORT.toBuilder().taskBudget(BetaTokenTaskBudget.of(1)).build())
                .outputConfig(
                    StructuredOutputConfig.builder<X>()
                        .format(X::class.java, JsonSchemaLocalValidation.NO)
                        .effort(BetaOutputConfig.Effort.LOW)
                        .build()
                )
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.outputConfig()).hasValue(LOW_EFFORT.toBuilder().format(FORMAT).build())
        assertThat(params.betas()).isEmpty
    }

    @Test
    fun `a later output config keeps the format unless it sets its own`() {
        val structured = builder().outputConfig(X::class.java)
        val otherFormat =
            BetaJsonOutputFormat.of(
                BetaJsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("type", JsonValue.from("object"))
                    .build()
            )
        val withOtherFormat = BetaOutputConfig.builder().format(otherFormat).build()

        assertThat(structured.outputConfig(LOW_EFFORT).build().outputConfig())
            .hasValue(LOW_EFFORT.toBuilder().format(FORMAT).build())
        assertThat(structured.outputConfig(JsonField.of(LOW_EFFORT)).build().outputConfig())
            .hasValue(LOW_EFFORT.toBuilder().format(FORMAT).build())
        assertThat(structured.outputConfig(withOtherFormat).build().outputConfig())
            .hasValue(withOtherFormat)
        assertThat(structured.outputConfig(X::class.java).build().outputConfig())
            .hasValue(BetaOutputConfig.builder().format(FORMAT).build())
    }

    @Test
    fun `a class that no schema can be derived from is refused when it is set`() {
        class Local

        assertThrows<IllegalArgumentException> { builder().outputConfig(Local::class.java) }
    }

    @Test
    fun `params built from the same values are equal`() {
        val builder = builder().addUserMessage("Hello").outputConfig(X::class.java)
        val params = builder.build()

        assertThat(params).isEqualTo(builder.build()).isEqualTo(params.toBuilder().build())
        assertThat(params).hasSameHashCodeAs(builder.build())
        assertThat(params).isNotEqualTo(params.toBuilder().maxTokens(512).build())
        assertThat(params.toString())
            .startsWith("StructuredThreadCreateParams{outputType=")
            .contains("rawParams=ThreadCreateParams{")
    }

    @Test
    fun `the members are those of the thread create params, with the output type`() {
        assertThat(publicNames(StructuredThreadCreateParams::class.java))
            .`as`("Give `StructuredThreadCreateParams` the members of `ThreadCreateParams`")
            .isEqualTo(
                publicNames(ThreadCreateParams::class.java) + setOf("outputType", "rawParams")
            )
        assertThat(publicNames(StructuredThreadCreateParams.Builder::class.java))
            .`as`(
                "Give `StructuredThreadCreateParams.Builder` the members of " +
                    "`ThreadCreateParams.Builder` except `outputFormat`"
            )
            .isEqualTo(publicNames(ThreadCreateParams.Builder::class.java) - "outputFormat")
    }

    private companion object {

        val FORMAT = betaOutputFormatFromClass(X::class.java)

        val LOW_EFFORT: BetaOutputConfig =
            BetaOutputConfig.builder().effort(BetaOutputConfig.Effort.LOW).build()

        fun builder() = ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024)

        fun publicNames(type: Class<*>): Set<String> =
            type.methods.map { it.name }.filterNot { "$" in it }.toSet()
    }
}
