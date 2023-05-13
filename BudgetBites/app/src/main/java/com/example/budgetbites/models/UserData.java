package com.example.budgetbites.models;

public class UserData {

    public String Name;
    public String Email;
    public String Bio;
    public String Goals;
    public String Challenges;
    public String Succ;

    public String imageURL;

    public UserData(){}

    public UserData(String n, String e, String b, String g, String c, String s, String u){
        Name = n;
        Email = e;
        Bio = b;
        Goals = g;
        Challenges = c;
        Succ = s;
        imageURL = u;
    }
}
