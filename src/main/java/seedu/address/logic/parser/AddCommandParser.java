package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_AUTHOR_NAME_TOO_LONG;
import static seedu.address.commons.core.Messages.MESSAGE_BOOK_TITLE_TOO_LONG;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.SerialNumber;
import seedu.address.model.book.SerialNumberGenerator;
import seedu.address.model.book.Title;
import seedu.address.model.genre.Genre;
import seedu.address.model.loan.Loan;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final Loan NULL_LOAN = null;

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @return AddCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_SERIAL_NUMBER, PREFIX_AUTHOR,
                        PREFIX_GENRE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_AUTHOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        if (title.toString().length() > 30) {
            throw new ParseException(MESSAGE_BOOK_TITLE_TOO_LONG);
        }

        Author author = ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get());
        if (author.toString().length() > 30) {
            throw new ParseException(MESSAGE_AUTHOR_NAME_TOO_LONG);
        }

        boolean haveSerialNumber = argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent();
        SerialNumber serialNumber;
        if (haveSerialNumber) {
            serialNumber = ParserUtil.parseSerialNumber(argMultimap.getValue(PREFIX_SERIAL_NUMBER).get());
        } else {
            serialNumber = SerialNumberGenerator.generateSerialNumber();
        }
        Set<Genre> genreList = ParserUtil.parseGenres(argMultimap.getAllValues(PREFIX_GENRE));
        Book book = new Book(title, serialNumber, author, NULL_LOAN, genreList);
        return new AddCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
