package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.api.model.OrderServiceInput;
import com.algaworks.osworks.api.model.OrderServiceModel;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.repository.OrderServiceRepository;
import com.algaworks.osworks.domain.service.ManagementOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders-service")
public class OrderServiceController {

    @Autowired
    private ManagementOrderService managementOrderService;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderServiceModel create(@Valid @RequestBody OrderServiceInput orderServiceInput) {
        OrderService orderService = toEntity(orderServiceInput);
        return toModel(managementOrderService.create(orderService));
    }

    @GetMapping
    public List<OrderServiceModel> list(){

        return toCollectionModel(orderServiceRepository.findAll());
    }

    @GetMapping("/{orderServiceId}")
    public ResponseEntity<OrderServiceModel> get(@PathVariable Long orderServiceId) {
        Optional<OrderService> orderService = orderServiceRepository.findById(orderServiceId);

        if(orderService.isPresent()) {
            OrderServiceModel orderServiceModel = toModel(orderService.get());

            return ResponseEntity.ok(orderServiceModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{orderServiceId}/finalizing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizing(@PathVariable Long orderServiceId) {
        managementOrderService.finalizing(orderServiceId);
    }

    private OrderServiceModel toModel(OrderService orderService){
        return modelMapper.map(orderService, OrderServiceModel.class);
    }

    private List<OrderServiceModel> toCollectionModel(List<OrderService> ordersService) {
        return ordersService.stream().map(orderService -> toModel(orderService)).collect(Collectors.toList());
    }

    private OrderService toEntity(OrderServiceInput orderServiceInput){
        return modelMapper.map(orderServiceInput, OrderService.class);
    }
}
