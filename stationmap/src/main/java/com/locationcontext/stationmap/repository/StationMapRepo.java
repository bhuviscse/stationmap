package com.locationcontext.stationmap.repository;

import com.locationcontext.stationmap.entity.CompanyEntity;
import com.locationcontext.stationmap.entity.StationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class StationMapRepo {
    @PersistenceContext
    EntityManager em;
    private static final String PARENT_COMPANY_ID = "parentCompanyId";
    private static final String COMPANY_ID = "companyId";

    public List<StationEntity> findStationByLongituteAndLatitude(String companyId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StationEntity> query = cb.createQuery(StationEntity.class);
        Root<CompanyEntity> aRoot = query.from(CompanyEntity.class);
        Root<StationEntity> bRoot = query.from(StationEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        query.select(bRoot);
        // Predicate to check id & parent id is station and company tables
        Predicate companyIdPredicate = cb.equal(aRoot.get(PARENT_COMPANY_ID), bRoot.get(COMPANY_ID));
        Predicate parentCompanyIdPredicate = cb.equal(aRoot.get(PARENT_COMPANY_ID), companyId);
        predicates.add(companyIdPredicate);
        predicates.add(parentCompanyIdPredicate);
        query.where(predicates.toArray(new Predicate[] {}));
        return em.createQuery(query).getResultList();
    }
}
