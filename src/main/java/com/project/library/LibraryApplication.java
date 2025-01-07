package com.project.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.library.entity.Book;
import com.project.library.entity.User;
import com.project.library.repository.BooksRepository;
import com.project.library.repository.UsersRepository;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	UsersRepository usersRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book[] books = new Book[] {
				new Book("Il Buio Oltre la Siepe", "Harper Lee", "Narrativa", 1960, true),
				new Book("1984", "George Orwell", "Distopico", 1949, false),
				new Book("Il Grande Gatsby", "F. Scott Fitzgerald", "Classico", 1925, true),
				new Book("Il Giovane Holden", "J.D. Salinger", "Classico", 1951, true),
				new Book("Il Mondo Nuovo", "Aldous Huxley", "Distopico", 1932, false)
		};

		for (Book book : books) {
			this.booksRepository.save(book);
		}

		User[] users = new User[] {
			new User("dicralex"),
			new User("urbanmario"),
			new User("jgiota"),
			new User("hermlos"),
			new User("gojji")
		};

		for (User user : users) {
			this.usersRepository.save(user);
		}
	}

}
