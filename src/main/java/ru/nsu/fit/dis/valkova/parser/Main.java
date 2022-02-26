package ru.nsu.fit.dis.valkova.parser;

import org.apache.commons.cli.*;
import ru.nsu.fit.dis.valkova.parser.jaxb.JaxbParser;
import ru.nsu.fit.dis.valkova.parser.stax.StAXParser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            CommandLine commandLine = getParsedCommandLine(args);
            parser.get(ParseMode.JAXB).parse(commandLine.getOptionValue("i"), commandLine.getOptionValue("o"));
        } catch (IOException | XMLStreamException | ParseException | JAXBException e) {
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

    private static final Map<ParseMode, ParserToStatistics> parser = Map.of(
            ParseMode.JAXB, new JaxbParser(),
            ParseMode.STAX, new StAXParser()
    );
}
