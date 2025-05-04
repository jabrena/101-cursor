package info.jab.ms.controller;

import info.jab.ms.domain.entity.GreekGod;
import info.jab.ms.service.GreekGodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreekGodController.class)
class GreekGodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreekGodService greekGodService;

    @Test
    void getAllGods_ShouldReturnListOfGods() throws Exception {
        // Given
        List<GreekGod> gods = List.of(
                new GreekGod(1L, "Zeus"),
                new GreekGod(2L, "Hera")
        );
        when(greekGodService.getAllGods()).thenReturn(gods);

        // When & Then
        mockMvc.perform(get("/api/v1/gods/greek")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Zeus"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Hera"));
    }
}