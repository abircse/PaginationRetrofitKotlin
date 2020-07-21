package com.example.recyclerviewpagination

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewpagination.ApiClient.apiClient
import com.example.recyclerviewpagination.adapter.PaginationAdapter
import com.example.recyclerviewpagination.databinding.ActivityMainBinding
import com.example.recyclerviewpagination.model.Data
import com.example.recyclerviewpagination.model.Response
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private var list: List<Data>? = null
    private var adapter: PaginationAdapter? = null
    private var apiInterface: ApiInterface? = null
    private var layoutManager: LinearLayoutManager? = null
    private var page_number = 1
    private val item_count = 8
    private var isLoading = true
    private var pastVisibleItems = 0
    private var VisibleItemCount = 0
    private var TotalItemCount = 0
    private var previousTotal = 0
    private val view_thereshold = 8


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        apiInterface = apiClient!!.create(ApiInterface::class.java)
        binding.recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager


        // Network Call
        list = ArrayList()
        binding.progressBar.setVisibility(View.VISIBLE)
        val call = apiInterface!!.fetchData(page_number, item_count)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                binding.progressBar.setVisibility(View.GONE)
                list = response.body()!!.data
                adapter = PaginationAdapter(list as MutableList<Data>?, this@MainActivity)
                binding.recyclerview.setAdapter(adapter)
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                binding.progressBar.setVisibility(View.GONE)
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        // REYCLERVIEW Scroll listerner
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                VisibleItemCount = layoutManager!!.childCount
                TotalItemCount = layoutManager!!.itemCount
                pastVisibleItems = layoutManager!!.findFirstVisibleItemPosition()
                if (dy > 0) {
                    if (isLoading) {
                        if (TotalItemCount > previousTotal) {
                            isLoading = false
                            previousTotal = TotalItemCount
                        }
                    }
                    if (!isLoading && TotalItemCount - VisibleItemCount <= pastVisibleItems + view_thereshold) {
                        page_number++
                        PerformPagination()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun PerformPagination() {
        binding.progressBar.visibility = View.VISIBLE
        val call = apiInterface!!.fetchData(page_number, item_count)
        call.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.body()!!.status == 200) {
                    binding.progressBar.visibility = View.GONE
                    list = response.body()!!.data
                    adapter!!.addpeople(list!!)
                    //Toast.makeText(MainActivity.this, "Page "+page_number+" is loaded", Toast.LENGTH_SHORT).show();
                } else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "No More Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}