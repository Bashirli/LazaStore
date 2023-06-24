package com.bashirli.lazastore.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bashirli.lazastore.R
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

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

        navController.addOnDestinationChangedListener{_,destination,_->
            with(binding.bottomNavigationView){
                when(destination.id){
                    R.id.splashFragment->   visibility=View.GONE
                    R.id.welcomeFragment->  visibility=View.GONE
                    R.id.loginFragment->    visibility=View.GONE
                    R.id.signUpFragment->   visibility=View.GONE
                    else->{
                        if(visibility==View.GONE){
                            visibility=View.VISIBLE
                        }
                    }
                }
            }
        }

    }

}