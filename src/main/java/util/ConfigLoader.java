package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();
    private static final String CONFIGURATION_FILE_PATH = "application.properties";

    static {
        try {
            properties.load(new FileReader(CONFIGURATION_FILE_PATH));
        } catch (IOException exception) {
            throw new RuntimeException("설정 파일 로딩에 실패하였습니다.");
        }
    }

    public static String getProperty(final String property) {
        return properties.getProperty(property);
    }
}
