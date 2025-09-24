package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping()
  public String index(Model model) {
    List<Ingredient> ingredients = ingredientRepository.findAll();
    model.addAttribute("ingredients", ingredients);
    return "ingredients/index";
  }
}
