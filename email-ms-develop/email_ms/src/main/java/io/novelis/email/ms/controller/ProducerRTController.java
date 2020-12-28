/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.controller;
/**
 * User Controller
 *
 * @author Alias King - Younes OUFRID
 */
import io.novelis.email.ms.model.MailSender;
import io.novelis.email.ms.model.ProducerRT;
import io.novelis.email.ms.service.MailSenderService;
import io.novelis.email.ms.service.ProducerRTService;
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
public class ProducerRTController {

	private static Logger _log = LoggerFactory.getLogger(ProducerRTController.class);

	@Autowired
	private ProducerRTService producerRTService;

	@GetMapping("api/producerrts")
	public ResponseEntity<List<ProducerRT>> retrieveAllProducerRTs() {
		_log.info("retrieve all producer RTs controller...!");
		List<ProducerRT> producerRTS = producerRTService.retrieveAllProducersRT();
		return new ResponseEntity<List<ProducerRT>>(producerRTS, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/producerrts/{id}")
	public ResponseEntity<ProducerRT> retrieveProducerRTById(@PathVariable long id) {

		_log.info("retrieve ProducerRT controller...!");
		ProducerRT producerRT = producerRTService.getProducerRT(id);
		return new ResponseEntity<ProducerRT>(producerRT, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/producerrts")
	public ResponseEntity<ProducerRT> createProducerRT(@RequestBody ProducerRT producerRT) {

		_log.info("create ProducerRT controller...!");
		ProducerRT savedProducerRT = producerRTService.createProducerRT(producerRT);
		return new ResponseEntity<ProducerRT>(savedProducerRT, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/producerrts/{id}")
	public HttpStatus deleteProducerRT(@PathVariable long id) throws Exception {
		_log.info("delete ProducerRT controller...!");
		producerRTService.deleteProducerRT(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/producerrts/{id}")
	public ResponseEntity<ProducerRT> updateProducerRT(@PathVariable long id,@RequestBody ProducerRT producerRT) {
		_log.info("update ProducerRT controller...!");
		ProducerRT updatedProducerRT= producerRTService.updateProducerRT(producerRT,id);
		return new ResponseEntity<ProducerRT>(updatedProducerRT, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
