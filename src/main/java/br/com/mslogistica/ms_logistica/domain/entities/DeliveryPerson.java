package br.com.mslogistica.ms_logistica.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPerson {

    private Long personCode;

    private String name;

    private String CPF;

    private String phoneNumber;

    private List<DeliveryTraking> deliveryTrakings;

    public DeliveryPerson(String name, String CPF, String phoneNumber) {
        this.name = name;
        this.CPF = CPF;
        this.phoneNumber = phoneNumber;
    }

}
