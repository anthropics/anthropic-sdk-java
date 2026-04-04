package kmp.apigen

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.io.File

fun main(args: Array<String>) {
    val spec = args.indexOf("--spec").let { if (it >= 0) args[it + 1] else null }
        ?: error("Usage: api-gen --spec <openapi.yaml> [--output <dir>] [--package <pkg>] [--protocols rest,grpc,...]")
    val output = args.indexOf("--output").let { if (it >= 0) args[it + 1] else "src/commonMain/kotlin" }
    val pkg = args.indexOf("--package").let { if (it >= 0) args[it + 1] else "com.anthropic" }
    val protocols = args.indexOf("--protocols").let {
        if (it >= 0) args[it + 1].split(",").map { p -> p.trim() } else null
    }

    val specFile = File(spec)
    require(specFile.exists()) { "Spec file not found: $spec" }

    println("api-gen: reading ${specFile.name}")

    val parsedSpec = OpenApiParser.parse(specFile)

    println("  schemas: ${parsedSpec.schemas.size}")
    println("  paths: ${parsedSpec.paths.size}")
    println("  security: ${parsedSpec.securitySchemes.size}")

    val outputDir = File(output)
    outputDir.mkdirs()

    // Start Koin — get all ProtocolEmitter instances
    val koin = startKoin { modules(emittersModule) }.koin
    val emitters: List<ProtocolEmitter> = koin.getAll()

    // Filter by --protocols if specified
    val selected = if (protocols != null) {
        emitters.filter { it.protocol in protocols }
    } else {
        emitters
    }

    println("  emitters: ${selected.joinToString { it.protocol }}")

    selected.forEach { emitter ->
        emitter.emit(parsedSpec, pkg, outputDir)
        println("  ✓ ${emitter.protocol}")
    }

    stopKoin()
    println("api-gen: done (${selected.size} emitters)")
}
