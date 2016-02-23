package net.chris.replay;

import java.io.File;

/**
 * Mat Joe Graham Tom
 */

public class ReplayFile implements Comparable<ReplayFile> {

    private final File file;
    private final long whenToSend;

    private ReplayFile(Builder builder) {
        file = builder.file;
        whenToSend = builder.whenToSend;
    }

    public File getFile() {
        return file;
    }

    public long getWhenToSend() {
        return whenToSend;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReplayFile copy) {
        Builder builder = new Builder();
        builder.file = copy.file;
        builder.whenToSend = copy.whenToSend;
        return builder;
    }

    @Override
    public int compareTo(ReplayFile o) {
        //        return Long.compare(whenToSend, o.getWhenToSend());

        Integer thisFile = Integer.parseInt(file.getName().split("\\.")[0]);
        Integer thatFile = Integer.parseInt(o.getFile().getName().split("\\.")[0]);
        return Integer.compare(thisFile, thatFile);
    }


    public static final class Builder {
        private File file;
        private long whenToSend;

        private Builder() {
        }

        public Builder withFile(File val) {
            file = val;
            return this;
        }

        public Builder withWhenToSend(long val) {
            whenToSend = val;
            return this;
        }

        public ReplayFile build() {
            return new ReplayFile(this);
        }
    }
}
