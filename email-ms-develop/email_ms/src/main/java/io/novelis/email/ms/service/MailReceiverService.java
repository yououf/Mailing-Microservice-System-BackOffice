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
import io.novelis.email.ms.model.MailReceiver;
import io.novelis.email.ms.model.MailSender;
import io.novelis.email.ms.repository.MailReceiverRepository;
import io.novelis.email.ms.repository.MailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MailReceiverService {

	@Autowired
	private MailReceiverRepository mailReceiverRepository;


	public List<MailReceiver> retrieveAllMailReceivers() {
		return mailReceiverRepository.findAll();
	}

	public MailReceiver getMailReceiver(Long id) {
		return mailReceiverRepository.getOne(id);
	}

	public MailReceiver createMailReceiver(MailReceiver mailreceiver) {
		return mailReceiverRepository.save(mailreceiver);
	}
	
	@Transactional
	public void deleteMailReceiver(Long id) throws Exception {

		MailReceiver mailreceiver = mailReceiverRepository.getOne(id);
		if (mailreceiver != null) {
			mailReceiverRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public MailReceiver updateMailReceiver(MailReceiver mailreceiver) {
		
		MailReceiver editedMailReceiver = mailReceiverRepository.getOne(mailreceiver.getId());
		editedMailReceiver.setEmail(mailreceiver.getEmail());
		return mailReceiverRepository.save(editedMailReceiver);
	}
}
