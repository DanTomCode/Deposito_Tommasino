package order.boot.controller;

import jakarta.validation.Valid;
import java.util.List;
import order.boot.command.CreateOrderCommand;
import order.boot.command.RecoveryAllOrdersCommand;
import order.boot.model.request.CreateOrderRequest;
import order.boot.model.request.UpdateOrderStatusRequest;
import order.boot.model.response.OrderResponse;
import order.boot.service.OrdersService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ObjectProvider<RecoveryAllOrdersCommand> recoveryAllOrdersCommandProvider;

    public OrdersController(
            OrdersService ordersService,
            ObjectProvider<RecoveryAllOrdersCommand> recoveryAllOrdersCommandProvider) {
        this.ordersService = ordersService;
        this.recoveryAllOrdersCommandProvider = recoveryAllOrdersCommandProvider;
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return recoveryAllOrdersCommandProvider.getObject().execute();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return ordersService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        CreateOrderCommand command = new CreateOrderCommand(
                request.productName(), request.quantity(), request.unitPrice());
        return ordersService.create(command);
    }

    @PutMapping("/{id}/status")
    public OrderResponse updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        return ordersService.updateStatus(id, request.status());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ordersService.delete(id);
    }
}
