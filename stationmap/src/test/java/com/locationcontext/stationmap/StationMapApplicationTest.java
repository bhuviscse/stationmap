package com.locationcontext.stationmap;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StationMapApplicationTest {
    @Autowired
    StationMapApplication stationMapApplication;

    @Test
    void contextLoads() {
        assertNotNull(stationMapApplication);
    }

}
