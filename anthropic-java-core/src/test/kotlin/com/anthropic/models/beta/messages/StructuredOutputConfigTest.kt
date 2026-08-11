package com.anthropic.models.beta.messages

import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JSON_FIELD
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.MAP
import com.anthropic.core.OPTIONAL
import com.anthropic.core.SET
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorWriteFunctionsAreTested
import com.anthropic.core.checkOneDelegationWrite
import com.anthropic.core.findDelegationMethod
import io.swagger.v3.oas.annotations.media.Schema
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the [StructuredOutputConfig] class (delegator) and its delegation of most
 * functions to a wrapped [BetaOutputConfig] (delegate). It is the `Builder` class of each main
 * class that is involved in the delegation. The tests include confirmation of the following:
 * - All functions in the delegator correspond to a function in the delegate and _vice versa_.
 * - All functions in the delegator call their corresponding function in the delegate and only that
 *   function.
 * - A unit test exists for all functions.
 *
 * There are some exceptions to the above that are handled differently.
 */
internal class StructuredOutputConfigTest {
    companion object {
        private val NULLABLE_EFFORT: BetaOutputConfig.Effort? = null
        private val NULLABLE_TASK_BUDGET: BetaTokenTaskBudget? = null

        // The list order follows the declaration order in `BetaOutputConfig.Builder` for easier
        // maintenance.
        @JvmStatic
        private fun builderDelegationTestCases() =
            listOf(
                DelegationWriteTestCase("effort", NULLABLE_EFFORT),
                DelegationWriteTestCase("effort", OPTIONAL),
                DelegationWriteTestCase("effort", JSON_FIELD),
                // `format` is a special case that is tested separately.
                DelegationWriteTestCase("taskBudget", NULLABLE_TASK_BUDGET),
                DelegationWriteTestCase("taskBudget", OPTIONAL),
                DelegationWriteTestCase("taskBudget", JSON_FIELD),
                DelegationWriteTestCase("additionalProperties", MAP),
                DelegationWriteTestCase("putAdditionalProperty", STRING, JSON_VALUE),
                DelegationWriteTestCase("putAllAdditionalProperties", MAP),
                DelegationWriteTestCase("removeAdditionalProperty", STRING),
                DelegationWriteTestCase("removeAllAdditionalProperties", SET),
            )
    }

    // New instances of the `mockBuilderDelegate` and `builderDelegator` are required for each test
    // case (each test case runs in its own instance of the test class).
    private val mockBuilderDelegate: BetaOutputConfig.Builder =
        mock(BetaOutputConfig.Builder::class.java)
    private val builderDelegator = StructuredOutputConfig.builder<X>().inject(mockBuilderDelegate)

    @Test
    fun allBuilderDelegateFunctionsExistInDelegator() {
        // The delegator class does not implement the various `format` functions of the delegate
        // class; the format is derived from the output type instead.
        checkAllDelegation(mockBuilderDelegate::class, builderDelegator::class, "format")
    }

    @Test
    fun allBuilderDelegatorFunctionsExistInDelegate() {
        // The delegator implements a different `format` function from those overloads in the
        // delegate class.
        checkAllDelegation(builderDelegator::class, mockBuilderDelegate::class, "format")
    }

    @Test
    fun allBuilderDelegatorFunctionsAreTested() {
        checkAllDelegatorWriteFunctionsAreTested(
            builderDelegator::class,
            builderDelegationTestCases(),
            // This function has non-standard delegation and is tested separately below.
            exceptionalTestedFns = listOf("format"),
            nonDelegatingFns = setOf("build", "inject", "from"),
        )
    }

    @ParameterizedTest
    @MethodSource("builderDelegationTestCases")
    fun `delegation of Builder write functions`(testCase: DelegationWriteTestCase) {
        checkOneDelegationWrite(builderDelegator, mockBuilderDelegate, testCase)
    }

    @Test
    fun `delegation of format`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method: it converts a `Class` to a `BetaJsonOutputFormat`.
        val delegatorTestCase = DelegationWriteTestCase("format", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        verify(mockBuilderDelegate, times(1)).format(betaOutputFormatFromClass(X::class.java))
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun build() {
        val taskBudget = BetaTokenTaskBudget.builder().total(1024L).build()
        val outputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .format(X::class.java)
                .taskBudget(taskBudget)
                .putAdditionalProperty(STRING, JSON_VALUE)
                .build()

        assertThat(outputConfig.outputType).isEqualTo(X::class.java)
        assertThat(outputConfig.rawOutputConfig)
            .isEqualTo(
                BetaOutputConfig.builder()
                    .effort(BetaOutputConfig.Effort.HIGH)
                    .format(betaOutputFormatFromClass(X::class.java))
                    .taskBudget(taskBudget)
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
                .effort(BetaOutputConfig.Effort.LOW)
                .format(X::class.java)
                .build()

        assertThat(outputConfig.toBuilder().build()).isEqualTo(outputConfig)
        assertThat(outputConfig.toBuilder().effort(BetaOutputConfig.Effort.MAX).build())
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
