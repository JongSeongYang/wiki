package com.globalorder.wiki.domain.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id @GeneratedValue
    private String id;

    @Column(length = 50,nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String author;

    @UpdateTimestamp
    private Timestamp date;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

}
