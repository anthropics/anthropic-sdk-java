package com.anthropic.helpers

import com.anthropic.core.checkRequired
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import java.util.function.Function

/**
 * An MCP tool converted for use with the Anthropic API.
 *
 * Holds both the tool definition (name, schema, description) and the runner that executes the tool
 * against the MCP server. Pass to
 * [com.anthropic.models.beta.messages.MessageCreateParams.Builder.addTool] to wire it into a
 * request.
 *
 * Instances are produced by `com.anthropic.mcp.BetaMcp.mcpTool` in the `anthropic-java-mcp` module.
 */
class McpBetaTool
private constructor(
    @get:JvmName("name") val name: String,
    @get:JvmName("definition") val definition: BetaTool,
    @get:JvmName("runner") val runner: Function<String, BetaToolResultBlockParam.Content>,
) {

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    fun toBuilder() = Builder().from(this)

    class Builder internal constructor() {

        private var name: String? = null
        private var definition: BetaTool? = null
        private var runner: Function<String, BetaToolResultBlockParam.Content>? = null

        @JvmSynthetic
        internal fun from(tool: McpBetaTool) = apply {
            name = tool.name
            definition = tool.definition
            runner = tool.runner
        }

        /** The tool name as advertised by the MCP server. */
        fun name(name: String) = apply { this.name = name }

        /** The Anthropic tool definition (name, input schema, description). */
        fun definition(definition: BetaTool) = apply { this.definition = definition }

        /** The function that executes the tool given a JSON-encoded input string. */
        fun runner(runner: Function<String, BetaToolResultBlockParam.Content>) = apply {
            this.runner = runner
        }

        fun build(): McpBetaTool =
            McpBetaTool(
                checkRequired("name", name),
                checkRequired("definition", definition),
                checkRequired("runner", runner),
            )
    }
}
