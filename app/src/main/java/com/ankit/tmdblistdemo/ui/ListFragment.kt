package com.ankit.tmdblistdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ankit.tmdblistdemo.R
import com.ankit.tmdblistdemo.adapter.MovieClickListener
import com.ankit.tmdblistdemo.adapter.MoviePagedlistAdapter
import com.ankit.tmdblistdemo.databinding.FragmentListBinding
import com.ankit.tmdblistdemo.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(), MovieClickListener {

    private lateinit var swipeLayout: SwipeRefreshLayout
    private val args: ListFragmentArgs by navArgs()
    private val compositeDisposable = CompositeDisposable()
    private val movieAdapter = MoviePagedlistAdapter(this)

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.home)
//        movieViewModel.refresh()
        movieViewModel.fetchPagedList(compositeDisposable, args.minYear, args.maxYear, args.genres)
        hideKeyboard()
        swipeLayout = binding.swipeRefresh
        setupRecyclerView()
        observe()
        setBindings()

    }

    private fun setBindings() {
        binding.swipeRefresh.setOnRefreshListener {
            movieViewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun observe() {
        movieViewModel.moviePagedList.observe(viewLifecycleOwner, {
            movieAdapter.submitList(it)

        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            layoutManager = GridLayoutManager(
                context,
                2,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    override fun onItemClick(item: Movie, position: Int) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
        findNavController().navigate(action)

    }

    override fun getFragmentView() = R.layout.fragment_list
}