package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.OrderRequestPostDto;
import com.ada.sorvetada.dtos.OrderRequestPutDto;
import com.ada.sorvetada.dtos.OrderResponseDto;
import com.ada.sorvetada.services.OrderService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderResponseDto> registerOrder(
            @RequestBody @Valid OrderRequestPostDto newOrder
    ) {
        OrderResponseDto order = this.orderService.saveOrder(newOrder);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new ResponseEntity<>(
                this.orderService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{idOrder}")
    public ResponseEntity<OrderResponseDto> getOneOrder(
            @PathVariable("idOrder") Long id
    ) {
        return new ResponseEntity<>(
                this.orderService.getOrderById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("{idOrder}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable("idOrder") Long id,
            @RequestBody @Valid OrderRequestPutDto orderToUpdate
    ) {
        OrderResponseDto orderUpdated = this.orderService.updateOrderById(id, orderToUpdate);

        return new ResponseEntity<>(orderUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{idOrder}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable("idOrder") Long id
    ) {
        this.orderService.deleteOrder(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
