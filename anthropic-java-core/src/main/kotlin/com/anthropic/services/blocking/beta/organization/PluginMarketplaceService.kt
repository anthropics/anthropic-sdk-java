package com.anthropic.services.blocking.beta.organization

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplace
import com.anthropic.models.beta.organization.pluginmarketplaces.BetaPluginMarketplaceValidationReport
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListPage
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceListParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceRetrieveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceUpdateParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateArchiveParams
import com.anthropic.models.beta.organization.pluginmarketplaces.PluginMarketplaceValidateRepositoryParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface PluginMarketplaceService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): PluginMarketplaceService

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
    fun retrieve(marketplaceId: String): BetaPluginMarketplace =
        retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        marketplaceId: String,
        params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplace =
        retrieve(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        marketplaceId: String,
        params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
    ): BetaPluginMarketplace = retrieve(marketplaceId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: PluginMarketplaceRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplace

    /** @see retrieve */
    fun retrieve(params: PluginMarketplaceRetrieveParams): BetaPluginMarketplace =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(marketplaceId: String, requestOptions: RequestOptions): BetaPluginMarketplace =
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
    ): BetaPluginMarketplace = update(marketplaceId, params, RequestOptions.none())

    /** @see update */
    fun update(
        marketplaceId: String,
        params: PluginMarketplaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplace =
        update(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

    /** @see update */
    fun update(params: PluginMarketplaceUpdateParams): BetaPluginMarketplace =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        params: PluginMarketplaceUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplace

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
    fun list(): PluginMarketplaceListPage = list(PluginMarketplaceListParams.none())

    /** @see list */
    fun list(
        params: PluginMarketplaceListParams = PluginMarketplaceListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): PluginMarketplaceListPage

    /** @see list */
    fun list(
        params: PluginMarketplaceListParams = PluginMarketplaceListParams.none()
    ): PluginMarketplaceListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): PluginMarketplaceListPage =
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
    ): BetaPluginMarketplaceValidationReport = validateArchive(params, RequestOptions.none())

    /** @see validateArchive */
    fun validateArchive(
        params: PluginMarketplaceValidateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplaceValidationReport

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
    ): BetaPluginMarketplaceValidationReport = validateRepository(params, RequestOptions.none())

    /** @see validateRepository */
    fun validateRepository(
        params: PluginMarketplaceValidateRepositoryParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaPluginMarketplaceValidationReport

    /**
     * A view of [PluginMarketplaceService] that provides access to raw HTTP responses for each
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
        ): PluginMarketplaceService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/plugin_marketplaces/{marketplace_id}?beta=true`, but is otherwise the
         * same as [PluginMarketplaceService.retrieve].
         */
        @MustBeClosed
        fun retrieve(marketplaceId: String): HttpResponseFor<BetaPluginMarketplace> =
            retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            marketplaceId: String,
            params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplace> =
            retrieve(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            marketplaceId: String,
            params: PluginMarketplaceRetrieveParams = PluginMarketplaceRetrieveParams.none(),
        ): HttpResponseFor<BetaPluginMarketplace> =
            retrieve(marketplaceId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: PluginMarketplaceRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplace>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: PluginMarketplaceRetrieveParams
        ): HttpResponseFor<BetaPluginMarketplace> = retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            marketplaceId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaPluginMarketplace> =
            retrieve(marketplaceId, PluginMarketplaceRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/{marketplace_id}?beta=true`, but is otherwise the
         * same as [PluginMarketplaceService.update].
         */
        @MustBeClosed
        fun update(
            marketplaceId: String,
            params: PluginMarketplaceUpdateParams,
        ): HttpResponseFor<BetaPluginMarketplace> =
            update(marketplaceId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            marketplaceId: String,
            params: PluginMarketplaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplace> =
            update(params.toBuilder().marketplaceId(marketplaceId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(params: PluginMarketplaceUpdateParams): HttpResponseFor<BetaPluginMarketplace> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: PluginMarketplaceUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplace>

        /**
         * Returns a raw HTTP response for `get /v1/organizations/plugin_marketplaces?beta=true`,
         * but is otherwise the same as [PluginMarketplaceService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<PluginMarketplaceListPage> =
            list(PluginMarketplaceListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginMarketplaceListParams = PluginMarketplaceListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<PluginMarketplaceListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: PluginMarketplaceListParams = PluginMarketplaceListParams.none()
        ): HttpResponseFor<PluginMarketplaceListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<PluginMarketplaceListPage> =
            list(PluginMarketplaceListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/validate_archive?beta=true`, but is otherwise the
         * same as [PluginMarketplaceService.validateArchive].
         */
        @MustBeClosed
        fun validateArchive(
            params: PluginMarketplaceValidateArchiveParams
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport> =
            validateArchive(params, RequestOptions.none())

        /** @see validateArchive */
        @MustBeClosed
        fun validateArchive(
            params: PluginMarketplaceValidateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport>

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/plugin_marketplaces/validate_repository?beta=true`, but is otherwise
         * the same as [PluginMarketplaceService.validateRepository].
         */
        @MustBeClosed
        fun validateRepository(
            params: PluginMarketplaceValidateRepositoryParams
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport> =
            validateRepository(params, RequestOptions.none())

        /** @see validateRepository */
        @MustBeClosed
        fun validateRepository(
            params: PluginMarketplaceValidateRepositoryParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaPluginMarketplaceValidationReport>
    }
}
