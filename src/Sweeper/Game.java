package Sweeper;

    public class Game
    {

       private Bomb bomb;
       private Flag flag;
       private GameState state;

       public GameState getState() {
            return state;
        }

      public Game (int cols, int rows, int bombs) // Пока нет бомб только столбцы и строки
        {
        Ranges.setSize(new Coord(cols,rows)); //Запустим класс Ранджес
            bomb = new Bomb(bombs);
            flag = new Flag();
        }

        public void start() // Запуск Игры?
        {
          bomb.start();
          flag.start();
          state =  GameState.PLAYED; //Создаем сотояние мы играем
        }

    public Box getBox (Coord coord)

    {
        if (flag.get(coord) == Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord); // Возвращаем то, что у нас в выше указанных координатах
    }

        public void pressLeftButton(Coord coord)
        {
            if (gameOver()) return;
           openBox (coord);
           checkWinner(); // А не победили ли мы?
        }

        private void checkWinner ()
        {
            if (state == GameState.PLAYED)
                if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                    state = GameState.WINNER;
        }

        private void openBox (Coord coord)
        {
            switch (flag.get(coord))
            {
                case OPENED: setOpenedToCloseBoxesAroundNumber(coord); return;
                case FLAGED: return;
                case CLOSED:
                    switch (bomb.get(coord))
                    {
                        case ZERO: openBoxesAround (coord); return;
                        case BOMB: openBombs(coord); return;
                        default: flag.setFlagedToBox(coord); return;
                    }
            }
        }

        private void setOpenedToCloseBoxesAroundNumber(Coord coord)
        {
            if (bomb.get(coord) != Box.BOMB)
                if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                    for (Coord around : Ranges.getCoordsArround(coord))
                        if (flag.get(around) == Box.CLOSED)
                            openBox(around);
        }

        private void openBombs(Coord bombed)
        {
            state = GameState.BOMBED;
            flag.setBombedToBox (bombed);
            for (Coord coord : Ranges.getAllCoords())
                if (bomb.get(coord) == Box.BOMB)
                    flag.setOpenedOfClosedBox(coord);
                else
                    flag.setNobombToFlagedSafeBox (coord);
        }

        private void openBoxesAround(Coord coord)
        {
            flag.setFlagedToBox(coord);
            for (Coord around : Ranges.getCoordsArround(coord))
                openBox(around);
        }

        public void pressRightButton(Coord coord)
        {
            if (gameOver()) return;
            flag.togleFlagedToBox(coord);
        }

        private boolean gameOver()
        {
            if (state == GameState.PLAYED)
                return false;
            start();
            return true;
        }
    }
