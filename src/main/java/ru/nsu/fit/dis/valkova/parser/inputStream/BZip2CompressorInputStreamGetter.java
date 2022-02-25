package ru.nsu.fit.dis.valkova.parser.inputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

public class BZip2CompressorInputStreamGetter implements InputStreamGetter {

    private static final int LENGTH = 2048 * 32;

    @Override
    public BZip2CompressorInputStream get(String inPath, String outPath) throws IOException {
        var in = new BZip2CompressorInputStream(new BufferedInputStream(new FileInputStream(inPath), 4096 * 32));
        if (outPath != null) {
            try (var out = new FileOutputStream(outPath)) {
                IOUtils.copyRange(in, LENGTH, out);
            }
        }
        return in;
    }
}
