package order.boot.service;

import java.math.BigDecimal;
import java.util.List;
import order.boot.command.CreateOrderCommand;
import order.boot.entity.Order;
import order.boot.entity.OrderStatus;
import order.boot.model.response.OrderResponse;
import order.boot.repository.OrdersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public OrderResponse create(CreateOrderCommand command) {
        return toResponse(ordersRepository.save(new Order(
                command.productName(), command.quantity(), command.unitPrice())));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return ordersRepository.findAll().stream().map(OrdersService::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long id) {
        return toResponse(findOrder(id));
    }

    public OrderResponse updateStatus(Long id, OrderStatus status) {
        Order order = findOrder(id);
        order.setStatus(status);
        return toResponse(ordersRepository.save(order));
    }

    public void delete(Long id) {
        Order order = findOrder(id);
        ordersRepository.delete(order);
    }

    private Order findOrder(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordine non trovato"));
    }

    private static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getUnitPrice().multiply(BigDecimal.valueOf(order.getQuantity())),
                order.getProductName());
    }
}
