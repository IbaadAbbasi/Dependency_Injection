package com.example.dependencyinjectionexample.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjectionexample.repository.MainRepository
import com.example.dependencyinjectionexample.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val postStateFlow:MutableStateFlow<ApiState>
            = MutableStateFlow(ApiState.Empty)
    private var boolean = false

    val _postStateFlow:StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch{
        if (boolean){
            return@launch
        }
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost()
        .catch {e->
        postStateFlow.value = ApiState.Failure(e)
        }.collect{data->

        postStateFlow.value = ApiState.Success(data)
        }
        boolean = true
    }

}