package com.example.shopmania.ui.Product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopmania.R
import com.example.shopmania.data.model.ProductResponseModel
import com.example.shopmania.databinding.FragmentProductScreenBinding
import com.example.shopmania.util.Resource
import com.example.shopmania.util.gone
import com.example.shopmania.util.toastMessage
import com.example.shopmania.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductScreen : Fragment() {
    private lateinit var binding: FragmentProductScreenBinding
    private val args by navArgs<ProductScreenArgs>()
    private val productViewModel: ProductViewModel by viewModels()
    private val productAdapter by lazy { ProductAdapter(productOnClick = ::productOnClick)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.getCategoryProduct(args.category)
        initObserver()

    }
    private fun initObserver(){
            productViewModel.getCategoryProduct.observe(viewLifecycleOwner){productresponse->
                when(productresponse){
                    is Resource.Success -> {
                        binding.apply {
                            productsNameTextView.visible()
                            productsProgressBar.gone()
                        }
                        productresponse.data?.let {
                            initAdapter(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.productsNameTextView.gone()
                        requireContext().toastMessage(getString(R.string.somethings_wrong))
                    }
                    is Resource.Loading ->{
                        binding.productsProgressBar.visible()
                    }
                    else -> {}
                }
            }
    }
    private fun initAdapter(product: List<ProductResponseModel>){
        binding.apply {
            productsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            productsNameTextView.text = args.category
            productsRecyclerView.adapter = productAdapter
        }
        productAdapter.bind(product)
    }
    private fun productOnClick(product: ProductResponseModel){
      val action=ProductScreenDirections.actionProductScreenToDetailScreen(product)
        findNavController().navigate(action)
    }
}