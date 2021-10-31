package com.example.rickandmorty.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vtpsales.vtpsales.data.local.AppDatabase
import com.vtpsales.vtpsales.data.local.UserDao
import com.vtpsales.vtpsales.data.repository.LoginRemoteDataSource
import com.vtpsales.vtpsales.data.repository.LoginRepository
import com.vtpsales.vtpsales.data.repository.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://vtpsales.com/vtpsales/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(loginService: LoginService) = LoginRemoteDataSource(loginService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: LoginRemoteDataSource,
                          localDataSource: UserDao) =
        LoginRepository(remoteDataSource, localDataSource)
}