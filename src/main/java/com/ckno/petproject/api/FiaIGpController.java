package com.ckno.petproject.api;

import com.ckno.petproject.adapters.igp.IGpRepository;
import com.ckno.petproject.adapters.igp.dto.Driver;
import com.ckno.petproject.api.dto.SuperTeam;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/igp")
public class FiaIGpController {

    private final IGpRepository iGpRepository;

    public FiaIGpController(final IGpRepository iGpRepository) {
        this.iGpRepository = iGpRepository;
    }

    @GetMapping("drivers")
    public List<Driver> getDrivers() {
        return iGpRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    @GetMapping("managers")
    public List<Driver> getProManagers() {
        List<Driver> drivers = iGpRepository.findAll();

        List<Driver> managers = new ArrayList<>();
        outer:
        for (Driver driver : drivers) {
            for (Driver manager : managers) {
                manager.setDriver(null);
                if (driver.getTeam().equals(manager.getTeam())) {
                    manager.getEvents().addAll(driver.getEvents());
                    manager.setPoints(manager.getPoints() + driver.getPoints());
                    continue outer;
                }
            }
            managers.add(driver);
        }

        Collections.sort(managers);

        return managers;
    }

    @GetMapping("superteams")
    public List<SuperTeam> getSuperTeams() {
        List<Driver> drivers = iGpRepository.findAll();
        List<SuperTeam> superTeams = new ArrayList<>();

        Arrays.stream(SuperTeam.TeamName.values()).forEach(superTeam -> {
            List<Driver> teamDrivers = drivers.stream()
                    .filter(driver -> driver.getSuperTeam() != null)
                    .filter(driver -> (superTeam.teamName).equals(driver.getSuperTeam()))
                    .collect(Collectors.toList());

            superTeams.add(SuperTeam.builder()
                    .name(superTeam)
                    .points(teamDrivers.stream().mapToInt(Driver::getPoints).sum())
                    .category(teamDrivers.get(0).getCategory())
                    .build());
        });

        return superTeams;
    }
}
