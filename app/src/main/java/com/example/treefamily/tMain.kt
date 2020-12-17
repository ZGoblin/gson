package com.example.treefamily

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("isAdult") val isAdult: Boolean,
    @SerializedName("numberOfRelatives") val numberOfRelatives: Int,
    @SerializedName("mother") val mother: Person?,
    @SerializedName("father") val father: Person?
)

fun main() {
    val jsonString: String
    try {
        jsonString = File("app\\src\\main\\assets\\family.json").readText(Charsets.UTF_8)
    }
    catch (exception: FileNotFoundException) {
        println("File not found!")
        return
    }

    var gson = Gson()
    val token = object: TypeToken<MutableList<Person>>() {}.type
    val itsMe = gson.fromJson<MutableList<Person>>(jsonString, token)

    for (person in itsMe) {
        println(person)
    }

    val generatedJsonString = gson.toJson(itsMe)

    println(generatedJsonString)
}