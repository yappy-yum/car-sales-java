# generate headers
# for more info head over to https://github.com/yappy-yum/headers

PATH_TO_HEADERS = ../headers/src/java.sh
java: 
	@bash $(PATH_TO_HEADERS) ${text}


# a shortcut to run the program directly using terminal

JAVA = "C:/Program Files/Java/jdk-22/bin/java.exe"
JAVAC = "C:/Program Files/Java/jdk-22/bin/javac.exe"
JAVAFX_LIB = lib/javafx-sdk-24.0.1/lib
SRC = src
BIN = bin

MODULES = --module-path "$(JAVAFX_LIB)" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing

deploy:
	$(JAVA) $(MODULES) -cp $(BIN) App