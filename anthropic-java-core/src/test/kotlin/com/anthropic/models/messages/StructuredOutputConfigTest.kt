package com.anthropic.models.messages

import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.findDelegationMethod
import com.anthropic.core.outputFormatFromClass
import io.swagger.v3.oas.annotations.media.Schema
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the functions of the GA [StructuredOutputConfig.Builder] class (delegator) that do
 * more than call the function of the same name in the wrapped [OutputConfig.Builder] (delegate).
 */
internal class StructuredOutputConfigTest {
    // New instances of the `mockBuilderDelegate` and `builderDelegator` are required for each test
    // case (each test case runs in its own instance of the test class).
    private val mockBuilderDelegate: OutputConfig.Builder = mock(OutputConfig.Builder::class.java)
    private val builderDelegator = StructuredOutputConfig.builder<X>().inject(mockBuilderDelegate)

    @Test
    fun `delegation of format`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method: it converts a `Class` to a `JsonOutputFormat`.
        val delegatorTestCase = DelegationWriteTestCase("format", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        verify(mockBuilderDelegate, times(1)).format(outputFormatFromClass(X::class.java))
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun build() {
        val outputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(OutputConfig.Effort.HIGH)
                .format(X::class.java)
                .putAdditionalProperty(STRING, JSON_VALUE)
                .build()

        assertThat(outputConfig.outputType).isEqualTo(X::class.java)
        assertThat(outputConfig.rawOutputConfig)
            .isEqualTo(
                OutputConfig.builder()
                    .effort(OutputConfig.Effort.HIGH)
                    .format(outputFormatFromClass(X::class.java))
                    .putAdditionalProperty(STRING, JSON_VALUE)
                    .build()
            )
    }

    @Test
    fun buildWithoutFormatThrows() {
        assertThatThrownBy { StructuredOutputConfig.builder<X>().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("`format` is required, but was not set")
    }

    @Test
    fun roundtripToBuilder() {
        val outputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(OutputConfig.Effort.LOW)
                .format(X::class.java)
                .build()

        assertThat(outputConfig.toBuilder().build()).isEqualTo(outputConfig)
        assertThat(outputConfig.toBuilder().effort(OutputConfig.Effort.MAX).build())
            .isNotEqualTo(outputConfig)
    }

    @Test
    @Suppress("unused")
    fun formatWithLocalValidationFailure() {
        // A class that results in an invalid schema (`"pattern"` is not a supported keyword).
        class Y(@get:Schema(pattern = "unsupported") val s: String)

        assertThatThrownBy { StructuredOutputConfig.builder<Y>().format(Y::class.java) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)

        val outputConfig =
            StructuredOutputConfig.builder<Y>()
                .format(Y::class.java, JsonSchemaLocalValidation.NO)
                .build()
        assertThat(outputConfig.rawOutputConfig.format()).isPresent()
    }
}
