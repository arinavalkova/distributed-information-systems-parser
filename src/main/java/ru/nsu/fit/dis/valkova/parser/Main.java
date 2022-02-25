package ru.nsu.fit.dis.valkova.parser;

import org.apache.commons.cli.*;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            CommandLine commandLine = getParsedCommandLine(args);
            new JaxbParserToStatisticsToStatistics().parse(commandLine.getOptionValue("i"), commandLine.getOptionValue("o"));
        } catch (IOException | JAXBException | XMLStreamException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static CommandLine getParsedCommandLine(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", "input", true, "Input xml file");
        options.addOption("o", "output", true, "Output xml file");
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }
}
