package com.anthropic.services.async.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplace
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplaceValidationReport
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListPageAsync
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceRetrieveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceUpdateParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateArchiveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateRepositoryParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface PluginMarketplaceServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginMarketplaceServiceAsync

    /**
     * Retrieve a plugin marketplace by ID.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun retrieve(marketplaceId: String): CompletableFuture<BetaPluginMarketplace> =
        retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        marketplaceId: String,
        params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplace> =
        retrieve(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        marketplaceId: String,
        params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
    ): CompletableFuture<BetaPluginMarketplace> =
        retrieve(marketplaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: PluginMarketplaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplace>

    /** @see retrieve */
    fun retrieve(
        params: PluginMarketplaceRetrieveParams
    ): CompletableFuture<BetaPluginMarketplace> = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        marketplaceId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaPluginMarketplace> =
        retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none(), requestOptions)

    /**
     * Set the default installation setting of one of the organization's own plugin marketplaces.
     * Every Plugin in it without a setting of its own gets this default as its organization-wide
     * setting, including Plugins added later.
     *
     * Pass it as `default_installation_preference`. A member's personal marketplace cannot be
     * updated here (403).
     *
     * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun update(
        marketplaceId: String,
        params: PluginMarketplaceUpdateParams,
    ): CompletableFuture<BetaPluginMarketplace> =
        update(marketplaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        marketplaceId: String,
        params: PluginMarketplaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplace> =
        update(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

    /** @see update */
    fun update(params: PluginMarketplaceUpdateParams): CompletableFuture<BetaPluginMarketplace> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: PluginMarketplaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplace>

    /**
     * List the plugin marketplaces Plugins live in, newest first: the organization's own and its
     * members' personal ones.
     *
     * Plugin marketplaces are created, connected to a repository and deleted in claude.ai, not
     * through this API. The organization's library marketplace, the organization-owned `manual`
     * marketplace that uploads go to when no marketplace is named, is created the first time
     * something is put in it and is listed from then on.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope,
     * or a Compliance Access Key with the `read:compliance_org_data` scope.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun list(): CompletableFuture<PluginMarketplaceListPageAsync> =
        list(PluginMarketplaceListParams.none())

    /** @see list */
    fun list(
        params: PluginMarketplaceListParams = PluginMarketplaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<PluginMarketplaceListPageAsync>

    /** @see list */
    fun list(
        params: PluginMarketplaceListParams = PluginMarketplaceListParams.none()
    ): CompletableFuture<PluginMarketplaceListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<PluginMarketplaceListPageAsync> =
        list(PluginMarketplaceListParams.none(), requestOptions)

    /**
     * Check whether a plugin marketplace, uploaded as a `.zip` of the marketplace directory, would
     * synchronize into claude.ai, without connecting or storing it.
     *
     * To check a public GitHub repository instead, use Validate Plugin Marketplace Repository.
     *
     * The report says whether `marketplace.json` is well-formed, which plugins a synchronization
     * would skip and why, and which plugins would synchronize only in part, with some files left
     * out. An archive that cannot be read as a marketplace is reported, not refused: the response
     * is a report with `valid: false`. Plugin sources outside the marketplace are fetched
     * anonymously from GitHub, so a private one is reported as not found; a source on any other
     * host is not fetched here, and the report notes that it will be checked when the marketplace
     * actually synchronizes.
     *
     * Nothing is recorded on the Compliance API activity feed.
     *
     * For a worked example, see
     * [Validate marketplace content](/docs/en/manage-claude/plugins-api#validate-marketplace-content)
     * in the Plugins API guide.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `write:plugins` scope;
     * `read:org_audit` and `read:compliance_org_data` do not grant it.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun validateArchive(
        params: PluginMarketplaceValidateArchiveParams
    ): CompletableFuture<BetaPluginMarketplaceValidationReport> =
        validateArchive(params, RequestOptions.none())

    /** @see validateArchive */
    fun validateArchive(
        params: PluginMarketplaceValidateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplaceValidationReport>

    /**
     * Check whether a plugin marketplace held in a public GitHub repository would synchronize into
     * claude.ai, without connecting or storing it.
     *
     * To check a `.zip` of the marketplace directory instead, use Validate Plugin Marketplace
     * Archive.
     *
     * The report says whether `marketplace.json` is well-formed, which plugins a synchronization
     * would skip and why, and which plugins would synchronize only in part, with some files left
     * out. A repository that is missing, private, or has no such branch or commit is reported, not
     * refused: the response is a report with `valid: false`. Plugin sources outside the marketplace
     * are fetched anonymously from GitHub, so a private one is reported as not found; a source on
     * any other host is not fetched here, and the report notes that it will be checked when the
     * marketplace actually synchronizes.
     *
     * Nothing is recorded on the Compliance API activity feed.
     *
     * For a worked example, see
     * [Validate marketplace content](/docs/en/manage-claude/plugins-api#validate-marketplace-content)
     * in the Plugins API guide.
     *
     * **Accepted credentials:** an Admin API key with the `read:plugins` or `write:plugins` scope;
     * `read:org_audit` and `read:compliance_org_data` do not grant it.
     *
     * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
     * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in
     * beta and is available to Claude Enterprise organizations only. It is not available to Claude
     * Platform (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
     */
    fun validateRepository(
        params: PluginMarketplaceValidateRepositoryParams
    ): CompletableFuture<BetaPluginMarketplaceValidationReport> =
        validateRepository(params, RequestOptions.none())

    /** @see validateRepository */
    fun validateRepository(
        params: PluginMarketplaceValidateRepositoryParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaPluginMarketplaceValidationReport>

    /**
     * A view of [PluginMarketplaceServiceAsync] that provides access to raw HTTP responses for each
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
        ): PluginMarketplaceServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugin_marketplaces/{marketplace_id}?beta=true`, but is otherwise the
         * same as [PluginMarketplaceServiceAsync.retrieve].
         */
        fun retrieve(
            marketplaceId: String
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            marketplaceId: String,
            params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            retrieve(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            marketplaceId: String,
            params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            retrieve(marketplaceId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: PluginMarketplaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>>

        /** @see retrieve */
        fun retrieve(
            params: PluginMarketplaceRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            marketplaceId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/{marketplace_id}?beta=true`, but is otherwise the
         * same as [PluginMarketplaceServiceAsync.update].
         */
        fun update(
            marketplaceId: String,
            params: PluginMarketplaceUpdateParams,
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            update(marketplaceId, params, RequestOptions.none())

        /** @see update */
        fun update(
            marketplaceId: String,
            params: PluginMarketplaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            update(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

        /** @see update */
        fun update(
            params: PluginMarketplaceUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            params: PluginMarketplaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplace>>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/plugin_marketplaces?beta=true`,
         * but is otherwise the same as [PluginMarketplaceServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<PluginMarketplaceListPageAsync>> =
            list(PluginMarketplaceListParams.none())

        /** @see list */
        fun list(
            params: PluginMarketplaceListParams = PluginMarketplaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<PluginMarketplaceListPageAsync>>

        /** @see list */
        fun list(
            params: PluginMarketplaceListParams = PluginMarketplaceListParams.none()
        ): CompletableFuture<HttpResponseFor<PluginMarketplaceListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<PluginMarketplaceListPageAsync>> =
            list(PluginMarketplaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/validate_archive?beta=true`, but is otherwise the
         * same as [PluginMarketplaceServiceAsync.validateArchive].
         */
        fun validateArchive(
            params: PluginMarketplaceValidateArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplaceValidationReport>> =
            validateArchive(params, RequestOptions.none())

        /** @see validateArchive */
        fun validateArchive(
            params: PluginMarketplaceValidateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplaceValidationReport>>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/validate_repository?beta=true`, but is otherwise
         * the same as [PluginMarketplaceServiceAsync.validateRepository].
         */
        fun validateRepository(
            params: PluginMarketplaceValidateRepositoryParams
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplaceValidationReport>> =
            validateRepository(params, RequestOptions.none())

        /** @see validateRepository */
        fun validateRepository(
            params: PluginMarketplaceValidateRepositoryParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaPluginMarketplaceValidationReport>>
    }
}
