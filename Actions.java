//Вводим необходимые библиотеки
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//начинаем класс Действия, описывающего операции, совершаемые при нажатии на кнопки
public class Actions {
    //numbers
    static ActionListener newNumAction(String num) {
        return new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String old=Calculate.Text.getText();
                if (old.length()<9
                        || (old.startsWith("-") || old.contains("."))&& old.length()<10
                        || old.startsWith("-") && old.contains(".") && old.length()<10) {
                    if (Objects.equals(old, "0")) {
                        Calculate.Text.setText(num);
                    }
                    else {
                        Calculate.Text.setText(old+num);
                    }
                }
            }
        };
    }
    //simple actions
    static ActionListener newSimpleAction(Action act,String symbol) {
        return new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Calculate.action==Action.None) {
                    String old = Calculate.Text.getText();
                    Calculate.first=Double.parseDouble(old);
                }
                else {
                    String old = Calculate.Text.getText();
                    Calculate.second=Double.parseDouble(old);
                    Calculate.equal();
                }
                Calculate.action=act;
                Calculate.old_action=false;
//Calculate.row_action++; 
                Calculate.Text.setText(null);
                Calculate.upperText.setText(Calculate.first+" "+symbol);
            }
        };
    } //при нажатии на кнопки с цифрами они будут введены в строку ввода
    static ActionListener[] actions={
            newNumAction("7"),
            newNumAction("8"),
            newNumAction("9"),
            //действие для кнопки * (операция умножение - Multiply)
            newSimpleAction(Action.Mul,"*"),
//del 
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Calculate.comma=false;
                    Calculate.upperText.setText(null);
                    Calculate.Text.setText("0");
                    Calculate.action=Action.None;
                }
            },
            newNumAction("4"),
            newNumAction("5"),
            newNumAction("6"),
            //описание действия на кнопку / (операция деления - divide)
            newSimpleAction(Action.Div,"/"),
//описание операции при нажатии на кнопку = (равенство)
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String old = Calculate.Text.getText();
                    if (!old.isEmpty() && !Calculate.old_action) {
                        Calculate.second=Double.parseDouble(old);
                        Calculate.old_action=true;

                    }
                    Calculate.equal();
                }
            },
            newNumAction("1"),
            newNumAction("2"),
            newNumAction("3"),
            newSimpleAction(Action.Add,"+"),
//Расчет имт для женщин
            newSimpleAction(Action.IMT,"ИМТ-Ж"),
//0 
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String old=Calculate.Text.getText();
                    if (!Objects.equals(old,"0")){
                        Calculate.Text.setText(old+"0");
                    }
                }
            },
//действие для кнопки . (разделение целой и дробной части)
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String old = Calculate.Text.getText();
                    if (!Calculate.comma){
                        Calculate.Text.setText(old+".");
                        Calculate.comma=true;
                    }
                }
            },
//действие для кнопки -/+ (смена знака)
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String old= Calculate.Text.getText();
                    if (!Objects.equals(old, "0") && !Objects.equals(old, "0.0")) {
                        if (Objects.equals(old.substring(0,1), "-") ) {
                            Calculate.Text.setText(old.substring(1));
                        }
                        else{
                            Calculate.Text.setText("-"+old);
                        }
                    }
                }
            },
            newSimpleAction(Action.Sub,"-"),
//расчет логарифма имт для мужчин
            newSimpleAction(Action.IMT,"ИМТ-М"),
            }
    ;
}