package com.eci.sterling.api.webfluxintegration.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Arnaldo Trujillo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    String orderId;

    String parentOrderId;

    boolean confirmed;

    List<LineItem> lineItems;

}
