LAB = lab05

all: executar

executar: compilar mover
	cd /home/kant/unicamp/semestres/s3/materias/mc322/labs/$(LAB)/bin && java Main && cd ../src

compilar:
	javac Main.java

mover:
	mv /home/kant/unicamp/semestres/s3/materias/mc322/labs/$(LAB)/src/*.class /home/kant/unicamp/semestres/s3/materias/mc322/labs/$(LAB)/bin
