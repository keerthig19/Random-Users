package com.example.randomusers.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusers.model.User
import com.example.randomusers.repo.UsersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: UsersRepo) : ViewModel() {

    private val _usersList = MutableStateFlow<List<User?>>(listOf())
    val usersList = _usersList

    //    api call to get random users list
    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getUsers()
            response.peekData()?.apply {
                results?.let {
                    _usersList.emit(it)
                }
            }
        }
    }

}