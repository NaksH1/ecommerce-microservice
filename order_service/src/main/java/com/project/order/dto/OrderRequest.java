package com.project.order.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
     private List<OrderLineItemsDto> orderLineItemsDtoList;
}
