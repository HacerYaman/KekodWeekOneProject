package com.example.kekodweekoneproject.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.databinding.ActivityMainBinding
import com.example.kekodweekoneproject.ui.switchscreen.SwitchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var switchViewModel: SwitchViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        switchViewModel = ViewModelProvider(this).get(SwitchViewModel::class.java)

        switchViewModel.switchState.observe( this) { isEgo ->
            toggleBottomNavVisibility(isEgo.ego)
        }
    }

    fun toggleBottomNavVisibility(isEgo: Boolean){
        if (isEgo){
            binding.bottomNav.visibility= View.GONE
        }else{
            binding.bottomNav.visibility= View.VISIBLE
        }
    }
}