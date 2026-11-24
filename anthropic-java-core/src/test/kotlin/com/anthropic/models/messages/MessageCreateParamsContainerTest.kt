package com.anthropic.models.messages

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

internal class MessageCreateParamsContainerTest {

    @Test
    fun ofContainerParams() {
        val containerParams =
            ContainerParams.builder()
                .id("id")
                .addSkill(
                    SkillParams.builder()
                        .skillId("pdf")
                        .type(SkillParams.Type.ANTHROPIC)
                        .version("latest")
                        .build()
                )
                .build()

        val messageCreateParamsContainer =
            MessageCreateParamsContainer.ofContainerParams(containerParams)

        assertThat(presentVariants(messageCreateParamsContainer))
            .isEqualTo(mapOf("containerParams" to Optional.of(containerParams)))
    }

    @Test
    fun ofContainerParamsRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageCreateParamsContainer =
            MessageCreateParamsContainer.ofContainerParams(
                ContainerParams.builder()
                    .id("id")
                    .addSkill(
                        SkillParams.builder()
                            .skillId("pdf")
                            .type(SkillParams.Type.ANTHROPIC)
                            .version("latest")
                            .build()
                    )
                    .build()
            )

        val roundtrippedMessageCreateParamsContainer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCreateParamsContainer),
                jacksonTypeRef<MessageCreateParamsContainer>(),
            )

        assertThat(roundtrippedMessageCreateParamsContainer).isEqualTo(messageCreateParamsContainer)
    }

    @Test
    fun ofString() {
        val string = "string"

        val messageCreateParamsContainer = MessageCreateParamsContainer.ofString(string)

        assertThat(presentVariants(messageCreateParamsContainer))
            .isEqualTo(mapOf("string" to Optional.of(string)))
    }

    @Test
    fun ofStringRoundtrip() {
        val jsonMapper = jsonMapper()
        val messageCreateParamsContainer = MessageCreateParamsContainer.ofString("string")

        val roundtrippedMessageCreateParamsContainer =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageCreateParamsContainer),
                jacksonTypeRef<MessageCreateParamsContainer>(),
            )

        assertThat(roundtrippedMessageCreateParamsContainer).isEqualTo(messageCreateParamsContainer)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val messageCreateParamsContainer =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<MessageCreateParamsContainer>())

        val e =
            assertThrows<AnthropicInvalidDataException> { messageCreateParamsContainer.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        messageCreateParamsContainer: MessageCreateParamsContainer
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "containerParams" to messageCreateParamsContainer.containerParams(),
                "string" to messageCreateParamsContainer.string(),
            )
            .filterValues { it.isPresent }
}
