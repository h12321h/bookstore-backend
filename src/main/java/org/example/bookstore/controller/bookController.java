package org.example.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class bookController {
    private static class Book{
        int id;
        String title;
        String author;
        String publisher;
        String introduction;
        int price;

        public Book(int id, String title, String author, String publisher, String introduction, int price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.introduction = introduction;
            this.price = price;
        }
        public int getId() {
            return id;
        }
        public String getTitle() {
            return title;
        }
        public String getAuthor() {
            return author;
        }
        public String getPublisher() {
            return publisher;
        }
        public String getIntroduction() {
            return introduction;
        }
        public int getPrice() {
            return price;
        }

    }
    private static List<Book> books = new ArrayList<>();
    static {
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", "The story of the mysteriously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan.", 180));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", "J. B. Lippincott & Co.", "The story is set in the Great Depression, and it follows the story of... ", 200));
        books.add(new Book(3, "1984", "George Orwell", "Secker & Warburg", "The story of Winston Smith, a member of the Outer Party who works for the Ministry of Truth.", 220));
        books.add(new Book(4, "Pride and Prejudice", "Jane Austen", "T. Egerton, Whitehall", "The story follows the main character Elizabeth Bennet as she deals with issues of manners, upbringing, morality, education, and marriage in the society of the landed gentry of the British Regency.", 250));
        books.add(new Book(5, "The Catcher in the Rye", "J. D. Salinger", "Little, Brown and Company", "The story is set around the 1950s and is narrated by a young ...", 230));
        books.add(new Book(6, "The Hobbit", "J. R. R. Tolkien", "George Allen & Unwin", "The story is set in a time between the Dawn of Færie and...", 300));
        books.add(new Book(7, "The Lord of the Rings", "J. R. R. Tolkien", "George Allen & Unwin", "The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work.", 350));
        books.add(new Book(8, "Animal Farm", "George Orwell", "Secker & Warburg", "The story is set on Manor Farm, where the animals rise up against the drunken and irresponsible farmer Mr. Jones.", 200));
        books.add(new Book(9, "Brave New World", "Aldous Huxley", "Chatto & Windus", "The story is set in a futuristic World State, whose citizens are environmentally engineered into an intelligence-based social hierarchy.", 250));
        books.add(new Book(10, "The Da Vinci Code", "Dan Brown", "Doubleday", "The story follows Robert Langdon, a professor of religious symbology, who is investigating...", 300));
        books.add(new Book(11, "The Little Prince", "Antoine de Saint-Exupéry", "Reynal & Hitchcock", "The story follows a young prince who visits various planets in space, including Earth, and addresses themes of loneliness, friendship, love, and loss.", 180));
        books.add(new Book(12, "The Alchemist", "Paulo Coelho", "HarperTorch", "The story follows a young Andalusian shepherd named Santiago in his journey to Egypt, after having a recurring dream of finding treasure there.", 200));
        books.add(new Book(13, "The Chronicles of Narnia", "C. S. Lewis", "Geoffrey Bles", "The story is set in the fictional world of Narnia, a fantasy world of magic, mythical beasts, and talking animals.", 250));
        books.add(new Book(14, "The Picture of Dorian Gray", "Oscar Wilde", "Ward, Lock & Co.", "The story is set in London and follows the young... ", 220));
        books.add(new Book(15, "The Adventures of Sherlock Holmes", "Arthur Conan Doyle", "George Newnes", "The story is set in London and follows the detective Sherlock Holmes and his companion Dr. John Watson.", 230));
        books.add(new Book(16, "The Great Expectations", "Charles Dickens", "Chapman & Hall", "The story follows the life of an orphan named Pip, who lives with his sister and her husband in the Kent marshes.", 250));
    }


    @GetMapping("/books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam("type") String type,@RequestParam("query") String query){
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (type.equals("title") && book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            } else if (type.equals("author") && book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            } else if (type.equals("publisher") && book.getPublisher().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
}
