package com.ckno.petproject.api;

import com.ckno.petproject.adapters.igp.IGpRepository;
import com.ckno.petproject.adapters.igp.dto.ProDriver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/igp")
public class FiaIGpController {

    private final IGpRepository iGpRepository;

    public FiaIGpController(final IGpRepository iGpRepository) {
        this.iGpRepository = iGpRepository;
    }

    @CrossOrigin(origins = "https://campeonato-fia-igp.herokuapp.com/")
    @GetMapping("pro-drivers")
    public List<ProDriver> getProDrivers() {
        return iGpRepository.findAll();
    }
}
