package com.romantupikov.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ad")
public class Ad extends AbstractEntity {

    @Size(max = 255, min = 3, message = "{error.size}")
    @NotEmpty(message = "{error.empty}")
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{error.empty}")
    private String content;

    @Column(name = "phone_number")
    @Size(max = 255, min = 3, message = "{error.size}")
    @NotEmpty(message = "{error.empty}")
    private String phoneNumber;

    @Column(name = "published_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date publishedDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(name, ad.name) &&
                Objects.equals(content, ad.content) &&
                Objects.equals(phoneNumber, ad.phoneNumber) &&
                Objects.equals(publishedDate, ad.publishedDate) &&
                Objects.equals(company, ad.company) &&
                Objects.equals(category, ad.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, content, phoneNumber, publishedDate, company, category);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", company=" + company +
                ", category=" + category +
                '}';
    }
}
