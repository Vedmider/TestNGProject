package com.study;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("employee")
public class Employee {
    @XStreamAlias("lastName")
    private String lastName;
    @XStreamAlias("firstName")
    private String firstName;
    @XStreamAlias("middleName")
    private String middleName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
