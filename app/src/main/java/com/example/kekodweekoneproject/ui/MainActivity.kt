package com.example.kekodweekoneproject.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.kekodweekoneproject.R
import com.example.kekodweekoneproject.databinding.ActivityMainBinding
import com.example.kekodweekoneproject.ui.switchscreen.SwitchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        enableEdgeToEdge()

        switchViewModel = ViewModelProvider(this).get(SwitchViewModel::class.java)

        switchViewModel.switchState.observe(this) { isEgo ->
            toggleBottomNavVisibility(isEgo.ego)
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnItemSelectedListener { a->
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(
                    navController.graph.findStartDestination().id,
                    false,
                    saveState = true
                )
                .setRestoreState(true)
                .build()
            navController.navigate(a.itemId, null, navOptions)
            true
        }
    }

//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager= supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.)
//    }

    fun toggleBottomNavVisibility(isEgo: Boolean) {
        if (isEgo) {
            binding.bottomNav.visibility = View.GONE
        } else {
            binding.bottomNav.visibility = View.VISIBLE
        }
    }
}

