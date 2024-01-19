package com.hslee.coding_test1.controller;

import com.hslee.coding_test1.dto.EmployeesDTO;
import com.hslee.coding_test1.dto.Question5DTO;
import com.hslee.coding_test1.solve.Question1;
import com.hslee.coding_test1.solve.Question3;
import com.hslee.coding_test1.solve.Question5;
import com.hslee.coding_test1.solve.Question8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @GetMapping("/question1")
    public ResponseEntity<String> question1() {
        try {
            String result = new Question1().solve();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    Question3 question3;

    @GetMapping("/question3")
    public ResponseEntity<?> question3() {
        try {
//            List<EmployeesDTO> resultList = question3.solve();
//            return new ResponseEntity<>(resultList, HttpStatus.OK);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    Question5 question5;

    @GetMapping("/question5")
    public ResponseEntity<?> question5() {
        try {
            List<Question5DTO> resultList = question5.solve();
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/question8")
    public ResponseEntity<String> question8(@RequestParam int n, @RequestParam int m) {
        try {
            String result = new Question8().solve(n, m);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
