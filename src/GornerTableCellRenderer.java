import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private String needle = null;
    private Double needleStart = null;
    private Double needleEnd = null;

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        // Устанавливаем базовый цвет
        panel.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);

        // Проверяем значение во втором столбце (col == 1)
        if (needle != null) {
            String valueInSecondColumn = formatter.format(table.getValueAt(row, 1));
            if (needle.equals(valueInSecondColumn)) {
                // Если найдено совпадение во втором столбце, красим всю строку
                panel.setBackground(Color.RED);
            }
        }

        if (needleStart != null && needleEnd != null) {
            if (Double.parseDouble(formattedDouble) >= needleStart && Double.parseDouble(formattedDouble) <= needleEnd) {
                panel.setBackground(Color.GREEN);
            }
        }
        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }

    public void setNeedleStart(Double needleStart) {
        this.needleStart = needleStart;
    }

    public void setNeedleEnd(Double needleEnd) {
        this.needleEnd = needleEnd;
    }
}
