package com.example.eComputer;
import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.domain.ComputerPartType;
import com.example.eComputer.repository.ComputerPartRepository;
import com.example.eComputer.service.ComputerPartsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComputerPartsServiceTest {

    @Mock
    private ComputerPartRepository computerPartRepository;

    @InjectMocks
    private ComputerPartsService computerPartsService;

    @Test
    public void testGetAllComputerParts() {
        // Arrange
        List<ComputerPartEntity> partsList = new ArrayList<>();
        partsList.add(new ComputerPartEntity(1L, "CPU", "Intel Core i5", 199.99, ComputerPartType.CPU, 10, null));
        partsList.add(new ComputerPartEntity(2L, "GPU", "NVIDIA GTX 1660", 299.99, ComputerPartType.GPU, 5, null));

        when(computerPartRepository.findAll()).thenReturn(partsList);

        // Act
        List<ComputerPartEntity> result = computerPartsService.getAllComputerParts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("CPU", result.get(0).getTitle());
        assertEquals("GPU", result.get(1).getTitle());
    }

    @Test
    public void testGetComputerPartById() {
        // Arrange
        Long partId = 1L;
        ComputerPartEntity part = new ComputerPartEntity(partId, "CPU", "Intel Core i5", 199.99, ComputerPartType.CPU, 10, null);

        when(computerPartRepository.findById(partId)).thenReturn(Optional.of(part));

        // Act
        Optional<ComputerPartEntity> result = computerPartsService.getComputerPartById(partId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("CPU", result.get().getTitle());
    }

    @Test
    public void testDeleteComputerPart() {
        // Arrange
        Long partId = 1L;

        when(computerPartRepository.existsById(partId)).thenReturn(true);

        // Act
        boolean result = computerPartsService.deleteComputerPart(partId);

        // Assert
        assertTrue(result);
        verify(computerPartRepository, Mockito.times(1)).deleteById(partId);
    }


}
