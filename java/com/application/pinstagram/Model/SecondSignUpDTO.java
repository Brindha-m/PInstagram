package com.application.pinstagram.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

public class SecondSignUpDTO implements Serializable {
    @SerializedName("id")
    private String userId;
    @SerializedName("name")
    private String name;
    //    private String imageUrl;
    @SerializedName("gender")
    private String gender;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private String DOB;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("interests")
    private List<String> interests;
    @SerializedName("accountType")
    private String accountType;
    @SerializedName("socialMediaId")
    private int socialMediaId = 2;
    @SerializedName("password")
    private String password;

    public SecondSignUpDTO() {
    }

    public int getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId() {
        this.socialMediaId = 2;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SecondSignUpDTO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", DOB='" + DOB + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", interests=" + interests +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
