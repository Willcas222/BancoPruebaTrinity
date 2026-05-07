package com.example.banco;

import org.springframework.boot.SpringApplication;

public class TestBancoApplication {

    public static void main(String[] args) {
        SpringApplication.from(BancoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
