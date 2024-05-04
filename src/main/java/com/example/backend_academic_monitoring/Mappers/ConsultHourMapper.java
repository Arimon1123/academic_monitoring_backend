package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.ConsultHourDTO;
import com.example.backend_academic_monitoring.Entity.ConsultHourEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;

public class ConsultHourMapper {
    public static ConsultHourEntity toEntity(ConsultHourDTO hours, TeacherEntity teacher) {
        ConsultHourEntity newHour = new ConsultHourEntity();
        newHour.setId(hours.getId());
        newHour.setWeekday(hours.getWeekday());
        newHour.setTeacherEntity(teacher);
        newHour.setStartTime(hours.getStartTime());
        newHour.setEndTime(hours.getEndTime());
        newHour.setPeriod(hours.getPeriod());
        return newHour;
    }

    public static ConsultHourDTO toDTO(ConsultHourEntity hours) {
        ConsultHourDTO newHour = new ConsultHourDTO();
        newHour.setId(hours.getId());
        newHour.setWeekday(hours.getWeekday());
        newHour.setTeacherId(hours.getTeacherEntity().getId());
        newHour.setStartTime(hours.getStartTime());
        newHour.setEndTime(hours.getEndTime());
        newHour.setPeriod(hours.getPeriod());
        return newHour;
    }
}
