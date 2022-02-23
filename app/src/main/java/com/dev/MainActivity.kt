package com.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.MyApplication
import com.dev.core.models.Children
import com.dev.core.repository.PostsRepository
import com.dev.databinding.ActivityMainBinding
import com.dev.ui.adapter.PostsAdapter
import com.dev.ui.base.BaseActivity
import com.dev.ui.bottomsheet.DefaultBottomsheetFragment
import com.dev.ui.paging.PostDataSource
import com.mvvmref.utils.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var repository: PostsRepository
    lateinit var adapter:PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        adapter = PostsAdapter(this)
        initAll()

    }


    suspend fun getAllCharacters(): Flow<PagingData<Children>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { PostDataSource(repository) }
    ).flow

    override fun initViews() {
        repository = PostsRepository(application as MyApplication)
    }

    override fun initListener() {

        binding.apply {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun setData() {
        lifecycleScope.launchWhenCreated {
            getAllCharacters().collectLatest {
                adapter.submitData(it)
            }
        }
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
