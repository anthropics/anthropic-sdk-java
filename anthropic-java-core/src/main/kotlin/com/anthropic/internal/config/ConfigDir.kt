package com.anthropic.internal.config

import java.nio.file.Path
import java.nio.file.Paths

internal object ConfigDir {
    private const val ENV_CONFIG_DIR = "ANTHROPIC_CONFIG_DIR"

    fun resolve(): String? =
        resolve(
            envConfigDir = System.getenv(ENV_CONFIG_DIR),
            envHome = System.getProperty("user.home") ?: System.getenv("HOME"),
            envAppData = System.getenv("APPDATA"),
            osName = System.getProperty("os.name") ?: "",
        )

    internal fun resolve(
        envConfigDir: String?,
        envHome: String?,
        envAppData: String?,
        osName: String,
    ): String? {
        if (envConfigDir != null) {
            return envConfigDir
        }

        val isWindows = osName.lowercase().contains("windows")
        return if (isWindows) {
            envAppData?.let { Paths.get(it, "Anthropic").toString() }
        } else {
            envHome?.let { Paths.get(it, ".config", "anthropic").toString() }
        }
    }

    fun configsDir(): Path? = resolve()?.let { Paths.get(it, "configs") }

    fun credentialsDir(): Path? = resolve()?.let { Paths.get(it, "credentials") }

    fun activeConfigFile(): Path? = resolve()?.let { Paths.get(it, "active_config") }
}
