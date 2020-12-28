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
import io.novelis.email.ms.model.ProducerRT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRTRepository extends JpaRepository<ProducerRT, Long> {

}