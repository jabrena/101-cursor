package info.jab.ms.controller;

import info.jab.ms.model.GreekGod;
import info.jab.ms.service.GreekGodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreekGodsApiDelegateTest {

    @Mock
    private GreekGodService greekGodService;

    @InjectMocks
    private GreekGodsApiDelegate greekGodsApiDelegate;

    private List<GreekGod> mockGreekGods;

    @BeforeEach
    void setUp() {
        GreekGod zeus = new GreekGod(1L, "Zeus");
        GreekGod poseidon = new GreekGod(2L, "Poseidon");
        GreekGod hades = new GreekGod(3L, "Hades");
        
        mockGreekGods = Arrays.asList(zeus, poseidon, hades);
    }

    @Test
    void getAllGreekGods_ReturnsGreekGods() {
        // Given
        when(greekGodService.getAllGods()).thenReturn(mockGreekGods);
        
        // When
        ResponseEntity<List<GreekGod>> response = greekGodsApiDelegate.getAllGreekGods();
        
        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size());
        assertEquals("Zeus", response.getBody().get(0).getName());
        assertEquals("Poseidon", response.getBody().get(1).getName());
        assertEquals("Hades", response.getBody().get(2).getName());
    }
} 