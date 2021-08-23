package com.fiskus.nbaitemsstore.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fiskus.nbaitemsstore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {

    private lateinit var binding: InstructionsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init binding
        binding = InstructionsFragmentBinding.inflate(inflater, container, false)

        //set FAB click listener- navigation to instruction page
        binding.nextABtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                InstructionsFragmentDirections.actionInstructionsFragmentToItemsListFragment().actionId))

        // Inflate the layout for this fragment
        return binding.root
    }

}