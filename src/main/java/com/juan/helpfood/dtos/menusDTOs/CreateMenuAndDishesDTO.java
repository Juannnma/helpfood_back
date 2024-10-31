package com.juan.helpfood.dtos.menusDTOs;

import com.juan.helpfood.dtos.dishesDTOs.CreateDishDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuAndDishesDTO {

    private String menuName;
    private List<CreateDishDTO> dishes;

}
