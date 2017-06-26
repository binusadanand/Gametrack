package com.igt.binu.gametrack;

import com.igt.binu.gametrack.Utils.DateConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class UtilsUnitTest {
    @Test
    public void pretty_dateConverter_isCorrect() throws Exception {
        assertEquals(DateConverter.PrettyFromStr("04/05/2016T16:45"), "1 year ago");
    }

}
