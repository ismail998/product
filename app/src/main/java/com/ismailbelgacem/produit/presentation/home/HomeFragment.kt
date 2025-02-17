package com.ismailbelgacem.produit.presentation.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ismailbelgacem.produit.core.collectLatestLifecycleFlow
import com.ismailbelgacem.produit.databinding.FragmentHomeBinding
import com.ismailbelgacem.produit.presentation.home.adapter.AdapterProduit
import com.ismailbelgacem.produit.presentation.home.viewModel.ViewModel
import com.ismailbelgacem.produit.presentation.update.CurdProduct
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: AdapterProduit

    private val viewModel by viewModels<ViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        initClicks()

        collectData()
    }

    private fun initClicks() {
        binding.btnAdd.setOnClickListener {
            CurdProduct(null, add = {
                viewModel.addProduit(it)
            }, update = {}).show(requireActivity().supportFragmentManager, "tag-add")
        }
        binding.btnDelete.setOnClickListener {
            viewModel.list.clear()
            mAdapter.submitList(emptyList())
            mAdapter.notifyDataSetChanged()
        }
    }


    private fun initAdapter() {
        mAdapter = AdapterProduit({
            CurdProduct(it, add = {
            }, update = {
                viewModel.updateProduit(it)
            }).show(requireActivity().supportFragmentManager, "tag-update")
        }, {
            Log.e("TAG", "initAdapter:clicks change", )
            viewModel.selectedUn(it)
        })

        binding.rvProduct.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun collectData() {
        requireActivity().collectLatestLifecycleFlow(viewModel.produitAdd) {
            mAdapter.submitList(it)
            mAdapter.notifyDataSetChanged()
        }
        requireActivity().collectLatestLifecycleFlow(viewModel.produitUpdate) {
            mAdapter.submitList(viewModel.list)
            mAdapter.notifyItemChanged(it)
        }
    }

}

