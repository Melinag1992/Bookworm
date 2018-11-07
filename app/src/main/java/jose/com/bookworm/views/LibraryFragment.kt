package jose.com.bookworm.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jose.com.bookworm.adapter.BookAdapter
import jose.com.bookworm.model.Book
import jose.com.bookworm.model.BookViewModel
import jose.com.bookworm.presentations.LibraryPresentation
import jose.com.bookworm.presenters.LibraryPresenter

class LibraryFragment : Fragment(), LibraryPresentation {
  lateinit var presenter: LibraryPresenter
  private lateinit var adapter: BookAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val model = ViewModelProviders.of(this).get(BookViewModel::class.java)
    model.getBooks().observe(this, Observer<List<Book>> { books ->
      adapter = BookAdapter(books)
    })
  }

  override fun onStart() {
    super.onStart()

    presenter.attach(this)
  }

  override fun onStop() {
    super.onStop()

    presenter.detach()
  }

  override fun showBookDeleted() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun hideLoading() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun hideEmptyLibrary() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun showSortedBooks() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}