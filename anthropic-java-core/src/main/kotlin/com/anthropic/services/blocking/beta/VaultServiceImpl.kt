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
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.vaults.BetaManagedAgentsDeletedVault
import com.anthropic.models.beta.vaults.BetaManagedAgentsVault
import com.anthropic.models.beta.vaults.VaultArchiveParams
import com.anthropic.models.beta.vaults.VaultCreateParams
import com.anthropic.models.beta.vaults.VaultDeleteParams
import com.anthropic.models.beta.vaults.VaultListPage
import com.anthropic.models.beta.vaults.VaultListPageResponse
import com.anthropic.models.beta.vaults.VaultListParams
import com.anthropic.models.beta.vaults.VaultRetrieveParams
import com.anthropic.models.beta.vaults.VaultUpdateParams
import com.anthropic.services.blocking.beta.vaults.CredentialService
import com.anthropic.services.blocking.beta.vaults.CredentialServiceImpl
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class VaultServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    VaultService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: VaultService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    private val credentials: CredentialService by lazy { CredentialServiceImpl(clientOptions) }

    override fun withRawResponse(): VaultService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): VaultService =
        VaultServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun credentials(): CredentialService = credentials

    override fun create(
        params: VaultCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsVault =
        // post /v1/vaults?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: VaultRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsVault =
        // get /v1/vaults/{vault_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: VaultUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsVault =
        // post /v1/vaults/{vault_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: VaultListParams, requestOptions: RequestOptions): VaultListPage =
        // get /v1/vaults?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: VaultDeleteParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeletedVault =
        // delete /v1/vaults/{vault_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun archive(
        params: VaultArchiveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsVault =
        // post /v1/vaults/{vault_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        VaultService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        private val credentials: CredentialService.WithRawResponse by lazy {
            CredentialServiceImpl.WithRawResponseImpl(clientOptions)
        }

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VaultService.WithRawResponse =
            VaultServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        override fun credentials(): CredentialService.WithRawResponse = credentials

        private val createHandler: Handler<BetaManagedAgentsVault> =
            jsonHandler<BetaManagedAgentsVault>(clientOptions.jsonMapper)

        override fun create(
            params: VaultCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
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

        private val retrieveHandler: Handler<BetaManagedAgentsVault> =
            jsonHandler<BetaManagedAgentsVault>(clientOptions.jsonMapper)

        override fun retrieve(
            params: VaultRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0))
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

        private val updateHandler: Handler<BetaManagedAgentsVault> =
            jsonHandler<BetaManagedAgentsVault>(clientOptions.jsonMapper)

        override fun update(
            params: VaultUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
                response
                    .use { updateHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val listHandler: Handler<VaultListPageResponse> =
            jsonHandler<VaultListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: VaultListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VaultListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults")
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
                        VaultListPage.builder()
                            .service(VaultServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedVault> =
            jsonHandler<BetaManagedAgentsDeletedVault>(clientOptions.jsonMapper)

        override fun delete(
            params: VaultDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedVault> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0))
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

        private val archiveHandler: Handler<BetaManagedAgentsVault> =
            jsonHandler<BetaManagedAgentsVault>(clientOptions.jsonMapper)

        override fun archive(
            params: VaultArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsVault> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("vaultId", params.vaultId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "vaults", params._pathParam(0), "archive")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
