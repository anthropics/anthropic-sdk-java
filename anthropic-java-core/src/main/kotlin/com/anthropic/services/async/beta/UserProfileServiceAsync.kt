package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.BetaDeletedUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfileEnrollmentUrl
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileDeleteParams
import com.anthropic.models.beta.userprofiles.UserProfileListPageAsync
import com.anthropic.models.beta.userprofiles.UserProfileListParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import com.anthropic.services.async.beta.userprofiles.ActionUrlServiceAsync
import com.anthropic.services.async.beta.userprofiles.ProviderServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/**
 * Register the end customers that your platform serves, so each request can name who it's for. To
 * get started, see the
 * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles).
 */
interface UserProfileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileServiceAsync

    fun actionUrls(): ActionUrlServiceAsync

    fun providers(): ProviderServiceAsync

    /**
     * Register one of your end customers and get the ID to send on the requests you make for them.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#create-a-profile-for-each-end-customer)
     * for what to send.
     */
    fun create(): CompletableFuture<BetaUserProfile> = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none()
    ): CompletableFuture<BetaUserProfile> = create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<BetaUserProfile> =
        create(UserProfileCreateParams.none(), requestOptions)

    /**
     * Get a user profile by ID to check the details stored for it.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles) for
     * how to set up and manage profiles.
     */
    fun retrieve(userProfileId: String): CompletableFuture<BetaUserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile> =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): CompletableFuture<BetaUserProfile> = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): CompletableFuture<BetaUserProfile> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfile> =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /**
     * Change the details of a user profile, leaving the fields that you don't send as they are.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#update-a-profile)
     * for how each field is updated.
     */
    fun update(userProfileId: String): CompletableFuture<BetaUserProfile> =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile> =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): CompletableFuture<BetaUserProfile> = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfile>

    /** @see update */
    fun update(params: UserProfileUpdateParams): CompletableFuture<BetaUserProfile> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfile> =
        update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

    /**
     * List the user profiles in your organization, newest first by default.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles) for
     * how to set up and manage profiles.
     */
    fun list(): CompletableFuture<UserProfileListPageAsync> = list(UserProfileListParams.none())

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<UserProfileListPageAsync>

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none()
    ): CompletableFuture<UserProfileListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<UserProfileListPageAsync> =
        list(UserProfileListParams.none(), requestOptions)

    /**
     * Delete a user profile, for example when the end customer closes their account.
     *
     * Stop sending the profile's ID first. See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#delete-a-profile)
     * for what happens after a delete.
     */
    fun delete(userProfileId: String): CompletableFuture<BetaDeletedUserProfile> =
        delete(userProfileId, UserProfileDeleteParams.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedUserProfile> =
        delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
    ): CompletableFuture<BetaDeletedUserProfile> =
        delete(userProfileId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaDeletedUserProfile>

    /** @see delete */
    fun delete(params: UserProfileDeleteParams): CompletableFuture<BetaDeletedUserProfile> =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaDeletedUserProfile> =
        delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

    /**
     * Create a link that sends the profile's end customer to the Verification Portal from Claude
     * Platform on AWS.
     *
     * On the Claude API, create an action URL instead. See
     * [Send an end customer a link to the Verification Portal](https://platform.claude.com/docs/en/manage-claude/user-profiles-verification-portal)
     * for when to create the link and what the end customer sees.
     */
    fun createEnrollmentUrl(
        userProfileId: String
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(userProfileId, params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaUserProfileEnrollmentUrl>

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaUserProfileEnrollmentUrl> =
        createEnrollmentUrl(
            userProfileId,
            UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions,
        )

    /**
     * A view of [UserProfileServiceAsync] that provides access to raw HTTP responses for each
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
        ): UserProfileServiceAsync.WithRawResponse

        fun actionUrls(): ActionUrlServiceAsync.WithRawResponse

        fun providers(): ProviderServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles`, but is otherwise the same as
         * [UserProfileServiceAsync.create].
         */
        fun create(): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(UserProfileCreateParams.none())

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see create */
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.retrieve].
         */
        fun retrieve(userProfileId: String): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see retrieve */
        fun retrieve(
            params: UserProfileRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.update].
         */
        fun update(userProfileId: String): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>>

        /** @see update */
        fun update(
            params: UserProfileUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfile>> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles`, but is otherwise the same as
         * [UserProfileServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(UserProfileListParams.none())

        /** @see list */
        fun list(
            params: UserProfileListParams = UserProfileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>>

        /** @see list */
        fun list(
            params: UserProfileListParams = UserProfileListParams.none()
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<UserProfileListPageAsync>> =
            list(UserProfileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileServiceAsync.delete].
         */
        fun delete(
            userProfileId: String
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>> =
            delete(userProfileId, UserProfileDeleteParams.none())

        /** @see delete */
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>> =
            delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see delete */
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>> =
            delete(userProfileId, params, RequestOptions.none())

        /** @see delete */
        fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>>

        /** @see delete */
        fun delete(
            params: UserProfileDeleteParams
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>> =
            delete(params, RequestOptions.none())

        /** @see delete */
        fun delete(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaDeletedUserProfile>> =
            delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/enrollment_url`, but is otherwise the same as
         * [UserProfileServiceAsync.createEnrollmentUrl].
         */
        fun createEnrollmentUrl(
            userProfileId: String
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(
                params.toBuilder().userProfileId(userProfileId).build(),
                requestOptions,
            )

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(userProfileId, params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>>

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        fun createEnrollmentUrl(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaUserProfileEnrollmentUrl>> =
            createEnrollmentUrl(
                userProfileId,
                UserProfileCreateEnrollmentUrlParams.none(),
                requestOptions,
            )
    }
}
