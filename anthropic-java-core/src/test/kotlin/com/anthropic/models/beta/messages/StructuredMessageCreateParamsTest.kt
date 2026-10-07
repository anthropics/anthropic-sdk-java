package com.anthropic.models.beta.messages

import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.LONG
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.findDelegationMethod
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the functions of the [StructuredMessageCreateParams.Builder] class (delegator)
 * that do more than call the function of the same name in the wrapped [MessageCreateParams.Builder]
 * (delegate).
 */
internal class StructuredMessageCreateParamsTest {
    companion object {
        private val MODEL = Model.CLAUDE_SONNET_4_5
        private val OUTPUT_CONFIG_WITH_EFFORT =
            BetaOutputConfig.builder()
                .effort(BetaOutputConfig.Effort.HIGH)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        private val OTHER_FORMAT =
            BetaJsonOutputFormat.of(
                BetaJsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("type", JSON_VALUE)
                    .build()
            )
        private val OUTPUT_CONFIG_WITH_FORMAT =
            BetaOutputConfig.builder().format(OTHER_FORMAT).build()
        private val STRUCTURED_OUTPUTS_BETA = AnthropicBeta.of("structured-outputs-2025-12-15")
        private val VALIDATION = JsonSchemaLocalValidation.NO
    }

    // New instances of the `mockBuilderDelegate` and `builderDelegator` are required for each test
    // case (each test case runs in its own instance of the test class).
    private val mockBuilderDelegate: MessageCreateParams.Builder =
        mock(MessageCreateParams.Builder::class.java)
    private val builderDelegator =
        StructuredMessageCreateParams.builder<X>().wrap(mockBuilderDelegate)

    @Test
    fun `delegation of outputFormat`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method. The deprecated `outputFormat` now calls `outputConfig` and `addBeta`
        // internally instead of the delegate's `outputFormat`.
        val delegatorTestCase = DelegationWriteTestCase("outputFormat", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        @Suppress("DEPRECATION")
        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that the current output config was read and then set with the derived format.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class`() {
        // Special unit test case for the outputConfig(Class<T>, ...) overload which has
        // non-standard delegation behavior similar to outputFormat.
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that the current output config was read and then set with the derived format.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class merges into current output config`() {
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                OUTPUT_CONFIG_WITH_FORMAT.toBuilder()
                    .taskBudget(BetaTokenTaskBudget.of(LONG))
                    .build()
            )

        builderDelegator.outputConfig(X::class.java)

        // Only the format of the current output config is replaced.
        val expectedOutputConfig =
            BetaOutputConfig.builder()
                .format(betaOutputFormatFromClass(X::class.java))
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class and effort`() {
        // Special unit test case for the deprecated outputConfig(Class<T>, Effort, ...) overload
        // which must set the effort alongside the derived format on the current output config.
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                OUTPUT_CONFIG_WITH_EFFORT.toBuilder().effort(BetaOutputConfig.Effort.LOW).build()
            )

        @Suppress("DEPRECATION")
        builderDelegator.outputConfig(X::class.java, BetaOutputConfig.Effort.HIGH, VALIDATION)

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder()
                .format(betaOutputFormatFromClass(X::class.java, VALIDATION))
                .build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with StructuredOutputConfig`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method: it unwraps a `StructuredOutputConfig` to its raw `BetaOutputConfig`.
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .format(X::class.java)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", structuredOutputConfig)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig and addBeta were called on the mock delegate.
        verify(mockBuilderDelegate, times(1)).outputConfig(structuredOutputConfig.rawOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig`() {
        // With no current output config, the given output config is passed through verbatim.
        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig carries over current format`() {
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder().format(OTHER_FORMAT).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig keeps given format`() {
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                BetaOutputConfig.builder().format(betaOutputFormatFromClass(X::class.java)).build()
            )

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_FORMAT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with JsonField`() {
        // A known value is merged like a `BetaOutputConfig`; any other JSON field is passed
        // verbatim.
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(JsonField.of(OUTPUT_CONFIG_WITH_EFFORT))
        builderDelegator.outputConfig(JsonMissing.of())

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder().format(OTHER_FORMAT).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).outputConfig(JsonMissing.of())
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `outputConfig with StructuredOutputConfig preserves other output options`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .format(X::class.java)
                .build()

        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        // The beta header is injected automatically.
        assertThat(params.rawParams.betas()).hasValue(listOf(STRUCTURED_OUTPUTS_BETA))
    }

    @Test
    fun `outputConfig with StructuredOutputConfig replaces the output config`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .format(X::class.java)
                .build()

        @Suppress("DEPRECATION")
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputFormat(X::class.java)
                .outputConfig(X::class.java)
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `outputConfig with class after output config with effort keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputConfig(X::class.java)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `deprecated outputConfig with class and effort after output config keeps task budget`() {
        @Suppress("DEPRECATION")
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputConfig(X::class.java, BetaOutputConfig.Effort.LOW)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.LOW)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `output config with effort after outputConfig with class keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `outputConfig with class after output config with format replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(
                    OUTPUT_CONFIG_WITH_FORMAT.toBuilder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .build()
                )
                .outputConfig(X::class.java)
                .build()

        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.LOW)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
    }

    @Test
    fun `output config with format after outputConfig with class replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(OUTPUT_CONFIG_WITH_FORMAT)
    }
}
