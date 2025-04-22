package info.jab.ms.services;

import info.jab.ms.domain.God;
import info.jab.ms.repository.GodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GodQueryServiceTest {

    @Mock
    GodRepository godRepository;

    @InjectMocks
    GodQueryService godQueryService;

    @Test
    void testGetAllGodNames_Success() {
        // Arrange
        God zeus = new God("Zeus");
        zeus.setId(1L); // Although ID is not used in mapping, it's good practice for entity state
        God hera = new God("Hera");
        hera.setId(2L);
        List<God> mockGods = Arrays.asList(zeus, hera);
        when(godRepository.listAll()).thenReturn(mockGods);

        // Act
        List<String> result = godQueryService.getAllGodNames();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Arrays.asList("Zeus", "Hera"), result);
    }

    @Test
    void testGetAllGodNames_EmptyList() {
        // Arrange
        when(godRepository.listAll()).thenReturn(Collections.emptyList());

        // Act
        List<String> result = godQueryService.getAllGodNames();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
} 