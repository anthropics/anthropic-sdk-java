package com.anthropic.config

class InMemoryProfileConfigProvider private constructor(private val config: ProfileConfig) :
    ProfileConfigProvider {

    override fun get(): ProfileConfig = config

    companion object {
        @JvmStatic
        fun of(config: ProfileConfig): InMemoryProfileConfigProvider =
            InMemoryProfileConfigProvider(config)
    }
}
