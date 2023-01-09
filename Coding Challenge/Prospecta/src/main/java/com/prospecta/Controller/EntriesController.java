package com.prospecta.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prospecta.Exceptions.EntryException;
import com.prospecta.Model.Entry;
import com.prospecta.Model.EntryEntity;
import com.prospecta.Service.EntryService;

@RestController
public class EntriesController {

	@Autowired
	private EntryService entryService;

	// List of the consume API
	@GetMapping("/")
	public ResponseEntity<?> ConsumeExternalApi() {
		return new ResponseEntity<>(entryService.consumeExternalApi(), HttpStatus.OK);
	}

	@GetMapping("/entries/{category}")
	public ResponseEntity<?> getEntriesByCategory(@PathVariable("category") String category) throws EntryException {
		return new ResponseEntity<>(entryService.searchByCategory(category), HttpStatus.OK);
	}

//	@PostMapping("/entries")
//	public ResponseEntity<?> registerNewEntries(@RequestBody Entry entry) throws EntryException {
//		
//		return new ResponseEntity<>(entryService.registerNewEntry(entry),HttpStatus.CREATED);
//	}
	
	@PostMapping("/entries")
	public ResponseEntity<?> registerNewEntriesDataBase(@Valid @RequestBody EntryEntity entry) throws EntryException {

		return new ResponseEntity<>(entryService.registerNewEntryDatabase(entry), HttpStatus.CREATED);
	}

}
