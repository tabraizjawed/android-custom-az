package com.mvvm.aie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.aie.R
import com.mvvm.aie.model.Product

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var products: MutableList<Product> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addData(products: List<Product>) {
        if (this.products != null) {
            this.products.addAll(products)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.get(position)
        holder.textView.text = product.title
        Glide.with(holder.imageView.context).load(product.image).into(holder.imageView)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }


}