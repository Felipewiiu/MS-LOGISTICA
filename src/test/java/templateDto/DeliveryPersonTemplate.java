package templateDto;

import br.com.mslogistica.ms_logistica.domain.entities.DeliveryPerson;

public class DeliveryPersonTemplate {

    public static DeliveryPerson deliveryPersonTemplate(Long id) {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setPersonCode(id);
        deliveryPerson.setName("Joao Silva");
        deliveryPerson.setCPF("333.333.333-33");
        deliveryPerson.setPhoneNumber("(33) 33333-33333");
        return deliveryPerson;
    }
}