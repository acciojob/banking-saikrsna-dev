package com.driver;

import lombok.Setter;
import lombok.Getter;

import java.util.Random;

@Getter
@Setter
public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum < 0 || sum > digits*9){
            throw new AccountNumberCannotBeGeneratedException("Account Number cannot be generated");
        }
        String accNo = "";
        int remainingSum = sum;
        Random rand = new Random();
        int n;
        for(int i = 0; i < digits; i++){
            int max = Math.min(remainingSum + 1, 10);
            n = rand.nextInt(max);
            accNo += String.valueOf(n);
            remainingSum -= n;
        }
        return accNo;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.balance - amount < this.minBalance){
            throw new Exception("Insufficient Balance");
        }
        this.balance -= amount;
    }

}