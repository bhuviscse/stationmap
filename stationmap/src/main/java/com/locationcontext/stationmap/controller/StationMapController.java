package com.locationcontext.stationmap.controller;

import com.locationcontext.stationmap.domain.dto.ResponseDTO;
import com.locationcontext.stationmap.entity.StationEntity;
import com.locationcontext.stationmap.service.StationMapService;
import com.locationcontext.stationmap.validator.StationMapValidator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StationMapController {

    private final StationMapValidator stationMapValidator;
    private final StationMapService stationMapService;

    @Autowired
    public StationMapController(StationMapService stationMapService,
                                StationMapValidator stationMapValidator) {
        this.stationMapService = stationMapService;
        this.stationMapValidator = stationMapValidator;
    }

    @GetMapping("/findStationMap")
    public ResponseDTO findStationMap(@RequestParam("longitude") Double longitude,
                                      @RequestParam("latitude") Double latitude,
                                      @RequestParam("companyId") String companyId,
                                      @RequestParam("preferredDistance") Double preferredDistance,
                                      @RequestParam Optional<String> preferredCriteria) {
        log.info("Stating the service");
        stationMapValidator.validateInputRequest(longitude, latitude, companyId, preferredDistance);
        List<StationEntity> stationDTOs =
            stationMapService.findStationByLongituteAndLatitude(longitude, latitude, companyId, preferredDistance, preferredCriteria);
        log.info("The station found for the given input :" + stationDTOs);
        return stationMapValidator.prepareResponse(stationDTOs);
    }
}
