package com.caravana.agendamento.model.enums;

import javax.management.relation.Role;

public enum Roles {
    ADMIN(1),
    STAKE(2),
    LOCAL_LEADER(3),
    USER(4);

    private int code;

    private Roles(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Roles valueOf(int code) {
        for (Roles roles : Roles.values()) {
            if (roles.getCode() == code) {
                return roles;
            }
        }
        throw new IllegalArgumentException("Erro de usuario");
    }
}
