package com.certified.dependencyinjectionexamplehilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.certified.dependencyinjectionexamplehilt.databinding.ActivityMainBinding
import com.certified.dependencyinjectionexamplehilt.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = this.supportFragmentManager.beginTransaction()
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction
            .replace(binding.fragmentContainerView.id, ProfileFragment())
            .addToBackStack(null)
            .commit()
    }
}