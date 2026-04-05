// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

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
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillCreateResponse
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillDeleteResponse
import com.anthropic.models.beta.skills.SkillListPageAsync
import com.anthropic.models.beta.skills.SkillListPageResponse
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.models.beta.skills.SkillRetrieveResponse
import com.anthropic.services.async.beta.skills.VersionServiceAsync
import com.anthropic.services.async.beta.skills.VersionServiceAsyncImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SkillServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SkillServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "skills-2025-10-02").build()
    }

    private val withRawResponse: SkillServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val versions: VersionServiceAsync by lazy { VersionServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): SkillServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillServiceAsync =
        SkillServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun versions(): VersionServiceAsync = versions

    override suspend fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions,
    ): SkillCreateResponse =
        // post /v1/skills?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override suspend fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions,
    ): SkillRetrieveResponse =
        // get /v1/skills/{skill_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override suspend fun list(
        params: SkillListParams,
        requestOptions: RequestOptions,
    ): SkillListPageAsync =
        // get /v1/skills?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override suspend fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions,
    ): SkillDeleteResponse =
        // delete /v1/skills/{skill_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SkillServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val versions: VersionServiceAsync.WithRawResponse by lazy {
            VersionServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SkillServiceAsync.WithRawResponse =
            SkillServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun versions(): VersionServiceAsync.WithRawResponse = versions

        private val createHandler: Handler<SkillCreateResponse> =
            jsonHandler<SkillCreateResponse>(clientOptions.jsonMapper)

        override suspend fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillCreateResponse> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills")
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

        private val retrieveHandler: Handler<SkillRetrieveResponse> =
            jsonHandler<SkillRetrieveResponse>(clientOptions.jsonMapper)

        override suspend fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillRetrieveResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0))
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

        private val listHandler: Handler<SkillListPageResponse> =
            jsonHandler<SkillListPageResponse>(clientOptions.jsonMapper)

        override suspend fun list(
            params: SkillListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillListPageAsync> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills")
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
                                SkillListPageAsync.builder()
                                    .service(SkillServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamExecutor())
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
        }

        private val deleteHandler: Handler<SkillDeleteResponse> =
            jsonHandler<SkillDeleteResponse>(clientOptions.jsonMapper)

        override suspend fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillDeleteResponse> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0))
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
