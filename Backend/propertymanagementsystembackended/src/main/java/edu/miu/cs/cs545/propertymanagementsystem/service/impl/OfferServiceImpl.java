package edu.miu.cs.cs545.propertymanagementsystem.service.impl;

import edu.miu.cs.cs545.propertymanagementsystem.dto.request.OfferRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.OfferResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Offer;
import edu.miu.cs.cs545.propertymanagementsystem.repository.OfferRepository;
import edu.miu.cs.cs545.propertymanagementsystem.service.OfferService;
import edu.miu.cs.cs545.propertymanagementsystem.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
   private ListMapper listMapper;
    @Override
    public List<OfferResponse> findAll() {
       // return listMapper.mapList(offerRepository.findAll(), OfferResponse.class);
       return offerRepository.findAll().stream().map(e->modelMapper.map(e, OfferResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OfferResponse findOfferBYId(long id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), OfferResponse.class);
    }

    @Override
    public void addNewOffer(OfferRequest offerRequest) {
  offerRepository.save(modelMapper.map(offerRequest, Offer.class));
    }

    @Override
    public void deleteOfferById(long id) {
     offerRepository.deleteById(id);
    }

    @Override
    public OfferResponse updateOfferById(long id, OfferRequest offerRequest) {
         Offer offer= offerRepository.findById(id).orElse(null);
         if(offer!=null){
             offer= modelMapper.map(offerRequest, Offer.class);
         }
         offerRepository.save(offer);

        return modelMapper.map(offer, OfferResponse.class);
    }
}
