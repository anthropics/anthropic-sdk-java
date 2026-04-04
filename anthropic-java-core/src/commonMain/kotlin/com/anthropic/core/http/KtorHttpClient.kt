package com.anthropic.core.http

import com.anthropic.core.RequestOptions
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse as KtorResponse
import io.ktor.client.statement.readRawBytes
import io.ktor.http.*
import io.ktor.http.content.ByteArrayContent
import kotlinx.coroutines.*
import okio.Buffer
import java.util.concurrent.CompletableFuture

/**
 * Ktor-based HTTP client implementation.
 * Uses Ktor client directly — replaces custom retry/multipart/SSE infrastructure.
 */
class KtorHttpClient(
    private val ktorClient: io.ktor.client.HttpClient = io.ktor.client.HttpClient {
        install(HttpTimeout)
        expectSuccess = false
    }
) : HttpClient {

    override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
        runBlocking { executeKtor(request, requestOptions) }

    override fun executeAsync(
        request: HttpRequest,
        requestOptions: RequestOptions
    ): CompletableFuture<HttpResponse> {
        val future = CompletableFuture<HttpResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                future.complete(executeKtor(request, requestOptions))
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }
        return future
    }

    private suspend fun executeKtor(
        request: HttpRequest,
        requestOptions: RequestOptions
    ): HttpResponse {
        val sdkTimeout = requestOptions.timeout
        val response: KtorResponse = ktorClient.request {
            this.method = io.ktor.http.HttpMethod(request.method.value)  // enum.value → ktor method
            url(request.url())
            request.headers.names().forEach { name ->
                request.headers.values(name).forEach { value ->
                    headers.append(name, value)
                }
            }
            request.body?.let { body ->
                val buf = Buffer()
                body.writeTo(buf)
                setBody(ByteArrayContent(buf.readByteArray(), body.contentType()?.let {
                    ContentType.parse(it)
                } ?: ContentType.Application.Json))
            }
            if (sdkTimeout != null) {
                timeout {
                    requestTimeoutMillis = sdkTimeout.request().inWholeMilliseconds
                    connectTimeoutMillis = sdkTimeout.connect().inWholeMilliseconds
                    socketTimeoutMillis = sdkTimeout.read().inWholeMilliseconds
                }
            }
        }
        val body = response.readRawBytes()
        return object : HttpResponse {
            override fun statusCode() = response.status.value
            override fun headers(): Headers {
                val b = Headers.builder()
                response.headers.forEach { name, values -> values.forEach { b.put(name, it) } }
                return b.build()
            }
            override fun body() = Buffer().write(body)
            override fun close() {}
        }
    }

    override fun close() = ktorClient.close()
}

// HttpMethod conversion removed — now uses io.ktor.http.HttpMethod directly.
