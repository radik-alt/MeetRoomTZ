package com.example.tz_nord_klan.presentation.LogIn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.presentation.viewModelDB
import com.example.tz_nord_klan.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    private val viewModelСonnectDB : viewModelDB by activityViewModels()

    private var listUser: ArrayList<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        viewModelСonnectDB.getListUser().observe(viewLifecycleOwner){
            listUser = it as ArrayList<User>
            Log.d("ResponseLogINsd00", it.toString())
        }
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
                viewModelСonnectDB.setAuthUser(i)
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