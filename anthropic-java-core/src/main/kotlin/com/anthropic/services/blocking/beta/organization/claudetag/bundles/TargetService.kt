package com.anthropic.services.blocking.beta.organization.claudetag.bundles

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.BetaClaudeTagDeletedBundleTarget
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetCreateParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetDeleteParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListPage
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetListParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface TargetService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): TargetService

    /**
     * Attach a Bundle to a Slack channel or a Slack workspace. A Bundle attached to a workspace can
     * be used in the workspace and in every channel in it. Attaching a Bundle to a channel or
     * workspace it is already attached to returns the existing Bundle Target. The channel or
     * workspace must be set up: attaching never sets a channel up. A group direct message takes no
     * Bundle and returns 400.
     */
    fun create(bundleId: String, params: TargetCreateParams): BetaClaudeTagBundleTarget =
        create(bundleId, params, RequestOptions.none())

    /** @see create */
    fun create(
        bundleId: String,
        params: TargetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundleTarget =
        create(params.toBuilder().bundleId(bundleId).build(), requestOptions)

    /** @see create */
    fun create(params: TargetCreateParams): BetaClaudeTagBundleTarget =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: TargetCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundleTarget

    /**
     * Get one Bundle Target. Returns 404 when `target_id` is the identifier of another Bundle's
     * Bundle Target, or the Bundle Target was detached.
     */
    fun retrieve(targetId: String, params: TargetRetrieveParams): BetaClaudeTagBundleTarget =
        retrieve(targetId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        targetId: String,
        params: TargetRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundleTarget =
        retrieve(params.toBuilder().targetId(targetId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: TargetRetrieveParams): BetaClaudeTagBundleTarget =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: TargetRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundleTarget

    /**
     * List the Slack channels and Slack workspaces a Bundle is attached to, newest first by
     * `created_at`. Only channels and workspaces the organization owns are listed; a Bundle that is
     * attached to anything else has no Bundle Target for it.
     */
    fun list(bundleId: String): TargetListPage = list(bundleId, TargetListParams.none())

    /** @see list */
    fun list(
        bundleId: String,
        params: TargetListParams = TargetListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TargetListPage = list(params.toBuilder().bundleId(bundleId).build(), requestOptions)

    /** @see list */
    fun list(bundleId: String, params: TargetListParams = TargetListParams.none()): TargetListPage =
        list(bundleId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: TargetListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TargetListPage

    /** @see list */
    fun list(params: TargetListParams): TargetListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(bundleId: String, requestOptions: RequestOptions): TargetListPage =
        list(bundleId, TargetListParams.none(), requestOptions)

    /** Detach a Bundle from a Slack channel or a Slack workspace. A repeated detach returns 404. */
    fun delete(targetId: String, params: TargetDeleteParams): BetaClaudeTagDeletedBundleTarget =
        delete(targetId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        targetId: String,
        params: TargetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagDeletedBundleTarget =
        delete(params.toBuilder().targetId(targetId).build(), requestOptions)

    /** @see delete */
    fun delete(params: TargetDeleteParams): BetaClaudeTagDeletedBundleTarget =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: TargetDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagDeletedBundleTarget

    /** A view of [TargetService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): TargetService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/claude_tag/bundles/{bundle_id}/targets`, but is otherwise the same as
         * [TargetService.create].
         */
        @MustBeClosed
        fun create(
            bundleId: String,
            params: TargetCreateParams,
        ): HttpResponseFor<BetaClaudeTagBundleTarget> =
            create(bundleId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            bundleId: String,
            params: TargetCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundleTarget> =
            create(params.toBuilder().bundleId(bundleId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: TargetCreateParams): HttpResponseFor<BetaClaudeTagBundleTarget> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: TargetCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundleTarget>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}`, but is otherwise
         * the same as [TargetService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            targetId: String,
            params: TargetRetrieveParams,
        ): HttpResponseFor<BetaClaudeTagBundleTarget> =
            retrieve(targetId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            targetId: String,
            params: TargetRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundleTarget> =
            retrieve(params.toBuilder().targetId(targetId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: TargetRetrieveParams): HttpResponseFor<BetaClaudeTagBundleTarget> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: TargetRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundleTarget>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/claude_tag/bundles/{bundle_id}/targets`, but is otherwise the same as
         * [TargetService.list].
         */
        @MustBeClosed
        fun list(bundleId: String): HttpResponseFor<TargetListPage> =
            list(bundleId, TargetListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            bundleId: String,
            params: TargetListParams = TargetListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TargetListPage> =
            list(params.toBuilder().bundleId(bundleId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            bundleId: String,
            params: TargetListParams = TargetListParams.none(),
        ): HttpResponseFor<TargetListPage> = list(bundleId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: TargetListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TargetListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: TargetListParams): HttpResponseFor<TargetListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            bundleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<TargetListPage> = list(bundleId, TargetListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/organizations/claude_tag/bundles/{bundle_id}/targets/{target_id}`, but is otherwise
         * the same as [TargetService.delete].
         */
        @MustBeClosed
        fun delete(
            targetId: String,
            params: TargetDeleteParams,
        ): HttpResponseFor<BetaClaudeTagDeletedBundleTarget> =
            delete(targetId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            targetId: String,
            params: TargetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagDeletedBundleTarget> =
            delete(params.toBuilder().targetId(targetId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(params: TargetDeleteParams): HttpResponseFor<BetaClaudeTagDeletedBundleTarget> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: TargetDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagDeletedBundleTarget>
    }
}
