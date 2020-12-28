/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmaill.com
 * MNS team coders
 * */

package io.novelis.email.ms.service;
/**
 * Mail DTO Service
 *
 * @author Alias King - Younes OUFRID
 */
import io.novelis.email.ms.controller.MailDTOController;
import io.novelis.email.ms.model.*;
import io.novelis.email.ms.repository.MailDTORepository;
import io.novelis.email.ms.repository.ProducerRTRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailDTOService {

	private static Logger _log = LoggerFactory.getLogger(MailDTOController.class);

	@Autowired
	private MailDTOService maildtoService;
	@Autowired
	private MailReceiverService mailReceiverService;
	@Autowired
	private MailSenderService mailSenderService;
	@Autowired
	private ProducerRTService producerRTService;
	@Autowired
	private StateService stateService;
	@Autowired
	private MailDTORepository mailDTORepository;
	@Autowired
	private MailDTORepository producerRTRepository;
	@Autowired
	private MailingService mailingService;
	public ArrayList<MailDTO> retrieveAllMailDTO() {
		return (ArrayList<MailDTO>) mailDTORepository.findAll();
	}

	public MailDTO getMailDTO(long id) {
		return mailDTORepository.getOne(id);
	}

	public MailDTO createMailDTO(MailDTO maildto) {
		//when we create a mail dto we create also the state of this mail dto having notsent state for the first time
		State state = new State();
		state.setMaildto(maildto);
		state.setState(StatesOfMail.NotSent);
		stateService.createState(state);
		// sending mail dto instantly
		mailingService.sendOneEmail(maildto);
		return mailDTORepository.save(maildto);
	}
	
	@Transactional
	public void deleteMailDTO(long id) throws Exception {
		MailDTO maildto = mailDTORepository.getOne(id);
		if (maildto != null) {
			mailDTORepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public MailDTO updateMailDTO(MailDTO maildto) {
		
		MailDTO editedMailDTO = mailDTORepository.getOne(maildto.getId());
		editedMailDTO.setDescription(maildto.getDescription());
		editedMailDTO.setMailSubject(maildto.getMailSubject());
		editedMailDTO.setMailContent(maildto.getMailContent());
		return mailDTORepository.save(editedMailDTO);
	}
}
