package com.octenweb.springbootmvcsecurity.controllers;

import com.octenweb.springbootmvcsecurity.dao.DocumentDAO;
import com.octenweb.springbootmvcsecurity.entity.Document;
import com.octenweb.springbootmvcsecurity.util.LocalDateTimeEditor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class MainController {
    private DocumentDAO documentDAO;
//    @Autowired
    private LocalDateTimeEditor localDateTimeEditor;

    @GetMapping("/")
    public String home(Model model) {
//        model.addAttribute("xxx", "hi! man!");
        model.addAttribute("document", new Document());

        return "homeTemplate";
    }

       @InitBinder
       public  void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class,localDateTimeEditor);

       }

    @PostMapping("/saveDocument")
    public String saveUser(Document document, Model model) {
        model.addAttribute(document);
        documentDAO.save(document);



        return "next";
    }


    @GetMapping("/findDocumentByName")
    public String findDocumentByName (int id){
        documentDAO.findById(id).toString();
        return "DASDA";
    }
    @GetMapping("/showById")
    public String showById (@PathVariable int id,Model model){
        Document document = documentDAO.findById(id).get();
        model.addAttribute("idDocument",document);
        return "redirect:/";


//    @PostMapping("/editUser")
//    public String editDocument(Document document, Model model) {
//        System.out.println(document);
//        documentDAO.save(document);
//
//        model.addAttribute("someDocument", document);
//        List<Document> all = documentDAO.findAll();
//        model.addAttribute("documents", all);
//
//        return "next";

    }

}
