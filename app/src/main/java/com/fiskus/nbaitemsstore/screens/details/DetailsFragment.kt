package com.fiskus.nbaitemsstore.screens.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.fiskus.nbaitemsstore.R
import com.fiskus.nbaitemsstore.databinding.DetailsFragmentBinding
import com.fiskus.nbaitemsstore.model.StoreItem
import com.fiskus.nbaitemsstore.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class DetailsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: DetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //init binding
        binding = DetailsFragmentBinding.inflate(inflater, container, false)

        //init shared view model
        viewModel  = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        //add click listener
        binding.addFAB.setOnClickListener {
            try {
                //add item
                viewModel.addStoreItem(getStoreItemFromInputs())

                //navigate to list
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToItemsListFragment().actionId)

            } catch (e: Exception) {
                Snackbar.make(it, R.string.details_error, Snackbar.LENGTH_SHORT).show()
            }
        }

        //init type list
        binding.typeACTV.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item_dropdown, StoreItem.Type.values()))

        //init currency list
        binding.currencyACTV.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item_dropdown, StoreItem.Currency.values()))

        return binding.root
    }

    private fun getStoreItemFromInputs() = StoreItem(
        name = binding.nameET.text.toString(),
        type = StoreItem.Type.valueOf(binding.typeACTV.text.toString()),
        price = binding.priceET.text.toString().toDouble(),
        currency = StoreItem.Currency.valueOf(binding.currencyACTV.text.toString()))
}