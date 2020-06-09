/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "additional")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Additional.findAll", query = "SELECT a FROM Additional a"),
  @NamedQuery(name = "Additional.findById", query = "SELECT a FROM Additional a WHERE a.id = :id"),
  @NamedQuery(name = "Additional.findByPrice", query = "SELECT a FROM Additional a WHERE a.price = :price"),
  @NamedQuery(name = "Additional.findByDescription", query = "SELECT a FROM Additional a WHERE a.description = :description")})
public class Additional implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "price")
  private int price;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "description")
  private String description;
  @JoinColumn(name = "additional_type_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Additionaltype additionalTypeId;

  public Additional() {
  }

  public Additional(Integer id) {
    this.id = id;
  }

  public Additional(Integer id, int price, String description) {
    this.id = id;
    this.price = price;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Additionaltype getAdditionalTypeId() {
    return additionalTypeId;
  }

  public void setAdditionalTypeId(Additionaltype additionalTypeId) {
    this.additionalTypeId = additionalTypeId;
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
    if (!(object instanceof Additional)) {
      return false;
    }
    Additional other = (Additional) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Additional[ id=" + id + " ]";
  }
  
}
