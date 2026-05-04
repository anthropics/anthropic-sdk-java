package com.anthropic.config

interface ProfileConfigProvider {
    fun get(): ProfileConfig
}
