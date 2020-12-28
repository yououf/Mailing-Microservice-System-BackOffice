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
import io.novelis.email.ms.model.MailReceiver;
import io.novelis.email.ms.model.MailSender;
import io.novelis.email.ms.service.MailReceiverService;
import io.novelis.email.ms.service.MailSenderService;
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
public class MailReceiverController {

	private static Logger _log = LoggerFactory.getLogger(MailReceiverController.class);

	@Autowired
	private MailReceiverService mailReceiverService;

	@GetMapping("api/mailreceivers")
	public ResponseEntity<List<MailReceiver>> retrieveAllMailReceivers() {
		_log.info("retrieve all mail receivers controller...!");
		List<MailReceiver> mailReceivers = mailReceiverService.retrieveAllMailReceivers();
		return new ResponseEntity<List<MailReceiver>>(mailReceivers, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/mailreceivers/{id}")
	public ResponseEntity<MailReceiver> retrieveMailReceiverById(@PathVariable long id) {

		_log.info("retrieve mail receivers controller...!");
		MailReceiver mailReceiver = mailReceiverService.getMailReceiver(id);
		return new ResponseEntity<MailReceiver>(mailReceiver, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/mailreceivers")
	public ResponseEntity<MailReceiver> createMailReceiver(@RequestBody MailReceiver mailReceiver) {

		_log.info("create mail receiver controller...!");
		MailReceiver savedMailreceiver = mailReceiverService.createMailReceiver(mailReceiver);
		return new ResponseEntity<MailReceiver>(savedMailreceiver, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/mailreceivers/{id}")
	public HttpStatus deleteMailReceiver(@PathVariable long id) throws Exception {

		_log.info("delete Mail receiver controller...!");
		mailReceiverService.deleteMailReceiver(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/mailreceivers")
	public ResponseEntity<MailReceiver> updateMailReceiver(@RequestBody MailReceiver mailReceiver) {

		_log.info("update Mail receiver controller...!");
		MailReceiver updatedMailReceiver= mailReceiverService.updateMailReceiver(mailReceiver);
		return new ResponseEntity<MailReceiver>(updatedMailReceiver, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
