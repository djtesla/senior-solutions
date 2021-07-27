package authors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    //(id, ISBN szám, cím, szerző)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ISBN;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }
}
