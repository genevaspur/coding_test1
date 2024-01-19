package com.hslee.coding_test1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuoteDTO {
    public Integer id;
    public String quote;
}
