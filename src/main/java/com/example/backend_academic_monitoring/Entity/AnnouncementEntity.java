package com.example.backend_academic_monitoring.Entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "announcement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String message;
    private Date date;
    @Column(name = "publishingdate")
    private Date publishingDate;
    @Column(columnDefinition = "jsonb", name = "receivers")
    @Type(JsonBinaryType.class)
    @Convert(converter = ReceiverConverter.class)
    private Receiver receiver;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "announcement_image",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<ImageEntity> images;

}
