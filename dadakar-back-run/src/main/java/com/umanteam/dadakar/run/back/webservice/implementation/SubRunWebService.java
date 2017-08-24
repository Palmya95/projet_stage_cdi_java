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

import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.service.interfaces.ISubRunService;
import com.umanteam.dadakar.run.back.webservice.interfaces.ISubRunWebService;

@RestController
@RequestMapping("${appli.path}/subruns")
@CrossOrigin(origins="*")
public class SubRunWebService implements ISubRunWebService {
	
	@Autowired
	private ISubRunService subRunService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public SubRunDTO add(@RequestBody SubRunDTO subRunDTO) { // OK
		return subRunService.addOrUpdate(subRunDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public SubRunDTO update(@RequestBody SubRunDTO subRunDTO) { // OK
		return subRunService.addOrUpdate(subRunDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		subRunService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<SubRunDTO>> findAll() { // OK
		List<SubRunDTO> subRunDTOs = subRunService.findAll();
		if(subRunDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<SubRunDTO>>(subRunDTOs, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public SubRunDTO findById(@PathVariable("id") String id) { // OK
		return subRunService.findById(id);
	}

}
