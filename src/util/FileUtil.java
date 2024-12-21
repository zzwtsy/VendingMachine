package util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    /**
     * 从文件中读取内容
     *
     * @param filePath 文件路径
     * @return String
     * @throws IOException 如果读取文件时发生错误
     */
    public static String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * 保存文件
     *
     * @param filePath 文件路径
     * @param content  文件内容
     */
    public static boolean saveFile(String filePath, String content) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
