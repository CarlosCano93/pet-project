package com.ckno.petproject.api;

import com.ckno.petproject.adapters.igp.IGpRepository;
import com.ckno.petproject.adapters.igp.dto.Driver;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/igp")
public class FiaIGpController {

    private final IGpRepository iGpRepository;

    public FiaIGpController(final IGpRepository iGpRepository) {
        this.iGpRepository = iGpRepository;
    }

    @GetMapping("pro-drivers")
    @Deprecated
    public List<Driver> getProDrivers() {
        return iGpRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
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
                if (driver.getTeam().equals(manager.getTeam())) {
                    manager.setDriver(null);
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
}
