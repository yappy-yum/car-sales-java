# generate headers
# for more info head over to https://github.com/yappy-yum/headers

PATH_TO_HEADERS = ../headers/src/java.sh
java: 
	@bash $(PATH_TO_HEADERS) ${text}


# a shortcut to run the program directly using terminal

CREATE_BIN = mkdir -p bin

JAVAC = "C:/Program Files/Java/jdk-22/bin/javac.exe"
JAVA = "C:/Program Files/Java/jdk-22/bin/java.exe"

JFX_LIB = lib/javafx-sdk-24.0.1/lib
JFX_MODULES = --module-path "$(JFX_LIB)" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing

ARGON_LIBS = lib/argon2/argon2-jvm-2.8.jar;lib/argon2/jna-5.17.0.jar;lib/argon2/jna-platform-5.17.0.jar

deploy:
	$(CREATE_BIN) && $(JAVAC) -d bin -cp "lib/argon2/*;src" $(JFX_MODULES) src/App.java && $(JAVA) $(JFX_MODULES) -cp "bin;$(ARGON_LIBS)" App