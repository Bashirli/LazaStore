package com.bashirli.lazastore.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

abstract class BaseFragment<VB:ViewBinding> (
    private val bindingInflater : (inflater:LayoutInflater) -> VB
        ): Fragment() {

    private var _binding:VB?=null
    val binding get()=_binding!!

    abstract fun onViewCreateFinished()
    abstract fun setup()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=bindingInflater.invoke(layoutInflater)
        if(_binding == null){
            throw IllegalArgumentException("Binding is null")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onViewCreateFinished()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }



}