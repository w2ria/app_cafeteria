package com.utp.app_cafeteria.config

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class MySupaClient {
    private val supabaseKey = System.getenv("SUPABASE_KEY") ?: "default_key"

    private val client = createSupabaseClient(
        supabaseKey = supabaseKey,
        supabaseUrl = "https://tnzshzxssdtgthhbzmwk.supabase.co"
    ) {
        install(Postgrest)
    }

    fun getData() {
    }
}