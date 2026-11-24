package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

internal class BetaGoogleManagedStorageControlParamsTest {

    @Test
    fun ofDisabled() {
        val disabled = BetaGoogleManagedStorageDisabledParams.builder().build()

        val betaGoogleManagedStorageControlParams =
            BetaGoogleManagedStorageControlParams.ofDisabled(disabled)

        assertThat(presentVariants(betaGoogleManagedStorageControlParams))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageControlParams =
            BetaGoogleManagedStorageControlParams.ofDisabled(
                BetaGoogleManagedStorageDisabledParams.builder().build()
            )

        val roundtrippedBetaGoogleManagedStorageControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageControlParams),
                jacksonTypeRef<BetaGoogleManagedStorageControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageControlParams)
            .isEqualTo(betaGoogleManagedStorageControlParams)
    }

    @Test
    fun ofEnabled() {
        val enabled =
            BetaGoogleManagedStorageEnabledParams.builder()
                .buckets(
                    BetaGoogleManagedStorageEnabledParams.Buckets.builder()
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

        val betaGoogleManagedStorageControlParams =
            BetaGoogleManagedStorageControlParams.ofEnabled(enabled)

        assertThat(presentVariants(betaGoogleManagedStorageControlParams))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageControlParams =
            BetaGoogleManagedStorageControlParams.ofEnabled(
                BetaGoogleManagedStorageEnabledParams.builder()
                    .buckets(
                        BetaGoogleManagedStorageEnabledParams.Buckets.builder()
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

        val roundtrippedBetaGoogleManagedStorageControlParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageControlParams),
                jacksonTypeRef<BetaGoogleManagedStorageControlParams>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageControlParams)
            .isEqualTo(betaGoogleManagedStorageControlParams)
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
        val betaGoogleManagedStorageControlParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaGoogleManagedStorageControlParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaGoogleManagedStorageControlParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaGoogleManagedStorageControlParams: BetaGoogleManagedStorageControlParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "disabled" to betaGoogleManagedStorageControlParams.disabled(),
                "enabled" to betaGoogleManagedStorageControlParams.enabled(),
            )
            .filterValues { it.isPresent }
}
