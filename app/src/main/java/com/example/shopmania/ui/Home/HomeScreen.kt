package com.example.shopmania.ui.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopmania.R
import com.example.shopmania.databinding.FragmentHomeScreenBinding
import com.example.shopmania.util.Resource
import com.example.shopmania.util.gone
import com.example.shopmania.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private lateinit var binding:FragmentHomeScreenBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy { HomeAdapter(onCategoryClick = ::onCategoryClick) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      binding.homeToolbar.apply {
                customToolbar.navigationIcon=null
                favButton.gone()
      }
        initObserver()
    }
    private fun initObserver(){
        homeViewModel.getCategoryList.observe(viewLifecycleOwner){response->
            when(response){
                is Resource.Success->{
                    binding.apply {
                        homeTextView.text = getString(R.string.categories)
                        homeProgressBar.gone()
                    }
                    response.data.let {
                        initAdapter(it!!)
                    }
                }
                is Resource.Error->{
                    binding.apply {
                        homeTextView.gone()
                        homeProgressBar.gone()
                    }
                }
                is Resource.Loading->{
                    binding.apply {
                        homeProgressBar.visible()
                    }
                }
                else->{}
            }
        }
    }
    private fun initAdapter(list: List<String>) {
        binding.homeRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.homeRecyclerView.adapter = homeAdapter
        homeAdapter.submitList(list)
    }
    private fun onCategoryClick(category: String) {
        var action=HomeScreenDirections.actionHomeScreenToProductScreen(category)
        findNavController().navigate(action)
    }
}