package com.example.demo.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDto {

    @Id
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private String isbn;

}
