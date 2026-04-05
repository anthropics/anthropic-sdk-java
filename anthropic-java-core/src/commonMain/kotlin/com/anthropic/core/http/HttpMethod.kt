package com.anthropic.core.http

/**
 * Protocol method enum — covers HTTP, WebDAV, gRPC, RSocket, and MCP transports.
 *
 * Matches Spring/standard integrations. Each method has:
 * - [value]: wire-level method string
 * - [protocol]: which protocol family it belongs to
 * - [streamMode]: streaming behavior (unary, server, client, bidi)
 *
 * gRPC methods map to HTTP/2 POST with content-type application/grpc.
 * RSocket interaction models map to their native frame types.
 * MCP methods map to JSON-RPC over HTTP SSE / WebSocket / stdio.
 */
enum class HttpMethod(
    val value: String,
    val protocol: Protocol = Protocol.HTTP,
    val streamMode: StreamMode = StreamMode.UNARY,
) {
    // ---- HTTP (RFC 7231) ----
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    CONNECT("CONNECT", streamMode = StreamMode.BIDI),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    PATCH("PATCH"),

    // ---- WebDAV (RFC 4918) — okio file system integration ----
    PROPFIND("PROPFIND", Protocol.WEBDAV),
    PROPPATCH("PROPPATCH", Protocol.WEBDAV),
    MKCOL("MKCOL", Protocol.WEBDAV),
    COPY("COPY", Protocol.WEBDAV),
    MOVE("MOVE", Protocol.WEBDAV),
    LOCK("LOCK", Protocol.WEBDAV),
    UNLOCK("UNLOCK", Protocol.WEBDAV),

    // ---- gRPC interaction models (over HTTP/2 POST) ----
    GRPC_UNARY("POST", Protocol.GRPC, StreamMode.UNARY),
    GRPC_SERVER_STREAM("POST", Protocol.GRPC, StreamMode.SERVER),
    GRPC_CLIENT_STREAM("POST", Protocol.GRPC, StreamMode.CLIENT),
    GRPC_BIDI_STREAM("POST", Protocol.GRPC, StreamMode.BIDI),

    // ---- RSocket interaction models ----
    RSOCKET_REQUEST_RESPONSE("REQUEST_RESPONSE", Protocol.RSOCKET, StreamMode.UNARY),
    RSOCKET_FIRE_AND_FORGET("FIRE_AND_FORGET", Protocol.RSOCKET, StreamMode.UNARY),
    RSOCKET_REQUEST_STREAM("REQUEST_STREAM", Protocol.RSOCKET, StreamMode.SERVER),
    RSOCKET_REQUEST_CHANNEL("REQUEST_CHANNEL", Protocol.RSOCKET, StreamMode.BIDI),
    RSOCKET_METADATA_PUSH("METADATA_PUSH", Protocol.RSOCKET, StreamMode.UNARY),

    // ---- MCP (Model Context Protocol — JSON-RPC over SSE/WebSocket/stdio) ----
    MCP_REQUEST("request", Protocol.MCP, StreamMode.UNARY),
    MCP_NOTIFICATION("notification", Protocol.MCP, StreamMode.UNARY),
    MCP_STREAM("request", Protocol.MCP, StreamMode.SERVER),
    ;

    /** Protocol family. */
    enum class Protocol {
        HTTP, WEBDAV, GRPC, RSOCKET, MCP
    }

    /** Streaming mode — how data flows between client and server. */
    enum class StreamMode {
        /** Single request, single response. */
        UNARY,
        /** Single request, stream of responses. */
        SERVER,
        /** Stream of requests, single response. */
        CLIENT,
        /** Bidirectional stream. */
        BIDI,
    }

    /** True if this method involves any streaming. */
    val isStreaming: Boolean get() = streamMode != StreamMode.UNARY

    /** True if the server sends a stream of responses. */
    val isServerStreaming: Boolean get() = streamMode == StreamMode.SERVER || streamMode == StreamMode.BIDI

    /** True if the client sends a stream of requests. */
    val isClientStreaming: Boolean get() = streamMode == StreamMode.CLIENT || streamMode == StreamMode.BIDI
}
