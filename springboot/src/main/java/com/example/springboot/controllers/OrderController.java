//package com.example.springboot.controllers;
//
//import com.example.springboot.dtos.OrderRecordDto;
//import com.example.springboot.models.OrderModel;
//import com.example.springboot.repositories.OrderRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class OrderController {
//    @Autowired
//    OrderRepository OrderRepository;
//
//    @PostMapping("/Orders")
//    public ResponseEntity<OrderModel> saveOrder(@RequestBody @Valid OrderRecordDto OrderRecordDto) {
//        var OrderModel = new OrderModel();
//        BeanUtils.copyProperties(OrderRecordDto, OrderModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(OrderRepository.save(OrderModel));
//    }
//    @GetMapping("/Orders")
//    public ResponseEntity<List<OrderModel>> getAllOrders(){
//        List<OrderModel> OrdersList = OrderRepository.findAll();
//        if(!OrdersList.isEmpty()) {
//            for(OrderModel Order : OrdersList) {
//
//            }
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(OrdersList);
//}


package com.example.springboot.controllers;

import com.example.springboot.dtos.OrderRecordDto;
import com.example.springboot.models.OrderModel;
import com.example.springboot.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController
public class OrderController {

    @Autowired
    OrderRepository OrderRepository;

      @PostMapping("/Orders")
    public ResponseEntity<OrderModel> saveOrder(@RequestBody @Valid OrderRecordDto OrderRecordDto) {
        var OrderModel = new OrderModel();
        BeanUtils.copyProperties(OrderRecordDto, OrderModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderRepository.save(OrderModel));
    }
@jakarta.ws.rs.GET
    @GetMapping("/Orders/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") UUID id) {
        Optional<OrderModel> OrderO = OrderRepository.findById(id);
        if (OrderO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(OrderO.get());
    }
    @PutMapping("/Orders/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value="id") UUID id,
                                              @RequestBody @Valid OrderRecordDto OrderRecordDto) {
        Optional<OrderModel> OrderO = OrderRepository.findById(id);
        if(OrderO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        var OrderModel = OrderO.get();
        BeanUtils.copyProperties(OrderRecordDto, OrderModel);
        return ResponseEntity.status(HttpStatus.OK).body(OrderRepository.save(OrderModel));
    }
    @DeleteMapping("/Orders/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") UUID id) {
        Optional<OrderModel> OrderO = OrderRepository.findById(id);
        if(OrderO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        OrderRepository.delete(OrderO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }
}
