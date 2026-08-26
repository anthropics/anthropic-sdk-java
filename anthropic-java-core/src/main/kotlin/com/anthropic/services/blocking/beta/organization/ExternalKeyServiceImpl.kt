// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.externalkeys.BetaExternalKey
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyDeleteResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPage
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListPageResponse
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyListParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyRetrieveParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyValidateResponse
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class ExternalKeyServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    ExternalKeyService {

    private val withRawResponse: ExternalKeyService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): ExternalKeyService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): ExternalKeyService =
        ExternalKeyServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: ExternalKeyCreateParams,
        requestOptions: RequestOptions,
    ): BetaExternalKey =
        // post /v1/organizations/external_keys?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: ExternalKeyRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaExternalKey =
        // get /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: ExternalKeyUpdateParams,
        requestOptions: RequestOptions,
    ): BetaExternalKey =
        // post /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(
        params: ExternalKeyListParams,
        requestOptions: RequestOptions,
    ): ExternalKeyListPage =
        // get /v1/organizations/external_keys?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: ExternalKeyDeleteParams,
        requestOptions: RequestOptions,
    ): ExternalKeyDeleteResponse =
        // delete /v1/organizations/external_keys/{external_key_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun validate(
        params: ExternalKeyValidateParams,
        requestOptions: RequestOptions,
    ): ExternalKeyValidateResponse =
        // post /v1/organizations/external_keys/{external_key_id}/validate?beta=true
        withRawResponse().validate(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        ExternalKeyService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): ExternalKeyService.WithRawResponse =
            ExternalKeyServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun create(
            params: ExternalKeyCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalKey> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys")
                    .putQueryParam("beta", "true")
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

        private val retrieveHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun retrieve(
            params: ExternalKeyRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalKey> {
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

        private val updateHandler: Handler<BetaExternalKey> =
            jsonHandler<BetaExternalKey>(clientOptions.jsonMapper)

        override fun update(
            params: ExternalKeyUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaExternalKey> {
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

        private val listHandler: Handler<ExternalKeyListPageResponse> =
            jsonHandler<ExternalKeyListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: ExternalKeyListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalKeyListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "external_keys")
                    .putQueryParam("beta", "true")
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
                        ExternalKeyListPage.builder()
                            .service(ExternalKeyServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<ExternalKeyDeleteResponse> =
            jsonHandler<ExternalKeyDeleteResponse>(clientOptions.jsonMapper)

        override fun delete(
            params: ExternalKeyDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalKeyDeleteResponse> {
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

        private val validateHandler: Handler<ExternalKeyValidateResponse> =
            jsonHandler<ExternalKeyValidateResponse>(clientOptions.jsonMapper)

        override fun validate(
            params: ExternalKeyValidateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<ExternalKeyValidateResponse> {
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
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return errorHandler.handle(response).parseable {
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
