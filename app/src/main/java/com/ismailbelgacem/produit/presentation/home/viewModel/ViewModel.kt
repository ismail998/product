package com.ismailbelgacem.produit.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailbelgacem.produit.data.model.Produit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor() : ViewModel() {

    private val _produitAdd = Channel<MutableList<Produit>>()
    val produitAdd = _produitAdd.receiveAsFlow()

    private val _produitUpdate = Channel<Int>()
    val produitUpdate = _produitUpdate.receiveAsFlow()

    var list: MutableList<Produit> = mutableListOf()


    fun addProduit(produit: Produit) {
        produit.id = (list.size + 1)
        list.add(0, produit)
        viewModelScope.launch {
            _produitAdd.send(list)
        }
    }

    fun updateProduit(produit: Produit) {
        var item = list.find { it.id == produit.id }
        var index = list.indexOf(item)
        if (item != null)
            list[index] = produit
        viewModelScope.launch {
            _produitUpdate.send(index)
        }
    }

    fun selectedUn(produit: Produit) {
        var item = list.find { it.id == produit.id }
        var index = list.indexOf(item)
        if (item != null)
            list[index].isSelected = !item.isSelected
    }

    fun sortByAlphab() {
        list.sortBy { it.name }
        viewModelScope.launch {
            _produitAdd.send(list)
        }
    }
    fun sortByAsian() {
        list.sortBy { it.aisleNumber }
        viewModelScope.launch {
            _produitAdd.send(list)
        }
    }


}