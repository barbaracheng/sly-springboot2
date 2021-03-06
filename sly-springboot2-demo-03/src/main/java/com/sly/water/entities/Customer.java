package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer cid;

    private String custName;

    private String custMobile;

    private String custAddress;

    private Integer custTicket;
}
