package com.tydic.spc.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtils {

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            close0(closeable);
        }
    }

    private static void close0(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            // nothing to do
        }
    }
}
