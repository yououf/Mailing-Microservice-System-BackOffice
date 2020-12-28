/*
 * MNS - Novelis Mail System - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package io.novelis.email.ms.model;

import io.novelis.email.ms.model.MailDTO;
import io.novelis.email.ms.model.ProducerRT;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type MailReceiver.
 *
 * @author King
 */


@Entity
@Table(name = "States")
@EntityListeners(AuditingEntityListener.class)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = {CascadeType.REMOVE})
    private MailDTO maildto;

    @Column(name = "state", nullable = false)
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MailDTO getMaildto() {
        return maildto;
    }

    public void setMaildto(MailDTO maildto) {
        this.maildto = maildto;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", maildto=" + maildto +
                ", state='" + state + '\'' +
                '}';
    }
}