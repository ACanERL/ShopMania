package com.example.shopmania.ui.Success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopmania.R
import com.example.shopmania.databinding.FragmentSuccessScreenBinding
import com.example.shopmania.util.click
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessScreen : Fragment() {
    private lateinit var binding: FragmentSuccessScreenBinding
    private val successViewModel: SuccessViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentSuccessScreenBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            paymentSuccessButton.click {
                successViewModel.clearCart()
                val action=SuccessScreenDirections.actionSuccessScreenToHomeScreen()
                findNavController().navigate(action)
            }
        }
    }
}