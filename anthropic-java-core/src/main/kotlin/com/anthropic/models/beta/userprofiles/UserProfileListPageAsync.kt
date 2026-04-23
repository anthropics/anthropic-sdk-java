// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.AutoPagerAsync
import com.anthropic.core.PageAsync
import com.anthropic.core.checkRequired
import com.anthropic.services.async.beta.UserProfileServiceAsync
import java.util.Objects
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.jvm.optionals.getOrNull

/** @see UserProfileServiceAsync.list */
class UserProfileListPageAsync
private constructor(
    private val service: UserProfileServiceAsync,
    private val streamHandlerExecutor: Executor,
    private val params: UserProfileListParams,
    private val response: UserProfileListPageResponse,
) : PageAsync<BetaUserProfile> {

    /**
     * Delegates to [UserProfileListPageResponse], but gracefully handles missing data.
     *
     * @see UserProfileListPageResponse.data
     */
    fun data(): List<BetaUserProfile> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [UserProfileListPageResponse], but gracefully handles missing data.
     *
     * @see UserProfileListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaUserProfile> = data()

    override fun hasNextPage(): Boolean = items().isNotEmpty() && nextPageRaw().isPresent

    fun nextPageParams(): UserProfileListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): CompletableFuture<UserProfileListPageAsync> =
        service.list(nextPageParams())

    fun autoPager(): AutoPagerAsync<BetaUserProfile> =
        AutoPagerAsync.from(this, streamHandlerExecutor)

    /** The parameters that were used to request this page. */
    fun params(): UserProfileListParams = params

    /** The response that this page was parsed from. */
    fun response(): UserProfileListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserProfileListPageAsync].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileListPageAsync]. */
    class Builder internal constructor() {

        private var service: UserProfileServiceAsync? = null
        private var streamHandlerExecutor: Executor? = null
        private var params: UserProfileListParams? = null
        private var response: UserProfileListPageResponse? = null

        @JvmSynthetic
        internal fun from(userProfileListPageAsync: UserProfileListPageAsync) = apply {
            service = userProfileListPageAsync.service
            streamHandlerExecutor = userProfileListPageAsync.streamHandlerExecutor
            params = userProfileListPageAsync.params
            response = userProfileListPageAsync.response
        }

        fun service(service: UserProfileServiceAsync) = apply { this.service = service }

        fun streamHandlerExecutor(streamHandlerExecutor: Executor) = apply {
            this.streamHandlerExecutor = streamHandlerExecutor
        }

        /** The parameters that were used to request this page. */
        fun params(params: UserProfileListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: UserProfileListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [UserProfileListPageAsync].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .streamHandlerExecutor()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfileListPageAsync =
            UserProfileListPageAsync(
                checkRequired("service", service),
                checkRequired("streamHandlerExecutor", streamHandlerExecutor),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileListPageAsync &&
            service == other.service &&
            streamHandlerExecutor == other.streamHandlerExecutor &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, streamHandlerExecutor, params, response)

    override fun toString() =
        "UserProfileListPageAsync{service=$service, streamHandlerExecutor=$streamHandlerExecutor, params=$params, response=$response}"
}
