package com.candybytes.taco.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.candybytes.taco.R
import com.candybytes.taco.databinding.FragmentFoodDetailsBinding

class FoodFragment : Fragment(R.layout.fragment_food_details) {

    private lateinit var binding: FragmentFoodDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoodDetailsBinding.bind(view)
        binding.apply {
            // stuff here
        }
    }
}