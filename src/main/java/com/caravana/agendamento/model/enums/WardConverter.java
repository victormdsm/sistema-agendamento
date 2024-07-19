package com.caravana.agendamento.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class WardConverter implements AttributeConverter<Ward, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Ward ward) {
        if(ward == null) {
            return null;
        }
        return ward.getCode();
    }

    @Override
    public Ward convertToEntityAttribute(Integer ward) {
        if(ward == null) {
            return null;
        }
        return Ward.valueOf(ward);
    }
}
