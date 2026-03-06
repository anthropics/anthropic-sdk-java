package com.anthropic.models.beta.messages

import com.anthropic.helpers.BetaMemoryToolHandler
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class ToolRunnerCreateParamsTest {
    private class TestMemoryToolHandler : BetaMemoryToolHandler {
        override fun create(path: String, fileText: String): String = ""

        override fun view(path: String, viewRange: Optional<List<Long>>): String = ""

        override fun strReplace(path: String, oldStr: String, newStr: String): String = ""

        override fun insert(path: String, insertLine: Long, insertText: String): String = ""

        override fun delete(path: String): String = ""

        override fun rename(oldPath: String, newPath: String): String = ""
    }

    companion object {
        private val messageCreateParams =
            MessageCreateParams.builder()
                .maxTokens(2048)
                .addUserMessage("Hello, World!")
                .model("test-model")
                .build()

        private val memoryTool = BetaMemoryTool20250818.builder().build()
    }

    @Test
    fun buildWithoutMemoryTool() {
        assertThatNoException().isThrownBy {
            ToolRunnerCreateParams.builder().initialMessageParams(messageCreateParams).build()
        }
    }

    @Test
    fun buildWithoutMemoryToolButMemoryToolHandlerIsSet() {
        assertThatThrownBy {
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(messageCreateParams)
                    .betaMemoryToolHandler(TestMemoryToolHandler())
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Memory tool not defined")
    }

    @Test
    fun buildWithMemoryToolButMemoryToolHandlerIsNotSet() {
        val initialParams = messageCreateParams.toBuilder().addTool(memoryTool).build()

        assertThatThrownBy {
                ToolRunnerCreateParams.builder().initialMessageParams(initialParams).build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("Memory tool defined")
    }

    @Test
    fun buildWithMemoryToolAndMemoryToolHandlerIsSet() {
        val memoryToolHandler = TestMemoryToolHandler()
        val initialParams = messageCreateParams.toBuilder().addTool(memoryTool).build()
        val toolParams =
            ToolRunnerCreateParams.builder()
                .initialMessageParams(initialParams)
                .betaMemoryToolHandler(memoryToolHandler)
                .build()

        assertThat(toolParams.betaMemoryToolHandler()).isPresent
        assertThat(toolParams.betaMemoryToolHandler().get()).isSameAs(memoryToolHandler)
    }
}
