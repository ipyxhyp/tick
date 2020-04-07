package ptr.tick;

import ptr.tick.util.RecordProcessor;

public class Main {

    public static void main(String[] args) {
        RecordProcessor recordProcessor = new RecordProcessor();
        recordProcessor.process("server_input.txt", null);
    }
}
