package com.example.tz_nord_klan.presentation.adapter.container

import androidx.recyclerview.widget.DiffUtil
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent

class DiffUtilsContainer(
    private val oldList: ArrayList<ContatinerWithEvent>,
    private val newList: ArrayList<ContatinerWithEvent>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].container.idContainer == newList[newItemPosition].container.idContainer
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].container == newList[newItemPosition].container
    }
}