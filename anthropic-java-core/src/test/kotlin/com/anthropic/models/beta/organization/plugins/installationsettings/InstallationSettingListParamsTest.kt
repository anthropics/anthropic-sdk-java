package com.anthropic.models.beta.organization.plugins.installationsettings

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InstallationSettingListParamsTest {

    @Test
    fun create() {
        InstallationSettingListParams.builder()
            .pluginId("plugin_id")
            .limit(1L)
            .organizationId("organization_id")
            .page("page")
            .targetType(InstallationSettingListParams.TargetType.ORGANIZATION)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params = InstallationSettingListParams.builder().pluginId("plugin_id").build()

        assertThat(params._pathParam(0)).isEqualTo("plugin_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            InstallationSettingListParams.builder()
                .pluginId("plugin_id")
                .limit(1L)
                .organizationId("organization_id")
                .page("page")
                .targetType(InstallationSettingListParams.TargetType.ORGANIZATION)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = InstallationSettingListParams.builder().pluginId("plugin_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            InstallationSettingListParams.builder()
                .pluginId("plugin_id")
                .limit(1L)
                .organizationId("organization_id")
                .page("page")
                .targetType(InstallationSettingListParams.TargetType.ORGANIZATION)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("organization_id", "organization_id")
                    .put("page", "page")
                    .put("target_type", "organization")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = InstallationSettingListParams.builder().pluginId("plugin_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
