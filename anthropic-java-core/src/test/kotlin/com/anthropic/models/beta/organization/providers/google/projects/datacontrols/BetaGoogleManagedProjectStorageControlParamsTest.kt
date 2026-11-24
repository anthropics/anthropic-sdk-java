package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaGoogleManagedProjectStorageControlParamsTest {

    @Test
    fun ofDisabled() {
        val disabled = BetaGoogleManagedProjectStorageDisabledParams.builder().build()

        val betaGoogleManagedProjectStorageControlParams =
            BetaGoogleManagedProjectStorageControlParams.ofDisabled(disabled)

        assertThat(betaGoogleManagedProjectStorageControlParams.disabled()).contains(disabled)
        assertThat(betaGoogleManagedProjectStorageControlParams.enabled()).isEmpty
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageControlParams =
            BetaGoogleManagedProjectStorageControlParams.ofDisabled(
                BetaGoogleManagedProjectStorageDisabledParams.builder().build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageControlParams),
                jacksonTypeRef<BetaGoogleManagedProjectStorageControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageControlParams)
            .isEqualTo(betaGoogleManagedProjectStorageControlParams)
    }

    @Test
    fun ofEnabled() {
        val enabled =
            BetaGoogleManagedProjectStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedProjectStorageEnabledParams.Buckets.builder()
                        .putAdditionalProperty(
                            "foo",
                            JsonValue.from(
                                mapOf(
                                    "bucket_name" to "bucket_name",
                                    "project_number" to "project_number",
                                )
                            ),
                        )
                        .build()
                )
                .build()

        val betaGoogleManagedProjectStorageControlParams =
            BetaGoogleManagedProjectStorageControlParams.ofEnabled(enabled)

        assertThat(betaGoogleManagedProjectStorageControlParams.disabled()).isEmpty
        assertThat(betaGoogleManagedProjectStorageControlParams.enabled()).contains(enabled)
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageControlParams =
            BetaGoogleManagedProjectStorageControlParams.ofEnabled(
                BetaGoogleManagedProjectStorageEnabledParams.builder()
                    .buckets(
                        BetaGoogleManagedProjectStorageEnabledParams.Buckets.builder()
                            .putAdditionalProperty(
                                "foo",
                                JsonValue.from(
                                    mapOf(
                                        "bucket_name" to "bucket_name",
                                        "project_number" to "project_number",
                                    )
                                ),
                            )
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaGoogleManagedProjectStorageControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageControlParams),
                jacksonTypeRef<BetaGoogleManagedProjectStorageControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageControlParams)
            .isEqualTo(betaGoogleManagedProjectStorageControlParams)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaGoogleManagedProjectStorageControlParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedProjectStorageControlParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedProjectStorageControlParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
