package de.havox_design.aoc.utils.java.input;

import de.havox_design.aoc.utils.java.exceptions.ReadDataException;
import org.apache.struts2.util.ClassLoaderUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Data reader for Advent of Code input files.
 */
public class DataReader {
    /**
     * Constructor for the data reader.
     */
    private DataReader() {
        super();
    }

    /**
     * Reads the file contents as a {@link List<String>} representing the rows of the file.
     *
     * @param fileName the file name
     * @param callingClass the calling class
     * @return the list of rows on the file
     * @throws ReadDataException if the file cannot be read
     */
    public static List<String> readData(String fileName, Class<?> callingClass) {
        try {
            URL url = ClassLoaderUtil.getResource(fileName, callingClass);
            Path path = Paths.get(url.toURI());

            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException|URISyntaxException e) {
            throw new ReadDataException(e);
        }
        catch (NullPointerException e) {
            throw new ReadDataException("The file " + fileName + " could not be found.", e);
        }
    }

    /**
     * Reads the file content as a {@link String}.
     * <br/>
     * <br/>
     * <u>Warning</u>: <i>Please mind the system specific line separators.</i>
     *
     * @param fileName the file name
     * @param callingClass the calling class
     * @return the file content
     * @throws ReadDataException if the file cannot be read
     */
    public static String readString(String fileName, Class<?> callingClass) {
        try {
            URL url = ClassLoaderUtil.getResource(fileName, callingClass);
            Path path = Paths.get(url.toURI());

            return Files.readString(path, StandardCharsets.UTF_8);
        }
        catch (IOException|URISyntaxException e) {
            throw new ReadDataException(e);
        }
        catch (NullPointerException e) {
            throw new ReadDataException("The file " + fileName + " could not be found.", e);
        }
    }
}
