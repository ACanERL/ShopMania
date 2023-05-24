package com.example.shopmania.ui.Profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopmania.R
import com.example.shopmania.databinding.FragmentProfilScreenBinding
import com.example.shopmania.util.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilScreen : Fragment() {
    private lateinit var binding: FragmentProfilScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfilScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileToolbar.apply {
            favButton.gone()
            toolbarTitle.gone()
            toolbarImageView.gone()
            customToolbar.navigationIcon=null
        }
    }
}