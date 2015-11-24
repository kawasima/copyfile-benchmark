package net.unit8.benchmark;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class BaseBenchmark {
    protected static final int PAGE_SIZE = 1024 * 4;
    private static final long FILE_SIZE = PAGE_SIZE * 1000L * 50L;
    private static final byte[] BLANK_PAGE = new byte[PAGE_SIZE];

    protected File createTestFile() throws IOException {
        File testFile = new File("test.dat");
        try (RandomAccessFile file = new RandomAccessFile(testFile, "rw")) {
            for (long i = 0; i < FILE_SIZE; i+= PAGE_SIZE) {
                file.write(BLANK_PAGE, 0, PAGE_SIZE);
            }
        }
        return testFile;
    }
}
