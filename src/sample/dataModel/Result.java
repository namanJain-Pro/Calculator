package sample.dataModel;

import javafx.beans.property.SimpleStringProperty;

public class Result {
    private SimpleStringProperty expression;
    private SimpleStringProperty result;

    public Result(String expression, String result) {
        this.expression = new SimpleStringProperty(expression);
        this.result = new SimpleStringProperty(result);
    }

    public String getExpression() {
        return expression.get();
    }

    public SimpleStringProperty expressionProperty() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression.set(expression);
    }

    public String getResult() {
        return result.get();
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }
}
