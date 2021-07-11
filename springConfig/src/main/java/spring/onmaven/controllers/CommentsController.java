package spring.onmaven.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.onmaven.Models.Comments;
import spring.onmaven.Models.Pages;
import spring.onmaven.Models.Person;
import spring.onmaven.database.CommentsTemplate;
import spring.onmaven.database.PersonDAO;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	private final CommentsTemplate ct;
	
	@Autowired
	public CommentsController(CommentsTemplate ct) {
		this.ct = ct;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("comments", ct.index());
		return "comments/index";
	}
	
	@GetMapping("/page/{id}")
	public String indexPag(@PathVariable("id") int id,Model model) {
		// use a list and add elements amount for <a href> cycle
		int pages = ct.CountAll(); int maxPerPage = 5;
		int min = Pages.getFrom(id, maxPerPage);
		Pages p = new Pages();
		List<Comments> list = new ArrayList<>();
		list = ct.indexPagination();
	 
		model.addAttribute("comments",list.stream().skip(Long.valueOf(min)).limit(Long.valueOf(maxPerPage)).collect(Collectors.toList()));
		model.addAttribute("pages", p.PageList(pages, maxPerPage));
		model.addAttribute("id", id);
		model.addAttribute("plus", p.PageAddOne(id));
		model.addAttribute("minus", p.PageMinOne(id));
		return "comments/indexPag";
	}
	
	@GetMapping("/{id}")
	public String Comment(@PathVariable("id") int id, Model model) throws SQLException
	{
		model.addAttribute("comment", ct.show(id));
		//back page save to session or transmit to page as a variable 
		return "comments/oneComment";
	}
	
	@GetMapping("/create")
	public String create(Model model) throws SQLException {
		model.addAttribute("comments", new Comments());
		model.addAttribute("person", new PersonDAO().index());
		return "comments/create";
	} 
	
	@PostMapping("/create")
	public String create(@ModelAttribute("comments") Comments comment, @ModelAttribute("person") Person p) {
		//comment.setDate("2021-06-01");
		
		ct.save(comment, p);
		return "redirect:/comments/page/1";
	}
	
	
	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		
		ct.delete(id);
		return "redirect:/comments/page/1";
	}
}
