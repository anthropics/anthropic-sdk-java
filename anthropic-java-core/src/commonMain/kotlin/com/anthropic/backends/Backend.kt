package com.anthropic.backends

import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse

/**
 * An interface that represents adaptations required to support different backend AI services.
 * Different backends may require different credentials, request headers, request authorization,
 * request signatures, response handlers and endpoint URLs. Implementations of this interface may
 * define the information and handling appropriate for each backend service.
 *
 * When a backend is no longer needed, such as when the client it is associated with is closed and
 * discarded, [close] must be called on the backend to ensure any resources it is using are
 * released.
 */
interface Backend {
    /**
     * Gets the backend service's base URL identifying the network address of the service that will
     * be accessed by this backend.
     */
    fun baseUrl(): String

    /**
     * Prepares the request for use by the backend service. Changes may be made to the request to
     * adapt it for backends other than the default Anthropic backend.
     *
     * Changes may be made to the headers, path segments, query parameters, and body content to suit
     * the requirements of the backend where they differ from the default Anthropic backend. All
     * changes that may affect the resolution of the full URL for the request _must_ be made by this
     * method.
     *
     * Changes specifically related to request authorization or signing should be performed by the
     * implementation of [authorizeRequest].
     *
     * If no changes are required, the given request can be returned unchanged. This is the behavior
     * of the default implementation.
     */
    fun prepareRequest(request: HttpRequest): HttpRequest {
        return request
    }

    /**
     * Authorizes the given request and returns the new authorized request. The URL of the request
     * will be resolved fully _before_ this method is called.
     *
     * Changes will typically include the addition of authorization headers or signature headers.
     * Other changes not directly related to authorization should be performed by [prepareRequest].
     *
     * Where a backend does not require request authorization or other related changes to the
     * request, the given [HttpRequest] may be returned unchanged. This is the behavior of the
     * default implementation.
     */
    fun authorizeRequest(request: HttpRequest): HttpRequest {
        return request
    }

    /**
     * Prepares the response returned by the backend service. This allows changes to be made to the
     * response to adapt it for backend services other than the default Anthropic backend service.
     * The adaptations make the response appear to come from that default Anthropic backend.
     *
     * If no changes are required, the given response can be returned unchanged. This is the
     * behavior of the default implementation.
     */
    fun prepareResponse(response: HttpResponse): HttpResponse {
        return response
    }

    /**
     * Closes any streams and releases any resources used by this backend. For example, an
     * implementation might use this to shut down a thread pool that will no longer be needed.
     */
    fun close()
}
