package com.example.tz_nord_klan.presentation.Event

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.Container
import com.example.tz_nord_klan.data.entity.EventWithUser
import com.example.tz_nord_klan.data.entity.User
import com.example.tz_nord_klan.presentation.viewModelDB
import com.example.tz_nord_klan.databinding.EventHomeFragmentBinding
import com.example.tz_nord_klan.presentation.Event.changeEvent.ChangeEventFragment
import com.example.tz_nord_klan.presentation.adapter.Interface.InterfaceEvent
import com.example.tz_nord_klan.presentation.adapter.event.EventAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.days

class EventHomeFragment : Fragment() {

    private var _binding: EventHomeFragmentBinding? = null
    private val binding: EventHomeFragmentBinding
        get() = _binding ?: throw RuntimeException("EventHomeFragmentBinding == null")

    private val viewModelShared: SharedEventViewModel by activityViewModels()
    private val viewModelConnectDb : viewModelDB by activityViewModels()

    private var selectDate = Calendar.getInstance()

    private val fullListEvent: ArrayList<EventWithUser> = ArrayList()
    private val sortListEvent: ArrayList<EventWithUser> = ArrayList()

    private var user: User?=null
    private var container: Container?=null

    private var isMyEvent = false

    override fun onResume() {

        viewModelConnectDb.getContainerWithEventAndUserByUsed().observe(viewLifecycleOwner){ it ->
            if (fullListEvent.isNotEmpty()){
                fullListEvent.clear()
            }
            fullListEvent.addAll(it.event)
            sortListByDate()


            container = it.container
            viewModelShared.setContainer(container!!)
            binding.room.text = container!!.nameRoom
        }


        viewModelConnectDb.getAuthUser().observe(viewLifecycleOwner){
            user = it
        }

        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventHomeFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectDate.setOnClickListener {
            if (binding.calendar.visibility == View.VISIBLE){
                binding.calendar.visibility = View.GONE
            } else if (binding.calendar.visibility == View.GONE) {
                binding.calendar.visibility = View.VISIBLE
            }

            val calendar = binding.calendar
            calendar.init(selectDate.get(Calendar.YEAR),
                selectDate.get(Calendar.MONTH), selectDate.get(Calendar.DAY_OF_MONTH), null)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                calendar.setOnDateChangedListener { datePicker, year, mounth, daouOfMouth ->
                    selectDate.set(year, mounth, daouOfMouth)
                    sortListByDate()
                    binding.selectDate.text = convertDate(selectDate.time)
                }
            }

        }

        binding.myEvent.setOnClickListener {
            if (!isMyEvent){
                isMyEvent = true
                sortListByUser()
                binding.myEvent.text = "Все события"
            } else{
                isMyEvent = false
                binding.myEvent.text = "Мои события"
                sortListByDate()
            }
        }

        binding.ToFragmentContainer.setOnClickListener {
            findNavController().navigate(R.id.action_eventHomeFragment_to_containerFragment)
        }

        binding.todayEvent.setOnClickListener {
            sortListByDateToday()
        }

    }

    private fun bottomDialog () {
        ChangeEventFragment().show(parentFragmentManager, "eventBottom")
    }

    private fun convertDate(date: Date): String =
        DateFormat.format("MM:dd:yyyy", date).toString()


    private fun setAdapter(list:List<EventWithUser>){
        val eventAdapter = EventAdapter(list, object : InterfaceEvent{
            override fun onClickEvent(event: EventWithUser) {
                viewModelShared.setIsEdit(true)
                viewModelShared.setEvent(event)
                findNavController().navigate(R.id.action_eventHomeFragment_to_changeEventFragment)
            }
        })
        binding.eventRecycler.adapter = eventAdapter
    }

    private fun sortListByDateToday(){
        selectDate = Calendar.getInstance()
        binding.selectDate.text = "Сегодня"
        sortListByDate()
    }

    private fun sortListByDate(){

        val sortDateList = fullListEvent.filter { sortDateVariable ->  sortDateVariable.event.dateEvent.date.days == selectDate.time.date.days }
        sortListEvent.clear()
        sortListEvent.addAll(sortedByTime(sortDateList))

        setAdapter(sortListEvent)
    }

    private fun sortedByTime(list: List<EventWithUser>): List<EventWithUser> {
        return list.sortedWith(compareBy { it.event.timeEventStart })
    }

    private fun sortListByUser(){

        val listUserEvent = ArrayList<EventWithUser>()
        for (i in sortListEvent){
            for (j in i.playlists){
                if (j.idUser == user?.idUser){
                    listUserEvent.add(i)
                }
            }
        }

        sortListEvent.clear()
        sortListEvent.addAll(sortedByTime(listUserEvent))

        setAdapter(sortListEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val bottomSheet = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.bottomsheet_setting)

            val logOut = bottomSheet.findViewById<Button>(R.id.logout)
            logOut?.setOnClickListener {
                findNavController().navigate(R.id.action_eventHomeFragment_to_logInFragment)
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        } else if (item.itemId == R.id.add){
            bottomDialog()
            viewModelShared.setIsEdit(false)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}