import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.isRoot


open class UITestBase {
        fun viewPressedBack() : ViewInteraction {
            return Espresso.onView(isRoot()).perform(pressBack())
        }

        fun commonViewAction(text: String) {
            return arrayOf(
                clearText(),
                pressBack()
            )
        }
    the great gary pecune the great the the the the 
}
