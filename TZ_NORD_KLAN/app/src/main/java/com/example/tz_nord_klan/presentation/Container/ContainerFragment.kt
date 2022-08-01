package com.example.tz_nord_klan.presentation.Container

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.presentation.viewModelDB
import com.example.tz_nord_klan.databinding.ContainerFragmentBinding
import com.example.tz_nord_klan.presentation.Container.changeContainer.ChangeContainerFragment
import com.example.tz_nord_klan.presentation.Container.changeContainer.ViewContainerFragment
import com.example.tz_nord_klan.presentation.adapter.Interface.InterfaceContainer
import com.example.tz_nord_klan.presentation.adapter.container.ContainerAdapter
import kotlin.collections.ArrayList

class ContainerFragment : Fragment() {

    private var _binding: ContainerFragmentBinding? = null
    private val binding: ContainerFragmentBinding
        get() = _binding ?: throw RuntimeException("ContainerFragmentBinding == null")
    private val viewModel: ContainerViewModel by activityViewModels()
    private val viewModelConnectDb : viewModelDB by activityViewModels()

    private var listContainer : ArrayList<ContatinerWithEvent> = ArrayList()

    private lateinit var adapterContainer : ContainerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContainerFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onResume() {
        viewModelConnectDb.getContainerWithEventAndUser().observe(viewLifecycleOwner){
            if (listContainer.isNotEmpty()){
                listContainer.clear()
            }
            listContainer.addAll(it)
            setAdapter()
        }

        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.addContainer.setOnClickListener {
            viewModel.setEdit(false)
            bottomDialog(0)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setAdapter() {
        adapterContainer = ContainerAdapter(object : InterfaceContainer{
            override fun onClickContainer(container: ContatinerWithEvent, choose: Int) {
                if (choose == 0){
                    viewModel.setContainer(container)
                    viewModel.setEdit(true)
                } else if (choose==1) {
                    viewModel.setContainer(container)
                    viewModel.setEdit(false)
                }

                bottomDialog(choose)
            }

        })
        adapterContainer.setData(listContainer)
        binding.recyclerContainer.adapter = adapterContainer

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerContainer)

    }

    private fun updateAdapter() {
        adapterContainer.setData(listContainer)
    }

    private val simpleCallback = object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModelConnectDb.getContainerWithEventAndUserById(listContainer[viewHolder.adapterPosition].container.idContainer!!).observe(viewLifecycleOwner){
                Log.d("NewGetContainer", it.toString())
            }
        }
    }

    private fun bottomDialog (choose:Int) {
        if (choose == 0){
            ChangeContainerFragment().show(parentFragmentManager, "changeContainer")
        } else if (choose == 1) {
            ViewContainerFragment().show(parentFragmentManager, "viewContainer")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_container, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.doneContainer) {
            findNavController().popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}