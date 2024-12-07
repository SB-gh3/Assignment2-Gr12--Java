import java.util.*;

class StepTracker //Translated from Rust
{
    int check_active;
    int days_active;
    int total_steps;
    int total_days;

    StepTracker(int active)
    {
        this.check_active = active;
        this.days_active = 0;
        this.total_steps = 0;
        this.total_days = 1;
    }

    public void add_daily_steps(int steps)
    {
        if (this.total_steps != 0)
        {
            this.total_days += 1;
        }

        this.total_steps += steps;

        if (steps >= this.check_active)
        {
            this.days_active += 1;
        }
    }

    public int active_days()
    {
        return this.days_active;
    }

    public double average_steps()
    {
        return this.total_steps / this.total_days;
    }
}

class Phrase //Translated from Rust
{
    String stri;

    Phrase(String phrase)
    {
        this.stri = phrase;
    }

    public int find_nth(String val, int n)
    {
        int count = 0;

        for (int i = 0; i < stri.length(); ++i)
        {
            if (i + val.length() - 1 < this.stri.length() && this.stri.substring(i, i + val.length()).equals(val))
            {
                count += 1;

                if (count == n)
                {
                    return i;
                }
            }
        }

        return -1;
    }

    public int find_last(String val)
    {
        int num = -1;

        for (int i = 0; i < stri.length(); ++i)
        {
            if (i + val.length() - 1 < this.stri.length() && this.stri.substring(i, i + val.length()).equals(val))
            {
                num = i;
            }
        }

        return num;
    }

    public void replace_nth(String val, int n, String repl)
    {
        int count = 0;
        StringBuilder buf = new StringBuilder(this.stri);

        for (int i = 0; i < stri.length(); ++i)
        {
            if (i + val.length() - 1 < this.stri.length() && this.stri.substring(i, i + val.length()).equals(val))
            {
                count += 1;

                if (count == n)
                {
                    buf.replace(i, (i + val.length()), repl);
                    stri = buf.toString();
                    return;
                }
            }
        }
    }
}

class StringChecker //Translated from Go
{
    String str;
    int min;
    int max;

    StringChecker(String stri) 
    {
        this.str = stri;
        this.min = 6;
        this.max = 20;
    }

    StringChecker(String stri, int minh, int mahx)
    {
        this.str = stri;
        this.min = minh;
        this.max = mahx;
    }

    public boolean isValid(String in)
    {
        if (in.length() < this.min || in.length() > this.max)
        {
            return false;
        }
    
        if (in.contains(this.str))
        {
            return false;
        }
    
        return true;
    }
}

class StudyPractice //Translated from Go
{
    int fir;
    int sec;

    StudyPractice(int firl, int secs)
    {
        this.fir = firl;
        this.sec = secs;
    }

    public void getProblem()
    {
        System.out.println(this.fir + " times " + this.sec);
    }
    
    public void nextProblem()
    {
        this.sec++;
    }
}

public class Assignment4 
{
    static boolean light_board(int num_row, int num_col, int row, int col) //Translated from Rust
    {
        Random rand = new Random();
        ArrayList<ArrayList<Boolean>> board = new ArrayList<>();

        for (int i = 0; i < num_row; ++i)
        {
            board.add(new ArrayList<>());

            for (int j = 0; j < num_col; ++i)
            {
                board.get(i).add(false);
            }
        }

        ArrayList<Boolean> column = new ArrayList<>();

        for (int i = 0; i < board.size(); ++i)
        {
            for (int j = 0; j < board.get(i).size(); ++j)
            {
                if (rand.nextInt(10) < 4)
                {
                    board.get(i).set(j, true);
                }
            }

            column.add(board.get(i).get(col)); //Unsafe?
        }

        if (board.get(row).get(col))
        {
            return column.stream().filter(p -> p == true).count() % 2 != 0;
        }
        else
        {
            return column.stream().filter(p -> p == true).count() % 3 == 0;
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Light Board\n2. Step Tracker\n3. Phrases\n4. String Checker\n5. Study Practice");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1 -> System.out.println(light_board(10, 10, 5, 3));

            case 2 -> 
            {
                StepTracker track = new StepTracker(10000);
                track.add_daily_steps(1023);
                track.add_daily_steps(10000);
                track.add_daily_steps(15000);
                System.out.println(track.active_days());
                System.out.println(track.average_steps());
            }

            case 3 -> 
            {
                Phrase pl = new Phrase("A cat ate late");
                System.out.println(pl.find_nth("at", 2));
                System.out.println(pl.find_last("at"));
                pl.replace_nth("at", 1, "rane");
                System.out.println(pl.stri);
            }

            case 4 ->
            {
                StringChecker check = new StringChecker("pass", 5, 8);
                System.out.println(check.isValid("password") + "\n" + check.isValid("grapefruit"));
                StringChecker checko = new StringChecker("glub");
                System.out.println(checko.isValid("flubglub") + "\n" + checko.isValid("hello"));
            }

            case 5 ->
            {
                StudyPractice study = new StudyPractice(7, 3);
                study.getProblem();
                study.nextProblem();
                study.nextProblem();
                study.getProblem();
            }

            default -> System.out.println("ERROR");
        }
    }
}