package com.technischools.myapplication

import android.graphics.Bitmap
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DomesticDevicesTest {
    @Test
    fun washingAndVacuumInteractions() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            onView(withId(R.id.washingProgramText)).check(matches(withText("Numer prania: nie podano")))
            onView(withId(R.id.vacuumStateText)).check(matches(withText("Odkurzacz wyłączony")))
            capture("mobile1")

            onView(withId(R.id.washingProgramInput)).perform(replaceText("4"))
            closeSoftKeyboard()
            onView(withId(R.id.confirmButton)).perform(click())
            onView(withId(R.id.washingProgramText)).check(matches(withText("Numer prania: 4")))
            capture("mobile2")

            onView(withId(R.id.vacuumButton)).perform(click())
            onView(withId(R.id.vacuumButton)).check(matches(withText("Wyłącz")))
            onView(withId(R.id.vacuumStateText)).check(matches(withText("Odkurzacz włączony")))
            capture("mobile3")

            scenario.recreate()
            onView(withId(R.id.washingProgramText)).check(matches(withText("Numer prania: 4")))
            onView(withId(R.id.vacuumStateText)).check(matches(withText("Odkurzacz włączony")))

            onView(withId(R.id.vacuumButton)).perform(click())
            onView(withId(R.id.vacuumButton)).check(matches(withText("Włącz")))
            onView(withId(R.id.vacuumStateText)).check(matches(withText("Odkurzacz wyłączony")))
            capture("mobile4")
        }
    }

    @Test
    fun washingBoundariesAndInvalidValues() {
        ActivityScenario.launch(MainActivity::class.java).use {
            for (valid in listOf("1", "12")) {
                onView(withId(R.id.washingProgramInput)).perform(replaceText(valid))
                closeSoftKeyboard()
                onView(withId(R.id.confirmButton)).perform(click())
                onView(withId(R.id.washingProgramText)).check(matches(withText("Numer prania: $valid")))
            }
            for (invalid in listOf("", "0", "13", "999999999999999999999")) {
                onView(withId(R.id.washingProgramInput)).perform(replaceText(invalid))
                closeSoftKeyboard()
                onView(withId(R.id.confirmButton)).perform(click())
                onView(withId(R.id.washingProgramText)).check(matches(withText("Numer prania: 12")))
            }
        }
    }

    private fun capture(name: String) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.waitForIdleSync()
        val bitmap = checkNotNull(instrumentation.uiAutomation.takeScreenshot())
        val directory = File(instrumentation.targetContext.getExternalFilesDir(null), "screenshots")
        check(directory.mkdirs() || directory.isDirectory)
        File(directory, "$name.png").outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        bitmap.recycle()
    }
}
