package br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson;

public record DeliveryPersonDto(

        Long personCode,

        String name,

        String CPF,

        String phoneNumber
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long personCode;
        private String name;
        private String CPF;
        private String phoneNumber;

        public Builder personCode(Long personCode) {
            this.personCode = personCode;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder CPF(String CPF) {
            this.CPF = CPF;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DeliveryPersonDto build() {
            return new DeliveryPersonDto(personCode, name, CPF, phoneNumber);
        }
    }
}

