package com.eci.sterling.api.webfluxintegration.poc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Arnaldo Trujillo
 */
@Data
@NoArgsConstructor
public class LineItem {

    private boolean stockConfirmed;

    private String productName;

    public LineItem(String productName){
        this.productName = productName;
    }

}
