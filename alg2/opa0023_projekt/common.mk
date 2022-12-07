OUT = bin

SEARCHPATH += src
vpath %.cpp $(SEARCHPATH)
vpath %.h $(SEARCHPATH)

DEPS += main.h cube.h fileOpener.h towerBuilder.h

_OBJS += main.o cube.o fileOpener.o towerBuilder.o

OBJS = $(patsubst %,$(OUT)/%,$(_OBJS))

all: $(PROG)

$(OUT)/%.o: %.cpp %.h $(DEPS)
	@mkdir -p $(OUT)
	$(CC) $(CFLAGS) $(CXXFLAGS) -c -o $@ $<
	
clean:
	$(RM) -rf $(OUT) $(PROG)
