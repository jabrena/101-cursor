package info.jab.ms.services;

import info.jab.ms.domain.God;
import info.jab.ms.repository.GodRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GodQueryServiceTestIT {

    @Inject
    GodQueryService godQueryService;

    @Inject
    GodRepository godRepository; // Inject repository for test setup

    @BeforeEach
    @Transactional
    void setUp() {
        // Clear the database before each test
        godRepository.deleteAll();
    }

    @Test
    @Transactional // Keep transaction for setup
    void testGetAllGodNames_WhenGodsExist() {
        // Arrange: Persist some gods directly using the repository
        godRepository.persist(new God("Zeus"));
        godRepository.persist(new God("Hera"));

        // Act: Call the service method
        List<String> godNames = godQueryService.getAllGodNames();

        // Assert
        assertNotNull(godNames);
        assertEquals(2, godNames.size());
        assertTrue(godNames.contains("Zeus"));
        assertTrue(godNames.contains("Hera"));
    }

    @Test
    void testGetAllGodNames_WhenNoGodsExist() {
        // Arrange: Database is empty due to setUp

        // Act: Call the service method
        List<String> godNames = godQueryService.getAllGodNames();

        // Assert
        assertNotNull(godNames);
        assertTrue(godNames.isEmpty());
    }
} 