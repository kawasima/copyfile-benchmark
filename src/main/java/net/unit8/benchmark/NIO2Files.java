package net.unit8.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author kawasima
 */
public class NIO2Files extends BaseBenchmark {
    File source;
    Path dest = Paths.get("dest.dat");

    @Setup
    public void setup() throws IOException {
        source = createTestFile();
    }

    @Benchmark
    public void benchmark() throws IOException {
        Files.copy(source.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
    }
}
