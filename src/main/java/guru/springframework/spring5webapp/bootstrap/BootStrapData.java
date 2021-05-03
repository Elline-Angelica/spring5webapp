package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher penguin = new Publisher();
        penguin.setName("Penguin Books Publishing");
        penguin.setCity("Brussels");
        penguin.setState("Belgium");

        publisherRepository.save(penguin);
        System.out.println("Publisher count: " + publisherRepository.count());

        Author elline = new Author("Elline", "Medrano");
        Book life = new Book("Life of Elline","14082001172");
        elline.getBooks().add(life);
        life.getAuthors().add(elline);

        life.setPublisher(penguin);
        penguin.getBooks().add(life);

        authorRepository.save(elline);
        bookRepository.save(life);
        publisherRepository.save(penguin);

        Author sofia = new Author("Sofia", "Angelica");
        Book cutie = new Book("Cutie", "28052010172");
        sofia.getBooks().add(cutie);
        cutie.getAuthors().add(sofia);

        authorRepository.save(sofia);
        bookRepository.save(cutie);
        publisherRepository.save(penguin);

        cutie.setPublisher(penguin);
        penguin.getBooks().add(cutie);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + penguin.getBooks().size());
    }
}
