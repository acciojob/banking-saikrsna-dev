package com.driver;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentAccount extends BankAccount{

    String institutionName;

    public StudentAccount(String name, double balance, String institutionName) {
        //minimum balance is 0 by default
        super(name, balance, 0);
        this.institutionName = institutionName;
    }

}
