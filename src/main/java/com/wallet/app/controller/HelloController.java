package com.wallet.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
  @GetMapping("/hello/{id}")
  public String helloById(@PathVariable(name = "id") String helloId) {
    return "Account ID = " + helloId;
  }

  @GetMapping("/hello")
  public String ping(@RequestParam(required = false) Integer page,
                     @RequestParam(required = false) Integer size) {
    return "This is a GET request with params page = " + page + " and size=" + size;
  }

  @PostMapping("/hello")
  public String postHello(@RequestBody(required = false) String input) {
    return "This is a POST request and this is the input : " + input;
  }

  @PutMapping("/hello")
  public String putHello() {
    return "This is a PUT request";
  }

  @PatchMapping("/hello")
  public String patchHello() {
    return "This is a PATCH request";
  }

  @DeleteMapping("/hello")
  public String deleteHello() {
    return "This is a DELETE request";
  }
}
