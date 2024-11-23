package pl.sulazula.orderApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sulazula.orderApp.DTO.UserDto;
import pl.sulazula.orderApp.clients.UserClient;
import pl.sulazula.orderApp.model.Order;
import pl.sulazula.orderApp.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{orderId}/user")
    public ResponseEntity<UserDto> getUserForOrder(@PathVariable Long orderId) {
        Long userId = orderService.getUserIdInOrder(orderId)
                .orElse(null);
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto user = userClient.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean removed = orderService.deleteOrder(id);

        return removed ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrdersByUserId(@PathVariable Long id) {
        return orderService.getOrdersByUserId(id);
    }
}
