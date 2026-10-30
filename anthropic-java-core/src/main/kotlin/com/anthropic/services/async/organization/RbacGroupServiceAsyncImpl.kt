package com.anthropic.services.async.organization

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.organization.rbacgroups.DeletedRbacGroup
import com.anthropic.models.organization.rbacgroups.RbacGroup
import com.anthropic.models.organization.rbacgroups.RbacGroupCreateParams
import com.anthropic.models.organization.rbacgroups.RbacGroupDeleteParams
import com.anthropic.models.organization.rbacgroups.RbacGroupListPageAsync
import com.anthropic.models.organization.rbacgroups.RbacGroupListPageResponse
import com.anthropic.models.organization.rbacgroups.RbacGroupListParams
import com.anthropic.models.organization.rbacgroups.RbacGroupRetrieveParams
import com.anthropic.models.organization.rbacgroups.RbacGroupUpdateParams
import com.anthropic.services.async.organization.rbacgroups.MemberServiceAsync
import com.anthropic.services.async.organization.rbacgroups.MemberServiceAsyncImpl
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class RbacGroupServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    RbacGroupServiceAsync {

    private val withRawResponse: RbacGroupServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val members: MemberServiceAsync by lazy { MemberServiceAsyncImpl(clientOptions) }

    override fun withRawResponse(): RbacGroupServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): RbacGroupServiceAsync =
        RbacGroupServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun members(): MemberServiceAsync = members

    override fun create(
        params: RbacGroupCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroup> =
        // post /v1/organizations/rbac_groups
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: RbacGroupRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroup> =
        // get /v1/organizations/rbac_groups/{rbac_group_id}
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: RbacGroupUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroup> =
        // post /v1/organizations/rbac_groups/{rbac_group_id}
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: RbacGroupListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<RbacGroupListPageAsync> =
        // get /v1/organizations/rbac_groups
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: RbacGroupDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<DeletedRbacGroup> =
        // delete /v1/organizations/rbac_groups/{rbac_group_id}
        withRawResponse().delete(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        RbacGroupServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val members: MemberServiceAsync.WithRawResponse by lazy {
            MemberServiceAsyncImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): RbacGroupServiceAsync.WithRawResponse =
            RbacGroupServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun members(): MemberServiceAsync.WithRawResponse = members

        private val createHandler: Handler<RbacGroup> =
            jsonHandler<RbacGroup>(clientOptions.jsonMapper)

        override fun create(
            params: RbacGroupCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroup>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { createHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .also {
                                    client.recordCreatedId(
                                        "anthropic.organization.rbac_group.id",
                                        it._id(),
                                    )
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<RbacGroup> =
            jsonHandler<RbacGroup>(clientOptions.jsonMapper)

        override fun retrieve(
            params: RbacGroupRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroup>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { retrieveHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val updateHandler: Handler<RbacGroup> =
            jsonHandler<RbacGroup>(clientOptions.jsonMapper)

        override fun update(
            params: RbacGroupUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroup>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { updateHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }

        private val listHandler: Handler<RbacGroupListPageResponse> =
            jsonHandler<RbacGroupListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: RbacGroupListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<RbacGroupListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { listHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                                .let {
                                    RbacGroupListPageAsync.builder()
                                        .service(RbacGroupServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<DeletedRbacGroup> =
            jsonHandler<DeletedRbacGroup>(clientOptions.jsonMapper)

        override fun delete(
            params: RbacGroupDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<DeletedRbacGroup>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("rbacGroupId", params.rbacGroupId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "rbac_groups")
                    .addPathParam("rbacGroupId", params._pathParam(0))
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { deleteHandler.handle(it) }
                                .also {
                                    if (requestOptions.responseValidation().get()) {
                                        it.validate()
                                    }
                                }
                        }
                    }
            }
        }
    }
}
