package com.umcs.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LocalDate createDate;

    public Person(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public void fillFieldsFromResultSet(ResultSet rs) throws SQLException {
        this.id = rs.getInt("Id");
        this.firstName = rs.getString("FirstName");
        this.lastName = rs.getString("LastName");
        Date dob = rs.getDate("DOB");
        if (null != dob) {
            this.dob = dob.toLocalDate();
        }
        Date createDate = rs.getDate("CreateDate");
        if (null != createDate) {
            this.createDate = createDate.toLocalDate();
        }
    }
}
