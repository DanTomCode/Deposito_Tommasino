package it.corso.products.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.corso.products.api.dto.ProductRequest;
import it.corso.products.persistence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void cleanDatabase() {
        productRepository.deleteAll();
    }

    @Test
    void createsProductAndReturnsSavedProduct() throws Exception {
        ProductRequest request = new ProductRequest(
                "Tastiera",
                new BigDecimal("49.90"),
                12
        );

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome", is("Tastiera")))
                .andExpect(jsonPath("$.prezzoUnitario", is(49.90)))
                .andExpect(jsonPath("$.quantitaDisponibile", is(12)));

        org.junit.jupiter.api.Assertions.assertEquals(1, productRepository.count());
    }

    @Test
    void rejectsInvalidProductRequest() throws Exception {
        ProductRequest request = new ProductRequest(
                " ",
                new BigDecimal("-1.00"),
                -2
        );

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        org.junit.jupiter.api.Assertions.assertEquals(0, productRepository.count());
    }
}
