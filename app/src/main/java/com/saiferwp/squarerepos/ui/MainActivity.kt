package com.saiferwp.squarerepos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saiferwp.squarerepos.R
import com.saiferwp.squarerepos.ui.repos.ReposFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    ReposFragment.newInstance()
                )
                .commitNow()
        }
    }

}
