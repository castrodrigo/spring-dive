package castrodrigo.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import castrodrigo.springframework.spring5webapp.domain.Author;
import castrodrigo.springframework.spring5webapp.domain.Book;
import castrodrigo.springframework.spring5webapp.domain.Publisher;
import castrodrigo.springframework.spring5webapp.repositories.AuthorRepository;
import castrodrigo.springframework.spring5webapp.repositories.BookRepository;
import castrodrigo.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;

  }

  @Override
  public void run(String... args) throws Exception {

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Drive Design", "123321123");
    Publisher rocco = new Publisher("Some Street", "NY", "NY", "91020");

    publisherRepository.save(rocco);

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    ddd.setPublisher(rocco);
    rocco.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);
    publisherRepository.save(rocco);

    Author rod = new Author("Rod", "Johson");
    Book noEJB = new Book("J2EE Development without EJB", "37298423984");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    noEJB.setPublisher(rocco);
    rocco.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(rocco);

    System.out.println("Bootstrap has started");
    System.out.println("Nummber of Books:" + bookRepository.count());
    System.out.println("Nummber of Authors:" + authorRepository.count());
    System.out.println("Nummber of Publisher:" + publisherRepository.count());
    System.out.println("Books of Publisher:" + rocco.getBooks().size());
  }

}