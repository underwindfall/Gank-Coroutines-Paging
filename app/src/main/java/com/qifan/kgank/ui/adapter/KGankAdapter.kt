package com.qifan.kgank.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qifan.kgank.R
import com.qifan.kgank.model.KGankResultsItem
import kotlinx.android.synthetic.main.item_kgank_content.view.*

/**
 * Created by Qifan on 21/03/2019.
 */
class KGankAdapter : PagedListAdapter<KGankResultsItem, KGankAdapter.KGankViewHolder>(KGankDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KGankAdapter.KGankViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kgank_content, parent, false)
        return KGankViewHolder(view)
    }

    override fun onBindViewHolder(holder: KGankAdapter.KGankViewHolder, position: Int) {
        val entity = getItem(position)
        entity?.let { holder.bindView(position, it) }
    }

    inner class KGankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int, item: KGankResultsItem) {
            with(item) {
                with(itemView) {
                    var iconText = "无"
                    if (!TextUtils.isEmpty(who)) {
                        iconText = who!!.trim().substring(0, 1).toUpperCase()
                    }
                    tvIcon.text = iconText
                    val name = if (TextUtils.isEmpty(who)) "无名氏" else who
                    tvNameDate.text = "$name  ${createdAt}"
                    tvSummary.text = desc
                }
            }
        }
    }

}