package com.example.tpandroid.auth

import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.api.RetrofitTools.Companion.retrofit
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(var email: String, var password : String){

}

open class User(var email: String = "user@example.com", var password : String = "password", var pseudo: String = "User", var cityCode: String = "44000", var city : String = "Nantes", var phone : String = "0600000000"){

}

data class SignUpRequest(var passwordConfirm : String = "password") : User() {

}

interface AuthService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest) : ApiResponse<String>

    @POST("signup")
    suspend fun signup(@Body signUpRequest: SignUpRequest) : ApiResponse<User>

    object AuthApi {
        val authService : AuthService by lazy { retrofit.create(AuthService::class.java) }
    }
}