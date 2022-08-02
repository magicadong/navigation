package com.example.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_welcome,
            container,
            false
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener {v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                changeIsFirstStatus()
                findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
            }
            true
        }
    }

    fun changeIsFirstStatus(){
        lifecycleScope.launch(Dispatchers.IO){
            requireActivity().dataStore.edit {
                it[isFirstPreference] = true
            }
        }
    }
}