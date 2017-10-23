package jose.com.bookworm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import jose.com.bookworm.model.Book;
import jose.com.bookworm.model.Book_;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText titleEditText;
    private EditText authorEditText;
    private Button addBookButton;
    private Button getBookButton;
    private Box<Book> bookBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_layout);
        setTitle("BookWorm");

        BoxStore boxStore =((App) getApplication()).getBoxStore();
        bookBox = boxStore.boxFor(Book.class);

        textView = (TextView) findViewById(R.id.textView);
        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        authorEditText = (EditText) findViewById(R.id.author_edit_text);
        addBookButton = (Button) findViewById(R.id.add_book_button);
        getBookButton = (Button) findViewById(R.id.button);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setTitle(titleEditText.getText().toString());
                book.setAuthor(authorEditText.getText().toString());
                addBook(book);
            }
        });

        getBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryBuilder<Book> queryBuilder = bookBox.query();
                queryBuilder.equal(Book_.title,titleEditText.getText().toString());
                Query<Book> query = queryBuilder.build();
                String title = query.findFirst() == null ? "No Books": query.findFirst().getTitle();
                textView.setText(title);
            }
        });

    }

    void addBook(Book book) {
        bookBox.put(book);
    }
}
