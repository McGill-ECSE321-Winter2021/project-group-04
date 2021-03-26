/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup04.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
public abstract class Service {

    private Long serviceId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long aServiceId) {
        this.serviceId = aServiceId;
    }

    private int price;

    public void setPrice(int aPrice) {
        this.price = aPrice;
    }

    public int getPrice() {
        return this.price;
    }

    private String name;

    public void setName(String aName) {
        this.name = aName;
    }

    public String getName() {
        return this.name;
    }

}