package com.yusuferkamozyer.graphqlpaging.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuferkamozyer.graphqlpaging.R
import com.yusuferkamozyer.graphqlpaging.databinding.FragmentFeedBinding
import com.yusuferkamozyer.graphqlpaging.domain.apollo.CharacterClient
import com.yusuferkamozyer.graphqlpaging.presentation.feed.adapter.FeedAdapter
import com.yusuferkamozyer.graphqlpaging.presentation.feed.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private val viewModel:FeedViewModel by viewModels()
    @Inject
    lateinit var feedAdapter: FeedAdapter
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var characterClient: CharacterClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val charList=characterClient.downloadCharacters(4)
            withContext(Dispatchers.Main){
                println(charList)
            }
        }

        viewModel.charList.observe(viewLifecycleOwner, Observer {
            binding.recyclerFeedView.layoutManager=LinearLayoutManager(requireContext())
            feedAdapter.submitData(viewLifecycleOwner.lifecycle,it)
            binding.recyclerFeedView.adapter=feedAdapter
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            println("loading is :$it")
        })

    }
}