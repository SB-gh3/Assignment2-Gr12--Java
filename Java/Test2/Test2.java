import java.util.Scanner;
import javax.swing.JOptionPane;

interface Shape
{
    double calculatePerimeter();
    double calculateArea();
}

class Rectangle implements Shape
{
    double length;
    double width;

    Rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculatePerimeter()
    {
        return (this.length + this.width) * 2;
    }

    @Override
    public double calculateArea() 
    {
        return this.length * this.width;
    }

}

class Circle implements Shape
{
    double radius;

    Circle(double radius)
    {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() 
    {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public double calculateArea() 
    {
        return Math.PI * this.radius * this.radius;
    }
}

class Animal
{
    public void eat()
    {
        System.out.println("\nThe animal eats");
    }

    public void move()
    {
        System.out.println("\nThe animal moves");
    }
}

class Cheetah extends Animal
{
    public void eat()
    {
        System.out.println("\nThe cheetah eats");
    }

    public void move()
    {
        System.out.println("\nThe cheetah moves");
    }
}

class Turtle extends Animal
{
    public void eat()
    {
        System.out.println("\nThe turtle eats");
    }
}

public class Test2
{
    public static void main(String[] args) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Shapes\n2. Animals");
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1 -> 
            {
                Rectangle rec = new Rectangle(3, 4);
                Circle cir = new Circle(5);
                System.out.println("Rectangle Area = " + rec.calculateArea() + "\nRectangle Perimeter = " + rec.calculatePerimeter() + "\n\nCircle Area = " + Math.round(cir.calculateArea() * 100.) / 100. + "\nCircle Circumference = " + Math.round(cir.calculatePerimeter() * 100.) / 100.);
            }

            case 2 ->
            {
                Cheetah chester = new Cheetah();
                Turtle terrence = new Turtle();
                chester.eat();
                chester.move();
                terrence.eat();
                terrence.move();
            }

            default -> 
            {
                JOptionPane.showMessageDialog(null, "ERROR");
                return;
            }
        }
    }
}
