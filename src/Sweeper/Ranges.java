package Sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{


    private static Coord size; // Размер игрового поля
    private static ArrayList<Coord> allCoords; // Лист с кучей координат, ячейки
    private static Random random = new Random();

    public static void setSize (Coord _size)
    {
        size = _size; //Присвоили сайзу переданное значение КОЛС РОВС
        allCoords = new ArrayList<Coord>(); //Создали Лист с Объектами содержащими Координаты X,Y
        for (int y = 0; y < size.y; y++) //Заполняем объект, сперва с 0 координаты y, переберая X
            for (int x=0; x < size.x; x++)
                allCoords.add(new Coord(x,y));
    }
    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords () //Создаем возможность получить Коодинаты из нашего Приват Листа.
    {
        return allCoords;
    }

    static boolean inRange (Coord coord)
    {
         return coord.x >= 0 && coord.x < size.x &&
                 coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord ()
    {
        return new Coord(random.nextInt(size.x),
                         random.nextInt(size.y));
    }
    static ArrayList<Coord> getCoordsArround (Coord coord)
    {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x,y)))
                    if (!around.equals(coord))
                        list.add(around);

        return list;
    }

}
