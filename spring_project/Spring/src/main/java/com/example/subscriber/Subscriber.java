package com.example.subscriber;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subs_seq")
    @SequenceGenerator(name = "subs_seq", sequenceName = "SUBS_SEQ", allocationSize = 1)
    private Long id;

    @JsonProperty("subsc_name")
    private String subscName;

    @JsonProperty("subsc_surname")
    private String subscSurname;

    @JsonProperty("msisdn")
    private String msisdn;

    @JsonProperty("tariff_id")
    private int tariffId;

    @JsonProperty("start_date")
    private Date startDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscName() {
        return subscName;
    }

    public void setSubscName(String subscName) {
        this.subscName = subscName;
    }

    public String getSubscSurname() {
        return subscSurname;
    }

    public void setSubscSurname(String subscSurname) {
        this.subscSurname = subscSurname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
