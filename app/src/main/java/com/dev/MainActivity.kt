package com.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.MyApplication
import com.dev.core.repository.PostsRepository
import com.dev.databinding.ActivityMainBinding
import com.dev.ui.base.BaseActivity
import com.mvvmref.utils.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var repository: PostsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initAll()

    }

    override fun initViews() {
        repository = PostsRepository(application as MyApplication)
    }

    override fun initListener() {
        binding.btnHitApi.setOnClickListener {
            hitPostsApi()
        }
    }

    override fun setData() {

    }

    private fun hitPostsApi() {
        lifecycleScope.launch(Dispatchers.IO) {
            repository.getRedditPosts().collect {
              withContext(Dispatchers.Main){
                  when (it.status) {
                      ApiStatus.SUCCESS -> {
                          hideLoader()
                          it.data?.let { data->
                              if (data.data.children.isNotEmpty()){
                                  showMessage(data.data.children[0].data.title)
                              }
                          }
                      }
                      ApiStatus.ERROR -> {
                          hideLoader()
                          showMessage(it.message?:"")
                      }
                      ApiStatus.LOADING -> {
                          showLoader()
                      }
                  }
              }
            }
        }
    }
}