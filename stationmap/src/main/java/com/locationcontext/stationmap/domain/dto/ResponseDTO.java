package com.locationcontext.stationmap.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(toBuilder = true)
public class ResponseDTO {
    private ExceptionDTO exception;
    private List<StationDetailsDTO> stationDetail;
}
