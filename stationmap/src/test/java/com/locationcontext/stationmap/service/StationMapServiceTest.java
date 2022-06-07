package com.locationcontext.stationmap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.locationcontext.stationmap.entity.StationEntity;
import com.locationcontext.stationmap.repository.StationMapRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StationMapServiceTest {
    @Autowired
    private StationMapService stationMapService;
    @MockBean
    StationMapRepo stationMapRepo;

    @Test
    public void testCriteriaByCompanyId() {
        List<StationEntity> stationEntities = new ArrayList<>();
        StationEntity stationEntity = new StationEntity();
        stationEntity.setCriteria("HIGHVOLUMEVOLTAGE");
        stationEntity.setCompanyId("cogni123");
        stationEntity.setLatitude(29.46786);
        stationEntity.setLongitude(-98.53506);
        StationEntity stationEntity1 = new StationEntity();
        stationEntity1.setCriteria("FASTCHARGING");
        stationEntity1.setCompanyId("cogni123");
        stationEntity1.setLatitude(29.46786);
        stationEntity1.setLongitude(-98.8000);
        stationEntities.add(stationEntity);
        stationEntities.add(stationEntity1);
        when(stationMapRepo.findStationByLongituteAndLatitude(anyString()))
            .thenReturn(stationEntities);
        List<StationEntity> output =
            stationMapService.findStationByLongituteAndLatitude(-98.80322, 29.9697, "cogni123", 65d, Optional.empty());
        assertEquals(2, output.size());
    }

    @Test
    public void testCriteriaByCompanyIdAndCriteria() {
        List<StationEntity> stationEntities = new ArrayList<>();
        StationEntity stationEntity = new StationEntity();
        stationEntity.setCriteria("HIGHVOLUMEVOLTAGE");
        stationEntity.setCompanyId("cogni123");
        stationEntity.setLatitude(29.46786);
        stationEntity.setLongitude(-98.53506);
        StationEntity stationEntity1 = new StationEntity();
        stationEntity1.setCriteria("FASTCHARGING");
        stationEntity1.setCompanyId("cogni123");
        stationEntity1.setLatitude(29.46786);
        stationEntity1.setLongitude(-98.8000);
        stationEntities.add(stationEntity);
        stationEntities.add(stationEntity1);
        when(stationMapRepo.findStationByLongituteAndLatitude(anyString()))
            .thenReturn(stationEntities);
        List<StationEntity> output =
            stationMapService.findStationByLongituteAndLatitude(-98.80322, 29.9697, "cogni123", 65d, Optional.of("FASTCHARGING"));
        assertEquals(1, output.size());
    }

}
