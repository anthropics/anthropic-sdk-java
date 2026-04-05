// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.skills

import kotlinx.kmp.util.core.streamExecutor
import kotlinx.kmp.util.core.ClientOptions
import kotlinx.kmp.util.core.RequestOptions
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.handlers.errorBodyHandler
import kotlinx.kmp.util.core.handlers.errorHandler
import kotlinx.kmp.util.core.handlers.jsonHandler
import kotlinx.kmp.util.core.http.Headers
import kotlinx.kmp.util.core.http.HttpMethod
import kotlinx.kmp.util.core.http.HttpRequest
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.http.HttpResponse.Handler
import kotlinx.kmp.util.core.http.HttpResponseFor
import kotlinx.kmp.util.core.http.json
import kotlinx.kmp.util.core.http.multipartFormData
import kotlinx.kmp.util.core.http.parseable
import kotlinx.kmp.util.core.prepareSuspend
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionCreateResponse
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionDeleteResponse
import com.anthropic.models.beta.skills.versions.VersionListPageAsync
import com.anthropic.models.beta.skills.versions.VersionListPageResponse
import com.anthropic.models.beta.skills.versions.VersionListParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class VersionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    VersionServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "skills-2025-10-02").build()
    }

    private val withRawResponse: VersionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): VersionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionServiceAsync =
        VersionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override suspend fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions,
    ): VersionCreateResponse =
        // post /v1/skills/{skill_id}/versions?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions,
    ): VersionRetrieveResponse =
        // get /v1/skills/{skill_id}/versions/{version}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: VersionListParams,
        requestOptions: RequestOptions,
    ): VersionListPageAsync =
        // get /v1/skills/{skill_id}/versions?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions,
    ): VersionDeleteResponse =
        // delete /v1/skills/{skill_id}/versions/{version}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        VersionServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VersionServiceAsync.WithRawResponse =
            VersionServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<VersionCreateResponse> =
            jsonHandler<VersionCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionCreateResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0), "versions")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val retrieveHandler: Handler<VersionRetrieveResponse> =
            jsonHandler<VersionRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionRetrieveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("version", params.version().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "skills",
                        params._pathParam(0),
                        "versions",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }

        private val listHandler: Handler<VersionListPageResponse> =
            jsonHandler<VersionListPageResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: VersionListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionListPageAsync> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0), "versions")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { listHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                            .let {
                                VersionListPageAsync.builder()
                                    .service(VersionServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamExecutor())
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
        }

        private val deleteHandler: Handler<VersionDeleteResponse> =
            jsonHandler<VersionDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionDeleteResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("version", params.version().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "skills",
                        params._pathParam(0),
                        "versions",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareSuspend(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.executeSuspend(request, requestOptions)
            return errorHandler.handle(response).parseable {
                        response
                            .use { deleteHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
        }
    }
}
