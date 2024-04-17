package id.ac.ui.cs.advprog.fashionpediareport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @GetMapping("/")
    public String review(){
        return "Hello World!";
    }
}