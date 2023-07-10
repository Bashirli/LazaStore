package com.bashirli.lazastore.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.ActivityMainBinding
import com.bashirli.lazastore.databinding.HeaderLayoutBinding
import com.bashirli.lazastore.domain.model.UserModel
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val viewModel by viewModels<MainMVVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
        setup()
    }

    private fun setup(){
        val navHost=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController= navHost.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.navigationView.setupWithNavController(navController)


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
                    R.id.searchFragment->   gone()
                    else->{
                        if(visibility==View.GONE){
                            visible()
                        }

                    }
                }

                when(destination.id){
                    R.id.homeFragment->binding.navigationView.visible()
                    else->{
                        if(binding.navigationView.visibility==View.VISIBLE){
                            binding.navigationView.gone()
                        }
                    }
                }

            }
        }

    }

    private fun observeData(){
        viewModel.apply {
            currentData.observe(this@MainActivity){
                when(it.status){
                    Status.ERROR->{
                        MotionToast.createColorToast(
                            this@MainActivity,
                            resources.getString(R.string.error),
                            it.message?:"Error",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this@MainActivity,R.font.raleway_regular)
                            )
                    }
                    Status.SUCCESS->{
                        it.data?.let {
                            setData(it)
                        }
                    }
                    Status.LOADING->{

                    }
                }
            }
        }
    }

    private fun setData(data:UserModel){
        val headerBinding=HeaderLayoutBinding.bind(binding.navigationView.getHeaderView(0))
        headerBinding.userModel=data
    }


}