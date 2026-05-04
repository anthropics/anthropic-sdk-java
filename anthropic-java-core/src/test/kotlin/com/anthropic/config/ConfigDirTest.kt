package com.anthropic.config

import com.anthropic.internal.config.ConfigDir
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS

internal class ConfigDirTest {

    @Test
    fun envVarOverridesDefault() {
        val configDir =
            ConfigDir.resolve(
                envConfigDir = "/custom/config",
                envHome = "/home/user",
                envAppData = null,
                osName = "Linux",
            )

        assertThat(configDir).isEqualTo("/custom/config")
    }

    @Test
    @EnabledOnOs(OS.LINUX, OS.MAC)
    fun unixUsesHomeConfig() {
        val configDir =
            ConfigDir.resolve(
                envConfigDir = null,
                envHome = "/home/user",
                envAppData = null,
                osName = "Linux",
            )

        assertThat(configDir).isEqualTo("/home/user/.config/anthropic")
    }

    @Test
    fun windowsUsesAppData() {
        val configDir =
            ConfigDir.resolve(
                envConfigDir = null,
                envHome = null,
                envAppData = "C:\\Users\\user\\AppData\\Roaming",
                osName = "Windows 10",
            )

        assertThat(configDir).startsWith("C:\\Users\\user\\AppData\\Roaming")
        assertThat(configDir).endsWith("Anthropic")
    }

    @Test
    fun returnsNullWhenNoHomeOrAppData() {
        val configDir =
            ConfigDir.resolve(
                envConfigDir = null,
                envHome = null,
                envAppData = null,
                osName = "Linux",
            )

        assertThat(configDir).isNull()
    }
}
