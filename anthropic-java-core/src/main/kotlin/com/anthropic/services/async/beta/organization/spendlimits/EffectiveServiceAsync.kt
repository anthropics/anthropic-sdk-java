package com.anthropic.services.async.beta.organization.spendlimits

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListPageAsync
import com.anthropic.models.beta.organization.spendlimits.effective.EffectiveListParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface EffectiveServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): EffectiveServiceAsync

    /**
     * List each member's effective spend limit and period-to-date spend.
     *
     * Returns one row per (member, period) the member resolves a spend limit for, with the `source`
     * scope the spend limit was inherited from. Paginates by member, so a member's periods never
     * split across pages. Listing Claude Console limits is in an early access preview. To request
     * access, contact your Anthropic account team.
     */
    fun list(): CompletableFuture<EffectiveListPageAsync> = list(EffectiveListParams.none())

    /** @see list */
    fun list(
        params: EffectiveListParams = EffectiveListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<EffectiveListPageAsync>

    /** @see list */
    fun list(
        params: EffectiveListParams = EffectiveListParams.none()
    ): CompletableFuture<EffectiveListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<EffectiveListPageAsync> =
        list(EffectiveListParams.none(), requestOptions)

    /**
     * A view of [EffectiveServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): EffectiveServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/spend_limits/effective?beta=true`,
         * but is otherwise the same as [EffectiveServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<EffectiveListPageAsync>> =
            list(EffectiveListParams.none())

        /** @see list */
        fun list(
            params: EffectiveListParams = EffectiveListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<EffectiveListPageAsync>>

        /** @see list */
        fun list(
            params: EffectiveListParams = EffectiveListParams.none()
        ): CompletableFuture<HttpResponseFor<EffectiveListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<EffectiveListPageAsync>> =
            list(EffectiveListParams.none(), requestOptions)
    }
}
