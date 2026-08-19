// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorBodyHandler
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.multipartFormData
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.skills.DeletedSkill
import com.anthropic.models.skills.Skill
import com.anthropic.models.skills.SkillCreateParams
import com.anthropic.models.skills.SkillDeleteParams
import com.anthropic.models.skills.SkillListPageAsync
import com.anthropic.models.skills.SkillListPageResponse
import com.anthropic.models.skills.SkillListParams
import com.anthropic.models.skills.SkillRetrieveParams
import com.anthropic.services.async.skills.VersionServiceAsync
import com.anthropic.services.async.skills.VersionServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class SkillServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    SkillServiceAsync {

    private val withRawResponse: SkillServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val versions: VersionServiceAsync by lazy { VersionServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): SkillServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): SkillServiceAsync =
        SkillServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun versions(): VersionServiceAsync = versions

    override fun create(
        params: SkillCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Skill> =
        // post /v1/skills
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: SkillRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<Skill> =
        // get /v1/skills/{skill_id}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: SkillListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SkillListPageAsync> =
        // get /v1/skills
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: SkillDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedSkill> =
        // delete /v1/skills/{skill_id}
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

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

        private val createHandler: Handler<Skill> = jsonHandler<Skill>(clientOptions.jsonMapper)

        override fun create(
            params: SkillCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Skill>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills")
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { createHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val retrieveHandler: Handler<Skill> = jsonHandler<Skill>(clientOptions.jsonMapper)

        override fun retrieve(
            params: SkillRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<Skill>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { retrieveHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<SkillListPageResponse> =
            jsonHandler<SkillListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: SkillListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SkillListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<DeletedSkill> =
            jsonHandler<DeletedSkill>(clientOptions.jsonMapper)

        override fun delete(
            params: SkillDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedSkill>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
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
}
