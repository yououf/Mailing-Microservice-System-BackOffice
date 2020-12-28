/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.controller;


import javax.mail.MessagingException;

import io.novelis.email.ms.model.MailDTO;
import io.novelis.email.ms.service.MailDTOService;
import io.novelis.email.ms.service.MailingService;
import io.novelis.email.ms.service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;


/**
 * Mailing Controller
 *
 * @author Alias King - Younes OUFRID
 */

//@CrossOrigin("http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MailingController {

    private static Logger _log = LoggerFactory.getLogger(MailDTOController.class);

    @Autowired
    private MailingService notificationService;
    @Autowired
    private MailDTOService mailDTOService;
    @Autowired
    private StateService stateService;

    /**
     *
     * @return True if Mail dto was sent successful False for the else case
     * @throws MailException
     */
    @RequestMapping("api/send-one-mail/{id}")
    public Boolean sendOne(@PathVariable Long id) {
        _log.info("Send One Mail DTO by id param ...!");
            MailDTO mdto = mailDTOService.getMailDTO(id);
            return notificationService.sendOneEmail(mdto);
    }

    /**
     *
     * @return True if Mail dto was sent successful False for the else case
     * @throws MessagingException
     */
    @RequestMapping("api/send-one-mail-attachment/{id}")
    public Boolean sendOneWithAttachment(@PathVariable Long id) throws MessagingException {
        _log.info("Send One Mail DTO by id param with attachement ...!");
        MailDTO mdto = mailDTOService.getMailDTO(id);
        return  notificationService.sendOneEmailWithAttachment(mdto);
    }

    /**
     *
     * @return if have some mail dto not sent we get it (mail dtos json objects)
     *         else we get a null object if all mail dto are sent successful
     * @throws MailException
     */
    @RequestMapping("api/send-many-mail/{ids}")
    public ArrayList<MailDTO> sendManyEmail(@PathVariable Long[] ids) throws MailException{
        _log.info("Retry List of Mail DTOs by ids selection ...!");
        ArrayList<MailDTO> maildtolist = new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            maildtolist.add(mailDTOService.getMailDTO(ids[i]));
        }
        ArrayList<MailDTO> maildtos = notificationService.sendManyEmail(maildtolist);
        /*
        if have some mail dto not sent we get it (mail dtos json objects)
        else we get a null object if all mail dto are sent successful
        *  */
        if(!maildtos.isEmpty()){
            return maildtos;
        }else{
            return null;
        }
    }

}
