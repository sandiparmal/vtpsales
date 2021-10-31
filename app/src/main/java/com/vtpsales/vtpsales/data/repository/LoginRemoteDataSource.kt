package com.vtpsales.vtpsales.data.repository

import com.vtpsales.vtpsales.data.remote.BaseDataSource
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginService: LoginService
): BaseDataSource() {

    suspend fun executeUserLogin(email: String, password: String) = getResult { loginService.executeLogin(email, password) }
    suspend fun getCharacter(id: Int) = getResult { loginService.getCharacter(id) }
}
