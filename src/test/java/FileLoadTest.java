import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLoadTest {

    @org.junit.jupiter.api.Test
    void testHelpOption() throws Exception {
        String[] args = {"-help"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineOption() throws Exception {
        String[] args = {"-inline", "'abc 123 !@# 3.1415'"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineCSVOption() throws Exception {
        String[] args = {"-csv","-inline", "abc,123,!@#,3.1415"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testInlineJsonOption() throws Exception {
        String[] args = {"-json","-inline", "{'key1' : 'value1' , 'key2': 'value2'}"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadRawOption() throws Exception {
        String[] args = {"-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadCsvOption() throws Exception {
        String[] args = {"-csv","-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }

    @org.junit.jupiter.api.Test
    void testFileReadJsonOption() throws Exception {
        String[] args = {"-json","-file", "test.txt"};
        System.out.println("FileLoad " + String.join(" ", args));
        FileLoad.main(args);
    }
}