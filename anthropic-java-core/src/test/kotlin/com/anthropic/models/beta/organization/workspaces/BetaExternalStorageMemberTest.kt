package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageMemberTest {

    @Test
    fun create() {
        val betaExternalStorageMember = BetaExternalStorageMember.of("external_storage_id")

        assertThat(betaExternalStorageMember.externalStorageId()).isEqualTo("external_storage_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageMember = BetaExternalStorageMember.of("external_storage_id")

        val roundtrippedBetaExternalStorageMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageMember),
                jacksonTypeRef<BetaExternalStorageMember>(),
            )

        assertThat(roundtrippedBetaExternalStorageMember).isEqualTo(betaExternalStorageMember)
    }
}
