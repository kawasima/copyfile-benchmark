package net.unit8.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author kawasima
 */
public class InputStream2OutputStream extends BaseBenchmark {
    private static final int BUFFER_SIZE = 1024 * 4;
    File source;
    File dest = new File("dest.dat");

    @Setup
    public void setup() throws IOException {
        source = createTestFile();
    }

    @Benchmark
    public void benchmark() throws IOException {
        final byte[] buffer = new byte[BUFFER_SIZE];
        int n = 0;

        try (FileInputStream input = new FileInputStream(source);
             FileOutputStream output = new FileOutputStream(dest)) {
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
    }

}
