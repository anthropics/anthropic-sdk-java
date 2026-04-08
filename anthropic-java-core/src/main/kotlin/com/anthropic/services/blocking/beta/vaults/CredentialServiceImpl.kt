// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.vaults

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
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsCredential
import com.anthropic.models.beta.vaults.credentials.BetaManagedAgentsDeletedCredential
import com.anthropic.models.beta.vaults.credentials.CredentialArchiveParams
import com.anthropic.models.beta.vaults.credentials.CredentialCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialDeleteParams
import com.anthropic.models.beta.vaults.credentials.CredentialListPage
import com.anthropic.models.beta.vaults.credentials.CredentialListPageResponse
import com.anthropic.models.beta.vaults.credentials.CredentialListParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.beta.vaults.credentials.CredentialUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class CredentialServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    CredentialService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "managed-agents-2026-04-01").build()
    }

    private val withRawResponse: CredentialService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): CredentialService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): CredentialService =
        CredentialServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: CredentialCreateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsCredential =
        // post /v1/vaults/{vault_id}/credentials?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: CredentialRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsCredential =
        // get /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: CredentialUpdateParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsCredential =
        // post /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: CredentialListParams,
        requestOptions: RequestOptions,
    ): CredentialListPage =
        // get /v1/vaults/{vault_id}/credentials?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: CredentialDeleteParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsDeletedCredential =
        // delete /v1/vaults/{vault_id}/credentials/{credential_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun archive(
        params: CredentialArchiveParams,
        requestOptions: RequestOptions,
    ): BetaManagedAgentsCredential =
        // post /v1/vaults/{vault_id}/credentials/{credential_id}/archive?beta=true
        withRawResponse().archive(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        CredentialService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CredentialService.WithRawResponse =
            CredentialServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun create(
            params: CredentialCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsCredential> {
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

        private val retrieveHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun retrieve(
            params: CredentialRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsCredential> {
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

        private val updateHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun update(
            params: CredentialUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsCredential> {
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

        private val listHandler: Handler<CredentialListPageResponse> =
            jsonHandler<CredentialListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: CredentialListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CredentialListPage> {
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
                        CredentialListPage.builder()
                            .service(CredentialServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<BetaManagedAgentsDeletedCredential> =
            jsonHandler<BetaManagedAgentsDeletedCredential>(clientOptions.jsonMapper)

        override fun delete(
            params: CredentialDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsDeletedCredential> {
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

        private val archiveHandler: Handler<BetaManagedAgentsCredential> =
            jsonHandler<BetaManagedAgentsCredential>(clientOptions.jsonMapper)

        override fun archive(
            params: CredentialArchiveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaManagedAgentsCredential> {
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
