package data;

import util.JsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * 销售日志
 */
public class Log {
    public static final String LOG_PATH = "./log.json";
    private static final File FILE = new File(LOG_PATH);
    /**
     * 日志时间
     */
    private String dateTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作
     */
    private String action;
    /**
     * 操作结果
     */
    private String result;

    public Log() {
    }

    public Log(String action, String dateTime, String operator, String result) {
        this.action = action;
        this.dateTime = dateTime;
        this.operator = operator;
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public Log setAction(String action) {
        this.action = action;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Log setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public Log setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Log setResult(String result) {
        this.result = result;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return result == log.result && Objects.equals(dateTime, log.dateTime) && Objects.equals(operator, log.operator) && Objects.equals(action, log.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, operator, action, result);
    }

    /**
     * 初始化日志
     */
    public static void initLog() {
        // 判断是否存在 log.json 文件
        if (!FILE.exists()) {
            // 不存在则创建
            try {
                boolean newFile = FILE.createNewFile();
                if (newFile) {
                    System.out.println("日志文件创建成功");
                } else {
                    System.out.println("日志文件创建失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 保存日志
     */
    public static void save(Log log) {
        String json = JsonUtil.encodeToString(log);
        // 追加到 log.json 文件中
        try (FileWriter fileWriter = new FileWriter(FILE, true)) {
            fileWriter.write(json + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Page.updatePage();
    }
}

