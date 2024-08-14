package edu.odu.cs.cs350;

import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 * A simple command line driver to an analyze the static content on each page
 * within a local copy of a website.
 * 
 * @author yomnaE1
 */
public class Driver {
    /**
     * The main function for the command line Driver class
     * 
     * @param args represent the command line arguments
     * @throws Exception to throw an exception
     */
    public static void main(String[] args) throws Exception {

        // Commandline argument validation
        checkForInsufficientArgs(args);

        // Handle user arguments
        Path websitePath = getWebPath(args);

        // Grab the remaining arguments using a Java Stream
        // (for some functional style programming)
        Set<URL> baseURLs = getBaseURLs(args);

        Website site = new WebsiteBuilder()
                .withPath(websitePath)
                .withURLs(baseURLs)
                .build();

        ReportManager manager = new ReportManager();
        manager.setSourceData(site);

        // We want to control when this happens... since time does not pause.
        manager.determineBaseFileName();

        // Write the reports before writing the filenames.
        // If something goes wrong... we do not want to
        // output the filename for a report that was not generated
        manager.writeAll();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out));
        manager.writeReportNames(writer);
    }

    /**
     * Process the path to a local website as a first command line arguement
     * 
     * @param args command line arguemnts
     * @return Path as a first command line argument
     */
    public static Path getWebPath(String[] args) throws IOException {
        return Paths.get(args[0]);
    }

    /**
     * Processes command line arguments to extract the base URL and optional
     * supplemental URLs
     * 
     * @param args Command line arguments
     * @return A set of URLs including the base URL and any supplemental URLs
     * @throws Exception if URL parsing fails
     */
    public static Set<URL> getBaseURLs(String[] args) {
        Set<URL> baseURLs = Arrays.stream(args)
                .skip(1)
                .map(arg -> {
                    try {
                        return new URL(arg);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
        return baseURLs;
    }

    /**
     * Prints the error message when the user fails
     * to provide the correct number of arguments to the console.
     */
    public static void checkForInsufficientArgs(String[] args) 
    throws ArrayIndexOutOfBoundsException   
    {
        if(args.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Insufficient number of arguments provided.");
        }
    }
}
