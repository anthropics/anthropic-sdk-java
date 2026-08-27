// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.skills

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.versions.BetaDeletedSkillVersion
import com.anthropic.models.beta.skills.versions.BetaSkillVersion
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionDownloadParams
import com.anthropic.models.beta.skills.versions.VersionListPage
import com.anthropic.models.beta.skills.versions.VersionListParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface VersionService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService

    /** Create Skill Version */
    fun create(skillId: String, params: VersionCreateParams): BetaSkillVersion =
        create(skillId, params, RequestOptions.none())

    /** @see create */
    fun create(
        skillId: String,
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkillVersion = create(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see create */
    fun create(params: VersionCreateParams): BetaSkillVersion =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkillVersion

    /** Get Skill Version */
    fun retrieve(version: String, params: VersionRetrieveParams): BetaSkillVersion =
        retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkillVersion = retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: VersionRetrieveParams): BetaSkillVersion =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaSkillVersion

    /** List Skill Versions */
    fun list(skillId: String): VersionListPage = list(skillId, VersionListParams.none())

    /** @see list */
    fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPage = list(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see list */
    fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
    ): VersionListPage = list(skillId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: VersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPage

    /** @see list */
    fun list(params: VersionListParams): VersionListPage = list(params, RequestOptions.none())

    /** @see list */
    fun list(skillId: String, requestOptions: RequestOptions): VersionListPage =
        list(skillId, VersionListParams.none(), requestOptions)

    /** Delete Skill Version */
    fun delete(version: String, params: VersionDeleteParams): BetaDeletedSkillVersion =
        delete(version, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        version: String,
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedSkillVersion = delete(params.toBuilder().version(version).build(), requestOptions)

    /** @see delete */
    fun delete(params: VersionDeleteParams): BetaDeletedSkillVersion =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedSkillVersion

    /** Download a skill version's content as a zip archive. */
    @MustBeClosed
    fun download(version: String, params: VersionDownloadParams): HttpResponse =
        download(version, params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(
        version: String,
        params: VersionDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse = download(params.toBuilder().version(version).build(), requestOptions)

    /** @see download */
    @MustBeClosed
    fun download(params: VersionDownloadParams): HttpResponse =
        download(params, RequestOptions.none())

    /** @see download */
    @MustBeClosed
    fun download(
        params: VersionDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** A view of [VersionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/skills/{skill_id}/versions?beta=true`, but is
         * otherwise the same as [VersionService.create].
         */
        @MustBeClosed
        fun create(
            skillId: String,
            params: VersionCreateParams,
        ): HttpResponseFor<BetaSkillVersion> = create(skillId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            skillId: String,
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkillVersion> =
            create(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: VersionCreateParams): HttpResponseFor<BetaSkillVersion> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkillVersion>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions/{version}?beta=true`,
         * but is otherwise the same as [VersionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): HttpResponseFor<BetaSkillVersion> = retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkillVersion> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: VersionRetrieveParams): HttpResponseFor<BetaSkillVersion> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaSkillVersion>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions?beta=true`, but is
         * otherwise the same as [VersionService.list].
         */
        @MustBeClosed
        fun list(skillId: String): HttpResponseFor<VersionListPage> =
            list(skillId, VersionListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPage> =
            list(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
        ): HttpResponseFor<VersionListPage> = list(skillId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: VersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: VersionListParams): HttpResponseFor<VersionListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionListPage> =
            list(skillId, VersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/skills/{skill_id}/versions/{version}?beta=true`, but is otherwise the same as
         * [VersionService.delete].
         */
        @MustBeClosed
        fun delete(
            version: String,
            params: VersionDeleteParams,
        ): HttpResponseFor<BetaDeletedSkillVersion> = delete(version, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            version: String,
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedSkillVersion> =
            delete(params.toBuilder().version(version).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(params: VersionDeleteParams): HttpResponseFor<BetaDeletedSkillVersion> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedSkillVersion>

        /**
         * Returns a raw HTTP response for `get
         * /v1/skills/{skill_id}/versions/{version}/content?beta=true`, but is otherwise the same as
         * [VersionService.download].
         */
        @MustBeClosed
        fun download(version: String, params: VersionDownloadParams): HttpResponse =
            download(version, params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(
            version: String,
            params: VersionDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse = download(params.toBuilder().version(version).build(), requestOptions)

        /** @see download */
        @MustBeClosed
        fun download(params: VersionDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /** @see download */
        @MustBeClosed
        fun download(
            params: VersionDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse
    }
}
