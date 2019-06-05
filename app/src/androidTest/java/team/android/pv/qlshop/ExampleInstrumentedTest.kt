package team.android.pv.qlshop

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented show_dialog_nhacc, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under show_dialog_nhacc.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("team.android.pv.appdemo", appContext.packageName)
    }
}
