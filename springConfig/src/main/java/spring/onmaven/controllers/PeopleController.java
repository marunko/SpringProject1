package spring.onmaven.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import spring.onmaven.Models.Person;
import spring.onmaven.database.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {
	 private final PersonDAO person = new PersonDAO();
	
	  
	 
	@GetMapping()
	public String index(Model model) throws SQLException
	{
		model.addAttribute("people", person.index());
		return "people/index"; 
	}
	
	@GetMapping("/{id}")
	public String find_id(@PathVariable("id") int id, Model model) throws SQLException  {
		model.addAttribute("id", person.show_id(id));
		model.addAttribute("comments", person.show_comment(id));
		return "people/show";
	}
	
	
	@ModelAttribute("helloMessage")
	public String HeaderHello() {
		return "Hello, all";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("person", new Person());
		return "forms/ModelPost";
	}
	@PostMapping("/create")
	public String create( @ModelAttribute("person") @javax.validation.Valid Person personObject,
			BindingResult bindingResult) throws SQLException {
		if(bindingResult.hasErrors()) {
			return "forms/ModelPost"; 
		}
		person.save(personObject);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) throws SQLException
	{
		model.addAttribute("person", person.show_id(id));
		return "/forms/Edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("person") @javax.validation.Valid Person personObject, BindingResult bR, 
			@PathVariable("id") int id) throws SQLException {
		
		if(bR.hasErrors()) {
			return "/forms/Edit"; 
		}
		
		person.update(id, personObject);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) throws SQLException {
		
		person.delete(id);
		return "redirect:/people";
	}
	
}
