package com.example.movies_coroutines.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.movies_coroutines.R
import com.example.movies_coroutines.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(MainRepository())
        ).get(MainViewModel::class.java)
        viewModel.filmesLiveData.observe(viewLifecycleOwner, Observer { filmes ->
            binding.textViewFilmes.text = filmes[0].titulo
        })

        viewModel.getFilmesCoroutines()

    }

}