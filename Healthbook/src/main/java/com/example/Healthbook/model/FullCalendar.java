package com.example.Healthbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name="calendar")
@Table(name ="calendar")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class FullCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title must not be empty")
    private String title;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String color;
}
