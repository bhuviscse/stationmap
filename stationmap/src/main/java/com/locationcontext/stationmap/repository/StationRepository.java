package com.locationcontext.stationmap.repository;

import com.locationcontext.stationmap.entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Integer> {

}