FROM tomcat

WORKDIR $CATALINA_HOME

RUN rm -r ./webapps/ROOT