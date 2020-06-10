/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
  @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
  @NamedQuery(name = "Bill.findByAddress", query = "SELECT b FROM Bill b WHERE b.address = :address"),
  @NamedQuery(name = "Bill.findByClient", query = "SELECT b FROM Bill b WHERE b.client = :client"),
  @NamedQuery(name = "Bill.findByDeliveryType", query = "SELECT b FROM Bill b WHERE b.deliveryType = :deliveryType")})
public class Bill implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "address")
  private String address;
  @Basic(optional = false)
  @Column(name = "client")
  private String client;
  @Basic(optional = false)
  @Column(name = "deliveryType")
  private String deliveryType;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "billId")
  private List<Billtodish> billtodishList;
  @JoinColumn(name = "user_id", referencedColumnName = "email")
  @ManyToOne
  private User userId;

  public Bill() {
  }

  public Bill(Integer id) {
    this.id = id;
  }

  public Bill(Integer id, String address, String client, String deliveryType) {
    this.id = id;
    this.address = address;
    this.client = client;
    this.deliveryType = deliveryType;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getDeliveryType() {
    return deliveryType;
  }

  public void setDeliveryType(String deliveryType) {
    this.deliveryType = deliveryType;
  }

  @XmlTransient
  public List<Billtodish> getBilltodishList() {
    return billtodishList;
  }

  public void setBilltodishList(List<Billtodish> billtodishList) {
    this.billtodishList = billtodishList;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
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
    if (!(object instanceof Bill)) {
      return false;
    }
    Bill other = (Bill) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Bill[ id=" + id + " ]";
  }
  
}
