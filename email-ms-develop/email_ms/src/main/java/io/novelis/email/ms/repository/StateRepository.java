/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.repository;

import io.novelis.email.ms.model.State;
import io.novelis.email.ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Query(
			value = "SELECT * FROM states s WHERE s.maildto_id = ?1",
			nativeQuery = true)
	State findStateIDbyMailDTOID(Long idmaildto);
}
