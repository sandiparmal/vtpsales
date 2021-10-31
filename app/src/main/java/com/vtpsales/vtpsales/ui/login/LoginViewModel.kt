package com.vtpsales.vtpsales.ui.login

import androidx.hilt.Assisted
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vtpsales.vtpsales.data.repository.LoginRepository;
import dagger.Binds
import dagger.hilt.InstallIn
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.components.SingletonComponent

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    // Live Data
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val user = repository.getUser(_email.value.toString(), _password.value.toString())

    fun start(email : String, password : String) {
        _email.value = email
        _password.value = password
    }

}