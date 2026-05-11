package com.example.bpcare.data.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object SupabaseManager {

    val client = createSupabaseClient(
        supabaseUrl = "https://hyjciqxcstozkeqdgkax.supabase.co/rest/v1/",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZi"
    ) {
        install(Auth)
        install(Postgrest)
    }
}