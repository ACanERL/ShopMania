package com.example.shopmania.ui.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopmania.R
import com.example.shopmania.databinding.FragmentDetailScreenBinding
import com.example.shopmania.util.click
import com.example.shopmania.util.toastMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {
    private lateinit var binding: FragmentDetailScreenBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            detailToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            addCartButton.click {
                detailViewModel.addProductToCart()
                requireContext().toastMessage(getString(R.string.click_cart_button_message))
            }

            favButton.click {
                detailViewModel.setFavorite()
            }
        }
        initObserver()
    }

    private fun initObserver(){

        detailViewModel.product.observe(viewLifecycleOwner){
            binding.product = it
        }

        detailViewModel.favState.observe(viewLifecycleOwner){
            binding.favButton.setImageResource(
                if (it.first) R.drawable.fav_icon_fill
                else R.drawable.baseline_favorite_border_24
            )
            if (it.second){
                requireContext().toastMessage(
                    getString(
                        if (it.first) R.string.success_fav_button_message
                        else R.string.delete_from_favorites_message
                    )
                )
            }
        }
    }

}