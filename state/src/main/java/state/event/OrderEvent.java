package state.event;

import state.domain.Order;
import state.domain.enumeration.OrderStatus;
import state.state.OrderState;

import java.time.Instant;
import java.util.List;

/**
 * 常规实现
 * <p>
 * if-else 到代码里
 */
public class OrderEvent implements OrderState {

    @Override
    public OrderStatus currentState() {
        return null;
    }

    @Override
    public Order create(List<Order.Item> items, String uid) {
        // init order
        Order order = new Order()
                .uid(uid)
                .status(OrderStatus.UNPAID)
                .createdTime(Instant.now())
                .items(items)
                .totalAmount(items.stream().map(Order.Item::price).reduce(Float::sum).orElse(0F));

        // write to db

        System.out.println("创建订单成功, 等待买家付款");

        return order;
    }

    @Override
    public void paid(Order order) {
        if (!OrderStatus.UNPAID.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.READY_TO_SHIP + "，当前订单状态为: " + order.status());
        }

        order.status(OrderStatus.READY_TO_SHIP).payTime(Instant.now());

        // write to db

        System.out.println("买家以付款, 等待卖家备货至平台仓库");
    }

    @Override
    public void readyToShip(Order order) {
        if (OrderStatus.COMPLETED.equals(order.status())) {
            throw new IllegalStateException("该订单以取消，无法放入平台仓库");
        }

        System.out.println("货物以入平台仓库");
    }

    @Override
    public void shipped(Order order) {
        if (OrderStatus.IN_CANCEL.equals(order.status())) {
            System.out.println("货物已经开始运输，拦截取消订单失败");
        } else if (!OrderStatus.READY_TO_SHIP.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.SHIPPED + "，当前订单状态为: " + order.status());
        }

        order.status(OrderStatus.SHIPPED);

        // write to db

        System.out.println("货物开始运输, 等待买家收货");
    }

    @Override
    public void toConfirmReceive(Order order) {
        if (!OrderStatus.SHIPPED.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.TO_CONFIRM_RECEIVE + "，当前订单状态为: " + order.status());
        }

        order.status(OrderStatus.TO_CONFIRM_RECEIVE);

        // write to db

        System.out.println("买家确认收货, 订单流程结束");
    }

    @Override
    public void inCancel(Order order) {
        if (OrderStatus.COMPLETED.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.TO_CONFIRM_RECEIVE + "，当前订单状态为: " + order.status());
        }
        if (OrderStatus.IN_CANCEL.equals(order.status())) {
            throw new IllegalStateException("订单正在取消，请勿重复操作");
        }

        // 未进入物流运输环节，通常可以直接取消
        if (OrderStatus.UNPAID.equals(order.status())) {
            order.status(OrderStatus.IN_CANCEL);

            // write to db

            System.out.println("买家申请取消订单");

            this.cancelled(order);
        } else {
            // 已进入物流运输环节则需要卖家|平台审批
            order.status(OrderStatus.IN_CANCEL);

            /*
             * 同未收到货，申请退货流程类似
             * 尝试拦截订单，已经发出去了的，通常都是到达之后再发一个快递退回来.
             */

            // write to db
        }
    }

    @Override
    public void cancelled(Order order) {
        if (!OrderStatus.IN_CANCEL.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.TO_CONFIRM_RECEIVE + "，当前订单状态为: " + order.status());
        }

        order.status(OrderStatus.CANCELLED);

        // write to db

        System.out.println("取消订单申请通过");
    }

    @Override
    public void toReturn(Order order) {
        if (!OrderStatus.COMPLETED.equals(order.status())) {
            throw new IllegalStateException("无法修改订单状态为: " + OrderStatus.TO_CONFIRM_RECEIVE + "，当前订单状态为: " + order.status());
        }

        // 检测是否符合退货条件，创建退货流程

        order.status(OrderStatus.TO_RETURN);

        // write to db

        System.out.println("进入退货流程");
    }

    @Override
    public void completed(Order order) {
        order.status(OrderStatus.COMPLETED);

        System.out.println("订单完成");
    }
}
