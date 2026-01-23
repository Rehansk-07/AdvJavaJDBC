package com.pack1;

import java.util.Scanner;

public class JdbcPro4 
{
	Scanner sc = new Scanner(System.in);
	void library_menu()
	{
		while(true)
		{
			System.out.println("Welcome to Library management");
			System.out.println("Choose your option");
			System.out.println("1. Add Book to the library");
			System.out.println("2. Retriving specific book");
			System.out.println("3. Deleting specific book");
			System.out.println("4. View all the books");
			System.out.println("5. Exit");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice)
			{
			case 1:
				System.out.println("Adding book to the Library");
				//call the method for adding the book
				
				break;
			case 2:
				System.out.println("Retiriving book from the Library");
				//call the method for retrieving the book basing on bookid
				break;
			case 3:
				System.out.println("Deleting book from the Library");
				//call the method for deleting the book basing on bookid
				break;
				
			case 4:
				System.out.println("View all the books");
				break;
			case 5:
				System.out.println("thank you see you soon");
				System.exit(0);
				break;
			default:
				System.out.println("Choose a valid option");
				
			}
		}
		
	}
	public static void main(String[] args)
	{
		JdbcPro4 obj= new JdbcPro4();
		obj.library_menu();
	}
}
