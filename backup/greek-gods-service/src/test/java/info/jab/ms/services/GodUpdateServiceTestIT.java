package info.jab.ms.services;

import info.jab.ms.domain.God;
import info.jab.ms.repository.GodRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@QuarkusTest
class GodUpdateServiceTestIT {

    @Inject
    GodUpdateService godUpdateService;

    @Inject
    GodRepository godRepository;

    @InjectMock
    @RestClient // Important: Use @RestClient to ensure the correct bean is mocked
    ExternalGodClient externalGodClient;

    @BeforeEach
    @Transactional
    void setUp() {
        // Clean the database before each test
        godRepository.deleteAll();
    }

    @Test
    @Transactional // Use transactional to easily check DB state after service call within the same test context
    void testUpdateGodDatabase_Success() {
        // Arrange: Mock the external client response
        ExternalGod externalZeus = new ExternalGod();
        externalZeus.setName("ZeusFromExternal");
        ExternalGod externalHera = new ExternalGod();
        externalHera.setName("HeraFromExternal");
        when(externalGodClient.getGods()).thenReturn(Arrays.asList(externalZeus, externalHera));

        // Arrange: Add an initial god to ensure deleteAll works
        godRepository.persist(new God("InitialGod"));
        assertEquals(1, godRepository.count());

        // Act: Call the service method that contains the logic
        godUpdateService.updateGodDatabase();

        // Assert: Check the database state
        assertEquals(2, godRepository.count(), "Database should contain 2 gods after update");
        List<God> gods = godRepository.listAll();
        assertEquals(2, gods.size());
        assertTrue(gods.stream().anyMatch(g -> g.getName().equals("ZeusFromExternal")));
        assertTrue(gods.stream().anyMatch(g -> g.getName().equals("HeraFromExternal")));
    }

    @Test
    @Transactional
    void testUpdateGodDatabase_ClientReturnsEmpty() {
        // Arrange: Mock the external client response as empty
        when(externalGodClient.getGods()).thenReturn(Collections.emptyList());

        // Arrange: Add an initial god
        godRepository.persist(new God("InitialGod"));
        assertEquals(1, godRepository.count());

        // Act
        godUpdateService.updateGodDatabase();

        // Assert: Database should be empty
        assertEquals(0, godRepository.count(), "Database should be empty after update with empty client response");
    }

    // Note: Testing the actual @Scheduled trigger requires different techniques (e.g., awaiting execution)
    // or specific Quarkus testing features, which might be overly complex for this stage.
    // We are testing the *logic* within the scheduled method here.
} 