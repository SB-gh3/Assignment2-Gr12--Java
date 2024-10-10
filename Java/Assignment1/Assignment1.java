import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.text.Collator;

import javax.swing.JOptionPane;

class Student
{
    String age;
    String name;
    int mark;
    char grade;

    Student (String age, String name)
    {
        this.age = age;
        this.name = name;
        System.out.println("Name: " + name + "\tAge: " + age);
    }

    Student (String name, int mark, char grade)
    {
        this.name = name;
        this.mark = mark;
        this.grade = grade;
    }
}

class sortByName implements Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2) 
    {
        return o1.name.compareTo(o2.name);
    }
}

class sortByMgark implements Comparator<Student>
{
    @Override
    public int compare(Student o1, Student o2) 
    {
        if (o1.grade - o2.grade == 0)
        {
            return o2.grade - o1.grade;
        }
        else
        {
            return o2.mark - o1.mark;
        }
    }
    
}

public class Assignment1 
{
    static List<String> one (List<String> arrList)
    {
        arrList = arrList.reversed();

        return arrList;
    }

    static List<String> two (List<String> arrList, int index1, int index2)
    {
        String temp = arrList.get(index1);
        arrList.remove(index1);
        arrList.add(index1, arrList.get(index2 - 1));
        arrList.remove(index2);
        arrList.add(index2, temp);

        return arrList;
    }

    static String threea (int[] arr)
    {
        List<Integer> earr = new ArrayList<>();
        List<Integer> oarr = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            if (arr[i] % 2 == 0)
            {
                earr.add(arr[i]);
            }
            else
            {
                oarr.add(arr[i]);
            }
        }

        String str = "evenNumbers = " + earr + "\noddNumbers = " + oarr;

        return str;
    }

    static String threeb (int[] arr)
    {
        List<Integer> parr = new ArrayList<>();
        List<Integer> nparr = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            for (int j = 2; j <= arr[i]/2; ++j)
            {
                if ((arr[i] % j) == 0)
                {
                    nparr.add(arr[i]);
                    break;
                }
            }

            if (!nparr.contains(arr[i]))
            {
                parr.add(arr[i]);
            }
        }

        String str = "primeNumbers = " + parr + "\nnon-primeNumbers = " + nparr;

        return str;
    }

    static String foura (String[] arr)
    {
        List<String> cap = new ArrayList<>();
        List<String> nocap = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) 
        {
            if (arr[i].charAt(0) < 97)
            {
                cap.add(arr[i]);
            }
            else
            {
                nocap.add(arr[i]);
            }
        }

        String str = "capLetterList = " + cap + "\nsmallLetterList = " + nocap;

        return str;
    }

    static String[] fourb (String[] arr)
    {
        Arrays.sort(arr, Collator.getInstance());

        return arr;
    }

    static String seven (String text, int width)
    {
        String str = "";
        List<String> format = new ArrayList<>();

        while(text.length() > width)
        {
            if (text.charAt(width) == ' ')
            {
                format.add(text.substring(0, width));
                text = text.replace(text.substring(0, width + 1), "");
            }
            else
            {
                for (int i = width - 1; i >= 0; --i) 
                {
                    if (text.charAt(i) == ' ')
                    {
                        format.add(text.substring(0, i));
                        text = text.replace(text.substring(0, i + 1), "");
                        break;
                    }
                }
            }
        }

        format.add(text);

        for (int i = 0; i < format.size(); ++i) 
        {
            str += format.get(i) + "\n";
        }

        return str;
    }

    static int LongestWordLength(String str)
    {
        
        int n = str.length();
        int res = 0, curr_len = 0;
        
        for (int i = 0; i < n; i++) 
        {
            if (str.charAt(i) != ' ')
            {
                curr_len++;
            }
            else 
            {
                res = Math.max(res, curr_len);
                curr_len = 0;
            }
        }

        return Math.max(res, curr_len);
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Reverse Array\n2. Switch Indexes\n3. Odds and Primes\n4. Capitals and Alphabetization\n5. Student Info\n6. Student Grades\n7. Formatting");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1:
                List<String> arrList = new ArrayList<>();
                String str = "";
                
                while (!str.equals("0"))
                {
                    System.out.println("Enter a word or enter 0 to stop: ");
                    str = sc.next();
                    arrList.add(str);

                    if (arrList.get(arrList.size() - 1).equals("0"))
                    {
                        arrList.remove(arrList.size() - 1);
                    }
                }

                System.out.println(one(arrList));
                break;

            case 2:
                List<String> arrList2 = new ArrayList<>();
                String str2 = "";
                
                while (!str2.equals("0"))
                {
                    System.out.println("Enter a word or enter 0 to stop: ");
                    str2 = sc.next();
                    arrList2.add(str2);

                    if (arrList2.get(arrList2.size() - 1).equals("0"))
                    {
                        arrList2.remove(arrList2.size() - 1);
                    }
                }

                System.out.println("Enter index:");
                int index1 = sc.nextInt();
                System.out.println("Enter index:");
                int index2 = sc.nextInt();

                System.out.println(two(arrList2, index1, index2));
                break;

            case 3:
                int[] arr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
                System.out.println(threea(arr));
                System.out.println(threeb(arr));
                break;

            case 4:
                String[] sarr = {"apple", "cherry", "Olivia", "Catherine", "banana", "orange", "pear", "Bob", "Patrick", "Allen"};
                System.out.println(foura(sarr));
                System.out.println(Arrays.toString(fourb(sarr)));
                break;
            
            case 5:
                String age = "not entered";
                String name = "not entered"; 
                sc.nextLine();   

                System.out.println("Enter age: ");
                String tempa = sc.nextLine();

                if (!tempa.isEmpty())
                {
                    age = tempa;
                }

                System.out.println("Enter name: ");
                String tempn = sc.nextLine();

                if (!tempn.isEmpty())
                {
                    name = tempn;
                }

                Student student = new Student(age, name);
                break;
            
            case 6:
                List<Student> list = Arrays.asList(new Student("Eddy", 92, 'A'), new Student("Edward", 88, 'A'), new Student("Jessie", 76, 'B'), new Student("James", 78, 'B'), new Student("Jim", 77, 'B'), new Student("Alvin", 85, 'A'));
                List<Student> list2 = Arrays.asList(new Student("Eddy", 92, 'A'), new Student("Edward", 88, 'A'), new Student("Jessie", 76, 'B'), new Student("James", 78, 'B'), new Student("Jim", 77, 'B'), new Student("Alvin", 85, 'A'));

                Collections.sort(list, new sortByName());

                System.out.println("Mark\tGrade\tName");
                System.out.println("====\t=====\t====");

                for (int i = 0; i < list.size(); ++i) 
                {
                    System.out.println(list.get(i).mark + "  \t" + list.get(i).grade + "    \t" + list.get(i).name);   
                }

                Collections.sort(list2, new sortByMgark());

                System.out.println("Mark\tGrade\tName");
                System.out.println("====\t=====\t====");

                for (int i = 0; i < list2.size(); ++i) 
                {
                    System.out.println(list2.get(i).mark + "  \t" + list2.get(i).grade + "    \t" + list2.get(i).name);   
                }
                break;

            case 7:
                // The output will not be correct acording to the assignment, but this is actually more correct, as it removes spaces
                sc.nextLine();
                System.out.println("Enter text: ");
                String text = sc.nextLine();
                System.out.println("Enter formatting width: ");
                int temp = sc.nextInt();
                int longest = LongestWordLength(text);
                int width = temp;

                while(temp < longest)
                {
                    System.out.println("INVALID, formatting width must be greater than " + longest);
                    System.out.println("Enter formatting width: ");
                    temp = sc.nextInt();
                    width = temp;
                }
                

                System.out.println(seven(text, width));
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "ERROR");
                return;
        }
    }
}