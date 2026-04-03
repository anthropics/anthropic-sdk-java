// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.skills

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionCreateResponse
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionDeleteResponse
import com.anthropic.models.beta.skills.versions.VersionListPage
import com.anthropic.models.beta.skills.versions.VersionListParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveResponse
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
    fun create(skillId: String): VersionCreateResponse = create(skillId, VersionCreateParams.none())

    /** @see create */
    fun create(
        skillId: String,
        params: VersionCreateParams = VersionCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionCreateResponse = create(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see create */
    fun create(
        skillId: String,
        params: VersionCreateParams = VersionCreateParams.none(),
    ): VersionCreateResponse = create(skillId, params, RequestOptions.none())

    /** @see create */
    fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionCreateResponse

    /** @see create */
    fun create(params: VersionCreateParams): VersionCreateResponse =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(skillId: String, requestOptions: RequestOptions): VersionCreateResponse =
        create(skillId, VersionCreateParams.none(), requestOptions)

    /** Get Skill Version */
    fun retrieve(version: String, params: VersionRetrieveParams): VersionRetrieveResponse =
        retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionRetrieveResponse =
        retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: VersionRetrieveParams): VersionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionRetrieveResponse

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
    fun delete(version: String, params: VersionDeleteParams): VersionDeleteResponse =
        delete(version, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        version: String,
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionDeleteResponse = delete(params.toBuilder().version(version).build(), requestOptions)

    /** @see delete */
    fun delete(params: VersionDeleteParams): VersionDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionDeleteResponse

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
        fun create(skillId: String): HttpResponseFor<VersionCreateResponse> =
            create(skillId, VersionCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            skillId: String,
            params: VersionCreateParams = VersionCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionCreateResponse> =
            create(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(
            skillId: String,
            params: VersionCreateParams = VersionCreateParams.none(),
        ): HttpResponseFor<VersionCreateResponse> = create(skillId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionCreateResponse>

        /** @see create */
        @MustBeClosed
        fun create(params: VersionCreateParams): HttpResponseFor<VersionCreateResponse> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionCreateResponse> =
            create(skillId, VersionCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions/{version}?beta=true`,
         * but is otherwise the same as [VersionService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: VersionRetrieveParams): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionRetrieveResponse>

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
        ): HttpResponseFor<VersionDeleteResponse> = delete(version, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            version: String,
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionDeleteResponse> =
            delete(params.toBuilder().version(version).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(params: VersionDeleteParams): HttpResponseFor<VersionDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionDeleteResponse>
    }
}
