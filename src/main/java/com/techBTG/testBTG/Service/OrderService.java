package com.techBTG.testBTG.Service;

import com.techBTG.testBTG.Controller.DTO.OrderResponse;
import com.techBTG.testBTG.Entity.OrderItem;
import com.techBTG.testBTG.Entity.OrderEntity;
import com.techBTG.testBTG.Repository.OrderRepository;
import com.techBTG.testBTG.listener.DTO.OrderCreatedEvent;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(OrderCreatedEvent orderCreatedEvent) {
        var entity = new OrderEntity();

        entity.setOrderId(orderCreatedEvent.codigoPedido());
        entity.setCustomerID(orderCreatedEvent.codigoCliente());
        entity.setItems(getOrdemItems(orderCreatedEvent));
        entity.setTotal(getTotal(orderCreatedEvent));

        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens().stream()
                .map(itens -> itens.preco().multiply(BigDecimal.valueOf(itens.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    public BigDecimal findTotalOnOrdersByCustomersId(Long customerID){
        var aggregations = newAggregation(
                match(Criteria.where("customerID").is(customerID)),
                group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations,"tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());


    }


    private static List<OrderItem> getOrdemItems(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens()
                .stream()
                .map(itens -> new OrderItem(itens.produto(), itens.quantidade(), itens.preco()))
                .toList();
    }


    public Page<OrderResponse> findAllByCustomersId(Long customersID, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerID(customersID, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }
}
