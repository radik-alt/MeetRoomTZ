package com.example.tz_nord_klan.presentation.Event.changeEvent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.*
import com.example.tz_nord_klan.databinding.FragmentChangeEventBinding
import com.example.tz_nord_klan.presentation.Event.SharedEventViewModel
import com.example.tz_nord_klan.presentation.SharedViewModelAuth
import com.example.tz_nord_klan.presentation.adapter.event.UserEventAdapter.UserEventAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class ChangeEventFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangeEventBinding? = null
    private val binding: FragmentChangeEventBinding
        get() = _binding ?: throw RuntimeException("FragmentChangeEventBinding == null")

    private val viewModelShared: SharedEventViewModel by activityViewModels()
    private val viewModelDbConnect: SharedViewModelAuth by activityViewModels()
    private lateinit var changeViewModel : ChangeEventViewModel

    private var isEdit : Boolean = false
    private var isUserUsed :Boolean = false
    private var objectEvent : EventWithUser? = null

    private var currentDate = Calendar.getInstance()
    private var timeStart : Long = 0
    private var timeEnd :Long = 0

    private var user:User ?= null
    private var container: Container ?= null

    override fun onResume() {
        viewModelShared.getIsEdit().observe(viewLifecycleOwner){
            isEdit = it
        }

        if(isEdit){
            viewModelShared.getEvent().observe(viewLifecycleOwner){
                if (objectEvent==null){
                    objectEvent = it
                }
            }

            viewModelDbConnect.getAuthUser().observe(viewLifecycleOwner){
                user = it
            }
        }

        viewModelShared.getContainer().observe(viewLifecycleOwner){
            container = it
        }

        if (objectEvent != null && user!=null) {
            for (i in objectEvent?.playlists!!){
                if (i.idUser == user!!.idUser){
                    isUserUsed = true
                    changeButtonAddUser()
                }
            }
        }

        setData()


        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeEventBinding.inflate(inflater, container, false)
        changeViewModel = ViewModelProvider(this).get(ChangeEventViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.timeEventClick.setOnClickListener {
            TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val tempStartTime = ((hour * 60) + minute).toLong()
                TimePickerDialog(it.context, TimePickerDialog.OnTimeSetListener { timePicker2, hour2, minute2 ->
                    val tempEndTime = ((hour2 * 60) + minute2).toLong()
                    if (validTimer(tempStartTime, tempEndTime)){
                        timeStart = tempStartTime
                        timeEnd = tempEndTime
                        binding.timeEventStart.text = convertLongToTime(timeStart)
                        binding.timeEventEnd.text = convertLongToTime(timeEnd)
                    }
                }, 0, 0, true).show()
            }, 0, 0, true).show()
        }

        binding.dateEventClick.setOnClickListener {
            val yearC = currentDate.get(Calendar.YEAR)
            val monthC = currentDate.get(Calendar.MONTH)
            val dayC = currentDate.get(Calendar.DAY_OF_MONTH)

            val dateDialog = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                currentDate.set(year, monthOfYear, dayOfMonth)
                binding.dateEvent.text = convertDate(currentDate.time)
            }, yearC, monthC, dayC)
            dateDialog.show()
        }

        binding.saveEvent.setOnClickListener {
            if (isEdit){
                updateEvent()
            } else {
                addEvent()
            }
        }

        binding.deleteEvent.setOnClickListener {
            deleteEvent()
        }

        binding.addUser.setOnClickListener {
            if (isUserUsed){
                deleteUserToEvent()
                isUserUsed = false
            } else{
                addUserToEvent()
                isUserUsed = true
            }
            changeButtonAddUser()
            updateListUser()
            setAdapterUser()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun updateEvent() {
        val nameEvent = binding.nameEvent.text.toString()
        if (valid(nameEvent)){
            if (!validTimer(timeStart, timeEnd)){
                timeStart = objectEvent?.event!!.timeEventStart
                timeEnd = objectEvent?.event!!.timeEventEnd
            }

            val event = Event(
                objectEvent?.event?.idEvent,
                nameEvent,
                currentDate.time,
                timeStart,
                timeEnd,
                objectEvent!!.event.listEventId
            )
            changeViewModel.updateEvent(event)
        }
    }

    private fun addEvent(){
        val nameEvent = binding.nameEvent.text.toString()
        if (valid(nameEvent) && validTimer(timeStart, timeEnd)){
            val event = Event(null, nameEvent, currentDate.time, timeStart, timeEnd, container?.idContainer!!)
            changeViewModel.addEvent(event)
        }
    }

    private fun deleteEvent() {
        val event = Event(
            objectEvent?.event?.idEvent,
            objectEvent?.event!!.nameEvent,
            objectEvent!!.event.dateEvent,
            objectEvent!!.event.timeEventStart,
            objectEvent!!.event.timeEventEnd,
            objectEvent!!.event.listEventId
        )
        changeViewModel.deleteEvent(event)
        dismiss()
        Toast.makeText(requireContext(), "Event delete!", Toast.LENGTH_SHORT).show()
    }

    private fun changeButtonAddUser(){
        if (isUserUsed){
            binding.addUser.background = requireContext().getDrawable(R.drawable.sign_up_disabled)
            binding.addUser.text = "Отписаться"
        } else {
            binding.addUser.background = requireContext().getDrawable(R.drawable.sign_up)
            binding.addUser.text = "Подписаться"
        }
    }

    private fun addUserToEvent(){
        if (isEdit){
            changeViewModel.addUserToEvent(
                EventUserRef(
                    objectEvent?.event!!.idEvent!!,
                    user?.idUser!!
                )
            )
        }
    }

    private fun deleteUserToEvent(){
        if (isUserUsed){
            changeViewModel.deleteUserToEvent(
                EventUserRef(
                    objectEvent?.event!!.idEvent!!,
                    user?.idUser!!
                )
            )
        }
    }

    private fun setData(){
        if (isEdit){
            currentDate.time = objectEvent?.event!!.dateEvent

            binding.nameEvent.setText(objectEvent?.event!!.nameEvent)
            binding.dateEvent.text = convertDate(objectEvent?.event!!.dateEvent)
            binding.timeEventStart.text = convertLongToTime(objectEvent?.event!!.timeEventStart)
            binding.timeEventEnd.text = convertLongToTime(objectEvent?.event!!.timeEventEnd)
            setAdapterUser()
        } else{
            binding.deleteEvent.visibility = View.GONE
            binding.addUser.visibility = View.GONE
            binding.dateEvent.text = convertDate(currentDate.time)
            binding.timeEventStart.text = convertLongToTime(timeStart)
            binding.timeEventEnd.text = convertLongToTime(timeEnd)
        }
    }

    private fun updateListUser(){
        viewModelShared.getEvent().observe(viewLifecycleOwner){
            if (objectEvent==null){
                objectEvent = it
            }
        }
    }

    private fun setAdapterUser(){
        binding.recyclerUserEvent.adapter = UserEventAdapter(objectEvent?.playlists!!)
    }

    private fun convertLongToTime(time: Long): String = String.format("%02d:%02d", time / 60, time % 60)

    private fun convertDate(date: Date): String = DateFormat.format("MM:dd:yyyy", date.time).toString()

    private fun valid(nameEvent: String): Boolean {
        if (nameEvent.isNotEmpty()) return true
        return false
    }

    private fun validTimer(start:Long, end:Long):Boolean{
        if (start > end) {
            Toast.makeText(requireContext(), "Начало не может быть позже конца события!", Toast.LENGTH_SHORT).show()
            return false
        } else if (end - start <= 30){
            Toast.makeText(requireContext(), "Событие должно проходить минимум 30 минут!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}