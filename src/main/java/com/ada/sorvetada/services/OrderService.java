package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.*;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.entities.Icecream;
import com.ada.sorvetada.entities.Item;
import com.ada.sorvetada.entities.Order;
import com.ada.sorvetada.repositories.CustomerRepository;
import com.ada.sorvetada.repositories.IcecreamRepository;
import com.ada.sorvetada.repositories.ItemRepository;
import com.ada.sorvetada.repositories.OrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final IcecreamRepository icecreamRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, IcecreamRepository icecreamRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.icecreamRepository = icecreamRepository;
        this.itemRepository = itemRepository;
    }

    private Order findOrderById(Long id) {
        return this.orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "O id do pedido é inválido!"
                        )
                );
    }

    private Customer verifyCustomerIdExists(Long id) {
        return this.customerRepository
                .findById(
                        id
                )
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "O id do cliente é inválido!"
                        )
                );
    }

    private Icecream verifyIcecreamExists(List<Icecream> icecreams, Long productId) {
        return icecreams
                .stream()
                .filter(
                        ice -> ice.getId().equals(productId)
                )
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "O id do produto é inválido!"
                        )
                );
    }

    private List<Item> convertListItemsPutDtoToListItems(List<ItemRequestPutDto> items) {
        List<Icecream> icecreams = this.icecreamRepository.findAll();
        return items
                .stream()
                .map(itemDto -> {
                    Item item = new Item();
                    /*Icecream icecream = icecreams
                            .stream()
                            .filter(
                                    ice -> ice.getId().equals(itemDto.productId())
                            )
                            .findFirst()
                            .orElseThrow(
                                    () -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            "O id do produto é inválido!"
                                    )
                            );*/
                    Icecream icecream = this.verifyIcecreamExists(icecreams, itemDto.productId());

                    item.setId(itemDto.id());
                    item.setProduct(icecream);
                    item.setQuantity(itemDto.quantity());
                    item.setPrice(itemDto.price());

                    return item;
                })
                // .toList();
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<ItemRequestPutDto> convertItemPostToItemPut(List<ItemRequestPostDto> items) {
        return items
                .stream()
                .map(ItemRequestPutDto::new)
                // .collect(Collectors.toList());
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void addOrderIdToItems(Order order) {
        order.getItems().forEach(
                item -> {
                    item.setOrder(order);

                    this.itemRepository.save(item);
                }
        );
    }

    public OrderResponseDto saveOrder(OrderRequestPostDto newOrder) {
        Order order = new Order();

        Customer customer = this.verifyCustomerIdExists(newOrder.customerId());

        List<ItemRequestPutDto> itemsPut = this.convertItemPostToItemPut(newOrder.items());
        List<Item> items = this.convertListItemsPutDtoToListItems(itemsPut);

        order.setCustomer(customer);
        order.setTotalPrice(newOrder.totalPrice());
        order.setItems(items);

        Order orderAdded = this.orderRepository.save(
                order
        );

        this.addOrderIdToItems(orderAdded);

        return new OrderResponseDto(
                order
        );
    }

    public List<OrderResponseDto> getAll() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(OrderResponseDto::new)
                .toList();
    }

    public OrderResponseDto getOrderById(Long id) {
        return new OrderResponseDto(
                this.findOrderById(id)
        );
    }

    @Transactional
    public OrderResponseDto updateOrderById(
            Long id,
            OrderRequestPutDto orderToUpdate
    ) {
        Order orderFound = this.findOrderById(id);
        List<Item> items = this.convertListItemsPutDtoToListItems(orderToUpdate.items());

        orderFound.setTotalPrice(orderToUpdate.totalPrice());
        orderFound.setItems(items);

        Order orderAdded = this.orderRepository.save(
                orderFound
        );

        this.addOrderIdToItems(orderAdded);

        return new OrderResponseDto(
                orderAdded
        );
    }

    public void deleteOrder(Long id) {
        Order order = this.findOrderById(id);

        this.orderRepository.delete(order);
    }
}
