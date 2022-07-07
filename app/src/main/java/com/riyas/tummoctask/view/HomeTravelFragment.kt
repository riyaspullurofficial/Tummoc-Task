package com.riyas.tummoctask.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyas.tummoctask.R
import com.riyas.tummoctask.databinding.FragmentHomeTravelBinding
import com.riyas.tummoctask.model.travelapimodel.SubDirectionModel
import com.riyas.tummoctask.util.ApiState
import com.riyas.tummoctask.view.adapter.DirectionsAdapter
import com.riyas.tummoctask.viewmodel.DirectionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeTravelFragment : Fragment() {
    @Inject
    lateinit var postAdapter: DirectionsAdapter

    private val mainViewModel: DirectionViewModel by viewModels()

    private var _binding: FragmentHomeTravelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTravelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()

        mainViewModel.getPost()




        lifecycleScope.launchWhenStarted {
            mainViewModel.stateFlow.collect {
                when(it){
                    is ApiState.Loading->{
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        //
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    /* is ApiState.Success<*> ->{
                         binding.recyclerview.isVisible = true
                         binding.progressBar.isVisible = false
                         val result = it.result as DirectionModel
                         postAdapter.submitList(result.routes)
                     }*/

                    is ApiState.Success<*> ->{
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        val result = it.result as SubDirectionModel
                        /*postAdapter.submitList(result.routes)*/

                        result.routes.forEach { data->
                            postAdapter.submitList(data.legs)
                        }


                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerview() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(requireContext())
            adapter=postAdapter
        }
    }
}