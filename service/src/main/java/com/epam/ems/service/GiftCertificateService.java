package com.epam.ems.service;

import com.epam.ems.service.dto.GiftCertificateDto;
import com.epam.ems.service.exception.NoSuchEntityException;

import java.util.List;
import java.util.Map;

/**
 * Interface extending {@link AbstractService}, suited for use with
 * instances of {@link GiftCertificateDto}
 *
 * @author Baranouski Y. K.
 * @version 1.0.0
 */
public interface GiftCertificateService extends AbstractService<GiftCertificateDto> {

    /**
     * Retrieves list of gift certificates from data source
     * by map of filter configuration parameters.
     *
     * @param criteria A map containing filter as key and value to filter by as value.
     * @return List of {@link GiftCertificateDto} instances
     * matching given criteria.
     */
    List<GiftCertificateDto> getByCriteria(Map<String, String> criteria);

    /**
     * Updates given certificate in Data Source.
     * @param entity Entity to update, wrapped in {@link GiftCertificateDto}
     * @throws NoSuchEntityException if there is no such entity in Data Source.
     */
    void update(GiftCertificateDto entity) throws NoSuchEntityException;

}
