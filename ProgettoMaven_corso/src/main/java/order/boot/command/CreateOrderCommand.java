package order.boot.command;

import java.math.BigDecimal;

public record CreateOrderCommand(String productName, int quantity, BigDecimal unitPrice) {
}
