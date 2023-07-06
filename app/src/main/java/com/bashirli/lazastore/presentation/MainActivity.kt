package com.bashirli.lazastore.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup(){
        val navHost=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController= navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_,destination,_->
            with(binding.bottomNavigationView){
                when(destination.id){
                    R.id.splashFragment->   gone()
                    R.id.welcomeFragment->  gone()
                    R.id.loginFragment->    gone()
                    R.id.signUpFragment->   gone()
                    R.id.categoryFragment->   gone()
                    R.id.productFragment->   gone()
                    R.id.profileFragment->   gone()
                    else->{
                        if(visibility==View.GONE){
                            visible()
                        }
                    }
                }
            }
        }

    }

}