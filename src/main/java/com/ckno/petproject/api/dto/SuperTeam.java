package com.ckno.petproject.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuperTeam {
    private TeamName name;
    private String category;
    private int points;

    public enum TeamName {
        LA_LLORERIA_BYB_SALOME("La lloreria byb Salome");

        public final String teamName;

        TeamName(String teamName) {
            this.teamName = teamName;
        }
    }
}
