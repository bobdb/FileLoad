import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLoadTest {

    @org.junit.jupiter.api.Test
    void testHelpOption() {
        System.out.println("testHelpOption()");
        String[] args = {"-help"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineOption() {
        System.out.println("testInlineOption()");
        String[] args = {"-inline", "'abc 123 !@# 3.1415\ndef 456 *%@ 2.718'"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineCSVOption() {
        System.out.println("testInlineCSVOption()");
        String[] args = {"-csv","-inline", "abc,123,!@#,3.1415\nghi,789,%^&,1.0101"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineJsonOption()  {
        System.out.println("testInlineJsonOption()");
        String[] args = {"-json","-inline", "{'key1' : 'value1' , 'key2': 'value2'}"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadRawOption() {
        String[] args = {"-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadCsvOption() {
        String[] args = {"-csv","-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadJsonOption() {
        String[] args = {"-json","-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }
}