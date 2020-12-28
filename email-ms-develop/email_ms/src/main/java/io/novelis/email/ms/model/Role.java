/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private io.novelis.email.ms.model.ERole name;

	public Role() {

	}

	public Role(io.novelis.email.ms.model.ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public io.novelis.email.ms.model.ERole getName() {
		return name;
	}

	public void setName(io.novelis.email.ms.model.ERole name) {
		this.name = name;
	}
}