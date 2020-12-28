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
public class MailSenderController {

	private static Logger _log = LoggerFactory.getLogger(MailSenderController.class);

	@Autowired
	private MailSenderService mailSenderService;

	@GetMapping("api/mailsenders")
	public ResponseEntity<List<MailSender>> retrieveAllMailSenders() {
		_log.info("retrieve all mail senders controller...!");
		List<MailSender> mailsenders = mailSenderService.retrieveAllMailSenders();
		return new ResponseEntity<List<MailSender>>(mailsenders, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/mailsenders/{id}")
	public ResponseEntity<MailSender> retrieveMailSenderById(@PathVariable long id) {

		_log.info("retrieve mail sender controller...!");
		MailSender mailsender = mailSenderService.getMailSender(id);
		return new ResponseEntity<MailSender>(mailsender, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/mailsenders")
	public ResponseEntity<MailSender> createMailSender(@RequestBody MailSender mailsender) {

		_log.info("create mail sender controller...!");
		MailSender savedMailsender = mailSenderService.createMailSender(mailsender);
		return new ResponseEntity<MailSender>(savedMailsender, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/mailsenders/{id}")
	public HttpStatus deleteMailSender(@PathVariable long id) throws Exception {

		_log.info("delete Mail sender controller...!");
		mailSenderService.deleteMailSender(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/mailsenders")
	public ResponseEntity<MailSender> updateMailSender(@RequestBody MailSender mailsender) {

		_log.info("update mail sender controller...!");
		MailSender updatedMailSender= mailSenderService.updateMailSender(mailsender);
		return new ResponseEntity<MailSender>(updatedMailSender, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
