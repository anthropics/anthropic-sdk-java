package kmp.apigen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File

/**
 * Generates synthetic sanitized test data using Kotlin Faker.
 *
 * For each OpenAPI schema object → generates a TestDataFactory with:
 * - `random()` — fully random instance using Faker providers
 * - `sample()` — deterministic sample (seeded)
 * - `samples(n)` — list of n unique instances
 * - `sanitized()` — PII-free version (emails → fake, phones → fake, names → fake)
 *
 * Maps OpenAPI string formats to appropriate Faker providers:
 * - email → faker.internet.email()
 * - phone → faker.phoneNumber.phoneNumber()
 * - uri/url → faker.internet.url()
 * - hostname → faker.internet.domainName()
 * - ipv4 → faker.internet.ipV4Address()
 * - ipv6 → faker.internet.ipV6Address()
 * - uuid → faker.random.nextUUID()
 * - date → faker.date (formatted)
 * - date-time → faker.date (ISO 8601)
 * - person-name → faker.name.name()
 * - address → faker.address
 * - currency → faker.currency.code()
 * - country → faker.address.countryCode()
 * - language → faker.nation.language()
 *
 * Wire proto messages get the same treatment — field types map to Faker providers.
 */
class FakerTestDataEmitter : ProtocolEmitter {
    override val protocol = "faker-testdata"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val modelsPackage = "$pkg.models"
        val testDataPackage = "$pkg.testdata"

        spec.schemas.forEach { (name, schema) ->
            if (schema !is ParsedSchema.Object) return@forEach
            if (schema.properties.size < 2) return@forEach

            val file = generateFactory(name, schema, modelsPackage, testDataPackage)
            file.writeTo(outputDir)
        }

        // Generate a master TestDataModule that collects all factories
        generateModule(spec, modelsPackage, testDataPackage, outputDir)
    }

    private fun generateFactory(
        name: String,
        schema: ParsedSchema.Object,
        modelsPackage: String,
        testDataPackage: String,
    ): FileSpec {
        val entityType = ClassName(modelsPackage, name)
        val faker = ClassName("io.github.serpro69.kfaker", "Faker")

        val file = FileSpec.builder(testDataPackage, "${name}TestData")
        file.addImport("io.github.serpro69.kfaker", "Faker", "fakerConfig")

        val factory = TypeSpec.objectBuilder("${name}TestData")

        // Seeded faker for deterministic tests
        factory.addProperty(
            PropertySpec.builder("faker", faker)
                .addModifiers(KModifier.PRIVATE)
                .initializer("Faker(fakerConfig { random = java.util.Random(42) })")
                .build()
        )

        // random() — fully random instance
        factory.addFunction(
            FunSpec.builder("random")
                .returns(entityType)
                .addParameter(
                    ParameterSpec.builder("f", faker)
                        .defaultValue("Faker()")
                        .build()
                )
                .addCode(buildFakerConstructor(name, schema, "f"))
                .build()
        )

        // sample() — deterministic (seeded)
        factory.addFunction(
            FunSpec.builder("sample")
                .returns(entityType)
                .addCode("return random(faker)\n")
                .build()
        )

        // samples(n) — list of n unique instances
        factory.addFunction(
            FunSpec.builder("samples")
                .addParameter("count", Int::class)
                .returns(ClassName("kotlin.collections", "List").parameterizedBy(entityType))
                .addCode("val f = Faker()\nreturn (1..count).map { random(f) }\n")
                .build()
        )

        // sanitized(entity) — replace PII fields with fake data
        factory.addFunction(
            FunSpec.builder("sanitized")
                .addParameter("entity", entityType)
                .returns(entityType)
                .addCode(buildSanitizer(name, schema))
                .build()
        )

        file.addType(factory.build())
        return file.build()
    }

    private fun buildFakerConstructor(name: String, schema: ParsedSchema.Object, fakerVar: String): CodeBlock {
        val builder = CodeBlock.builder()
        builder.add("return %L(\n", name)

        schema.properties.entries.forEachIndexed { i, (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val fakerExpr = fakerExpression(fakerVar, prop.type, prop.format, propName)

            if (prop.nullable && propName !in schema.required) {
                // Skip optional nullable fields — leave as null
            } else {
                if (i > 0) builder.add(",\n")
                builder.add("    %L = %L", kotlinName, fakerExpr)
            }
        }

        builder.add("\n)\n")
        return builder.build()
    }

    private fun fakerExpression(f: String, type: String, format: String?, propName: String): String {
        // Map type + format + propName to best Faker provider
        return when {
            // By format (OpenAPI string format)
            format == "email" || type == "Email" -> "$f.internet.email()"
            format == "phone" || type == "Phone" -> "$f.phoneNumber.phoneNumber()"
            format == "uri" || format == "url" || type == "Uri" -> "\"https://\" + $f.internet.domain()"
            format == "hostname" || type == "Hostname" -> "$f.internet.domain()"
            format == "ipv4" || (type == "IpAddress" && format != "ipv6") -> "$f.internet.ipV4Address()"
            format == "ipv6" -> "$f.internet.ipV6Address()"
            format == "uuid" || type == "Uuid" -> "$f.random.nextUUID()"
            format == "date" || type == "LocalDate" -> "\"\" + $f.random.nextInt(2020, 2025) + \"-\" + String.format(\"%02d\", $f.random.nextInt(1, 12)) + \"-\" + String.format(\"%02d\", $f.random.nextInt(1, 28))"
            format == "date-time" || type == "Instant" -> "\"\" + $f.random.nextInt(2020, 2025) + \"-01-01T00:00:00Z\""
            format == "time" || type == "LocalTime" -> "String.format(\"%02d:%02d:%02d\", $f.random.nextInt(0, 23), $f.random.nextInt(0, 59), $f.random.nextInt(0, 59))"
            format == "duration" || type == "Duration" -> "\"PT\" + $f.random.nextInt(1, 100) + \"S\""
            format == "password" || type == "Password" -> "$f.random.randomString(12)"
            format == "currency" || type == "Currency" -> "listOf(\"USD\", \"EUR\", \"GBP\", \"JPY\").random()"
            format == "country" || type == "Country" -> "listOf(\"US\", \"GB\", \"DE\", \"JP\", \"FR\").random()"
            format == "language" || type == "Language" -> "listOf(\"en\", \"fr\", \"de\", \"ja\", \"es\").random()"
            format == "timezone" || type == "Timezone" -> "listOf(\"UTC\", \"America/New_York\", \"Europe/London\", \"Asia/Tokyo\").random()"

            // By propName heuristics
            "name" in propName.lowercase() && "user" in propName.lowercase() -> "$f.name.name()"
            "firstName" == propName || "given" == propName -> "$f.name.firstName()"
            "lastName" == propName || "family" == propName -> "$f.name.lastName()"
            "name" in propName.lowercase() -> "$f.name.name()"
            "email" in propName.lowercase() -> "$f.internet.email()"
            "phone" in propName.lowercase() -> "$f.phoneNumber.phoneNumber()"
            "address" in propName.lowercase() -> "$f.address.fullAddress()"
            "city" in propName.lowercase() -> "$f.address.city()"
            "state" in propName.lowercase() -> "$f.address.state()"
            "country" in propName.lowercase() -> "$f.address.countryCode()"
            "zip" in propName.lowercase() || "postal" in propName.lowercase() -> "$f.address.postcode()"
            "street" in propName.lowercase() -> "$f.address.streetAddress()"
            "company" in propName.lowercase() || "org" in propName.lowercase() -> "$f.company.name()"
            "url" in propName.lowercase() -> "\"https://\" + $f.internet.domain()"
            "description" in propName.lowercase() -> "$f.lorem.words()"
            "title" in propName.lowercase() -> "$f.book.title()"
            "tag" in propName.lowercase() -> "$f.lorem.words()"

            // By type
            type == "String" -> "$f.lorem.words()"
            type == "Int" -> "$f.random.nextInt(1, 10000)"
            type == "Long" -> "$f.random.nextLong(10000)"
            type == "Double" || type == "Float" -> "$f.random.nextDouble()"
            type == "Boolean" -> "$f.random.nextBoolean()"
            type.startsWith("List") -> "emptyList()"
            else -> "null"
        }
    }

    private fun buildSanitizer(name: String, schema: ParsedSchema.Object): CodeBlock {
        val builder = CodeBlock.builder()
        builder.addStatement("val f = Faker()")
        builder.add("return entity.copy(\n")

        val piiFields = schema.properties.filter { (propName, prop) ->
            isPii(propName, prop.type, prop.format)
        }

        piiFields.entries.forEachIndexed { i, (propName, prop) ->
            val kotlinName = propName.toCamelCase()
            val replacement = fakerExpression("f", prop.type, prop.format, propName)
            if (i > 0) builder.add(",\n")
            builder.add("    %L = %L", kotlinName, replacement)
        }

        builder.add("\n)\n")
        return builder.build()
    }

    private fun isPii(propName: String, type: String, format: String?): Boolean {
        val lower = propName.lowercase()
        return format in listOf("email", "phone", "password") ||
            type in listOf("Email", "Phone", "Password", "PersonName", "PostalAddress") ||
            "email" in lower || "phone" in lower || "password" in lower ||
            "name" in lower || "address" in lower || "ssn" in lower ||
            "credit" in lower || "card" in lower
    }

    private fun generateModule(
        spec: ParsedSpec,
        modelsPackage: String,
        testDataPackage: String,
        outputDir: File,
    ) {
        val schemas = spec.schemas.filter { (_, s) ->
            s is ParsedSchema.Object && (s as ParsedSchema.Object).properties.size >= 2
        }
        if (schemas.isEmpty()) return

        val file = FileSpec.builder(testDataPackage, "TestDataModule")

        val module = TypeSpec.objectBuilder("TestDataModule")

        // allFactories — registry of name → factory
        module.addFunction(
            FunSpec.builder("randomOf")
                .addParameter("schemaName", String::class)
                .returns(Any::class.asTypeName().copy(nullable = true))
                .addCode(buildString {
                    appendLine("return when (schemaName) {")
                    schemas.forEach { (name, _) ->
                        appendLine("    \"$name\" -> ${name}TestData.random()")
                    }
                    appendLine("    else -> null")
                    appendLine("}")
                })
                .build()
        )

        module.addFunction(
            FunSpec.builder("sampleOf")
                .addParameter("schemaName", String::class)
                .returns(Any::class.asTypeName().copy(nullable = true))
                .addCode(buildString {
                    appendLine("return when (schemaName) {")
                    schemas.forEach { (name, _) ->
                        appendLine("    \"$name\" -> ${name}TestData.sample()")
                    }
                    appendLine("    else -> null")
                    appendLine("}")
                })
                .build()
        )

        module.addProperty(
            PropertySpec.builder("schemaNames", ClassName("kotlin.collections", "List").parameterizedBy(String::class.asTypeName()))
                .initializer("listOf(${schemas.keys.joinToString { "\"$it\"" }})")
                .build()
        )

        file.addType(module.build())
        file.build().writeTo(outputDir)
    }
}

private fun String.toCamelCase(): String =
    split("_", "-").mapIndexed { i, part ->
        if (i == 0) part.lowercase() else part.replaceFirstChar { it.uppercase() }
    }.joinToString("")
