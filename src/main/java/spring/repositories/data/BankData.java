package spring.repositories.data;

import domain.Bank;
import lombok.Builder;

@Builder
public class BankData {

    private int coins;

    public Bank toDomain() {
        return new Bank(coins);
    }

    public static BankData toData(Bank bank) {
        return BankData.builder().coins(bank.getTotalCoin()).build();
    }
}
