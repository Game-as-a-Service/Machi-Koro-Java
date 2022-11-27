package main.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank(78);
    }

    @Test
    void payCoin() {
        fail();
    }

    @Test
    void gainCoin() {
        fail();
    }
}