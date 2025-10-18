// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.multipartFormData
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.skills.SkillCreateParams
import com.anthropic.models.beta.skills.SkillCreateResponse
import com.anthropic.models.beta.skills.SkillDeleteParams
import com.anthropic.models.beta.skills.SkillDeleteResponse
import com.anthropic.models.beta.skills.SkillListPage
import com.anthropic.models.beta.skills.SkillListPageResponse
import com.anthropic.models.beta.skills.SkillListParams
import com.anthropic.models.beta.skills.SkillRetrieveParams
import com.anthropic.models.beta.skills.SkillRetrieveResponse
import com.anthropic.services.blocking.beta.skills.VersionService
import com.anthropic.services.blocking.beta.skills.VersionServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SkillServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    SkillService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "skills-2025-10-02").build()
    }

    private val withRawResponse: SkillService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val versions: VersionService by lazy { VersionServiceImpl(clientOptions) }

    override fun withRawResponse(): SkillService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillService =
        SkillServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun versions(): VersionService = versions

    override fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions,
    ): SkillCreateResponse =
        // post /v1/skills?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions,
    ): SkillRetrieveResponse =
        // get /v1/skills/{skill_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun list(params: SkillListParams, requestOptions: RequestOptions): SkillListPage =
        // get /v1/skills?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions,
    ): SkillDeleteResponse =
        // delete /v1/skills/{skill_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        SkillService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val versions: VersionService.WithRawResponse by lazy {
            VersionServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SkillService.WithRawResponse =
            SkillServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun versions(): VersionService.WithRawResponse = versions

        private val createHandler: Handler<SkillCreateResponse> =
            jsonHandler<SkillCreateResponse>(clientOptions.jsonMapper)

        override fun create(
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        override fun retrieve(
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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

        override fun list(
            params: SkillListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SkillListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        SkillListPage.builder()
                            .service(SkillServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<SkillDeleteResponse> =
            jsonHandler<SkillDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
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
