package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.AutoPager
import com.anthropic.core.Page
import com.anthropic.core.checkRequired
import com.anthropic.services.blocking.beta.organization.plugins.InstallationSettingService
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** @see InstallationSettingService.list */
class InstallationSettingListPage
private constructor(
    private val service: InstallationSettingService,
    private val params: InstallationSettingListParams,
    private val response: InstallationSettingListPageResponse,
) : Page<BetaPluginInstallationSetting> {

    /**
     * Delegates to [InstallationSettingListPageResponse], but gracefully handles missing data.
     *
     * @see InstallationSettingListPageResponse.data
     */
    fun data(): List<BetaPluginInstallationSetting> =
        response._data().getOptional("data").getOrNull() ?: emptyList()

    /**
     * Delegates to [InstallationSettingListPageResponse], but gracefully handles missing data.
     *
     * @see InstallationSettingListPageResponse.nextPage
     */
    fun nextPageRaw(): Optional<String> = response._nextPage().getOptional("next_page")

    override fun items(): List<BetaPluginInstallationSetting> = data()

    override fun hasNextPage(): Boolean = nextPageRaw().isPresent

    fun nextPageParams(): InstallationSettingListParams {
        val nextCursor =
            nextPageRaw().getOrNull()
                ?: throw IllegalStateException("Cannot construct next page params")
        return params.toBuilder().page(nextCursor).build()
    }

    override fun nextPage(): InstallationSettingListPage = service.list(nextPageParams())

    fun autoPager(): AutoPager<BetaPluginInstallationSetting> = AutoPager.from(this)

    /** The parameters that were used to request this page. */
    fun params(): InstallationSettingListParams = params

    /** The response that this page was parsed from. */
    fun response(): InstallationSettingListPageResponse = response

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [InstallationSettingListPage].
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

    /** A builder for [InstallationSettingListPage]. */
    class Builder internal constructor() {

        private var service: InstallationSettingService? = null
        private var params: InstallationSettingListParams? = null
        private var response: InstallationSettingListPageResponse? = null

        @JvmSynthetic
        internal fun from(installationSettingListPage: InstallationSettingListPage) = apply {
            service = installationSettingListPage.service
            params = installationSettingListPage.params
            response = installationSettingListPage.response
        }

        fun service(service: InstallationSettingService) = apply { this.service = service }

        /** The parameters that were used to request this page. */
        fun params(params: InstallationSettingListParams) = apply { this.params = params }

        /** The response that this page was parsed from. */
        fun response(response: InstallationSettingListPageResponse) = apply {
            this.response = response
        }

        /**
         * Returns an immutable instance of [InstallationSettingListPage].
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
        fun build(): InstallationSettingListPage =
            InstallationSettingListPage(
                checkRequired("service", service),
                checkRequired("params", params),
                checkRequired("response", response),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is InstallationSettingListPage &&
            service == other.service &&
            params == other.params &&
            response == other.response
    }

    override fun hashCode(): Int = Objects.hash(service, params, response)

    override fun toString() =
        "InstallationSettingListPage{service=$service, params=$params, response=$response}"
}
