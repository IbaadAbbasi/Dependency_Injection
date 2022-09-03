package com.example.dependencyinjectionexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjectionexample.adapter.HelloTest
import com.example.dependencyinjectionexample.adapter.PostAdapter
import com.example.dependencyinjectionexample.databinding.ActivityMainBinding
import com.example.dependencyinjectionexample.utils.ApiState
import com.example.dependencyinjectionexample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        mainViewModel.getPost()
        /*val test = HelloTest()
        test.goPilot()*/
        lifecycleScope.launchWhenCreated {
        mainViewModel._postStateFlow.collect{it->
        when(it){
        is ApiState.Loading->{
            binding.recyclerview.isVisible=false
            binding.progressBar.isVisible=true
        }
            is ApiState.Failure->{
                binding.recyclerview.isVisible = false
                binding.progressBar.isVisible = false
                Log.d("main", "onCreate: ${it.msg}")
            }
            is ApiState.Success->{
                binding.recyclerview.isVisible = true
                binding.progressBar.isVisible = false
                postAdapter.setData(it.data)
                postAdapter.notifyDataSetChanged()
            }
            is ApiState.Empty->{

            }
        }
        }
        }


    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(ArrayList())
        binding.recyclerview.apply {
        layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

    }
}