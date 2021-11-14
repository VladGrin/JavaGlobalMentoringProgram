package com.epam.architecture.dto.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "place")
    private String place;
    @Column(name = "speaker")
    private String speaker;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
