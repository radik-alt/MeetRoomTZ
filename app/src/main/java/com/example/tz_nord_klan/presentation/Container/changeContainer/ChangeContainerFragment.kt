package com.example.tz_nord_klan.presentation.Container.changeContainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.ContatinerWithEvent
import com.example.tz_nord_klan.databinding.FragmentChangeBinding
import com.example.tz_nord_klan.presentation.Container.SharedContainerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangeContainerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangeBinding? = null
    private val binding: FragmentChangeBinding
        get() = _binding ?: throw RuntimeException("FragmentChangeBinding == null")

    private val viewModelShared: SharedContainerViewModel by activityViewModels()
    private lateinit var changeViewModel : ChangeContainerViewModel
    private var containerObject : ContatinerWithEvent?= null
    private var isEdit: Boolean = false

    override fun onResume() {
        viewModelShared.getEdit().observe(viewLifecycleOwner){
            isEdit = it
        }

        if (isEdit){
            if (containerObject == null){
                containerObject = viewModelShared.getContainer().value
            }
        }

        setData()

        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeBinding.inflate(inflater, container, false)
        changeViewModel = ViewModelProvider(this).get(ChangeContainerViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.saveContainer.setOnClickListener {
            if (isEdit){
                updateContainerSetUsed()
            } else{
                addContainer()
            }
            dismiss()
        }

        binding.deleteContainer.setOnClickListener {
            deleteContainer()
            dismiss()
        }

        binding.setUsedContainer.setOnClickListener {
            updateContainerSetUsed()
            dismiss()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun deleteContainer(){
        changeViewModel.deleteContainer(containerObject!!.container)
    }

    private fun updateContainerSetUsed() {
        changeViewModel.setAllContainerUsedFalse()
        updateContainer(true)
    }

    private fun addContainer() {
        val nameRoom = binding.nameContainer.text.toString()
        val descriptionRoom = binding.descriptionContainer.text.toString()
        if (valid(nameRoom)){
            val container = Container(null, nameRoom, false, descriptionRoom)
            changeViewModel.addContainer(container)
            Toast.makeText(requireContext(), "Запись добавлена!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateContainer(used:Boolean){
        val nameRoom = binding.nameContainer.text.toString()
        val descriptionRoom = binding.descriptionContainer.text.toString()
        if (valid(nameRoom)){
            val container = Container(containerObject?.container?.idContainer, nameRoom, used, descriptionRoom)
            changeViewModel.updateContainer(container)
            Toast.makeText(requireContext(), "Запись обновлена!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setData() {
        if (isEdit){
            binding.nameContainer.setText(containerObject!!.container.nameRoom)
            binding.descriptionContainer.setText(containerObject!!.container.descriptionRoom)
        } else{
            binding.deleteContainer.visibility = View.GONE
            binding.setUsedContainer.visibility = View.GONE
        }

    }

    private fun valid(nameRoom: String): Boolean {
        if (nameRoom.isNotBlank()) {
            return true
        }

        Toast.makeText(requireContext(), "Напишите название комнаты!", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}