package edu.miu.cs.cs545.propertymanagementsystem.service;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.OfferRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.OfferResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Address;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;

import java.util.List;

public interface OfferService {

    public List<OfferResponse> findAll();
    public OfferResponse findOfferBYId(long id);
    public void addNewOffer(Offer offer);
    public void deleteOfferById(long id);
    public OfferResponse updateOfferById(long id, OfferRequest offerRequest);
}
