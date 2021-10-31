package com.vtpsales.vtpsales.data.repository

import com.vtpsales.vtpsales.data.local.UserDao
import com.vtpsales.vtpsales.data.utils.performGetOperation
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: UserDao
){

    fun getUser(email : String, password : String) = performGetOperation(
        databaseQuery = { localDataSource.getUser() },
        networkCall = { remoteDataSource.executeUserLogin(email, password) },
        saveCallResult = { localDataSource.insertUser(it) }
    )
}