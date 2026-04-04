package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates Component classes from parsed OpenAPI schemas.
 *
 * Each schema → one Component class with:
 * - CRUD: get/list/add/replace/patch/remove
 * - Streaming: changes() → Flow<PatchEvent<T>>
 * - All mutations return PatchEvent<T>
 */
class ComponentEmitter : ProtocolEmitter {
    override val protocol = "component"

    private val componentInterface = ClassName("com.anthropic.core.component", "Component")
    private val patchEvent = ClassName("com.anthropic.core.component", "PatchEvent")
    private val patchOperation = ClassName("com.anthropic.core.component", "PatchOperation")
    private val flow = ClassName("kotlinx.coroutines.flow", "Flow")
    private val httpClient = ClassName("io.ktor.client", "HttpClient")
    private val contentType = ClassName("io.ktor.http", "ContentType")
    private val json = ClassName("kotlinx.serialization.json", "Json")

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelsPackage = "$pkg.models"
        val componentPackage = "$pkg.components"

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach

            val entityType = ClassName(modelsPackage, name)
            val patchEventType = patchEvent.parameterizedBy(entityType)
            val flowType = flow.parameterizedBy(patchEventType)
            val endpoint = "/${name.lowercase()}"

            val classBuilder = TypeSpec.classBuilder("${name}Component")
                .addSuperinterface(componentInterface.parameterizedBy(entityType))
                .primaryConstructor(
                    FunSpec.constructorBuilder()
                        .addParameter("client", httpClient)
                        .addParameter("baseUrl", String::class)
                        .addParameter(
                            ParameterSpec.builder("json", json)
                                .defaultValue("%T { ignoreUnknownKeys = true }", json)
                                .build()
                        )
                        .build()
                )
                .addProperty(PropertySpec.builder("client", httpClient).initializer("client").addModifiers(KModifier.PRIVATE).build())
                .addProperty(PropertySpec.builder("baseUrl", String::class).initializer("baseUrl").addModifiers(KModifier.PRIVATE).build())
                .addProperty(PropertySpec.builder("json", json).initializer("json").addModifiers(KModifier.PRIVATE).build())
                .addProperty(
                    PropertySpec.builder("name", String::class)
                        .addModifiers(KModifier.OVERRIDE)
                        .getter(FunSpec.getterBuilder().addStatement("return %S", name.lowercase()).build())
                        .build()
                )

            // get(id)
            classBuilder.addFunction(
                FunSpec.builder("get")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter("id", String::class)
                    .returns(entityType)
                    .addStatement("return client.get(\"\$baseUrl$endpoint/\$id\").body()")
                    .build()
            )

            // list(params)
            classBuilder.addFunction(
                FunSpec.builder("list")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter(
                        ParameterSpec.builder("params", MAP.parameterizedBy(STRING, STRING))
                            .defaultValue("emptyMap()")
                            .build()
                    )
                    .returns(LIST.parameterizedBy(entityType))
                    .addStatement("return client.get(\"\$baseUrl$endpoint\") { params.forEach { (k, v) -> parameter(k, v) } }.body()")
                    .build()
            )

            // add(entity) → PatchEvent
            classBuilder.addFunction(
                FunSpec.builder("add")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter("entity", entityType)
                    .returns(patchEventType)
                    .addStatement("return client.post(\"\$baseUrl$endpoint\") { contentType(%T.Application.Json); setBody(entity) }.body()", contentType)
                    .build()
            )

            // replace(id, entity) → PatchEvent
            classBuilder.addFunction(
                FunSpec.builder("replace")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter("id", String::class)
                    .addParameter("entity", entityType)
                    .returns(patchEventType)
                    .addStatement("return client.put(\"\$baseUrl$endpoint/\$id\") { contentType(%T.Application.Json); setBody(entity) }.body()", contentType)
                    .build()
            )

            // patch(id, ops) → PatchEvent
            classBuilder.addFunction(
                FunSpec.builder("patch")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter("id", String::class)
                    .addParameter("ops", LIST.parameterizedBy(patchOperation))
                    .returns(patchEventType)
                    .addStatement("return client.patch(\"\$baseUrl$endpoint/\$id\") { setBody(ops) }.body()")
                    .build()
            )

            // remove(id) → PatchEvent
            classBuilder.addFunction(
                FunSpec.builder("remove")
                    .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
                    .addParameter("id", String::class)
                    .returns(patchEventType)
                    .addStatement("return client.delete(\"\$baseUrl$endpoint/\$id\").body()")
                    .build()
            )

            // changes() → Flow<PatchEvent<T>>
            classBuilder.addFunction(
                FunSpec.builder("changes")
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(flowType)
                    .addStatement("""
                        |return kotlinx.coroutines.flow.flow {
                        |    client.sse("${'$'}baseUrl$endpoint/changes") {
                        |        incoming.collect { event ->
                        |            event.data?.let { emit(json.decodeFromString(it)) }
                        |        }
                        |    }
                        |}
                    """.trimMargin())
                    .build()
            )

            FileSpec.builder(componentPackage, "${name}Component")
                .addImport("io.ktor.client.call", "body")
                .addImport("io.ktor.client.request", "get", "post", "put", "delete", "patch", "parameter", "setBody")
                .addImport("io.ktor.client.plugins.sse", "sse")
                .addImport("io.ktor.http", "contentType")
                .addType(classBuilder.build())
                .build()
                .writeTo(outputDir)
        }
    }
}
