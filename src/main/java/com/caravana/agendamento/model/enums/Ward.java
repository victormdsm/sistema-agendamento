package com.caravana.agendamento.model.enums;

public enum Ward {
    ALA_ITAIPU(1),
    ALA_MEDIANEIRA(2),
    ALA_FOZ_DO_IGUAÇU(3),
    ALA_CATARATAS(4),
    ALA_PORTAL_DA_FOZ(5),
    ALA_PORTO_MEIRA(6);

    private int code;

    private Ward(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Ward valueOf(int code) {
        for (Ward ward : Ward.values()) {
            if (ward.getCode() == code) {
                return ward;
            }
        }
        throw new IllegalArgumentException("Ala invalida");
    }
}
