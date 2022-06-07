package com.locationcontext.stationmap.validator;

import com.locationcontext.stationmap.domain.dto.ResponseDTO;
import com.locationcontext.stationmap.domain.dto.StationDetailsDTO;
import com.locationcontext.stationmap.entity.StationEntity;
import com.locationcontext.stationmap.exception.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class StationMapValidator {

    public final Predicate<Double> isObjectNullOrEmpty = Objects::isNull;

    public final Predicate<String> isCompanyIdNullOrEmpty = companyId -> null == companyId
        || companyId.isBlank();

    public void validateInputRequest(Double longitude, Double latitude, String companyId, Double preferredDistance) {
        if (isObjectNullOrEmpty.test(longitude) || isObjectNullOrEmpty.test(latitude)) {
            log.error("Longitude/Latitude is null or empty");
            throw new ValidationException("Longitude/Latitude is null or empty");
        } else if (isCompanyIdNullOrEmpty.test(companyId)) {
            log.error("companyId is null or empty");
            throw new ValidationException("companyId is null or empty");
        } else if (isObjectNullOrEmpty.test(preferredDistance)) {
            log.error("preferredDistance is null or empty");
            throw new ValidationException("preferredDistance is null or empty");
        }
    }

    public ResponseDTO prepareResponse(List<StationEntity> stationDTOS) {
        return ResponseDTO.builder().stationDetail(prepareStations(stationDTOS)).build();
    }

    private List<StationDetailsDTO> prepareStations(List<StationEntity> stationDTOS) {

        if (CollectionUtils.isEmpty(stationDTOS)) {
            log.error("Sorry !! Couldnt find any station in the preferred distance");
            throw new ValidationException("Sorry !! Couldnt find any station in the preferred distance");
        } else {
            return stationDTOS.stream()
                .map(station -> StationDetailsDTO.builder().name(station.getName())
                    .longitude(station.getLongitude()).latitude(station.getLatitude())
                    .companyId(station.getCompanyId()).preferredCriteria(station.getCriteria()).build())
                .collect(Collectors.toList());
        }
    }
}

