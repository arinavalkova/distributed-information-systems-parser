package ru.nsu.fit.dis.valkova.parser;

import org.apache.commons.cli.*;
import ru.nsu.fit.dis.valkova.parser.data.loader.LoaderMode;
import ru.nsu.fit.dis.valkova.parser.parser.jaxb.JaxbParser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            CommandLine commandLine = getParsedCommandLine(args);

            List<ParserToStatistics> listOfParser = Arrays.asList(
//                    new JaxbParser(LoaderMode.STATEMENT),
//                    new JaxbParser(LoaderMode.PREPARED),
                    new JaxbParser(LoaderMode.BATCH)
            );

            for (ParserToStatistics parser : listOfParser) {
                parser.parse(commandLine.getOptionValue("i"), commandLine.getOptionValue("o"));
            }
        } catch (IOException | XMLStreamException | ParseException | JAXBException | SQLException e) {
            e.getLocalizedMessage();
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
