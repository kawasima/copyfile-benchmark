package net.unit8.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author kawasima
 */
public class NIOFileChannel extends BaseBenchmark {
    private static final long FILE_COPY_BUFFER_SIZE = 30 * 1024 * 1024;
    File source;
    File dest = new File("dest.dat");

    @Setup
    public void setup() throws IOException {
        source = createTestFile();
    }

    @Benchmark
    public void benchmark() throws IOException {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest);
             FileChannel input = fis.getChannel();
             FileChannel output = fos.getChannel()) {
            final long size = input.size();
            long pos = 0;
            long count;

            while (pos < size) {
                final long remain = size - pos;
                count = remain > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : remain;
                final long bytesCopied = output.transferFrom(input, pos, count);
                if (bytesCopied == 0) {
                    break;
                }
                pos += bytesCopied;
            }

        }
    }

}
