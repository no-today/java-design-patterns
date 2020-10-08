package state;

import state.domain.Order;
import state.event.OrderEvent;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        Random random = new Random();

        OrderEvent event = new OrderEvent();

        List<Order.Item> items = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .map(e -> new Order.Item()
                        .itemId(String.valueOf(e))
                        .itemSkuId(String.valueOf(e))
                        .quantity(random.nextInt(10) + 1)
                        .price(random.nextFloat() * 100))
                .collect(Collectors.toList());

        Order order = event.create(items);
        event.paid(order);
        event.inCancel(order);
//        event.cancelled(order);
        event.readyToShip(order);
        event.shipped(order);
        event.toConfirmReceive(order);
        event.toReturn(order);

        System.out.println(order);
    }
}
