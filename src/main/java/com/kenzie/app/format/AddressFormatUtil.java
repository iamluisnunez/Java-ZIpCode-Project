package com.kenzie.app.format;

import java.util.HashMap;

public class AddressFormatUtil {
    //delcare properties
    private static HashMap<String, String> codeTable = new HashMap<>();

    public static void main(String[] args) {
        String street = "123 main St.";
        initCodeTable();
        System.out.println(replaceAbbreviation(street));
    }

    public static void initCodeTable(){
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("Ave.", "AVENUE");
    }
    public static String replaceAbbreviation(String input){
        String result = "";
        for (String currentKey: codeTable.keySet()) {
            if(input.contains(currentKey)){
                result = input.replace(currentKey, codeTable.get(currentKey));
            }
        }

        return result;
    }
}
