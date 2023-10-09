package com.mvvm.aie.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.aie.AIEApplication
import com.mvvm.aie.R
import com.mvvm.aie.db.DatabaseHelper
import com.mvvm.aie.db.DatabaseProvider
import com.mvvm.aie.utils.NetworkResult
import com.mvvm.aie.viewmodel.ProductsViewModel
import com.mvvm.aie.viewmodel.ViewModeFactory

class MainActivity : AppCompatActivity() {

    lateinit var productsViewModel: ProductsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var productsAdapter: ProductsAdapter
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter()
        recyclerView.adapter = productsAdapter

        textView = findViewById<TextView>(R.id.text)

        val repository = (application as AIEApplication).productsRepository

        val db = DatabaseProvider.getInstance(this)
        val databaseHelperImpl = DatabaseHelper(db)

        productsViewModel = ViewModelProvider(this, ViewModeFactory(repository, databaseHelperImpl))
            .get(ProductsViewModel::class.java)


        productsViewModel.getProducts()

        productsViewModel.products.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    textView.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    textView.visibility = View.GONE
                    productsAdapter.addData(it.data!!)
                }


                else -> NetworkResult.Error(null, it!!.message.toString())
            }
        }
    }
}