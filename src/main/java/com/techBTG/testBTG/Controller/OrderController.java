package com.techBTG.testBTG.Controller;


import com.techBTG.testBTG.Controller.DTO.ApiResponse;
import com.techBTG.testBTG.Controller.DTO.OrderResponse;
import com.techBTG.testBTG.Controller.DTO.PaginationResponse;
import com.techBTG.testBTG.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/customers/{customersId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> ordersByCustomersId(
            @PathVariable long customersId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = orderService.findAllByCustomersId(customersId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomersId(customersId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }

}


