package br.com.gomescarlosdev.mvvm.repository

class UserRepository {


    fun login (email: String, password: String): Boolean {
        return email != "" && password != ""
    }

}