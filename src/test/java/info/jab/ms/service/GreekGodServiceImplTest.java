package info.jab.ms.service;

import info.jab.ms.model.GreekGod;
import info.jab.ms.repository.GreekGodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreekGodServiceImplTest {

    @Mock
    private GreekGodRepository greekGodRepository;

    @InjectMocks
    private GreekGodServiceImpl greekGodService;

    private List<info.jab.ms.repository.GreekGod> mockEntityGreekGods;

    @BeforeEach
    void setUp() {
        info.jab.ms.repository.GreekGod zeus = new info.jab.ms.repository.GreekGod("Zeus");
        zeus.setId(1L);
        
        info.jab.ms.repository.GreekGod poseidon = new info.jab.ms.repository.GreekGod("Poseidon");
        poseidon.setId(2L);
        
        info.jab.ms.repository.GreekGod hades = new info.jab.ms.repository.GreekGod("Hades");
        hades.setId(3L);
        
        mockEntityGreekGods = Arrays.asList(zeus, poseidon, hades);
    }

    @Test
    void getAllGods_ReturnsMappedGreekGods() {
        // Given
        when(greekGodRepository.findAll()).thenReturn(mockEntityGreekGods);
        
        // When
        List<GreekGod> result = greekGodService.getAllGods();
        
        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        
        // Verify mappings
        assertEquals(1L, result.get(0).getId());
        assertEquals("Zeus", result.get(0).getName());
        
        assertEquals(2L, result.get(1).getId());
        assertEquals("Poseidon", result.get(1).getName());
        
        assertEquals(3L, result.get(2).getId());
        assertEquals("Hades", result.get(2).getName());
    }
} 