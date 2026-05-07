package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://oumtrgabexaqidqupamq.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im91bXRyZ2FiZXhhcWlkcXVwYW1xIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc5MTcxMTQsImV4cCI6MjA5MzQ5MzExNH0.3_Y3j2EwlIw3H4UoVZ4kjC_5GvsQhkeUA0Xs_20PAvc"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}
