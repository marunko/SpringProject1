package spring.onmaven.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupingController {
	@RequestMapping(value = "/one", 
			  method = RequestMethod.GET)
	public String one() {
		return "one";
	}
	
	@RequestMapping(value = "/two", 
			  method = RequestMethod.GET)
	public String two() {
		return "two";
	}
}

