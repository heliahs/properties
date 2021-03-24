package com.hh.averageprice.views

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hh.averageprice.MainActivity
import com.hh.averageprice.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AveragePriceFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isAveragePriceFragmentVisible_onAppLaunch() {
        Espresso.onView(withId(R.id.swipe_refresh))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun test_isQuestionVisible(){
        Espresso.onView(withId(R.id.list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



}
