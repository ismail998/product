package com.ismailbelgacem.produit.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ismailbelgacem.produit.core.getEmojiForProduct
import com.ismailbelgacem.produit.data.model.Produit
import com.ismailbelgacem.produit.databinding.ItemProductBinding


class AdapterProduit(val clickProduit: (Produit) -> Unit, val changeCb: (Produit) -> Unit) :
    ListAdapter<Produit, AdapterProduit.MyViewHolder>(AdapterProduit.DiffUtilCallBacks()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            parent.context,
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    inner class MyViewHolder(val context: Context, val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myItem: Produit, position: Int) {
            binding.cbProdact.setOnCheckedChangeListener(null)
            binding.cbProdact.isChecked = myItem.isSelected
            if (myItem.isSelected) {
                binding.cbProdact.paintFlags =
                    binding.cbProdact.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding.cbProdact.paintFlags =
                    binding.cbProdact.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            binding.cbProdact.text = "${myItem.aisleNumber} ${myItem.name}"
            binding.cbProdact.setOnCheckedChangeListener { _, isChecked ->
              //  myItem.isSelected = isChecked
                if (isChecked) {
                    binding.cbProdact.paintFlags =
                        binding.cbProdact.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    binding.cbProdact.paintFlags =
                        binding.cbProdact.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                changeCb(myItem)
            }
            binding.root.setOnClickListener {
                clickProduit(myItem)
            }
            binding.icOfProduit.text = getEmojiForProduct(myItem.name)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position) as? Produit
        if (item != null) {
            holder.bind(item, position)
        }
    }

    class DiffUtilCallBacks() : DiffUtil.ItemCallback<Produit>() {
        override fun areItemsTheSame(oldItem: Produit, newItem: Produit): Boolean =
            newItem == oldItem

        override fun areContentsTheSame(oldItem: Produit, newItem: Produit): Boolean =
            oldItem.id == newItem.id
    }
}