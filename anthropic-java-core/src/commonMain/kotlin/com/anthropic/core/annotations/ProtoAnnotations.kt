package com.anthropic.core.annotations

import com.anthropic.core.http.HttpMethod

/**
 * Wire-style proto annotations for gRPC/RSocket/MCP service definitions.
 *
 * These annotations describe the protocol method, path, and streaming mode
 * for service interface methods — similar to Square Wire's @WireRpc.
 * Compatible with code generation from .proto files or manual annotation.
 */

/**
 * Marks a service method with its RPC metadata.
 *
 * Works across all protocol modes:
 * - gRPC: path = "/package.Service/Method", method = GRPC_UNARY
 * - RSocket: path = route string, method = RSOCKET_REQUEST_STREAM
 * - MCP: path = JSON-RPC method name, method = MCP_REQUEST
 * - HTTP: path = "/v1/messages", method = POST
 *
 * Example:
 * ```kotlin
 * @Rpc(path = "/anthropic.Messages/Create", method = HttpMethod.GRPC_UNARY)
 * suspend fun create(request: CreateMessageRequest): Message
 *
 * @Rpc(path = "/v1/messages", method = HttpMethod.POST)
 * suspend fun create(request: CreateMessageRequest): Message
 * ```
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Rpc(
    /** Service path — gRPC path, HTTP endpoint, RSocket route, or MCP method name. */
    val path: String,
    /** Protocol method with streaming mode. */
    val method: HttpMethod = HttpMethod.POST,
    /** Request type name (fully qualified proto message). Empty = inferred from parameter. */
    val requestType: String = "",
    /** Response type name (fully qualified proto message). Empty = inferred from return. */
    val responseType: String = "",
)

/**
 * Marks a class as a proto-style service definition.
 *
 * Example:
 * ```kotlin
 * @ProtoService(name = "anthropic.v1.Messages")
 * interface MessageService {
 *     @Rpc(path = "/anthropic.v1.Messages/Create", method = HttpMethod.GRPC_UNARY)
 *     suspend fun create(request: CreateRequest): Message
 * }
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProtoService(
    /** Fully qualified proto service name. */
    val name: String,
    /** Default protocol for all methods in this service. */
    val protocol: HttpMethod.Protocol = HttpMethod.Protocol.HTTP,
)

/**
 * Marks a method as server-streaming — returns Flow<T>.
 *
 * Example:
 * ```kotlin
 * @Rpc(path = "/anthropic.v1.Messages/CreateStream", method = HttpMethod.GRPC_SERVER_STREAM)
 * @Streaming
 * suspend fun createStream(request: CreateRequest): Flow<MessageEvent>
 * ```
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Streaming(
    /** Streaming mode. Default SERVER for SSE/gRPC server-streaming patterns. */
    val mode: HttpMethod.StreamMode = HttpMethod.StreamMode.SERVER,
)

/**
 * Marks a proto field number for Wire compatibility.
 *
 * Example:
 * ```kotlin
 * @ProtoField(tag = 1) val model: String
 * @ProtoField(tag = 2) val maxTokens: Int
 * ```
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProtoField(
    /** Proto field tag number. */
    val tag: Int,
    /** Wire type: "string", "int32", "int64", "bool", "bytes", "message", "enum". */
    val type: String = "",
)
