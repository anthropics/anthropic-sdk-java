package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaStorageMemberTest {

    @Test
    fun ofInternal() {
        val internal_ = BetaInternalStorage.builder().build()

        val betaStorageMember = BetaStorageMember.ofInternal(internal_)

        assertThat(betaStorageMember.internal_()).contains(internal_)
        assertThat(betaStorageMember.external()).isEmpty
    }

    @Test
    fun ofInternalRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageMember = BetaStorageMember.ofInternal(BetaInternalStorage.builder().build())

        val roundtrippedBetaStorageMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageMember),
                jacksonTypeRef<BetaStorageMember>(),
            )

        assertThat(roundtrippedBetaStorageMember).isEqualTo(betaStorageMember)
    }

    @Test
    fun ofExternal() {
        val external = BetaExternalStorageMember.of("external_storage_id")

        val betaStorageMember = BetaStorageMember.ofExternal(external)

        assertThat(betaStorageMember.internal_()).isEmpty
        assertThat(betaStorageMember.external()).contains(external)
    }

    @Test
    fun ofExternalRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageMember =
            BetaStorageMember.ofExternal(BetaExternalStorageMember.of("external_storage_id"))

        val roundtrippedBetaStorageMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageMember),
                jacksonTypeRef<BetaStorageMember>(),
            )

        assertThat(roundtrippedBetaStorageMember).isEqualTo(betaStorageMember)
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
        val betaStorageMember =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaStorageMember>())

        val e = assertThrows<AnthropicInvalidDataException> { betaStorageMember.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
