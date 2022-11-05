package com.instil

import com.squareup.kotlinpoet.*
import kotlin.reflect.KClass

fun main() {
    FileSpec.builder("", "KotlinPoetDemo")
        .addType(employeeType())
        .build()
        .writeTo(System.out)
}

private fun employeeType(): TypeSpec {
    return TypeSpec.classBuilder("Employee")
        .addProperty(employeeProperty("name", String::class))
        .addProperty(employeeProperty("age", Int::class))
        .addProperty(employeeProperty("salary", Double::class, true))
        .primaryConstructor(employeeConstructor())
        .addFunction(awardBonusFun())
        .addFunction(toStringFun())
        .build()
}

private fun employeeProperty(name: String, type: KClass<*>, isMutable: Boolean = false): PropertySpec {
    val spec = PropertySpec.builder(name, type).initializer(name)
    if(isMutable) {
        spec.mutable()
    }
    return spec.build()
}

private fun employeeConstructor(): FunSpec {
    val msg = "Created \$name";
    return FunSpec.constructorBuilder()
        .addParameter("name", String::class)
        .addParameter("age", Int::class)
        .addParameter("salary", Double::class)
        .addStatement("println(%P)", msg)
        .build()
}

private fun awardBonusFun(): FunSpec {
    return FunSpec.builder("awardBonus")
        .addCode(
            """
                    |if (age > 40) {
                    |    salary += 5000.0
                    |} else {
                    |    salary += 3000.0
                    |}
                    |""".trimMargin())
        .build()
}

private fun toStringFun(): FunSpec {
    val msg = "\$name of age \$age earning \$salary"
    return FunSpec.builder("toString")
        .addModifiers(KModifier.OVERRIDE)
        .addStatement("return %P", msg)
        .returns(String::class)
        .build()
}