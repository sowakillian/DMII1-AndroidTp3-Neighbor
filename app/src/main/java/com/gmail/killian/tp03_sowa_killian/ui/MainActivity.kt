package com.gmail.killian.tp03_sowa_killian.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.communicators.MainCommunicator
import com.gmail.killian.tp03_sowa_killian.databinding.ActivityMainBinding
import com.gmail.killian.tp03_sowa_killian.di.DI
import com.gmail.killian.tp03_sowa_killian.listeners.NavigationListener
import com.gmail.killian.tp03_sowa_killian.models.Neighbor
import com.gmail.killian.tp03_sowa_killian.ui.fragments.ListNeighborsFragment
import com.gmail.killian.tp03_sowa_killian.ui.fragments.NeighborDetailFragment

class MainActivity :
    AppCompatActivity(),
    NavigationListener,
    MainCommunicator
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DI.inject(application)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        showFragment(ListNeighborsFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        binding.toolbar.setTitle(title)
    }

    override fun passNeighbor(neighbor: Neighbor) {
        val bundle = Bundle()
        bundle.putParcelable("neighbor", neighbor)

        val neighborDetailFragment = NeighborDetailFragment()
        neighborDetailFragment.arguments = bundle

        showFragment(neighborDetailFragment)
    }

}
