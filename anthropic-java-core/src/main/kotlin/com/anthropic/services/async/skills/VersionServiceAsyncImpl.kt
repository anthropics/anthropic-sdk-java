// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.skills

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
import com.anthropic.models.skills.versions.DeletedSkillVersion
import com.anthropic.models.skills.versions.SkillVersion
import com.anthropic.models.skills.versions.VersionCreateParams
import com.anthropic.models.skills.versions.VersionDeleteParams
import com.anthropic.models.skills.versions.VersionListPageAsync
import com.anthropic.models.skills.versions.VersionListPageResponse
import com.anthropic.models.skills.versions.VersionListParams
import com.anthropic.models.skills.versions.VersionRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class VersionServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    VersionServiceAsync {

    private val withRawResponse: VersionServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): VersionServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionServiceAsync =
        VersionServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SkillVersion> =
        // post /v1/skills/{skill_id}/versions
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<SkillVersion> =
        // get /v1/skills/{skill_id}/versions/{version}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: VersionListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<VersionListPageAsync> =
        // get /v1/skills/{skill_id}/versions
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedSkillVersion> =
        // delete /v1/skills/{skill_id}/versions/{version}
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

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

        private val createHandler: Handler<SkillVersion> =
            jsonHandler<SkillVersion>(clientOptions.jsonMapper)

        override fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SkillVersion>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0), "versions")
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

        private val retrieveHandler: Handler<SkillVersion> =
            jsonHandler<SkillVersion>(clientOptions.jsonMapper)

        override fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SkillVersion>> {
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

        private val listHandler: Handler<VersionListPageResponse> =
            jsonHandler<VersionListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: VersionListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("skillId", params.skillId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "skills", params._pathParam(0), "versions")
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
                                VersionListPageAsync.builder()
                                    .service(VersionServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<DeletedSkillVersion> =
            jsonHandler<DeletedSkillVersion>(clientOptions.jsonMapper)

        override fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedSkillVersion>> {
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
