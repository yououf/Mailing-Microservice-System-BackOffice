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

import io.novelis.email.ms.model.MailDTO;
import io.novelis.email.ms.model.State;
import io.novelis.email.ms.service.MailDTOService;
import io.novelis.email.ms.service.StateService;
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
public class StateController {

	private static Logger _log = LoggerFactory.getLogger(StateController.class);


	@Autowired
	private StateService stateService;

	@GetMapping("api/states")
	public ResponseEntity<List<State>> retrieveAllStates() {
		_log.info("retrieve all States controller...!");
		List<State> states = stateService.retrieveAllStates();
		return new ResponseEntity<List<State>>(states, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/states/{id}")
	public ResponseEntity<State> retrieveStateById(@PathVariable long id) {
		_log.info("retrieve State by ID controller...!");
		State state = stateService.getState(id);
		return new ResponseEntity<State>(state, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/states")
	public ResponseEntity<State> createState(@RequestBody State state) {
		_log.info("create State Controller...!");
		State savedState = stateService.createState(state);
		return new ResponseEntity<State>(savedState, new HttpHeaders(), HttpStatus.CREATED);

	}

	@DeleteMapping("api/states/{id}")
	public HttpStatus deleteState(@PathVariable long id) throws Exception {

		_log.info("delete State controller...!");
		stateService.deleteState(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/states")
	public ResponseEntity<State> updateState(@RequestBody State state) {

		_log.info("update State controller...!");
		State updatedState = stateService.updateState(state);
		return new ResponseEntity<State>(updatedState, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
