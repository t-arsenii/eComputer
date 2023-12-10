package com.example.eComputer;

import com.example.eComputer.controller.ComputerPartController;
import com.example.eComputer.domain.ComputerPartType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.service.ComputerPartsServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ComputerPartControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComputerPartsServiceImp computerPartService;

    @Test
    public void testGetComputerPartById() throws Exception {
        ComputerPartEntity part = new ComputerPartEntity();
        part.setId(1L);
        part.setTitle("CPU");
        part.setPrice(200.0);
        part.setDescription("Central Processing Unit");
        part.setType(ComputerPartType.CPU);
        part.setAmountLeft(10);

        when(computerPartService.getComputerPartById(1L)).thenReturn(Optional.of(part));

        mockMvc.perform(get("/api/parts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("CPU"))
                .andExpect(jsonPath("$.price").value(200.0))
                .andExpect(jsonPath("$.description").value("Central Processing Unit"))
                .andExpect(jsonPath("$.type").value("CPU"))
                .andExpect(jsonPath("$.amountLeft").value(10));
    }

    @Test
    public void testGetAllComputerParts() throws Exception {
        ComputerPartEntity part1 = new ComputerPartEntity();
        part1.setId(1L);
        part1.setTitle("CPU");
        part1.setPrice(200.0);
        part1.setDescription("Central Processing Unit");
        part1.setType(ComputerPartType.CPU);
        part1.setAmountLeft(10);

        ComputerPartEntity part2 = new ComputerPartEntity();
        part2.setId(2L);
        part2.setTitle("GPU");
        part2.setPrice(400.0);
        part2.setDescription("Graphics Processing Unit");
        part2.setType(ComputerPartType.GPU);
        part2.setAmountLeft(5);

        List<ComputerPartEntity> parts = Arrays.asList(part1, part2);

        when(computerPartService.getAllComputerParts()).thenReturn(parts);

        mockMvc.perform(get("/api/parts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("CPU"))
                .andExpect(jsonPath("$[0].price").value(200.0))
                .andExpect(jsonPath("$[0].description").value("Central Processing Unit"))
                .andExpect(jsonPath("$[0].type").value("CPU"))
                .andExpect(jsonPath("$[0].amountLeft").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("GPU"))
                .andExpect(jsonPath("$[1].price").value(400.0))
                .andExpect(jsonPath("$[1].description").value("Graphics Processing Unit"))
                .andExpect(jsonPath("$[1].type").value("GPU"))
                .andExpect(jsonPath("$[1].amountLeft").value(5));
    }
}
