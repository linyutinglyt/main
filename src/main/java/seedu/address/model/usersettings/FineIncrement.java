package seedu.address.model.usersettings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the default fine amount in cents of a overdue book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFineIncrement(String)}
 */
public class FineIncrement {

    public static final String MESSAGE_CONSTRAINTS =
            "Fine increment should be positive and should not exceed Integer.MAX_VALUE cents.";
    public static final String VALIDATION_REGEX = "\\d+";
    public final int fineIncrement;

    /**
     * Constructs a {@code FineIncrement}.
     *
     * @param fineIncrement A valid fine increment in String.
     */
    public FineIncrement(String fineIncrement) {
        requireNonNull(fineIncrement);
        checkArgument(isValidFineIncrement(fineIncrement), MESSAGE_CONSTRAINTS);
        this.fineIncrement = Integer.parseInt(fineIncrement);
    }

    /**
     * Constructs a {@code FineIncrement}.
     *
     * @param fineIncrement A valid fine increment in integer.
     */
    public FineIncrement(int fineIncrement) {
        this.fineIncrement = fineIncrement;
    }

    public int getFineIncrement() {
        return fineIncrement;
    }

    /**
     * Returns true if a given string is a valid fine amount.
     */
    public static boolean isValidFineIncrement(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%d", fineIncrement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FineIncrement // instanceof handles nulls
                && fineIncrement == (((FineIncrement) other).fineIncrement)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(fineIncrement);
    }
}
