package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Employee;
import com.example.demo.EmployeeRepo;

@Controller
@RequestMapping(path = "/demo")
public class DemoController {
    @Autowired 
    
    private EmployeeRepo employeeRepo;



    //docker run --name mysql -d -p 3306 -e MYSQL_ROOT_PASSWORD=1224 mysql
    //docker run --name phpmyadmin -d --link mysql:db -p 3606:80 phpmyadmin

    //curl localhost:8080/demo/add -d name=Test -d mail=test@mail.com -d age=28
    @PostMapping(path = "/add")
    public @ResponseBody String addNewEmployee(@RequestParam String name, @RequestParam String mail, @RequestParam String age){
        Employee e = new Employee();
        e.setAge(Integer.valueOf(age));
        e.setMail(mail);
        e.setName(name);
        employeeRepo.save(e);
        return "added";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    
    
}
