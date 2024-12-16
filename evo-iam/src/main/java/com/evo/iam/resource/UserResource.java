package com.evo.iam.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/")
public class UserResource {

    @GetMapping("")
    LocalDate test() {
        return LocalDate.now();
    }

    @GetMapping("api/test")
    LocalDate test2() {
        return LocalDate.now();
    }
}
