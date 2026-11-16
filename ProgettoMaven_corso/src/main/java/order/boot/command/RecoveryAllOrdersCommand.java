package order.boot.command;

import java.util.List;
import order.boot.model.response.OrderResponse;
import order.boot.service.OrdersService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RecoveryAllOrdersCommand {

    private final OrdersService ordersService;

    public RecoveryAllOrdersCommand(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public List<OrderResponse> execute() {
        return ordersService.findAll();
    }
}
