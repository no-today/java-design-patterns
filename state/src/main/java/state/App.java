package state;

import state.domain.Order;
import state.event.OrderEvent;
import state.state.OrderState;
import state.state.OrderStateManager;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        Random random = new Random();

        List<Order.Item> items = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .map(e -> new Order.Item()
                        .itemId(String.valueOf(e))
                        .itemSkuId(String.valueOf(e))
                        .quantity(random.nextInt(10) + 1)
                        .price(random.nextFloat() * 100))
                .collect(Collectors.toList());

//        OrderState event = new OrderEvent();
        OrderState event = new OrderStateManager();

        Order order = event.create(items, "Anonymous");
        event.paid(order);
//        event.inCancel(order);
//        event.cancelled(order);
        event.readyToShip(order);
        event.shipped(order);
        event.toConfirmReceive(order);
        event.toReturn(order);

        System.out.println(order);
    }

}
