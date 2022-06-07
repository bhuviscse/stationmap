package com.locationcontext.stationmap.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationcontext.stationmap.domain.dto.ResponseDTO;
import com.locationcontext.stationmap.domain.dto.StationDetailsDTO;
import com.locationcontext.stationmap.entity.StationEntity;
import com.locationcontext.stationmap.service.StationMapService;
import com.locationcontext.stationmap.validator.StationMapValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class StationMapControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationMapService stationMapService;
    @MockBean
    private StationMapValidator stationMapValidator;

    @Test
    void findStationMapWithNoAuth() throws Exception {

        this.mockMvc.perform(get("/findStationMap").queryParam("longitude", "-96.80322")
            .queryParam("latitude", "32.9697").queryParam("companyId", "cogni123", "preferredDistance", "20"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findStationMapWithSuccessScenario() throws Exception {
        when(stationMapService.findStationByLongituteAndLatitude(anyDouble(), anyDouble(), anyString(), anyDouble(), any()))
            .thenReturn(getStationEntities());
        when(stationMapValidator.prepareResponse(anyList())).thenReturn(getResponse());
        this.mockMvc.perform(get("/findStationMap").queryParam("longitude", "-96.80322")
            .queryParam("latitude", "32.9697")
            .queryParam("preferredDistance", "20")
            .queryParam("companyId", "cogni123"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                "{\"stationDetail\":[{\"name\":\"cognoCharge\",\"latitude\":32.9697,\"longitude\":-96.80322,\"companyId\":\"cogni123\",\"preferredCriteria\":\"HIGHVOLUMEVOLTAGE\"}]}"));
    }

    private ResponseDTO getResponse() {
        return ResponseDTO.builder()
            .stationDetail(
                Collections.singletonList(StationDetailsDTO.builder()
                    .name("cognoCharge")
                    .preferredCriteria("HIGHVOLUMEVOLTAGE").companyId("cogni123")
                    .latitude(32.9697).longitude(-96.80322).build())).build();
    }

    private List<StationEntity> getStationEntities() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<StationEntity> stationEntities = new ArrayList<>();
        StationEntity stationEntity = new StationEntity();
        stationEntity.setCriteria("HIGHVOLUMEVOLTAGE");
        stationEntity.setCompanyId("cogni123");
        stationEntity.setLatitude(32.9697);
        stationEntity.setLongitude(-96.80322);
        stationEntities.add(stationEntity);
        objectMapper.writeValueAsString(stationEntities);
        return stationEntities;
    }
}
