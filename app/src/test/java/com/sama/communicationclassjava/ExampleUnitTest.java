package com.sama.communicationclassjava;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Date data = new Date();
        System.out.println();

        DateFormat format = DateFormat.getDateInstance ( DateFormat.LONG, Locale.KOREA );
        String formatted = format.format ( data );

        System.out.println(formatted);
        assertEquals(4, 2 + 2);
    }
}