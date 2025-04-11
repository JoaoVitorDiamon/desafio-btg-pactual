package com.techBTG.testBTG.Repository;

import com.techBTG.testBTG.Controller.DTO.OrderResponse;
import com.techBTG.testBTG.Entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerID(Long customersID, PageRequest pageRequest);



}
