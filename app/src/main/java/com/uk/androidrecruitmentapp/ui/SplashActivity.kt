package com.uk.androidrecruitmentapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.ui.main.MainActivity

class SplashActivity : Activity() {
    private val DELAY: Long = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, DELAY)
    }
}