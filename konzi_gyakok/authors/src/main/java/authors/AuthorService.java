package authors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    public List<AuthorDto> getAuthorsWithBooks() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> modelMapper.map(author, AuthorDto.class)).collect(Collectors.toList());
    }

    public AuthorDto createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    @Transactional
    public AuthorDto addBook(long id, CreateBookCommand command) {
        Book book = new Book(command.getISBN(), command.getTitle());
        Author author = authorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        author.addBook(book);
        return modelMapper.map(author, AuthorDto.class);

    }

    public void deleteAuthor(long id) {
        Author author = authorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        authorRepository.delete(author);
    }
}
