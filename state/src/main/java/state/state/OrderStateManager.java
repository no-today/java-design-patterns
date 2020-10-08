package state.state;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStateManager implements OrderState {

    private final Map<OrderStatus, OrderState> states;

    public OrderStateManager() {
        List<OrderState> orderStates = Arrays.asList(
                new InitOrderState(),
                new UnpaidOrderState(),
                new ReadyToShipOrderState(),
                new ShippedOrderState(),
                new ToConfirmReceiveOrderState(),
                new CompletedOrderState(),
                new InCancelOrderState(),
                new CancelledOrderState(),
                new ToConfirmReceiveOrderState()
        );

        states = new HashMap<>((int) Math.ceil(orderStates.size() / 0.75));

        for (OrderState state : orderStates) {
            states.put(state.currentState(), state);
        }

        System.out.println("loading order states...");
    }

    private OrderState getState(OrderStatus status) {
        return states.get(status);
    }

    @Override
    public OrderStatus currentState() {
        throw new IllegalStateException();
    }

    @Override
    public Order create(List<Order.Item> items, String uid) {
        return getState(null).create(items, uid);
    }

    @Override
    public void paid(Order order) {
        getState(order.status()).paid(order);
    }

    @Override
    public void readyToShip(Order order) {
        getState(order.status()).readyToShip(order);
    }

    @Override
    public void shipped(Order order) {
        getState(order.status()).shipped(order);
    }

    @Override
    public void toConfirmReceive(Order order) {
        getState(order.status()).toConfirmReceive(order);
    }

    @Override
    public void inCancel(Order order) {
        getState(order.status()).inCancel(order);
    }

    @Override
    public void cancelled(Order order) {
        getState(order.status()).cancelled(order);
    }

    @Override
    public void toReturn(Order order) {
        getState(order.status()).toReturn(order);
    }

    @Override
    public void completed(Order order) {
        getState(order.status()).completed(order);
    }
}
