package br.com.gutendex.service;

import br.com.gutendex.entities.Author;
import br.com.gutendex.entities.Book;
import br.com.gutendex.repository.AuthorRepository;
import br.com.gutendex.repository.BookRepository;import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {
    private static final String GUTENDEX_API_URL = "https://gutendex.com/books/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book fetchAndSaveBook(Long bookId) {
        String url = GUTENDEX_API_URL + bookId;
        GutendexBookResponse response = restTemplate.getForObject(url, GutendexBookResponse.class);

        if (response != null) {
            return saveBookFromResponse(response);
        }
        return null;
    }

    public List<Book> searchAndSaveBooks(String searchTerm) {
        String url = GUTENDEX_API_URL + "?search=" + searchTerm;
        GutendexSearchResponse response = restTemplate.getForObject(url, GutendexSearchResponse.class);

        if (response != null && response.getResults() != null) {
            return response.getResults().stream()
                    .map(this::saveBookFromResponse)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public List<Book> getAllBooksFromDatabase() {
        return bookRepository.findAll();
    }

    private Book saveBookFromResponse(GutendexBookResponse response) {
        // Verifica se o livro já existe
        Book existingBook = bookRepository.findById(response.getId()).orElse(null);
        if (existingBook != null) {
            return existingBook;
        }

        // Cria o livro
        Book book = new Book();
        book.setId(response.getId());
        book.setTitle(response.getTitle());
        book.setDownloadCount(response.getDownloadCount());

        // Processa os formatos para obter a URL de download
        if (response.getFormats() != null) {
            if (response.getFormats().getTextHtml() != null) {
                book.setMediaType("text/html");
                book.setDownloadUrl(response.getFormats().getTextHtml());
            } else if (response.getFormats().getTextPlain() != null) {
                book.setMediaType("text/plain");
                book.setDownloadUrl(response.getFormats().getTextPlain());
            } else if (response.getFormats().getApplicationPdf() != null) {
                book.setMediaType("application/pdf");
                book.setDownloadUrl(response.getFormats().getApplicationPdf());
            }
        }

        // Processa idiomas
        if (response.getLanguages() != null) {
            book.setLanguages(Arrays.asList(response.getLanguages()));
        }

        // Processa assuntos
        if (response.getSubjects() != null) {
            book.setSubjects(Arrays.asList(response.getSubjects()));
        }

        // Processa autores
        if (response.getAuthors() != null) {
            List<Author> authors = response.getAuthors().stream()
                    .map(authorResponse -> {
                        Author author = authorRepository.findByName(authorResponse.getName())
                                .orElseGet(() -> {
                                    Author newAuthor = new Author();
                                    newAuthor.setName(authorResponse.getName());
                                    newAuthor.setBirthYear(authorResponse.getBirthYear());
                                    newAuthor.setDeathYear(authorResponse.getDeathYear());
                                    return authorRepository.save(newAuthor);
                                });
                        return author;
                    })
                    .collect(Collectors.toList());

            book.setAuthors(authors);
        }

        return bookRepository.save(book);
    }
}

// Classes de resposta da API movidas para fora da classe GutendexService

class GutendexSearchResponse {
    private List<GutendexBookResponse> results;

    public List<GutendexBookResponse> getResults() {
        return results;
    }

    public void setResults(List<GutendexBookResponse> results) {
        this.results = results;
    }
}

class GutendexBookResponse {
    private Long id;
    private String title;
    private List<GutendexAuthorResponse> authors;
    private String[] languages;
    private String[] subjects;
    private Integer downloadCount;
    private GutendexFormats formats;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<GutendexAuthorResponse> getAuthors() { return authors; }
    public void setAuthors(List<GutendexAuthorResponse> authors) { this.authors = authors; }
    public String[] getLanguages() { return languages; }
    public void setLanguages(String[] languages) { this.languages = languages; }
    public String[] getSubjects() { return subjects; }
    public void setSubjects(String[] subjects) { this.subjects = subjects; }
    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }
    public GutendexFormats getFormats() { return formats; }
    public void setFormats(GutendexFormats formats) { this.formats = formats; }
}

class GutendexAuthorResponse {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }
}

class GutendexFormats {
    private String textHtml;
    private String textPlain;
    private String applicationPdf;

    // Getters e Setters
    public String getTextHtml() { return textHtml; }
    public void setTextHtml(String textHtml) { this.textHtml = textHtml; }
    public String getTextPlain() { return textPlain; }
    public void setTextPlain(String textPlain) { this.textPlain = textPlain; }
    public String getApplicationPdf() { return applicationPdf; }
    public void setApplicationPdf(String applicationPdf) { this.applicationPdf = applicationPdf; }
}
