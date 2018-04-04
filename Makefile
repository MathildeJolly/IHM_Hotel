FLAGS = -g
JC = javac
J = java -cp "mariadb-client.jar:." Main

.java.class:
	$(JC) $(JFLAGS) *.java

CLASSES =	\
			Main.java\
			Fenetre.java\
			AllocationVue.java\
			Database.java\
			Controleur.java\
			ChambreDispo.java\
			Chambre.java\
			Reservation.java\

default: .java.class

clean:
	$(RM) *.class

run: .java.class
	$(J)