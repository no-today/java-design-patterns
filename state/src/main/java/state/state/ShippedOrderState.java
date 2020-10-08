package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

public class ShippedOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.SHIPPED;
    }

    @Override
    public void toConfirmReceive(Order order) {

        // change state
        order.status(OrderStatus.TO_CONFIRM_RECEIVE);

        // write to db

        System.out.println("买家确认收货, 订单流程结束");
    }
}
