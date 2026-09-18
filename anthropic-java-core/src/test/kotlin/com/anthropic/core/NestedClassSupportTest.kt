package com.anthropic.core

import com.anthropic.helpers.BetaRunnableTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.beta.messages.StructuredOutputConfig as BetaStructuredOutputConfig
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.StructuredOutputConfig
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class NestedClassSupportTest {

    enum class EntryPoint(val accept: (Class<*>) -> Unit) {
        BETA_ADD_TOOL({ BetaMessageCreateParams.builder().addTool(it) }),
        BETA_RUNNABLE_TOOL({ runnableTool(it) }),
        BETA_OUTPUT_CONFIG({ BetaMessageCreateParams.builder().outputConfig(it) }),
        BETA_OUTPUT_FORMAT({ BetaMessageCreateParams.builder().outputFormat(it) }),
        BETA_STRUCTURED_OUTPUT_CONFIG_FORMAT({ betaStructuredOutputConfig(it) }),
        OUTPUT_CONFIG({ MessageCreateParams.builder().outputConfig(it) }),
        STRUCTURED_OUTPUT_CONFIG_FORMAT({ structuredOutputConfig(it) }),
    }

    @ParameterizedTest
    @EnumSource
    fun staticNestedClass_isAccepted(entryPoint: EntryPoint) {
        assertThatCode { entryPoint.accept(NestedClassJavaFixtures.GetWeather::class.java) }
            .doesNotThrowAnyException()
    }

    @ParameterizedTest
    @EnumSource
    @Suppress("unused")
    fun functionLocalClass_isAccepted(entryPoint: EntryPoint) {
        class GetWeather(val location: String)

        assertThatCode { entryPoint.accept(GetWeather::class.java) }.doesNotThrowAnyException()
    }

    @ParameterizedTest
    @EnumSource
    fun memberClass_isRejected(entryPoint: EntryPoint) {
        assertThatThrownBy { entryPoint.accept(NestedClassJavaFixtures.Member::class.java) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @EnumSource
    fun localClass_isRejected(entryPoint: EntryPoint) {
        assertThatThrownBy { entryPoint.accept(NestedClassJavaFixtures.localClass()) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @EnumSource
    fun anonymousClass_isRejected(entryPoint: EntryPoint) {
        assertThatThrownBy { entryPoint.accept(NestedClassJavaFixtures.anonymousClass()) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }
}

private fun <T : Any> runnableTool(parametersType: Class<T>) =
    BetaRunnableTool.of(parametersType, { BetaToolResultBlockParam.Content.ofString("") })

private fun <T : Any> structuredOutputConfig(outputType: Class<T>) =
    StructuredOutputConfig.builder<T>().format(outputType)

private fun <T : Any> betaStructuredOutputConfig(outputType: Class<T>) =
    BetaStructuredOutputConfig.builder<T>().format(outputType)
