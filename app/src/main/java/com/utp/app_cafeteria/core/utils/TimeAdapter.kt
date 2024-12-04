package com.utp.app_cafeteria.core.utils

import java.sql.Time

class TimeAdapter : com.google.gson.JsonDeserializer<Time>, com.google.gson.JsonSerializer<Time> {
    override fun deserialize(json: com.google.gson.JsonElement, typeOfT: java.lang.reflect.Type, context: com.google.gson.JsonDeserializationContext): Time {
        return Time.valueOf(json.asString)
    }

    override fun serialize(src: Time, typeOfSrc: java.lang.reflect.Type, context: com.google.gson.JsonSerializationContext): com.google.gson.JsonElement {
        return com.google.gson.JsonPrimitive(src.toString())
    }
}
