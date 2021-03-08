run: build
	java -classpath build-out Main
	make clean

build: $(shell find src -name *.java)
	mkdir -p build-out
	javac -d build-out src/*.java

clean: $(shell find . -name *.class)
	rm -f $^

