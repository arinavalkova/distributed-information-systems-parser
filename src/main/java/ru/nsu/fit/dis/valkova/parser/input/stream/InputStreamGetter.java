package ru.nsu.fit.dis.valkova.parser.input.stream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InputStreamGetter {
    BZip2CompressorInputStream get(String inPath, String outPath) throws IOException;
}
