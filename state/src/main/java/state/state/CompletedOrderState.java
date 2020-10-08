package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

public class CompletedOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.COMPLETED;
    }

    @Override
    public void toReturn(Order order) {

        // 检测是否符合退货条件，创建退货流程

        // change state
        order.status(OrderStatus.TO_RETURN);

        // write to db

        System.out.println("进入退货流程");
    }
}
