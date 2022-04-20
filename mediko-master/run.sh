#build jar file
mvn clean compile assembly:single -Djacoco.skip=true

#run the jar file
java -jar target/mediko-1.0-SNAPSHOT-jar-with-dependencies.jar