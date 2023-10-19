package com.yusuferkamozyer.graphqlpaging.presentation.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.graphqlpaging.R
import com.yusuferkamozyer.graphqlpaging.databinding.ActivityMainBinding.inflate
import com.yusuferkamozyer.graphqlpaging.databinding.FeedRecyclerRowBinding
import com.yusuferkamozyer.graphqlpaging.domain.model.CharacterUiState
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter
import com.yusuferkamozyer.graphqlpaging.util.downloadImage
import com.yusuferkamozyer.graphqlpaging.util.placeHolderProgressBar
import javax.inject.Inject

class FeedAdapter @Inject constructor(): PagingDataAdapter<SimpleCharacter, FeedAdapter.FeedHolder>(Comparator) {
    class FeedHolder(val binding: FeedRecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        getItem(position)?.let {simpleCharacter ->
            holder.binding.characterName.text=simpleCharacter.name
            holder.binding.characterImage.downloadImage(simpleCharacter.image,
                placeHolderProgressBar(holder.itemView.context)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        val binding = FeedRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeedHolder(binding)

    }
    object Comparator: DiffUtil.ItemCallback<SimpleCharacter>() {
        override fun areItemsTheSame(oldItem: SimpleCharacter, newItem: SimpleCharacter): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SimpleCharacter,
            newItem: SimpleCharacter,
        ): Boolean {
            return oldItem==newItem
        }

        override fun getChangePayload(oldItem: SimpleCharacter, newItem: SimpleCharacter): Any? {
            return super.getChangePayload(oldItem, newItem)
            if (oldItem != newItem) {
                return newItem
            }
            return super.getChangePayload(oldItem, newItem)
        }

    }
}