package com.locationcontext.stationmap.domain.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class StationDetailsDTO {
    private String name;
    private Double latitude;
    private Double longitude;
    private String companyId;
    private String preferredCriteria;
}

