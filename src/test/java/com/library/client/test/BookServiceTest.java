/**
 * 
 */
package com.library.client.test;

/**
 * @author ANIZAM
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.client.model.Author;
import com.library.client.model.Book;
import com.library.client.repository.BookRepository;
import com.library.client.service.impl.BookServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
		
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private Book book;
		
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Author author = new Author();
		author.setId(1);
		author.setName("Abror");
		book = new Book();
		book.setId(1);
		book.setIdbook("BOOK1");
		book.setTitle("Meraih Mimpi");
		book.setDescripiton("Desc book");
		book.setYear("2018");
		book.setAvailability(10);
		book.setAuthor(author);
		book.setStatus(true);
		bookServiceImpl = new BookServiceImpl();
		bookServiceImpl.setbookRepository(bookRepository);
	}

	@Test
	public void shouldReturnbook_whenSavebookValid() throws Exception {
		when(bookRepository.save(book)).thenReturn(book);
		Book bookSaved = bookServiceImpl.saveBook(book);
		System.out.println("*** SAVE : " + bookSaved.getIdbook());
		assertEquals(book, bookSaved);
	}
	
	@Test
	public void shouldReturnListbook_whenMethodListbookCalled() throws Exception {
		List<Book> listbook = new ArrayList<Book>();
		listbook.add(book);
		when(bookRepository.findAll()).thenReturn(listbook);
		List<Book> listbookSaved = bookServiceImpl.findAll();
		System.out.println("*** COUNT : " + listbookSaved.size());
		assertEquals(1, listbookSaved.size());
	}
	
	@Test
	public void shouldReturnbook_whenKdbookValid() throws Exception {
		when(bookRepository.findByIdbook("BOOK1")).thenReturn(book);
		Book bookFound = bookServiceImpl.findByIdbook("BOOK1");
		System.out.println("*** FOUND : " + bookFound.getIdbook());
		assertEquals(book, bookFound);
	}
	
	@Test
	public void shouldReturnbookNotFound_whenKdbookInvalid() throws Exception {
		when(bookRepository.findByIdbook("BOOK1")).thenReturn(book);
		Book bookNotFound = bookServiceImpl.findByIdbook("BOOK2");
		System.out.println("*** NOT FOUND : " + bookNotFound);
		assertNull(bookNotFound);
	}
	
	@Test
	public void shouldReturnbookEdited_whenMethodEditbookCalled() throws Exception {
		when(bookRepository.findByIdbook("BOOK1")).thenReturn(book);
		book.setIdbook("BOOK2");
		Book bookEdited = bookServiceImpl.findByIdbook("BOOK1");
		System.out.println("*** AFTER EDIT : " + bookEdited.getIdbook());
		assertEquals("BOOK2", bookEdited.getIdbook());
	}
	
	@Test
	public void shouldDeletebook_whenDeletebookValid() throws Exception {
		doNothing().when(bookRepository).delete(book);
		bookServiceImpl.deleteBook("BOOK1");
		Book bookDeleted = bookServiceImpl.findByIdbook("BOOK1");
		System.out.println("*** DELETE : " + bookDeleted);
		assertNull(bookDeleted);
	}
	
	@Test
	public void sholdReturnTotalbook_whenbookBorrowed() throws Exception {
		int totalbook = book.getAvailability();
		int borrow = 2;
		int left = totalbook - borrow;
		when(bookRepository.findByIdbook("BOOK1")).thenReturn(book);		
		bookServiceImpl.updateBookLeft("BOOK1", left);
		Book bookTotal = bookServiceImpl.findByIdbook("BOOK1");
		System.out.println("*** TOTAL BOOK : " + bookTotal.getAvailability());
		assertEquals(8, left);
	}
}
