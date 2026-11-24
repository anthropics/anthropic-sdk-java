package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(betaGoogleManagedProjectStorageControlParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
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

        assertThat(presentVariants(betaGoogleManagedProjectStorageControlParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
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

    private fun presentVariants(
        betaGoogleManagedProjectStorageControlParams: BetaGoogleManagedProjectStorageControlParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "disabled" to betaGoogleManagedProjectStorageControlParams.disabled(),
                "enabled" to betaGoogleManagedProjectStorageControlParams.enabled(),
            )
            .filterValues { it.isPresent }
}
