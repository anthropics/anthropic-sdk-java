package com.anthropic.services.async.beta.organization

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
import com.anthropic.core.opentelemetry.thenParse
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.core.prepareAsync
import com.anthropic.models.beta.organization.webhooks.BetaDeletedOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaNewOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpointSigningSecret
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookListPageAsync
import com.anthropic.models.beta.organization.webhooks.WebhookListPageResponse
import com.anthropic.models.beta.organization.webhooks.WebhookListParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class WebhookServiceAsyncImpl internal constructor(private val clientOptions: ClientOptions) :
    WebhookServiceAsync {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "webhooks-2026-10-07").build()
    }

    private val withRawResponse: WebhookServiceAsync.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): WebhookServiceAsync.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookServiceAsync =
        WebhookServiceAsyncImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun create(
        params: WebhookCreateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaNewOrganizationWebhookEndpoint> =
        // post /v1/organizations/webhooks?beta=true
        withRawResponse().create(params, requestOptions).thenParse()

    override fun retrieve(
        params: WebhookRetrieveParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        // get /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().retrieve(params, requestOptions).thenParse()

    override fun update(
        params: WebhookUpdateParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        // post /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().update(params, requestOptions).thenParse()

    override fun list(
        params: WebhookListParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<WebhookListPageAsync> =
        // get /v1/organizations/webhooks?beta=true
        withRawResponse().list(params, requestOptions).thenParse()

    override fun delete(
        params: WebhookDeleteParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        // delete /v1/organizations/webhooks/{webhook_endpoint_id}?beta=true
        withRawResponse().delete(params, requestOptions).thenParse()

    override fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        // post /v1/organizations/webhooks/{webhook_endpoint_id}/regenerate_signing_secret?beta=true
        withRawResponse().regenerateSigningSecret(params, requestOptions).thenParse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        WebhookServiceAsync.WithRawResponse {

        private val errorHandler: Handler<HttpResponse> =
            errorHandler(errorBodyHandler(clientOptions.jsonMapper))

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WebhookServiceAsync.WithRawResponse =
            WebhookServiceAsyncImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val createHandler: Handler<BetaNewOrganizationWebhookEndpoint> =
            jsonHandler<BetaNewOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun create(
            params: WebhookCreateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaNewOrganizationWebhookEndpoint>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                                        "anthropic.organization.webhook.id",
                                        it._id(),
                                    )
                                }
                        }
                    }
            }
        }

        private val retrieveHandler: Handler<BetaOrganizationWebhookEndpoint> =
            jsonHandler<BetaOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun retrieve(
            params: WebhookRetrieveParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> {
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

        private val updateHandler: Handler<BetaOrganizationWebhookEndpoint> =
            jsonHandler<BetaOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun update(
            params: WebhookUpdateParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> {
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

        private val listHandler: Handler<WebhookListPageResponse> =
            jsonHandler<WebhookListPageResponse>(clientOptions.jsonMapper)

        override fun list(
            params: WebhookListParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<WebhookListPageAsync>> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "organizations", "webhooks")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
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
                                    WebhookListPageAsync.builder()
                                        .service(WebhookServiceAsyncImpl(clientOptions))
                                        .streamHandlerExecutor(clientOptions.streamHandlerExecutor)
                                        .params(params)
                                        .response(it)
                                        .build()
                                }
                        }
                    }
            }
        }

        private val deleteHandler: Handler<BetaDeletedOrganizationWebhookEndpoint> =
            jsonHandler<BetaDeletedOrganizationWebhookEndpoint>(clientOptions.jsonMapper)

        override fun delete(
            params: WebhookDeleteParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> {
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

        private val regenerateSigningSecretHandler:
            Handler<BetaOrganizationWebhookEndpointSigningSecret> =
            jsonHandler<BetaOrganizationWebhookEndpointSigningSecret>(clientOptions.jsonMapper)

        override fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> {
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
                    .prepareAsync(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return traceApiCallAsync(clientOptions, params) { client ->
                request
                    .thenComposeAsync { client.executeAsync(it, requestOptions) }
                    .thenApply { response ->
                        errorHandler.handle(response).parseable {
                            response
                                .use { regenerateSigningSecretHandler.handle(it) }
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
