package com.rodrigues.rodrigues.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
@Entity
public class MachineRoom implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant instant;
    private int flowRate;
    private int pressure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
