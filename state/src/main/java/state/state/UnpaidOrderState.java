package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

import java.time.Instant;

public class UnpaidOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.UNPAID;
    }

    @Override
    public void paid(Order order) {

        // change state
        order.status(OrderStatus.READY_TO_SHIP).payTime(Instant.now());

        // write to db

        System.out.println("买家以付款, 等待卖家备货至平台仓库");
    }

    @Override
    public void inCancel(Order order) {

        // 未付款订单，允许直接取消

        // change state
        order.status(OrderStatus.CANCELLED);

        System.out.println("买家申请取消订单");

        cancelled(order);
    }

    @Override
    public void cancelled(Order order) {

        // change state
        order.status(OrderStatus.CANCELLED);

        // write to db

        System.out.println("取消订单申请通过");
    }
}
