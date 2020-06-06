import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sweeper.Box;
import Sweeper.Coord;
import Sweeper.Game;
import Sweeper.Ranges;

public class JavaSweeper extends JFrame
{
    private Game game;
    private JPanel panel; //Устанавливаем панель на которой можно рисовать
    private JLabel label;
    private final int COLS = 9; // Столбцы
    private final int ROWS = 9; // Строки
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50; // Размер одной иконки, поля под мины

    public static void main(String[] args) { new JavaSweeper(); } //Создает Экземпляр программы, окно

    private JavaSweeper(){
        game = new Game(COLS,ROWS, BOMBS);
        game.start();
        setImages();
        initLable();
        initPanel(); // Вызываем панель (Это картина, у которой есть та самая рамка)
        initFrame(); //вызвали метод, это рамка без которой не вызывается Панель, нет фрейма ибо

    }

    private void initLable()
    {
        label = new JLabel("Welcome!");
        add (label, BorderLayout.SOUTH);
    }

    private void initPanel()
    { // 2 Шаг
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) //Перебор всех координат Rangers и помещаем туда нужное Изображение
                {
                    g.drawImage((Image) game.getBox(coord).image, // Получили бокс
                            coord.x*IMAGE_SIZE, coord.y*IMAGE_SIZE, this);
                }
               // this для передачи объекта Аргументу, передаем объект Иэдж с именем бомб
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = getX() / IMAGE_SIZE;
                int y = getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton (coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton (coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage ());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*IMAGE_SIZE ,
                Ranges.getSize().y*IMAGE_SIZE));
                                                                    //Задаем размер панели, Столбцы на размер картинки, и ширина на размер картинки
        add(panel);                                                 //добавили панель, Метод унаследован из ДЖфрейм
    }

    private String getMessage()
    {
        switch (game.getState())
        {
            case PLAYED: return "Think twice!";
            case BOMBED: return "YOU LOSE! BIG BA_DA_BOOOM!";
            case WINNER: return "CONGRATULATIONS!";
            default: return "Welcome!";
        }
    }

    private void initFrame(){ // 1 Шаг
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Завершаем Джава приложения при нажатии крестика.
                                                                    // И Джава стопается
        setTitle("Java Sweeper"); //Устанавливаем заголовок на Окошко
        setResizable(false); //Нельзя менять размер окна, такое как есть
        setVisible(true);//чтобы форму было видно
        pack();                     //метод устанавливает такой размер контейнера, который необходим для отображения всех компонентов
        setLocationRelativeTo(null); //Делаем чтобы заоловок был по центру
        setIconImage(getImage("icon")); //Создаем Иконку нашему значку запуска
    }

    private void setImages (){ //устанавливаем картинкию
        for (Box box : Box.values()){
            box.image = getImage(box.name().toLowerCase());// для каждого экземпляра Бокса устанавливаем картинку
        }
    }

    private Image getImage (String name){ //Вносим картинки в проект
        String filename = "img/" + name + ".png"; //Подготовили имя файла для каждой картинки
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // Сделали папку ИМГресурсом и подключили
        return icon.getImage(); //Возращаем картинку, которая необходима

    }

}
