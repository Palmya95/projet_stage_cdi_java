package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.AddressDTO;
import com.umanteam.dadakar.back.full.entities.Address;
import com.umanteam.dadakar.back.full.repository.AddressRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IAddressService;

@Service("addressService")
public class AddressService implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	/* copy from AddressDTO to Address */
	private Address addressDTOToAddress(AddressDTO addressDTO) {
		Address address = new Address();
		BeanUtils.copyProperties(addressDTO, address);
		return address;
	}
	
	/* copy from Address to AddressDTO */
	private AddressDTO addressToAddressDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);
		return addressDTO;
	}

	@Override
	public AddressDTO addOrUpdate(AddressDTO addressDTO) {
		return addressToAddressDTO(addressRepository.save(addressDTOToAddress(addressDTO)));
	}

	@Override
	public void delete(String id) {
		addressRepository.delete(id);
	}

	@Override
	public List<AddressDTO> findAll() {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		List<Address> addresses = addressRepository.findAll();
		if(addresses != null) for(Address address: addresses) addressDTOs.add(addressToAddressDTO(address));
		return addressDTOs;
	}

	@Override
	public AddressDTO findById(String id) {
		Address address = addressRepository.findOne(id);
		if(address != null) return addressToAddressDTO(address);
		return new AddressDTO();
	}
	
	@Override
	public List<AddressDTO> findByPostCode(String postCode) {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		List<Address> addresses = addressRepository.findByPostCode(postCode);
		if(addresses != null) for(Address address: addresses) addressDTOs.add(addressToAddressDTO(address));
		return addressDTOs;
	}

	@Override
	public List<AddressDTO> findByTown(String town) {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		List<Address> addresses = addressRepository.findByTown(town);
		if(addresses != null) for(Address address: addresses) addressDTOs.add(addressToAddressDTO(address));
		return addressDTOs;
	}

	@Override
	public List<AddressDTO> findByPostCodeAndTown(String postCode, String Town) {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		List<Address> addresses = addressRepository.findByPostCodeAndTown(postCode, Town);
		if(addresses != null) for(Address address: addresses) addressDTOs.add(addressToAddressDTO(address));
		return addressDTOs;
	}

	@Override
	public List<AddressDTO> findByPostCodeAndTownAndDistrict(String postCode, String town, String district) {
		List<AddressDTO> addressDTOs = new ArrayList<>();
		List<Address> addresses = addressRepository.findByPostCodeAndTownAndDistrict(postCode, town, district);
		if(addresses != null) for(Address address: addresses) addressDTOs.add(addressToAddressDTO(address));
		return addressDTOs;
	}

}
