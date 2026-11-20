package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedExternalStorageTest {

    @Test
    fun create() {
        val betaDeletedExternalStorage =
            BetaDeletedExternalStorage.of("estrg_01SDCCSbTxrXDpWc1phhtcfK")

        assertThat(betaDeletedExternalStorage.id()).isEqualTo("estrg_01SDCCSbTxrXDpWc1phhtcfK")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedExternalStorage =
            BetaDeletedExternalStorage.of("estrg_01SDCCSbTxrXDpWc1phhtcfK")

        val roundtrippedBetaDeletedExternalStorage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedExternalStorage),
                jacksonTypeRef<BetaDeletedExternalStorage>(),
            )

        assertThat(roundtrippedBetaDeletedExternalStorage).isEqualTo(betaDeletedExternalStorage)
    }
}
