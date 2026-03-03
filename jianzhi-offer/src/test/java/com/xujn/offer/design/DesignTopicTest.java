package com.xujn.offer.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesignTopicTest {

    @Test
    void shouldTrackMedianFromDataStream() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        assertEquals(1.5, medianFinder.findMedian());
        medianFinder.addNum(3);
        assertEquals(2.0, medianFinder.findMedian());
    }

    @Test
    void shouldTrackFirstUniqueCharacterInStream() {
        FirstUniqueCharacterStream stream = new FirstUniqueCharacterStream();
        stream.insert('g');
        stream.insert('o');
        stream.insert('o');
        stream.insert('g');
        stream.insert('l');
        stream.insert('e');

        assertEquals('l', stream.firstAppearingOnce());
    }
}
