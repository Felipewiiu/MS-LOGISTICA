package br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliveryperson;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;
import br.com.mslogistica.ms_logistica.infrastructure.persistence.entityJpa.deliverytraking.DeliveryTrakingJpa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "deliveryPerson")
@Table(name = "ENTREGADOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryPersonJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personCode;

    private String name;

    private String CPF;

    private String phoneNumber;

    @OneToMany(mappedBy = "deliveryPersonCode", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DeliveryTrakingJpa> deliveryTrakings = new ArrayList<>();

    public DeliveryPersonJpa(String name, String CPF, String phoneNumber) {
        this.name = name;
        this.CPF = CPF;
        this.phoneNumber = phoneNumber;
    }

    public void updateDataDeliveryPerson(DeliveryPerson deliveryPerson) {
        if (deliveryPerson.getCPF() != null) {
            this.CPF = deliveryPerson.getCPF();
        }

        if (deliveryPerson.getName() != null) {
            this.name = deliveryPerson.getName();
        }

        if (deliveryPerson.getPhoneNumber() != null) {
            this.phoneNumber = deliveryPerson.getPhoneNumber();
        }
    }

    public void addDeliveryTraking(DeliveryTrakingJpa deliveryTraking) {
        deliveryTrakings.add(deliveryTraking);
        deliveryTraking.setDeliveryPersonCode(this);
    }

    public void removeDeliveryTraking(DeliveryTrakingJpa deliveryTraking) {
        deliveryTrakings.remove(deliveryTraking);
        deliveryTraking.setDeliveryPersonCode(null);
    }

    public void removeOrphanDeliveryTrakings() {
        deliveryTrakings.removeIf(deliveryTraking -> deliveryTraking.getDeliveryPersonCode() == null);
    }
}