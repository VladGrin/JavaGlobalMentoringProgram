package com.pgdatabase;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.Assert.*;

public class DataGeneratorTest {

    @Test
    public void generateData() {
        LocalDate date = LocalDate.of(2001,2,1);
        System.out.println(date.getDayOfYear());
    }

}