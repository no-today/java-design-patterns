package state.state;

import state.domain.enumeration.OrderStatus;

public class CancelledOrderState implements OrderState {

    @Override
    public OrderStatus currentState() {
        return OrderStatus.CANCELLED;
    }
}
