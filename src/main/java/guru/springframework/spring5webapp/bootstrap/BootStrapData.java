package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author elline = new Author("Elline", "Medrano");
        Book life = new Book("Life of Elline","14082001172");
        elline.getBooks().add(life);
        life.getAuthors().add(elline);

        authorRepository.save(elline);
        bookRepository.save(life);

        Author sofia = new Author("Sofia", "Angelica");
        Book cutie = new Book("Cutie", "28052010172");
        sofia.getBooks().add(cutie);
        cutie.getAuthors().add(sofia);

        authorRepository.save(sofia);
        bookRepository.save(cutie);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
    }
}
