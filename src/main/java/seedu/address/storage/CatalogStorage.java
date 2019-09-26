package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Catalog;
import seedu.address.model.ReadOnlyCatalog;

/**
 * Represents a storage for {@link Catalog}.
 */
public interface CatalogStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCatalogFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyCatalog}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCatalog> readCatalog() throws DataConversionException, IOException;

    /**
     * @see #getCatalogFilePath()
     */
    Optional<ReadOnlyCatalog> readCatalog(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCatalog} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyCatalog addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyCatalog)
     */
    void saveAddressBook(ReadOnlyCatalog addressBook, Path filePath) throws IOException;

}
