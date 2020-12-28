/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmaill.com
 * MNS team coders
 * */

package io.novelis.email.ms.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import io.novelis.email.ms.controller.MailDTOController;
import io.novelis.email.ms.model.MailDTO;
import io.novelis.email.ms.model.MailReceiver;
import io.novelis.email.ms.model.State;
import io.novelis.email.ms.model.StatesOfMail;
import io.novelis.email.ms.repository.StateRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mailing Service
 *
 * @author Alias King - Younes OUFRID
 */

@Service
public class MailingService {

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private JavaMailSender javaMailSender;


    /**
     *
     * @param javaMailSender
     */
    @Autowired
    public MailingService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private MailDTOService maildtoService;

    private static org.slf4j.Logger _log = LoggerFactory.getLogger(MailDTOController.class);
    @Autowired
    private  StateService stateService;
    @Autowired
    private StateRepository stateRepository;
    /**
     * This function is used to send mail without attachment.
     * @param maildto
     * @throws MailException
     */

    public Boolean sendOneEmail(MailDTO maildto) throws MailException {
        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */
        Boolean exception=false;
        String MailContent = "From " + maildto.getMailSender().getEmail() + " ,\n\n" + maildto.getMailContent() + "\n\n" +
                "This mail was sent to you By -Novelis mailing MS-";
                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(GetMailReceiversString(maildto));
                    _log.info("---> sending to "+Arrays.toString(GetMailReceiversString(maildto)));
                    mail.setSubject("[" + maildto.getMailSender().getProducerrt().getName() + "] " + maildto.getMailSubject());
                    mail.setText(MailContent);
                    /*
                     * This send() contains an Object of SimpleMailMessage as an Parameter
                     */
                    try {
                        javaMailSender.send(mail);
                    }catch(MailSendException ex){
                        Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE, null, ex);
                        exception=true;
                    }finally {
                        if (exception){
                            return false;
                        }else{
                            SetSentStateOfMailDTO(maildto);
                            return true;

                        }
                    }
    }

    /**
     * This fucntion is used to send mail that contains a attachment.
     *
     * @param maildto
     * @throws MailException
     * @throws MessagingException
     */
    public Boolean sendOneEmailWithAttachment(MailDTO maildto) throws MailException, MessagingException {

        Boolean exception=false;
            String MailContent = "From " + maildto.getMailSender().getEmail() + " ,\n\n" + maildto.getMailContent() + "\n\n" +
                "This mail was sent to you By -Novelis mailing MS-";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setTo(GetMailReceiversString(maildto));
                    helper.setSubject("[" + maildto.getMailSender().getProducerrt().getName() + "] " + maildto.getMailSubject());
                    helper.setText(MailContent);
                    ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
                    helper.addAttachment(classPathResource.getFilename(), classPathResource);
                    try {
                       javaMailSender.send(mimeMessage);
                    }catch(MailSendException ex){
                        Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE, null, ex);
                        exception=true;
                    }finally {
                        if (exception==true){
                            return false;
                        }else{
                            SetSentStateOfMailDTO(maildto);
                            return true;
                        }
                    }
    }


    /**
     * This fucntion is used to send mails with ids mail dtos param
     * @param  mailDTOS
     * @throws MailException
     * @throws MessagingException
     */
    public ArrayList<MailDTO> sendManyEmail(ArrayList<MailDTO> mailDTOS) throws MailException{
        ArrayList<MailDTO> notsentmaildtos = new ArrayList<MailDTO>();
        boolean exception;
        for (int i=0;i<mailDTOS.size();i++) {
            exception=false;
            MailDTO maildtoboucle = mailDTOS.get(i);
            String MailContent = "From "+maildtoboucle.getMailSender().getEmail()+" ,\n\n"+maildtoboucle.getMailContent()+"\n\n this mail was sent to you by -Novelis Mail MS-";
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(GetMailReceiversString(maildtoboucle));
          //  String subject = "["+maildtoboucle.getMailSender().getProducerrt().getName()+"]"+maildtoboucle.getMailSubject();
            mail.setSubject(maildtoboucle.getMailSubject());
            mail.setText(MailContent);
            try {
                javaMailSender.send(mail);
            }catch(MailSendException ex){
                Logger.getLogger(MailingService.class.getName()).log(Level.SEVERE, null, ex);
                exception=true;
            }finally {
                if (exception==true){
                    notsentmaildtos.add(maildtoboucle);
                }else{
                    // check the body of the method below
                    SetSentStateOfMailDTO(maildtoboucle);
                }
            }
        }

        return notsentmaildtos;

    }

    /*
    this method is used to change the state of MailDTO after sending to "Sent"
    i find the id of state concerned using JPQL request "findStateIDbyMailDTOID" and i update the state
    * */
    public void SetSentStateOfMailDTO(MailDTO mailDTO){
        State state = stateRepository.findStateIDbyMailDTOID(mailDTO.getId());
        state.setMaildto(mailDTO);
        state.setState(StatesOfMail.Sent);
        stateService.updateState(state);
    }

    /*
    this method is used to retreive all emails of MailReceiver of mailDTO object using streams
    important .setTO() of mailing accept just String Array[] so i convert stream list to Array of string before return it
     */
    public String[] GetMailReceiversString(MailDTO mailDTO){
        List<String> mailsto =
                mailDTO.getMailreceivers().stream()
                        .map(MailReceiver::getEmail)
                        .collect(Collectors.toList());
        return mailsto.toArray(new String[0]);
        }

        /*
    * this method used in the first i save it here just like backup of method please don't delete it !! -_-'
    */
    public String[] GetStringEmailsofmailreceivers(MailDTO maildto){
        int size = maildto.getMailreceivers().size();
        String[] stockArr = new String[size];
        for(int j=0;j<size;j++){
            stockArr[j]=maildto.getMailreceivers().get(j).getEmail();
        }
        return stockArr;
    }

}