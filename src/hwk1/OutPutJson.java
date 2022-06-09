package hwk1;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutPutJson {
    public static void toFile(Object json, File file) throws Exception {
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
}
