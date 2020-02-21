/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ozar.gokhan.training.springboot.countrycityperson.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByMiddleName", query = "SELECT p FROM Person p WHERE p.middleName = :middleName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByDesignation", query = "SELECT p FROM Person p WHERE p.designation = :designation"),
    @NamedQuery(name = "Person.findBySex", query = "SELECT p FROM Person p WHERE p.sex = :sex"),
    @NamedQuery(name = "Person.findByPhoneNumber", query = "SELECT p FROM Person p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Person.findByEmailAddress", query = "SELECT p FROM Person p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "Person.findByCityName", query = "SELECT p FROM Person p WHERE p.cityName = :cityName"),
    @NamedQuery(name = "Person.findByBirthDate", query = "SELECT p FROM Person p WHERE p.birthDate = :birthDate"),
    @NamedQuery(name = "Person.findByAge", query = "SELECT p FROM Person p WHERE p.age = :age"),
    @NamedQuery(name = "Person.findByIsFriend", query = "SELECT p FROM Person p WHERE p.isFriend = :isFriend"),
    @NamedQuery(name = "Person.findByDateTimeCreated", query = "SELECT p FROM Person p WHERE p.dateTimeCreated = :dateTimeCreated"),
    @NamedQuery(name = "Person.findByDateTimeLastModified", query = "SELECT p FROM Person p WHERE p.dateTimeLastModified = :dateTimeLastModified"),
    @NamedQuery(name = "Person.findByPhoto", query = "SELECT p FROM Person p WHERE p.photo = :photo"),
    @NamedQuery(name = "Person.findByEnabled", query = "SELECT p FROM Person p WHERE p.enabled = :enabled"),
    @NamedQuery(name = "Person.findByDeleted", query = "SELECT p FROM Person p WHERE p.deleted = :deleted")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;
    @Size(max = 40)
    @Column(name = "middle_name", length = 40)
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Size(max = 45)
    @Column(length = 45)
    private String designation;
    private Character sex;
    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @Size(max = 50)
    @Column(name = "email_address", length = 50)
    private String emailAddress;
    @Size(max = 60)
    @Column(name = "city_name", length = 60)
    private String cityName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private Short age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_friend", nullable = false)
    private boolean isFriend;
    @Column(name = "date_time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeCreated;
    @Column(name = "date_time_last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeLastModified;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String notes;
    @Size(max = 255)
    @Column(length = 255)
    private String photo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean enabled;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean deleted;
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne
    private City city;
    @JoinColumn(name = "country_code", referencedColumnName = "iso_code")
    @ManyToOne
    private Country country;

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String firstName, String lastName, boolean isFriend, boolean enabled, boolean deleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isFriend = isFriend;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }

    public Date getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(Date dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public Date getDateTimeLastModified() {
        return dateTimeLastModified;
    }

    public void setDateTimeLastModified(Date dateTimeLastModified) {
        this.dateTimeLastModified = dateTimeLastModified;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ozar.gokhan.training.springboot.countrycityperson.model.Person[ id=" + id + " ]";
    }
    
}
