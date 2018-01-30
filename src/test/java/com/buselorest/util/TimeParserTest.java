package com.buselorest.util;

import org.junit.Assert;
import org.junit.Test;

public class TimeParserTest {

    @Test
    public void testParseLongTimeToString(){
        Assert.assertEquals(TimeParser.parseLongToTimeString(7390000),"02:03:10");
    }

    @Test
    public void testParseStringTimeToLing(){
        Assert.assertEquals(TimeParser.parseStringTimeToLong("02:03:10"),7390000);
    }
}
