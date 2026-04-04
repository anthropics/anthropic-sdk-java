// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.skills

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionCreateResponse
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionDeleteResponse
import com.anthropic.models.beta.skills.versions.VersionListPageAsync
import com.anthropic.models.beta.skills.versions.VersionListParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveResponse
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
    suspend fun create(skillId: String): VersionCreateResponse =
        create(skillId, VersionCreateParams.none())

    /** @see create */
    suspend fun create(
        skillId: String,
        params: VersionCreateParams = VersionCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionCreateResponse =
        create(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see create */
    suspend fun create(
        skillId: String,
        params: VersionCreateParams = VersionCreateParams.none(),
    ): VersionCreateResponse = create(skillId, params, RequestOptions.none())

    /** @see create */
    suspend fun create(
        params: VersionCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionCreateResponse

    /** @see create */
    suspend fun create(params: VersionCreateParams): VersionCreateResponse =
        create(params, RequestOptions.none())

    /** @see create */
    suspend fun create(
        skillId: String,
        requestOptions: RequestOptions,
    ): VersionCreateResponse =
        create(skillId, VersionCreateParams.none(), requestOptions)

    /** Get Skill Version */
    suspend fun retrieve(
        version: String,
        params: VersionRetrieveParams,
    ): VersionRetrieveResponse = retrieve(version, params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        version: String,
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionRetrieveResponse =
        retrieve(params.toBuilder().version(version).build(), requestOptions)

    /** @see retrieve */
    suspend fun retrieve(params: VersionRetrieveParams): VersionRetrieveResponse =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    suspend fun retrieve(
        params: VersionRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionRetrieveResponse

    /** List Skill Versions */
    suspend fun list(skillId: String): VersionListPageAsync =
        list(skillId, VersionListParams.none())

    /** @see list */
    suspend fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPageAsync =
        list(params.toBuilder().skillId(skillId).build(), requestOptions)

    /** @see list */
    suspend fun list(
        skillId: String,
        params: VersionListParams = VersionListParams.none(),
    ): VersionListPageAsync = list(skillId, params, RequestOptions.none())

    /** @see list */
    suspend fun list(
        params: VersionListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionListPageAsync

    /** @see list */
    suspend fun list(params: VersionListParams): VersionListPageAsync =
        list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(
        skillId: String,
        requestOptions: RequestOptions,
    ): VersionListPageAsync =
        list(skillId, VersionListParams.none(), requestOptions)

    /** Delete Skill Version */
    suspend fun delete(
        version: String,
        params: VersionDeleteParams,
    ): VersionDeleteResponse = delete(version, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        version: String,
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionDeleteResponse =
        delete(params.toBuilder().version(version).build(), requestOptions)

    /** @see delete */
    suspend fun delete(params: VersionDeleteParams): VersionDeleteResponse =
        delete(params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        params: VersionDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): VersionDeleteResponse

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
         * Returns a raw HTTP response for `post /v1/skills/{skill_id}/versions?beta=true`, but is
         * otherwise the same as [VersionServiceAsync.create].
         */
        suspend fun create(skillId: String): HttpResponseFor<VersionCreateResponse> =
            create(skillId, VersionCreateParams.none())

        /** @see create */
        suspend fun create(
            skillId: String,
            params: VersionCreateParams = VersionCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionCreateResponse> =
            create(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see create */
        suspend fun create(
            skillId: String,
            params: VersionCreateParams = VersionCreateParams.none(),
        ): HttpResponseFor<VersionCreateResponse> =
            create(skillId, params, RequestOptions.none())

        /** @see create */
        suspend fun create(
            params: VersionCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionCreateResponse>

        /** @see create */
        suspend fun create(
            params: VersionCreateParams
        ): HttpResponseFor<VersionCreateResponse> =
            create(params, RequestOptions.none())

        /** @see create */
        suspend fun create(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionCreateResponse> =
            create(skillId, VersionCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions/{version}?beta=true`,
         * but is otherwise the same as [VersionServiceAsync.retrieve].
         */
        suspend fun retrieve(
            version: String,
            params: VersionRetrieveParams,
        ): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(version, params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            version: String,
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(params.toBuilder().version(version).build(), requestOptions)

        /** @see retrieve */
        suspend fun retrieve(
            params: VersionRetrieveParams
        ): HttpResponseFor<VersionRetrieveResponse> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        suspend fun retrieve(
            params: VersionRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionRetrieveResponse>

        /**
         * Returns a raw HTTP response for `get /v1/skills/{skill_id}/versions?beta=true`, but is
         * otherwise the same as [VersionServiceAsync.list].
         */
        suspend fun list(skillId: String): HttpResponseFor<VersionListPageAsync> =
            list(skillId, VersionListParams.none())

        /** @see list */
        suspend fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPageAsync> =
            list(params.toBuilder().skillId(skillId).build(), requestOptions)

        /** @see list */
        suspend fun list(
            skillId: String,
            params: VersionListParams = VersionListParams.none(),
        ): HttpResponseFor<VersionListPageAsync> =
            list(skillId, params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            params: VersionListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionListPageAsync>

        /** @see list */
        suspend fun list(
            params: VersionListParams
        ): HttpResponseFor<VersionListPageAsync> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            skillId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<VersionListPageAsync> =
            list(skillId, VersionListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete
         * /v1/skills/{skill_id}/versions/{version}?beta=true`, but is otherwise the same as
         * [VersionServiceAsync.delete].
         */
        suspend fun delete(
            version: String,
            params: VersionDeleteParams,
        ): HttpResponseFor<VersionDeleteResponse> =
            delete(version, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            version: String,
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionDeleteResponse> =
            delete(params.toBuilder().version(version).build(), requestOptions)

        /** @see delete */
        suspend fun delete(
            params: VersionDeleteParams
        ): HttpResponseFor<VersionDeleteResponse> =
            delete(params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            params: VersionDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<VersionDeleteResponse>
    }
}
