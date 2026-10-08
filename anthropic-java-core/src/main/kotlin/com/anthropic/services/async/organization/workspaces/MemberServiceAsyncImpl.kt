package com.anthropic.services.async.organization.workspaces

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
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.organization.workspaces.WorkspaceMember
import com.anthropic.models.organization.workspaces.members.MemberAddParams
import com.anthropic.models.organization.workspaces.members.MemberListPageAsync
import com.anthropic.models.organization.workspaces.members.MemberListPageResponse
import com.anthropic.models.organization.workspaces.members.MemberListParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveParams
import com.anthropic.models.organization.workspaces.members.MemberRemoveResponse
import com.anthropic.models.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.organization.workspaces.members.MemberUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class MemberServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    MemberServiceAsync {

    private val withRawResponse: MemberServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): MemberServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): MemberServiceAsync =
        MemberServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun retrieve(
        params: MemberRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceMember> =
        // get /v1/organizations/workspaces/{workspace_id}/members/{user_id}
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: MemberUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceMember> =
        // post /v1/organizations/workspaces/{workspace_id}/members/{user_id}
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: MemberListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemberListPageAsync> =
        // get /v1/organizations/workspaces/{workspace_id}/members
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun add(
        params: MemberAddParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WorkspaceMember> =
        // post /v1/organizations/workspaces/{workspace_id}/members
        withRawResponse().add(params, requestOptions).thenApply { it.parse() }

    override fun remove(
        params: MemberRemoveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<MemberRemoveResponse> =
        // delete /v1/organizations/workspaces/{workspace_id}/members/{user_id}
        withRawResponse().remove(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        MemberServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): MemberServiceAsync.WithRawResponse =
            MemberServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val retrieveHandler: Handler<WorkspaceMember> =
            jsonHandler<WorkspaceMember>(clientOptions.jsonMapper)

        override fun retrieve(
            params: MemberRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userId", params.userId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("members")
                    .addPathParam("userId", params._pathParam(1))
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

        private val updateHandler: Handler<WorkspaceMember> =
            jsonHandler<WorkspaceMember>(clientOptions.jsonMapper)

        override fun update(
            params: MemberUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userId", params.userId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("members")
                    .addPathParam("userId", params._pathParam(1))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { updateHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val listHandler: Handler<MemberListPageResponse> =
            jsonHandler<MemberListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: MemberListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemberListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("members")
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
                                MemberListPageAsync.builder()
                                    .service(MemberServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val addHandler: Handler<WorkspaceMember> =
            jsonHandler<WorkspaceMember>(clientOptions.jsonMapper)

        override fun add(
            params: MemberAddParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WorkspaceMember>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("workspaceId", params.workspaceId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("members")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { addHandler.handle(it) }
                            .also {
                                if (requestOptions.responseValidation!!) {
                                    it.validate()
                                }
                            }
                    }
                }
        }

        private val removeHandler: Handler<MemberRemoveResponse> =
            jsonHandler<MemberRemoveResponse>(clientOptions.jsonMapper)

        override fun remove(
            params: MemberRemoveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<MemberRemoveResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("userId", params.userId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "workspaces")
                    .addPathParam("workspaceId", params._pathParam(0))
                    .addPathSegments("members")
                    .addPathParam("userId", params._pathParam(1))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { removeHandler.handle(it) }
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
