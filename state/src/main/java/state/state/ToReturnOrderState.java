package state.state;

import state.domain.enumeration.OrderStatus;

public class ToReturnOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.TO_RETURN;
    }
}
