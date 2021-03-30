package com.ckno.petproject.adapters.igp.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("drivers")
@Data
public class Driver implements Comparable<Driver> {
    private @Id String id;
    private String driver;
    private String team;
    private String category;
    private int points;
    private List<Event> events;

    @Override
    public int compareTo(Driver o) {
        return o.getPoints() - this.getPoints();
    }

}
