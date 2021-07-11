package spring.onmaven.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller 
public class ParamsController {
	@GetMapping("/person")
	public String name(@RequestParam(value="name", required=false) String name,
			HttpServletRequest request, Model model) {
		String age = request.getParameter("age");
		model.addAttribute("message", "Hello" + name + "\t" +  age);
		return "user";
	}
	 
}
