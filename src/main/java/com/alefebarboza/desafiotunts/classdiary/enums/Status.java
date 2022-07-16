package com.alefebarboza.desafiotunts.classdiary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    REPROVADO_POR_NOTA("REPROVADO POR NOTA"),
    REPROVADO_POR_FALTA("REPROVADO POR FALTA"),
    EXAME_FINAL("EXAME FINAL"),
    APROVADO("APROVADO");

    private final String description;
}
