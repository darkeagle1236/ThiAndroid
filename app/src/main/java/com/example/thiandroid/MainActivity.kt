package com.example.thiandroid

import Data
import UserResponses
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thiandroid.network.Endpoint
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var dataList = ArrayList<Data>()
    var currentPage:Int = 1
    fun hideProgress(){
        progress_circular.visibility = View.GONE
        recyclerview.visibility = View.VISIBLE
    }
    private fun showProgress(){
        progress_circular.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData(currentPage)
        swipeRefresh.setOnRefreshListener {
            getData(currentPage)
            swipeRefresh.isRefreshing = false
        }
        initScrollListener()

    }
    private fun getData(page:Int){
        showProgress()
        val request = API.buildService(Endpoint::class.java)
        val call = request.getUser(currentPage)

        call.enqueue(object : Callback<UserResponses> {
            override fun onResponse(call: Call<UserResponses>, response: Response<UserResponses>) {
                if (response.isSuccessful){
                    recyclerview.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        dataList.addAll(response.body()!!.data)
                        adapter = UserAdapter(dataList,this@MainActivity)
                        currentPage++
                    }
                    hideProgress()
                }
            }
            override fun onFailure(call: Call<UserResponses>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                hideProgress()
            }
        })
    }
    private fun initScrollListener() {
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataList.size - 1) {
                        getData(currentPage)
                    }
            }
        })
    }
    }
