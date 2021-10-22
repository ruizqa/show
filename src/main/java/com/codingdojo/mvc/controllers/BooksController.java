package com.codingdojo.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

//... imports removed for brevity
@Controller
public class BooksController {
 private final BookService bookService;
 
 public BooksController(BookService bookService) {
     this.bookService = bookService;}

 
 @RequestMapping("/books")
 public String index(Model model) {
     List<Book> books = bookService.allBooks();
     model.addAttribute("books", books);
     return "/books/index.jsp";

 }
 
 @RequestMapping("/books/new")
 public String newBook(@ModelAttribute("book") Book book) {
     return "/books/new.jsp";
 }
 
 @RequestMapping(value="/books", method=RequestMethod.POST)
 public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
     if (result.hasErrors()) {
         return "/books/new.jsp";
     } else {
         bookService.createBook(book);
         return "redirect:/books";
     }
 }
 
 @RequestMapping(value="/books/{id}")
 public String display(@PathVariable("id") Long id, Model model) {
     Book book = new Book();
	 book = bookService.findBook(id);
	 System.out.println(book);
	 model.addAttribute(book);
	 return "/books/show.jsp";
 }


}