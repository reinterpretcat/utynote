package com.utynote.test.io;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.utynote.utils.Preconditions.checkNotNull;

public final class ResourceProvider {
    @NonNull
    public static String readString(@NonNull String path) {
        return Stream.of(path)
                .map(ResourceProvider::createStream)
                .map(InputStreamReader::new)
                .map(BufferedReader::new)
                .map(reader -> {
                    StringBuilder text = new StringBuilder();
                    try {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return text.toString();
                })
                .findFirst()
                .get();
    }

    @NonNull
    public static byte[] readBytes(@NonNull String path) {
        return Stream.of(path)
                .map(ResourceProvider::createStream)
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
                })
                .findFirst()
                .get();
    }

    @NonNull
    private static InputStream createStream(@NonNull String path) {
        return checkNotNull(ResourceProvider.class.getClassLoader().getResourceAsStream(path));
    }
}
