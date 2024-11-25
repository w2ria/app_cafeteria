package com.utp.app_cafeteria.config

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class MySupaClient {
    private val client = createSupabaseClient(
       supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpsc29lbHpveGdmdnp6dGhra2ZmIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTczMjQ5MDY5MiwiZXhwIjoyMDQ4MDY2NjkyfQ.BF7dMGA-rnu4m4KqDagUDz62RbQfHBIo8wdAa9-iNu8",
        supabaseUrl = "https://zlsoelzoxgfvzzthkkff.supabase.co",
    ){
        install(Postgrest)

    }
}