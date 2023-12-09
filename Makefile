JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

SRC_DIR = src
OUT_DIR = $(SRC_DIR)


SRCS := $(wildcard $(SRC_DIR)/*.java)

CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

.SUFFIXES: .java .class

.PHONY: compile clean run

compile: $(CLS)


$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	javac -d $(OUT_DIR)/ -classpath $(SRC_DIR)/ $<


run: compile
	java -classpath $(OUT_DIR) Main

clean:
	$(RM) $(CLS)
