package com.certified.dependencyinjectionexamplehilt.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.certified.dependencyinjectionexamplehilt.databinding.ActivityMainBinding
import com.certified.dependencyinjectionexamplehilt.view.profile.ProfileFragment
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