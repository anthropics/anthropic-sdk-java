package com.anthropic.backends

import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse

/**
 * An interface that represents adaptations required to support different
 * backend AI services. Different backends may require different credentials,
 * request contents, request signatures, response handler and endpoint URLs.
 * Implementations of this interface may define the information and handling
 * appropriate for each backend service.
 */
interface BackendAdapter {
    /**
     * Gets the backend service's base URL (endpoint).
     *
     * @return The backend base URL, the endpoint identifying the service that
     *     can be accessed by this adapter..
     */
    fun baseUrl(): String

    /**
     * Prepares the request for use by the backend service. This allows changes
     * to be made to the request to adapt it for backends other than the default
     * Anthropic backend.
     *
     * If no changes are required, the given request can be returned unchanged,
     * which is the behavior of the default implementation of this method.
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
     * Signs the given request and returns the new signed request.
     *
     * In addition to adding signature headers, the request may be modified in
     * other ways to adapt it to the requirements of the backend service. For
     * example, there may be changes to the URL path segments, non-signature
     * headers, or other elements of the request.
     *
     * Where a backend does not require request signatures or other changes to
     * the request, the given [HttpRequest] may be returned unchanged. This is
     * the behavior of the default implementation.
     *
     * @param request The request to be signed. This will not be modified; if
     *     any changes are required, a modified copy will be returned.
     *
     * @return The signed request. This may be the same instance as the given
     *     request if a signature is not required, or it may be a new instance
     *     of [HttpRequest] if a signature *is* required.
     */
    fun signRequest(request: HttpRequest): HttpRequest {
        return request
    }

    /**
     * Prepares the response returned by the backend service. This allows
     * changes to be made to the response to adapt it for backend services other
     * than the default Anthropic backend service. The adaptations make the
     * response appear to come from that Anthropic backend.
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
}