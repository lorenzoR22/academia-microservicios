package com.example.pagos_service.Mappers;

import com.example.dtos.pago.PagoRequestDTO;
import com.example.dtos.pago.PagoResponseDTO;
import com.example.pagos_service.Entities.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    PagoResponseDTO toDTO(Pago pago);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "uuid",ignore = true)
    @Mapping(target = "fechaPago",ignore = true)
    @Mapping(target = "usuarioId",ignore = true)
    Pago toEntity(PagoRequestDTO dto);
}
