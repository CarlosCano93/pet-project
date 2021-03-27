package com.ckno.petproject.adapters.igp.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("pro_drivers")
@Data
public class ProDriver {
    private @Id String id;
    private String driver;
    private String team;
    private int points;
    private List<Event> events;
}
