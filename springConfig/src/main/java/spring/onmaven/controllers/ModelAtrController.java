package spring.onmaven.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.onmaven.Models.Person;

@Controller 
@RequestMapping("/models")
public class ModelAtrController {

	
	@GetMapping("/simple")
	public String simple(Model model, String name) {
		name = "Vasia";
		model.addAttribute("keyname", name);
		return "/model/variable";
	}
	
	@GetMapping("/names")
	public String mess(Model model) {
		
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("tom", 33));
		list.add(new Person("bom", 33));
		list.add(new Person("sem", 33));
		
		model.addAttribute("keynames", list);
		return "/model/iterate";
	}
	@GetMapping("/additions")
	public String name(@ModelAttribute("keynames") ArrayList<Person> list ) {
		
		 list = new ArrayList<Person>();
		list.add(new Person("tom", 33));
		list.add(new Person("bom", 33));
		list.add(new Person("sem", 33));
		
		//model.addAttribute("keynames", list);
		return "/model/iterate";
	}
}
 
