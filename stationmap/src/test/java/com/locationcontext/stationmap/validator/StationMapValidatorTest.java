package com.locationcontext.stationmap.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.locationcontext.stationmap.domain.dto.ResponseDTO;
import com.locationcontext.stationmap.entity.StationEntity;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StationMapValidatorTest {
    @Autowired
    StationMapValidator stationMapValidator;

    @Test
    public void testLongitudeEmpty() {
        final Throwable thrownException = catchThrowable(() -> stationMapValidator.validateInputRequest(null, 10.1, "123", 10d));
        assertThat(thrownException).isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Longitude/Latitude is null or empty");
    }

    @Test
    public void testLatitudeEmpty() {
        final Throwable thrownException = catchThrowable(() -> stationMapValidator.validateInputRequest(12.90, null, "123", 10d));
        assertThat(thrownException).isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Longitude/Latitude is null or empty");
    }

    @Test
    public void testCompanyIdEmpty() {
        final Throwable thrownException = catchThrowable(() -> stationMapValidator
            .validateInputRequest(10.1, 13.89, null, 10d));
        assertThat(thrownException).isInstanceOf(RuntimeException.class)
            .hasMessageContaining("companyId is null or empty");
    }

    @Test
    public void testPreferredDistanceEmpty() {
        final Throwable thrownException = catchThrowable(() -> stationMapValidator.validateInputRequest(10.1, 123.4, "123", null));
        assertThat(thrownException).isInstanceOf(RuntimeException.class)
            .hasMessageContaining("preferredDistance is null or empty");
    }

    @Test
    public void testPrepareResponse() {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setCriteria("HIGHVOLUMEVOLTAGE");
        stationEntity.setCompanyId("cogni123");
        stationEntity.setLatitude(32.9697);
        stationEntity.setLongitude(-96.80322);
        ResponseDTO responseDTO = stationMapValidator.prepareResponse(Collections.singletonList(stationEntity));
        assertEquals("cogni123", responseDTO.getStationDetail().get(0).getCompanyId());
    }

    @Test
    public void testPrepareResponseWhenNoStationFound() {
        final Throwable thrownException =
            catchThrowable(() -> stationMapValidator.prepareResponse(Collections.emptyList()));
        assertThat(thrownException).isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Sorry !! Couldnt find any station in the preferred distance");
    }

}
