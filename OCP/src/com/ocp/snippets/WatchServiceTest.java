package com.ocp.snippets;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by helangovan on 7/27/16.
 */
public class WatchServiceTest {
    public static void main(String[] args) throws IOException {

        Path p = Paths.get("/Users/helangovan");
        try (WatchService ws = p.getFileSystem().newWatchService()) {

            p.register(ws, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            for (; ; ) {
                try {
                    WatchKey watchKey = ws.take();

                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        System.out.println(event.kind() + "  " + event.context());

                    }
                    watchKey.reset();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
