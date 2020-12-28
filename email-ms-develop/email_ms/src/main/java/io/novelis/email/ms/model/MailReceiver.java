/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */
package io.novelis.email.ms.model;
/**
 * The type MailReceiver.
 *
 * @author King
 */

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "MailReceiver")
@EntityListeners(AuditingEntityListener.class)
public class MailReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MailReceiver() {
    }


    @Override
    public String toString() {
        return "MailReceiver{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
