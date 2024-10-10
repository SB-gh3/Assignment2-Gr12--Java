import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

class ArrayTester
{
    static int[] getColumn(int[][] arr, int col)
    {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; ++i)
        {
            result[i] = arr[i][col - 1];
        }

        return result;
    }

    static boolean isDuplicate(int[][] arr, int[] arr2)
    {
        for (int i = 0; i < arr.length; ++i)
        {
            for (int j = 0; j < arr[i].length; ++j)
            {
                for (int k = 0; k < arr2.length; ++k)
                {
                    if(arr[i][j] == arr2[k])
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean containsFull(int[][] arr, int[] arr2)
    {
        boolean check = false;

        for (int i = 0; i < arr.length; ++i)
        {
            for (int j = 0; j < arr[i].length; ++j)
            {
                if(arr[i][j] == arr2[j])
                {
                    check = true;
                }
                else
                {
                    break;
                }
            }
        }

        if(check)
        {
            return true;
        }

        return false;
    }
}

public class Assignment2
{
    static String reverse(String str)
    {
        char ch;
        String revstr = "";

        for (int i = 0; i < str.length(); ++i)
        {
            ch= str.charAt(i); //extracts each character
            revstr = ch + revstr; //adds each character in front of the existing string
        }

        return revstr;
    }

    static String repeat(int count, String with)
    {
        return new String(new char[count]).replace("\0", with);
    }

    static String removeDupe(String str)
    {
        String result = "";
        List<Character> chars = new ArrayList<>();

        for (int i = 0; i < str.length(); ++i)
        {
            if (!chars.contains(str.charAt(i)))
            {
                chars.add(str.charAt(i));
            }
        }

        for (int i = 0; i < chars.size(); ++i)
        {
            result += chars.get(i);
        }

        return result;
    }

    static String one(int number)
    {
        String rome = "";
        String numbers = Integer.toString(number);
        List<Integer> splitNumbers = new ArrayList<>();
        int counter = 0, check = 0;

        for (int i = 0; i < numbers.length(); ++i)
        {
            splitNumbers.add(Character.getNumericValue(numbers.charAt(i)));
        }

        for (int i = splitNumbers.size() - 1; i >= 0; --i)
        {
            check = splitNumbers.get(i) - 5;

            if(counter == 0)
            {
                if(check <= -2)
                {
                    rome = (repeat(check + 5, "I")) + rome;
                }
                else if(check <= 0)
                {
                    rome = (repeat(Math.abs(check), "I")) + "V" + rome;
                }
                else if(check <= 3)
                {
                    rome = "V" + (repeat(check, "I")) + rome;
                }
                else if(check == 4)
                {
                    rome = (repeat(check - 3, "I")) + "X" + rome;
                }
            }
            else if(counter == 1)
            {
                if(check <= -2)
                {
                    rome = (repeat(check + 5, "X")) + rome;
                }
                else if(check <= 0)
                {
                    rome = (repeat(Math.abs(check), "X")) + "L" + rome;
                }
                else if(check <= 3)
                {
                    rome = "L" + (repeat(check, "X")) + rome;
                }
                else if(check == 4)
                {
                    rome = (repeat(check - 3, "X")) + "C" + rome;
                }
            }
            else if(counter == 2)
            {
                if(check <= -2)
                {
                    rome = (repeat(check + 5, "C")) + rome;
                }
                else if(check <= 0)
                {
                    rome = (repeat(Math.abs(check), "C")) + "D" + rome;
                }
                else if(check <= 3)
                {
                    rome = "D" + (repeat(check, "C")) + rome;
                }
                else if(check == 4)
                {
                    rome = (repeat(check - 3, "C")) + "M" + rome;
                }
            }
            else if(counter == 3)
            {
                rome = (repeat(check + 5, "M")) + rome;
            }

            ++counter;
        }

        return rome;
    }

    static double two(List<Integer> numbers)
    {
        double divisor = 0;

        for (int i = 0; i < numbers.size(); ++i)
        {
            divisor += 1.0 / numbers.get(i);
        }

        return (double)Math.round((numbers.size() / divisor) * 100) / 100;
    }

    static String three(String keyword)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        keyword = removeDupe(keyword);

        for (int i = 0; i < keyword.length(); ++i)
        {
            alphabet = alphabet.replace(Character.toString(keyword.charAt(i)), "");
        }

        alphabet = keyword + alphabet;

        return alphabet;
    }

    static List<Integer> four(List<Integer> reaction) throws InterruptedException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("3");
        Thread.sleep(1000);
        System.out.println("2");
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(400);
        System.out.println("GO!");

        int start = (int) System.currentTimeMillis();
        sc.next();
        int end = (int) System.currentTimeMillis() - start;

        reaction.add(end);

        System.out.println("Would you like to go again?(Y/N)");
        char repeat = sc.next().charAt(0);

        if (repeat == 'y' || repeat == 'Y')
        {
            four(reaction);
        }

        Collections.sort(reaction);

        return reaction;
    }

    static boolean five(Random rand, int player, int comp)
    {
        Scanner sc = new Scanner(System.in);

        int num = rand.nextInt(10);
        int cguess = rand.nextInt(10);
        System.out.println("Enter your guess(0 - 10)");
        int pguess = sc.nextInt();

        player -= Math.abs(num - pguess);
        comp -= Math.abs(num - cguess);

        System.out.println("Player Guess: " + pguess + "\nComputer Guess: " + cguess + "\nActual Number: " + num + "\n\nPlayer points: " + player + "\nComputer points: " + comp);

        if(comp <= 0)
        {
            return true;
        }
        else if(player <= 0)
        {
            return false;
        }

        five(rand, player, comp);

        return false;
    }

    static String seven(String word)
    {
        String result = "";
        int wordLen = word.length();

        for (int i = 0; i < wordLen; ++i)
        {
            if (i == (wordLen / 2))
            {
                result += "\n" + word;
            }
            else
            {
                result += "\n" + repeat(wordLen / 2, " ") + word.charAt(i);
            }
        }

        result += "\n";

        for (int i = 0; i < wordLen; ++i)
        {
            if (i == (wordLen / 2))
            {
                result += "\n" + repeat(wordLen / 2, " ") + word.charAt(i);
            }
            else if (i < (wordLen / 2))
            {
                result += "\n" + repeat(i, " ") + word.charAt(i) + repeat(wordLen - (2 * i) - 2, " ") + word.charAt(wordLen - (i + 1));
            }
            else if (i > (wordLen / 2))
            {
                result += "\n" + repeat(wordLen - i - 1, " ") + reverse(word.charAt(i) + repeat(i - (wordLen - i), " ") + word.charAt(wordLen - (i + 1)));
            }
        }

        result += "\n";

        for (int i = 0; i <= (wordLen * 2); ++i)
        {
            if (i <= wordLen)
            {
                result += "\n" + word.substring((i * -1) + wordLen, wordLen);
            }
            else
            {
                result += "\n" + repeat(i - wordLen, " ") + word.substring(0, wordLen - (i - wordLen) + 1);
            }
        }

        for (int i = 0; i <= (wordLen * 2); ++i)
        {
            if (i <= wordLen)
            {
                result += "\n" + repeat(wordLen - i + 1, " ") + word.substring(0, i);
            }
            else
            {
                result += "\n" + word.substring(Math.abs(i - wordLen) - 1, wordLen);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Roman Numerals\n2. Harmonic Mean\n3. Secret Alphabet\n4. Reaction Time Test\n5. Guessing Game\n6. Array Stuff\n7. Four Patterns");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1:
                System.out.println("Enter Number(up to 9,999)");
                int romanNumber = sc.nextInt();

                while(romanNumber > 9999 || romanNumber < 0)
                {
                    System.out.println("Enter Number(up to 9,999)");
                    romanNumber = sc.nextInt();
                }

                System.out.println(one(romanNumber));
                break;

            case 2:
                List<Integer> harMean = new ArrayList<>();
                int temp = 1;

                while (temp != 0)
                {
                    System.out.println("Enter a number (0 to stop)");
                    temp = sc.nextInt();

                    if (temp !=0)
                    {
                        harMean.add(temp);
                    }
                }

                System.out.println(two(harMean));
                break;

            case 3:
                System.out.println("Enter keyword");
                String keyword = sc.next();
                System.out.println(three(keyword));
                break;

            case 4:
                List<Integer> reaction = new ArrayList<>();
                List<Integer> times = four(reaction);

                System.out.println("Best Times\tReaction Time(Milliseconds)");
                System.out.println("==========\t===========================");

                for (int i = 0; i < times.size() || i < 3; ++i)
                {
                    System.out.println("\t" + (i + 1) + "\t\t" + times.get(i));
                }
                break;

            case 5:
                Random rand = new Random();

                if (five(rand, 7, 7))
                {
                    File f = new File("./victorymale-version-230553.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    Thread.sleep(2000);
                }
                else
                {
                    File f = new File("./tf.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    Thread.sleep(7000);
                }
                break;

            case 6:
                int[][] arr2D = {{0,1,2}, {3,4,5}, {6,7,8}, {9,5,3}};
                int[] arr = {1, 2, 0};

                System.out.println(Arrays.toString(ArrayTester.getColumn(arr2D, 3)));
                System.out.println(ArrayTester.isDuplicate(arr2D, arr));
                System.out.println(ArrayTester.containsFull(arr2D, arr));
                break;

            case 7:
                System.out.println("Enter word (odd number of letters)");
                String word = sc.next();

                while(word.length() % 2 == 0)
                {
                    System.out.println("Enter word (odd number of letters)");
                    word = sc.next();
                }

                System.out.println(seven(word));
                break;

            default:
                JOptionPane.showMessageDialog(null, "ERROR");
                return;
        }
    }
}
