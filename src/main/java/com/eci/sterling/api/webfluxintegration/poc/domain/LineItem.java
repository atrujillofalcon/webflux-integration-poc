package com.eci.sterling.api.webfluxintegration.poc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Arnaldo Trujillo
 */
@Data
@NoArgsConstructor
public class LineItem implements Serializable {

    private boolean stockConfirmed;

    private String productName;

    public LineItem(String productName){
        this.productName = productName;
    }

}
