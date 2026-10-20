package com.anthropic.models.userprofiles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedUserProfileTest {

    @Test
    fun create() {
        val deletedUserProfile = DeletedUserProfile.of("uprof_011CZkZCu8hGbp5mYRQgUmz9")

        assertThat(deletedUserProfile.id()).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedUserProfile = DeletedUserProfile.of("uprof_011CZkZCu8hGbp5mYRQgUmz9")

        val roundtrippedDeletedUserProfile =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedUserProfile),
                jacksonTypeRef<DeletedUserProfile>(),
            )

        assertThat(roundtrippedDeletedUserProfile).isEqualTo(deletedUserProfile)
    }
}
