/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.service;
/**
 * Mail DTO Service
 *
 * @author Alias King - Younes OUFRID
 */
import io.novelis.email.ms.controller.MailDTOController;
import io.novelis.email.ms.model.State;
import io.novelis.email.ms.repository.MailDTORepository;
import io.novelis.email.ms.repository.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class StateService {

	private static Logger _log = LoggerFactory.getLogger(MailDTOController.class);

	@Autowired
	private StateService maildtoService;
	@Autowired
	private MailReceiverService mailReceiverService;
	@Autowired
	private MailSenderService mailSenderService;
	@Autowired
	private ProducerRTService producerRTService;
	@Autowired
	private  StateService stateService;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private MailDTORepository mailDTORepository;
	@Autowired
	private MailDTORepository producerRTRepository;

	public ArrayList<State> retrieveAllStates() {
		return (ArrayList<State>) stateRepository.findAll();
	}

	public State getState(long id) {
		return stateRepository.getOne(id);
	}

	public State createState(State state) {
		return stateRepository.save(state);
	}



	@Transactional
	public void deleteState(long id) throws Exception {

		State state = stateRepository.getOne(id);
		if (state != null) {
			stateRepository.deleteById(id);
		} else {

			throw new RuntimeException("Record not found");
		}
	}
	
	public State updateState(State state) {
		
		State editedState = stateRepository.getOne(state.getId());
		editedState.setState(state.getState());
		return stateRepository.save(editedState);
	}
}
