package pl.sulazula.orderApp.service;

import org.springframework.stereotype.Service;
import pl.sulazula.orderApp.model.Order;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders(){
        return orders;
    }

    public Optional<Order> getOrderById(Long id){
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    public Optional<Long> getUserIdInOrder(Long orderId){
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .map(Order::getUserId)
                .findFirst();
    }

    public Order addOrder(Order order){
        orders.add(order);

        return order;
    }

    public boolean deleteOrder(Long id){
        return orders
                .removeIf(order -> order.getId().equals(id));
    }

    public List<Order> getOrdersByUserId(Long userId){
        return orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
