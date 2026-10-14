package com.anthropic.core.opentelemetry

/** Whether a client's spans hold the content of its requests and responses. */
enum class OpenTelemetryContentMode {
    /** The spans hold no content. */
    METADATA_ONLY,
    /** The spans also hold the content of the requests and responses. */
    CONTENT,
}
