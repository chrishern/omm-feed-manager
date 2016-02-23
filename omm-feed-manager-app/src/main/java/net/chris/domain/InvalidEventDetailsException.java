package net.chris.domain;


public class InvalidEventDetailsException extends Exception {

    private String caerusEventId;

    public InvalidEventDetailsException(final Builder builder) {
        this.caerusEventId = builder.caerusEventId;
    }

    @Override
    public String getMessage() {
        return String.format("Data with Caerus ID %s not a valid EventDetails object", caerusEventId);
    }

    public static final Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private String caerusEventId;

        public Builder withCaerusEventId(String caerusEventId) {
            this.caerusEventId = caerusEventId;
            return this;
        }

        public InvalidEventDetailsException build() {
            return new InvalidEventDetailsException(this);
        }
    }
}
