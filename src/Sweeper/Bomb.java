package Sweeper;

class Bomb
    {
        private Matrix bombMap;
        private int totalBombs;

        Bomb (int totalBombs)
        {
            this.totalBombs = totalBombs;
            fixBombsCount();
        }

        void start ()
        {
            bombMap = new Matrix(Box.ZERO);
            for (int j = 0; j<totalBombs; j++)
                placeBomb ();
        }

        Box get (Coord coord)
        {
            return bombMap.get(coord);
        }

        private void fixBombsCount ()
        {
            int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
            if (totalBombs > maxBombs)
                totalBombs = maxBombs;
        }

        private void placeBomb () {
            while (true)
            {
                Coord coord = Ranges.getRandomCoord();
                if (Box.BOMB == bombMap.get(coord)) // Если в координате есть бомба, то пропускаем, пока не найдет где она нужна
                    continue;
                bombMap.set(coord, Box.BOMB); //Одна бомба в случайном месте
                incNumbersAroundBomb(coord);
                break;
            }



        }
        private void incNumbersAroundBomb (Coord coord)
        {
            for (Coord around : Ranges.getCoordsArround(coord))
                if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
        }

        int getTotalBombs()
        {
        return totalBombs;
        }
    }
