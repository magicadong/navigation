package com.example.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_splash,
            container,
            false
        )
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main){
                if (isFirst()) {
                    findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
                }else{
                    val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                        20,User("jack",30))
                    findNavController().navigate(action)
                }
            }
        }
    }

    suspend fun isFirst():Boolean{
        val result = requireActivity().dataStore.data.first {
            true
        }
        return !(result[isFirstPreference]?:false)
    }

//    override fun onResume() {
//        super.onResume()
//        lifecycleScope.launch(Dispatchers.IO) {
//            delay(3000)
//            isFirst {
//                lifecycleScope.launch(Dispatchers.Main){
//                    findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
//                }
//            }
//        }
//    }
//
//    suspend fun isFirst(callBack:(Boolean)->Unit){
//        requireActivity().dataStore.data.first {
//            val result = it[isFirstPreference] ?: false
//            callBack(result)
//            true
//        }
//    }
}
