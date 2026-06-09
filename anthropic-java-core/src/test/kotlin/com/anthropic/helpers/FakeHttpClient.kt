package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.models.beta.messages.MessageCreateParams
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicBoolean

/**
 * An [HttpClient] that replays canned responses in order, capturing each request.
 *
 * A canned response may be a JSON body [String] (HTTP 200), a status-to-body [Pair], a [Streaming]
 * body, a [Failure] to throw, or a `() -> Any` supplier of any of these, invoked when its request
 * executes (e.g. to block until the test releases it).
 */
internal class FakeHttpClient(responses: List<Any>) : HttpClient {

    constructor(vararg responses: Any) : this(responses.toList())

    private val remaining = responses.toMutableList()

    /** The requests executed so far, buffered for repeated reads. */
    val requests = mutableListOf<HttpRequest>()

    /** The responses issued so far, exposing whether each has been closed. */
    val issued = mutableListOf<TrackedResponse>()

    val jsonBodies: List<ObjectNode>
        get() = requests.map { it.body!!.json(ObjectNode::class.java) }

    val bodies: List<MessageCreateParams.Body>
        get() = requests.map { it.body!!.json(MessageCreateParams.Body::class.java) }

    fun models(): List<String> = bodies.map { it.model().asString() }

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
        requests.add(request.buffered())
        return materialize(remaining.removeAt(0))
    }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions,
    ): CompletableFuture<HttpResponse> =
        try {
            CompletableFuture.completedFuture(execute(request, requestOptions))
        } catch (e: Throwable) {
            CompletableFuture<HttpResponse>().apply { completeExceptionally(e) }
        }

    override fun close() {}

    private fun materialize(response: Any): HttpResponse =
        when (response) {
            is Function0<*> -> materialize(response()!!)
            is Failure -> throw response.error
            else -> TrackedResponse(response).also { issued.add(it) }
        }

    internal class TrackedResponse(private val response: Any) : HttpResponse {

        val closed = AtomicBoolean()

        override fun statusCode(): Int = if (response is Pair<*, *>) response.first as Int else 200

        override fun headers(): Headers = Headers.builder().build()

        override fun body(): InputStream =
            when (response) {
                is String -> ByteArrayInputStream(response.toByteArray())
                is Pair<*, *> -> ByteArrayInputStream((response.second as String).toByteArray())
                is Streaming -> response.body
                else -> throw IllegalArgumentException("Unexpected canned response: $response")
            }

        override fun close() {
            closed.set(true)
        }
    }
}

/** A canned streaming response body, delivered as-is (not re-readable). */
internal class Streaming(val body: InputStream)

/** A canned failure, thrown from the execute call itself. */
internal class Failure(val error: Throwable)

/** Runs [block] with [System.err] redirected, returning what it wrote. */
internal fun captureStderr(block: () -> Unit): String {
    val originalErr = System.err
    val stderr = ByteArrayOutputStream()
    System.setErr(PrintStream(stderr, true))
    try {
        block()
    } finally {
        System.setErr(originalErr)
    }
    return stderr.toString()
}

/** Executes [request] through the sync or async path, joining the async result. */
internal fun HttpClient.execute(
    request: HttpRequest,
    requestOptions: RequestOptions,
    async: Boolean,
): HttpResponse =
    if (async) executeAsync(request, requestOptions).get() else execute(request, requestOptions)
