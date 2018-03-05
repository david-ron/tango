package com.tango;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfilePageTest {

    @Rule
    public ActivityTestRule<ProfilePage> mActivityTestRule = new ActivityTestRule<>(ProfilePage.class);

    @Test
    public void profilePageTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.changepicture), ViewMatchers.withText("Change picture"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withClassName(Matchers.is("android.widget.RelativeLayout")),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton.perform(ViewActions.click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3535997);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.profilePicture),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        imageView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction button = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.changepicture),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction textView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("First Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        ViewMatchers.isDisplayed()));
        textView.check(ViewAssertions.matches(ViewMatchers.withText("First Name")));

        ViewInteraction textView2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("Last Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                2),
                        ViewMatchers.isDisplayed()));
        textView2.check(ViewAssertions.matches(ViewMatchers.withText("Last Name")));

        ViewInteraction linearLayout = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                0),
                        0),
                        ViewMatchers.isDisplayed()));
        linearLayout.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction textView3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.QuestionsInProfilePage), ViewMatchers.withText("Questions"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        textView3.check(ViewAssertions.matches(ViewMatchers.withText("Questions")));

        ViewInteraction textView4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        textView4.check(ViewAssertions.matches(ViewMatchers.withText("0")));

        ViewInteraction linearLayout2 = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                0),
                        1),
                        ViewMatchers.isDisplayed()));
        linearLayout2.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction textView5 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.AnswersInProfilePage), ViewMatchers.withText("Answers"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        ViewMatchers.isDisplayed()));
        textView5.check(ViewAssertions.matches(ViewMatchers.withText("Answers")));

        ViewInteraction textView6 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        ViewMatchers.isDisplayed()));
        textView6.check(ViewAssertions.matches(ViewMatchers.withText("0")));

        ViewInteraction linearLayout3 = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                0),
                        2),
                        ViewMatchers.isDisplayed()));
        linearLayout3.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction textView7 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.AcceptedAnswersInProfilePage), ViewMatchers.withText("Accepted"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        ViewMatchers.isDisplayed()));
        textView7.check(ViewAssertions.matches(ViewMatchers.withText("Accepted")));

        ViewInteraction textView8 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                1),
                        ViewMatchers.isDisplayed()));
        textView8.check(ViewAssertions.matches(ViewMatchers.withText("0")));

        ViewInteraction frameLayout = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                ViewMatchers.withId(android.R.id.content),
                                0),
                        1),
                        ViewMatchers.isDisplayed()));
        frameLayout.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction imageView2 = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                0),
                        0),
                        ViewMatchers.isDisplayed()));
        imageView2.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        ViewInteraction textView9 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withText("user@email.com"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        textView9.check(ViewAssertions.matches(ViewMatchers.withText("user@email.com")));

        ViewInteraction imageView3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.profilePicture),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        imageView3.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
