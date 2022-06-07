package com.locationcontext.stationmap.service;

import com.locationcontext.stationmap.entity.StationEntity;
import com.locationcontext.stationmap.repository.StationMapRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StationMapService {
    private final StationMapRepo stationMapRepo;

    public StationMapService(StationMapRepo stationMapRepo) {
        this.stationMapRepo = stationMapRepo;
    }

    public List<StationEntity> findStationByLongituteAndLatitude(Double longitude, Double latitude, String companyId,
                                                                 Double preferredDistance, Optional<String> preferredCriteria) {
        log.info("Input Received - {longitude},{latitude},{companyId},{preferredDistance}"
            + longitude + ":" + latitude + ":" + companyId + ":" +
            preferredDistance);
        List<StationEntity> stationEntities =
            stationMapRepo.findStationByLongituteAndLatitude(companyId);
        log.info("DB records Received - {stationEntities}" + stationEntities);
        return findStationByPreferredDistance(stationEntities, longitude, latitude, preferredDistance, preferredCriteria);
    }

    private List<StationEntity> findStationByPreferredDistance(List<StationEntity> stationEntities, Double longitude, Double latitude,
                                                               Double preferredDistance, Optional<String> preferredCriteria) {

        return stationEntities.stream()
            .filter(stationEntity -> findDistance(stationEntity.getLatitude(), stationEntity.getLongitude(), latitude, longitude) <=
                preferredDistance)
            .filter(stationEntity -> preferredCriteria.map(preferredCriteriaObj -> stationEntity.getCriteria()
                .equalsIgnoreCase(preferredCriteriaObj))
                .orElse(Boolean.TRUE))
            .collect(Collectors.toList());
    }

    private double findDistance(double lat1, double lon1, double lat2, double lon2) {
        log.info("Calculating Distance - {DBlongitude},{DBlatitude},{Inputlongitude},{Inputlatitude}"
            + lat1 + ":" + lon1 + ":" + lat2 + ":" +
            lat2);
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        log.info("Distance for the given longitude & latitude" + dist);
        return dist;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
