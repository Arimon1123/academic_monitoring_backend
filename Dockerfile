FROM openjdk:22-ea-20-slim-bullseye

ENV DB_HOST=database
ENV DB_PORT=5432
ENV DB_NAME=acad_monitoring
ENV DB_USER=postgres
ENV DB_PASSWORD=password


RUN mkdir -p /opt/arqui_software/logs
VOLUME /opt/arqui_software/logs

EXPOSE 8080

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp","app:app/lib/*", "com/example/backend_academic_monitoring/BackendAcademicMonitoringApplication"]