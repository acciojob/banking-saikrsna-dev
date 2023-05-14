package com.driver;

import lombok.Setter;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.*;
@Setter
@Getter
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public static int minBalance = 5000;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, minBalance);
        if(balance < minBalance){
            throw new Exception("Insufficent Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!tradeLicenseId.equals(tradeLicenseId.toUpperCase())){
            throw new Exception("Valid License can not be generated");
        }
        int size = tradeLicenseId.length();
        HashMap<Character, Integer> map = new HashMap<>();
        getFrequencyMap(size, map);

        if(Collections.max(map.values()) > size/2){
            throw new Exception("Valid License can not be generated");
        }

        while(!isValidTradeId()){
            List<Character> list = tradeLicenseId.chars().mapToObj(e ->(char)e).collect(Collectors.toList());
            Collections.shuffle(list);
            StringBuilder sb = new StringBuilder();

            // Appends characters one by one
            for(Character ch : list){
                sb.append(ch);
            }

            String tradeId = sb.toString();
            this.tradeLicenseId = tradeId;
        }

    }

    private void getFrequencyMap(int size, HashMap<Character, Integer> map) {
        for(int i = 0; i < size; i++){
            if(map.containsKey(tradeLicenseId.charAt(i))){
                int value = map.get(tradeLicenseId.charAt(i));
                map.put(tradeLicenseId.charAt(i), value + 1);
            }else{
                map.put(tradeLicenseId.charAt(i), 1);
            }
        }
    }

    public boolean isValidTradeId(){
        int size = tradeLicenseId.length();
        for(int i = 0, j = 1; i < size && j < size; i++, j++){
            if(((Character)tradeLicenseId.charAt(i)).equals((Character)tradeLicenseId.charAt(j))){
                break;
            }
            if(j == size - 1){
                return true;
            }
        }
        return false;
    }

}

