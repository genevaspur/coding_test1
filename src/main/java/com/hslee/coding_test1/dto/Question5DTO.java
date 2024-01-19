package com.hslee.coding_test1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Question5DTO {
    public Integer count;
    public QuoteDTO item;
}
