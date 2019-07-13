package com.nikolasdion.moodjournal.dummy

import com.nikolasdion.moodjournal.Entry
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<Entry> = ArrayList()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            ITEMS.add(createDummyItem(i))
        }
    }

    private fun createDummyItem(position: Int): Entry {
        return Entry(0,
            System.currentTimeMillis() + position*10000,
            "Trigger $position",
            "Thoughts",
            "Feelings",
            "Physical",
            "Behaviour",
            "Notes")
    }
}
