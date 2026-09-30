package com.anthropic.services.blocking.beta.organization.spendlimits

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListPage
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface EffectiveService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EffectiveService

    /**
     * List each member's effective spend limit and period-to-date spend.
     *
     * Returns one row per (member, period) the member resolves a spend limit for, with the `source`
     * scope the spend limit was inherited from. Paginates by member, so a member's periods never
     * split across pages.
     */
    fun list(): EffectiveListPage = list(EffectiveListParams.none())

    /** @see list */
    fun list(
        params: EffectiveListParams = EffectiveListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): EffectiveListPage

    /** @see list */
    fun list(params: EffectiveListParams = EffectiveListParams.none()): EffectiveListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): EffectiveListPage =
        list(EffectiveListParams.none(), requestOptions)

    /** A view of [EffectiveService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): EffectiveService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/spend_limits/effective?beta=true`,
         * but is otherwise the same as [EffectiveService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<EffectiveListPage> = list(EffectiveListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: EffectiveListParams = EffectiveListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<EffectiveListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: EffectiveListParams = EffectiveListParams.none()
        ): HttpResponseFor<EffectiveListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<EffectiveListPage> =
            list(EffectiveListParams.none(), requestOptions)
    }
}
