package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimit
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitDeleteResponse
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListPage
import com.anthropic.models.beta.organization.spendlimits.SpendLimitListParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitRetrieveParams
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import com.anthropic.services.blocking.beta.organization.spendlimits.EffectiveService
import com.anthropic.services.blocking.beta.organization.spendlimits.IncreaseRequestService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface SpendLimitService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): SpendLimitService

    fun effective(): EffectiveService

    fun increaseRequests(): IncreaseRequestService

    /** Retrieve a spend limit by ID. */
    fun retrieve(spendLimitId: String): BetaSpendLimit =
        retrieve(spendLimitId, SpendLimitRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        spendLimitId: String,
        params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimit =
        retrieve(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        spendLimitId: String,
        params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
    ): BetaSpendLimit = retrieve(spendLimitId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: SpendLimitRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimit

    /** @see retrieve */
    fun retrieve(params: SpendLimitRetrieveParams): BetaSpendLimit =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(spendLimitId: String, requestOptions: RequestOptions): BetaSpendLimit =
        retrieve(spendLimitId, SpendLimitRetrieveParams.none(), requestOptions)

    /**
     * List the organization's spend limits.
     *
     * A Claude Console organization's limits come in an order that is stable across pages. A Claude
     * Enterprise organization's are grouped by scope type, in the order `organization`,
     * `seat_tier`, `rbac_group`, `organization_service`, `user`; within a type they come in a fixed
     * order that is not creation order.
     */
    fun list(): SpendLimitListPage = list(SpendLimitListParams.none())

    /** @see list */
    fun list(
        params: SpendLimitListParams = SpendLimitListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SpendLimitListPage

    /** @see list */
    fun list(params: SpendLimitListParams = SpendLimitListParams.none()): SpendLimitListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): SpendLimitListPage =
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
    fun delete(spendLimitId: String): SpendLimitDeleteResponse =
        delete(spendLimitId, SpendLimitDeleteParams.none())

    /** @see delete */
    fun delete(
        spendLimitId: String,
        params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SpendLimitDeleteResponse =
        delete(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

    /** @see delete */
    fun delete(
        spendLimitId: String,
        params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
    ): SpendLimitDeleteResponse = delete(spendLimitId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: SpendLimitDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SpendLimitDeleteResponse

    /** @see delete */
    fun delete(params: SpendLimitDeleteParams): SpendLimitDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(spendLimitId: String, requestOptions: RequestOptions): SpendLimitDeleteResponse =
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
    fun set(params: SpendLimitSetParams): BetaSpendLimit = set(params, RequestOptions.none())

    /** @see set */
    fun set(
        params: SpendLimitSetParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSpendLimit

    /** A view of [SpendLimitService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): SpendLimitService.WithRawResponse

        fun effective(): EffectiveService.WithRawResponse

        fun increaseRequests(): IncreaseRequestService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/spend_limits/{spend_limit_id}?beta=true`, but is otherwise the same as
         * [SpendLimitService.retrieve].
         */
        @MustBeClosed
        fun retrieve(spendLimitId: String): HttpResponseFor<BetaSpendLimit> =
            retrieve(spendLimitId, SpendLimitRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitId: String,
            params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimit> =
            retrieve(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitId: String,
            params: SpendLimitRetrieveParams = SpendLimitRetrieveParams.none(),
        ): HttpResponseFor<BetaSpendLimit> = retrieve(spendLimitId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: SpendLimitRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimit>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: SpendLimitRetrieveParams): HttpResponseFor<BetaSpendLimit> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            spendLimitId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaSpendLimit> =
            retrieve(spendLimitId, SpendLimitRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/spend_limits?beta=true`, but is
         * otherwise the same as [SpendLimitService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<SpendLimitListPage> = list(SpendLimitListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: SpendLimitListParams = SpendLimitListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SpendLimitListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: SpendLimitListParams = SpendLimitListParams.none()
        ): HttpResponseFor<SpendLimitListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<SpendLimitListPage> =
            list(SpendLimitListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/spend_limits/{spend_limit_id}?beta=true`, but is otherwise the same as
         * [SpendLimitService.delete].
         */
        @MustBeClosed
        fun delete(spendLimitId: String): HttpResponseFor<SpendLimitDeleteResponse> =
            delete(spendLimitId, SpendLimitDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            spendLimitId: String,
            params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SpendLimitDeleteResponse> =
            delete(params.toBuilder().spendLimitId(spendLimitId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            spendLimitId: String,
            params: SpendLimitDeleteParams = SpendLimitDeleteParams.none(),
        ): HttpResponseFor<SpendLimitDeleteResponse> =
            delete(spendLimitId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: SpendLimitDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SpendLimitDeleteResponse>

        /** @see delete */
        @MustBeClosed
        fun delete(params: SpendLimitDeleteParams): HttpResponseFor<SpendLimitDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            spendLimitId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<SpendLimitDeleteResponse> =
            delete(spendLimitId, SpendLimitDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/organizations/spend_limits?beta=true`, but is
         * otherwise the same as [SpendLimitService.set].
         */
        @MustBeClosed
        fun set(params: SpendLimitSetParams): HttpResponseFor<BetaSpendLimit> =
            set(params, RequestOptions.none())

        /** @see set */
        @MustBeClosed
        fun set(
            params: SpendLimitSetParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSpendLimit>
    }
}
