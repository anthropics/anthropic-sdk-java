package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.webhooks.BetaDeletedOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaNewOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpointSigningSecret
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookListPageAsync
import com.anthropic.models.beta.organization.webhooks.WebhookListParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface WebhookServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookServiceAsync

    /**
     * Create an organization webhook endpoint.
     *
     * An organization webhook endpoint belongs to the organization rather than to one of its
     * workspaces. An organization can have at most 32 organization webhook endpoints, enabled or
     * disabled.
     *
     * The response includes the endpoint's signing secret. No read returns it, so store it when you
     * receive it.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun create(params: WebhookCreateParams): CompletableFuture<BetaNewOrganizationWebhookEndpoint> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WebhookCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaNewOrganizationWebhookEndpoint>

    /**
     * Retrieve an organization webhook endpoint by ID.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun retrieve(webhookEndpointId: String): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        retrieve(webhookEndpointId, WebhookRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        retrieve(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        retrieve(webhookEndpointId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WebhookRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint>

    /** @see retrieve */
    fun retrieve(
        params: WebhookRetrieveParams
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        retrieve(webhookEndpointId, WebhookRetrieveParams.none(), requestOptions)

    /**
     * Update an organization webhook endpoint. Only the fields you send are changed.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun update(webhookEndpointId: String): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        update(webhookEndpointId, WebhookUpdateParams.none())

    /** @see update */
    fun update(
        webhookEndpointId: String,
        params: WebhookUpdateParams = WebhookUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        update(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see update */
    fun update(
        webhookEndpointId: String,
        params: WebhookUpdateParams = WebhookUpdateParams.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        update(webhookEndpointId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WebhookUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpoint>

    /** @see update */
    fun update(params: WebhookUpdateParams): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpoint> =
        update(webhookEndpointId, WebhookUpdateParams.none(), requestOptions)

    /**
     * List the organization's webhook endpoints.
     *
     * An organization webhook endpoint belongs to the organization rather than to one of its
     * workspaces. The list does not include webhook endpoints that belong to a workspace.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun list(): CompletableFuture<WebhookListPageAsync> = list(WebhookListParams.none())

    /** @see list */
    fun list(
        params: WebhookListParams = WebhookListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<WebhookListPageAsync>

    /** @see list */
    fun list(
        params: WebhookListParams = WebhookListParams.none()
    ): CompletableFuture<WebhookListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<WebhookListPageAsync> =
        list(WebhookListParams.none(), requestOptions)

    /**
     * Delete an organization webhook endpoint.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun delete(
        webhookEndpointId: String
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        delete(webhookEndpointId, WebhookDeleteParams.none())

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        params: WebhookDeleteParams = WebhookDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        delete(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        params: WebhookDeleteParams = WebhookDeleteParams.none(),
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        delete(webhookEndpointId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: WebhookDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint>

    /** @see delete */
    fun delete(
        params: WebhookDeleteParams
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedOrganizationWebhookEndpoint> =
        delete(webhookEndpointId, WebhookDeleteParams.none(), requestOptions)

    /**
     * Replace an organization webhook endpoint's signing secret. The old secret stops working
     * immediately. The new secret is returned only in this response.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun regenerateSigningSecret(
        webhookEndpointId: String
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        regenerateSigningSecret(webhookEndpointId, WebhookRegenerateSigningSecretParams.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        params: WebhookRegenerateSigningSecretParams = WebhookRegenerateSigningSecretParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        regenerateSigningSecret(
            params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
            requestOptions,
        )

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        params: WebhookRegenerateSigningSecretParams = WebhookRegenerateSigningSecretParams.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        regenerateSigningSecret(webhookEndpointId, params, RequestOptions.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret>

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        regenerateSigningSecret(params, RequestOptions.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaOrganizationWebhookEndpointSigningSecret> =
        regenerateSigningSecret(
            webhookEndpointId,
            WebhookRegenerateSigningSecretParams.none(),
            requestOptions,
        )

    /**
     * A view of [WebhookServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): WebhookServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/webhooks`, but is otherwise the
         * same as [WebhookServiceAsync.create].
         */
        fun create(
            params: WebhookCreateParams
        ): CompletableFuture<HttpResponseFor<BetaNewOrganizationWebhookEndpoint>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: WebhookCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaNewOrganizationWebhookEndpoint>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/webhooks/{webhook_endpoint_id}`,
         * but is otherwise the same as [WebhookServiceAsync.retrieve].
         */
        fun retrieve(
            webhookEndpointId: String
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            retrieve(webhookEndpointId, WebhookRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            webhookEndpointId: String,
            params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            retrieve(
                params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
                requestOptions,
            )

        /** @see retrieve */
        fun retrieve(
            webhookEndpointId: String,
            params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            retrieve(webhookEndpointId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: WebhookRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>>

        /** @see retrieve */
        fun retrieve(
            params: WebhookRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            retrieve(webhookEndpointId, WebhookRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/webhooks/{webhook_endpoint_id}`,
         * but is otherwise the same as [WebhookServiceAsync.update].
         */
        fun update(
            webhookEndpointId: String
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            update(webhookEndpointId, WebhookUpdateParams.none())

        /** @see update */
        fun update(
            webhookEndpointId: String,
            params: WebhookUpdateParams = WebhookUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            update(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

        /** @see update */
        fun update(
            webhookEndpointId: String,
            params: WebhookUpdateParams = WebhookUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            update(webhookEndpointId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: WebhookUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>>

        /** @see update */
        fun update(
            params: WebhookUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpoint>> =
            update(webhookEndpointId, WebhookUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/webhooks`, but is otherwise the
         * same as [WebhookServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<WebhookListPageAsync>> =
            list(WebhookListParams.none())

        /** @see list */
        fun list(
            params: WebhookListParams = WebhookListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<WebhookListPageAsync>>

        /** @see list */
        fun list(
            params: WebhookListParams = WebhookListParams.none()
        ): CompletableFuture<HttpResponseFor<WebhookListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<WebhookListPageAsync>> =
            list(WebhookListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/webhooks/{webhook_endpoint_id}`, but is otherwise the same as
         * [WebhookServiceAsync.delete].
         */
        fun delete(
            webhookEndpointId: String
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> =
            delete(webhookEndpointId, WebhookDeleteParams.none())

        /** @see delete */
        fun delete(
            webhookEndpointId: String,
            params: WebhookDeleteParams = WebhookDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> =
            delete(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

        /** @see delete */
        fun delete(
            webhookEndpointId: String,
            params: WebhookDeleteParams = WebhookDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> =
            delete(webhookEndpointId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: WebhookDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>>

        /** @see delete */
        fun delete(
            params: WebhookDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>> =
            delete(webhookEndpointId, WebhookDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/webhooks/{webhook_endpoint_id}/regenerate_signing_secret`, but is
         * otherwise the same as [WebhookServiceAsync.regenerateSigningSecret].
         */
        fun regenerateSigningSecret(
            webhookEndpointId: String
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> =
            regenerateSigningSecret(webhookEndpointId, WebhookRegenerateSigningSecretParams.none())

        /** @see regenerateSigningSecret */
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            params: WebhookRegenerateSigningSecretParams =
                WebhookRegenerateSigningSecretParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> =
            regenerateSigningSecret(
                params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
                requestOptions,
            )

        /** @see regenerateSigningSecret */
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            params: WebhookRegenerateSigningSecretParams =
                WebhookRegenerateSigningSecretParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> =
            regenerateSigningSecret(webhookEndpointId, params, RequestOptions.none())

        /** @see regenerateSigningSecret */
        fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>>

        /** @see regenerateSigningSecret */
        fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> =
            regenerateSigningSecret(params, RequestOptions.none())

        /** @see regenerateSigningSecret */
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>> =
            regenerateSigningSecret(
                webhookEndpointId,
                WebhookRegenerateSigningSecretParams.none(),
                requestOptions,
            )
    }
}
