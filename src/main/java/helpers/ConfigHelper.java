package helpers;

import java.io.*;
import java.util.Properties;
import java.util.function.Supplier;

public class ConfigHelper {

    File file = new File("src/main/resources/env/config.properties");
    private final String propertyFilePath = file.getAbsolutePath();
    private Properties properties;

//    public ConfigHelper() throws FileNotFoundException {
//        BufferedReader reader;
//        try{
//            reader = new BufferedReader(new FileReader(propertyFilePath));
//            properties = new Properties();
//            try{
//                properties.load(reader);
//                reader.close();
//            } catch(IOException e){
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//            throw new RuntimeException("Configuration file not found at " + propertyFilePath);
//        }
//    }
//
//    public String getBaseUri(){
//        String baseUri = properties.getProperty("baseUri");
//        if(baseUri!=null) return baseUri;
//        else throw new RuntimeException("Base uri not specified in config properties file");
//    }
//
//    public String getBasePath(){
//        String basePath = properties.getProperty("basePath");
//        if(basePath!=null) return basePath;
//        else throw new RuntimeException("Base path not specified in config properties file");
//    }

    public ConfigHelper() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(propertyFilePath));
        properties = new Properties();
        properties.load(reader);
    }

    private Supplier<String> baseUri = () -> {
        String baseResource = properties.getProperty("baseUri");
        return baseResource;
    };

    private Supplier<String> basePath = () -> {
        String pathResource = properties.getProperty("basePath");
        return pathResource;
    };

    public String getBaseUri(){
       return baseUri.get();
    }

    public String getBasePath(){
        return basePath.get();
    }
}
