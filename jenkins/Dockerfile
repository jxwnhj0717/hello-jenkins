FROM adoptopenjdk:8-jdk-hotspot
ENV TZ=Asia/Shanghai \
    DEBIAN_FRONTEND=noninteractive
RUN apt update \
    && apt install -y tzdata \
    && ln -fs /usr/share/zoneinfo/${TZ} /etc/localtime \
    && echo ${TZ} > /etc/timezone \
    && dpkg-reconfigure --frontend noninteractive tzdata \
    && rm -rf /var/lib/apt/lists/*
EXPOSE 8090
#RUN ls -R
COPY libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]