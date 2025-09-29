// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaMemoryTool20250818CommandTest {

    @Test
    fun ofView() {
        val view =
            BetaMemoryTool20250818ViewCommand.builder()
                .path("/memories")
                .addViewRange(1L)
                .addViewRange(10L)
                .build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofView(view)

        assertThat(betaMemoryTool20250818Command.view()).contains(view)
        assertThat(betaMemoryTool20250818Command.create()).isEmpty
        assertThat(betaMemoryTool20250818Command.strReplace()).isEmpty
        assertThat(betaMemoryTool20250818Command.insert()).isEmpty
        assertThat(betaMemoryTool20250818Command.delete()).isEmpty
        assertThat(betaMemoryTool20250818Command.rename()).isEmpty
    }

    @Test
    fun ofViewRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofView(
                BetaMemoryTool20250818ViewCommand.builder()
                    .path("/memories")
                    .addViewRange(1L)
                    .addViewRange(10L)
                    .build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
    }

    @Test
    fun ofCreate() {
        val create =
            BetaMemoryTool20250818CreateCommand.builder()
                .fileText("Meeting notes:\n- Discussed project timeline\n- Next steps defined\n")
                .path("/memories/notes.txt")
                .build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofCreate(create)

        assertThat(betaMemoryTool20250818Command.view()).isEmpty
        assertThat(betaMemoryTool20250818Command.create()).contains(create)
        assertThat(betaMemoryTool20250818Command.strReplace()).isEmpty
        assertThat(betaMemoryTool20250818Command.insert()).isEmpty
        assertThat(betaMemoryTool20250818Command.delete()).isEmpty
        assertThat(betaMemoryTool20250818Command.rename()).isEmpty
    }

    @Test
    fun ofCreateRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofCreate(
                BetaMemoryTool20250818CreateCommand.builder()
                    .fileText(
                        "Meeting notes:\n- Discussed project timeline\n- Next steps defined\n"
                    )
                    .path("/memories/notes.txt")
                    .build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
    }

    @Test
    fun ofStrReplace() {
        val strReplace =
            BetaMemoryTool20250818StrReplaceCommand.builder()
                .newStr("Favorite color: green")
                .oldStr("Favorite color: blue")
                .path("/memories/preferences.txt")
                .build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofStrReplace(strReplace)

        assertThat(betaMemoryTool20250818Command.view()).isEmpty
        assertThat(betaMemoryTool20250818Command.create()).isEmpty
        assertThat(betaMemoryTool20250818Command.strReplace()).contains(strReplace)
        assertThat(betaMemoryTool20250818Command.insert()).isEmpty
        assertThat(betaMemoryTool20250818Command.delete()).isEmpty
        assertThat(betaMemoryTool20250818Command.rename()).isEmpty
    }

    @Test
    fun ofStrReplaceRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofStrReplace(
                BetaMemoryTool20250818StrReplaceCommand.builder()
                    .newStr("Favorite color: green")
                    .oldStr("Favorite color: blue")
                    .path("/memories/preferences.txt")
                    .build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
    }

    @Test
    fun ofInsert() {
        val insert =
            BetaMemoryTool20250818InsertCommand.builder()
                .insertLine(2L)
                .insertText("- Review memory tool documentation\n")
                .path("/memories/todo.txt")
                .build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofInsert(insert)

        assertThat(betaMemoryTool20250818Command.view()).isEmpty
        assertThat(betaMemoryTool20250818Command.create()).isEmpty
        assertThat(betaMemoryTool20250818Command.strReplace()).isEmpty
        assertThat(betaMemoryTool20250818Command.insert()).contains(insert)
        assertThat(betaMemoryTool20250818Command.delete()).isEmpty
        assertThat(betaMemoryTool20250818Command.rename()).isEmpty
    }

    @Test
    fun ofInsertRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofInsert(
                BetaMemoryTool20250818InsertCommand.builder()
                    .insertLine(2L)
                    .insertText("- Review memory tool documentation\n")
                    .path("/memories/todo.txt")
                    .build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
    }

    @Test
    fun ofDelete() {
        val delete =
            BetaMemoryTool20250818DeleteCommand.builder().path("/memories/old_file.txt").build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofDelete(delete)

        assertThat(betaMemoryTool20250818Command.view()).isEmpty
        assertThat(betaMemoryTool20250818Command.create()).isEmpty
        assertThat(betaMemoryTool20250818Command.strReplace()).isEmpty
        assertThat(betaMemoryTool20250818Command.insert()).isEmpty
        assertThat(betaMemoryTool20250818Command.delete()).contains(delete)
        assertThat(betaMemoryTool20250818Command.rename()).isEmpty
    }

    @Test
    fun ofDeleteRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofDelete(
                BetaMemoryTool20250818DeleteCommand.builder().path("/memories/old_file.txt").build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
    }

    @Test
    fun ofRename() {
        val rename =
            BetaMemoryTool20250818RenameCommand.builder()
                .newPath("/memories/final.txt")
                .oldPath("/memories/draft.txt")
                .build()

        val betaMemoryTool20250818Command = BetaMemoryTool20250818Command.ofRename(rename)

        assertThat(betaMemoryTool20250818Command.view()).isEmpty
        assertThat(betaMemoryTool20250818Command.create()).isEmpty
        assertThat(betaMemoryTool20250818Command.strReplace()).isEmpty
        assertThat(betaMemoryTool20250818Command.insert()).isEmpty
        assertThat(betaMemoryTool20250818Command.delete()).isEmpty
        assertThat(betaMemoryTool20250818Command.rename()).contains(rename)
    }

    @Test
    fun ofRenameRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818Command =
            BetaMemoryTool20250818Command.ofRename(
                BetaMemoryTool20250818RenameCommand.builder()
                    .newPath("/memories/final.txt")
                    .oldPath("/memories/draft.txt")
                    .build()
            )

        val roundtrippedBetaMemoryTool20250818Command =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818Command),
                jacksonTypeRef<BetaMemoryTool20250818Command>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818Command)
            .isEqualTo(betaMemoryTool20250818Command)
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
        val betaMemoryTool20250818Command =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaMemoryTool20250818Command>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaMemoryTool20250818Command.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
