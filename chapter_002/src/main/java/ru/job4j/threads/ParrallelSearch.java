package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

@ThreadSafe
public class ParrallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean isFinish = false;

    @GuardedBy("this")
    private Queue<String> files = new LinkedBlockingQueue<>();

    @GuardedBy("this")
    private List<String> paths = Collections.synchronizedList(new ArrayList<>());

    public ParrallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    @GuardedBy("this")
    public synchronized List<String> result() {
        return this.paths;
    }

    public void init() {
        Thread read = new Thread() {

            @Override
            public void run() {
                while (files.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (!isFinish || !files.isEmpty()) {
                    String file = files.poll();
                    if (text.equals(file)) {
                        paths.add(file);
                    }
                }
                for (String path : paths) {
                    System.out.println(path);
                }
            }
        };

        Thread search = new Thread() {
            private final FileSearch walkFiles = new FileSearch();

            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(root), walkFiles);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isFinish = true;
            }
        };
    }

    private final class FileSearch extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            boolean pass = false;
            for (String ext : exts) {
                if (file.endsWith(ext)) {
                    pass = true;
                    break;
                }
            }
            if (pass) {
                files.offer(file.getFileName().toString());
                this.notifyAll();
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
