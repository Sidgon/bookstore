package com.david.gsponer.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.david.gsponer.bookstore.domain.Book;
import com.david.gsponer.bookstore.domain.BookRepository;
import com.david.gsponer.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/booklist")
	public String getindex(Model model) {
		ArrayList<Book> books=(ArrayList<Book>) repository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model){
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editbook";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String saveeditedBook(@PathVariable("id") Long bookId, Model model, Book book){
		repository.delete(bookId);
		repository.save(book);
		return "redirect:../booklist";
	}
	
	//===========REST starts here=============
	@RequestMapping(value="/booklistjson", method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooks(){
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value="/bookjson/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findbookRest(@PathVariable("id") Long bookId) {
		return repository.findOne(bookId);
	}
	
	//security
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	
	
/*
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginpost(Model model) {
		System.out.println("login succesfull");
		return "redirect:booklist";
	}
*/
}
