FROM jboss/wildfly:10.1.0.Final

MAINTAINER Laureline David <jeremy.david@heig-vd.ch>

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

ADD mysql/ /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

ADD server.xml /opt/jboss/wildfly/standalone/configuration/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "server.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]