package info.jab.ms.controller;

import info.jab.ms.domain.entity.GreekGod;
import info.jab.ms.service.GreekGodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class GreekGodController {

    private final GreekGodService greekGodService;

    public GreekGodController(GreekGodService greekGodService) {
        this.greekGodService = greekGodService;
    }

    @GetMapping("/gods")
    public ResponseEntity<List<GreekGodResponse>> getAllGods() {
        List<GreekGod> gods = greekGodService.getAllGods();
        List<GreekGodResponse> response = gods.stream()
                .map(this::convertToApiModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private GreekGodResponse convertToApiModel(GreekGod god) {
        return new GreekGodResponse(god.getId(), god.getName());
    }

    // Inner class for API response
    public static class GreekGodResponse {
        private Long id;
        private String name;

        public GreekGodResponse() {
        }

        public GreekGodResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
} 