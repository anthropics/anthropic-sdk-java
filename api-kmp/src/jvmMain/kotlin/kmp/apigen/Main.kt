package kmp.apigen

import java.io.File

fun main(args: Array<String>) {
    val spec = args.indexOf("--spec").let { if (it >= 0) args[it + 1] else null }
        ?: error("Usage: api-gen --spec <openapi.yaml> [--output <dir>] [--package <pkg>]")
    val output = args.indexOf("--output").let { if (it >= 0) args[it + 1] else "src/commonMain/kotlin" }
    val pkg = args.indexOf("--package").let { if (it >= 0) args[it + 1] else "com.anthropic" }

    val specFile = File(spec)
    require(specFile.exists()) { "Spec file not found: $spec" }

    println("api-gen: reading ${specFile.name}")

    val openApi = OpenApiParser.parse(specFile)

    println("  schemas: ${openApi.schemas.size}")
    println("  paths: ${openApi.paths.size}")
    println("  security: ${openApi.securitySchemes.size}")

    val outputDir = File(output)
    outputDir.mkdirs()

    // Generate models from schemas
    val modelGen = ModelGenerator(pkg, outputDir)
    openApi.schemas.forEach { (name, schema) ->
        modelGen.generate(name, schema)
    }
    println("  generated ${openApi.schemas.size} models → $output")

    // Generate services from paths
    val serviceGen = ServiceGenerator(pkg, outputDir)
    serviceGen.generate(openApi.paths, openApi.securitySchemes)
    println("  generated components")
    ComposeEmitter().emit(openApi, pkg, outputDir)
    println("  generated UI")
    DatabaseEmitter().emit(openApi, pkg, outputDir)
    println("  generated DB")
    ComponentEmitter().emit(openApi, pkg, outputDir)
    println("  generated services → $output")

    println("api-gen: done")
}
