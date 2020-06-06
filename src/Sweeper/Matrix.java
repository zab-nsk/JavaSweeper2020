package Sweeper;

class Matrix
{
    private Box [] [] matrix; // Создали Массив

    Matrix (Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y]; // Указали размеры, по классике
        for (Coord coord : Ranges.getAllCoords())
        {
            matrix [coord.x][coord.y] =defaultBox;
        }
    }

    Box get (Coord coord) // Бокс возращаемый тип, и у нас тут все не паблик, по этому тупо Бокс
    {
        if (Ranges.inRange (coord))
            return matrix [coord.x][coord.y];
        return null;
    }

    void set (Coord coord, Box box) //т.к. разные имена переменных, this не нужен
    {
        if (Ranges.inRange (coord))
        matrix [coord.x][coord.y] = box;  // Наша переменная Корд присваеват значения Бокса. По старинке.
    }

}
