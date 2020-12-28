/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.repository;
/**
 * Mail DTO Repository
 *
 * @author Alias King - Younes OUFRID
 */
import io.novelis.email.ms.model.MailSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSenderRepository extends JpaRepository<MailSender, Long> {

}