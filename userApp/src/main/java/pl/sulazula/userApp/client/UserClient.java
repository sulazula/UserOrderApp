package pl.sulazula.userApp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sulazula.userApp.model.User;

import java.util.List;

@FeignClient(name = "orderApp")
public interface UserClient {

    @GetMapping("/orders/user/{orderId}")
    List<User> getUserByOrderId(@PathVariable("orderId") Long orderId);
}
