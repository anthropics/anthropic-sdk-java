package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPageAsync
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitRetrieveParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import com.anthropic.services.async.beta.organization.spendlimits.EffectiveServiceAsync
import com.anthropic.services.async.beta.organization.spendlimits.IncreaseRequestServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface SpendLimitServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitServiceAsync

    fun effective(): EffectiveServiceAsync

    fun increaseRequests(): IncreaseRequestServiceAsync

    /** Retrieve a spend limit by ID. */
    fun retrieve(spendLimitId: String): CompletableFuture<BetaSpendLimit> =
        retrieve(spendLimitId, SpendLimitRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitId: String,
        params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSpendLimit> =
        retrieve(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        spendLimitId: String,
        params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
    ): CompletableFuture<BetaSpendLimit> = retrieve(spendLimitId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SpendLimitRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSpendLimit>

    /** @see retrieve */
    fun retrieve(params: SpendLimitRetrieveParams): CompletableFuture<BetaSpendLimit> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaSpendLimit> =
        retrieve(spendLimitId, SpendLimitRetrieveParams.none(), requestOptions)

    /**
     * List the organization's spend limits.
     *
     * A Claude Console organization's limits come in an order that is stable across pages. A Claude
     * Enterprise organization's are grouped by scope type, in the order `organization`,
     * `seat_tier`, `rbac_group`, `organization_service`, `user`; within a type they come in a fixed
     * order that is not creation order. Listing Claude Console limits is in an early access
     * preview. To request access, contact your Anthropic account team.
     */
    fun list(): CompletableFuture<SpendLimitListPageAsync> = list(SpendLimitListParams.none())

    /** @see list */
    fun list(
        params: SpendLimitListParams = SpendLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitListPageAsync>

    /** @see list */
    fun list(
        params: SpendLimitListParams = SpendLimitListParams.none()
    ): CompletableFuture<SpendLimitListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<SpendLimitListPageAsync> =
        list(SpendLimitListParams.none(), requestOptions)

    /**
     * Delete a spend limit.
     *
     * For a Claude Enterprise organization, this deletes a per-user override, and the member falls
     * back to any inherited spend limit at that period. Its seat-tier, group, and
     * organization-level rows cannot be deleted via this endpoint. A Claude Console organization
     * deletes its organization and workspace limits. Deleting them through the API is in an early
     * access preview.
     */
    fun delete(spendLimitId: String): CompletableFuture<SpendLimitDeleteResponse> =
        delete(spendLimitId, SpendLimitDeleteParams.none())

    /** @see delete */
    fun delete(
        spendLimitId: String,
        params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitDeleteResponse> =
        delete(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

    /** @see delete */
    fun delete(
        spendLimitId: String,
        params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
    ): CompletableFuture<SpendLimitDeleteResponse> =
        delete(spendLimitId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SpendLimitDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SpendLimitDeleteResponse>

    /** @see delete */
    fun delete(params: SpendLimitDeleteParams): CompletableFuture<SpendLimitDeleteResponse> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        spendLimitId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<SpendLimitDeleteResponse> =
        delete(spendLimitId, SpendLimitDeleteParams.none(), requestOptions)

    /**
     * Set a spend limit.
     *
     * Upsert keyed on (scope, period): setting a limit that already exists overwrites it in place.
     * A Claude Enterprise organization sets `user` limits. Its seat-tier, group, and
     * organization-level defaults are configured in claude.ai. A Claude Console organization sets
     * `organization` and `workspace` limits, which are monthly and always carry an amount. Setting
     * those limits is in an early access preview. To request access, contact your Anthropic account
     * team.
     */
    fun set(params: SpendLimitSetParams): CompletableFuture<BetaSpendLimit> =
        set(params, RequestOptions.none())

    /** @see set */
    fun set(
        params: SpendLimitSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaSpendLimit>

    /**
     * A view of [SpendLimitServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitServiceAsync.WithRawResponse

        fun effective(): EffectiveServiceAsync.WithRawResponse

        fun increaseRequests(): IncreaseRequestServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/spend_limits/{spend_limit_id}?beta=true`, but is otherwise the same as
         * [SpendLimitServiceAsync.retrieve].
         */
        fun retrieve(spendLimitId: String): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            retrieve(spendLimitId, SpendLimitRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            spendLimitId: String,
            params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            retrieve(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            spendLimitId: String,
            params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            retrieve(spendLimitId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: SpendLimitRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>>

        /** @see retrieve */
        fun retrieve(
            params: SpendLimitRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            spendLimitId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            retrieve(spendLimitId, SpendLimitRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/spend_limits?beta=true`, but is
         * otherwise the same as [SpendLimitServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<SpendLimitListPageAsync>> =
            list(SpendLimitListParams.none())

        /** @see list */
        fun list(
            params: SpendLimitListParams = SpendLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitListPageAsync>>

        /** @see list */
        fun list(
            params: SpendLimitListParams = SpendLimitListParams.none()
        ): CompletableFuture<HttpResponseFor<SpendLimitListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<SpendLimitListPageAsync>> =
            list(SpendLimitListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/spend_limits/{spend_limit_id}?beta=true`, but is otherwise the same as
         * [SpendLimitServiceAsync.delete].
         */
        fun delete(
            spendLimitId: String
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> =
            delete(spendLimitId, SpendLimitDeleteParams.none())

        /** @see delete */
        fun delete(
            spendLimitId: String,
            params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> =
            delete(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

        /** @see delete */
        fun delete(
            spendLimitId: String,
            params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> =
            delete(spendLimitId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: SpendLimitDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>>

        /** @see delete */
        fun delete(
            params: SpendLimitDeleteParams
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            spendLimitId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<SpendLimitDeleteResponse>> =
            delete(spendLimitId, SpendLimitDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/spend_limits?beta=true`, but is
         * otherwise the same as [SpendLimitServiceAsync.set].
         */
        fun set(params: SpendLimitSetParams): CompletableFuture<HttpResponseFor<BetaSpendLimit>> =
            set(params, RequestOptions.none())

        /** @see set */
        fun set(
            params: SpendLimitSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaSpendLimit>>
    }
}
