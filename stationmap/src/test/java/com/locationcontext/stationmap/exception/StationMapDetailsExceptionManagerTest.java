package com.locationcontext.stationmap.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StationMapDetailsExceptionManagerTest {
    @Autowired
    StationMapDetailsExceptionManager stationMapDetailsExceptionManager;

    @Test
    public void testValidationException() {
        ResponseEntity<Object> output = stationMapDetailsExceptionManager.handleValidationException(new Exception(), null);
        assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
    }

}
