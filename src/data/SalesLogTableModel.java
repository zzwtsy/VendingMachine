package data;

import util.Const;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Vector;

public class SalesLogTableModel extends AbstractTableModel {
    protected final Vector<String> tableTitle;
    protected final Vector<Log> tableData;

    public SalesLogTableModel() {
        this.tableTitle = new Vector<>();
        this.tableTitle.add("日期");
        this.tableTitle.add("操作人");
        this.tableTitle.add("操作");
        this.tableTitle.add("结果");
        tableData = new Vector<>(Const.PAGE.getData());
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return tableTitle.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Log log = tableData.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> {
                LocalDateTime ld = ZonedDateTime.parse(log.getDateTime()).toLocalDateTime();
                yield ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            case 1 -> log.getOperator();
            case 2 -> log.getAction();
            case 3 -> log.getResult();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return tableTitle.get(column);
    }

    /**
     * 刷新表格数据
     */
    public void refresh() {
        tableData.clear();
        Page.updatePage();
        tableData.addAll(Const.PAGE.getData());
    }
}
