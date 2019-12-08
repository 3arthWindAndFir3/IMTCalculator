//Вводим необходимые для работы библиотеки
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.*;
//вводим операции + * / -
enum Action{
    Add,
    Mul,
    Div,
    Sub,
    None,
    IMT
}
//Начало класса Calculate, описывающего содержимое окна
public class Calculate extends JFrame{
    private static final long serialVersionUID = 1L;
    /*first и second переменные, над которыми проводятся операции, по умолчанию их значение 0,
    а уже по ходу программы оно меняется на вводимое калькулятором */
    static double first=0;
    static double second=0;
    static Action action=Action.None;
    //преобразование содержимого строки в значение
    static JLabel upperText=new JLabel();
    static JTextField Text=new JTextField("0");
    //строка, отвечающая за время
    static JLabel timelabel=new JLabel();
    static JPanel panel = new JPanel();
    //задаем массив кнопок
    static JButton[] buttons= new JButton[20];
    //задание кнопки стирания
    static boolean comma=false;
    static boolean old_action=false;
    boolean running=true;
    boolean timeView=false;
    Thread timeThread=new Thread(new Background());
    static int row_action=0;

    //описание содержимого кнопок в массиве
    String[] textButton = {
            "7", "8", "9","x", "C",
            "4", "5", "6", "/","=",
            "1", "2", "3", "+", "ИМТ-Ж",
            "0", "." ,"-/+", "-","ИМТ-М",
    };
    
    //описание операций над числами
    public static void equal() {
        double num=0;
        switch(action) {
            case Add:
                num = first + second;
                break;
            case Mul:
                num = first * second;
                break;
            case Div:
                num = first / second;
                break;
            case Sub:
                num = first - second;
                break;
            case None:
                break;
            case IMT: num=first/(second*second)*10000; break;
        }
        //получение чисел из строчки ввода
        String new_str = String.valueOf(num);
        Text.setText(new_str);
        Calculate.upperText.setText(null);
        Calculate.first=num;
    }

    //Метод окна калькулятора
    void calculatePage() {

//задаем его панель
        panel = new JPanel();
        panel.setLayout(null);
/*задание месторасположения, размера, изначальной невидимости панели,
в которой будет указана текущие дата и время, в окне калькулятора */
        timelabel.setLocation(230,200);
        timelabel.setSize(200,30);
        timelabel.setVisible(false);
        setVisible(true);
        panel.add(timelabel);
//добавляем панель меню с различными настройками
        JMenuBar menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("Параметры и расчет ИМТ");

//Добавляем в меню параметры, отвечающие за время и цвет панели калькулятора, а также интерпретацию ИМТ
          
        JMenuItem time= new JMenuItem("Отобразить время");
        time.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//вызов метода, который покажет текущую дату и время, а при повторном нажатии уберет их
                timeView=!timeView;
                if (timeView) {
                    timelabel.setVisible(true);
                }
                else {
                    timelabel.setVisible(false);
                }
            }
        });
        newMenu.add(time);
        
      //вызов метода, который выведет сообщение, содержащее информации об ИМТ и его интерпретацию для девушек
        JMenuItem raschet2= new JMenuItem("Интерпритация ИМТ-Ж");
        raschet2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Calculate.first<15) 
            	{
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Дефицит массы тела!");
                                                              }
                }
            	else if (15<=(Calculate.first) && (Calculate.first)<19.5) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Вес в пределах нормы");}}
            		
            	else if (19.5<=(Calculate.first) && (Calculate.first)<=23.9) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Избыточная масса тела");}}
            	
            	else if (23.9<(Calculate.first) && (Calculate.first)<=26.5) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 1 степени!");}}
            	
            	else if (26.5<(Calculate.first) && (Calculate.first)<=31.5) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 2 степени!!");}}
            	
            	else if (31.5<(Calculate.first) && (Calculate.first)<=36.5) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 3 степени!!!");}}
            	
            	else {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 4 степени!!!!");
                                                              }
                }
            	}
            }
        );
        newMenu.add (raschet2);
        
      //вызов метода, который выведет сообщение, содержащее информации об ИМТ и его интерпретацию для мужчин
        JMenuItem raschet= new JMenuItem("Интерпритация ИМТ-М");
        raschet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (Calculate.first<18.5) 
            	{
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Дефицит массы тела!");
                                                              }
                }
            	else if (18.5<=(Calculate.first) && (Calculate.first)<23) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Вес в пределах нормы");}}
            		
            	else if (23<=(Calculate.first) && (Calculate.first)<=27.4) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Избыточная масса тела");}}
            	
            	else if (27.4<(Calculate.first) && (Calculate.first)<=29.9) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 1 степени!");}}
            	
            	else if (29.9<(Calculate.first) && (Calculate.first)<=34.9) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 2 степени!!");}}
            	
            	else if (34.9<(Calculate.first) && (Calculate.first)<=40) {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 3 степени!!!");}}
            	
            	else {
            		DecimalFormat df = new DecimalFormat("###.##");
            		{JOptionPane.showMessageDialog(null, df.format(Calculate.first)  + " - " + "Ожирение 4 степени!!!!");
                                                              }
                }
            	}
            }
        );
        newMenu.add(raschet);
        

//задаем массив цветов и массив их названий для выбора в меню
        Color[] color= {Color.red,Color.orange,Color.yellow,Color.green,Color.cyan,Color.blue,Color.magenta};
        String[] colorName= {"Красный","Оранжевый","Желтый","Зеленый","Голубой","Синий","Фиолетовый"};
        JMenu Color = new JMenu("Цвет");
//цикл смены цвета панели путем выбора цвета из массива, который соответствует своему названию
        for (int i=0;i<7;i++) {
            Color col=color[i];
            JMenuItem colour = new JMenuItem(colorName[i]);
            colour.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    panel.setBackground(col);
                }
            });
            Color.add(colour);
        }
        newMenu.add(Color);
//добавление в настройки кнопки, отвечающей за выход 
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(EXIT_ON_CLOSE);
            }
        });
        newMenu.add(exitItem);

        //отвечает за отображение кнопки Параметры и Интерпретация ИМТ
        menuBar.add(newMenu);
        setJMenuBar(menuBar);

        /* строка поверх строки текущего ввода, в которой
        будет указано 1 введенное число при умножении, сложении, делении, вычитании */
        upperText.setSize(100,30);
        upperText.setLocation(270,20);
        upperText.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(upperText);

        //размер строки, в которой будет отражаться текущий ввод и результат вычислений
        Text.setEditable(false);
        Text.setSize(360, 20);
        Text.setLocation(80,60);
        Text.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(Text);
        //кнопка <, которая стирает последний введенный элемент, ее параметры и метод, отвечающий за работу
        JButton cl_one=new JButton("<");
        cl_one.setSize(90,20);
        cl_one.setLocation(440,60);
        cl_one.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String old=Calculate.Text.getText();
                        if (old.endsWith(".")) {
                            Calculate.comma=false;
                        }
                        Calculate.Text.setText(old.substring(0,old.length()-1));
                    }
                });
        panel.add(cl_one);
        //параметры кнопок для всех цифр и операций (в количестве 20 штук),
        // их вывод на экран панели посредством цикла
        int y=80;
        int dy=20;
        int x;
        int dx=90;
        for (int i=0;i<4;i++){
            x=80;
            for (int j=0;j<5;j++){
                buttons[5*i+j]=new JButton(textButton[5*i+j]);
                buttons[5*i+j].setSize(dx,dy);
                buttons[5*i+j].setLocation(x,y);
                buttons[5*i+j].addActionListener(Actions.actions[5*i+j]);
                panel.add(buttons[5*i+j]);
                x+=dx;
            }
            y+=dy;
        }
        setContentPane(panel);
        setVisible(true);
    }
    
    Calculate(){
    	//надпись вверху окна
        super("Калькулятор индекса массы тела");
        //задание кнопок окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //размер окна
        setSize(620, 300);
        setContentPane(panel);
        setVisible(true);
        calculatePage();
    }
    public class Background extends Thread{
        long time;
        //описание метода, отвечающего за дату и время
        public void run() {
            while (running){
                try {
                    sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Date date = new Date();
                timelabel.setText(String.valueOf(date));
            }
        }
    }
    //Наш главный метод - выводит результат всех методов
    public static void main(String[] args) throws IOException {
        Calculate w = new Calculate();
        w.timeThread.start();
    }
}