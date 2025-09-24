package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

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

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {
    model.addAttribute("ingredient", ingredientRepository.findById(id).get());
    return "ingredients/show";
  }

  @GetMapping("/create")
  public String create(Model model) {
    Ingredient ingredient = new Ingredient();
    model.addAttribute("ingredient", ingredient);
    return "ingredients/create-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("ingredient") Ingredient ingredientToStore, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "ingredients/create-edit";
    }
    ingredientRepository.save(ingredientToStore);
    return "redirect:/ingredients";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    model.addAttribute("ingredient", ingredientRepository.findById(id).get());
    model.addAttribute("edit", true);
    return "ingredients/create-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientToUpdate, BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "ingredients/create-edit";
    }
    ingredientRepository.save(ingredientToUpdate);
    return "redirect:/ingredients";
  }

  @PostMapping("delete/{id}")
  public String delete(@PathVariable Integer id) {
    ingredientRepository.deleteById(id);
    return "redirect:/ingredients";
  }
}
