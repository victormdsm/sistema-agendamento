package com.caravana.agendamento.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<Roles, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Roles role) {
        if(role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public Roles convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Roles.valueOf(code);
    }
}

