/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type MailReceiver.
 *
 * @author King
 */


@Entity
@Table(name = "MailSender")
@EntityListeners(AuditingEntityListener.class)
public class MailSender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private ProducerRT producerrt;

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

    public ProducerRT getProducerrt() {
        return producerrt;
    }

    public void setProducerrt(ProducerRT producerrt) {
        this.producerrt = producerrt;
    }

    public MailSender() {
    }

    @Override
    public String toString() {
        return "MailSender{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", producerrt=" + producerrt +
                '}';
    }
}
