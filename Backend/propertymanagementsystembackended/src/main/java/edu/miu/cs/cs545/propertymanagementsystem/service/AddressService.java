package edu.miu.cs.cs545.propertymanagementsystem.service;


import edu.miu.cs.cs545.propertymanagementsystem.dto.request.AddressRequest;
import edu.miu.cs.cs545.propertymanagementsystem.dto.response.AddressResponse;
import edu.miu.cs.cs545.propertymanagementsystem.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {
public List<AddressResponse> findAll();
public AddressResponse findAddressBYId(long id);
public void addNewAddress(AddressRequest addressRequest);
public void deleteAddressById(long id);
public AddressResponse updateAddressById(long id, AddressRequest addressRequest);

}
