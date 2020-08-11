FROM maven:3.6.3-openjdk-15 as build

WORKDIR /usr/src/gcwebx
COPY pom.xml /usr/src/gcwebx
RUN ["mvn", "dependency:go-offline","-B"]

COPY src /usr/src/gcwebx/src
RUN ["mvn","package","-DskipTests"]
RUN mkdir -p /usr/src/gcwebx/target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:15-jdk as  prod
ARG DEPENDENCY=/usr/src/gcwebx/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /gcwebx/lib
COPY --from=build ${DEPENDENCY}/META-INF /gcwebx/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /gcwebx

EXPOSE 8080 8080
ENTRYPOINT ["java","-cp","gcwebx:gcwebx/lib/*","de.cschillingtschuehly.gcwebx.GcwebxApplication"]
