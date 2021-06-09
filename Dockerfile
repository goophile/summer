FROM ubuntu:20.04

ENV DEBIAN_FRONTEND noninteractive


COPY docker/sources.list /etc/apt/

RUN apt-get update --fix-missing && \
    apt-get --yes --fix-missing install default-jdk maven tomcat9 && \
    apt-get clean


RUN mkdir /tmp/build/
WORKDIR /tmp/build

COPY docker/settings.xml .
COPY pom.xml .


# cache packages, avoid re-download on src changes
RUN mvn -s settings.xml package && \
    rm -rf target


COPY src ./src

RUN mvn -s settings.xml package && \
    rm -rf /var/lib/tomcat9/webapps/ROOT && \
    cp target/*.war /var/lib/tomcat9/webapps/ROOT.war && \
    rm -rf target


COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh


EXPOSE 8080
CMD ["/entrypoint.sh"]
