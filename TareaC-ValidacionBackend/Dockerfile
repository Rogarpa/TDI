# Install Tomcat & openjdk 17 (has java and javac)
FROM tomcat:9.0.58-jdk17-openjdk

# Copy source files to tomcat folder structure
COPY . /usr/local/tomcat/webapps/myApp

# -cp, Adding compile time classpath as Tomcat's /lib/servlet-api.jar file.
# - d, destination output location.
RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/src/TestingServlet.java"]

RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/prueba_hora.jsp"]

# Serve Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]