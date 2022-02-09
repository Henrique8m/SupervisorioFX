package com.rodrigues.rodrigues.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
@Entity
public class AirExit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant instant;
    private int exit1;
    private int exit2;
    private int exit3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
