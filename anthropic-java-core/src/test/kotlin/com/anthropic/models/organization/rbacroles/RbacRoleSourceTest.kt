package com.anthropic.models.organization.rbacroles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class RbacRoleSourceTest {

    @Test
    fun ofBuiltIn() {
        val builtIn = RbacRoleSourceBuiltIn.builder().build()

        val rbacRoleSource = RbacRoleSource.ofBuiltIn(builtIn)

        assertThat(rbacRoleSource.builtIn()).contains(builtIn)
        assertThat(rbacRoleSource.custom()).isEmpty
    }

    @Test
    fun ofBuiltInRoundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleSource = RbacRoleSource.ofBuiltIn(RbacRoleSourceBuiltIn.builder().build())

        val roundtrippedRbacRoleSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleSource),
                jacksonTypeRef<RbacRoleSource>(),
            )

        assertThat(roundtrippedRbacRoleSource).isEqualTo(rbacRoleSource)
    }

    @Test
    fun ofCustom() {
        val custom = RbacRoleSourceCustom.builder().build()

        val rbacRoleSource = RbacRoleSource.ofCustom(custom)

        assertThat(rbacRoleSource.builtIn()).isEmpty
        assertThat(rbacRoleSource.custom()).contains(custom)
    }

    @Test
    fun ofCustomRoundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRoleSource = RbacRoleSource.ofCustom(RbacRoleSourceCustom.builder().build())

        val roundtrippedRbacRoleSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRoleSource),
                jacksonTypeRef<RbacRoleSource>(),
            )

        assertThat(roundtrippedRbacRoleSource).isEqualTo(rbacRoleSource)
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
        val rbacRoleSource =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<RbacRoleSource>())

        val e = assertThrows<AnthropicInvalidDataException> { rbacRoleSource.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
