package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hospital/booking")
public class BookingController {

    @PostMapping("")
    public String booking(@RequestBody BookingDTO bookingDTO) {


        return "ok";
    }

}
