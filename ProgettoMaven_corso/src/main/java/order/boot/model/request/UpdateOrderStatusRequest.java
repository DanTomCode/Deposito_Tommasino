package order.boot.model.request;

import jakarta.validation.constraints.NotNull;
import order.boot.entity.OrderStatus;

public record UpdateOrderStatusRequest(@NotNull OrderStatus status) {
}
