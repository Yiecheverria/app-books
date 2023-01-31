package com.distribuida.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books implements Serializable {
    private Integer id;
    private String isbn;
    private String title;
    private Integer author_id;
    private BigDecimal price;
}
