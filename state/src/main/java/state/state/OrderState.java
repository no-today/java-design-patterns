package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

import java.util.List;

/**
 * 订单的所有动作
 * <p>
 * 所有状态都需要实现这些动作
 */
public interface OrderState {

    OrderStatus currentState();

    default Order create(List<Order.Item> items, String uid) {
        throw new IllegalStateException();
    }

    default void paid(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void readyToShip(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void shipped(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void toConfirmReceive(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void inCancel(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void cancelled(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void toReturn(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }

    default void completed(Order order) {
        throw new IllegalStateException("Cannot perform the operation in the current state: " + order.status());
    }
}
