// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.messages.BetaServerToolUseBlock
import com.anthropic.models.beta.messages.BetaToolUseBlock
import com.anthropic.models.beta.messages.BetaWebFetchToolResultBlock
import com.anthropic.models.beta.messages.BetaWebSearchToolResultBlock
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CallerToParamTest {

    private val jsonMapper = jsonMapper()

    private val callers =
        listOf(
            """{"type":"code_execution_20250825","tool_id":"srvtoolu_caller"}""",
            """{"type":"code_execution_20260120","tool_id":"srvtoolu_caller"}""",
        )

    @Test
    fun stableBlocksPreserveCodeExecutionCallers() {
        callers.forEach { caller ->
            assertCallerPreserved(
                """{"type":"tool_use","id":"toolu_1","input":{},"name":"test","caller":<caller>}""",
                caller,
                jacksonTypeRef<ToolUseBlock>(),
                ToolUseBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"server_tool_use","id":"srvtoolu_1","input":{},"name":"web_search","caller":<caller>}""",
                caller,
                jacksonTypeRef<ServerToolUseBlock>(),
                ServerToolUseBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"web_search_tool_result","tool_use_id":"srvtoolu_1","content":{"type":"web_search_tool_result_error","error_code":"invalid_tool_input"},"caller":<caller>}""",
                caller,
                jacksonTypeRef<WebSearchToolResultBlock>(),
                WebSearchToolResultBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"web_fetch_tool_result","tool_use_id":"srvtoolu_1","content":{"type":"web_fetch_tool_result_error","error_code":"invalid_tool_input"},"caller":<caller>}""",
                caller,
                jacksonTypeRef<WebFetchToolResultBlock>(),
                WebFetchToolResultBlock::toParam,
            )
        }
    }

    @Test
    fun betaBlocksPreserveCodeExecutionCallers() {
        callers.forEach { caller ->
            assertCallerPreserved(
                """{"type":"tool_use","id":"toolu_1","input":{},"name":"test","caller":<caller>}""",
                caller,
                jacksonTypeRef<BetaToolUseBlock>(),
                BetaToolUseBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"server_tool_use","id":"srvtoolu_1","input":{},"name":"web_search","caller":<caller>}""",
                caller,
                jacksonTypeRef<BetaServerToolUseBlock>(),
                BetaServerToolUseBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"web_search_tool_result","tool_use_id":"srvtoolu_1","content":{"type":"web_search_tool_result_error","error_code":"invalid_tool_input"},"caller":<caller>}""",
                caller,
                jacksonTypeRef<BetaWebSearchToolResultBlock>(),
                BetaWebSearchToolResultBlock::toParam,
            )
            assertCallerPreserved(
                """{"type":"web_fetch_tool_result","tool_use_id":"srvtoolu_1","content":{"type":"web_fetch_tool_result_error","error_code":"invalid_tool_input"},"caller":<caller>}""",
                caller,
                jacksonTypeRef<BetaWebFetchToolResultBlock>(),
                BetaWebFetchToolResultBlock::toParam,
            )
        }
    }

    private fun <T> assertCallerPreserved(
        block: String,
        caller: String,
        typeReference: TypeReference<T>,
        toParam: (T) -> Any,
    ) {
        val response = jsonMapper.readValue(block.replace("<caller>", caller), typeReference)
        val param = jsonMapper.readTree(jsonMapper.writeValueAsString(toParam(response)))

        assertThat(param["caller"]).isEqualTo(jsonMapper.readTree(caller))
    }
}
