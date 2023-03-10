package com.fastcampus.projectboardadmin.domain.converter;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter()
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        return attribute.stream().map(RoleType::name).sorted()
                        .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {
        // update 쿼리 등을 위해 변경가능하게 toUnmodifiableSet이 아니라 toSet으로 반환
        return Arrays.stream(dbData.split(DELIMITER)).map(RoleType::valueOf)
                     .collect(Collectors.toSet());
    }
}
