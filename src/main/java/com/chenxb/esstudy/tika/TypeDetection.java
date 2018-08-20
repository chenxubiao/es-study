package com.chenxb.esstudy.tika;

import com.chenxb.esstudy.util.IoUtil;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class TypeDetection {
    public static void main(String[] args) throws Exception {

        //assume example.mp3 is in your current directory
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.MP3_PATH);
        //Instantiating tika facade class
        Tika tika = new Tika();

        //detecting the file type using detect method
        String fileType = tika.detect(file);
        System.out.println(fileType);
    }

    @Test
    public void tikaExtraction() throws IOException, TikaException {
        //Assume sample.txt is in your current directory
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.TXT_PATH);
        //Instantiating Tika facade class
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: \n" + filecontent);
    }

    /**
     * 程序使用的解析器接口内容提取
     * <p>
     * 要使用解析器接口的parse()方法，实例化任何为其提供实现这个接口的类。
     * <p>
     * 也有个别解析器类，如PDFParser，OfficeParser，XMLParser等等。可以使用这些个人文件解析器。或者也可以使用CompositeParser或AutoDetectParser在内部使用的所有解析器类，并提取使用合适的解析器文档的内容。
     *
     * @throws TikaException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void parserExtraction() throws TikaException, SAXException, IOException {
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.TXT_PATH);

        //parse method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        //parsing the file
        parser.parse(inputstream, handler, metadata, context);
        System.out.println("File content : \n" + handler.toString());
    }

    @Test
    public void getMetadata() throws IOException, TikaException, SAXException {
        //Assume that boy.jpg is in your current directory
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.JPG_PATH);

        //Parser method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputstream, handler, metadata, context);
        System.out.println(handler.toString());

        //getting the list of all meta data elements
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }

    @Test
    public void addMetadata() throws IOException, SAXException, TikaException {

        //create a file object and assume sample.txt is in your current directory
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.TXT_PATH);

        //Parser method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        //parsing the document
        parser.parse(inputstream, handler, metadata, context);

        //list of meta data elements before adding new elements
        System.out.println(" metadata elements :" + Arrays.toString(metadata.names()));

        //adding new meta data name value pair
        metadata.add("Author", "Tutorials Point");
        System.out.println(" metadata name value pair is successfully added");

        //printing all the meta data elements after adding new elements
        System.out.println("Here is the list of all the metadata elements  after adding new elements ");
        System.out.println(Arrays.toString(metadata.names()));
    }

    @Test
    public void setMetadata() throws IOException, SAXException, TikaException {

        //Create a file object and assume example.txt is in your current directory
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.TXT_PATH);

        //parameters of parse() method
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        //Parsing the given file
        parser.parse(inputstream, handler, metadata, context);

        //list of meta data elements elements
        System.out.println(" metadata elements and values of the given file :");
        String[] metadataNamesb4 = metadata.names();

        for (String name : metadataNamesb4) {
            System.out.println(name + ": " + metadata.get(name));
        }

        //setting date meta data
        metadata.set(Metadata.DATE, new Date());

        //setting multiple values to author property
        metadata.set(Metadata.AUTHOR, "ram ,raheem ,robin ");

        //printing all the meta data elements with new elements
        System.out.println("List of all the metadata elements  after adding new elements ");
        String[] metadataNamesafter = metadata.names();

        for (String name : metadataNamesafter) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }

    @Test
    public void getLanguage() throws IOException, SAXException, TikaException {

        //Instantiating a file object
        File file = IoUtil.getResourceAsFile(TypeDetection.class, Consts.TXT_PATH);

        //Parser method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream content = new FileInputStream(file);

        //Parsing the given document
        parser.parse(content, handler, metadata, new ParseContext());

        LanguageIdentifier object = new LanguageIdentifier(handler.toString());
        System.out.println("Language name :" + object.getLanguage());
    }
}
