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
import io.novelis.email.ms.model.ProducerRT;
import io.novelis.email.ms.repository.ProducerRTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProducerRTService {

	@Autowired
	private ProducerRTRepository producerRTRepository;


	public List<ProducerRT> retrieveAllProducersRT() {
		return producerRTRepository.findAll();
	}

	public ProducerRT getProducerRT(Long id) {
		return producerRTRepository.getOne(id);
	}

	public ProducerRT createProducerRT(ProducerRT producerrt) {
		return producerRTRepository.save(producerrt);
	}
	
	@Transactional
	public void deleteProducerRT(Long id) throws Exception {

		ProducerRT producerrt = producerRTRepository.getOne(id);
		if (producerrt != null) {
			producerRTRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public ProducerRT updateProducerRT(ProducerRT producerrt,Long id) {
		ProducerRT editedProducerRT = producerRTRepository.getOne(id);
		editedProducerRT.setCode(producerrt.getCode());
		editedProducerRT.setName(producerrt.getName());
		return producerRTRepository.save(editedProducerRT);
	}
}
