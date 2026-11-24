package com.anthropic.models.beta.organization.workspaces

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

internal class BetaStorageMemberParamTest {

    @Test
    fun ofInternal() {
        val internal_ = BetaInternalStorageParam.builder().build()

        val betaStorageMemberParam = BetaStorageMemberParam.ofInternal(internal_)

        assertThat(presentVariants(betaStorageMemberParam))
            .isEqualTo(mapOf("internal_" to Optional.of(internal_)))
    }

    @Test
    fun ofInternalRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageMemberParam =
            BetaStorageMemberParam.ofInternal(BetaInternalStorageParam.builder().build())

        val roundtrippedBetaStorageMemberParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageMemberParam),
                jacksonTypeRef<BetaStorageMemberParam>(),
            )

        assertThat(roundtrippedBetaStorageMemberParam).isEqualTo(betaStorageMemberParam)
    }

    @Test
    fun ofExternal() {
        val external = BetaExternalStorageMemberParam.of("external_storage_id")

        val betaStorageMemberParam = BetaStorageMemberParam.ofExternal(external)

        assertThat(presentVariants(betaStorageMemberParam))
            .isEqualTo(mapOf("external" to Optional.of(external)))
    }

    @Test
    fun ofExternalRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageMemberParam =
            BetaStorageMemberParam.ofExternal(
                BetaExternalStorageMemberParam.of("external_storage_id")
            )

        val roundtrippedBetaStorageMemberParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageMemberParam),
                jacksonTypeRef<BetaStorageMemberParam>(),
            )

        assertThat(roundtrippedBetaStorageMemberParam).isEqualTo(betaStorageMemberParam)
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
        val betaStorageMemberParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaStorageMemberParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaStorageMemberParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaStorageMemberParam: BetaStorageMemberParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "internal_" to betaStorageMemberParam.internal_(),
                "external" to betaStorageMemberParam.external(),
            )
            .filterValues { it.isPresent }
}
