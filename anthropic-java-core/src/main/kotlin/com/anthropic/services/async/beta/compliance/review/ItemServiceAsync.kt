package com.anthropic.services.async.beta.compliance.review

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.compliance.review.items.BetaComplianceReviewItem
import com.anthropic.models.beta.compliance.review.items.ItemRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface ItemServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): ItemServiceAsync

    /**
     * Read one message that Anthropic flagged for your organization to review, with its request and
     * response.
     *
     * A message that the API key can't read, or that isn't flagged, returns the same 404 as one
     * that doesn't exist. For a message recorded with a user profile, send that profile's ID in the
     * `anthropic-user-profile-id` header. A flagged message can also return 404 if it belongs to
     * another organization than the key's own, or to your end customer's user profile or Workspace.
     *
     * If your encryption key or storage bucket refuses the read, the API returns a 400 with
     * `cmek_key_disabled`, `storage_bucket_unreadable` or `customer_storage_refusal_unattributed`
     * in `error.details.error_code`.
     *
     * The API key must have the `read:compliance_review_items`, `read:compliance_user_data` or
     * `read:org_audit` scope. A key of an organization with no parent organization must have
     * `read:compliance_review_items`.
     */
    fun retrieve(itemId: String): CompletableFuture<BetaComplianceReviewItem> =
        retrieve(itemId, ItemRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        itemId: String,
        params: ItemRetrieveParams = ItemRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewItem> =
        retrieve(params.toBuilder().itemId(itemId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        itemId: String,
        params: ItemRetrieveParams = ItemRetrieveParams.none(),
    ): CompletableFuture<BetaComplianceReviewItem> = retrieve(itemId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: ItemRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaComplianceReviewItem>

    /** @see retrieve */
    fun retrieve(params: ItemRetrieveParams): CompletableFuture<BetaComplianceReviewItem> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        itemId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaComplianceReviewItem> =
        retrieve(itemId, ItemRetrieveParams.none(), requestOptions)

    /** A view of [ItemServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): ItemServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/compliance/review/items/{item_id}`, but is
         * otherwise the same as [ItemServiceAsync.retrieve].
         */
        fun retrieve(itemId: String): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> =
            retrieve(itemId, ItemRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            itemId: String,
            params: ItemRetrieveParams = ItemRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> =
            retrieve(params.toBuilder().itemId(itemId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            itemId: String,
            params: ItemRetrieveParams = ItemRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> =
            retrieve(itemId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: ItemRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>>

        /** @see retrieve */
        fun retrieve(
            params: ItemRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            itemId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaComplianceReviewItem>> =
            retrieve(itemId, ItemRetrieveParams.none(), requestOptions)
    }
}
