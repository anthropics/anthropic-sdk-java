package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.webhooks.BetaDeletedOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaNewOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpoint
import com.anthropic.models.beta.organization.webhooks.BetaOrganizationWebhookEndpointSigningSecret
import com.anthropic.models.beta.organization.webhooks.WebhookCreateParams
import com.anthropic.models.beta.organization.webhooks.WebhookDeleteParams
import com.anthropic.models.beta.organization.webhooks.WebhookListPage
import com.anthropic.models.beta.organization.webhooks.WebhookListParams
import com.anthropic.models.beta.organization.webhooks.WebhookRegenerateSigningSecretParams
import com.anthropic.models.beta.organization.webhooks.WebhookRetrieveParams
import com.anthropic.models.beta.organization.webhooks.WebhookUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface WebhookService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService

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
    fun create(params: WebhookCreateParams): BetaNewOrganizationWebhookEndpoint =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: WebhookCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaNewOrganizationWebhookEndpoint

    /**
     * Retrieve an organization webhook endpoint by ID.
     *
     * Every request must include the beta header `anthropic-beta: webhooks-2026-10-07`. A request
     * without it returns `404`, exactly as if this API did not exist. Anthropic turns organization
     * webhook endpoints on for each organization; until yours is on, requests also return `404`.
     * Organization webhook endpoints are in beta and are not available to organizations with HIPAA
     * readiness enabled.
     */
    fun retrieve(webhookEndpointId: String): BetaOrganizationWebhookEndpoint =
        retrieve(webhookEndpointId, WebhookRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpoint =
        retrieve(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
    ): BetaOrganizationWebhookEndpoint = retrieve(webhookEndpointId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: WebhookRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpoint

    /** @see retrieve */
    fun retrieve(params: WebhookRetrieveParams): BetaOrganizationWebhookEndpoint =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpoint =
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
    fun update(webhookEndpointId: String): BetaOrganizationWebhookEndpoint =
        update(webhookEndpointId, WebhookUpdateParams.none())

    /** @see update */
    fun update(
        webhookEndpointId: String,
        params: WebhookUpdateParams = WebhookUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpoint =
        update(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see update */
    fun update(
        webhookEndpointId: String,
        params: WebhookUpdateParams = WebhookUpdateParams.none(),
    ): BetaOrganizationWebhookEndpoint = update(webhookEndpointId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: WebhookUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpoint

    /** @see update */
    fun update(params: WebhookUpdateParams): BetaOrganizationWebhookEndpoint =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpoint =
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
    fun list(): WebhookListPage = list(WebhookListParams.none())

    /** @see list */
    fun list(
        params: WebhookListParams = WebhookListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): WebhookListPage

    /** @see list */
    fun list(params: WebhookListParams = WebhookListParams.none()): WebhookListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): WebhookListPage =
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
    fun delete(webhookEndpointId: String): BetaDeletedOrganizationWebhookEndpoint =
        delete(webhookEndpointId, WebhookDeleteParams.none())

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        params: WebhookDeleteParams = WebhookDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedOrganizationWebhookEndpoint =
        delete(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        params: WebhookDeleteParams = WebhookDeleteParams.none(),
    ): BetaDeletedOrganizationWebhookEndpoint =
        delete(webhookEndpointId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: WebhookDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedOrganizationWebhookEndpoint

    /** @see delete */
    fun delete(params: WebhookDeleteParams): BetaDeletedOrganizationWebhookEndpoint =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): BetaDeletedOrganizationWebhookEndpoint =
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
    ): BetaOrganizationWebhookEndpointSigningSecret =
        regenerateSigningSecret(webhookEndpointId, WebhookRegenerateSigningSecretParams.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        params: WebhookRegenerateSigningSecretParams = WebhookRegenerateSigningSecretParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpointSigningSecret =
        regenerateSigningSecret(
            params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
            requestOptions,
        )

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        params: WebhookRegenerateSigningSecretParams = WebhookRegenerateSigningSecretParams.none(),
    ): BetaOrganizationWebhookEndpointSigningSecret =
        regenerateSigningSecret(webhookEndpointId, params, RequestOptions.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaOrganizationWebhookEndpointSigningSecret

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        params: WebhookRegenerateSigningSecretParams
    ): BetaOrganizationWebhookEndpointSigningSecret =
        regenerateSigningSecret(params, RequestOptions.none())

    /** @see regenerateSigningSecret */
    fun regenerateSigningSecret(
        webhookEndpointId: String,
        requestOptions: RequestOptions,
    ): BetaOrganizationWebhookEndpointSigningSecret =
        regenerateSigningSecret(
            webhookEndpointId,
            WebhookRegenerateSigningSecretParams.none(),
            requestOptions,
        )

    /** A view of [WebhookService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): WebhookService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/webhooks`, but is otherwise the
         * same as [WebhookService.create].
         */
        @MustBeClosed
        fun create(
            params: WebhookCreateParams
        ): HttpResponseFor<BetaNewOrganizationWebhookEndpoint> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: WebhookCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaNewOrganizationWebhookEndpoint>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/webhooks/{webhook_endpoint_id}`,
         * but is otherwise the same as [WebhookService.retrieve].
         */
        @MustBeClosed
        fun retrieve(webhookEndpointId: String): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            retrieve(webhookEndpointId, WebhookRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            webhookEndpointId: String,
            params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            retrieve(
                params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
                requestOptions,
            )

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            webhookEndpointId: String,
            params: WebhookRetrieveParams = WebhookRetrieveParams.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            retrieve(webhookEndpointId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: WebhookRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: WebhookRetrieveParams
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            retrieve(webhookEndpointId, WebhookRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/webhooks/{webhook_endpoint_id}`,
         * but is otherwise the same as [WebhookService.update].
         */
        @MustBeClosed
        fun update(webhookEndpointId: String): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            update(webhookEndpointId, WebhookUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            webhookEndpointId: String,
            params: WebhookUpdateParams = WebhookUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            update(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            webhookEndpointId: String,
            params: WebhookUpdateParams = WebhookUpdateParams.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            update(webhookEndpointId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: WebhookUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint>

        /** @see update */
        @MustBeClosed
        fun update(params: WebhookUpdateParams): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpoint> =
            update(webhookEndpointId, WebhookUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/webhooks`, but is otherwise the
         * same as [WebhookService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<WebhookListPage> = list(WebhookListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: WebhookListParams = WebhookListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<WebhookListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: WebhookListParams = WebhookListParams.none()
        ): HttpResponseFor<WebhookListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<WebhookListPage> =
            list(WebhookListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/webhooks/{webhook_endpoint_id}`, but is otherwise the same as
         * [WebhookService.delete].
         */
        @MustBeClosed
        fun delete(
            webhookEndpointId: String
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> =
            delete(webhookEndpointId, WebhookDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            webhookEndpointId: String,
            params: WebhookDeleteParams = WebhookDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> =
            delete(params.toBuilder().webhookEndpointId(webhookEndpointId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            webhookEndpointId: String,
            params: WebhookDeleteParams = WebhookDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> =
            delete(webhookEndpointId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: WebhookDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint>

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: WebhookDeleteParams
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedOrganizationWebhookEndpoint> =
            delete(webhookEndpointId, WebhookDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/webhooks/{webhook_endpoint_id}/regenerate_signing_secret`, but is
         * otherwise the same as [WebhookService.regenerateSigningSecret].
         */
        @MustBeClosed
        fun regenerateSigningSecret(
            webhookEndpointId: String
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> =
            regenerateSigningSecret(webhookEndpointId, WebhookRegenerateSigningSecretParams.none())

        /** @see regenerateSigningSecret */
        @MustBeClosed
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            params: WebhookRegenerateSigningSecretParams =
                WebhookRegenerateSigningSecretParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> =
            regenerateSigningSecret(
                params.toBuilder().webhookEndpointId(webhookEndpointId).build(),
                requestOptions,
            )

        /** @see regenerateSigningSecret */
        @MustBeClosed
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            params: WebhookRegenerateSigningSecretParams =
                WebhookRegenerateSigningSecretParams.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> =
            regenerateSigningSecret(webhookEndpointId, params, RequestOptions.none())

        /** @see regenerateSigningSecret */
        @MustBeClosed
        fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret>

        /** @see regenerateSigningSecret */
        @MustBeClosed
        fun regenerateSigningSecret(
            params: WebhookRegenerateSigningSecretParams
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> =
            regenerateSigningSecret(params, RequestOptions.none())

        /** @see regenerateSigningSecret */
        @MustBeClosed
        fun regenerateSigningSecret(
            webhookEndpointId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaOrganizationWebhookEndpointSigningSecret> =
            regenerateSigningSecret(
                webhookEndpointId,
                WebhookRegenerateSigningSecretParams.none(),
                requestOptions,
            )
    }
}
