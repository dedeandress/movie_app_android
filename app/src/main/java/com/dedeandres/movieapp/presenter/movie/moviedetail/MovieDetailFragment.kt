package com.dedeandres.movieapp.presenter.movie.moviedetail

import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.common.ProgressDialogUtil
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.ResourceState
import com.dedeandres.movieapp.common.base.BaseViewModelFragment
import com.dedeandres.movieapp.databinding.FragmentMovieDetailBinding
import com.dedeandres.movieapp.presenter.movie.moviedetail.adapter.CastCrewAdapter
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.CastCrewResult
import com.dedeandres.movieapp.presenter.movie.moviedetail.entity.MovieDetailResult
import com.dedeandres.scaffoldproject.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MovieDetailFragment :
    BaseViewModelFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {
    override val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var adapter: CastCrewAdapter

    override fun getViewBinding(): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        Timber.d("initViews MovieDetailFragment")
        adapter = CastCrewAdapter()
        binding.rvCastCrew.adapter = adapter
        val movieId = arguments?.getString("movieId")
        viewModel.fetchMovieDetail(movieId ?: "")
        viewModel.fetchMovieCredit(movieId ?: "")

    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()

        viewModel.fetchMovieDetailLiveData.observe(viewLifecycleOwner, EventObserver(::handleFetchMovieDetail))
        viewModel.fetchMovieCreditLiveData.observe(viewLifecycleOwner, EventObserver(::handleFetchMovieCredit))
    }

    private fun handleFetchMovieCredit(result: Resource<List<CastCrewResult>>) {
        when(result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchMovieDetail LOADING")
            }
            ResourceState.SUCCESS -> {
                Timber.d("handleFetchMovieDetail SUCCESS: ${result.data}")
                result.data?.let {
                    adapter.bind(it)
                }
            }
            ResourceState.ERROR -> {
                Timber.d("handleFetchMovieDetail ERROR: ${result.exception}")

            }
        }
    }

    private fun handleFetchMovieDetail(result: Resource<MovieDetailResult>){
        when(result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchMovieDetail LOADING")
                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                Timber.d("handleFetchMovieDetail SUCCESS: ${result.data}")

                result.data?.let {
                    if(!it.adult) {
                        binding.tvIsAdult.visibility = View.GONE
                    }else {
                        binding.tvIsAdult.visibility = View.VISIBLE
                    }

                    binding.tvPlotSummary.text = it.overview
                    binding.tvVoteAverage.text = getString(R.string.vote_average, it.voteAverage)
                    binding.tvVoteCount.text = getString(R.string.vote_count, it.voteCount)
                    binding.tvTitle.text = it.title
                    binding.tvReleaseYear.text = it.releaseDate.subSequence(0, 4)
                    Glide.with(binding.root)
                        .load("https://image.tmdb.org/t/p/original" + it.backdropPath)
                        .into(binding.ivPoster)
                }

                ProgressDialogUtil.hideProgressDialog()
            }
            ResourceState.ERROR -> {
                Timber.d("handleFetchMovieDetail ERROR: ${result.exception}")

                ProgressDialogUtil.hideProgressDialog()
            }
        }
    }


}