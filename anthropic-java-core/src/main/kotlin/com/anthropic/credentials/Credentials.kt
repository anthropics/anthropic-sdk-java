package com.anthropic.credentials

import com.anthropic.core.http.HttpRequest

/**
 * An interface that represents credentials required for authentication and
 * authorization of access to a service. Different services may require
 * different credentials, so implementations of this interface may define that
 * information appropriately for each service.
 *
 * The base URL and the authentication and authorization headers may also be
 * derived from the credentials.
 */
interface Credentials {
    /**
     * Gets the service base URL (endpoint) derived from these credentials.
     *
     * @return The service base URL, the endpoint identifying the service that
     *     can be accessed with these credentials.
     */
    fun baseUrl(): String

    /**
     * Prepares the request for use by the service. This allows changes to be
     * made to the request to support services other than the default Anthropic
     * service.
     *
     * If no changes are required, the given request can be returned unchanged,
     * which is the behavior of the default implementation of this method.
     *
     * @param request The request to prepare. This request will not be modified.
     *
     * @return A new request prepared for use by the service for which this
     *     method is implemented.
     */
    fun prepare(request: HttpRequest): HttpRequest  {
        return request
    }

    /**
     * Signs the given request and returns the new signed request.
     *
     * In addition to adding signature headers, the request may be modified in
     * other ways to make it conform to the requirements of the service that
     * uses these credentials. For example, there may be changes to the URL
     * path segments, non-signature headers, or other elements of the request.
     *
     * Where a service does not require request signatures or other changes to
     * the request, the given [HttpRequest] may be returned unchanged. This is
     * the behavior of the default implementation.
     *
     * @param request The request to be signed.
     *
     * @return The signed request. This may be the same instance as the given
     *     request if a signature is not required, or it may be a new instance
     *     of [HttpRequest] if a signature *is* required.
     */
    fun sign(request: HttpRequest): HttpRequest {
        return request
    }
}