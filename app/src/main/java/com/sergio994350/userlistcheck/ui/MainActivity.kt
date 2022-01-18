package com.sergio994350.userlistcheck.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sergio994350.userlistcheck.R
import com.sergio994350.userlistcheck.adapter.RecyclerViewAdapter
import com.sergio994350.userlistcheck.model.DataUser
import com.sergio994350.userlistcheck.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initMainViewModel()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this) {
            recyclerViewAdapter.listDataUser = it
        }

        val buttonRefreshData = findViewById<FloatingActionButton>(R.id.button_refresh_data)
        buttonRefreshData.setOnClickListener {
            Log.d("logging", "refresh Data from API")
            initViewModel()
            initMainViewModel()
        }

//        [prepared button to add new user if need]
//        val buttonAddNewUser = findViewById<FloatingActionButton>(R.id.button_add_user)
//        buttonAddNewUser.setOnClickListener {
//            Log.d("logging", "Add New User")
//            val intent = EditUserActivity.newIntentAddDataUser(this)
//            startActivity(intent)//
//        }
    }

    private fun initViewModel() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            Log.d("logging", "prepare ViewModel")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer<List<DataUser>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
        Log.d("logging", "make Main ViewModel")

        // setup click listener for edit
        recyclerViewAdapter.onDataUserClickListener = {
            // log (element id) to click
            Log.d("logging", "click by " + it.id.toString())
            val firstname = it.first_name
            val lastname = it.last_name
            val email = it.email
            val avatar = it.avatar
            val intent = EditUserActivity
                .newIntentEditDataUser(this, it.id, firstname, lastname, email, avatar)
            startActivity(intent)
        }
    }
}