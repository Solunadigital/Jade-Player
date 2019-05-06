// Copyright (c) 2019 . Wilberforce Uwadiegwu. All Rights Reserved.

package com.jadebyte.jadeplayer.main.playback


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.jadebyte.jadeplayer.R
import com.jadebyte.jadeplayer.databinding.FragmentBottomPlaybackBinding
import com.jadebyte.jadeplayer.main.MainFragmentDirections
import com.jadebyte.jadeplayer.main.common.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_bottom_playback.*

class BottomPlaybackFragment : BaseFragment() {

    lateinit var viewModel: BottomPlaybackViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[BottomPlaybackViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentBottomPlaybackBinding>(
            inflater,
            R.layout.fragment_bottom_playback,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playbackSeekBar.setOnTouchListener { _, _ ->
            return@setOnTouchListener true
        }

        clickableView.setOnClickListener {
            val transitionName = ViewCompat.getTransitionName(sharableView)!!
            val extras = FragmentNavigator.Extras.Builder().addSharedElement(sharableView, transitionName).build()
            val action =
                MainFragmentDirections.actionMainFragmentToPlaybackFragment(viewModel.song.get()!!, transitionName)
            activity?.findNavController(R.id.mainNavHostFragment)?.navigate(action, extras)
        }
    }


}