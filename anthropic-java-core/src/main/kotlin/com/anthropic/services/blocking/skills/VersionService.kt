// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.skills

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.skills.versions.DeletedSkillVersion
import com.anthropic.models.skills.versions.SkillVersion
import com.anthropic.models.skills.versions.VersionCreateParams
import com.anthropic.models.skills.versions.VersionDeleteParams
import com.anthropic.models.skills.versions.VersionListPage
import com.anthropic.models.skills.versions.VersionListParams
import com.anthropic.models.skills.versions.VersionRetrieveParams
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
    fun create(skillId: String, params: VersionCreateParams): SkillVersion =
        create(skillId, params, RequestOptions.none())

    /** @see create */
    fun create(
        skillId: String,
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillVersion = create(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see create */
    fun create(params: VersionCreateParams): SkillVersion = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillVersion

    /** Get Skill Version */
    fun retrieve(version: String, params: VersionRetrieveParams): SkillVersion =
        retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillVersion = retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: VersionRetrieveParams): SkillVersion =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): SkillVersion

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
    fun delete(version: String, params: VersionDeleteParams): DeletedSkillVersion =
        delete(version, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        version: String,
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedSkillVersion = delete(params.toBuilder().version(version).build(), requestOptions)

    /** @see delete */
    fun delete(params: VersionDeleteParams): DeletedSkillVersion =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedSkillVersion

    /** A view of [VersionService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/skills/{skill_id}/versions`, but is otherwise
         * the same as [VersionService.create].
         */
        @MustBeClosed
        fun create(skillId: String, params: VersionCreateParams): HttpResponseFor<SkillVersion> =
            create(skillId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            skillId: String,
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillVersion> =
            create(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: VersionCreateParams): HttpResponseFor<SkillVersion> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillVersion>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions/{version}`, but is
         * otherwise the same as [VersionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): HttpResponseFor<SkillVersion> = retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillVersion> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: VersionRetrieveParams): HttpResponseFor<SkillVersion> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<SkillVersion>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions`, but is otherwise
         * the same as [VersionService.list].
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
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}/versions/{version}`, but is
         * otherwise the same as [VersionService.delete].
         */
        @MustBeClosed
        fun delete(
            version: String,
            params: VersionDeleteParams,
        ): HttpResponseFor<DeletedSkillVersion> = delete(version, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            version: String,
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedSkillVersion> =
            delete(params.toBuilder().version(version).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(params: VersionDeleteParams): HttpResponseFor<DeletedSkillVersion> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedSkillVersion>
    }
}
