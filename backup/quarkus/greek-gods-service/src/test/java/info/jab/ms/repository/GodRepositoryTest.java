package info.jab.ms.repository;

import info.jab.ms.domain.God;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GodRepositoryTest {

    @Inject
    GodRepository godRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        // Ensure clean state before each test
        godRepository.deleteAll();
    }

    @Test
    @Transactional
    void testPersistAndFindById() {
        // Arrange
        God newGod = new God("Apollo");

        // Act
        godRepository.persist(newGod);
        assertNotNull(newGod.getId(), "ID should be generated after persist");

        Optional<God> foundGodOpt = godRepository.findByIdOptional(newGod.getId());

        // Assert
        assertTrue(foundGodOpt.isPresent(), "God should be found by ID");
        assertEquals("Apollo", foundGodOpt.get().getName());
    }

    @Test
    @Transactional
    void testListAll() {
        // Arrange
        God god1 = new God("Artemis");
        God god2 = new God("Athena");
        godRepository.persist(god1);
        godRepository.persist(god2);

        // Act
        List<God> gods = godRepository.listAll();

        // Assert
        assertNotNull(gods);
        assertEquals(2, gods.size());
        assertTrue(gods.stream().anyMatch(g -> g.getName().equals("Artemis")));
        assertTrue(gods.stream().anyMatch(g -> g.getName().equals("Athena")));
    }

    @Test
    @Transactional
    void testDeleteAll() {
        // Arrange
        God god1 = new God("Poseidon");
        godRepository.persist(god1);
        assertEquals(1, godRepository.count(), "Count should be 1 after persist");

        // Act
        long deletedCount = godRepository.deleteAll();

        // Assert
        assertEquals(1, deletedCount);
        assertEquals(0, godRepository.count(), "Count should be 0 after deleteAll");
    }

     @Test
    @Transactional
    void testDeleteById() {
        // Arrange
        God godToDelete = new God("Hades");
        godRepository.persist(godToDelete);
        assertNotNull(godToDelete.getId());
        Long id = godToDelete.getId();
        assertEquals(1, godRepository.count());

        // Act
        boolean deleted = godRepository.deleteById(id);

        // Assert
        assertTrue(deleted, "Delete operation should return true for existing ID");
        assertEquals(0, godRepository.count(), "Count should be 0 after deleteById");
        assertFalse(godRepository.findByIdOptional(id).isPresent(), "God should not be found after deletion");
    }
} 