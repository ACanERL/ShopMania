package com.example.shopmania.ui.Payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shopmania.R
import com.example.shopmania.databinding.FragmentPaymentScreenBinding
import com.example.shopmania.ui.cart.CartScreenDirections
import com.example.shopmania.util.click
import com.example.shopmania.util.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentScreen : Fragment() {
    private lateinit var binding: FragmentPaymentScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentPaymentScreenBinding.inflate(inflater,container,false)

      return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paymentoolbar.apply {
            toolbarTitle.gone()
            toolbarImageView.gone()
            favButton.gone()
            customToolbar.navigationIcon=null
        }

        paymetButton()
    }


    private fun paymetButton(){
        binding.buttonpay.click {
            val action= PaymentScreenDirections.actionPaymentScreenToSuccessScreen()
            findNavController().navigate(action)
        }
    }
}