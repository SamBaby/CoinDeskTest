package com.test.coindesk.coin;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table
public class Coin {
    @Id
    @SequenceGenerator(
            name = "coin_sequence",
            sequenceName = "coin_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "coin_sequence"
    )
    @Column
    private int id;
    @Column
    private String code;
    @Column
    private String mandarin;
    @Column
    private double rate;
    @Column
    private Timestamp updated;

    public Coin(){

    }

    public Coin(String code, String mandarin, double rate, Timestamp updated) {
        this.code = code;
        this.mandarin = mandarin;
        this.rate = rate;
        this.updated = updated;
    }

    public Coin(int id, String code, String mandarin, double rate, Timestamp updated) {
        this.id = id;
        this.code = code;
        this.mandarin = mandarin;
        this.rate = rate;
        this.updated = updated;
    }

    public String getCode() {
        return code;
    }

    public String getMandarin() {
        return mandarin;
    }

    public double getRate() {
        return rate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMandarin(String mandarin) {
        this.mandarin = mandarin;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"mandarin\":\"" + mandarin + '\"' +
                ", \"rate\":" + rate +
                ", \"updated\":\"" + updated.toLocalDateTime() + '\"' +
                '}';
    }
}
