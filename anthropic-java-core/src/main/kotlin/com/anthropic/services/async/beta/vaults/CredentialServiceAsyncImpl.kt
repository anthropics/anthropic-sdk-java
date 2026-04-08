// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.vaults

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
import com.anthropic.core.http.parseable
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsCredential
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsDeletedCredential
import com.anthropic.models.beta.vaults.credentials.CredentialArchiveParams
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialDeleteParams
import com.anthropic.models.beta.vaults.credentials.CredentialListPageAsync
import com.anthropic.models.beta.vaults.credentials.CredentialListPageResponse
import com.anthropic.models.beta.vaults.credentials.CredentialListParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.beta.vaults.credentials.CredentialUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CredentialServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    CredentialServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: CredentialServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CredentialServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialServiceAsync =
        CredentialServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        // post /v1/vaults/{vault_id}/credentials?beta=true
        withRawResponse().create(params, requestOptions).thenApply { it.parse() }

    override fun retrieve(
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        // get /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenApply { it.parse() }

    override fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        // post /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().update(params, requestOptions).thenApply { it.parse() }

    override fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<CredentialListPageAsync> =
        // get /v1/vaults/{vault_id}/credentials?beta=true
        withRawResponse().list(params, requestOptions).thenApply { it.parse() }

    override fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsDeletedCredential> =
        // delete /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenApply { it.parse() }

    override fun archive(
        params: CredentialArchiveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaManagedAgentsCredential> =
        // post /v1/vaults/{vault_id}/credentials/{credential_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).thenApply { it.parse() }

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CredentialServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialServiceAsync.WithRawResponse =
            CredentialServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0), "credentials")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val retrieveHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("credentialId", params.credentialId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "vaults",
                        params._pathParam(0),
                        "credentials",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val updateHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("credentialId", params.credentialId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "vaults",
                        params._pathParam(0),
                        "credentials",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val listHandler: Handler<CredentialListPageResponse> =
            jsonHandler<CredentialListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CredentialListPageAsync>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0), "credentials")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                                CredentialListPageAsync.builder()
                                    .service(CredentialServiceAsyncImpl(clientOptions))
                                    .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                    .params(params)
                                    .response(it)
                                    .build()
                            }
                    }
                }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedCredential> =
            jsonHandler<BetaManagedAgentsDeletedCredential>(clientOptions.jsonMapper)

        override fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsDeletedCredential>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("credentialId", params.credentialId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "vaults",
                        params._pathParam(0),
                        "credentials",
                        params._pathParam(1),
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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

        private val archiveHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun archive(
            params: CredentialArchiveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaManagedAgentsCredential>> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("credentialId", params.credentialId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments(
                        "v1",
                        "vaults",
                        params._pathParam(0),
                        "credentials",
                        params._pathParam(1),
                        "archive",
                    )
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return request
                .thenComposeAsync { clientOptions.httpClient.executeAsync(it, requestOptions) }
                .thenApply { response ->
                    errorHandler.handle(response).parseable {
                        response
                            .use { archiveHandler.handle(it) }
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
