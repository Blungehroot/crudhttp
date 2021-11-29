package com.crudhttp.app.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "events", schema = "public")
@Data
@RequiredArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String eventName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "event_medias",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "file_id") }
    )
    private Media media;

}
