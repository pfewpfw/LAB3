import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private Boolean[] exactValues;
    private Double[] values;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        int size = (int) ((to - from) / step) + 1;
        this.values = new Double[size];
        this.exactValues = new Boolean[size];
        this.setCoefficients(coefficients);
        calculateValues(size);
        calculateExactValues();
    }
    private double calculatePolynomial(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, coefficients.length - 1 - i);
        }
        return result;
    }
    private void calculateValues(int size) {
        for (int i = 0; i < size; i++) {
            double x = from + step * i;
            values[i] = calculatePolynomial(x);
        }
    }
    private void calculateExactValues() {
        for (int i = 0; i < values.length; i++) {
            exactValues[i] = values[i] == Math.floor(values[i]);
        }
    }
    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() { // Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }
    private void calculateExactVaмlues() {
        for (int i = 0; i < values.length; i++) {
            exactValues[i] = values[i] == Math.floor(values[i]);
        }
    }
    public Object getValueAt(int row, int col) {
        double x = from + step * row; // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ

        Double result = 0.0;
        for (int i = coefficients.length - 1; i >= 0; i--) {
            result = result * x + coefficients[i];
        }

        switch(col) {
            case 0:
                return x;
            case 1:
                return result;
            case 2:
                return exactValues[row]; // Точные значения?
        }
        return 0;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X"; // Название 1-го столбца
            case 1:
                return "Значение многочлена"; // Название 2-го столбца
            case 2:
                return "Точные значения";
        }
        return null;
    }

    public Class<?> getColumnClass(int col) {
        switch(col) {
            case 0:
                return Double.class;
            case 1:
                return Double.class;
            case 2:
                return Boolean.class;
        }
        return null;
    }

    public Double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Double[] coefficients) {
        this.coefficients = coefficients;
    }
}
