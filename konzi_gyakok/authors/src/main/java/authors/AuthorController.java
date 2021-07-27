package authors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAuthorsWithBooks() {
        return authorService.getAuthorsWithBooks();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody CreateAuthorCommand command) {
        return authorService.createAuthor(command);
    }

    @PostMapping("/{id}/books")
    public AuthorDto addBook(@PathVariable long id, @RequestBody CreateBookCommand command){
        return authorService.addBook(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable long id){
        authorService.deleteAuthor(id);
    }
}

