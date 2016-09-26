FROM jboss/wildfly:10.1.0.Final

MAINTAINER Olivier Liechti <olivier.liechti@heig-vd.ch>

ADD server/standalone/deployments/app.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
