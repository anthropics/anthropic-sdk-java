package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.claudetag.SlackChannelService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see SlackChannelService.list */
class SlackChannelListPage
private constructor(
    private val service: SlackChannelService,
    private val params: SlackChannelListParams,
    private val response: SlackChannelListPageResponse,
) : Page<BetaClaudeTagSlackChannel> {

    /**
     * Delegates to [SlackChannelListPageResponse], but gracefully handles missing data.
     *
     * @see SlackChannelListPageResponse.data
     */
    fun data(): List<BetaClaudeTagSlackChannel> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SlackChannelListPageResponse], but gracefully handles missing data.
     *
     * @see SlackChannelListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagSlackChannel> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SlackChannelListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): SlackChannelListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaClaudeTagSlackChannel> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): SlackChannelListParams = params

    /** The response that this page was parsed from. */
    fun response(): SlackChannelListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackChannelListPage].
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SlackChannelListPage]. */
    class Builder internal constructor() {

        private var service: SlackChannelService? = null
        private var params: SlackChannelListParams? = null
        private var response: SlackChannelListPageResponse? = null

        @JvmSynthetic
        internal fun from(slackChannelListPage: SlackChannelListPage) = apply {
            service = slackChannelListPage.service
            params = slackChannelListPage.params
            response = slackChannelListPage.response
        }

        fun service(service: SlackChannelService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: SlackChannelListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SlackChannelListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SlackChannelListPage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .service()
         * .params()
         * .response()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SlackChannelListPage =
            SlackChannelListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SlackChannelListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SlackChannelListPage{service=$service, params=$params, response=$response}"
}
