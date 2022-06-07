package com.locationcontext.stationmap.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.locationcontext.stationmap.entity.StationEntity;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StationMapRepoTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    StationMapRepo stationMapRepo;

    @Test
    public void testCriteriaByCompanyId() {
        List<StationEntity> output = stationMapRepo.findStationByLongituteAndLatitude("tcs123");
        assertEquals(2, output.size());
    }

}
