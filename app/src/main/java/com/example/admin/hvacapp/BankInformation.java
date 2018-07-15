package com.example.admin.hvacapp;

public class BankInformation {
    String account_holder,account_number,IFCI_code,branch,bank_name;
    public BankInformation(String account_holder,String bank_name,String branch,String account_number ,String IFCI_code){
        this.account_holder=account_holder;
        this.bank_name=bank_name;
        this.branch=branch;
        this.account_number=account_number;
        this.IFCI_code=IFCI_code;
    }
}
