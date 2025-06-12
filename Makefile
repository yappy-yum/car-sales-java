# generate headers
# for more info head over to https://github.com/yappy-yum/headers

PATH_TO_HEADERS = ../headers/src/java.sh
java: 
	@bash $(PATH_TO_HEADERS) ${text}

# a shortcut to run the program directly using terminal

CREATE_BIN = mkdir -p bin

# !! file path may differs !!
JAVAC = "C:/Program Files/Java/jdk-22/bin/javac.exe"
JAVA = "C:/Program Files/Java/jdk-22/bin/java.exe"

JFX_LIB = lib/javafx-sdk-24.0.1/lib
JFX_MODULES = --module-path "$(JFX_LIB)" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing

ARGON_LIBS = lib/argon2/argon2-jvm-2.8.jar;lib/argon2/jna-5.17.0.jar;lib/argon2/jna-platform-5.17.0.jar
DOTENV_LIB = lib/dotenv/dotenv-java-3.2.0.jar

JUNIT_JAR = lib/junit/junit-platform-console-standalone-1.13.0-RC1.jar

tests:
	$(CREATE_BIN) && $(JAVAC) -d bin $(JFX_MODULES) -cp "src;$(ARGON_LIBS);$(DOTENV_LIB);$(JUNIT_JAR)" src/**/*.java && $(JAVAC) -d bin $(JFX_MODULES) -cp "bin;$(ARGON_LIBS);$(DOTENV_LIB);$(JUNIT_JAR)" test/loginTest.java test/AgeIncTest.java && $(JAVA) $(JFX_MODULES) -jar $(JUNIT_JAR) --class-path "bin;$(ARGON_LIBS);$(DOTENV_LIB);$(JUNIT_JAR)" --scan-classpath

deploy:
	$(CREATE_BIN) && $(JAVAC) -d bin -cp "lib/argon2/*;$(DOTENV_LIB);src" $(JFX_MODULES) src/App.java && $(JAVA) $(JFX_MODULES) -cp "bin;$(ARGON_LIBS);$(DOTENV_LIB)" App
