package com.ankit.tmdblistdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ankit.tmdblistdemo.BaseApplication.Companion.detailViewOpen
import com.ankit.tmdblistdemo.R
import com.ankit.tmdblistdemo.databinding.FragmentDetailBinding
import com.ankit.tmdblistdemo.models.Movie
import com.ankit.tmdblistdemo.util.BACKGROUND_BASE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private val myCollectionViewModel: MyCollectionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (detailViewOpen) {
            findNavController().popBackStack()
        }
        detailViewOpen = true
        requireActivity().title = getString(R.string.details)
        setHasOptionsMenu(true)
        setBindings()
        setBackgroundImage(view)
    }

    private fun setBackgroundImage(view: View) {
        val backgroundPoster = args.movieDetails.backdropPath
        Glide.with(view)
            .load(BACKGROUND_BASE_URL + backgroundPoster)
            .into(binding.backgroundPoster)
    }

    private fun setBindings() {
        binding.movie = args.movieDetails
        binding.addToCollection.setOnClickListener {
            myCollectionViewModel.insert(binding.movie as Movie)
        }
    }

    override fun getFragmentView() = R.layout.fragment_detail
}