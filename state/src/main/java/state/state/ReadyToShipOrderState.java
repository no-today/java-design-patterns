package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

public class ReadyToShipOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.READY_TO_SHIP;
    }

    @Override
    public void readyToShip(Order order) {
        System.out.println("货物以入平台仓库");
    }

    @Override
    public void shipped(Order order) {

        // change state
        order.status(OrderStatus.SHIPPED);

        // write to db

        System.out.println("货物开始运输, 等待买家收货");
    }
}