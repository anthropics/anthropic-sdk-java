package com.anthropic.services.blocking.beta.organization

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
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.prepare
import com.anthropic.models.beta.organization.webhooks.BetaDeletedOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaNewOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpointSigningSecret
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookListPage
import com.anthropic.models.beta.organization.webhooks.WebhookListPageResponse
import com.anthropic.models.beta.organization.webhooks.WebhookListParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WebhookServiceImpl internal constructor(private val clientOptions: ClientOptions) :
    WebhookService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "webhooks-2026-10-07").build()
    }

    private val withRawResponse: WebhookService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WebhookService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService =
        WebhookServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: WebhookCreateParams,
        requestOptions: RequestOptions,
    ): BetaNewOrganizationWebhookEndpoint =
        // post /v1/organizations/webhooks?beta=true
        withRawResponse().create(params, requestOptions).parse()

    override fun retrieve(
        params: WebhookRetrieveParams,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpoint =
        // get /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).parse()

    override fun update(
        params: WebhookUpdateParams,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpoint =
        // post /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().update(params, requestOptions).parse()

    override fun list(params: WebhookListParams, requestOptions: RequestOptions): WebhookListPage =
        // get /v1/organizations/webhooks?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(
        params: WebhookDeleteParams,
        requestOptions: RequestOptions,
    ): BetaDeletedOrganizationWebhookEndpoint =
        // delete /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpointSigningSecret =
        // post /v1/organizations/webhooks/{webhook_endpoint_id}/regenerate_signing_secret?beta=true
        withRawResponse().regenerateSigningSecret(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WebhookService.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WebhookService.WithRawResponse =
            WebhookServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaNewOrganizationWebhookEndpoint> =
            jsonHandler<BetaNewOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun create(
            params: WebhookCreateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaNewOrganizationWebhookEndpoint> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { createHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation!!) {
                                it.validate()
                            }
                        }
                        .also {
                            client.recordCreatedId("anthropic.organization.webhook.id", it._id())
                        }
                }
            }
        }

        private val retrieveHandler: Handler<BetaOrganizationWebhookEndpoint> =
            jsonHandler<BetaOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun retrieve(
            params: WebhookRetrieveParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("webhookEndpointId", params.webhookEndpointId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .addPathParam("webhookEndpointId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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

        private val updateHandler: Handler<BetaOrganizationWebhookEndpoint> =
            jsonHandler<BetaOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun update(
            params: WebhookUpdateParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("webhookEndpointId", params.webhookEndpointId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .addPathParam("webhookEndpointId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(json(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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

        private val listHandler: Handler<WebhookListPageResponse> =
            jsonHandler<WebhookListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WebhookListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<WebhookListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { listHandler.handle(it) }
                        .also {
                            if (requestOptions.responseValidation!!) {
                                it.validate()
                            }
                        }
                        .let {
                            WebhookListPage.builder()
                                .service(WebhookServiceImpl(clientOptions))
                                .params(params)
                                .response(it)
                                .build()
                        }
                }
            }
        }

        private val deleteHandler: Handler<BetaDeletedOrganizationWebhookEndpoint> =
            jsonHandler<BetaDeletedOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun delete(
            params: WebhookDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("webhookEndpointId", params.webhookEndpointId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .addPathParam("webhookEndpointId", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
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

        private val regenerateSigningSecretHandler:
            Handler<BetaOrganizationWebhookEndpointSigningSecret> =
            jsonHandler<BetaOrganizationWebhookEndpointSigningSecret>(clientOptions.jsonMapper)

        override fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("webhookEndpointId", params.webhookEndpointId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .addPathParam("webhookEndpointId", params._pathParam(0))
                    .addPathSegments("regenerate_signing_secret")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCall(clientOptions, params) { client ->
                val response = client.execute(request, requestOptions)
                errorHandler.handle(response).parseable {
                    response
                        .use { regenerateSigningSecretHandler.handle(it) }
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
