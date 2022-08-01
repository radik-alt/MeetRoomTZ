package com.example.tz_nord_klan.presentation.Container.changeContainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tz_nord_klan.databinding.FragmentViewContainerBinding
import com.example.tz_nord_klan.presentation.Container.ContainerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ViewContainerFragment : BottomSheetDialogFragment() {


    private var _binding: FragmentViewContainerBinding? = null
    private val binding: FragmentViewContainerBinding
        get() = _binding ?: throw RuntimeException("FragmentViewContainerBinding == null")

    private val viewModel: ContainerViewModel by activityViewModels()

    override fun onResume() {
        viewModel.getContainer().observe(viewLifecycleOwner){
            binding.nameContainer.setText(it.container.nameRoom)
            binding.descriptionContainer.setText(it.container.descriptionRoom)
        }

        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewContainerBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}