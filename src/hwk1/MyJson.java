package hwk1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyJson {
    public static void writeJson(Object json, File file) throws Exception {
        String jsonstr;
        if (json instanceof JSONObject) {
            jsonstr = ((JSONObject) json).toString(2);
        } else if (json instanceof JSONArray) {
            jsonstr = ((JSONArray) json).toString(2);
        } else {
            throw new Exception("必须是org.json.JSONObject或org.json.JSONArray");
        }
        //存入文件
        OutputStream outputStream = new FileOutputStream(file, true);
        byte[] data = jsonstr.getBytes(StandardCharsets.UTF_8);
        outputStream.write(data);
        outputStream.close();
    }

    public static Object readJson(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            int fileSize = (int) file.length();
            byte[] data = new byte[fileSize];
            int n = inputStream.read(data);
            int offset = 0;
            String jsonstr = new String(data, offset, n - offset);
            char firstChar;
            for (int i = 0; i < jsonstr.length(); i++) {
                firstChar = jsonstr.charAt(i);
                if (firstChar != ' ' && firstChar != '\t' && firstChar != '\n' && firstChar != '\r') {
                    break;
                }
            }
            if (jsonstr.startsWith("{")) {
                return new JSONObject(jsonstr);
            } else if (jsonstr.startsWith("[")) {
                return new JSONArray(jsonstr);
            } else {
                throw new Exception("JSON必须以 { 或 [ 开头");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
