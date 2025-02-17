package com.ismailbelgacem.produit.presentation.update

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ismailbelgacem.produit.R
import com.ismailbelgacem.produit.data.model.Produit
import com.ismailbelgacem.produit.databinding.BottomBarAddUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurdProduct(val product:Produit?=null,val add:(Produit) -> Unit,val update:(Produit)->Unit): BottomSheetDialogFragment() {

    private lateinit var binding: BottomBarAddUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomBarAddUpdateBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        // dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modalBottomSheetBehavior = (this.dialog as BottomSheetDialog).behavior
        modalBottomSheetBehavior.isHideable = false
        modalBottomSheetBehavior.skipCollapsed = true
        initView()
        initEvents()
    }

    private fun initEvents() {
        binding.btnAdd.setOnClickListener {
            if (binding.etProduit.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show()
            }else{
                if (product!=null){
                    update(Produit(product.id ,binding.etProduit.text.toString(), isSelected = product.isSelected))
                    dismissNow()
                }else{
                    add(Produit(1,binding.etProduit.text.toString(), isSelected = false))
                    dismissNow()
                }

            }
        }
    }

    private fun initView() {
        if (product==null){
            binding.btnAdd.text=requireActivity().getText(R.string.add_new_product)
            binding.tvTitle.text=requireActivity().getText(R.string.add_product)
        }else{
            binding.btnAdd.text=requireActivity().getText(R.string.update_product)
            binding.etProduit.setText(product.name)
            binding.tvTitle.text=requireActivity().getText(R.string.update_product)
        }
    }
}