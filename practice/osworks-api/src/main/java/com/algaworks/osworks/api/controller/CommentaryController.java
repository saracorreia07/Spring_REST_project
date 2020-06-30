package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Commentary;
import com.algaworks.osworks.domain.model.CommentaryInput;
import com.algaworks.osworks.domain.model.CommentaryModel;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.repository.OrderServiceRepository;
import com.algaworks.osworks.domain.service.ManagementOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders-service/{orderServiceId}/commentaries")
public class CommentaryController {

    @Autowired
    private ManagementOrderService managementOrderService;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CommentaryModel> list(@PathVariable Long orderServiceId) {
        OrderService orderService = orderServiceRepository.findById(orderServiceId).orElseThrow(() -> new EntityNotFoundException("Order service not found."));

        return toCollectionModel(orderService.getCommentaries());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentaryModel add(@PathVariable Long orderServiceId, @Valid @RequestBody CommentaryInput commentaryInput){
        Commentary commentary = managementOrderService.addCommentary(orderServiceId, commentaryInput.getDescription());

        return toModel(commentary);
    }

    private CommentaryModel toModel(Commentary commentary){
        return modelMapper.map(commentary, CommentaryModel.class);
    }

    private List<CommentaryModel> toCollectionModel(List<Commentary> commentaries) {
        return commentaries.stream().map(commentary -> toModel(commentary)).collect(Collectors.toList());
    }
}
