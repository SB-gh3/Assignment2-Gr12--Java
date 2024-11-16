import java.util.*;

class StarryNight //Translated from Rust
{
    public ArrayList<ArrayList<Double>> convert(int row, int col, double[] lighti)
    {
        if (row * col != lighti.length)
        {
            System.out.println("Invalid");
            return null;
        }

        ArrayList<ArrayList<Double>> lightf = new ArrayList<>();

        for (int i = 0; i < col; i++)
        {
            lightf.add(new ArrayList<>());
        }

        for (int i = 0; i < lighti.length; i++)
        {
            if (((i / row) + 1) % 2 != 0)
            {
                lightf.get(i / row).add(lighti[i]);
            }
            else
            {
                lightf.get(i / row).add(0, lighti[i]);
            }
        }

        return lightf;
    }
}

class Appointment //Translated from Go
{
    boolean[] avail;

    Appointment()
    {
        this.avail = new boolean[62];
    
        for (int i = 0; i < this.avail.length; i++)
        {
            this.avail[i] = true;
        }
    }
    
    public boolean reserve(int start, int time)
    {
        if (start + time - 1 > 60)
        {
            return false;
        }
    
        for (int i = start; i < start + time; i++)
        {
            if (!this.avail[i])
            {
                return false;
            }
        }
    
        for (int i = start; i < start + time; i++)
        {
            this.avail[i] = false;
    
            if (i == start + time - 1)
            {
                return true;
            }
        }
    
        return false;
    }
    
    public void book(int start, int time)
    {
        boolean check = this.avail[0];
        int last = 0;
    
        if (this.reserve(start, time))
        {
            System.out.println("\n " + start + " to " + (start + time - 1) + " booked.\n\n---------------------");
        }
        else
        {
            System.out.println("\n " + start + " to " + (start + time - 1) + " not availible.\n\n---------------------");
        }
    
        for (int i = 0; i < this.avail.length; i++)
        {
            if (this.avail[i] != check || i == this.avail.length - 1)
            {
                if (this.avail[i - 1])
                {
                    System.out.println("\nAvailible: " + last + " - " + (i - 1));
                    last = i;
                }
                else
                {
                    System.out.println("\nUnavailible: " + last + " - " + (i - 1));
                    last = i;
                }

                check = this.avail[i];
            }
        }
    }
}
    
public class Assignment3 
{
    static boolean is_prime(int num) //Bespoke
    {
        boolean flag = false;

        // 0 and 1 are not prime numbers
        if (num == 0 || num == 1) 
        {
            flag = true;
        }

        for (int i = 2; i <= num / 2; ++i) 
        {
            // condition for nonprime number
            if (num % i == 0) 
            {
                flag = true;
                break;
            }
        }

        return !flag;
    }

    static ArrayList<Integer> circular() //Translated from Rust
    {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean check;
        String org;
    
        for (int i = 100; i < 1000; i++)
        {
            check = false;
            org = String.valueOf(i);
    
            if (is_prime(i) && !org.contains("0"))
            {
                for (int j = 0; j < 3; j++)
                {
                    org += org.charAt(0);
                    org = org.substring(1, 3);
    
                    if (is_prime(Integer.parseInt(org)))
                    {
                        check = true;
                    }
                    else
                    {
                        check = false;
                        break;
                    }
                }
            }
    
            if (check)
            {
                primes.add(i);
            }
        }
    
        return primes;
    }

    static int filter(int[] arr, int num) //Bespoke
    {
        int count = 0;

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == num)
            {
                count++;
            }
        }

        return count;
    }
    
    static boolean latin(int[][] squares) //Translated from Rust
    {
        ArrayList<Integer> col = new ArrayList<>();
        ArrayList<Integer> squoors = new ArrayList<>();
        int[] column = new int[squares.length];
    
        for (int i = 0; i < squares.length; i++)
        {
            for (int k = 0; k < squares[i].length; k++) 
            {
                col.add(squares[k][i]);
            }
    
            for (int j = 0; j < squares[i].length; j++) //elemj = sqaures[i][j]
            {
                if (filter(squares[0], squares[i][j]) != 1) //First row dupe checker
                {
                    return false;
                }

                for (int boop : squares[i])
                {
                    squoors.add(boop);
                }
    
                if (!squoors.contains(squares[j][i])) //Check row
                {
                    return false;
                }

                for (int l = 0; l < col.size(); l++) 
                {
                    column[l] = col.get(l);
                }

                if (filter(column, squares[i][j]) != 1) //Check column
                {
                    return false;
                }
            }
    
            col.clear();
        }
    
        return true;
    }

    public static int[] loc = new int[2];

    static String scramble(String str) //Translated from Go
    {
        char letr;
        char[] runeArr = str.toCharArray();

        for (int i = 0; i < runeArr.length; i++)
        {
            if (runeArr[i] == 'A' && i < runeArr.length - 1 && runeArr[i + 1] != 'A')
            {
                letr = runeArr[i];
                runeArr[i] = runeArr[i + 1];
                runeArr[i + 1] = letr;
                i++;
            }
        }

        str = new String(runeArr);
        return str;
    }

    static ArrayList<String> scrambleOrRemove(ArrayList<String> slice) //Translated from Go
    {
        ArrayList<String> arr = new ArrayList<>();

        for (int i = 0; i < slice.size(); i++)
        {
            String str = scramble(slice.get(i));

            if (!slice.get(i).equals(str))
            {
                arr.add(str);
            }
        }

        return arr;
    }

    static int getNext(int[][] grid, int row, int col) //Translated from Go
    {
        if (col + 1 >= grid.length)
        {
            loc[0] = loc[0] + 1;
            return grid[row + 1][col];
        }
        else if (row + 1 >= grid[row].length)
        {
            loc[1] = loc[1] + 1;
            return grid[row][col + 1];
        }

        if (grid[row + 1][col] < grid [row][col + 1])
        {
            loc[0] = loc[0] + 1;
            return grid[row + 1][col];
        }
        else
        {
            loc[1] = loc[1] + 1;
            return grid[row][col + 1];
        }
    }

    static int[] getLoc(int[][] grid, int val) //Translated from Go
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] == val)
                {
                    loc[0] = i;
                    loc[1] = j;
                    return loc;
                }
            }
        }

        return null;
    }

    static int sumPath(int[][] grid, int row, int col) //Translated from Go
    {
        int sum = grid[row][col];
        loc = getLoc(grid, grid[row][col]);

        for (int i = 0; (loc[0] + 1) * (loc[1] + 1) < grid.length * grid[row].length; i++)
        {
            sum += getNext(grid, loc[0], loc[1]);
        }

        return sum;
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Circular Primes\n2. Latin Squares\n3. Night Sky\n4. Word Scramble\n5. Array Locations\n6. Appointment Booking");
        int choice = sc.nextInt();
        int[][] vec = {{10, 30, 20, 0}, {0, 20, 30, 10}, {30, 0, 10, 20}, {20, 10, 0, 30}};
        //let vec = vec![vec![1, 2], vec![1, 2]];
        double[] light = {0.3, 0.7, 0.8, 0.4, 1.4, 1.1, 0.2, 0.5, 0.1, 1.6, 0.6, 0.9};
        StarryNight star = new StarryNight();
    
        switch (choice)
        {
            case 1: 
                System.out.println(circular());
                break;

            case 2:
                System.out.println(Arrays.deepToString(vec) + ": " + latin(vec));
                break;

            case 3:
                System.out.println(star.convert(3, 4, light));
                break;

            case 4:
                String word = "";
                ArrayList<String> slice = new ArrayList<>();

                while (!"0".equals(word))
                {
                    System.out.println("Enter word to scramble (capital letters only, 0 to stop)");
                    word = sc.next();
                    slice.add(word);
                }

                System.out.println(scrambleOrRemove(slice));
                break;

            case 5:
                int[][] grid = {{12, 30, 40, 25, 5}, {11, 3, 22, 15, 43}, {7, 2, 9, 20, 0}, {10, 17, 4, 19, 9}, {8, 33, 18, 6, 1}};
                System.out.println(sumPath(grid, 1, 1));
                break;

            case 6:
                Appointment app = new Appointment();
                app.book(30,12);
                app.book(10, 5);
                app.book(40,20);
                break;

            default:
                System.out.println("ERROR");
                break;
        }
    }
}