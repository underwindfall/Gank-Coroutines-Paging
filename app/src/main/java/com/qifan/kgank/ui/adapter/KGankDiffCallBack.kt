package com.qifan.kgank.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.qifan.kgank.model.KGankResultsItem

/**
 * Created by Qifan on 21/03/2019.
 */
class KGankDiffCallBack : DiffUtil.ItemCallback<KGankResultsItem>() {
    override fun areItemsTheSame(oldItem: KGankResultsItem, newItem: KGankResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: KGankResultsItem, newItem: KGankResultsItem): Boolean {
        return oldItem == newItem
    }

}