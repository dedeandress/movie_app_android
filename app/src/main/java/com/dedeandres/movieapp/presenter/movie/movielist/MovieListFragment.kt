package com.dedeandres.movieapp.presenter.movie.movielist

import androidx.fragment.app.viewModels
import com.dedeandres.scaffoldproject.common.EventObserver
import com.dedeandres.movieapp.common.ProgressDialogUtil
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.ResourceState
import com.dedeandres.movieapp.common.base.BaseViewModelFragment
import com.dedeandres.movieapp.databinding.FragmentMovieListBinding
import com.dedeandres.movieapp.presenter.movie.movielist.entity.GenreResult
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MovieListFragment : BaseViewModelFragment<FragmentMovieListBinding, MovieListViewModel>() {
    override val viewModel: MovieListViewModel by viewModels()

    override fun getViewBinding(): FragmentMovieListBinding {
        return FragmentMovieListBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        viewModel.fetchGenreList()
        viewModel.fetchNowPlayingMovie()

        viewModel.fetchGenreListLiveData.observe(viewLifecycleOwner, EventObserver(::handleFetchGenreList))
        viewModel.fetchNowPlayingMovieListLiveData.observe(viewLifecycleOwner, EventObserver(::handleFetchNowPlayingMovie))
    }

    private fun handleFetchNowPlayingMovie(result: Resource<List<MovieResult>>) {
        when(result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchNowPlayingMovie Loading")

                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchNowPlayingMovie Success: ${result.data}")
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchNowPlayingMovie Error: ${result.exception}")
            }
        }
    }

    private fun handleFetchGenreList(result: Resource<List<GenreResult>>) {
        when(result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchGenreList Loading")

                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchGenreList Success: ${result.data}")
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchGenreList Error: ${result.exception}")
            }
        }
    }

}