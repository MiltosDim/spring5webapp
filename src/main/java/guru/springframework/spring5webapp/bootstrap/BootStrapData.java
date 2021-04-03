package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class BootStrapData implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger( BootStrapData.class.getName() );

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

        Publisher publisher = new Publisher();
        publisher.setName("Test publisher");
        publisher.setCity("Lavrio");

        publisherRepository.save(publisher);
        System.err.println("Publisher : " + publisher);
        System.err.println("Number of publishers : " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Test book","121233");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book spring = new Book("Spring book","3434342");
        rod.getBooks().add(spring);
        spring.getAuthors().add(rod);

        spring.setPublisher(publisher);
        publisher.getBooks().add(spring);

        authorRepository.save(rod);
        bookRepository.save(spring);
        publisherRepository.save(publisher);


        LOGGER.warning("SWS");
        System.err.println("Started in BootStrap");
        System.err.println("Number of books : " + bookRepository.count());
        System.err.println("Publisher number of Books : " + publisher.getBooks().size());

    }
}
