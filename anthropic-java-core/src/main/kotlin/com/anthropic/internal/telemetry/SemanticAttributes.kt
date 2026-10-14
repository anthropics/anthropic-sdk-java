@file:JvmName("SemanticAttributes")

package com.anthropic.internal.telemetry

import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.AttributeKey.booleanKey
import io.opentelemetry.api.common.AttributeKey.doubleKey
import io.opentelemetry.api.common.AttributeKey.longKey
import io.opentelemetry.api.common.AttributeKey.stringArrayKey
import io.opentelemetry.api.common.AttributeKey.stringKey

/*
 * The attributes that the SDK's spans set beyond the generated ones of `TraceApiCall.kt`: the
 * `gen_ai.*` attributes of the OpenTelemetry semantic conventions for generative AI, named as the
 * conventions' Java constants name them, and the SDK's own `anthropic.*` attributes after the same
 * pattern. The SDK keeps its own copies of the `gen_ai.*` ones because the conventions artifact
 * that has them is still incubating.
 */

/** The `gen_ai.agent.id` attribute: the agent that a Session is created from. */
@get:JvmSynthetic internal val GEN_AI_AGENT_ID: AttributeKey<String> = stringKey("gen_ai.agent.id")

/** The `gen_ai.agent.name` attribute: the `name` of the created Session's agent. */
@get:JvmSynthetic
internal val GEN_AI_AGENT_NAME: AttributeKey<String> = stringKey("gen_ai.agent.name")

/**
 * The `gen_ai.conversation.id` attribute: the conversation that the application says a call is in.
 */
@get:JvmSynthetic
internal val GEN_AI_CONVERSATION_ID: AttributeKey<String> = stringKey("gen_ai.conversation.id")

/** The `gen_ai.input.messages` attribute: the request's messages, in content mode. */
@get:JvmSynthetic
internal val GEN_AI_INPUT_MESSAGES: AttributeKey<String> = stringKey("gen_ai.input.messages")

/** The `gen_ai.operation.name` attribute: one of [GenAiOperationNameValues]. */
@get:JvmSynthetic
internal val GEN_AI_OPERATION_NAME: AttributeKey<String> = stringKey("gen_ai.operation.name")

/** The `gen_ai.output.messages` attribute: the response's content, in content mode. */
@get:JvmSynthetic
internal val GEN_AI_OUTPUT_MESSAGES: AttributeKey<String> = stringKey("gen_ai.output.messages")

/**
 * The `gen_ai.output.type` attribute: one of [GenAiOutputTypeValues], when the request sets the
 * output format to JSON.
 */
@get:JvmSynthetic
internal val GEN_AI_OUTPUT_TYPE: AttributeKey<String> = stringKey("gen_ai.output.type")

/** The `gen_ai.provider.name` attribute: `anthropic`, or the platform the client goes through. */
@get:JvmSynthetic
internal val GEN_AI_PROVIDER_NAME: AttributeKey<String> = stringKey("gen_ai.provider.name")

@get:JvmSynthetic
internal val GEN_AI_REQUEST_MAX_TOKENS: AttributeKey<Long> = longKey("gen_ai.request.max_tokens")

/** The `gen_ai.request.model` attribute: the model the request names. */
@get:JvmSynthetic
internal val GEN_AI_REQUEST_MODEL: AttributeKey<String> = stringKey("gen_ai.request.model")

/** The `gen_ai.request.reasoning.level` attribute: the request's `output_config.effort`. */
@get:JvmSynthetic
internal val GEN_AI_REQUEST_REASONING_LEVEL: AttributeKey<String> =
    stringKey("gen_ai.request.reasoning.level")

/**
 * The `gen_ai.request.stop_sequences` attribute: the request's stop sequences, in content mode, one
 * element for each.
 */
@get:JvmSynthetic
internal val GEN_AI_REQUEST_STOP_SEQUENCES: AttributeKey<List<String>> =
    stringArrayKey("gen_ai.request.stop_sequences")

/** The `gen_ai.request.stream` attribute: set, to `true`, when the response is streamed. */
@get:JvmSynthetic
internal val GEN_AI_REQUEST_STREAM: AttributeKey<Boolean> = booleanKey("gen_ai.request.stream")

@get:JvmSynthetic
internal val GEN_AI_REQUEST_TEMPERATURE: AttributeKey<Double> =
    doubleKey("gen_ai.request.temperature")

@get:JvmSynthetic
internal val GEN_AI_REQUEST_TOP_K: AttributeKey<Long> = longKey("gen_ai.request.top_k")

@get:JvmSynthetic
internal val GEN_AI_REQUEST_TOP_P: AttributeKey<Double> = doubleKey("gen_ai.request.top_p")

/** The `gen_ai.response.id` attribute: the Message's `id`. */
@get:JvmSynthetic
internal val GEN_AI_RESPONSE_ID: AttributeKey<String> = stringKey("gen_ai.response.id")

/** The `gen_ai.response.model` attribute: the model that the response names. */
@get:JvmSynthetic
internal val GEN_AI_RESPONSE_MODEL: AttributeKey<String> = stringKey("gen_ai.response.model")

/**
 * The `gen_ai.response.time_to_first_chunk` attribute: the seconds from the start of the API call
 * to the first `content_block_delta` event of its stream.
 */
@get:JvmSynthetic
internal val GEN_AI_RESPONSE_TIME_TO_FIRST_CHUNK: AttributeKey<Double> =
    doubleKey("gen_ai.response.time_to_first_chunk")

/** The `gen_ai.system_instructions` attribute: the request's system prompt, in content mode. */
@get:JvmSynthetic
internal val GEN_AI_SYSTEM_INSTRUCTIONS: AttributeKey<String> =
    stringKey("gen_ai.system_instructions")

/** The `gen_ai.tool.call.arguments` attribute: the input of a tool call, in content mode. */
@get:JvmSynthetic
internal val GEN_AI_TOOL_CALL_ARGUMENTS: AttributeKey<String> =
    stringKey("gen_ai.tool.call.arguments")

/** The `gen_ai.tool.call.id` attribute: the `id` of the `tool_use` block. */
@get:JvmSynthetic
internal val GEN_AI_TOOL_CALL_ID: AttributeKey<String> = stringKey("gen_ai.tool.call.id")

/**
 * The `gen_ai.tool.description` attribute: the description in the definition of the tool that a
 * tool run executes.
 */
@get:JvmSynthetic
internal val GEN_AI_TOOL_DESCRIPTION: AttributeKey<String> = stringKey("gen_ai.tool.description")

@get:JvmSynthetic
internal val GEN_AI_TOOL_NAME: AttributeKey<String> = stringKey("gen_ai.tool.name")

/** The `gen_ai.tool.type` attribute: one of [GenAiToolTypeValues]. */
@get:JvmSynthetic
internal val GEN_AI_TOOL_TYPE: AttributeKey<String> = stringKey("gen_ai.tool.type")

@get:JvmSynthetic
internal val GEN_AI_USAGE_CACHE_READ_INPUT_TOKENS: AttributeKey<Long> =
    longKey("gen_ai.usage.cache_read.input_tokens")

@get:JvmSynthetic
internal val GEN_AI_USAGE_CACHE_WRITE_INPUT_TOKENS: AttributeKey<Long> =
    longKey("gen_ai.usage.cache_write.input_tokens")

/**
 * The `gen_ai.usage.input_tokens` attribute: all the input tokens, the cached and cache-writing
 * ones included.
 */
@get:JvmSynthetic
internal val GEN_AI_USAGE_INPUT_TOKENS: AttributeKey<Long> = longKey("gen_ai.usage.input_tokens")

@get:JvmSynthetic
internal val GEN_AI_USAGE_OUTPUT_TOKENS: AttributeKey<Long> = longKey("gen_ai.usage.output_tokens")

/** The `gen_ai.usage.reasoning.output_tokens` attribute: the output tokens spent on thinking. */
@get:JvmSynthetic
internal val GEN_AI_USAGE_REASONING_OUTPUT_TOKENS: AttributeKey<Long> =
    longKey("gen_ai.usage.reasoning.output_tokens")

/** The values of [GEN_AI_OPERATION_NAME]. */
internal object GenAiOperationNameValues {
    /** A Messages create call. */
    const val CHAT = "chat"

    /** One run of a tool. */
    const val EXECUTE_TOOL = "execute_tool"

    /** One loop of a tool runner. */
    const val INVOKE_AGENT = "invoke_agent"
}

/** The values of [GEN_AI_TOOL_TYPE]. */
internal object GenAiToolTypeValues {
    /** A tool that runs in the application. */
    const val FUNCTION = "function"
}

/** The values of [GEN_AI_OUTPUT_TYPE]. */
internal object GenAiOutputTypeValues {
    /** The request sets a JSON output format. */
    const val JSON = "json"
}

/** The `anthropic.beta` attribute: the beta versions that the request asks for. */
@get:JvmSynthetic
internal val ANTHROPIC_BETA: AttributeKey<List<String>> = stringArrayKey("anthropic.beta")

/**
 * The `anthropic.content.truncated` attribute: set, to `true`, when the size limit removed message
 * parts, the end of a `text` part, or a tool result's content blocks from a content attribute of
 * the span.
 */
@get:JvmSynthetic
internal val ANTHROPIC_CONTENT_TRUNCATED: AttributeKey<Boolean> =
    booleanKey("anthropic.content.truncated")

/**
 * The `anthropic.message.batch.request_count` attribute: the number of requests in the batch that
 * the call creates.
 */
@get:JvmSynthetic
internal val ANTHROPIC_MESSAGE_BATCH_REQUEST_COUNT: AttributeKey<Long> =
    longKey("anthropic.message.batch.request_count")

/**
 * The `anthropic.message.count_tokens.input_tokens` attribute: the count that the call returned.
 */
@get:JvmSynthetic
internal val ANTHROPIC_MESSAGE_COUNT_TOKENS_INPUT_TOKENS: AttributeKey<Long> =
    longKey("anthropic.message.count_tokens.input_tokens")

/**
 * The `anthropic.message.stop_reason` attribute: the Message's `stop_reason`, as the API gives it.
 */
@get:JvmSynthetic
internal val ANTHROPIC_MESSAGE_STOP_REASON: AttributeKey<String> =
    stringKey("anthropic.message.stop_reason")

/** The `anthropic.organization.id` attribute, from the `anthropic-organization-id` header. */
@get:JvmSynthetic
internal val ANTHROPIC_ORGANIZATION_ID: AttributeKey<String> =
    stringKey("anthropic.organization.id")

/** The `anthropic.request.id` attribute, from the `request-id` header. */
@get:JvmSynthetic
internal val ANTHROPIC_REQUEST_ID: AttributeKey<String> = stringKey("anthropic.request.id")

/** The `anthropic.request.service_tier` attribute: the `service_tier` the request asks for. */
@get:JvmSynthetic
internal val ANTHROPIC_REQUEST_SERVICE_TIER: AttributeKey<String> =
    stringKey("anthropic.request.service_tier")

/** The `anthropic.response.service_tier` attribute: the `service_tier` of the response's usage. */
@get:JvmSynthetic
internal val ANTHROPIC_RESPONSE_SERVICE_TIER: AttributeKey<String> =
    stringKey("anthropic.response.service_tier")

/** The `anthropic.session.environment.id` attribute: the environment a Session is created in. */
@get:JvmSynthetic
internal val ANTHROPIC_SESSION_ENVIRONMENT_ID: AttributeKey<String> =
    stringKey("anthropic.session.environment.id")

/** The `anthropic.thinking.budget_tokens` attribute: the request's `thinking.budget_tokens`. */
@get:JvmSynthetic
internal val ANTHROPIC_THINKING_BUDGET_TOKENS: AttributeKey<Long> =
    longKey("anthropic.thinking.budget_tokens")

/** The `anthropic.thinking.type` attribute: the request's `thinking.type`. */
@get:JvmSynthetic
internal val ANTHROPIC_THINKING_TYPE: AttributeKey<String> = stringKey("anthropic.thinking.type")

/**
 * The `anthropic.tool_result.content` attribute: the content blocks that a tool run sends back, in
 * content mode.
 */
@get:JvmSynthetic
internal val ANTHROPIC_TOOL_RESULT_CONTENT: AttributeKey<String> =
    stringKey("anthropic.tool_result.content")

/**
 * The `anthropic.toolset.name` attribute: the `toolset_name` of the `tool_use` block that a tool
 * run executes.
 */
@get:JvmSynthetic
internal val ANTHROPIC_TOOLSET_NAME: AttributeKey<String> = stringKey("anthropic.toolset.name")

@get:JvmSynthetic
internal val ANTHROPIC_USAGE_CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS: AttributeKey<Long> =
    longKey("anthropic.usage.cache_creation.ephemeral_1h_input_tokens")

@get:JvmSynthetic
internal val ANTHROPIC_USAGE_CACHE_CREATION_EPHEMERAL_5M_INPUT_TOKENS: AttributeKey<Long> =
    longKey("anthropic.usage.cache_creation.ephemeral_5m_input_tokens")

/**
 * The `anthropic.usage.compaction.input_tokens` attribute: the input tokens of the compactions in
 * the usage's iterations, the cached and cache-writing ones included.
 */
@get:JvmSynthetic
internal val ANTHROPIC_USAGE_COMPACTION_INPUT_TOKENS: AttributeKey<Long> =
    longKey("anthropic.usage.compaction.input_tokens")

/** The `anthropic.usage.compaction.output_tokens` attribute: the output tokens of the same. */
@get:JvmSynthetic
internal val ANTHROPIC_USAGE_COMPACTION_OUTPUT_TOKENS: AttributeKey<Long> =
    longKey("anthropic.usage.compaction.output_tokens")

/** The `anthropic.workspace.id` attribute, from the `anthropic-workspace-id` header. */
@get:JvmSynthetic
internal val ANTHROPIC_WORKSPACE_ID: AttributeKey<String> = stringKey("anthropic.workspace.id")
