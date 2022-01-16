build: retele reclame registre

run_retele:
	java Retele

run_reclame:
	java Reclame

run_registre:
	java Registre

retele: Retele.java Task.java Matrix.java
	javac $^

reclame: Reclame.java Task.java Matrix.java
	javac $^

registre: Registre.java Task.java Matrix.java
	javac $^

clean:
	rm -f *.class

.PHONY: build clean
