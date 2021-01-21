package thirdwayv.calculator

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import thirdwayv.calculator.ui.MainActivity


@RunWith(AndroidJUnit4::class)
class SimpleTest {
    @Rule @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(UiObjectNotFoundException::class, InterruptedException::class)
    fun testUiAutomatorAPI() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        var x:Int =3
        while (x<7) {
            //enter input
            takeInput(x, device)
            //try add, sub, multiply, divide
            doOperation(x-2, device)
            // then click equal
            doOperation(5, device)
            x++
        }
    }

    fun takeInput(x:Int, device:UiDevice){
        val inputSelector = UiSelector().className("android.widget.EditText").resourceId("thirdwayv.calculator:id/inputET")
        val inputWidget = device.findObject(inputSelector)
        inputWidget.text = "${x} "
        Thread.sleep(1000)
    }
    fun doOperation(instanceIndex:Int, device:UiDevice){
        val addSelector = UiSelector().className("android.widget.Button").instance(instanceIndex)
        val addWidget = device.findObject(addSelector)
        addWidget.click()
        Thread.sleep(1000)
    }
}

