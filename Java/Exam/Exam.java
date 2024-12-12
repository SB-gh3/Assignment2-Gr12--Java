import java.util.*;

class Stringcheck
{
    static void containsE(String[] arr)
    {
        ArrayList<String> lis = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            if (arr[i].contains("e"))
            {
                lis.add(arr[i]);
            }
        }

        System.out.println(lis);
    }
    
    static void greaterThan5(String[] arr)
    {
        ArrayList<String> lis = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            if (arr[i].length() > 5)
            {
                lis.add(arr[i]);
            }
        }

        System.out.println(lis);
    }

    static void replaceFirst(String[] arr)
    {
        ArrayList<String> lis = new ArrayList<>();
        String str;

        for (int i = 0; i < arr.length; ++i) 
        {
            str = "*";
            str += arr[i].substring(1,arr[i].length());
            lis.add(str);
        }

        System.out.println(lis);
    } 
}

public class Exam 
{
    static ArrayList<Integer> limiter(int[] arr, int num)
    {
        ArrayList<Integer> lis = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            if (Math.abs(arr[i]) <= num)
            {
                lis.add(arr[i]);
            }
            else
            {
                if (Math.abs(arr[i]) != arr[i])
                {
                    lis.add(-num);
                }
                else
                {
                    lis.add(num);
                }
            }
        }

        return lis;
    }

    static ArrayList<Integer> removeLeadingZeroes(int[] arr)
    {
        ArrayList<Integer> lis = new ArrayList<>();
        boolean check = false;

        for (int i = 0; i < arr.length; ++i) 
        {
            if (check)
            {
                lis.add(arr[i]);
            }
            else if (arr[i] != 0)
            {
                lis.add(arr[i]);
                check = true;
            }
        }

        return lis;
    }

    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Limiter\n2. Remove leading zeros\n3. String check");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1 ->
            {
                int[] arr = {0, 0, 45, -25, 0, -120, 10000, 0};
                ArrayList<Integer> arrs = limiter(arr, 40);
                ArrayList<Integer> arrso = limiter(arr, 100);
                System.out.println(arrs);
                System.out.println(arrso);
            }

            case 2 ->
            {
                int[] arr = {0, 0, 45, -25, 0, -120, 10000, 0};
                System.out.println(removeLeadingZeroes(arr));
            }

            case 3 ->
            {
                Stringcheck check = new Stringcheck();
                String[] arr = {"sine", "cosine", "tangent", "degree", "radian", "angle"};
                check.containsE(arr);
                check.greaterThan5(arr);
                check.replaceFirst(arr);
            }
        }
    }
}