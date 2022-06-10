package hwk1;

import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyJson {
    public static void writeJson(Object json, File file) throws Exception {
        String jsonstr;
        if (json instanceof JSONObject) {
            jsonstr = ((JSONObject) json).toString(2);
        } else {
            throw new Exception("必须是org.json.JSONObject或org.json.JSONArray");
        }
        //存入文件
        OutputStream outputStream = new FileOutputStream(file);
        byte[] data = jsonstr.getBytes(StandardCharsets.UTF_8);
        outputStream.write(data);
        outputStream.close();
    }

    public static String readJson(String jsonPath) {
        File jsonFile = new File(jsonPath);
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(jsonFile);
            reader = new BufferedReader(fileReader);
            StringBuilder tempData = new StringBuilder();
            while (true) {
                int ch = reader.read();
                if (ch != -1) {
                    tempData.append((char) ch);
                } else {
                    break;
                }
            }
            return tempData.toString();
        } catch (IOException e) {
            return "error";
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
