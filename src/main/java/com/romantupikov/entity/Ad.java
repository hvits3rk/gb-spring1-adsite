package com.romantupikov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class Ad extends AbstractEntity {

    private String name;
    private String content;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "published_data")
    private Date publishedData;

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

    public Date getPublishedData() {
        return publishedData;
    }

    public void setPublishedData(Date publishedData) {
        this.publishedData = publishedData;
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
                Objects.equals(publishedData, ad.publishedData) &&
                Objects.equals(company, ad.company) &&
                Objects.equals(category, ad.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, content, phoneNumber, publishedData, company, category);
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
