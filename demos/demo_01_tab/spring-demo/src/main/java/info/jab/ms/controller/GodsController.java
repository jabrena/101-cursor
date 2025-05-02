package info.jab.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gods")
public class GodsController {

    @GetMapping("/greek")
    public List<String> getGreekGods() {
        return List.of(
          "Zeus",
          "Hera",
          "Poseidon",
          "Demeter",
          "Ares",
          "Athena",
          "Apollo",
          "Artemis",
          "Hephaestus",
          "Aphrodite",
          "Hermes",
          "Dionysus",
          "Hades",
          "Hypnos",
          "Nike",
          "Janus",
          "Nemesis",
          "Iris",
          "Hecate",
          "Tyche"
        );
    }

    @GetMapping("/roman")
    public List<String> getRomanGods() {
        return List.of(
            "Venus",
            "Mars",
            "Neptun",
            "Mercury",
            "Pluto",
            "Jupiter"
        );
    }
}
