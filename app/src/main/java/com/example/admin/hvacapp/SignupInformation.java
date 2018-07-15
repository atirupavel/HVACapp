package com.example.admin.hvacapp;

public class SignupInformation { public  String pin_code, name, emailId, mobile_number, address, state_and_city,password;
SignupInformation( String name, String mobile_number, String address, String state_and_city, String pin_code, String emailId,String password){
    this.name=name;
    this.address=address;
    this.mobile_number=mobile_number;
            this.state_and_city=state_and_city;

this.pin_code=pin_code;
        this.emailId=emailId;
                this.password=password;
}
}
