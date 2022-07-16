package com.alefebarboza.desafiotunts.classdiary.utils;

import com.alefebarboza.desafiotunts.classdiary.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ClassDiaryUtils {

    private final float ABSENCE_LIMIT = 25;
    private final float NUMBER_OF_CLASSES = 60;

    public List<String> getStatus(float n1, float n2, float n3, int absence) {

        Status status = null;
        float passingGrade = 0;
        float average = getAverage(n1, n2, n3);
        List<String> statusList = new ArrayList<>();

        if (absenceLimit(absence, ABSENCE_LIMIT, NUMBER_OF_CLASSES)) {
            status = Status.REPROVADO_POR_FALTA;

        } else if (average >= 70) {
            status = Status.APROVADO;

        } else if (average >= 50) {
            status = Status.EXAME_FINAL;
            passingGrade = getPassingGrade(average);

        } else {
            status = Status.REPROVADO_POR_NOTA;
        }

        statusList.add(status.getDescription());
        statusList.add(String.format("%.0f", passingGrade));
        return statusList;
    }

    public float getPassingGrade(float average) {
        //RULE: m: média, naf: nota final para aprovação
        // 5 <= (m + naf)/2
        return 100 - average;
    }

    private float getAverage(float n1, float n2, float n3) {
        return Math.round((n1 + n2 + n3) / 3);
    }

    private boolean absenceLimit(int absence, float absenceLimit, float numberOfClasses) {
        return (absenceLimit < (absence * 100) / numberOfClasses);
    }
}
