package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

import java.time.Instant;
import java.util.List;

public class InitOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return null;
    }

    @Override
    public Order create(List<Order.Item> items, String uid) {
        // init order
        Order order = new Order()
                .status(OrderStatus.UNPAID)
                .createdTime(Instant.now())
                .items(items)
                .totalAmount(items.stream().map(Order.Item::price).reduce(Float::sum).orElse(0F));

        // init state
        order.status(OrderStatus.UNPAID);

        // write to db

        System.out.println("创建订单成功, 等待买家付款");

        return order;
    }
}
