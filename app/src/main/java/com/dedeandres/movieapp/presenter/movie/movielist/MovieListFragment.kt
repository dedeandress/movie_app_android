package com.dedeandres.movieapp.presenter.movie.movielist

import androidx.fragment.app.viewModels
import com.dedeandres.movieapp.R
import com.dedeandres.movieapp.common.ProgressDialogUtil
import com.dedeandres.movieapp.common.Resource
import com.dedeandres.movieapp.common.ResourceState
import com.dedeandres.movieapp.common.base.BaseViewModelFragment
import com.dedeandres.movieapp.databinding.FragmentMovieListBinding
import com.dedeandres.movieapp.presenter.movie.movielist.adapter.MovieListAdapter
import com.dedeandres.movieapp.presenter.movie.movielist.adapter.MovieMenuAdapter
import com.dedeandres.movieapp.presenter.movie.movielist.entity.GenreResult
import com.dedeandres.movieapp.presenter.movie.movielist.entity.MovieResult
import com.dedeandres.scaffoldproject.common.EventObserver
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MovieListFragment : BaseViewModelFragment<FragmentMovieListBinding, MovieListViewModel>(),
    MovieMenuAdapter.OnItemClickListener, MovieListAdapter.OnItemClickListener {
    override val viewModel: MovieListViewModel by viewModels()

    private lateinit var movieMenuAdapter: MovieMenuAdapter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun getViewBinding(): FragmentMovieListBinding {
        return FragmentMovieListBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()

        viewModel.fetchGenreList()

        viewModel.fetchGenreListLiveData.observe(
            viewLifecycleOwner,
            EventObserver(::handleFetchGenre)
        )
        viewModel.fetchMovieListLiveData.observe(
            viewLifecycleOwner,
            EventObserver(::handleFetchMovie)
        )

        setupMovieMenuAdapter()
        setupMovieListAdapter()
        checkCurrentStateMovieMenu()
    }

    private fun checkCurrentStateMovieMenu() {
        when (movieMenuAdapter.checkedPosition) {
            0 -> viewModel.fetchNowPlayingMovie()
            1 -> viewModel.fetchTopRatedMovie()
            else -> viewModel.fetchUpcomingMovie()
        }
    }

    private fun setupMovieMenuAdapter() {
        movieMenuAdapter = MovieMenuAdapter()
        binding.rvMovieMenu.adapter = movieMenuAdapter

        movieMenuAdapter.setOnItemClickListener(this)

        movieMenuAdapter.bind(resources.getStringArray(R.array.movie_menu).toList())
    }

    private fun setupMovieListAdapter() {
        movieListAdapter = MovieListAdapter()
        binding.rvMovieList.adapter = movieListAdapter

        movieListAdapter.setOnItemClickListener(this)
    }

    private fun handleFetchMovie(result: Resource<List<MovieResult>>) {
        when (result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchMovie Loading")

                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchMovie Success: ${result.data}")

                result.data?.let {
                    movieListAdapter.bind(it)
                }
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchMovie Error: ${result.exception}")
            }
        }
    }

    private fun handleFetchGenre(result: Resource<List<GenreResult>>) {
        when (result.state) {
            ResourceState.LOADING -> {
                Timber.d("handleFetchGenreList Loading")

                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchGenreList Success: ${result.data}")

                result.data?.forEach { it ->
                    val chip = layoutInflater.inflate(
                        R.layout.layout_chip_style,
                        binding.cgGenre,
                        false
                    ) as Chip
                    chip.text = it.name
                    chip.id = it.id
                    chip.tag = it.id
                    binding.cgGenre.addView(chip)
                }

                binding.cgGenre.isSelectionRequired = true


            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                Timber.d("handleFetchGenreList Error: ${result.exception}")
            }
        }
    }

    override fun onMovieMenuClick(movieMenu: String) {
        Timber.d("clicked $movieMenu")

        when (movieMenu) {
            BOX_OFFICE -> {
                viewModel.fetchTopRatedMovie()
            }
            COMING_SOON -> {
                viewModel.fetchUpcomingMovie()
            }
            NOW_PLAYING -> {
                viewModel.fetchNowPlayingMovie()
            }
        }
    }

    override fun onMovieItemClick(movieMenu: String) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val BOX_OFFICE = "Box Office"
        private const val NOW_PLAYING = "Now Playing"
        private const val COMING_SOON = "Coming Soon"
    }
}