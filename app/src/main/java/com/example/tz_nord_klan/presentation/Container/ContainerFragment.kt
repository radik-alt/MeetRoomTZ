package com.example.tz_nord_klan.presentation.Container

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
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

    private val viewModelShared: SharedContainerViewModel by activityViewModels()
    private lateinit var containerViewModel: ContainerViewModel

    private var listContainer : ArrayList<ContatinerWithEvent> = ArrayList()

    private lateinit var adapterContainer : ContainerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContainerFragmentBinding.inflate(inflater, container, false)
        containerViewModel = ViewModelProvider(this).get(ContainerViewModel::class.java)
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onResume() {
        containerViewModel.getContainer().observe(viewLifecycleOwner){
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
            viewModelShared.setEdit(false)
            bottomDialog(0)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setAdapter() {
        adapterContainer = ContainerAdapter(object : InterfaceContainer{
            override fun onClickContainer(container: ContatinerWithEvent, choose: Int) {
                if (choose == 0){
                    viewModelShared.setContainer(container)
                    viewModelShared.setEdit(true)
                } else if (choose==1) {
                    viewModelShared.setContainer(container)
                    viewModelShared.setEdit(false)
                }

                bottomDialog(choose)
            }

        })
        adapterContainer.setData(listContainer)
        binding.recyclerContainer.adapter = adapterContainer

    }

    private fun updateAdapter() {
        adapterContainer.setData(listContainer)
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