package com.umanteam.dadakar.run.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.run.back.dto.AddressDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IAddressService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IAddressWebService;

@RestController
@RequestMapping("${appli.path}/addresses")
@CrossOrigin(origins="*")
public class AddressWebService implements IAddressWebService {
	
	@Autowired
	private IAddressService addressService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public AddressDTO add(@RequestBody AddressDTO addressDTO) {
		return addressService.addOrUpdate(addressDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public AddressDTO update(@RequestBody AddressDTO addressDTO) {
		return addressService.addOrUpdate(addressDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		addressService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AddressDTO>> findAll() {
		List<AddressDTO> addressesDTOs = addressService.findAll();
		if(addressesDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AddressDTO>>(addressesDTOs, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<AddressDTO> findById(@PathVariable("id") String id) {
		AddressDTO addressDTO = addressService.findById(id);
		if(addressDTO.getAddressId().equals("")) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/town:{town}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<AddressDTO>> findByTown(@PathVariable("town") String town) {
		List<AddressDTO> addressesDTOs = addressService.findByTown(town);
		if(addressesDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AddressDTO>>(addressesDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/town:{town}/district:{district}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<AddressDTO> findByTownAndDistrict(@PathVariable("town") String town, @PathVariable("district") String district) {
		AddressDTO addressDTO = addressService.findByTownAndDistrict(town, district);
		if(addressDTO.getAddressId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
	}

}
