package com.example.tz_nord_klan.presentation.adapter.container

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.presentation.adapter.Interface.InterfaceContainer
import java.lang.RuntimeException

class ContainerAdapter(private val interfaceContainer: InterfaceContainer) : RecyclerView.Adapter<ViewHolderContainer>() {

    private var containerList: ArrayList<ContatinerWithEvent> = ArrayList<ContatinerWithEvent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContainer {
        val layout = when (viewType) {
            USED_ITEM -> R.layout.container_item_used
            NOT_USED_ITEM -> R.layout.container_item
            else -> throw RuntimeException("ViewType Error: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolderContainer(view)
    }

    override fun onBindViewHolder(holder: ViewHolderContainer, position: Int) {

        holder.name.text = containerList[position].container.nameRoom
        holder.description.text = containerList[position].container.descriptionRoom

        holder.itemView.setOnClickListener {
            interfaceContainer.onClickContainer(containerList[position], 0)
        }

        holder.itemView.setOnLongClickListener {
            interfaceContainer.onClickContainer(containerList[position], 1)
            false
        }
    }

    override fun getItemCount(): Int = containerList.size

    override fun getItemViewType(position: Int): Int {
        return if (containerList[position].container.isUsed){
            USED_ITEM
        } else{
          NOT_USED_ITEM
        }
    }

    fun setData(newList:ArrayList<ContatinerWithEvent>){
        val diffUtil = DiffUtilsContainer(containerList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        containerList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    companion object{
        private const val NOT_USED_ITEM = 0
        private const val USED_ITEM = 1
    }
}