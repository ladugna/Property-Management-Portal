package edu.miu.cs.cs545.propertymanagementsystem.controller;


import edu.miu.cs.cs545.propertymanagementsystem.dto.request.OfferRequest;
import edu.miu.cs.cs545.propertymanagementsystem.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {
//    @Autowired
//    private OfferService offerService;
//    @GetMapping
//  public ResponseEntity<?> getAll(){
//       return new ResponseEntity<>(offerService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addNewOffer(@RequestBody OfferRequest offerRequest){
//      offerService.addNewOffer(offerRequest);
//    }
//
//    @DeleteMapping("/{offer_id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteIt(@PathVariable("offer_id")long offer_id){
//        offerService.deleteOfferById(offer_id);
//    }
//    @PutMapping("/{offer_id}")
//    public ResponseEntity<?> updateIt( @PathVariable("offer_id") long offer_id, @RequestBody OfferRequest offerRequest){
//        return new ResponseEntity<>(offerService.updateOfferById(offer_id, offerRequest), HttpStatus.OK);
//    }
//    @GetMapping("/{offer_id}")
//    public ResponseEntity<?> getById(@PathVariable("offer_id")long offer_id){
//        return new ResponseEntity<>(offerService.findOfferBYId(offer_id), HttpStatus.OK);
//    }

}
