package com.anthropic.models.beta.organization.claudetag.slackworkspaces

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.claudetag.SlackWorkspaceService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see SlackWorkspaceService.list */
class SlackWorkspaceListPage
private constructor(
    private val service: SlackWorkspaceService,
    private val params: SlackWorkspaceListParams,
    private val response: SlackWorkspaceListPageResponse,
) : Page<BetaClaudeTagSlackWorkspace> {

    /**
     * Delegates to [SlackWorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see SlackWorkspaceListPageResponse.data
     */
    fun data(): List<BetaClaudeTagSlackWorkspace> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [SlackWorkspaceListPageResponse], but gracefully handles missing data.
     *
     * @see SlackWorkspaceListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaClaudeTagSlackWorkspace> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): SlackWorkspaceListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): SlackWorkspaceListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaClaudeTagSlackWorkspace> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): SlackWorkspaceListParams = params

    /** The response that this page was parsed from. */
    fun response(): SlackWorkspaceListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [SlackWorkspaceListPage].
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

    /** A builder for [SlackWorkspaceListPage]. */
    class Builder internal constructor() {

        private var service: SlackWorkspaceService? = null
        private var params: SlackWorkspaceListParams? = null
        private var response: SlackWorkspaceListPageResponse? = null

        @JvmSynthetic
        internal fun from(slackWorkspaceListPage: SlackWorkspaceListPage) = apply {
            service = slackWorkspaceListPage.service
            params = slackWorkspaceListPage.params
            response = slackWorkspaceListPage.response
        }

        fun service(service: SlackWorkspaceService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: SlackWorkspaceListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: SlackWorkspaceListPageResponse) = apply { this.response = response }

        /**
         * Returns an immutable instance of [SlackWorkspaceListPage].
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
        fun build(): SlackWorkspaceListPage =
            SlackWorkspaceListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SlackWorkspaceListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "SlackWorkspaceListPage{service=$service, params=$params, response=$response}"
}
