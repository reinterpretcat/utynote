package com.utynote.test.io;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class AssetProvider {
    @NonNull
    public static String readString(@NonNull String path) {
        return Stream.of(path)
                .map(Function.Util.safe(FileInputStream::new))
                .map(InputStreamReader::new)
                .map(BufferedReader::new)
                .map(bufferedReader -> {
                    StringBuilder text = new StringBuilder();
                    try {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return text.toString();
                }).single();
    }

    @NonNull
    public static byte[] readBytes(@NonNull String path) {
        return Stream.of(path)
                .map(Function.Util.safe(FileInputStream::new))
                .map(is -> {
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    try {
                        int nRead;
                        byte[] data = new byte[16384];
                        while ((nRead = is.read(data, 0, data.length)) != -1) {
                            buffer.write(data, 0, nRead);
                        }
                        buffer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return buffer.toByteArray();
                }).single();
    }
}
