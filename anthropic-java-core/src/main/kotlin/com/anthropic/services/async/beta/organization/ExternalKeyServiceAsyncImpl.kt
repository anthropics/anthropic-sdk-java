// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

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
import com.anthropic.models.beta.organization.externalkeys.BetaExternalKey
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPageAsync
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPageResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyRetrieveParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateResponse
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ExternalKeyServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    ExternalKeyServiceAsync {

    private val withRawResponse: ExternalKeyServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ExternalKeyServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalKeyServiceAsync =
        ExternalKeyServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: ExternalKeyCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalKey> =
        // post /v1/organizations/external_keys?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: ExternalKeyRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalKey> =
        // get /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: ExternalKeyUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaExternalKey> =
        // post /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: ExternalKeyListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExternalKeyListPageAsync> =
        // get /v1/organizations/external_keys?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: ExternalKeyDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExternalKeyDeleteResponse> =
        // delete /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun validate(
        params: ExternalKeyValidateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<ExternalKeyValidateResponse> =
        // post /v1/organizations/external_keys/{external_key_id}/validate?beta=true
        withRawResponse().validate(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ExternalKeyServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExternalKeyServiceAsync.WithRawResponse =
            ExternalKeyServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun create(
            params: ExternalKeyCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys")
                    .putQueryParam("beta", "true")
                    .body(json(clientOptions.jsonMapper, params._body()))
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

        private val retrieveHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ExternalKeyRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalKeyId", params.externalKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys", params._pathParam(0))
                    .putQueryParam("beta", "true")
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

        private val updateHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun update(
            params: ExternalKeyUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaExternalKey>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalKeyId", params.externalKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys", params._pathParam(0))
                    .putQueryParam("beta", "true")
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

        private val listHandler: Handler<ExternalKeyListPageResponse> =
            jsonHandler<ExternalKeyListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ExternalKeyListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExternalKeyListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys")
                    .putQueryParam("beta", "true")
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
                                ExternalKeyListPageAsync.builder()
                                    .service(ExternalKeyServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<ExternalKeyDeleteResponse> =
            jsonHandler<ExternalKeyDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: ExternalKeyDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExternalKeyDeleteResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalKeyId", params.externalKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys", params._pathParam(0))
                    .putQueryParam("beta", "true")
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

        private val validateHandler: Handler<ExternalKeyValidateResponse> =
            jsonHandler<ExternalKeyValidateResponse>(clientOptions.jsonMapper)

        override fun validate(
            params: ExternalKeyValidateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<ExternalKeyValidateResponse>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("externalKeyId", params.externalKeyId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "organizations",
                        "external_keys",
                        params._pathParam(0),
                        "validate",
                    )
                    .putQueryParam("beta", "true")
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { validateHandler.handle(it) }
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
