package order.boot;

import java.math.BigDecimal;
import order.boot.entity.Order;
import order.boot.entity.OrderStatus;
import order.boot.repository.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrdersRepository ordersRepository;

    private Long orderId;

    @BeforeEach
    void setUp() {
        ordersRepository.deleteAll();
        Order order = ordersRepository.save(
                new Order("Tastiera", 2, new BigDecimal("49.90")));
        orderId = order.getId();
    }

    @Test
    void findAllReturnsOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].products").value("Tastiera"))
                .andExpect(jsonPath("$[0].total").value(99.80));
    }

    @Test
    void createReturnsCreatedOrderWithTotal() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"productName":"Mouse","quantity":3,"unitPrice":24.50}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.products").value("Mouse"))
                .andExpect(jsonPath("$.total").value(73.50));
    }

    @Test
    void createRejectsInvalidOrder() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"productName":"Mouse","quantity":0,"unitPrice":24.50}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateStatusAndDeleteOrder() throws Exception {
        mockMvc.perform(put("/api/orders/{id}/status", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"status":"CONFIRMED"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").value("Tastiera"));

        assertEquals(
                OrderStatus.CONFIRMED, ordersRepository.findById(orderId).orElseThrow().getStatus());

        mockMvc.perform(delete("/api/orders/{id}", orderId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isNotFound());
    }
}
