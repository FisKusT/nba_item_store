package com.fiskus.nbaitemsstore.screens.items_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.fiskus.nbaitemsstore.R
import com.fiskus.nbaitemsstore.databinding.ItemsListFragmentBinding
import com.fiskus.nbaitemsstore.model.StoreItem
import com.fiskus.nbaitemsstore.ui.MainViewModel

class ItemsListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ItemsListFragmentBinding
    private lateinit var menuItemHandleHM: HashMap<MenuItem, ()->Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //init options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)

        //init menu HM
        menuItemHandleHM = hashMapOf()
        menuItemHandleHM[menu.findItem(R.id.logout)] = logout
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        menuItemHandleHM[item]?.let { it() }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //init binding
        binding = ItemsListFragmentBinding.inflate(inflater, container, false)

        //init view model
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        //observe items list
        viewModel.itemsList.observe(viewLifecycleOwner, { itemsList ->
            itemsList.forEach { storeItem ->
                addItemViewToLayout(storeItem)
            }
        })

        //add btn click listener
        binding.addFAB.setOnClickListener(Navigation.createNavigateOnClickListener(ItemsListFragmentDirections.actionItemsListFragmentToDetailsFragment().actionId))

        return binding.root
    }

    //add item view
    private fun addItemViewToLayout(storeItem: StoreItem) {
        val itemTV = TextView(context)
        itemTV.text = storeItem.toString()
        binding.listLayout.addView(itemTV)
    }

    //menu item functions
    //logout
    private var logout = {findNavController().navigate(ItemsListFragmentDirections.actionItemsListFragmentToLogin().actionId)}

}