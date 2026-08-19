// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.skills

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.skills.versions.DeletedSkillVersion
import com.anthropic.models.skills.versions.SkillVersion
import com.anthropic.models.skills.versions.VersionCreateParams
import com.anthropic.models.skills.versions.VersionDeleteParams
import com.anthropic.models.skills.versions.VersionListPageAsync
import com.anthropic.models.skills.versions.VersionListParams
import com.anthropic.models.skills.versions.VersionRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface VersionServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): VersionServiceAsync

    /** Create Skill Version */
    fun create(skillId: String, params: VersionCreateParams): CompletableFuture<SkillVersion> =
        create(skillId, params, RequestOptions.none())

    /** @see create */
    fun create(
        skillId: String,
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillVersion> =
        create(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see create */
    fun create(params: VersionCreateParams): CompletableFuture<SkillVersion> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillVersion>

    /** Get Skill Version */
    fun retrieve(version: String, params: VersionRetrieveParams): CompletableFuture<SkillVersion> =
        retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillVersion> =
        retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: VersionRetrieveParams): CompletableFuture<SkillVersion> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<SkillVersion>

    /** List Skill Versions */
    fun list(skillId: String): CompletableFuture<VersionListPageAsync> =
        list(skillId, VersionListParams.none())

    /** @see list */
    fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<VersionListPageAsync> =
        list(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see list */
    fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
    ): CompletableFuture<VersionListPageAsync> = list(skillId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: VersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<VersionListPageAsync>

    /** @see list */
    fun list(params: VersionListParams): CompletableFuture<VersionListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        skillId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<VersionListPageAsync> =
        list(skillId, VersionListParams.none(), requestOptions)

    /** Delete Skill Version */
    fun delete(
        version: String,
        params: VersionDeleteParams,
    ): CompletableFuture<DeletedSkillVersion> = delete(version, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        version: String,
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedSkillVersion> =
        delete(params.toBuilder().version(version).build(), requestOptions)

    /** @see delete */
    fun delete(params: VersionDeleteParams): CompletableFuture<DeletedSkillVersion> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<DeletedSkillVersion>

    /**
     * A view of [VersionServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): VersionServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/skills/{skill_id}/versions`, but is otherwise
         * the same as [VersionServiceAsync.create].
         */
        fun create(
            skillId: String,
            params: VersionCreateParams,
        ): CompletableFuture<HttpResponseFor<SkillVersion>> =
            create(skillId, params, RequestOptions.none())

        /** @see create */
        fun create(
            skillId: String,
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillVersion>> =
            create(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see create */
        fun create(params: VersionCreateParams): CompletableFuture<HttpResponseFor<SkillVersion>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillVersion>>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions/{version}`, but is
         * otherwise the same as [VersionServiceAsync.retrieve].
         */
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): CompletableFuture<HttpResponseFor<SkillVersion>> =
            retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillVersion>> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: VersionRetrieveParams
        ): CompletableFuture<HttpResponseFor<SkillVersion>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<SkillVersion>>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions`, but is otherwise
         * the same as [VersionServiceAsync.list].
         */
        fun list(skillId: String): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(skillId, VersionListParams.none())

        /** @see list */
        fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see list */
        fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(skillId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: VersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>>

        /** @see list */
        fun list(
            params: VersionListParams
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            skillId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<VersionListPageAsync>> =
            list(skillId, VersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/skills/{skill_id}/versions/{version}`, but is
         * otherwise the same as [VersionServiceAsync.delete].
         */
        fun delete(
            version: String,
            params: VersionDeleteParams,
        ): CompletableFuture<HttpResponseFor<DeletedSkillVersion>> =
            delete(version, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            version: String,
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedSkillVersion>> =
            delete(params.toBuilder().version(version).build(), requestOptions)

        /** @see delete */
        fun delete(
            params: VersionDeleteParams
        ): CompletableFuture<HttpResponseFor<DeletedSkillVersion>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<DeletedSkillVersion>>
    }
}
