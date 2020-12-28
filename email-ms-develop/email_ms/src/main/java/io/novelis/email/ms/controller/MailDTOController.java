/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.controller;
/**
 * Mail DTO COntroller
 *
 * @author Alias King - Younes OUFRID
 */

import io.novelis.email.ms.model.*;
import io.novelis.email.ms.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MailDTOController {

	private static Logger _log = LoggerFactory.getLogger(MailDTOController.class);

	@Autowired
	private MailDTOService maildtoService;
	@Autowired
	private StateService stateService;

	@GetMapping("api/maildtos")
	public ResponseEntity<List<MailDTO>> retrieveAllMailDTO() {
		_log.info("retrieve all Mail DTO controller...!");
		List<MailDTO> maildtos = maildtoService.retrieveAllMailDTO();
		return new ResponseEntity<List<MailDTO>>(maildtos, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/maildtos/{id}")
	public ResponseEntity<MailDTO> retrieveMailDTOById(@PathVariable long id) {
		_log.info("retrieve Mail DTO by ID controller...!");
		MailDTO maildto = maildtoService.getMailDTO(id);
		return new ResponseEntity<MailDTO>(maildto, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/maildtos")
	public ResponseEntity<MailDTO> createMailDTO(@RequestBody MailDTO maildto) {
		_log.info("create Mail DTO Controller...!");
		MailDTO savedMailDTO = maildtoService.createMailDTO(maildto);
		return new ResponseEntity<MailDTO>(savedMailDTO, new HttpHeaders(), HttpStatus.CREATED);

	}

	@DeleteMapping("api/maildtos/{id}")
	public HttpStatus deleteMailDTO(@PathVariable long id) throws Exception {
		_log.info("delete Mail DTO controller...!");
		maildtoService.deleteMailDTO(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/maildtos")
	public ResponseEntity<MailDTO> updateMailDTO(@RequestBody MailDTO maildto) {
		_log.info("update Mail DTO controller...!");
		MailDTO updatedMailDTO = maildtoService.updateMailDTO(maildto);
		return new ResponseEntity<MailDTO>(updatedMailDTO, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
