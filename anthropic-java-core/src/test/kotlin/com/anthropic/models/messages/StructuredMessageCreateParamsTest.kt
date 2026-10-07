package com.anthropic.models.messages

import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.X
import com.anthropic.core.findDelegationMethod
import com.anthropic.core.outputFormatFromClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the GA [StructuredMessageCreateParams.Builder] class (delegator) and its
 * delegation to a wrapped [MessageCreateParams.Builder] (delegate).
 */
internal class StructuredMessageCreateParamsTest {

    companion object {
        private val OUTPUT_CONFIG_WITH_EFFORT =
            OutputConfig.builder().effort(OutputConfig.Effort.HIGH).build()
        private val OTHER_FORMAT =
            JsonOutputFormat.of(
                JsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("type", JsonValue.from("object"))
                    .build()
            )
        private val OUTPUT_CONFIG_WITH_FORMAT = OutputConfig.builder().format(OTHER_FORMAT).build()
    }

    private val mockBuilderDelegate: MessageCreateParams.Builder =
        mock(MessageCreateParams.Builder::class.java)
    private val builderDelegator =
        StructuredMessageCreateParams.builder<X>().wrap(mockBuilderDelegate)

    @Test
    fun `delegation of outputConfig with class`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method - it converts a Class to an OutputConfig with JsonOutputFormat
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that the current output config was read and then set with the derived format
        val expectedFormat = outputFormatFromClass(X::class.java)
        val expectedOutputConfig = OutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with class merges into current output config`() {
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(X::class.java)

        // Only the format of the current output config is replaced
        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_FORMAT.toBuilder()
                .format(outputFormatFromClass(X::class.java))
                .build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with StructuredOutputConfig`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method - it unwraps a StructuredOutputConfig to its raw OutputConfig
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(OutputConfig.Effort.HIGH)
                .format(X::class.java)
                .build()
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", structuredOutputConfig)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig was called with the raw OutputConfig, effort included
        verify(mockBuilderDelegate, times(1)).outputConfig(structuredOutputConfig.rawOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with OutputConfig`() {
        // With no current output config, the given output config is passed through verbatim
        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with OutputConfig carries over current format`() {
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder().format(OTHER_FORMAT).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with OutputConfig keeps given format`() {
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(OutputConfig.builder().format(outputFormatFromClass(X::class.java)).build())

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_FORMAT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with JsonField`() {
        // A known value is merged like an `OutputConfig`; any other JSON field is passed verbatim
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
    fun `GA version does not inject beta header`() {
        // Verify that the GA version does NOT add any beta header
        val builder =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(X::class.java)
                .build()

        val params = builder.rawParams

        // No beta header should be present
        val headers = params._headers()
        assertThat(headers.names()).doesNotContain("anthropic-beta")
    }

    @Test
    fun `outputConfig with StructuredOutputConfig preserves other output options`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(OutputConfig.Effort.HIGH)
                .format(X::class.java)
                .build()

        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(OutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(outputFormatFromClass(X::class.java))
        // No beta header should be present
        assertThat(params.rawParams._headers().names()).doesNotContain("anthropic-beta")
    }

    @Test
    fun `outputConfig with StructuredOutputConfig replaces the output config`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(OutputConfig.Effort.HIGH)
                .format(X::class.java)
                .build()

        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(
                    OUTPUT_CONFIG_WITH_FORMAT.toBuilder().effort(OutputConfig.Effort.LOW).build()
                )
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
    }

    @Test
    fun `outputConfig with class after output config with effort keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputConfig(X::class.java)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(OutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(outputFormatFromClass(X::class.java))
    }

    @Test
    fun `output config with effort after outputConfig with class keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(OutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(outputFormatFromClass(X::class.java))
    }

    @Test
    fun `outputConfig with class after output config with format replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(
                    OUTPUT_CONFIG_WITH_FORMAT.toBuilder().effort(OutputConfig.Effort.LOW).build()
                )
                .outputConfig(X::class.java)
                .build()

        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(OutputConfig.Effort.LOW)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(outputFormatFromClass(X::class.java))
    }

    @Test
    fun `output config with format after outputConfig with class replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_SONNET_4_5)
                .addUserMessage("test")
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(OUTPUT_CONFIG_WITH_FORMAT)
    }
}
