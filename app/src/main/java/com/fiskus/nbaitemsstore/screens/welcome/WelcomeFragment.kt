package com.fiskus.nbaitemsstore.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fiskus.nbaitemsstore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init binding
        binding = WelcomeFragmentBinding.inflate(inflater, container, false)

        //set FAB click listener- navigation to instruction page
        binding.nextABtn.setOnClickListener(Navigation.createNavigateOnClickListener(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment().actionId))

        // Inflate the layout for this fragment
        return binding.root
    }
}