package com.bashirli.lazastore.presentation.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.FragmentCartBinding

class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    override fun onViewCreateFinished() {

    }

    override fun setup() {
        binding.buttonArrow.setOnClickListener {

                if(binding.layoutOrderInfo.visibility==View.GONE){
                    binding.layoutOrderInfo.visible()
                    binding.buttonArrow.setIconResource(R.drawable.baseline_keyboard_arrow_down_24)
                }else{
                    binding.layoutOrderInfo.gone()
                    binding.buttonArrow.setIconResource(R.drawable.baseline_keyboard_arrow_up_24)
                }

        }
    }

}