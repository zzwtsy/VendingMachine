package data;

import util.Const;
import util.JsonUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page {
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页显示的条数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总条数
     */
    private int totalLine;

    private List<Log> data;

    public Page() {

    }

    public Page(int currentPage, List<Log> data, int pageSize, int totalLine, int totalPage) {
        this.currentPage = currentPage;
        this.data = data;
        this.pageSize = pageSize;
        this.totalLine = totalLine;
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Page setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalLine() {
        return totalLine;
    }

    public Page setTotalLine(int totalLine) {
        this.totalLine = totalLine;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<Log> getData() {
        return data;
    }

    public Page setData(List<Log> data) {
        this.data = data;
        return this;
    }

    public Page setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }


    public static void updatePage() {
        // 计算开始行
        int start = (Const.PAGE.getCurrentPage() - 1) * Const.PAGE.getPageSize();
        // 计算结束行
        int end = start + Const.PAGE.getPageSize();
        // 创建一个空的日志列表
        List<Log> logs = new ArrayList<>();
        int lineCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Log.LOG_PATH))) {
            // 读取文件中的每一行
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // 如果当前行在指定范围内，则将其添加到日志列表中
                if (lineCount >= start && lineCount <= end) {
                    logs.add(JsonUtil.decodeFromString(line, Log.class));
                }
                lineCount++;
            }
        } catch (IOException e) {
            // 如果发生IO异常，则抛出运行时异常
            throw new RuntimeException(e);
        }
        int pageSize = Const.PAGE.getPageSize();
        if (!List.of(20, 50, 100, 200).contains(pageSize)) {
            pageSize = 20;
        }
        // 计算总页数
        int totalPage = (int) Math.ceil((double) lineCount / pageSize);
        // 计算当前页
        int currentPage = (int) Math.ceil((double) (start + 1) / pageSize);
        Const.PAGE = new Page().setData(logs).setTotalPage(totalPage).setTotalLine(lineCount).setCurrentPage(currentPage).setPageSize(pageSize);
    }

}
