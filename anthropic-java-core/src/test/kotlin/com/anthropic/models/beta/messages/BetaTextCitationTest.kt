package com.anthropic.models.beta.messages

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

internal class BetaTextCitationTest {

    @Test
    fun ofCharLocation() {
        val charLocation =
            BetaCitationCharLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endCharIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startCharIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofCharLocation(charLocation)

        assertThat(presentVariants(betaTextCitation))
            .isEqualTo(mapOf("charLocation" to Optional.of(charLocation)))
    }

    @Test
    fun ofCharLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofCharLocation(
                BetaCitationCharLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endCharIndex(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .startCharIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofPageLocation() {
        val pageLocation =
            BetaCitationPageLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endPageNumber(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startPageNumber(1L)
                .build()

        val betaTextCitation = BetaTextCitation.ofPageLocation(pageLocation)

        assertThat(presentVariants(betaTextCitation))
            .isEqualTo(mapOf("pageLocation" to Optional.of(pageLocation)))
    }

    @Test
    fun ofPageLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofPageLocation(
                BetaCitationPageLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endPageNumber(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .startPageNumber(1L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofContentBlockLocation() {
        val contentBlockLocation =
            BetaCitationContentBlockLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .documentIndex(0L)
                .documentTitle("My Document")
                .endBlockIndex(0L)
                .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                .startBlockIndex(0L)
                .build()

        val betaTextCitation = BetaTextCitation.ofContentBlockLocation(contentBlockLocation)

        assertThat(presentVariants(betaTextCitation))
            .isEqualTo(mapOf("contentBlockLocation" to Optional.of(contentBlockLocation)))
    }

    @Test
    fun ofContentBlockLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofContentBlockLocation(
                BetaCitationContentBlockLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .documentIndex(0L)
                    .documentTitle("My Document")
                    .endBlockIndex(0L)
                    .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                    .startBlockIndex(0L)
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofWebSearchResultLocation() {
        val webSearchResultLocation =
            BetaCitationsWebSearchResultLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .encryptedIndex("encrypted_index")
                .title("title")
                .url("url")
                .build()

        val betaTextCitation = BetaTextCitation.ofWebSearchResultLocation(webSearchResultLocation)

        assertThat(presentVariants(betaTextCitation))
            .isEqualTo(mapOf("webSearchResultLocation" to Optional.of(webSearchResultLocation)))
    }

    @Test
    fun ofWebSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofWebSearchResultLocation(
                BetaCitationsWebSearchResultLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .encryptedIndex("encrypted_index")
                    .title("title")
                    .url("url")
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun ofSearchResultLocation() {
        val searchResultLocation =
            BetaCitationSearchResultLocation.builder()
                .citedText("The grass is green. The sky is blue.")
                .endBlockIndex(0L)
                .searchResultIndex(0L)
                .source("source")
                .startBlockIndex(0L)
                .title("title")
                .build()

        val betaTextCitation = BetaTextCitation.ofSearchResultLocation(searchResultLocation)

        assertThat(presentVariants(betaTextCitation))
            .isEqualTo(mapOf("searchResultLocation" to Optional.of(searchResultLocation)))
    }

    @Test
    fun ofSearchResultLocationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextCitation =
            BetaTextCitation.ofSearchResultLocation(
                BetaCitationSearchResultLocation.builder()
                    .citedText("The grass is green. The sky is blue.")
                    .endBlockIndex(0L)
                    .searchResultIndex(0L)
                    .source("source")
                    .startBlockIndex(0L)
                    .title("title")
                    .build()
            )

        val roundtrippedBetaTextCitation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextCitation),
                jacksonTypeRef<BetaTextCitation>(),
            )

        assertThat(roundtrippedBetaTextCitation).isEqualTo(betaTextCitation)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaTextCitation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to "The grass is green. The sky is blue.",
                            "document_index" to 0,
                            "document_title" to "My Document",
                            "file_id" to "file_011CNha8iCJcU1wXNR6q4V8w",
                            "end_block_index" to 0,
                            "start_block_index" to 0,
                            "title" to "title",
                        )
                    ),
                    jacksonTypeRef<BetaTextCitation>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaTextCitation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaTextCitation.citedText()).isEqualTo("The grass is green. The sky is blue.")
        assertThat(betaTextCitation.documentIndex()).contains(0L)
        assertThat(betaTextCitation.documentTitle()).contains("My Document")
        assertThat(betaTextCitation.fileId()).contains("file_011CNha8iCJcU1wXNR6q4V8w")
        assertThat(betaTextCitation.endBlockIndex()).contains(0L)
        assertThat(betaTextCitation.startBlockIndex()).contains(0L)
        assertThat(betaTextCitation.title()).contains("title")

        val mismatchedBetaTextCitation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "cited_text" to listOf("invalid"),
                            "document_index" to listOf("invalid"),
                            "document_title" to listOf("invalid"),
                            "file_id" to listOf("invalid"),
                            "end_block_index" to listOf("invalid"),
                            "start_block_index" to listOf("invalid"),
                            "title" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaTextCitation>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaTextCitation.citedText() }
        assertThat(mismatchedBetaTextCitation.documentIndex()).isEmpty
        assertThat(mismatchedBetaTextCitation.documentTitle()).isEmpty
        assertThat(mismatchedBetaTextCitation.fileId()).isEmpty
        assertThat(mismatchedBetaTextCitation.endBlockIndex()).isEmpty
        assertThat(mismatchedBetaTextCitation.startBlockIndex()).isEmpty
        assertThat(mismatchedBetaTextCitation.title()).isEmpty
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
        val betaTextCitation =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaTextCitation>())

        val e = assertThrows<AnthropicInvalidDataException> { betaTextCitation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaTextCitation.citedText() }
        assertThat(betaTextCitation.documentIndex()).isEmpty
        assertThat(betaTextCitation.documentTitle()).isEmpty
        assertThat(betaTextCitation.fileId()).isEmpty
        assertThat(betaTextCitation.endBlockIndex()).isEmpty
        assertThat(betaTextCitation.startBlockIndex()).isEmpty
        assertThat(betaTextCitation.title()).isEmpty
    }

    private fun presentVariants(betaTextCitation: BetaTextCitation): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "charLocation" to betaTextCitation.charLocation(),
                "pageLocation" to betaTextCitation.pageLocation(),
                "contentBlockLocation" to betaTextCitation.contentBlockLocation(),
                "webSearchResultLocation" to betaTextCitation.webSearchResultLocation(),
                "searchResultLocation" to betaTextCitation.searchResultLocation(),
            )
            .filterValues { it.isPresent }
}
