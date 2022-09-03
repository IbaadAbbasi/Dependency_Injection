package com.example.dependencyinjectionexample.repository

import com.example.dependencyinjectionexample.model.Post
import com.example.dependencyinjectionexample.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {


    fun getPost(): kotlinx.coroutines.flow.Flow<List<Post>> = flow {

        emit(apiServiceImp.getPost())
    }.flowOn(Dispatchers.IO)

}