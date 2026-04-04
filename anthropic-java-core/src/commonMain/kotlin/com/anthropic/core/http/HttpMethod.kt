package com.anthropic.core.http

/**
 * HTTP method enum — matches Spring/standard integrations.
 *
 * Includes standard RFC 7231 methods plus WebDAV extensions for okio
 * file system integration (RFC 4918).
 */
enum class HttpMethod(val value: String) {
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    CONNECT("CONNECT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    PATCH("PATCH"),

    // WebDAV methods (RFC 4918) — for okio file system integration
    PROPFIND("PROPFIND"),
    PROPPATCH("PROPPATCH"),
    MKCOL("MKCOL"),
    COPY("COPY"),
    MOVE("MOVE"),
    LOCK("LOCK"),
    UNLOCK("UNLOCK"),
    ;
}
