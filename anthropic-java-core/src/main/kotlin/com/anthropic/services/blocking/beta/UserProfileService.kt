package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.userprofiles.BetaDeletedUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfile
import com.anthropic.models.beta.userprofiles.BetaUserProfileEnrollmentUrl
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileDeleteParams
import com.anthropic.models.beta.userprofiles.UserProfileListPage
import com.anthropic.models.beta.userprofiles.UserProfileListParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import com.anthropic.services.blocking.beta.userprofiles.ActionUrlService
import com.anthropic.services.blocking.beta.userprofiles.ProviderService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

/**
 * Register the end customers that your platform serves, so each request can name who it's for. To
 * get started, see the
 * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles).
 */
interface UserProfileService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): UserProfileService

    fun actionUrls(): ActionUrlService

    fun providers(): ProviderService

    /**
     * Register one of your end customers and get the ID to send on the requests you make for them.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#create-a-profile-for-each-end-customer)
     * for what to send.
     */
    fun create(): BetaUserProfile = create(UserProfileCreateParams.none())

    /** @see create */
    fun create(
        params: UserProfileCreateParams = UserProfileCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see create */
    fun create(params: UserProfileCreateParams = UserProfileCreateParams.none()): BetaUserProfile =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): BetaUserProfile =
        create(UserProfileCreateParams.none(), requestOptions)

    /**
     * Get a user profile by ID to check the details stored for it.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles) for
     * how to set up and manage profiles.
     */
    fun retrieve(userProfileId: String): BetaUserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile =
        retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        userProfileId: String,
        params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
    ): BetaUserProfile = retrieve(userProfileId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: UserProfileRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see retrieve */
    fun retrieve(params: UserProfileRetrieveParams): BetaUserProfile =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(userProfileId: String, requestOptions: RequestOptions): BetaUserProfile =
        retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

    /**
     * Change the details of a user profile, leaving the fields that you don't send as they are.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#update-a-profile)
     * for how each field is updated.
     */
    fun update(userProfileId: String): BetaUserProfile =
        update(userProfileId, UserProfileUpdateParams.none())

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile =
        update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see update */
    fun update(
        userProfileId: String,
        params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
    ): BetaUserProfile = update(userProfileId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: UserProfileUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfile

    /** @see update */
    fun update(params: UserProfileUpdateParams): BetaUserProfile =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(userProfileId: String, requestOptions: RequestOptions): BetaUserProfile =
        update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

    /**
     * List the user profiles in your organization, newest first by default.
     *
     * See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles) for
     * how to set up and manage profiles.
     */
    fun list(): UserProfileListPage = list(UserProfileListParams.none())

    /** @see list */
    fun list(
        params: UserProfileListParams = UserProfileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): UserProfileListPage

    /** @see list */
    fun list(params: UserProfileListParams = UserProfileListParams.none()): UserProfileListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): UserProfileListPage =
        list(UserProfileListParams.none(), requestOptions)

    /**
     * Delete a user profile, for example when the end customer closes their account.
     *
     * Stop sending the profile's ID first. See the
     * [User profiles guide](https://platform.claude.com/docs/en/manage-claude/user-profiles#delete-a-profile)
     * for what happens after a delete.
     */
    fun delete(userProfileId: String): BetaDeletedUserProfile =
        delete(userProfileId, UserProfileDeleteParams.none())

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedUserProfile =
        delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see delete */
    fun delete(
        userProfileId: String,
        params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
    ): BetaDeletedUserProfile = delete(userProfileId, params, RequestOptions.none())

    /** @see delete */
    fun delete(
        params: UserProfileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaDeletedUserProfile

    /** @see delete */
    fun delete(params: UserProfileDeleteParams): BetaDeletedUserProfile =
        delete(params, RequestOptions.none())

    /** @see delete */
    fun delete(userProfileId: String, requestOptions: RequestOptions): BetaDeletedUserProfile =
        delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

    /**
     * Create a link that sends the profile's end customer to the Verification Portal from Claude
     * Platform on AWS.
     *
     * On the Claude API, create an action URL instead. See
     * [Send an end customer a link to the Verification Portal](https://platform.claude.com/docs/en/manage-claude/user-profiles-verification-portal)
     * for when to create the link and what the end customer sees.
     */
    fun createEnrollmentUrl(userProfileId: String): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        params: UserProfileCreateEnrollmentUrlParams = UserProfileCreateEnrollmentUrlParams.none(),
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(userProfileId, params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaUserProfileEnrollmentUrl

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        params: UserProfileCreateEnrollmentUrlParams
    ): BetaUserProfileEnrollmentUrl = createEnrollmentUrl(params, RequestOptions.none())

    /** @see createEnrollmentUrl */
    fun createEnrollmentUrl(
        userProfileId: String,
        requestOptions: RequestOptions,
    ): BetaUserProfileEnrollmentUrl =
        createEnrollmentUrl(
            userProfileId,
            UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions,
        )

    /**
     * A view of [UserProfileService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): UserProfileService.WithRawResponse

        fun actionUrls(): ActionUrlService.WithRawResponse

        fun providers(): ProviderService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles`, but is otherwise the same as
         * [UserProfileService.create].
         */
        @MustBeClosed
        fun create(): HttpResponseFor<BetaUserProfile> = create(UserProfileCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see create */
        @MustBeClosed
        fun create(
            params: UserProfileCreateParams = UserProfileCreateParams.none()
        ): HttpResponseFor<BetaUserProfile> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<BetaUserProfile> =
            create(UserProfileCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.retrieve].
         */
        @MustBeClosed
        fun retrieve(userProfileId: String): HttpResponseFor<BetaUserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile> =
            retrieve(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            params: UserProfileRetrieveParams = UserProfileRetrieveParams.none(),
        ): HttpResponseFor<BetaUserProfile> = retrieve(userProfileId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: UserProfileRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: UserProfileRetrieveParams): HttpResponseFor<BetaUserProfile> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> =
            retrieve(userProfileId, UserProfileRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.update].
         */
        @MustBeClosed
        fun update(userProfileId: String): HttpResponseFor<BetaUserProfile> =
            update(userProfileId, UserProfileUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile> =
            update(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            params: UserProfileUpdateParams = UserProfileUpdateParams.none(),
        ): HttpResponseFor<BetaUserProfile> = update(userProfileId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: UserProfileUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfile>

        /** @see update */
        @MustBeClosed
        fun update(params: UserProfileUpdateParams): HttpResponseFor<BetaUserProfile> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfile> =
            update(userProfileId, UserProfileUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/user_profiles`, but is otherwise the same as
         * [UserProfileService.list].
         */
        @MustBeClosed
        fun list(): HttpResponseFor<UserProfileListPage> = list(UserProfileListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserProfileListParams = UserProfileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<UserProfileListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: UserProfileListParams = UserProfileListParams.none()
        ): HttpResponseFor<UserProfileListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<UserProfileListPage> =
            list(UserProfileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/user_profiles/{user_profile_id}`, but is
         * otherwise the same as [UserProfileService.delete].
         */
        @MustBeClosed
        fun delete(userProfileId: String): HttpResponseFor<BetaDeletedUserProfile> =
            delete(userProfileId, UserProfileDeleteParams.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedUserProfile> =
            delete(params.toBuilder().userProfileId(userProfileId).build(), requestOptions)

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            params: UserProfileDeleteParams = UserProfileDeleteParams.none(),
        ): HttpResponseFor<BetaDeletedUserProfile> =
            delete(userProfileId, params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            params: UserProfileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaDeletedUserProfile>

        /** @see delete */
        @MustBeClosed
        fun delete(params: UserProfileDeleteParams): HttpResponseFor<BetaDeletedUserProfile> =
            delete(params, RequestOptions.none())

        /** @see delete */
        @MustBeClosed
        fun delete(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaDeletedUserProfile> =
            delete(userProfileId, UserProfileDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/user_profiles/{user_profile_id}/enrollment_url`, but is otherwise the same as
         * [UserProfileService.createEnrollmentUrl].
         */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(userProfileId, UserProfileCreateEnrollmentUrlParams.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(
                params.toBuilder().userProfileId(userProfileId).build(),
                requestOptions,
            )

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            params: UserProfileCreateEnrollmentUrlParams =
                UserProfileCreateEnrollmentUrlParams.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(userProfileId, params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl>

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            params: UserProfileCreateEnrollmentUrlParams
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(params, RequestOptions.none())

        /** @see createEnrollmentUrl */
        @MustBeClosed
        fun createEnrollmentUrl(
            userProfileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaUserProfileEnrollmentUrl> =
            createEnrollmentUrl(
                userProfileId,
                UserProfileCreateEnrollmentUrlParams.none(),
                requestOptions,
            )
    }
}
