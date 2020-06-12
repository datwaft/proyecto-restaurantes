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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "billtodish")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Billtodish.findAll", query = "SELECT b FROM Billtodish b"),
  @NamedQuery(name = "Billtodish.findById", query = "SELECT b FROM Billtodish b WHERE b.id = :id"),
  @NamedQuery(name = "Billtodish.findByQuantity", query = "SELECT b FROM Billtodish b WHERE b.quantity = :quantity")})
public class Billtodish implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "quantity")
  private int quantity;
  @JoinColumn(name = "bill_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Bill billId;
  @JoinColumn(name = "dish_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Dish dishId;

  public Billtodish() {
  }

  public Billtodish(Integer id) {
    this.id = id;
  }

  public Billtodish(Integer id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Bill getBillId() {
    return billId;
  }

  public void setBillId(Bill billId) {
    this.billId = billId;
  }

  public Dish getDishId() {
    return dishId;
  }

  public void setDishId(Dish dishId) {
    this.dishId = dishId;
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
    if (!(object instanceof Billtodish)) {
      return false;
    }
    Billtodish other = (Billtodish) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "app.logic.Billtodish[ id=" + id + " ]";
  }
  
}
