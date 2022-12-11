package domain;

import domain.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank(100);
    }

    @Test
    void payCoin() {
        bank.payCoin(1);
        assertEquals(99, bank.getTotalCoin());
    }

    @Test
    void gainCoin() {
        bank.gainCoin(1);
        assertEquals(101, bank.getTotalCoin());
    }
}