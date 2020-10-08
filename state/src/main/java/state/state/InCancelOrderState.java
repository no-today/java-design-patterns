package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

public class InCancelOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.IN_CANCEL;
    }

    @Override
    public void cancelled(Order order) {

        // 是否同意取消

        // change state
        order.status(OrderStatus.CANCELLED);

        // write to db

        System.out.println("取消订单申请通过");
    }
}
