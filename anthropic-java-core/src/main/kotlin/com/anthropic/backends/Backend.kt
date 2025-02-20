package com.anthropic.backends

import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import java.io.Closeable

/**
 * An interface that represents adaptations required to support different
 * backend AI services. Different backends may require different credentials,
 * request contents, request authorization, request signatures, response
 * handlers and endpoint URLs. Implementations of this interface may define
 * the information and handling appropriate for each backend service.
 *
 * When a backend is no longer needed, such as when the client it is associated
 * with is closed and discarded, [close] should be called on the backend to
 * ensure any resources it is using are released.
 */
// "Backend.close()" should be called from "HttpClient.close()" (where
// implemented), so a simple "Closeable" is sufficient. There is no use case
// for using "AutoCloseable" at this time.
interface Backend : Closeable {
    /**
     * Gets the backend service's base URL (endpoint) identifying the service
     * that will be accessed by this backend.
     */
    fun baseUrl(): String

    /**
     * Prepares the request for use by the backend service. This allows changes
     * to be made to the request to adapt it for backends other than the default
     * Anthropic backend.
     *
     * Changes may be made to the headers, path segments, query parameters, and
     * body content to suit the requirements of the backend where they differ
     * from the default Anthropic backend.
     *
     * If no changes are required, the given request can be returned unchanged,
     * which is the behavior of the default implementation of this method.
     *
     * Changes specifically related to request authorization or signing should
     * be performed by the implementation of [authorizeRequest].
     *
     * @param request The request to prepare. This request will not be modified.
     *
     * @return A new request prepared for use by the backend service for which
     *     this method is implemented.
     */
    fun prepareRequest(request: HttpRequest): HttpRequest  {
        return request
    }

    /**
     * Authorizes the given request and returns the new authorized request.
     *
     * In addition to adding authorization headers or signature headers, the
     * request may be modified in other ways to adapt it to the requirements of
     * the backend service. However, most such changes should be performed by
     * [prepareRequest] if they are not related to authorization.
     *
     * Where a backend does not require request authorization or other related
     * changes to the request, the given [HttpRequest] may be returned
     * unchanged. This is the behavior of the default implementation.
     *
     * @param request The request to be authorized. This will not be modified;
     *     if any changes are required, a modified copy will be returned.
     *
     * @return The authorized request. This may be the same instance as the
     *     given request if a signature is not required, or it may be a new
     *     instance of [HttpRequest] if a signature *is* required.
     */
    fun authorizeRequest(request: HttpRequest): HttpRequest {
        return request
    }

    /**
     * Prepares the response returned by the backend service. This allows
     * changes to be made to the response to adapt it for backend services
     * other than the default Anthropic backend service. The adaptations make
     * the response appear to come from that default Anthropic backend.
     *
     * If no changes are required, the given response can be returned unchanged,
     * which is the behavior of the default implementation of this method.
     *
     * @param response The response to prepare. This response will not be
     *     modified.
     *
     * @return A new response prepared to adapt to the backend service for which
     *     this method is implemented.
     */
    fun prepareResponse(response: HttpResponse): HttpResponse {
        return response
    }

    /**
     * Closes any streams and releases any resources used by this backend. This
     * is called when [HttpClient.close()] is called. For example, an
     * implementation might use this to shut down a thread pool that will no
     * longer be needed.
     *
     * The default implementation does nothing.
     */
    override fun close() {}
}
