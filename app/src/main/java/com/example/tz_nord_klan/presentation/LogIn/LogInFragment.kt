package com.example.tz_nord_klan.presentation.LogIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.databinding.FragmentLogInBinding
import com.example.tz_nord_klan.presentation.SharedViewModelAuth

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    private val sharedViewModelAuth : SharedViewModelAuth by activityViewModels()
    private lateinit var viewModelLogIn : LogInViewModel

    private var listUser: ArrayList<User> = ArrayList()

    override fun onResume() {
        viewModelLogIn.getListUser().observe(viewLifecycleOwner){list->
            if (listUser.isNotEmpty()){
                listUser.clear()
            }
            listUser.addAll(list)
        }
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        viewModelLogIn = ViewModelProvider(this).get(LogInViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.go.setOnClickListener {
            val name = binding.login.text.toString()
            val password = binding.password.text.toString()

            if (valid(name, password)){
                findNavController().navigate(R.id.action_logInFragment_to_eventHomeFragment)
            } else {
                Toast.makeText(requireContext(), "Не правильный логин или пароль!", Toast.LENGTH_SHORT).show()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun valid (name: String, password: String):Boolean {
        if (name.isBlank() and password.isBlank())
            return false

        for (i in listUser) {
            if (i.nameUser == name && i.hashPassword == password) {
                sharedViewModelAuth.setAuthUser(i)
                return true
            }
        }
        return false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}