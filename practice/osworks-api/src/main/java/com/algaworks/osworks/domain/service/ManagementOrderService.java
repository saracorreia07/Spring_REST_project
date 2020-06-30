package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.Commentary;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.model.StatusOrderService;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.repository.CommentaryRepository;
import com.algaworks.osworks.domain.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ManagementOrderService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public OrderService create(OrderService orderService){

        Client client = clientRepository.findById(orderService.getClient().getId()).orElseThrow(() -> new BusinessException("Client not found"));

        orderService.setClient(client);
        orderService.setStatus(StatusOrderService.OPEN);
        orderService.setOpeningDate(OffsetDateTime.now());

        return orderServiceRepository.save(orderService);
    }

    public void finalizing(Long orderServiceId){
        OrderService orderService = get(orderServiceId);

        orderService.finalizing();

        orderServiceRepository.save(orderService);
    }

    public Commentary addCommentary(Long orderServiceId, String description) {

        OrderService orderService = orderServiceRepository.findById(orderServiceId).orElseThrow(() -> new EntityNotFoundException("Order Service not found"));
        Commentary commentary = new Commentary();
        commentary.setSendingDate(OffsetDateTime.now());
        commentary.setDescription(description);
        commentary.setOrderService(orderService);

        return commentaryRepository.save(commentary);
    }

    private OrderService get(Long orderServiceId) {
        return orderServiceRepository.findById(orderServiceId).orElseThrow(() -> new EntityNotFoundException("Order service not found."));
    }
}
