/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.service;
/**
 * User Service
 *
 * @author Alias King - Younes OUFRID
 */
import io.novelis.email.ms.model.MailSender;
import io.novelis.email.ms.repository.MailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MailSenderService {

	@Autowired
	private MailSenderRepository mailSenderRepository;


	public List<MailSender> retrieveAllMailSenders() {
		return mailSenderRepository.findAll();
	}

	public MailSender getMailSender(Long id) {
		return mailSenderRepository.getOne(id);
	}

	public MailSender createMailSender(MailSender mailsender) {
		return mailSenderRepository.save(mailsender);
	}
	
	@Transactional
	public void deleteMailSender(Long id) throws Exception {

		MailSender mailsender = mailSenderRepository.getOne(id);
		if (mailsender != null) {
			mailSenderRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public MailSender updateMailSender(MailSender mailsender) {
		
		MailSender editedMailSender = mailSenderRepository.getOne(mailsender.getId());
		editedMailSender.setEmail(mailsender.getEmail());
		editedMailSender.setProducerrt(mailsender.getProducerrt());
		return mailSenderRepository.save(editedMailSender);
	}
}
